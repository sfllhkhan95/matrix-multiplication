package sc.labs.matrices.tests;
import static org.junit.Assert.*;

import org.junit.Test;

import sc.labs.matrices.Matrix;
import sc.labs.matrices.MatrixMultiplicator;
import sc.labs.matrices.StrassenMultiplicator;

public class StrassenMultiplicatorTest {

	@Test(expected=IllegalArgumentException.class)
	public void test_multiply_incompatible_order(){
		Matrix a = new Matrix(2, 2);
		a.setRow(0, new int[]{1, 2});
		a.setRow(1, new int[]{3, 4});
		
		Matrix b = new Matrix(3, 2);
		b.setRow(0, new int[]{1, 2});
		b.setRow(1, new int[]{3, 4});
		b.setRow(2, new int[]{5, 6});
		
		MatrixMultiplicator multiplicator = new StrassenMultiplicator();
		multiplicator.multiply(a, b);
	}
	
	@Test
	public void test_multiply_compatible_order() {
		Matrix a = new Matrix(2, 2);
		a.setRow(0, new int[]{1, 2});
		a.setRow(1, new int[]{3, 4});
		
		Matrix b = new Matrix(2, 2);
		b.setRow(0, new int[]{1, 2});
		b.setRow(1, new int[]{3, 4});
		
		MatrixMultiplicator multiplicator = new StrassenMultiplicator();
		Matrix c = multiplicator.multiply(a, b);
		
		assertEquals(c.getRows(), 2);
		assertEquals(c.getColumns(), 2);
		assertArrayEquals(c.getData()[0], new int[]{7, 10});
		assertArrayEquals(c.getData()[1], new int[]{15, 22});	
	}

}
