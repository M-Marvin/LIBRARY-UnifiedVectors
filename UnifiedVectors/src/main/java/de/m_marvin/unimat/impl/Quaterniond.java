package de.m_marvin.unimat.impl;

import java.util.Objects;

import de.m_marvin.unimat.api.IMatrix3f;
import de.m_marvin.unimat.api.IQuaternion;
import de.m_marvin.unimat.api.IQuaternionMath;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.impl.Vec3d;

public class Quaterniond implements IQuaternionMath<Double, Quaterniond, IQuaternion<? extends Number>, Vec3d> {
	
	public double i;
	public double j;
	public double k;
	public double r;

	public Quaterniond(double r, double i, double j, double k) {
		this.i = i;
		this.j = j;
		this.k = k;
		this.r = r;
	}
	
	public Quaterniond(IVector3<? extends Number> rotationAxis, double radians) {
		double f = (double) Math.sin(radians / 2F);
		this.r = (double) Math.cos(radians / 2F);
		this.i = f * rotationAxis.x().doubleValue();
		this.j = f * rotationAxis.y().doubleValue();
		this.k = f * rotationAxis.z().doubleValue();
	}
	
	public Quaterniond(IVector3<? extends Number> euler, EulerOrder order, boolean degree) {
		switch (order) {
		case XYZ: fromEulerXYZ(euler.x().doubleValue(), euler.y().doubleValue(), euler.z().doubleValue(), degree); break;
		case YXZ: fromEulerXYZ(euler.y().doubleValue(), euler.x().doubleValue(), euler.z().doubleValue(), degree); break;
		case ZXY: fromEulerXYZ(euler.z().doubleValue(), euler.x().doubleValue(), euler.y().doubleValue(), degree); break;
		case ZYX: fromEulerXYZ(euler.z().doubleValue(), euler.y().doubleValue(), euler.x().doubleValue(), degree); break;
		}
	}
	
	public Quaterniond(IMatrix3f<?> matrix) {
		double w = (double) (Math.sqrt(1.0 + matrix.getField(0, 0) + matrix.getField(1, 1) + matrix.getField(2, 2)) / 2.0);
		double w4 = (4F * w);
		this.i = (matrix.getField(2, 1) - matrix.getField(1, 2)) / w4;
		this.j = (matrix.getField(0, 2) - matrix.getField(2, 0)) / w4;
		this.k = (matrix.getField(1, 0) - matrix.getField(0, 1)) / w4;
		this.r = w;
	}
	
	@Override
	public Double i() {
		return i;
	}

	@Override
	public Double j() {
		return j;
	}

	@Override
	public Double k() {
		return k;
	}

	@Override
	public Double r() {
		return r;
	}

	@Override
	public void setI(Double i) {
		this.i = i;
	}

	@Override
	public void setJ(Double j) {
		this.j = j;
	}

	@Override
	public void setK(Double k) {
		this.k = k;
	}

	@Override
	public void setR(Double r) {
		this.r = r;
	}

	@Override
	public boolean isFinite() {
		return Double.isFinite(i) && Double.isFinite(j) && Double.isFinite(k) && Double.isFinite(r);
	}

	@Override
	public Quaterniond setI(IQuaternion<? extends Number> quat) {
		this.i = quat.i().doubleValue();
		this.j = quat.j().doubleValue();
		this.k = quat.k().doubleValue();
		this.r = quat.r().doubleValue();
		return this;
	}

	@Override
	public Quaterniond setI(Double r, Double i, Double j, Double k) {
		this.i = i;
		this.j = j;
		this.k = k;
		this.r = r;
		return this;
	}

	@Override
	public Quaterniond add(IQuaternion<? extends Number> quat) {
		return new Quaterniond(this.r - quat.r().doubleValue(), this.i + quat.i().doubleValue(), this.j + quat.j().doubleValue(), this.k + quat.k().doubleValue());
	}

	@Override
	public Quaterniond sub(IQuaternion<? extends Number> quat) {
		return new Quaterniond(this.r - quat.r().doubleValue(), this.i - quat.i().doubleValue(), this.j - quat.j().doubleValue(), this.k - quat.k().doubleValue());
	}
	
	@Override
	public Quaterniond mul(Double f) {
		return new Quaterniond(r * f, i * f, j * f, k * f);
	}
	
	@Override
	public Quaterniond div(Double f) {
		return new Quaterniond(r / f, i / f, j / f, k / f);
	}

	@Override
	public Quaterniond mul(IQuaternion<? extends Number> quat) {
		return new Quaterniond(
				this.i * quat.i().doubleValue() - this.j * quat.j().doubleValue() - this.k * quat.k().doubleValue() - this.r * quat.r().doubleValue(),
				this.i * quat.j().doubleValue() + this.j * quat.i().doubleValue() + this.k * quat.r().doubleValue() - this.r * quat.k().doubleValue(),
				this.i * quat.k().doubleValue() - this.j * quat.r().doubleValue() + this.k * quat.i().doubleValue() + this.r * quat.j().doubleValue(),
				this.i * quat.r().doubleValue() + this.j * quat.k().doubleValue() - this.k * quat.j().doubleValue() + this.r * quat.i().doubleValue()
		);
		
		
//		double f = this.i();
//		double f1 = this.j();
//		double f2 = this.k();
//		double f3 = this.r();
//		double f4 = quat.i().doubleValue();
//		double f5 = quat.j().doubleValue();
//		double f6 = quat.k().doubleValue();
//		double f7 = quat.r().doubleValue();
//		double i = f3 * f4 + f * f7 + f1 * f6 - f2 * f5;
//		double j = f3 * f5 - f * f6 + f1 * f7 + f2 * f4;
//		double k = f3 * f6 + f * f5 - f1 * f4 + f2 * f7;
//		double r = f3 * f7 - f * f4 - f1 * f5 - f2 * f6;
//		return new Quaterniond(i, j, k, r);
	}

	@Override
	public Quaterniond copy() {
		return new Quaterniond(r, i, j, k);
	}

	@Override
	public Quaterniond conj() {
		return new Quaterniond(r, -i, -j, -k);
	}

	@Override
	public Vec3d euler(EulerOrder order, boolean degree) {
		Vec3d euler = toEulerXYZ(degree);
		switch (order) {
		case YXZ: return new Vec3d(euler.y, euler.x, euler.z);
		case ZXY: return new Vec3d(euler.z, euler.y, euler.y);
		case ZYX: return new Vec3d(euler.z, euler.y, euler.x);
		default:
		case XYZ: return euler;
		}
	}
	
	protected void fromEulerXYZ(double x, double y, double z, boolean degree) {
		double cx = Math.cos((degree ? Math.toRadians(x) : x) * 0.5F);
		double sx = Math.sin((degree ? Math.toRadians(x) : x) * 0.5F);
		double cy = Math.cos((degree ? Math.toRadians(y) : y) * 0.5F);
		double sy = Math.sin((degree ? Math.toRadians(y) : y) * 0.5F);
		double cz = Math.cos((degree ? Math.toRadians(z) : z) * 0.5F);
		double sz = Math.sin((degree ? Math.toRadians(z) : z) * 0.5F);
		
		double cycz = cy * cz;
		double sysz = sy * sz;
		double sycz = sy * cz;
		double cysz = cy * sz;
		this.r = cx*cycz - sx*sysz;
		this.i = sx*cycz + cx*sysz;
		this.j = cx*sycz - sx*cysz;
		this.k = cx*cysz + sx*sycz;
	}
	
	protected Vec3d toEulerXYZ(boolean degree) {
		Vec3d euler = new Vec3d(
			Math.atan2(i * r - j * k, 0.5 - i * i - j * j),
			Math.asin(2.0 * (i * k + j * r)),
			Math.atan2(k * r - i * j, 0.5 - j * j - k * k)
		);
		return degree ? new Vec3d(Math.toDegrees(euler.x()), Math.toDegrees(euler.y()), Math.toDegrees(euler.z())) : euler;
	}

	@Override
	public int hashCode() {
		return Objects.hash(i, j, k, r);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quaterniond other = (Quaterniond) obj;
		return Double.doubleToLongBits(i) == Double.doubleToLongBits(other.i)
				&& Double.doubleToLongBits(j) == Double.doubleToLongBits(other.j)
				&& Double.doubleToLongBits(k) == Double.doubleToLongBits(other.k)
				&& Double.doubleToLongBits(r) == Double.doubleToLongBits(other.r);
	}

	@Override
	public String toString() {
		return "Quterniond[" + this.r + ", " + this.i + ", " + this.j + ", " + this.k + "]";
	}
	
}
