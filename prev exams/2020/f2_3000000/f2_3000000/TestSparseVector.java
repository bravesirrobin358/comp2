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

public class TestSparseVector {

    public static final double DELTA = 0.00001;

    @Test
    public void testSparseVector0() {
        
        SparseVector v = new SparseVector(0);
        assertEquals(0, v.getDimension());

    }

    @Test
    public void testSparseVectorLongMaxValue() {
        
        SparseVector v = new SparseVector(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, v.getDimension());

    }

    @Test
    public void testSparseVectorIllegalArgumentException() {

        SparseVector v;
        
        try {
            v =  new SparseVector(-1);
            fail("negative value for index, IllegalArgumentException expected");
        } catch(IllegalArgumentException e) {
            ;
        }

    }

    @Test
    public void testGetSparse() {

        SparseVector v;
        v = new SparseVector(10);

        for (long i=0; i<10; i++) {
            assertEquals(0.0, v.get(i), DELTA);
        }
        
    }

    private static void testGetNoZero() {

        SparseVector v;
        v = new SparseVector(10);

        for (long i=0; i<10; i++) {
            v.set(i, 10 + i);
        }

        for (long i=0; i<10; i++) {
            assertEquals(10 + i, v.get(i), DELTA);
        }
        
    }

    @Test
    public void testSetGetOdd() {

        SparseVector v;
        v = new SparseVector(10);

        for (long i=0; i<10; i++) {
            if (i%2 == 1) {
                v.set(i, 10.0 + i);
            }
        }

        for (long i=0; i<10; i++) {
            if (i%2 == 1) {
                assertEquals(10.0 + i, v.get(i), DELTA);
            } else {
                assertEquals(0.0, v.get(i), DELTA);
            }
        }
        
    }

    @Test
    public void testSetGetEven() {

        SparseVector v;
        v = new SparseVector(10);

        for (long i=0; i<10; i++) {
            if (i%2 == 0) {
                v.set(i, 10.0 + i);
            }
        }

        for (long i=0; i<10; i++) {
            if (i%2 == 0) {
                assertEquals(10.0 + i, v.get(i), DELTA);
            } else {
                assertEquals(0.0, v.get(i), DELTA);
            }
        }
        
    }

    @Test
    public void testGetIndexOutOfBoundsException() {
        
        SparseVector v = new SparseVector(0);

        try {
            v.get(-1);
            fail("negative value for index, IndexOutOfBoundsException expected");
        } catch(IndexOutOfBoundsException e) {
            ;
        }

        try {
            v.get(0);
            fail("IndexOutOfBoundsException expected");
        } catch(IndexOutOfBoundsException e) {
            ;
        }

    }

    @Test
    public void testSetIndexOutOfBoundsException() {
        
        SparseVector v = new SparseVector(0);

        try {
            v.set(-1,-0.618);
            fail("negative value for index, IndexOutOfBoundsException expected");
        } catch(IndexOutOfBoundsException e) {
            ;
        }

        try {
            v.set(0,-0.618);
            fail("IndexOutOfBoundsException expected");
        } catch(IndexOutOfBoundsException e) {
            ;
        }

    }

    @Test
    public void testSetIndexOutOfBoundsExceptionNonZero() {
        
        SparseVector v = new SparseVector(1024);

        try {
            v.set(-1,-0.618);
            fail("negative value for index, IndexOutOfBoundsException expected");
        } catch(IndexOutOfBoundsException e) {
            ;
        }

        try {
            v.set(1024,-0.618);
            fail("IndexOutOfBoundsException expected");
        } catch(IndexOutOfBoundsException e) {
            ;
        }

    }

    @Test
    public void testGetL1Norm0() {
        
        SparseVector v = new SparseVector(0);
        assertEquals(0.0, v.getL1Norm(), DELTA);

    }

    @Test
    public void testGetL1NormLongMaxValue() {
        
        SparseVector v = new SparseVector(Long.MAX_VALUE);
        assertEquals(0.0, v.getL1Norm(), DELTA);

    }

    @Test
    public void testGetL1Norm() {

        SparseVector v;
        v = new SparseVector(10);

        for (long i=0; i<10; i++) {
            v.set(i, i);
        }

        assertEquals(45.0, v.getL1Norm(), DELTA);
        
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

        runClass(TestSparseVector.class);

    }

}
