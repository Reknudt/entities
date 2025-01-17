import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FormulaParserTest {

    public enum FunctionType {
        CNTOP {
            public double apply(double a) { // -0.11
                return -0.11;
            }
        },
        SUMOP {
            public double apply(double a) {
                return 2.555;
            }
        },          // 2.555
        RESERV {
            public double apply(double a) {
                return 0.888888;
            }
        },         // 0.8888888
        ROUNDCOST {
            public double apply(double a) {
                return a / 4;
            }
        },      //  /4
        ROUNDOPERSUM {
            public double apply(double a) {
                return Math.round (a * 100.0) / 100.0;
            }
            // round(2)
        };
        abstract double apply(double a);
    }

    String validate(String formula) {       //  убрать пустые скобки и пробелы

        formula = emptyBracketsRemove(formula);
        formula = spacesRemove(formula);

        return formula;
    }

    String emptyBracketsRemove(String formula) {
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

    String spacesRemove(String formula) {
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

    BigDecimal calculate(String formula) {
        if (formula.isEmpty()) {
            return null;
        }

        String[] operations = formula.split("\\s+");

        double res = checkSimpleMath(operations);

        return BigDecimal.valueOf(res);
    }

    double checkSimpleMath(String[] operations) {       //  start calculating

        double res = 0;

        for (int i = 0; i < operations.length; i++) {
            String cur = operations[i];

            int key = typeCheck(cur);
            System.out.println(operations[i] + " is current op and its code is " + key);

            if (key == 1) {
                res += Double.parseDouble(cur);
            }
            if (key == 2) {
                String nextOpStr = operations[i+1];
                double nextOp = 0;
                int key2 = typeCheck(nextOpStr);
                System.out.println(nextOpStr + " is current op and its code is " + key2);
                if (key2 == 1) {
                    nextOp += Double.parseDouble(nextOpStr);
                } else if (key2 == 3) {
                    nextOp += functionUse(cur);
                } else {
                    System.out.println("Operations like -- or *- are not supported!");
                    return 0;
                }

                if (cur.equals("+")) {
                    res = res + nextOp;
                    i++;
                } else if (cur.equals("-")) {
                    res = res - nextOp;
                    i++;
                } else if (cur.equals("*")) {
                    res = res * nextOp;
                    i++;
                } else if (cur.equals("/")) {
                    if (nextOp == 0) {
                        System.out.println("Incorrect input od " + nextOp);
                        return 0;
                    } else {
                        res = res/nextOp;
                        i++;
                    }
                } else {
                    System.out.println("Incorrect input of " + cur);
                    return 0;
                }
            }
            if (key == 3) res += functionUse(cur);
        }



//            if (cur.matches("-?\\d+(\\.\\d+)?")) {          //check if not double (float?)
//                res += Double.parseDouble(cur);
//
//            } else if (cur.length() != 1) {             // check if not function
//
//                if (cur.equals("CNTOP")) {
//                    res += FunctionType.CNTOP.apply(i);
//                } else if (cur.equals("SUMOP")) {
//                    res += FunctionType.SUMOP.apply(i);
//                } else if (cur.equals("RESERV")) {
//                    res += FunctionType.RESERV.apply(i);
//                } else if (cur.contains("ROUNDCOST")) {
//                    int iBegin = cur.indexOf("(");
//                    String value = cur.substring(iBegin + 1, cur.length() - 1);                 //надо проверить не является ли аргумент функцией
//                    res += FunctionType.ROUNDCOST.apply(Double.parseDouble(value));
//                } else if (cur.contains("ROUNDOPERSUM")) {
//                    int iBegin = cur.indexOf("(");
//                    String value = cur.substring(iBegin + 1, cur.length() - 1);
//                    res += FunctionType.ROUNDOPERSUM.apply(Double.parseDouble(value));
//                } else {
//                    System.out.println("Incorrect input of " + cur);
//                    return 0;
//                }
//            } else if (cur.length() == 1) {                 // check if not math operation
//                double nextOp = Double.parseDouble(operations[i+1]);
//
//                if (cur.equals("+")) {
//                    res = res + nextOp;
//                        i++;
//                    } else if (cur.equals("-")) {
//                    res = res - nextOp;
//                        i++;
//                    } else if (cur.equals("*")) {
//                        res = res * nextOp;
//                        i++;
//                    } else if (cur.equals("/")) {
//                        if (nextOp == 0) {
//                            System.out.println("Incorrect input od " + nextOp);
//                            return 0;
//                        } else {
//                            res = res/nextOp;
//                            i++;
//                        }
//                    } else {
//                    System.out.println("Incorrect input of " + cur);
//                    return 0;
//                }
//
//            }


        return res;
    }

    int typeCheck(String operation) {

        if (operation.matches("-?\\d+(\\.\\d+)?")) {
            return 1;
        } else if (operation.matches("^(\\+|-|\\*|/)$")) {
            return 2;
        } else if (operation.matches("^(CNTOP|SUMOP|RESERV|ROUNDCOST|ROUNDOPERSUM)")) {
            return 3;
        }
        return 9;
    }

    double functionUse(String operation) {

        double kostyl = 100;

        if (operation.equals("CNTOP")) {
            return FunctionType.CNTOP.apply(kostyl);
        } else if (operation.equals("SUMOP")) {
            return FunctionType.SUMOP.apply(kostyl);
        } else if (operation.equals("RESERV")) {
            return FunctionType.RESERV.apply(kostyl);
        } else if (operation.contains("ROUNDCOST")) {
            int iBegin = operation.indexOf("(");
            String value = operation.substring(iBegin + 1, operation.length() - 2);

            double argValue = typeCheck(value);             // проверка аргумента
            return FunctionType.ROUNDCOST.apply(argValue);
        } else if (operation.contains("ROUNDOPERSUM")) {
            int iBegin = operation.indexOf("(");
            String value = operation.substring(iBegin + 1, operation.length() - 2);

            double argValue = typeCheck(value);
            return FunctionType.ROUNDOPERSUM.apply(argValue);
        }

        return kostyl;
    }

    @Test
    void test1() {
//        String f1 = "ROUNDOPERSUM(ROUNDCOST(4.4444444)) * 2";
        String f1 = "ROUNDOPERSUM(4.444444444)";

        String f2 = validate(f1);

//        System.out.println(f1);
//        System.out.println(f2);

        System.out.println(calculate(f2));
    }

    @Test
    void validateSpacesTest() {
        String f = "CNTOP  +  6";
        String fe = "CNTOP + 6";
        String fa = validate(f);
        assertEquals(fe, fa);
    }

    @Test
    void validateEmptyBracketsTest() {
        String f = "CNTOP() + 6";
        String fe = "CNTOP + 6";
        String fa = validate(f);
        Assertions.assertEquals(fe, fa);
    }

    @Test
    void calculateNumbersWithPlusTest() {
        String f = "2.8888 + 6.1112";
        BigDecimal fe = new BigDecimal(9);
        BigDecimal fa = calculate(f);
        assertTrue(fe.compareTo(fa) == 0);
    }

    @Test
    void calculateNumbersWithMinusTest() {
        String f = "2 - 6";
        BigDecimal fe = new BigDecimal(-4);
        BigDecimal fa = calculate(f);
        assertTrue(fe.compareTo(fa) == 0);
    }

    @Test
    void calculateNumbersWithMultiplyTest() {
        String f = "6 * 2";
        BigDecimal fe = new BigDecimal(12);
        BigDecimal fa = calculate(f);
        assertTrue(fe.compareTo(fa) == 0);
    }

    @Test
    void calculateNumbersWithDivPositiveTest() {
        String f = "6 / 2";
        BigDecimal fe = new BigDecimal(3);
        BigDecimal fa = calculate(f);
        assertTrue(fe.compareTo(fa) == 0);
    }

    @Test
    void calculateNumbersWithDivNegativeTest() {
        String f = "2 / 0";
        BigDecimal fe = new BigDecimal(0);
        BigDecimal fa = calculate(f);
        assertTrue(fe.compareTo(fa) == 0);
    }

    @Test
    void calculateWithFunctionCNTOPTest() {
        String f = "CNTOP + 1.11";
        BigDecimal fe = new BigDecimal(1);
        BigDecimal fa = calculate(f);
//        assertThat(fe, Matchers.comparesEqualTo(fa));
        assertTrue(fe.compareTo(fa) == 0);
    }

    @Test
    void calculateWithFunctionSUMOPTest() {
        String f = "SUMOP - 0.555";
        BigDecimal fe = new BigDecimal(2);
        BigDecimal fa = calculate(f);
//        assertThat(fe, Matchers.comparesEqualTo(fa));
        assertTrue(fe.compareTo(fa) == 0);
    }

    @Test
    void calculateWithFunctionRESERVTest() {
        String f = "RESERV + 0.111112";
        BigDecimal fe = new BigDecimal(1);
        BigDecimal fa = calculate(f);
        assertThat(fe, Matchers.comparesEqualTo(fa));
//        assertTrue(fe.compareTo(fa) == 0);
    }

    @Test
    void calculateWithFunctionROUNDCOSTTest() {
        String f = "ROUNDCOST(8) - 1";
        BigDecimal fe = new BigDecimal(1);
        BigDecimal fa = calculate(f);
//        assertThat(fe, Matchers.comparesEqualTo(fa));
        assertTrue(fe.compareTo(fa) == 0);
    }

    @Test
    void calculateWithFunctionROUNDOPERSUMTest() {          ///
        String f = "ROUNDOPERSUM(2.222222) - 1.22";
        BigDecimal fe = new BigDecimal(1);
        BigDecimal fa = calculate(f);
//        MathContext m = new MathContext(1);
//        BigDecimal fer = fe.round(m);
//        assertThat(fe, Matchers.comparesEqualTo(fa));
        assertTrue(fe.compareTo(fa) == 0);
    }


//    CALCULATE("2 + CNTOP + CNTOP +  ROUNDOPERSUM(RESERV+1)") RETURNS  3.66
}