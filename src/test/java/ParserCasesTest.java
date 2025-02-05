import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserCasesTest {

    public FormulaParserTest formulaParserTest;

    @ParameterizedTest
    @ValueSource(strings = {"CNTOP() + 2.34234", "32.121212  +  CNTOP"})
    void validateTest(String formula) {
//        String formula = "CNTOP() + 2.345";
        String vf = FormulaParserTest.validate(formula);

        System.out.println(vf);
    }

    @ParameterizedTest
    @ValueSource(ints = { 2, 4 })
    void checkEvenNumber(int number) {
        assertEquals(0, number % 2,
                "Supplied number is not an even number");
    }

//    @Test
//    void calculateTest() {
//        String formula = "2.22 + CNTOP";
//        BigDecimal fr = FormulaParserTest.calculate(formula);
//        assertEquals(BigDecimal.valueOf(2.11), fr);
//    }


//
//
//    @Test
//    void validateSpacesTest() {
//        String f = "CNTOP  +  6";
//        String fe = "CNTOP + 6";
//        String fa = formulaParserTest.validate(f);
//        assertEquals(fe, fa);
//    }
//
//    @Test
//    void validateEmptyBracketsTest() {
//        String f = "CNTOP() + 6";
//        String fe = "CNTOP + 6";
//        String fa = formulaParserTest.validate(f);
//        assertEquals(fe, fa);
//    }
//
//    @Test
//    void calculateNumbersWithPlusTest() {
//        String f = "2.8888 + 6.1112";
//        BigDecimal fe = new BigDecimal(9);
//        BigDecimal fa = formulaParserTest.calculate(f);
//        assertTrue(fe.compareTo(fa) == 0);
//    }
//
//    @Test
//    void calculateNumbersWithMinusTest() {
//        String f = "2 - 6";
//        BigDecimal fe = new BigDecimal(-4);
//        BigDecimal fa = formulaParserTest.calculate(f);
//        assertTrue(fe.compareTo(fa) == 0);
//    }
//
//    @Test
//    void calculateNumbersWithMultiplyTest() {
//        String f = "6 * 2";
//        BigDecimal fe = new BigDecimal(12);
//        BigDecimal fa = formulaParserTest.calculate(f);
//        assertTrue(fe.compareTo(fa) == 0);
//    }
//
//    @Test
//    void calculateNumbersWithDivPositiveTest() {
//        String f = "6 / 2";
//        BigDecimal fe = new BigDecimal(3);
//        BigDecimal fa = formulaParserTest.calculate(f);
//        assertTrue(fe.compareTo(fa) == 0);
//    }
//
//    @Test
//    void calculateNumbersWithDivNegativeTest() {
//        String f = "2 / 0";
//        BigDecimal fa = formulaParserTest.calculate(f);
//        assertEquals(null, fa);
//    }
//
//    @Test
//    void calculateWithFunctionFirstCNTOPTest() {
//        String f = "CNTOP + 1.11";
//        BigDecimal fe = new BigDecimal(1);
//        BigDecimal fa = formulaParserTest.calculate(f);
////        assertEquals(fe, fa);
//        assertTrue(fe.compareTo(fa) == 0);
//    }
//
//    @Test
//    void calculateWithFunctionSecondCNTOPTest() {
//        String f = "1.11 + CNTOP";
//        BigDecimal fe = new BigDecimal(1);
//        BigDecimal fa = formulaParserTest.calculate(f);
////        assertEquals(fe, fa);
//        assertTrue(fe.compareTo(fa) == 0);
//    }
//
//    @Test
//    void calculateWithFunctionSUMOPTest() {
//        String f = "SUMOP - 0.555";
//        BigDecimal fe = new BigDecimal(2);
//        BigDecimal fa = formulaParserTest.calculate(f);
////        assertThat(fe, Matchers.comparesEqualTo(fa));
//        assertTrue(fe.compareTo(fa) == 0);
//    }
//
//    @Test
//    void calculateWithFunctionRESERVTest() {
//        String f = "RESERV + 0.111112";
//        BigDecimal fe = new BigDecimal(1);
//        BigDecimal fa = formulaParserTest.calculate(f);
//        assertThat(fe, Matchers.comparesEqualTo(fa));
////        assertTrue(fe.compareTo(fa) == 0);
//    }
//
//    @Test
//    void calculateWithFunctionROUNDCOSTTest() {
//        String f = "ROUNDCOST( 8 ) - 1";
//        BigDecimal fe = new BigDecimal(1);
//        BigDecimal fa = formulaParserTest.calculate(f);
//        assertEquals(fe, fa);
////        assertTrue(fe.compareTo(fa) == 0);
//    }
//
//    @Test
//    void calculateWithFunctionROUNDOPERSUMTest() {          ///
//        String f = "ROUNDOPERSUM( 2.222222 ) - 1.22";
//        BigDecimal fe = new BigDecimal(1);
//        BigDecimal fa = formulaParserTest.calculate(f);
////        MathContext m = new MathContext(1);
////        BigDecimal fer = fe.round(m);
//        assertEquals(fe, fa);
////        assertTrue(fe.compareTo(fa) == 0);
//    }
}
