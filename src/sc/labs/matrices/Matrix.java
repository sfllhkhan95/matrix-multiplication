package sc.labs.matrices;

/**
 * Matrix class provides an interface for creating matrices of any order which
 * can then be manipulated using other classes such as implementations of the
 * {@link MatrixMultiplier} for multiplying matrices.
 * 
 * @author saifkhichi96
 * @version 1.0
 */
public class Matrix {

	/**
	 * Number of rows in the matrix.
	 */
	private final int rows;
	
	/**
	 * Number of columns in the matrix.
	 */
	private final int cols;
	
	/**
	 * Actual data of the matrix, stored as a 2D array.
	 */
	private int[][] data;
	
	/**
	 * Default public constructor.
	 * 
	 * @param rows number of rows in the matrix
	 * @param cols number of columns in the matrix
	 */
	public Matrix (int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		this.data = new int[rows][cols];
	}
	
	/**
	 * Populates a row of the matrix.
	 * 
	 * @param index row number which has to be populated (starting from 0)
	 * @param columns 1D array representing columns of this row
	 * @throws ArrayIndexOutOfBoundsException exception if either the row number is invalid or columns array
	 * 										  has more elements than those specified in the constructor
	 */
	public void setRow(int index, int[] columns) throws ArrayIndexOutOfBoundsException {
		if (columns.length > this.cols)
			throw new ArrayIndexOutOfBoundsException();
		
		this.data[index] = columns;
	}
	
	/**
	 * Returns order of the matrix.
	 * 
	 * @return array containing number of rows and columns of the matrix
	 */
	public int[] getOrder(){
		int[] order = new int[2];
		order[0] = this.rows;
		order[1] = this.cols;
		return order;
	}
	
	/**
	 * Returns data of the matrix.
	 * 
	 * @return 2D array used to internally store matrix data
	 */
	public int[][] getData(){
		return this.data;
	}
	
	/**
	 * Checks if the two matrices have compatible orders which can be
	 * multiplied.
	 * 
	 * Matrix A of order m x n and matrix B of order p x q can only be
	 * multiplied if n == q.
	 * 
	 * @param b matrix whose compatibility with this matrix is to be tested
	 * @return true if this can be multiplied with given matrix, or false
	 */
	public boolean isMultiplicableWith(Matrix b){
		return (this.cols == b.rows);
	}
	
	/**
	 * Returns number of rows in the matrix.
	 * 
	 * @return number of rows
	 */
	public int getRows(){
		return this.rows;
	}
	
	/**
	 * Returns number of columns in the matrix.
	 * 
	 * @return number of columns
	 */
	public int getColumns(){
		return this.cols;
	}
	
}