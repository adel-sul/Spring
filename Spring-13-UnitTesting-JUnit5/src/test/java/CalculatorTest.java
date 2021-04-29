import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @BeforeAll // needs to be static
    static void setupAll() {
        System.out.println("@BeforeAll Executed");
    }

    @BeforeEach
    void setupEach() {
        System.out.println("@BeforeEach Executed");
    }

    @Test
    void testCase1() {
        System.out.println("TC1 Execution");
        int actual = Calculator.add(4, 8);
        assertEquals(12, actual);
    }

    @Test
    void testCase2() {
        System.out.println("TC2 Execution");
        assertTrue(Calculator.operator.equals("add"));
    }

    @Test
    void testCase3() {
        System.out.println("TC3 Execution");
        assertArrayEquals(new int[] {1, 2, 3}, new int[] {1, 2, 3});
    }

    @Test
    void testCase4() {
        System.out.println("TC4 Execution");
        String nullString = null;
        String notNullString = "Cybertek";
        assertNull(nullString);
        assertNotNull(notNullString);
    }

    @Test
    void testCase5() {
        System.out.println("TC5 Execution");
        Calculator c1 = new Calculator();
        Calculator c2 = c1;
        Calculator c3 = new Calculator();
        assertSame(c1, c2);
        assertNotSame(c1, c3);
    }

    @AfterEach
    void tearEach() {
        System.out.println("@AfterEach Executed");
    }

    @AfterAll // needs to be static
    static void tearAll() {
        System.out.println("@AfterAll Executed");
    }
}