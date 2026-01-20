package de.m_marvin.unimat.impl;

import de.m_marvin.unimat.api.IQuaternion;
import de.m_marvin.univec.impl.Vec2f;
import de.m_marvin.univec.impl.Vec3f;

public class Matrix3f extends BaseFloatMatrix<Matrix3f> {
	
	public Matrix3f() {
		super(3, 3, false);
	}

	public Matrix3f(Matrix3d mat) {
		this();
		setI(mat);
	}
	
	public Matrix3f(
			float m00, float m10, float m20,
			float m01, float m11, float m21,
			float m02, float m12, float m22
	) {
		super(new float[][] {
			new float[] { m00, m10, m20 },
			new float[] { m01, m11, m21 },
			new float[] { m02, m12, m22 }
		});
	}

	public float m00() {
		return m(0, 0);
	}

	public float m10() {
		return m(1, 0);
	}

	public float m20() {
		return m(2, 0);
	}
	
	public float m01() {
		return m(0, 1);
	}

	public float m11() {
		return m(1, 1);
	}

	public float m21() {
		return m(2, 1);
	}
	
	public float m02() {
		return m(0, 2);
	}

	public float m12() {
		return m(1, 2);
	}

	public float m22() {
		return m(2, 2);
	}

	@Override
	protected Matrix3f newMatrix(int width, int height, boolean sparse) {
		assert sparse == false : "matrix can not be sparse";
		assert width == 3 && height == 3 : "matrix dimensions have to be 3x3";
		return new Matrix3f();
	}
	
	public MatrixNf getGeneric() {
		return new MatrixNf(get2DArray());
	}
	
	public static Matrix3f scale(Vec3f vec) {
		return new Matrix3f(
				vec.x,	0,		0,
				0,		vec.y, 	0,
				0,		0,		vec.z
		);
	}
	
	public static Matrix3f rotationX(float angle) {
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		return new Matrix3f(
				1,		0,		0,
				0,		cos,	sin,
				0,		-sin,	cos
		);
	}

	public static Matrix3f rotationY(float angle) {
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		return new Matrix3f(
				cos,	0,		-sin,
				0,		1,		0,
				sin,	0,		cos
		);
	}
	
	public static Matrix3f rotationZ(float angle) {
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		return new Matrix3f(
				cos,	-sin,	0,
				sin,	cos,	0,
				0,		0,		1
		);
	}

	public static Matrix3f rotation(IQuaternion<? extends Number> quat) {
		float qi = quat.i().floatValue();
		float qj = quat.j().floatValue();
		float qk = quat.k().floatValue();
		float qr = quat.r().floatValue();
		float qi2 = 2.0F * qi * qi;
		float qj2 = 2.0F * qj * qj;
		float qk2 = 2.0F * qk * qk;
		float qij = qi * qj;
		float qjk = qj * qk;
		float qki = qk * qi;
		float qir = qi * qr;
		float qjr = qj * qr;
		float qkr = qk * qr;
		return new Matrix3f(
				1.0F - qj2 - qk2,		2.0F * (qij + qkr),		2.0F * (qki - qjr),
				2.0F * (qij - qkr),		1.0F - qk2 - qi2,		2.0F * (qjk + qir),
				2.0F * (qki + qjr),		2.0F * (qjk - qir),		1.0F - qi2 - qj2
		);
	}

	public static Matrix3f translation(Vec2f vec) {
		return new Matrix3f(
				1,		0,		vec.x,
				0,		1, 		vec.y,
				0,		0,		1
		);
	}
	
	public static Matrix3f scale(Vec2f vec) {
		return new Matrix3f(
				vec.x,	0,		0,
				0,		vec.y, 	0,
				0,		0,		1
		);
	}
	
}
