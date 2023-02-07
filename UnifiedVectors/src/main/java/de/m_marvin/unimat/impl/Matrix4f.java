package de.m_marvin.unimat.impl;

import de.m_marvin.unimat.api.IMatrix4f;
import de.m_marvin.unimat.api.IMatrixMath;
import de.m_marvin.unimat.api.IQuaternion;
import de.m_marvin.univec.api.IVector4;
import de.m_marvin.univec.impl.Vec4f;

public class Matrix4f implements IMatrix4f<Matrix4f>, IMatrixMath<Matrix4f, IVector4<Float>> {

	protected float m00;
	protected float m01;
	protected float m02;
	protected float m03;
	protected float m10;
	protected float m11;
	protected float m12;
	protected float m13;
	protected float m20;
	protected float m21;
	protected float m22;
	protected float m23;
	protected float m30;
	protected float m31;
	protected float m32;
	protected float m33;

	public Matrix4f(IQuaternion<?> p_27623_) {
		float f = p_27623_.i();
		float f1 = p_27623_.j();
		float f2 = p_27623_.k();
		float f3 = p_27623_.r();
		float f4 = 2.0F * f * f;
		float f5 = 2.0F * f1 * f1;
		float f6 = 2.0F * f2 * f2;
		this.m00 = 1.0F - f5 - f6;
		this.m11 = 1.0F - f6 - f4;
		this.m22 = 1.0F - f4 - f5;
		this.m33 = 1.0F;
		float f7 = f * f1;
		float f8 = f1 * f2;
		float f9 = f2 * f;
		float f10 = f * f3;
		float f11 = f1 * f3;
		float f12 = f2 * f3;
		this.m10 = 2.0F * (f7 + f12);
		this.m01 = 2.0F * (f7 - f12);
		this.m20 = 2.0F * (f9 - f11);
		this.m02 = 2.0F * (f9 + f11);
		this.m21 = 2.0F * (f8 + f10);
		this.m12 = 2.0F * (f8 - f10);
	}

	public Matrix4f(org.joml.Matrix4fc m) {
		this.m00 = m.m00();
		this.m01 = m.m01();
		this.m02 = m.m02();
		this.m03 = m.m03();
		this.m10 = m.m10();
		this.m11 = m.m11();
		this.m12 = m.m12();
		this.m13 = m.m13();
		this.m20 = m.m20();
		this.m21 = m.m21();
		this.m22 = m.m22();
		this.m23 = m.m23();
		this.m30 = m.m30();
		this.m31 = m.m31();
		this.m32 = m.m32();
		this.m33 = m.m33();
	}

	public org.joml.Matrix4fc convB() {
		return new org.joml.Matrix4f(
			this.m00,
			this.m01,
			this.m02,
			this.m03,
			this.m10,
			this.m11,
			this.m12,
			this.m13,
			this.m20,
			this.m21,
			this.m22,
			this.m23,
			this.m30,
			this.m31,
			this.m32,
			this.m33
		);
	}

	public org.joml.Matrix4f conv() {
		return new org.joml.Matrix4f(
				this.m00,
				this.m01,
				this.m02,
				this.m03,
				this.m10,
				this.m11,
				this.m12,
				this.m13,
				this.m20,
				this.m21,
				this.m22,
				this.m23,
				this.m30,
				this.m31,
				this.m32,
				this.m33
		);
	}
	
	public Matrix4f(
			float m00, float m01, float m02,  float m03, 
			float m10, float m11, float m12,  float m13, 
			float m20, float m21, float m22,  float m23, 
			float m30, float m31, float m32, float m33
		) {
		this.m00 = m00;
		this.m01 = m01;
		this.m02 = m02;
		this.m03 = m03;
		this.m10 = m10;
		this.m11 = m11;
		this.m12 = m12;
		this.m13 = m13;
		this.m20 = m20;
		this.m21 = m21;
		this.m22 = m22;
		this.m23 = m23;
		this.m30 = m30;
		this.m31 = m31;
		this.m32 = m32;
		this.m33 = m33;
	}

	public Matrix4f() {
		identity();
	}
	
	public static Matrix4f perspective(double fovDegrees, float aspect, float near, float far) {
		float frustumLength = far - near;
		float yScale = (float) ((1 / Math.tan(Math.toRadians(fovDegrees / 2))) * aspect);
		float xScale = yScale / aspect;
		Matrix4f matrix4f = new Matrix4f();
		matrix4f.m00 = xScale;
		matrix4f.m11 = yScale;
		matrix4f.m22 = -((far + near) / frustumLength);
		matrix4f.m23 = -1;
		matrix4f.m32 = -((2 * near * far) / frustumLength);
		matrix4f.m33 = 0;
		return matrix4f;
	}

	public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far) {
		Matrix4f matrix4f = new Matrix4f();
		float f = right - left;
		float f1 = bottom - top;
		float f2 = far - near;
		matrix4f.m00 = 2.0F / f;
		matrix4f.m11 = 2.0F / f1;
		matrix4f.m22 = 2.0F / f2;
		matrix4f.m03 = -(right + left) / f;
		matrix4f.m13 = -(bottom + top) / f1;
		matrix4f.m23 = -(far + near) / f2;
		matrix4f.m33 = 1.0F;
		return matrix4f;
	}

	public static Matrix4f scaleMatrix(float sx, float sy, float sz) {
		return new Matrix4f(
				sx, 0, 0, 0,
				0, sy, 0, 0,
				0, 0, sz, 0,
				0, 0, 0, 1
				);
	}

	public static Matrix4f translateMatrix(float x, float y, float z) {
		return new Matrix4f(
				1, 0, 0, x,
				0, 1, 0, y,
				0, 0, 1, z,
				0, 0, 0, 1
				);
	}

	public static Matrix4f rotationMatrixX(float radians) {
		float crad = (float) Math.cos(radians);
		float srad = (float) Math.sin(radians);
		return new Matrix4f(
				1, 0, 0, 0,
				0, crad, srad, 0,
				0, -srad, crad, 0,
				0, 0, 0, 1
				);
	}

	public static Matrix4f rotationMatrixY(float radians) {
		float crad = (float) Math.cos(radians);
		float srad = (float) Math.sin(radians);
		return new Matrix4f(
				crad, 0, -srad, 0,
				0, 1, 0, 0,
				srad, 0, crad, 0,
				0, 0, 0, 1
				);
	}

	public static Matrix4f rotationMatrixZ(float radians) {
		float crad = (float) Math.cos(radians);
		float srad = (float) Math.sin(radians);
		return new Matrix4f(
				crad, -srad, 0, 0,
				srad, crad, 0, 0,
				0, 0, 1, 0,
				0, 0, 0, 1
				);
	}
	
	public static Matrix4f rotationMatrix(float rx, float ry, float rz) {
		return rotationMatrixZ(rz).mul(rotationMatrixY(ry)).mul(rotationMatrixX(rx));
	}
	
	@Override
	public float getField(int x, int y) {
		switch (x) {
		case 0: {
			switch (y) {
			case 0: return m00;
			case 1: return m01;
			case 2: return m02;
			case 3: return m03;
			}
		}
		case 1: {
			switch (y) {
			case 0: return m10;
			case 1: return m11;
			case 2: return m12;
			case 3: return m13;
			}
		}
		case 2: {
			switch (y) {
			case 0: return m20;
			case 1: return m21;
			case 2: return m22;
			case 3: return m23;
			}
		}
		case 3: {
			switch (y) {
			case 0: return m30;
			case 1: return m31;
			case 2: return m32;
			case 3: return m33;
			}
		}
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void setField(int x, int y, float f) {
		switch (x) {
		case 0: {
			switch (y) {
			case 0: m00 = f; return;
			case 1: m01 = f; return;
			case 2: m02 = f; return;
			case 3: m03 = f; return;
			}
		}
		case 1: {
			switch (y) {
			case 0: m10 = f; return;
			case 1: m11 = f; return;
			case 2: m12 = f; return;
			case 3: m13 = f; return;
			}
		}
		case 2: {
			switch (y) {
			case 0: m20 = f; return;
			case 1: m21 = f; return;
			case 2: m22 = f; return;
			case 3: m23 = f; return;
			}
		}
		case 3: {
			switch (y) {
			case 0: m30 = f; return;
			case 1: m31 = f; return;
			case 2: m32 = f; return;
			case 3: m33 = f; return;
			}
		}
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public float m00() {
		return this.m00;
	}

	@Override
	public float m01() {
		return this.m01;
	}

	@Override
	public float m02() {
		return this.m02;
	}

	@Override
	public float m03() {
		return this.m03;
	}

	@Override
	public float m10() {
		return this.m10;
	}

	@Override
	public float m11() {
		return this.m11;
	}

	@Override
	public float m12() {
		return this.m12;
	}

	@Override
	public float m13() {
		return this.m13;
	}

	@Override
	public float m20() {
		return this.m20;
	}

	@Override
	public float m21() {
		return this.m21;
	}

	@Override
	public float m22() {
		return this.m22;
	}

	@Override
	public float m23() {
		return this.m23;
	}

	@Override
	public float m30() {
		return this.m30;
	}

	@Override
	public float m31() {
		return this.m31;
	}

	@Override
	public float m32() {
		return this.m32;
	}

	@Override
	public float m33() {
		return this.m33;
	}

	@Override
	public Matrix4f setI(Matrix4f mat) {
		this.m00 = mat.m00();
		this.m01 = mat.m01();
		this.m02 = mat.m02();
		this.m03 = mat.m03();
		this.m10 = mat.m10();
		this.m11 = mat.m11();
		this.m12 = mat.m12();
		this.m13 = mat.m13();
		this.m20 = mat.m20();
		this.m21 = mat.m21();
		this.m22 = mat.m22();
		this.m23 = mat.m23();
		this.m30 = mat.m30();
		this.m31 = mat.m31();
		this.m32 = mat.m32();
		this.m33 = mat.m33();
		return this;
	}

	@Override
	public Matrix4f copy() {
		return new Matrix4f(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
	}

	@Override
	public Matrix4f add(Matrix4f mat) {
		return new Matrix4f(m00 + mat.m00(), m01 + mat.m01(), m02 + mat.m02(), m03 + mat.m03(), m10 + mat.m10(), m11 + mat.m11(), m12 + mat.m12(), m13 + mat.m13(), m20 + mat.m20(), m21 + mat.m21(), m22 + mat.m22(), m23 + mat.m23(), m30 + mat.m30(), m31 + mat.m31(), m32 + mat.m32(), m33 + mat.m33());
	}

	@Override
	public Matrix4f sub(Matrix4f mat) {
		return new Matrix4f(m00 - mat.m00(), m01 - mat.m01(), m02 - mat.m02(), m03 - mat.m03(), m10 - mat.m10(), m11 - mat.m11(), m12 - mat.m12(), m13 - mat.m13(), m20 - mat.m20(), m21 - mat.m21(), m22 - mat.m22(), m23 - mat.m23(), m30 - mat.m30(), m31 - mat.m31(), m32 - mat.m32(), m33 - mat.m33());
	}

	@Override
	public Matrix4f mul(Matrix4f mat) {
//		return new Matrix4f(
//				mat.m00 + mat.m01 + mat.m02 + mat.m03
//			);
		
		return new Matrix4f(
			this.m00 * mat.m00 + this.m01 * mat.m10 + this.m02 * mat.m20 + this.m03 * mat.m30,
			this.m00 * mat.m01 + this.m01 * mat.m11 + this.m02 * mat.m21 + this.m03 * mat.m31,
			this.m00 * mat.m02 + this.m01 * mat.m12 + this.m02 * mat.m22 + this.m03 * mat.m32,
			this.m00 * mat.m03 + this.m01 * mat.m13 + this.m02 * mat.m23 + this.m03 * mat.m33,
			this.m10 * mat.m00 + this.m11 * mat.m10 + this.m12 * mat.m20 + this.m13 * mat.m30,
			this.m10 * mat.m01 + this.m11 * mat.m11 + this.m12 * mat.m21 + this.m13 * mat.m31,
			this.m10 * mat.m02 + this.m11 * mat.m12 + this.m12 * mat.m22 + this.m13 * mat.m32,
			this.m10 * mat.m03 + this.m11 * mat.m13 + this.m12 * mat.m23 + this.m13 * mat.m33,
			this.m20 * mat.m00 + this.m21 * mat.m10 + this.m22 * mat.m20 + this.m23 * mat.m30,
			this.m20 * mat.m01 + this.m21 * mat.m11 + this.m22 * mat.m21 + this.m23 * mat.m31,
			this.m20 * mat.m02 + this.m21 * mat.m12 + this.m22 * mat.m22 + this.m23 * mat.m32,
			this.m20 * mat.m03 + this.m21 * mat.m13 + this.m22 * mat.m23 + this.m23 * mat.m33,
			this.m30 * mat.m00 + this.m31 * mat.m10 + this.m32 * mat.m20 + this.m33 * mat.m30,
			this.m30 * mat.m01 + this.m31 * mat.m11 + this.m32 * mat.m21 + this.m33 * mat.m31,
			this.m30 * mat.m02 + this.m31 * mat.m12 + this.m32 * mat.m22 + this.m33 * mat.m32,
			this.m30 * mat.m03 + this.m31 * mat.m13 + this.m32 * mat.m23 + this.m33 * mat.m33
		);
	}

	@Override
	public Matrix4f identity() {
		this.m00 = 1;
		this.m01 = 0;
		this.m02 = 0;
		this.m03 = 0;
		this.m10 = 0;
		this.m11 = 1;
		this.m12 = 0;
		this.m13 = 0;
		this.m20 = 0;
		this.m21 = 0;
		this.m22 = 1;
		this.m23 = 0;
		this.m30 = 0;
		this.m31 = 0;
		this.m32 = 0;
		this.m33 = 1;
		return this;
	}

	@Override
	public Matrix4f scalar(float f) {
		return new Matrix4f(m00 * f, m01 * f, m02 * f, m03 * f, m10 * f, m11 * f, m12 * f, m13 * f, m20 * f, m21 * f, m22 * f, m23 * f, m30 * f, m31 * f, m32 * f, m33 * f);
	}

	@Override
	public float adjugateAndDet() {
		float f = this.m00 * this.m11 - this.m01 * this.m10;
		float f1 = this.m00 * this.m12 - this.m02 * this.m10;
		float f2 = this.m00 * this.m13 - this.m03 * this.m10;
		float f3 = this.m01 * this.m12 - this.m02 * this.m11;
		float f4 = this.m01 * this.m13 - this.m03 * this.m11;
		float f5 = this.m02 * this.m13 - this.m03 * this.m12;
		float f6 = this.m20 * this.m31 - this.m21 * this.m30;
		float f7 = this.m20 * this.m32 - this.m22 * this.m30;
		float f8 = this.m20 * this.m33 - this.m23 * this.m30;
		float f9 = this.m21 * this.m32 - this.m22 * this.m31;
		float f10 = this.m21 * this.m33 - this.m23 * this.m31;
		float f11 = this.m22 * this.m33 - this.m23 * this.m32;
		float f12 = this.m11 * f11 - this.m12 * f10 + this.m13 * f9;
		float f13 = -this.m10 * f11 + this.m12 * f8 - this.m13 * f7;
		float f14 = this.m10 * f10 - this.m11 * f8 + this.m13 * f6;
		float f15 = -this.m10 * f9 + this.m11 * f7 - this.m12 * f6;
		float f16 = -this.m01 * f11 + this.m02 * f10 - this.m03 * f9;
		float f17 = this.m00 * f11 - this.m02 * f8 + this.m03 * f7;
		float f18 = -this.m00 * f10 + this.m01 * f8 - this.m03 * f6;
		float f19 = this.m00 * f9 - this.m01 * f7 + this.m02 * f6;
		float f20 = this.m31 * f5 - this.m32 * f4 + this.m33 * f3;
		float f21 = -this.m30 * f5 + this.m32 * f2 - this.m33 * f1;
		float f22 = this.m30 * f4 - this.m31 * f2 + this.m33 * f;
		float f23 = -this.m30 * f3 + this.m31 * f1 - this.m32 * f;
		float f24 = -this.m21 * f5 + this.m22 * f4 - this.m23 * f3;
		float f25 = this.m20 * f5 - this.m22 * f2 + this.m23 * f1;
		float f26 = -this.m20 * f4 + this.m21 * f2 - this.m23 * f;
		float f27 = this.m20 * f3 - this.m21 * f1 + this.m22 * f;
		this.m00 = f12;
		this.m10 = f13;
		this.m20 = f14;
		this.m30 = f15;
		this.m01 = f16;
		this.m11 = f17;
		this.m21 = f18;
		this.m31 = f19;
		this.m02 = f20;
		this.m12 = f21;
		this.m22 = f22;
		this.m32 = f23;
		this.m03 = f24;
		this.m13 = f25;
		this.m23 = f26;
		this.m33 = f27;
		return f * f11 - f1 * f10 + f2 * f9 + f3 * f8 - f4 * f7 + f5 * f6;
	}

	@Override
	public float determinant() {
		float f = this.m00 * this.m11 - this.m01 * this.m10;
		float f1 = this.m00 * this.m12 - this.m02 * this.m10;
		float f2 = this.m00 * this.m13 - this.m03 * this.m10;
		float f3 = this.m01 * this.m12 - this.m02 * this.m11;
		float f4 = this.m01 * this.m13 - this.m03 * this.m11;
		float f5 = this.m02 * this.m13 - this.m03 * this.m12;
		float f6 = this.m20 * this.m31 - this.m21 * this.m30;
		float f7 = this.m20 * this.m32 - this.m22 * this.m30;
		float f8 = this.m20 * this.m33 - this.m23 * this.m30;
		float f9 = this.m21 * this.m32 - this.m22 * this.m31;
		float f10 = this.m21 * this.m33 - this.m23 * this.m31;
		float f11 = this.m22 * this.m33 - this.m23 * this.m32;
		return f * f11 - f1 * f10 + f2 * f9 + f3 * f8 - f4 * f7 + f5 * f6;
	}

	@Override
	public Matrix4f transpose() {
		return new Matrix4f(
				m00, m10, m20, m30, 
				m01, m11, m21, m31, 
				m02, m12, m22, m32, 
				m03, m13, m23, m33
				);
	}

	@Override
	public boolean invert() {
		float f = this.adjugateAndDet();
		if (Math.abs(f) > 1.0E-6F) {
			this.scalarI(f);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Matrix4f mul(IQuaternion<?> quat) {
		return mul(new Matrix4f(quat));
	}

	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj instanceof Matrix4f other) {
			return
					Float.compare(m00, other.m00()) == 0 &&
					Float.compare(m01, other.m01()) == 0 &&
					Float.compare(m02, other.m02()) == 0 &&
					Float.compare(m03, other.m03()) == 0 &&
					Float.compare(m10, other.m10()) == 0 &&
					Float.compare(m11, other.m11()) == 0 &&
					Float.compare(m12, other.m12()) == 0 &&
					Float.compare(m13, other.m13()) == 0 &&
					Float.compare(m20, other.m20()) == 0 &&
					Float.compare(m21, other.m21()) == 0 &&
					Float.compare(m22, other.m22()) == 0 &&
					Float.compare(m23, other.m23()) == 0 &&
					Float.compare(m30, other.m30()) == 0 &&
					Float.compare(m31, other.m31()) == 0 &&
					Float.compare(m32, other.m32()) == 0 &&
					Float.compare(m33, other.m33()) == 0;
		}
		return false;
	}

	public int hashCode() {
		int i = this.m00 != 0.0F ? Float.floatToIntBits(this.m00) : 0;
		i = 31 * i + (this.m01 != 0.0F ? Float.floatToIntBits(this.m01) : 0);
		i = 31 * i + (this.m02 != 0.0F ? Float.floatToIntBits(this.m02) : 0);
		i = 31 * i + (this.m03 != 0.0F ? Float.floatToIntBits(this.m03) : 0);
		i = 31 * i + (this.m10 != 0.0F ? Float.floatToIntBits(this.m10) : 0);
		i = 31 * i + (this.m11 != 0.0F ? Float.floatToIntBits(this.m11) : 0);
		i = 31 * i + (this.m12 != 0.0F ? Float.floatToIntBits(this.m12) : 0);
		i = 31 * i + (this.m13 != 0.0F ? Float.floatToIntBits(this.m13) : 0);
		i = 31 * i + (this.m20 != 0.0F ? Float.floatToIntBits(this.m20) : 0);
		i = 31 * i + (this.m21 != 0.0F ? Float.floatToIntBits(this.m21) : 0);
		i = 31 * i + (this.m22 != 0.0F ? Float.floatToIntBits(this.m22) : 0);
		i = 31 * i + (this.m23 != 0.0F ? Float.floatToIntBits(this.m23) : 0);
		i = 31 * i + (this.m30 != 0.0F ? Float.floatToIntBits(this.m30) : 0);
		i = 31 * i + (this.m31 != 0.0F ? Float.floatToIntBits(this.m31) : 0);
		i = 31 * i + (this.m32 != 0.0F ? Float.floatToIntBits(this.m32) : 0);
		return 31 * i + (this.m33 != 0.0F ? Float.floatToIntBits(this.m33) : 0);
	}

	public String toString() {
		return "Matrix4f:\n"
				+ this.m00 + " " + this.m01 + " " + this.m02 + " " + this.m03 + "\n"
				+ this.m10 + " " + this.m11 + " " + this.m12 + " " + this.m13 + "\n"
				+ this.m20 + " " + this.m21 + " " + this.m22 + " " + this.m23 + "\n"
				+ this.m30 + " " + this.m31 + " " + this.m32 + " " + this.m33;
	}

	@Override
	public IVector4<Float> translate(IVector4<Float> vec) {
		float f = (float) vec.x();
		float f1 = (float) vec.y();
		float f2 = (float) vec.z();
		float f3 = (float) vec.w();
		return new Vec4f(
				m00 * f + m01 * f1 + m02 * f2 + m03 * f3,
				m10 * f + m11 * f1 + m12 * f2 + m13 * f3,
				m20 * f + m21 * f1 + m22 * f2 + m23 * f3,
				m30 * f + m31 * f1 + m32 * f2 + m33 * f3
			);
	}

	@Override
	public float[] toFloatArr() {
		return new float[] {
				m00, m10, m20, m30,
				m01, m11, m21, m31,
				m02, m12, m22, m32,
				m03, m13, m23, m33
		};
	}

	@Override
	public void loadFloatArr(float[] arr) {
		if (arr.length != 16) throw new IllegalArgumentException("Matrix float arr has to be of length 16!");
		this.m00 = arr[0];
		this.m10 = arr[1];
		this.m20 = arr[2];
		this.m30 = arr[3];
		this.m01 = arr[4];
		this.m11 = arr[5];
		this.m21 = arr[6];
		this.m31 = arr[7];
		this.m02 = arr[8];
		this.m12 = arr[9];
		this.m22 = arr[10];
		this.m32 = arr[11];
		this.m03 = arr[12];
		this.m13 = arr[13];
		this.m23 = arr[14];
		this.m33 = arr[15];
	}

}
