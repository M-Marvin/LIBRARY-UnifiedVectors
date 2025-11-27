package de.m_marvin.unimat.impl;

import de.m_marvin.unimat.MatrixMathException;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector4;

public class MatrixNd extends BaseDoubleMatrix<MatrixNd> {

	public MatrixNd(int w, int h, boolean sparse) {
		super(w, h, sparse);
	}
	
	public MatrixNd(int w, int h) {
		this(w, h, false);
	}
	
	public MatrixNd(int s, boolean sparse) {
		this(s, s, sparse);
	}

	public MatrixNd(int s) {
		this(s, false);
	}

	public MatrixNd(MatrixNf mat) {
		this(mat.width(), mat.height(), mat.isSparse());
		setI(mat);
	}
	
	public MatrixNd(double[][] m) {
		super(m);
	}
	
	@Override
	protected MatrixNd newMatrix(int width, int height, boolean sparse) {
		return new MatrixNd(width, height, sparse);
	}

	public Matrix4d get4x4() {
		return get4x4(false);
	}

	public Matrix4d get3x3() {
		return get3x3(false);
	}
	
	public Matrix4d get4x4(boolean strip) {
		if ((width() != 4 || height() != 4) && !strip)
			throw new MatrixMathException("this generic matrix is not 4x4", this);
		
		Matrix4d m = new Matrix4d();
		for (int x = 0; x < width(); x++)
			for (int y = 0; y < height(); y++)
				m.set(x, y, m(x, y));
		return m;
	}

	public Matrix4d get3x3(boolean strip) {
		if ((width() != 3 || height() != 3) && !strip)
			throw new MatrixMathException("this generic matrix is not 3x3", this);
		
		Matrix4d m = new Matrix4d();
		for (int x = 0; x < width(); x++)
			for (int y = 0; y < height(); y++)
				m.set(x, y, m(x, y));
		return m;
	}
	
	public static MatrixNd columnVector(IVector2<Number> vector) {
		return new MatrixNd(1, 2, false).setColVecI(vector);
	}

	public static MatrixNd columnVector(IVector3<Number> vector) {
		return new MatrixNd(1, 3, false).setColVecI(vector);
	}
	
	public static MatrixNd columnVector(IVector4<Number> vector) {
		return new MatrixNd(1, 4, false).setColVecI(vector);
	}
	
	public static MatrixNd rowVector(IVector2<Number> vector) {
		return new MatrixNd(2, 1, false).setRowVecI(vector);
	}

	public static MatrixNd rowVector(IVector3<Number> vector) {
		return new MatrixNd(3, 1, false).setRowVecI(vector);
	}
	
	public static MatrixNd rowVector(IVector4<Number> vector) {
		return new MatrixNd(4, 1, false).setRowVecI(vector);
	}
	
}
