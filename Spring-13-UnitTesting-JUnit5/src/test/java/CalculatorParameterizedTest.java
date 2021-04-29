import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

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
}
