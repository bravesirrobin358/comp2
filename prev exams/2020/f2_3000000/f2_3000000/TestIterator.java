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

public class TestIterator {

    public static final double DELTA = 0.00001;

    @Test
    public void testIteratorSparse() {

        SparseVector v;
        v = new SparseVector(10);

        Iterator i;
        i = v.getIterator();

        for (long j=0; j<10; j++) {
            assertEquals(true, i.hasNext());
            assertEquals(0.0, i.next(), DELTA);
        }

    }

    @Test
    public void testIteratorSparse5() {

        SparseVector v;
        v = new SparseVector(5);

        v.set(2, 42.0);

        Iterator i;
        i = v.getIterator();

        assertEquals(true, i.hasNext());
        assertEquals(0.0, i.next(), DELTA);

        assertEquals(true, i.hasNext());
        assertEquals(0.0, i.next(), DELTA);

        assertEquals(true, i.hasNext());
        assertEquals(42.0, i.next(), DELTA);

        assertEquals(true, i.hasNext());
        assertEquals(0.0, i.next(), DELTA);

        assertEquals(true, i.hasNext());
        assertEquals(0.0, i.next(), DELTA);

        assertEquals(false, i.hasNext());
    }

    @Test
    public void testIteratorOdd() {

        SparseVector v;
        v = new SparseVector(10);

        for (long i=0; i<10; i++) {
            if (i%2 == 1) {
                v.set(i, 10.0 + i);
            }
        }

        Iterator i;
        i = v.getIterator();

        for (long j=0; j<10; j++) {
            assertEquals(true, i.hasNext());
            if (j%2 == 1) {
                assertEquals(10.0 + j, i.next(), DELTA);
            } else {
                assertEquals(0.0, i.next(), DELTA);
            }
        }

        assertEquals(false, i.hasNext());
        
    }

    @Test
    public void testIteratorEven() {

        SparseVector v;
        v = new SparseVector(10);

        for (long i=0; i<10; i++) {
            if (i%2 == 0) {
                v.set(i, 10.0 + i);
            }
        }

        Iterator i;
        i = v.getIterator();

        for (long j=0; j<10; j++) {
            assertEquals(true, i.hasNext());
            if (j%2 == 0) {
                assertEquals(10.0 + j, i.next(), DELTA);
            } else {
                assertEquals(0.0, i.next(), DELTA);
            }
        }

        assertEquals(false, i.hasNext());
        
    }

    @Test
    public void testIteratorNoZero() {
        
        SparseVector v;
        v = new SparseVector(10);

        for (long i=0; i<10; i++) {
            v.set(i, 10.0 + i);
        }

        Iterator i;
        i = v.getIterator();

        for (long j=0; j<10; j++) {
            assertEquals(true, i.hasNext());
            assertEquals(10.0 + j, i.next(), DELTA);
        }
        
        assertEquals(false, i.hasNext());
        
    }

    @Test
    public void testIteratorIllegalStateException() {

        SparseVector v;
        v =  new SparseVector(0);

        Iterator i;
        i = v.getIterator();
        
        try {
            i.next();
            fail("IllegalStateException expected");
        } catch(IllegalStateException e) {
            ;
        }

    }

    @Test
    public void testIteratorIllegalStateExceptionNonZero() {

        SparseVector v;
        v =  new SparseVector(5);

        v.set(2, 42.0);

        Iterator i;
        i = v.getIterator();

        while (i.hasNext()) {
            i.next();
        }
        
        try {
            i.next();
            fail("IllegalStateException expected");
        } catch(IllegalStateException e) {
            ;
        }

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

        runClass(TestIterator.class);

    }

}
