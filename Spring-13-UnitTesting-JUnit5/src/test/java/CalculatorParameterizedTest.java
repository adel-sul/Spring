import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class CalculatorParameterizedTest {

    @ParameterizedTest
    @ValueSource(strings = {"Java", "JS", "TS"})
    void test1(String args) {
        Assertions.assertTrue(!args.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 6, 9})
    void test2(int nums) {
        Assertions.assertEquals(0, nums %3);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Java", "JS", "TS"})
//    @EmptySource -> ADDING EMPTY VALUE
//    @NullSource -> ADDING NULL VALUE
    void test3(String args) {
        Assertions.assertTrue(!args.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void test4(String args) {
        Assertions.assertNotNull(args);
    }

    static String[] stringProvider() {
        String arr[] = {"Java", "JS", "TS"};
        return arr;
    }

    @ParameterizedTest
    @CsvSource({
            "10, 20, 30",
            "20, 20, 40",
            "10, 50, 60",
    })
    void test5(int num1, int num2, int result) {
        Assertions.assertEquals(result, Calculator.add(num1, num2));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/sample-data.csv",numLinesToSkip = 1)
    void test6(int num1, int num2, int result) {
        Assertions.assertEquals(result, Calculator.add(num1, num2));
    }
}
