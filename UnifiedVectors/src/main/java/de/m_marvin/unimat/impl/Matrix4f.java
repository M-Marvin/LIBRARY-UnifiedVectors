package de.m_marvin.unimat.impl;

import java.util.Objects;

import de.m_marvin.unimat.api.IMatrix;
import de.m_marvin.unimat.api.IQuaternion;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.impl.Vec3f;

public class Matrix4f extends BaseFloatMatrix<Matrix4f> {
	
	public Matrix4f() {
		super(4, 4);
	}

	public Matrix4f(Matrix4d mat) {
		this();
		setI(mat);
	}
	
	public Matrix4f(
			float m00, float m10, float m20, float m30,
			float m01, float m11, float m21, float m31,
			float m02, float m12, float m22, float m32,
			float m03, float m13, float m23, float m33
	) {
		super(new Float[][] {
			new Float[] { m00, m10, m20, m30 },
			new Float[] { m01, m11, m21, m31 },
			new Float[] { m02, m12, m22, m32 },
			new Float[] { m03, m13, m23, m33 }
		});
	}

	@Override
	protected Matrix4f newMatrix(int width, int height) {
		assert width == 4 && height == 4 : "matrix dimensions have to be 4x4";
		return new Matrix4f();
	}
	
	public MatrixNf getGeneric() {
		return new MatrixNf(this.m);
	}

	public static Matrix4f translate(IVector3<? extends Number> vec) {
		float vx = vec.x().floatValue();
		float vy = vec.y().floatValue();
		float vz = vec.z().floatValue();
		return new Matrix4f(
				1,		0,		0,		vx,
				0,		1,		0,		vy,
				0,		0,		1,		vz,
				0,		0,		0,		1
		);
	}
	
	public static Matrix4f scale(IVector3<? extends Number> vec) {
		float vx = vec.x().floatValue();
		float vy = vec.y().floatValue();
		float vz = vec.z().floatValue();
		return new Matrix4f(
				vx,		0,		0,		0,
				0,		vy, 	0,		0,
				0,		0,		vz,		0,
				0,		0,		0,		1
		);
	}
	
	public static Matrix4f rotationX(float angle) {
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		return new Matrix4f(
				1,		0,		0,		0,
				0,		cos,	sin,	0,
				0,		-sin,	cos,	0,
				0,		0,		0,		1
		);
	}

	public static Matrix4f rotationY(float angle) {
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		return new Matrix4f(
				cos,	0,		-sin,	0,
				0,		1,		0,		0,
				sin,	0,		cos,	0,
				0,		0,		0,		1
		);
	}
	
	public static Matrix4f rotationZ(float angle) {
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		return new Matrix4f(
				cos,	-sin,	0,		0,
				sin,	cos,	0,		0,
				0,		0,		1,		0,
				0,		0,		0,		1
		);
	}
	
	public static Matrix4f rotation(IQuaternion<? extends Number> quat) {
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
		return new Matrix4f(
				1.0F - qj2 - qk2,		2.0F * (qij + qkr),		2.0F * (qki - qjr),		0,
				2.0F * (qij - qkr),		1.0F - qk2 - qi2,		2.0F * (qjk + qir),		0,
				2.0F * (qki + qjr),		2.0F * (qjk - qir),		1.0F - qi2 - qj2,		0,
				0,						0,						0,						1.0F
		);
		
	}
	
	public static Matrix4f perspective(float fov, float aspect, float near, float far) {
		float frustumLength = far - near;
		float yScale = (float) ((1 / Math.tan(fov / 2)) * aspect);
		float xScale = yScale / aspect;
		return new Matrix4f(
				xScale,	0,		0,									0,
				0,		yScale,	0,									0,
				0,		0,		-((far + near) / frustumLength),	-((2 * near * far) / frustumLength),
				0,		0,		-1,									0
		);
	}
	
	public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far) {
		float width = right - left;
		float height = bottom - top;
		float depth = far - near;
		return new Matrix4f(
				2.0F / width,				0,							0,						0,
				0,							2.0F / height,				0,						0,
				0,							0,							2.0F / depth,			0,
				-(right + left) / width,	-(bottom + top) / height,	-(far + near) / depth,	1
		);
	}

	public static void decompose(Vec3f translation, Vec3f scale, IMatrix<Float> rotation, IMatrix<Float> matrix) {
		Objects.requireNonNull(matrix, "matrix vector can not be null");
		if (rotation.width() != 3 || rotation.height() != 3)
			throw new IllegalArgumentException("rotation matrix must be 3x3");
		if (matrix.width() != 3 || matrix.height() != 3)
			throw new IllegalArgumentException("rotation matrix must be 4x4");
		
		if (translation != null)
			translation.setI(matrix.m(0, 3).floatValue(), matrix.m(1, 3).floatValue(), matrix.m(2, 3).floatValue());
		
		if (scale != null || rotation != null) {
			if (scale == null)
				scale = new Vec3f();
			scale.setX(new Vec3f(matrix.m(0, 0).floatValue(), matrix.m(0, 1).floatValue(), matrix.m(0, 2).floatValue()).length());
			scale.setX(new Vec3f(matrix.m(1, 0).floatValue(), matrix.m(1, 1).floatValue(), matrix.m(1, 2).floatValue()).length());
			scale.setX(new Vec3f(matrix.m(2, 0).floatValue(), matrix.m(2, 1).floatValue(), matrix.m(2, 2).floatValue()).length());
			
		}
		
		if (rotation != null) {
			rotation.set(0, 0, matrix.m(0, 0).floatValue() / scale.x());
			rotation.set(1, 0, matrix.m(1, 0).floatValue() / scale.y());
			rotation.set(2, 0, matrix.m(2, 0).floatValue() / scale.z());
			rotation.set(0, 1, matrix.m(0, 1).floatValue() / scale.x());
			rotation.set(1, 1, matrix.m(1, 1).floatValue() / scale.y());
			rotation.set(2, 1, matrix.m(2, 1).floatValue() / scale.z());
			rotation.set(0, 2, matrix.m(0, 2).floatValue() / scale.x());
			rotation.set(1, 2, matrix.m(1, 2).floatValue() / scale.y());
			rotation.set(2, 2, matrix.m(2, 2).floatValue() / scale.z());
		}
	}
	
	public static Vec3f extractTranslation(IMatrix<Float> matrix) {
		Vec3f translation = new Vec3f();
		decompose(translation, null, null, matrix);
		return translation;
	}

}
