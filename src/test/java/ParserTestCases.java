import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTestCases {

    private FormulaParserTest formulaParserTest;

    @ParameterizedTest
    @ValueSource(strings = {"CNTOP() + 2.345", "2.2344  -  RESERV"})
    void validateTest(String formula) {
        String vf = formulaParserTest.validate(formula);

        System.out.println(vf);
    }

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
