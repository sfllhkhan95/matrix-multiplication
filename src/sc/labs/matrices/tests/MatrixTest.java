package sc.labs.matrices.tests;
import static org.junit.Assert.*;

import org.junit.Test;

import sc.labs.matrices.Matrix;

public class MatrixTest {

	@Test
	public void test_matrixOrder() {
		Matrix matrix = new Matrix(2, 3);
		int[] order = matrix.getOrder();
		
		assertEquals(order.length, 2);
		assertEquals(order[0], 2);
		assertEquals(order[1], 3);
		assertEquals(matrix.getRows(), 2);
		assertEquals(matrix.getColumns(), 3);
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void test_setColumn() {
		Matrix matrix = new Matrix(2, 2);
		matrix.setRow(0, new int[]{1, 2});
		
		assertEquals(matrix.getData()[0][0], 1);
		assertEquals(matrix.getData()[0][1], 2);
		
		matrix.setRow(1, new int[]{1, 2, 3});
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void test_setMatrixRows() {
		Matrix matrix = new Matrix(2, 2);
		matrix.setRow(5, new int[]{1, 2});
	}
	
	@Test
	public void test_isMultiplicable(){
		Matrix a = new Matrix(1, 2);
		Matrix b = new Matrix(2, 3);
		
		assertTrue(a.isMultiplicableWith(b));
		assertFalse(b.isMultiplicableWith(a));
	}

}
