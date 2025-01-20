import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FormulaParserTest {

    public enum FunctionType {
        CNTOP {
            public BigDecimal cntop() {
                return super.cntop();
            }
        },
        SUMOP {
            public BigDecimal sumop() {
                return super.sumop();
            }
        },          // 2.555
        RESERV {
            public BigDecimal reserv() {
                return super.reserv();
            }
        },         // 0.8888888
        ROUNDCOST {
            public BigDecimal roundcost(BigDecimal a) {
                return super.roundcost(a);
            }
        },      //  /4
        ROUNDOPERSUM {
            public BigDecimal roundopersum(BigDecimal a) {
                return super.roundopersum(a);
            }
            // round(2)
        };
        public BigDecimal cntop() {
            return BigDecimal.valueOf(-0.11);
        }

        public BigDecimal sumop() {
            return new BigDecimal("2.555");
        }

        public BigDecimal reserv() {
            return new BigDecimal("0.888888");
        }

        public BigDecimal roundcost(BigDecimal a) {
            BigDecimal divisor = new BigDecimal(4);
            return a.divide(divisor);
        }

        public BigDecimal roundopersum(BigDecimal a) {
            MathContext m = new MathContext(3);
            return a.round(m);
        }
    }

    final String[] functionsWithArgs = {"ROUNDCOST", "ROUNDOPERSUM"};

    public String validate(String formula) {       //  убрать пустые скобки и пробелы

        formula = emptyBracketsRemove(formula);
        formula = spacesRemove(formula);

        return formula;
    }

    public String emptyBracketsRemove(String formula) {
        if (formula.contains("(")) {
            char[] formulaArray = formula.toCharArray();

            for (int i = 0; i < formulaArray.length; i++) {
                if (i+1 < formulaArray.length && formulaArray[i] == '(' && formulaArray[i+1] == ')') {
                    String newFormula = formula.substring(0, i) + formula.substring(i+2);

                    return emptyBracketsRemove(newFormula);
                }
            }
        }
        return formula;
    }

    public String spacesRemove(String formula) {
        if (formula.contains(" ")) {
            char[] formulaArray = formula.toCharArray();

            for (int i = 0; i < formulaArray.length; i++) {
                if (i+1 < formulaArray.length && formulaArray[i] == ' ' && formulaArray[i+1] == ' ') {
                    String newFormula = formula.substring(0, i) + formula.substring(i+1);

                    return spacesRemove(newFormula);
                }
            }
        }
        return formula;
    }

    public BigDecimal calculate(String formula) {
        if (formula.isEmpty()) {
            return null;
        }
        List<String> operations = formulaSplit(formula);

        return checkSimpleMath(operations);
    }

    public List<String> formulaSplit(String formula) {
        List<String> operations = new ArrayList<>();

        if (formula.contains(" ")) {
            char[] formulaArray = formula.toCharArray();

            int operStart = 0;

            for (int i = 0; i < formulaArray.length; i++) {

                if (i+1 < formulaArray.length && i > 0
                        && formulaArray[i] == ' ' && (formulaArray[i-1] == '(')) {

                    String buffer = formula.substring(operStart, i);
                    if (Arrays.stream(functionsWithArgs).anyMatch(buffer::contains)) {

                        int leftBracket = 1;
                        int rightBracket = 0;

                        while (leftBracket != rightBracket && i < formulaArray.length) {

                            if (formulaArray[i] == '(') leftBracket++;
                            if (formulaArray[i] == ')') rightBracket++;
                            i++;
                        }

                        operations.add(formula.substring(operStart, i));
                        operStart = i+1;
                    }

                } else if (i + 1 < formulaArray.length && i > 0 && formulaArray[i] == ' ') {
                    operations.add(formula.substring(operStart, i));
                    operStart = i+1;
                } else if (i + 1 == formulaArray.length && formulaArray[i] != ' ') {
                    operations.add(formula.substring(operStart, i + 1));
                }
            }
            return operations;
        } else {
            operations.add(formula);
            return operations;
        }

    }

    public BigDecimal checkSimpleMath(List<String> operations) {       //  start calculating
        BigDecimal res = new BigDecimal("0");

        for (int i = 0; i < operations.size(); i++) {
            String cur = operations.get(i);

            int key = typeCheck(cur);
            System.out.println(cur + " is current op and its code is " + key);

            if (key == 1) {
                BigDecimal bufferDec = new BigDecimal(cur);
                res = res.add(bufferDec);
            }
            if (key == 2) {
                String nextOpStr = operations.get(i+1);

                BigDecimal nextOp = new BigDecimal(0);
                int key2 = typeCheck(nextOpStr);

                System.out.println(nextOpStr + " is current op and its code is " + key2);

                if (key2 == 1) {
                    nextOp = nextOp.add(new BigDecimal(nextOpStr));
                } else if (key2 == 3) {
                    nextOp = nextOp.add(functionUse(nextOpStr));
                    System.out.println(" next arg is " + nextOp);
                } else {
                    System.out.println("Operations like -- or *- are not supported!");
                    return null;
                }

                if (cur.equals("+")) {
                    res = res.add(nextOp);
                    i++;
                } else if (cur.equals("-")) {
                    res = res.subtract(nextOp);
                    i++;
                } else if (cur.equals("*")) {
                    res = res.multiply(nextOp);
                    i++;
                } else if (cur.equals("/")) {
                    if (nextOp.doubleValue() == 0) {
                        System.out.println("Incorrect input of " + nextOp);
                        return null;
                    } else {
                        res = res.divide(nextOp);
                        i++;
                    }
                } else {
                    System.out.println("Incorrect input of " + cur);
                    return null;
                }
            }
            if (key == 3) {
                res = res.add(functionUse(cur));
            }
        }
        return res;
    }

    public int typeCheck(String operation) {

        if (operation.matches("-?\\d+(\\.\\d+)?")) {
            return 1;
        } else if (operation.matches("^(\\+|-|\\*|/)$")) {
            return 2;
        } else if (operation.matches("^(CNTOP|SUMOP|RESERV|ROUNDCOST|ROUNDOPERSUM).*")) {
            return 3;
        }
        return 9;
    }

    public BigDecimal functionUse(String operation) {
        BigDecimal errorCode = new BigDecimal(50);

        if (operation.equals("CNTOP")) {
            return FunctionType.CNTOP.cntop();
        } else if (operation.equals("SUMOP")) {
            return FunctionType.SUMOP.sumop();
        } else if (operation.equals("RESERV")) {
            return FunctionType.RESERV.reserv();
        } else if (operation.contains("ROUNDCOST")) {
            int iBegin = operation.indexOf("(");
            String arg = operation.substring(iBegin + 2, operation.length() - 2);

            BigDecimal argValue = calculate(arg);

            return FunctionType.ROUNDCOST.roundcost(argValue);
        } else if (operation.contains("ROUNDOPERSUM")) {
            int iBegin = operation.indexOf("(");
            String arg = operation.substring(iBegin + 2, operation.length() - 2);

            BigDecimal argValue = calculate(arg);

            return FunctionType.ROUNDOPERSUM.roundopersum(argValue);
        }
        return errorCode;
    }

    @Test
    void test1() {
        String f1 = "2 + CNTOP + CNTOP +  ROUNDOPERSUM( RESERV + 1 )";
//        String f1 = "ROUNDCOST( 1.333 + ROUNDCOST( SUMOP ))";

        String f2 = validate(f1);

//        System.out.println(f1);
//        System.out.println(f2);

        BigDecimal result = calculate(f2);
        System.out.println(result);

//        assertEquals(BigDecimal.valueOf(3.67), result);
    }
}