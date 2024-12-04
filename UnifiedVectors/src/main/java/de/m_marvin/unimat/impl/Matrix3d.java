package de.m_marvin.unimat.impl;

import de.m_marvin.unimat.api.IMatrix3;
import de.m_marvin.unimat.api.IMatrixVecMath;
import de.m_marvin.unimat.api.IQuaternion;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.impl.Vec3d;

public class Matrix3d implements IMatrix3<Double>, IMatrixVecMath<Double, Matrix3d, IVector3<Double>, Vec3d> {
	
	protected double m00;
	protected double m01;
	protected double m02;
	protected double m10;
	protected double m11;
	protected double m12;
	protected double m20;
	protected double m21;
	protected double m22;

	public Matrix3d(IQuaternion<?> quat) {
		double f = quat.i().doubleValue();
		double f1 = quat.j().doubleValue();
		double f2 = quat.k().doubleValue();
		double f3 = quat.r().doubleValue();
		double f4 = 2.0F * f * f;
		double f5 = 2.0F * f1 * f1;
		double f6 = 2.0F * f2 * f2;
		this.m00 = 1.0F - f5 - f6;
		this.m11 = 1.0F - f6 - f4;
		this.m22 = 1.0F - f4 - f5;
		double f7 = f * f1;
		double f8 = f1 * f2;
		double f9 = f2 * f;
		double f10 = f * f3;
		double f11 = f1 * f3;
		double f12 = f2 * f3;
		this.m10 = 2.0F * (f7 + f12);
		this.m01 = 2.0F * (f7 - f12);
		this.m20 = 2.0F * (f9 - f11);
		this.m02 = 2.0F * (f9 + f11);
		this.m21 = 2.0F * (f8 + f10);
		this.m12 = 2.0F * (f8 - f10);
	}

	public Matrix3d(
			double m00, double m01, double m02, 
			double m10, double m11, double m12, 
			double m20, double m21, double m22
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

	public Matrix3d() {
		identity();
	}

	public static Matrix3d createScaleMatrix(double sx, double sy, double sz) {
		return new Matrix3d(
				sx, 0, 0,
				0, sy, 0,
				0, 0, sz
				);
	}
	
	public static Matrix3d createScaleMatrix(double sx, double sy) {
		return new Matrix3d(
				sx, 0, 0,
				0, sy, 0,
				0, 0, 1
				);
	}
	
	public static Matrix3d createTranslationMatrix(double x, double y) {
		return new Matrix3d(
				1, 0, x,
				0, 1, y,
				0, 0, 1
				);
	}

	@Override
	public Double getField(int x, int y) {
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
	public void setField(int x, int y, Double f) {
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
	public Double m00() {
		return this.m00;
	}

	@Override
	public Double m01() {
		return this.m01;
	}

	@Override
	public Double m02() {
		return this.m02;
	}

	@Override
	public Double m10() {
		return this.m10;
	}

	@Override
	public Double m11() {
		return this.m11;
	}

	@Override
	public Double m12() {
		return this.m12;
	}

	@Override
	public Double m20() {
		return this.m20;
	}

	@Override
	public Double m21() {
		return this.m21;
	}

	@Override
	public Double m22() {
		return this.m22;
	}

	@Override
	public Matrix3d setI(Matrix3d mat) {
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
	public Matrix3d copy() {
		return new Matrix3d(m00, m01, m02, m10, m11, m12, m20, m21, m22);
	}

	@Override
	public Matrix3d add(Matrix3d mat) {
		return new Matrix3d(m00 + mat.m00(), m01 + mat.m01(), m02 + mat.m02(), m10 + mat.m10(), m11 + mat.m11(), m12 + mat.m12(), m20 + mat.m20(), m21 + mat.m21(), m22 + mat.m22());
	}

	@Override
	public Matrix3d sub(Matrix3d mat) {
		return new Matrix3d(m00 - mat.m00(), m01 - mat.m01(), m02 - mat.m02(), m10 - mat.m10(), m11 - mat.m11(), m12 - mat.m12(), m20 - mat.m20(), m21 - mat.m21(), m22 - mat.m22());
	}

	@Override
	public Matrix3d mul(Matrix3d mat) {
		return new Matrix3d(
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
	public Matrix3d identity() {
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
	public Matrix3d scalar(Double f) {
		return new Matrix3d(m00 * f, m01 * f, m02 * f, m10 * f, m11 * f, m12 * f, m20 * f, m21 * f, m22 * f);
	}

	@Override
	public Matrix3d transpose() {
		return new Matrix3d(
				m00, m10, m20, 
				m01, m11, m21, 
				m02, m12, m22
				);
	}

	@Override
	public Double adjugateAndDet() {
		double f = this.m11 * this.m22 - this.m12 * this.m21;
		double f1 = -(this.m10 * this.m22 - this.m12 * this.m20);
		double f2 = this.m10 * this.m21 - this.m11 * this.m20;
		double f3 = -(this.m01 * this.m22 - this.m02 * this.m21);
		double f4 = this.m00 * this.m22 - this.m02 * this.m20;
		double f5 = -(this.m00 * this.m21 - this.m01 * this.m20);
		double f6 = this.m01 * this.m12 - this.m02 * this.m11;
		double f7 = -(this.m00 * this.m12 - this.m02 * this.m10);
		double f8 = this.m00 * this.m11 - this.m01 * this.m10;
		double f9 = this.m00 * f + this.m01 * f1 + this.m02 * f2;
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
	public Double determinant() {
		double f = this.m11 * this.m22 - this.m12 * this.m21;
		double f1 = -(this.m10 * this.m22 - this.m12 * this.m20);
		double f2 = this.m10 * this.m21 - this.m11 * this.m20;
		return this.m00 * f + this.m01 * f1 + this.m02 * f2;
	}

	@Override
	public boolean invertI() {
		double f = this.adjugateAndDet();
		if (Math.abs(f) > 1.0E-6F) {
			this.scalarI(f);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Matrix3d tryInvertI() {
		this.invertI();
		return this;
	}
	
	@Override
	public Matrix3d mul(IQuaternion<Double> quat) {
		return mul(new Matrix3d(quat));
	}

	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj instanceof Matrix3d other) {
			return
					Double.compare(m00, other.m00()) == 0 &&
					Double.compare(m01, other.m01()) == 0 &&
					Double.compare(m02, other.m02()) == 0 &&
					Double.compare(m10, other.m10()) == 0 &&
					Double.compare(m11, other.m11()) == 0 &&
					Double.compare(m12, other.m12()) == 0 &&
					Double.compare(m20, other.m20()) == 0 &&
					Double.compare(m21, other.m21()) == 0 &&
					Double.compare(m22, other.m22()) == 0;
		}
		return false;
	}

	public int hashCode() {
		int i = this.m00 != 0.0F ? Double.hashCode(this.m00) : 0;
		i = 31 * i + (this.m01 != 0.0F ? Double.hashCode(this.m01) : 0);
		i = 31 * i + (this.m02 != 0.0F ? Double.hashCode(this.m02) : 0);
		i = 31 * i + (this.m10 != 0.0F ? Double.hashCode(this.m10) : 0);
		i = 31 * i + (this.m11 != 0.0F ? Double.hashCode(this.m11) : 0);
		i = 31 * i + (this.m12 != 0.0F ? Double.hashCode(this.m12) : 0);
		i = 31 * i + (this.m20 != 0.0F ? Double.hashCode(this.m20) : 0);
		i = 31 * i + (this.m21 != 0.0F ? Double.hashCode(this.m21) : 0);
		return 31 * i + (this.m22 != 0.0F ? Double.hashCode(this.m22) : 0);
	}

	public String toString() {
		return "Matrix3f:\n"
				+ this.m00 + " " + this.m01 + " " + this.m02 + "\n"
				+ this.m10 + " " + this.m11 + " " + this.m12 + "\n"
				+ this.m20 + " " + this.m21 + " " + this.m22;
	}
	
	@Override
	public Vec3d translate(IVector3<Double> vec) {
		double f = vec.x();
		double f1 = vec.y();
		double f2 = vec.z();
		return new Vec3d(
				m00 * f + m01 * f1 + m02 * f2,
				m10 * f + m11 * f1 + m12 * f2,
				m20 * f + m21 * f1 + m22 * f2
			);
	}

	@Override
	public Double[] toArr() {
		return new Double[] {
				m00, m10, m20,
				m01, m11, m21,
				m02, m12, m22
		};
	}

	@Override
	public void loadArr(Double[] arr) {
		if (arr.length != 9) throw new IllegalArgumentException("Matrix double arr has to be of length 9!");
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

	public double[] toDoubleArr() {
		return new double[] {
				m00, m10, m20,
				m01, m11, m21,
				m02, m12, m22
		};
	}

	public void loadDoubleArr(double[] arr) {
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
