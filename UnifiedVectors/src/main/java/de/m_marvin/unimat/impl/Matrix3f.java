package de.m_marvin.unimat.impl;

import java.util.Objects;

import de.m_marvin.unimat.api.IMatrix;
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
				0,		cos,	-sin,
				0,		sin,	cos
		);
	}

	public static Matrix3f rotationY(float angle) {
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		return new Matrix3f(
				cos,	0,		sin,
				0,		1,		0,
				-sin,	0,		cos
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
				1.0F - qj2 - qk2,		2.0F * (qij - qkr),		2.0F * (qki + qjr),
				2.0F * (qij + qkr),		1.0F - qk2 - qi2,		2.0F * (qjk - qir),
				2.0F * (qki - qjr),		2.0F * (qjk + qir),		1.0F - qi2 - qj2
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

	public static void decompose(Vec3f scale, IMatrix<Float> rotation, IMatrix<Float> matrix) {
		Objects.requireNonNull(matrix, "matrix can not be null");
		if (rotation != null && (rotation.width() != 3 || rotation.height() != 3))
			throw new IllegalArgumentException("rotation matrix must be 3x3");
		if ((matrix.width() != 4 || matrix.height() != 4) && (matrix.width() != 3 || matrix.height() != 3))
			throw new IllegalArgumentException("input matrix must be 3x3 or 4x4");
		
		if (scale != null || rotation != null) {
			if (scale == null)
				scale = new Vec3f();
			scale.setX(new Vec3f(matrix.m(0, 0).floatValue(), matrix.m(1, 0).floatValue(), matrix.m(2, 0).floatValue()).length());
			scale.setY(new Vec3f(matrix.m(0, 1).floatValue(), matrix.m(1, 1).floatValue(), matrix.m(2, 1).floatValue()).length());
			scale.setZ(new Vec3f(matrix.m(0, 2).floatValue(), matrix.m(1, 2).floatValue(), matrix.m(2, 2).floatValue()).length());
		}
		
		if (rotation != null) {
			rotation.set(0, 1, matrix.m(0, 1).floatValue() / scale.y());
			rotation.set(0, 0, matrix.m(0, 0).floatValue() / scale.x());
			rotation.set(0, 2, matrix.m(0, 2).floatValue() / scale.z());
			rotation.set(1, 0, matrix.m(1, 0).floatValue() / scale.x());
			rotation.set(1, 1, matrix.m(1, 1).floatValue() / scale.y());
			rotation.set(1, 2, matrix.m(1, 2).floatValue() / scale.z());
			rotation.set(2, 0, matrix.m(2, 0).floatValue() / scale.x());
			rotation.set(2, 1, matrix.m(2, 1).floatValue() / scale.y());
			rotation.set(2, 2, matrix.m(2, 2).floatValue() / scale.z());
		}
	}

	public static void decompose(Vec3f scale, Quaternionf rotation, IMatrix<Float> matrix) {
		Matrix3f rotationMatrix = rotation != null ? new Matrix3f() : null;
		decompose(scale, rotationMatrix, matrix);
		
		if (rotation != null) {

			float trace = rotationMatrix.trace();
			
			if (trace > 0) {
				
				float w2 = (float) Math.sqrt(1 + trace);
				float w4 = w2 * 2;
				float x = (rotationMatrix.m21() - rotationMatrix.m12()) / w4;
				float y = (rotationMatrix.m02() - rotationMatrix.m20()) / w4;
				float z = (rotationMatrix.m10() - rotationMatrix.m01()) / w4;
				rotation.setI(x, y, z, w2 * 0.5F);
				
			} else if (rotationMatrix.m00() > rotationMatrix.m11() && rotationMatrix.m00() > rotationMatrix.m22()) {

				float x2 = (float) Math.sqrt(1 + rotationMatrix.m00() - rotationMatrix.m11() - rotationMatrix.m22());
				float x4 = x2 * 2;
				float w = (rotationMatrix.m21() - rotationMatrix.m12()) / x4;
				float y = (rotationMatrix.m01() + rotationMatrix.m10()) / x4;
				float z = (rotationMatrix.m02() + rotationMatrix.m20()) / x4;
				rotation.setI(x2 * 0.5F, y, z, w);
				
			} else if (rotationMatrix.m11() > rotationMatrix.m00() && rotationMatrix.m11() > rotationMatrix.m22()) {

				float y2 = (float) Math.sqrt(1 + rotationMatrix.m11() - rotationMatrix.m00() - rotationMatrix.m22());
				float y4 = y2 * 2;
				float w = (rotationMatrix.m02() - rotationMatrix.m20()) / y4;
				float x = (rotationMatrix.m01() + rotationMatrix.m10()) / y4;
				float z = (rotationMatrix.m12() + rotationMatrix.m21()) / y4;
				rotation.setI(x, y2 * 0.5F, z, w);
				
			} else {

				float z2 = (float) Math.sqrt(1 + rotationMatrix.m22() - rotationMatrix.m00() - rotationMatrix.m11());
				float z4 = z2 * 2;
				float w = (rotationMatrix.m10() - rotationMatrix.m01()) / z4;
				float x = (rotationMatrix.m02() + rotationMatrix.m20()) / z4;
				float y = (rotationMatrix.m12() + rotationMatrix.m21()) / z4;
				rotation.setI(x, y, z2 * 0.5F, w);
				
			}
			
			rotation.normalizeI();
			
		}
		
	}
	
}
