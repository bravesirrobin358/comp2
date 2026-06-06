/*
 * Demo:
 *
 * - http://www.site.uottawa.ca/~turcotte/teaching/iti-1121/lectures/JUnitDemo.zip
 * - https://youtu.be/IcZfyoGtPO0
 */

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class TestMediaLibrary {

    @Test
    public void test1() {

        int[] sizes = new int[] {10, 20, 30, 40};

        int[] map = new int[] {0, 0, 0, 0};

        assertEquals(0, MediaLibrary.getMaxDiff(sizes, map, 1));

    }
    
    @Test
    public void test2() {

        int[] sizes = new int[] {10, 20, 30, 40};

        int[] map = new int[] {0, 0, 0, 0};

        assertEquals(100, MediaLibrary.getMaxDiff(sizes, map, 2));

    }

    @Test
    public void test3() {

        int[] sizes = new int[] {100, 10, 75, 50, 20, 30, 40};

        int[] map = new int[] {0, 1, 2, 0, 1, 1, 1};

        assertEquals(75, MediaLibrary.getMaxDiff(sizes, map, 3));

    }

    public static void runClass(java.lang.Class<?> c) {

        System.out.println("Running " + c.getName());
        System.out.println();

        Result result;
        result = JUnitCore.runClasses(c);

        System.out.println("Number of tests run: " + result.getRunCount());
        System.out.println("Number of tests that failed: " + result.getFailureCount());

        if (! result.wasSuccessful()) {
            System.out.println();
            System.out.println("List of failed tests:");
            for (Failure failure : result.getFailures()) {
                System.out.println();
                System.out.println(failure.toString());
                for (StackTraceElement elem : failure.getException().getStackTrace()) {
                    if (elem.getClassName().equals(c.getName())) {
                        System.out.println("	at " + elem);
                    }
                }

            }
        }

        System.out.println();

    }


    public static void main(String[] args) {

        runClass(TestMediaLibrary.class);

    }

}
