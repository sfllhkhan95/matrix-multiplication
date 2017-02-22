package sc.labs.matrices.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * JUnit test suite for the matrix multiplication library which can be used
 * to run all the unit tests. Can be used to quickly test correctness of the
 * whole library.
 * 
 * @author saifkhichi96
 * @version 1.0
 */
@RunWith(Suite.class)
@SuiteClasses({ 
	IterativeMultiplierTest.class,
	MatrixTest.class,
	StrassenMultiplierTest.class
})
public class AllTests {

}