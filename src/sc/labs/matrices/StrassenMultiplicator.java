package sc.labs.matrices;

public class StrassenMultiplicator implements MatrixMultiplicator {

	@Override
	public Matrix multiply(Matrix a, Matrix b) throws ArithmeticException {
		if (!a.isMultiplicableWith(b))
			throw new ArithmeticException();
		
		return null;
	}

}
