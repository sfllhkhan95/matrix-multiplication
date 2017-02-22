package sc.labs.matrices;

public class IterativeMultiplicator implements MatrixMultiplicator {

	@Override
	public Matrix multiply(Matrix a, Matrix b) throws ArithmeticException {
		if (!a.isMultiplicableWith(b))
			throw new ArithmeticException();
		
		// A (m x n) * B (n x p) = C (m x p)
		Matrix result = new Matrix(a.getRows(), b.getColumns());
		int[][] matrixA = a.getData();
		int[][] matrixB = b.getData();
		
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
		
		return result;
	}
	
}
