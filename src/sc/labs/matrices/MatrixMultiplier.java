package sc.labs.matrices;

/**
 * MatrixMultiplier defines an interface for multiplying {@link Matrix} objects.
 * This interface is implemented by all the various matrix multiplication
 * algorithms, each of which is defined in a separate class.
 * 
 * @author saifkhichi96
 * @version 1.0
 */
public interface MatrixMultiplier {

	/**
	 * Multiplies two matrices and returns the resulting matrix.
	 * 
	 * @param a first operand of multiplication
	 * @param b second operand of multiplication
	 * 
	 * @return matrix resulting from multiplication a * b
	 * @throws IllegalArgumentException exception is thrown if input matrices have incompatible orders or size
	 */
	public Matrix multiply(Matrix a, Matrix b) throws IllegalArgumentException;
		
}