package de.m_marvin.unimat.impl;

import java.util.Objects;

import de.m_marvin.unimat.api.IMatrix;
import de.m_marvin.unimat.api.IQuaternion;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.impl.Vec3d;

public class Matrix4d extends BaseDoubleMatrix<Matrix4d> {
	
	public Matrix4d() {
		super(4, 4, false);
	}

	public Matrix4d(Matrix4f mat) {
		this();
		setI(mat);
	}
	
	public Matrix4d(
			double m00, double m10, double m20, double m30,
			double m01, double m11, double m21, double m31,
			double m02, double m12, double m22, double m32,
			double m03, double m13, double m23, double m33
	) {
		super(new double[][] {
			new double[] { m00, m10, m20, m30 },
			new double[] { m01, m11, m21, m31 },
			new double[] { m02, m12, m22, m32 },
			new double[] { m03, m13, m23, m33 }
		});
	}

	@Override
	protected Matrix4d newMatrix(int width, int height, boolean sparse) {
		assert sparse == false : "matrix can not be sparse";
		assert width == 4 && height == 4 : "matrix dimensions have to be 4x4";
		return new Matrix4d();
	}
	
	public MatrixNd getGeneric() {
		return new MatrixNd(get2DArray());
	}
	
	public static Matrix4d translate(IVector3<? extends Number> vec) {
		double vx = vec.x().doubleValue();
		double vy = vec.y().doubleValue();
		double vz = vec.z().doubleValue();
		return new Matrix4d(
				1,		0,		0,		vx,
				0,		1,		0,		vy,
				0,		0,		1,		vz,
				0,		0,		0,		1
		);
	}
	
	public static Matrix4d scale(IVector3<? extends Number> vec) {
		double vx = vec.x().doubleValue();
		double vy = vec.y().doubleValue();
		double vz = vec.z().doubleValue();
		return new Matrix4d(
				vx,		0,		0,		0,
				0,		vy, 	0,		0,
				0,		0,		vz,		0,
				0,		0,		0,		1
		);
	}
	
	public static Matrix4d rotationX(double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		return new Matrix4d(
				1,		0,		0,		0,
				0,		cos,	sin,	0,
				0,		-sin,	cos,	0,
				0,		0,		0,		1
		);
	}

	public static Matrix4d rotationY(double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		return new Matrix4d(
				cos,	0,		-sin,	0,
				0,		1,		0,		0,
				sin,	0,		cos,	0,
				0,		0,		0,		1
		);
	}
	
	public static Matrix4d rotationZ(double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		return new Matrix4d(
				cos,	-sin,	0,		0,
				sin,	cos,	0,		0,
				0,		0,		1,		0,
				0,		0,		0,		1
		);
	}
	
	public static Matrix4d rotation(IQuaternion<? extends Number> quat) {
		double qi = quat.i().doubleValue();
		double qj = quat.j().doubleValue();
		double qk = quat.k().doubleValue();
		double qr = quat.r().doubleValue();
		double qi2 = 2.0F * qi * qi;
		double qj2 = 2.0F * qj * qj;
		double qk2 = 2.0F * qk * qk;
		double qij = qi * qj;
		double qjk = qj * qk;
		double qki = qk * qi;
		double qir = qi * qr;
		double qjr = qj * qr;
		double qkr = qk * qr;
		return new Matrix4d(
				1.0F - qj2 - qk2,		2.0F * (qij + qkr),		2.0F * (qki - qjr),		0,
				2.0F * (qij - qkr),		1.0F - qk2 - qi2,		2.0F * (qjk + qir),		0,
				2.0F * (qki + qjr),		2.0F * (qjk - qir),		1.0F - qi2 - qj2,		0,
				0,						0,						0,						1.0F
		);
		
	}
	
	public static Matrix4d perspective(double fov, double aspect, double near, double far) {
		double frustumLength = far - near;
		double yScale = (double) ((1 / Math.tan(fov / 2)) * aspect);
		double xScale = yScale / aspect;
		return new Matrix4d(
				xScale,	0,		0,									0,
				0,		yScale,	0,									0,
				0,		0,		-((far + near) / frustumLength),	-((2 * near * far) / frustumLength),
				0,		0,		-1,									0
		);
	}
	
	public static Matrix4d orthographic(double left, double right, double bottom, double top, double near, double far) {
		double width = right - left;
		double height = bottom - top;
		double depth = far - near;
		return new Matrix4d(
				2.0 / width,				0,							0,						0,
				0,							2.0 / height,				0,						0,
				0,							0,							2.0 / depth,			0,
				-(right + left) / width,	-(bottom + top) / height,	-(far + near) / depth,	1
		);
	}

	public static void decompose(Vec3d translation, Vec3d scale, IMatrix<Double> rotation, IMatrix<Double> matrix) {
		Objects.requireNonNull(matrix, "matrix vector can not be null");
		if (rotation.width() != 3 || rotation.height() != 3)
			throw new IllegalArgumentException("rotation matrix must be 3x3");
		if (matrix.width() != 3 || matrix.height() != 3)
			throw new IllegalArgumentException("rotation matrix must be 4x4");
		
		if (translation != null)
			translation.setI(matrix.m(0, 3).doubleValue(), matrix.m(1, 3).doubleValue(), matrix.m(2, 3).doubleValue());
		
		if (scale != null || rotation != null) {
			if (scale == null)
				scale = new Vec3d();
			scale.setX(new Vec3d(matrix.m(0, 0).doubleValue(), matrix.m(0, 1).doubleValue(), matrix.m(0, 2).doubleValue()).length());
			scale.setX(new Vec3d(matrix.m(1, 0).doubleValue(), matrix.m(1, 1).doubleValue(), matrix.m(1, 2).doubleValue()).length());
			scale.setX(new Vec3d(matrix.m(2, 0).doubleValue(), matrix.m(2, 1).doubleValue(), matrix.m(2, 2).doubleValue()).length());
			
		}
		
		if (rotation != null) {
			rotation.set(0, 0, matrix.m(0, 0).doubleValue() / scale.x());
			rotation.set(1, 0, matrix.m(1, 0).doubleValue() / scale.y());
			rotation.set(2, 0, matrix.m(2, 0).doubleValue() / scale.z());
			rotation.set(0, 1, matrix.m(0, 1).doubleValue() / scale.x());
			rotation.set(1, 1, matrix.m(1, 1).doubleValue() / scale.y());
			rotation.set(2, 1, matrix.m(2, 1).doubleValue() / scale.z());
			rotation.set(0, 2, matrix.m(0, 2).doubleValue() / scale.x());
			rotation.set(1, 2, matrix.m(1, 2).doubleValue() / scale.y());
			rotation.set(2, 2, matrix.m(2, 2).doubleValue() / scale.z());
		}
	}
	
	public static Vec3d extractTranslation(IMatrix<Double> matrix) {
		Vec3d translation = new Vec3d();
		decompose(translation, null, null, matrix);
		return translation;
	}

}
