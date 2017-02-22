package sc.labs.matrices;

/**
 * IterativeMultiplier multiplies matrices using the naive iterative implementation
 * of matrix multiplication. It has a time complexity of O(n^3).
 * 
 * @author saifkhichi96
 * @version 1.0
 */
public class IterativeMultiplier implements MatrixMultiplier {

	@Override
	public Matrix multiply(Matrix a, Matrix b) throws IllegalArgumentException {
		/* Raise an exception if input matrices have incompatible orders */
		if (!a.isMultiplicableWith(b))
			throw new IllegalArgumentException();
		
		/* Create result matrix of order (m x p) where a has order (m x n) and 
		 * b has order (n x p).*/
		Matrix result = new Matrix(a.getRows(), b.getColumns());
		
		/* Convert input Matrix objects to 2D arrays. */
		int[][] matrixA = a.getData();
		int[][] matrixB = b.getData();
		
		/* Perform multiplication by iteratively multiplying each row of matrix a
		 * with each column of matrix b and calculating sum of all row x column
		 * calculations for each column of matrix b.
		 * 
		 * i.e.    [a b] * [p r] = [a*p+b*q a*r+b*s]
		 *         [c d]   [q s]   [c*p+d*q c*r+d*s] */
        int sum = 0;
		for (int i = 0; i < a.getRows(); i++){
			int[] row = new int[b.getColumns()];
			for (int j = 0; j < b.getColumns(); j++){
				for (int k = 0; k < b.getRows(); k++){
					sum += matrixA[i][k] * matrixB[k][j];
				}
				row[j] = sum;
				sum = 0;
			}
			result.setRow(i, row);
		}
		
		/* Result the resulting Matrix */
		return result;
	}
	
}
