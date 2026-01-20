package de.m_marvin.unimat.impl;

import de.m_marvin.unimat.api.IQuaternion;
import de.m_marvin.univec.impl.Vec2d;
import de.m_marvin.univec.impl.Vec3d;

public class Matrix3d extends BaseDoubleMatrix<Matrix3d> {
	
	public Matrix3d() {
		super(3, 3, false);
	}
	
	public Matrix3d(Matrix3f mat) {
		this();
		setI(mat);
	}
	
	public Matrix3d(
			double m00, double m10, double m20,
			double m01, double m11, double m21,
			double m02, double m12, double m22
	) {
		super(new double[][] {
			new double[] { m00, m10, m20 },
			new double[] { m01, m11, m21 },
			new double[] { m02, m12, m22 }
		});
	}
	
	public double m00() {
		return m(0, 0);
	}

	public double m10() {
		return m(1, 0);
	}

	public double m20() {
		return m(2, 0);
	}
	
	public double m01() {
		return m(0, 1);
	}

	public double m11() {
		return m(1, 1);
	}

	public double m21() {
		return m(2, 1);
	}
	
	public double m02() {
		return m(0, 2);
	}

	public double m12() {
		return m(1, 2);
	}

	public double m22() {
		return m(2, 2);
	}

	@Override
	protected Matrix3d newMatrix(int width, int height, boolean sparse) {
		assert sparse == false : "matrix can not be sparse";
		assert width == 3 && height == 3 : "matrix dimensions have to be 3x3";
		return new Matrix3d();
	}
	
	public MatrixNd getGeneric() {
		return new MatrixNd(get2DArray());
	}
	
	public static Matrix3d scale(Vec3d vec) {
		return new Matrix3d(
				vec.x,	0,		0,
				0,		vec.y, 	0,
				0,		0,		vec.z
		);
	}
	
	public static Matrix3d rotationX(double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		return new Matrix3d(
				1,		0,		0,
				0,		cos,	sin,
				0,		-sin,	cos
		);
	}

	public static Matrix3d rotationY(double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		return new Matrix3d(
				cos,	0,		-sin,
				0,		1,		0,
				sin,	0,		cos
		);
	}
	
	public static Matrix3d rotationZ(double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		return new Matrix3d(
				cos,	-sin,	0,
				sin,	cos,	0,
				0,		0,		1
		);
	}

	public static Matrix3d rotation(IQuaternion<? extends Number> quat) {
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
		return new Matrix3d(
				1.0F - qj2 - qk2,		2.0F * (qij + qkr),		2.0F * (qki - qjr),
				2.0F * (qij - qkr),		1.0F - qk2 - qi2,		2.0F * (qjk + qir),
				2.0F * (qki + qjr),		2.0F * (qjk - qir),		1.0F - qi2 - qj2
		);
	}

	public static Matrix3d translation(Vec2d vec) {
		return new Matrix3d(
				1,		0,		vec.x,
				0,		1, 		vec.y,
				0,		0,		1
		);
	}
	
	public static Matrix3d scale(Vec2d vec) {
		return new Matrix3d(
				vec.x,	0,		0,
				0,		vec.y, 	0,
				0,		0,		1
		);
	}
	
}
