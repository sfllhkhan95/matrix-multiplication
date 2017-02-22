package sc.labs.matrices;


/**
 * StrassenMultiplier multiplies matrices using the Strassen algorithm for matrix
 * multiplication. It has a time complexity of approximately O(n^2.8) and is
 * particularly useful in multiplying huge matrices.
 * 
 * This implementation of Strassen algorithm, however, works only with square matrices
 * of order (n x n) where n is a multiple of 2. Incompatible matrices will generate an
 * exception.
 * 
 * For multiplying non-square matrices or matrices whose order is not a power of 2,
 * use {@link IterativeMultiplier}.
 * 
 * @author saifkhichi96
 * @version 1.0
 */
public class StrassenMultiplier implements MatrixMultiplier {

	@Override
	public Matrix multiply(Matrix a, Matrix b) throws IllegalArgumentException {
		/* Raise an exception if input matrices are either non-square matrices
		 * or have incompatible orders. */
		if (a.getRows() != a.getColumns() || b.getRows() != b.getColumns() 
				|| a.isMultiplicableWith(b))
			throw new IllegalArgumentException();
		
		/* Raise an exception if input matrices have order which is not a power of
		 * two. */
		if (a.getRows() % 2 != 0)
			throw new IllegalArgumentException();
		
		/* Delegate multiplication task to the private multiplication method
		 * which takes two 2D arrays and returns the result as a 2D array. */
		int[][] result = multiply(a.getData(), b.getData());

		/* Create Matrix object from the resulting 2D array */
		Matrix matrix = new Matrix(a.getRows(), b.getRows());
		for (int i = 0; i < a.getRows(); i++){
			matrix.setRow(i, result[i]);
		}
		
		/* Return resulting Matrix object */
		return matrix;
	}
	
	/**
	 * Takes two square matrices of order (n x n) where n is a multiple of 2
	 * as two 2D arrays, multiplies them and returns the resulting 2D array.
	 * 
	 * @param a 2D array representing first operand of multiplication 
	 * @param b 2D array representing second operand of multiplication
	 * @return resulting matrix as 2D array
	 */
	private int[][] multiply(int[][] a, int[][] b){
		
		/* Create 2D array of order (n x n) to store result */
		int[][] result = new int[a.length][a.length];
		
		/* Base case: Matrix degenerates into numbers (i.e. n == 1 when block
		 * matrix is of order 1 x 1) */
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
			
			/* Construct result from resulting blocks */
			putBlocks(result, new int[][][]{
				c1, c2, c3, c4
			});
		}
		
		/* Return resulting 2D array */
		return result;
	}
	
	/**
	 * Joins together four block matrices to form a single matrix.
	 * 
	 * @param matrix the parent matrix in which blocks have to be joined and put
	 * @param blocks four 2D arrays for four block matrices
	 */
	private void putBlocks(int[][] matrix, int[][][] blocks){
		/* Locally store size of parent matrix. Assuming that parent
		 * matrix is of order (n x n) */
		int n = matrix.length;
		
		/* Put B11 in top-left block of parent matrix */
		int[][] blockA = blocks[0];
		for (int i = 0; i < n/2; i++)
			for (int j = 0; j < n/2; j++)
				matrix[i][j] = blockA[i][j];

		/* Put B12 in top-right block of parent matrix */
		int[][] blockB = blocks[1];
		for (int i = 0; i < n/2; i++)
			for (int j = n/2; j < n; j++)
				matrix[i][j] = blockB[i][j - n/2];

		/* Put B21 in bottom-left block of parent matrix */
		int[][] blockC = blocks[2];
		for (int i = n/2; i < n; i++)
			for (int j = 0; j < n/2; j++)
				matrix[i][j] = blockC[i - n/2][j];

		/* Put B22 in bottom-right block of parent matrix */
		int[][] blockD = blocks[3];
		for (int i = n/2; i < n; i++)
			for (int j = n/2; j < n; j++)
				matrix[i][j] = blockD[i - n/2][j - n/2];
	}
	
	/**
	 * Splits given matrix into four block matrices and returns them as
	 * a 3D array (i.e. four 2D arrays).
	 * 
	 * @param matrix the matrix to split into blocks
	 * @return four 2D arrays representing block matrices of input matrix
	 */
	private int[][][] getBlocks(int[][] matrix){
		int n = matrix.length;
		
		/* Calculate B11 as top-left block of parent matrix */
		int[][] blockA = new int[n/2][n/2];
		for (int i = 0; i < n/2; i++)
			for (int j = 0; j < n/2; j++)
				blockA[i][j] = matrix[i][j];

		/* Calculate B12 as top-right block of parent matrix */
		int[][] blockB = new int[n/2][n/2];
		for (int i = 0; i < n/2; i++)
			for (int j = n/2; j < n; j++)
				blockB[i][j - n/2] = matrix[i][j];

		/* Calculate B21 as bottom-left block of parent matrix */
		int[][] blockC = new int[n/2][n/2];
		for (int i = n/2; i < n; i++){
			for (int j = 0; j < n/2; j++) {
				blockC[i - n/2][j] = matrix[i][j];
			}
		}
		
		/* Calculate B22 as bottom-right block of parent matrix */
		int[][] blockD = new int[n/2][n/2];
		for (int i = n/2; i < n; i++)
			for (int j = n/2; j < n; j++)
				blockD[i - n/2][j - n/2] = matrix[i][j];
		
		/* Result all four blocks (2D arrays) as a 3D array */
		return new int[][][]{
				blockA,
				blockB,
				blockC,
				blockD
		};
	}
	
	/**
	 * Adds two matrices. Assumes that both input matrices are of same
	 * order and are square.
	 * 
	 * @param a first operand of addition
	 * @param b second operand of the addition
	 * @return resulting matrix as a 2D array
	 */
	private int[][] add(int[][] a, int[][] b){
		/* Locally save length of input matrix a to avoid repetition */
		int n = a.length;
		
		/* Create a 2D array of order (n x n) to store resulting matrix */
        int[][] result = new int[n][n];

        /* Add corresponding cells of matrix b and matrix a and store them
         * in result matrix */
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            	result[i][j] = a[i][j] + b[i][j];
        
        /* Return resulting matrix */
        return result;
	}
	
	/**
	 * Calculates difference of two matrices. Assumes that both input
	 * matrices are of same order and are square.
	 * 
	 * @param a first operand of subtraction
	 * @param b second operand of subtraction
	 * @return resulting matrix as a 2D array
	 */
	private int[][] subtract(int[][] a, int[][] b){
		/* Locally save length of input matrix a to avoid repetition */
		int n = a.length;
		
		/* Create a 2D array of order (n x n) to store resulting matrix */
        int[][] result = new int[n][n];
        
        /* Subtract corresponding cells of matrix b from matrix a and store them
         * in result matrix */
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            	result[i][j] = a[i][j] - b[i][j];
        
        /* Return resulting matrix */
        return result;
	}

}