package sc.labs.matrices.tests;
import static org.junit.Assert.*;

import org.junit.Test;

import sc.labs.matrices.IterativeMultiplier;
import sc.labs.matrices.Matrix;
import sc.labs.matrices.MatrixMultiplier;


/**
 * Unit test for {@link IterativeMultiplier} class.
 * 
 * @author saifkhichi96
 * @version 1.0
 */
public class IterativeMultiplierTest {

	/**
	 * Asserts that an exception is thrown on multiplying matrices of
	 * incompatible orders.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void test_multiply_incompatible_order(){
		Matrix a = new Matrix(2, 2);
		a.setRow(0, new int[]{1, 2});
		a.setRow(1, new int[]{3, 4});
		
		Matrix b = new Matrix(3, 2);
		b.setRow(0, new int[]{1, 2});
		b.setRow(1, new int[]{3, 4});
		b.setRow(2, new int[]{5, 6});
		
		MatrixMultiplier multiplicator = new IterativeMultiplier();
		multiplicator.multiply(a, b);
	}
	
	/**
	 * Asserts that matrix multiplication returns correct result.
	 */
	@Test
	public void test_multiply_compatible_order() {
		Matrix a = new Matrix(2, 2);
		a.setRow(0, new int[]{1, 2});
		a.setRow(1, new int[]{3, 4});
		
		Matrix b = new Matrix(2, 3);
		b.setRow(0, new int[]{1, 2, 3});
		b.setRow(1, new int[]{4, 5, 6});
		
		MatrixMultiplier multiplicator = new IterativeMultiplier();
		Matrix c = multiplicator.multiply(a, b);
		
		assertEquals(c.getRows(), 2);
		assertEquals(c.getColumns(), 3);
		assertArrayEquals(c.getData()[0], new int[]{9, 12, 15});
		assertArrayEquals(c.getData()[1], new int[]{19, 26, 33});	
	}

}
