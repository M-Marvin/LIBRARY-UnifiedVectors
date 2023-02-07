package de.m_marvin.unimat.impl;

import de.m_marvin.unimat.api.IMatrix3f;
import de.m_marvin.unimat.api.IMatrixMath;
import de.m_marvin.unimat.api.IQuaternion;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.impl.Vec3f;

public class Matrix3f implements IMatrix3f<Matrix3f>, IMatrixMath<Matrix3f, IVector3<Float>> {
	
	protected float m00;
	protected float m01;
	protected float m02;
	protected float m10;
	protected float m11;
	protected float m12;
	protected float m20;
	protected float m21;
	protected float m22;

	public Matrix3f(IQuaternion<?> p_8154_) {
		float f = p_8154_.i();
		float f1 = p_8154_.j();
		float f2 = p_8154_.k();
		float f3 = p_8154_.r();
		float f4 = 2.0F * f * f;
		float f5 = 2.0F * f1 * f1;
		float f6 = 2.0F * f2 * f2;
		this.m00 = 1.0F - f5 - f6;
		this.m11 = 1.0F - f6 - f4;
		this.m22 = 1.0F - f4 - f5;
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

	public Matrix3f(org.joml.Matrix3fc m) {
		this.m00 = m.m00();
		this.m01 = m.m01();
		this.m02 = m.m02();
		this.m10 = m.m10();
		this.m11 = m.m11();
		this.m12 = m.m12();
		this.m20 = m.m20();
		this.m21 = m.m21();
		this.m22 = m.m22();
	}

	public org.joml.Matrix3fc convB() {
		return new org.joml.Matrix3f(
			this.m00,
			this.m01,
			this.m02,
			this.m10,
			this.m11,
			this.m12,
			this.m20,
			this.m21,
			this.m22
		);
	}

	public org.joml.Matrix3f conv() {
		return new org.joml.Matrix3f(
				this.m00,
				this.m01,
				this.m02,
				this.m10,
				this.m11,
				this.m12,
				this.m20,
				this.m21,
				this.m22
		);
	}

	public Matrix3f(
			float m00, float m01, float m02, 
			float m10, float m11, float m12, 
			float m20, float m21, float m22
		) {
		this.m00 = m00;
		this.m01 = m01;
		this.m02 = m02;
		this.m10 = m10;
		this.m11 = m11;
		this.m12 = m12;
		this.m20 = m20;
		this.m21 = m21;
		this.m22 = m22;
	}

	public Matrix3f() {
		identity();
	}

	public static Matrix3f createScaleMatrix(float sx, float sy, float sz) {
		return new Matrix3f(
				sx, 0, 0,
				0, sy, 0,
				0, 0, sz
				);
	}
	
	public static Matrix3f createScaleMatrix(float sx, float sy) {
		return new Matrix3f(
				sx, 0, 0,
				0, sy, 0,
				0, 0, 1
				);
	}
	
	public static Matrix3f createTranslationMatrix(float x, float y) {
		return new Matrix3f(
				1, 0, x,
				0, 1, y,
				0, 0, 1
				);
	}

	@Override
	public float getField(int x, int y) {
		switch (x) {
		case 0: {
			switch (y) {
			case 0: return m00;
			case 1: return m01;
			case 2: return m02;
			}
		}
		case 1: {
			switch (y) {
			case 0: return m10;
			case 1: return m11;
			case 2: return m12;
			}
		}
		case 2: {
			switch (y) {
			case 0: return m20;
			case 1: return m21;
			case 2: return m22;
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
			}
		}
		case 1: {
			switch (y) {
			case 0: m10 = f; return;
			case 1: m11 = f; return;
			case 2: m12 = f; return;
			}
		}
		case 2: {
			switch (y) {
			case 0: m20 = f; return;
			case 1: m21 = f; return;
			case 2: m22 = f; return;
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
	public Matrix3f setI(Matrix3f mat) {
		this.m00 = mat.m00();
		this.m01 = mat.m01();
		this.m02 = mat.m02();
		this.m10 = mat.m10();
		this.m11 = mat.m11();
		this.m12 = mat.m12();
		this.m20 = mat.m20();
		this.m21 = mat.m21();
		this.m22 = mat.m22();
		return this;
	}

	@Override
	public Matrix3f copy() {
		return new Matrix3f(m00, m01, m02, m10, m11, m12, m20, m21, m22);
	}

	@Override
	public Matrix3f add(Matrix3f mat) {
		return new Matrix3f(m00 + mat.m00(), m01 + mat.m01(), m02 + mat.m02(), m10 + mat.m10(), m11 + mat.m11(), m12 + mat.m12(), m20 + mat.m20(), m21 + mat.m21(), m22 + mat.m22());
	}

	@Override
	public Matrix3f sub(Matrix3f mat) {
		return new Matrix3f(m00 - mat.m00(), m01 - mat.m01(), m02 - mat.m02(), m10 - mat.m10(), m11 - mat.m11(), m12 - mat.m12(), m20 - mat.m20(), m21 - mat.m21(), m22 - mat.m22());
	}

	@Override
	public Matrix3f mul(Matrix3f mat) {
		return new Matrix3f(
				this.m00 * mat.m00 + this.m01 * mat.m10 + this.m02 * mat.m20,
				this.m00 * mat.m01 + this.m01 * mat.m11 + this.m02 * mat.m21,
				this.m00 * mat.m02 + this.m01 * mat.m12 + this.m02 * mat.m22,
				this.m10 * mat.m00 + this.m11 * mat.m10 + this.m12 * mat.m20,
				this.m10 * mat.m01 + this.m11 * mat.m11 + this.m12 * mat.m21,
				this.m10 * mat.m02 + this.m11 * mat.m12 + this.m12 * mat.m22,
				this.m20 * mat.m00 + this.m21 * mat.m10 + this.m22 * mat.m20,
				this.m20 * mat.m01 + this.m21 * mat.m11 + this.m22 * mat.m21,
				this.m20 * mat.m02 + this.m21 * mat.m12 + this.m22 * mat.m22
				);
	}

	@Override
	public Matrix3f identity() {
		this.m00 = 1;
		this.m01 = 0;
		this.m02 = 0;
		this.m10 = 0;
		this.m11 = 1;
		this.m12 = 0;
		this.m20 = 0;
		this.m21 = 0;
		this.m22 = 1;
		return this;
	}

	@Override
	public Matrix3f scalar(float f) {
		return new Matrix3f(m00 * f, m01 * f, m02 * f, m10 * f, m11 * f, m12 * f, m20 * f, m21 * f, m22 * f);
	}

	@Override
	public Matrix3f transpose() {
		return new Matrix3f(
				m00, m10, m20, 
				m01, m11, m21, 
				m02, m12, m22
				);
	}

	@Override
	public float adjugateAndDet() {
		float f = this.m11 * this.m22 - this.m12 * this.m21;
		float f1 = -(this.m10 * this.m22 - this.m12 * this.m20);
		float f2 = this.m10 * this.m21 - this.m11 * this.m20;
		float f3 = -(this.m01 * this.m22 - this.m02 * this.m21);
		float f4 = this.m00 * this.m22 - this.m02 * this.m20;
		float f5 = -(this.m00 * this.m21 - this.m01 * this.m20);
		float f6 = this.m01 * this.m12 - this.m02 * this.m11;
		float f7 = -(this.m00 * this.m12 - this.m02 * this.m10);
		float f8 = this.m00 * this.m11 - this.m01 * this.m10;
		float f9 = this.m00 * f + this.m01 * f1 + this.m02 * f2;
		this.m00 = f;
		this.m10 = f1;
		this.m20 = f2;
		this.m01 = f3;
		this.m11 = f4;
		this.m21 = f5;
		this.m02 = f6;
		this.m12 = f7;
		this.m22 = f8;
		return f9;
	}

	@Override
	public float determinant() {
		float f = this.m11 * this.m22 - this.m12 * this.m21;
		float f1 = -(this.m10 * this.m22 - this.m12 * this.m20);
		float f2 = this.m10 * this.m21 - this.m11 * this.m20;
		return this.m00 * f + this.m01 * f1 + this.m02 * f2;
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
	public Matrix3f mul(IQuaternion<?> quat) {
		return mul(new Matrix3f(quat));
	}

	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj instanceof Matrix3f other) {
			return
					Float.compare(m00, other.m00()) == 0 &&
					Float.compare(m01, other.m01()) == 0 &&
					Float.compare(m02, other.m02()) == 0 &&
					Float.compare(m10, other.m10()) == 0 &&
					Float.compare(m11, other.m11()) == 0 &&
					Float.compare(m12, other.m12()) == 0 &&
					Float.compare(m20, other.m20()) == 0 &&
					Float.compare(m21, other.m21()) == 0 &&
					Float.compare(m22, other.m22()) == 0;
		}
		return false;
	}

	public int hashCode() {
		int i = this.m00 != 0.0F ? Float.floatToIntBits(this.m00) : 0;
		i = 31 * i + (this.m01 != 0.0F ? Float.floatToIntBits(this.m01) : 0);
		i = 31 * i + (this.m02 != 0.0F ? Float.floatToIntBits(this.m02) : 0);
		i = 31 * i + (this.m10 != 0.0F ? Float.floatToIntBits(this.m10) : 0);
		i = 31 * i + (this.m11 != 0.0F ? Float.floatToIntBits(this.m11) : 0);
		i = 31 * i + (this.m12 != 0.0F ? Float.floatToIntBits(this.m12) : 0);
		i = 31 * i + (this.m20 != 0.0F ? Float.floatToIntBits(this.m20) : 0);
		i = 31 * i + (this.m21 != 0.0F ? Float.floatToIntBits(this.m21) : 0);
		return 31 * i + (this.m22 != 0.0F ? Float.floatToIntBits(this.m22) : 0);
	}

	public String toString() {
		return "Matrix3f:\n"
				+ this.m00 + " " + this.m01 + " " + this.m02 + "\n"
				+ this.m10 + " " + this.m11 + " " + this.m12 + "\n"
				+ this.m20 + " " + this.m21 + " " + this.m22;
	}
	
	@Override
	public IVector3<Float> translate(IVector3<Float> vec) {
		float f = vec.x();
		float f1 = vec.y();
		float f2 = vec.z();
		return new Vec3f(
				m00 * f + m01 * f1 + m02 * f2,
				m10 * f + m11 * f1 + m12 * f2,
				m20 * f + m21 * f1 + m22 * f2
			);
	}

	@Override
	public float[] toFloatArr() {
		return new float[] {
				m00, m10, m20,
				m01, m11, m21,
				m02, m12, m22
		};
	}

	@Override
	public void loadFloatArr(float[] arr) {
		if (arr.length != 9) throw new IllegalArgumentException("Matrix float arr has to be of length 9!");
		this.m00 = arr[0];
		this.m10 = arr[1];
		this.m20 = arr[2];
		this.m01 = arr[3];
		this.m11 = arr[4];
		this.m21 = arr[5];
		this.m02 = arr[6];
		this.m12 = arr[7];
		this.m22 = arr[8];
	}

}
