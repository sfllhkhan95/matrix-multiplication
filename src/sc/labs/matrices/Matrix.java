package sc.labs.matrices;

public class Matrix {

	private int rows;
	private int cols;
	
	private int[][] data;
	
	public Matrix (int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		
		this.data = new int[rows][];
		for (int i = 0; i < rows; i++){
			this.data[i] = new int[cols];
		}
	}
	
	public void setRow(int index, int[] columns) throws ArrayIndexOutOfBoundsException {
		if (columns.length > this.cols)
			throw new ArrayIndexOutOfBoundsException();
		
		this.data[index] = columns;
	}
	
	public int[] getOrder(){
		int[] order = new int[2];
		order[0] = this.rows;
		order[1] = this.cols;
		return order;
	}
	
	public int[][] getData(){
		return this.data;
	}
	
	public boolean isMultiplicableWith(Matrix b){
		return (this.cols == b.rows);
	}
	
	public int getRows(){
		return this.rows;
	}
	
	public int getColumns(){
		return this.cols;
	}
	
}