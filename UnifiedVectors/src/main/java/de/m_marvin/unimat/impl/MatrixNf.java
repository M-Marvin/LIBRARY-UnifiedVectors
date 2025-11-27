package de.m_marvin.unimat.impl;

import de.m_marvin.unimat.MatrixMathException;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector4;

public class MatrixNf extends BaseFloatMatrix<MatrixNf> {

	public MatrixNf(int w, int h, boolean sparse) {
		super(w, h, sparse);
	}

	public MatrixNf(int w, int h) {
		super(w, h, false);
	}
	
	public MatrixNf(int s, boolean sparse) {
		this(s, s, sparse);
	}

	public MatrixNf(int s) {
		this(s, false);
	}
	
	public MatrixNf(MatrixNd mat) {
		this(mat.width(), mat.height(), mat.isSparse());
		setI(mat);
	}
	
	public MatrixNf(float[][] m) {
		super(m);
	}
	
	@Override
	protected MatrixNf newMatrix(int width, int height, boolean sparse) {
		return new MatrixNf(width, height, sparse);
	}

	public Matrix4f get4x4() {
		return get4x4(false);
	}

	public Matrix4f get3x3() {
		return get3x3(false);
	}
	
	public Matrix4f get4x4(boolean strip) {
		if ((width() != 4 || height() != 4) && !strip)
			throw new MatrixMathException("this generic matrix is not 4x4", this);
		
		Matrix4f m = new Matrix4f();
		for (int x = 0; x < width(); x++)
			for (int y = 0; y < height(); y++)
				m.set(x, y, m(x, y));
		return m;
	}

	public Matrix4f get3x3(boolean strip) {
		if ((width() != 3 || height() != 3) && !strip)
			throw new MatrixMathException("this generic matrix is not 3x3", this);
		
		Matrix4f m = new Matrix4f();
		for (int x = 0; x < width(); x++)
			for (int y = 0; y < height(); y++)
				m.set(x, y, m(x, y));
		return m;
	}
	
	public static MatrixNf columnVector(IVector2<Number> vector) {
		return new MatrixNf(1, 2, false).setColVecI(vector);
	}

	public static MatrixNf columnVector(IVector3<Number> vector) {
		return new MatrixNf(1, 3, false).setColVecI(vector);
	}
	
	public static MatrixNf columnVector(IVector4<Number> vector) {
		return new MatrixNf(1, 4, false).setColVecI(vector);
	}
	
	public static MatrixNf rowVector(IVector2<Number> vector) {
		return new MatrixNf(2, 1, false).setRowVecI(vector);
	}

	public static MatrixNf rowVector(IVector3<Number> vector) {
		return new MatrixNf(3, 1, false).setRowVecI(vector);
	}
	
	public static MatrixNf rowVector(IVector4<Number> vector) {
		return new MatrixNf(4, 1, false).setRowVecI(vector);
	}
	
}
