package sc.labs.matrices;


public class StrassenMultiplicator implements MatrixMultiplicator {

	@Override
	public Matrix multiply(Matrix a, Matrix b) throws IllegalArgumentException {
		/* Raise illegal argument exception if either of the given arrays is not square. */
		if (a.getRows() != a.getColumns() ||
				b.getRows() != b.getColumns())
			throw new IllegalArgumentException();
		
		/* User 2D array multiplier to get result array */
		int[][] result = multiply(a.getData(), b.getData());

		/* Create Matrix object from 2D array */
		Matrix matrix = new Matrix(a.getRows(), b.getRows());
		for (int i = 0; i < a.getRows(); i++){
			matrix.setRow(i, result[i]);
		}
		return matrix;
	}
	
	private int[][] multiply(int[][] a, int[][] b){
		
		/* Create 2D array to store result */
		int[][] result = new int[a.length][a.length];
		
		/* Base case: Matrix degenerates into numbers (i.e. n == 1) */
		if (a.length == 1) {
			result[0][0] = a[0][0] * b[0][0];
		}
		
		/* Recursive part */
		else {
			
			/* Partition matrix A into block matrices */
			int[][][] As = getBlocks(a);
			
			/* Partition matrix B into block matrices */
			int[][][] Bs = getBlocks(b);
			
			/*
			 * M1 = (A11 + A22) * (B11 + B22)
			 * M2 = (A21 + A22) * B11
			 * M3 = A11 * (B12 - B22)
			 * M4 = A22 * (B21 - B11)
			 * M5 = (A11 + A12) * B22
			 * M6 = (A21 - A11) * (B11 + B12)
			 * M7 = (A12 - A22) * (B21 + B22)
			 */
			int m1[][] = multiply(add(As[0], As[3]), add(Bs[0], Bs[3]));
			int m2[][] = multiply(add(As[2], As[3]), Bs[0]);
			int m3[][] = multiply(As[0], subtract(Bs[1], Bs[3]));
			int m4[][] = multiply(As[3], subtract(As[2], Bs[0]));
			int m5[][] = multiply(add(As[0], As[1]), Bs[3]);
			int m6[][] = multiply(subtract(As[2], As[0]), add(Bs[0], Bs[1]));
			int m7[][] = multiply(subtract(As[1], As[3]), add(Bs[2], Bs[3]));
			
			/*
			 * C1 = M1 + M4 - M5 + M7
			 * C2 = M3 + M5
			 * C3 = M2 + M4
			 * C4 = M1 - M2 + M3 + M6  
			 */
			int[][] c1 = add(subtract(add(m1, m4), m5), m7);
			int[][] c2 = add(m3, m5);
			int[][] c3 = add(m2, m4);
			int[][] c4 = add(add(subtract(m1, m2), m3), m6);
			
			putBlocks(result, new int[][][]{
				c1, c2, c3, c4
			});
		}
		
		return result;
	}
	
	private void putBlocks(int[][] matrix, int[][][] blocks){
		int n = matrix.length;
		
		int[][] blockA = blocks[0];
		for (int i = 0; i < n/2; i++){
			for (int j = 0; j < n/2; j++) {
				matrix[i][j] = blockA[i][j];
			}
		}
		
		int[][] blockB = blocks[1];
		for (int i = 0; i < n/2; i++){
			for (int j = n/2; j < n; j++) {
				matrix[i][j] = blockB[i][j - n/2];
			}
		}
		
		
		int[][] blockC = blocks[2];
		for (int i = n/2; i < n; i++){
			for (int j = 0; j < n/2; j++) {
				matrix[i][j] = blockC[i - n/2][j];
			}
		}
		
		
		int[][] blockD = blocks[3];
		for (int i = n/2; i < n; i++){
			for (int j = n/2; j < n; j++) {
				matrix[i][j] = blockD[i - n/2][j - n/2];
			}
		}
	}
	
	/**
	 * Splits given matrix into four block matrices and returns
	 * them as a 3D array.
	 * 
	 * @param matrix the matrix to split
	 * @return four 2D arrays representing block matrices of input matrix
	 */
	private int[][][] getBlocks(int[][] matrix){
		int n = matrix.length;
		
		int[][] blockA = new int[n/2][n/2];
		for (int i = 0; i < n/2; i++){
			for (int j = 0; j < n/2; j++) {
				blockA[i][j] = matrix[i][j];
			}
		}
		
		int[][] blockB = new int[n/2][n/2];
		for (int i = 0; i < n/2; i++){
			for (int j = n/2; j < n; j++) {
				int val = matrix[i][j];
				blockB[i][j - n/2] = val;
			}
		}
		
		
		int[][] blockC = new int[n/2][n/2];
		for (int i = n/2; i < n; i++){
			for (int j = 0; j < n/2; j++) {
				blockC[i - n/2][j] = matrix[i][j];
			}
		}
		
		
		int[][] blockD = new int[n/2][n/2];
		for (int i = n/2; i < n; i++){
			for (int j = n/2; j < n; j++) {
				blockD[i - n/2][j - n/2] = matrix[i][j];
			}
		}
		
		return new int[][][]{
				blockA,
				blockB,
				blockC,
				blockD
		};
	}
	
	/**
	 * Adds two matrices
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private int[][] add(int[][] a, int[][] b){
		int n = a.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            	result[i][j] = a[i][j] + b[i][j];
        
        return result;
	}
	
	/**
	 * Adds two matrices
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private int[][] subtract(int[][] a, int[][] b){
		int n = a.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            	result[i][j] = a[i][j] - b[i][j];
        
        return result;
	}

}