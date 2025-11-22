package de.m_marvin.unimat;

import de.m_marvin.unimat.api.IMatrix;

public class MatrixMathException extends RuntimeException {
	
	private static final long serialVersionUID = -8987640436761497379L;
	
	public MatrixMathException(String message) {
		super(message);
	}

	public MatrixMathException(String message, Object... args) {
		this(String.format(message, args));
	}
	
	public MatrixMathException(String message, IMatrix<? extends Number> m1) {
		this("%s: %dx%d", message, m1.width(), m1.height());
	}

	public MatrixMathException(String message, IMatrix<? extends Number> m1, IMatrix<? extends Number> m2) {
		this("%s: %dx%d | %dx%d", message, m1.width(), m1.height(), m2.width(), m2.height());
	}
	
	public MatrixMathException(String message, IMatrix<? extends Number> m1, Object... args) {
		this(String.format(message, args), m1);
	}

	public MatrixMathException(String message, IMatrix<? extends Number> m1, IMatrix<? extends Number> m2, Object... args) {
		this(String.format(message, args), m1, m2);
	}
	
}
