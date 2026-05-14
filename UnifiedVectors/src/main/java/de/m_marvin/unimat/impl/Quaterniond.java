package de.m_marvin.unimat.impl;

import java.util.Objects;

import de.m_marvin.unimat.api.IQuaternion;
import de.m_marvin.unimat.api.IQuaternionMath;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector4;
import de.m_marvin.univec.impl.Vec3d;

public class Quaterniond implements IQuaternionMath<Double, Quaterniond, IQuaternion<? extends Number>, Vec3d> {
	
	public double i;
	public double j;
	public double k;
	public double r;
	
	public Quaterniond(IVector2<? extends Number> vec2, double z, double w) {
		this.i = vec2.x().doubleValue();
		this.j = vec2.y().doubleValue();
		this.k = z;
		this.r = w;
	}

	public Quaterniond(IVector3<? extends Number> vec3, double w) {
		this.i = vec3.x().doubleValue();
		this.j = vec3.y().doubleValue();
		this.k = vec3.z().doubleValue();
		this.r = w;
	}
	
	public Quaterniond(double x, double y, double z, double w) {
		this.i = x;
		this.j = y;
		this.k = z;
		this.r = w;
	}

	public Quaterniond() {
		this.i = 0;
		this.j = 0;
		this.k = 0;
		this.r = 0;
	}

	public Quaterniond(IVector4<? extends Number> vec) {
		this.i = vec.x().doubleValue();
		this.j = vec.y().doubleValue();
		this.k = vec.z().doubleValue();
		this.r = vec.w().doubleValue();
	}

	@Override
	public Double i() {
		return this.i;
	}

	@Override
	public Double j() {
		return this.j;
	}

	@Override
	public Double k() {
		return this.k;
	}

	@Override
	public Double r() {
		return this.r;
	}

	@Override
	public void setI(Double i) {
		this.i = i;
	}

	@Override
	public void setJ(Double j) {
		this.j  = j;
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
	public Quaterniond mulI(IQuaternion<? extends Number> quat) {
		double i = this.r() * quat.i().doubleValue() + this.i() * quat.r().doubleValue() + this.j() * quat.k().doubleValue() - this.k() * quat.j().doubleValue();
		double j = this.r() * quat.j().doubleValue() - this.i() * quat.k().doubleValue() + this.j() * quat.r().doubleValue() + this.k() * quat.i().doubleValue();
		double k = this.r() * quat.k().doubleValue() + this.i() * quat.j().doubleValue() - this.j() * quat.i().doubleValue() + this.k() * quat.r().doubleValue();
		this.r = this.r() * quat.r().doubleValue() - this.i() * quat.i().doubleValue() - this.j() * quat.j().doubleValue() - this.k() * quat.k().doubleValue();
		this.i = i;
		this.j = j;
		this.k = k;
		return this;
	}
	
	@Override
	public Quaterniond mulI(Double f) {
		this.i *= f;
		this.j *= f;
		this.k *= f;
		this.r *= f;
		return this;
	}
	
	@Override
	public Quaterniond divI(Double f) {
		this.i /= f;
		this.j /= f;
		this.k /= f;
		this.r /= f;
		return this;
	}
	
	@Override
	public Quaterniond addI(IQuaternion<? extends Number> quat) {
		this.i += quat.i().doubleValue();
		this.j += quat.j().doubleValue();
		this.k += quat.k().doubleValue();
		this.r += quat.r().doubleValue();
		return this;
	}
	
	@Override
	public Quaterniond subI(IQuaternion<? extends Number> quat) {
		this.i -= quat.i().doubleValue();
		this.j -= quat.j().doubleValue();
		this.k -= quat.k().doubleValue();
		this.r -= quat.r().doubleValue();
		return this;
	}

	@Override
	public boolean isFinite() {
		return Double.isFinite(this.i) && Double.isFinite(this.j) && Double.isFinite(this.k) && Double.isFinite(this.r);
	}

	@Override
	public Quaterniond setI(Double i, Double j, Double k, Double r) {
		this.i = i;
		this.j = j;
		this.k = k;
		this.r = r;
		return this;
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
	public Double length() {
		return Math.sqrt(this.lengthSqr());
	}

	@Override
	public Double lengthSqr() {
		return this.i * this.i + this.j * this.j + this.k * this.k + this.r * this.r;
	}

	@Override
	public Quaterniond normalizeI() {
		double f = this.length();
		if (f == 0) throw new ArithmeticException("division trough zero, cant normalize vector of length 0");
		return this.divI(f);
	}

	@Override
	public Quaterniond tryNormalizeI() {
		double f = this.length();
		if (f == 0) return this.setI(0D, 0D, 0D, 0D);
		return this.divI(f);
	}
	
	@Override
	public Quaterniond copy() {
		return new Quaterniond(this.i, this.j, this.k, this.r);
	}

	@Override
	public Quaterniond conjI() {
		this.i = -this.i;
		this.j = -this.j;
		this.k = -this.k;
		return this;
	}

	@Override
	public Quaterniond setVectorI(IVector3<?> axis, boolean degree) {
		double angle = axis.length().doubleValue();
		if (degree) angle = Math.toRadians(angle);
		double f = Math.sin(angle / 2);
		this.r = Math.cos(angle / 2);
		if (angle != 0.0) {
			this.i = f * axis.x().doubleValue() / angle;
			this.j = f * axis.y().doubleValue() / angle;
			this.k = f * axis.z().doubleValue() / angle;
		} else {
			this.i = this.j = this.k = 0.0;
		}
		return this;
	}

	@Override
	public Quaterniond setVectorAngleI(IVector3<?> axis, Double angle, boolean degree) {
		if (degree) angle = Math.toRadians(angle);
		double f = Math.sin(angle / 2);
		this.r = Math.cos(angle / 2);
		this.i = f * axis.x().doubleValue();
		this.j = f * axis.y().doubleValue();
		this.k = f * axis.z().doubleValue();
		return this;
	}

	@Override
	public Vec3d vector(boolean degree) {
		double angle = Math.acos(this.r) * 2;
		if (degree) angle = Math.toDegrees(angle);
		return new Vec3d(this.i, this.j, this.k).tryNormalizeI().mulI(angle);
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
			Math.atan2(this.i * this.r - this.j * this.k, 0.5 - this.i * this.i - this.j * this.j),
			Math.asin(2.0 * (this.i * this.k + this.j * this.r)),
			Math.atan2(this.k * this.r - this.i * this.j, 0.5 - this.j * this.j - this.k * this.k)
		);
		return degree ? new Vec3d(Math.toDegrees(euler.x()), Math.toDegrees(euler.y()), Math.toDegrees(euler.z())) : euler;
	}
	
	@Override
	public Quaterniond setEulerI(IVector3<?> euler, EulerOrder order, boolean degree) {
		switch (order) {
		case XYZ: fromEulerXYZ(euler.x().doubleValue(), euler.y().doubleValue(), euler.z().doubleValue(), degree); break;
		case YXZ: fromEulerXYZ(euler.y().doubleValue(), euler.x().doubleValue(), euler.z().doubleValue(), degree); break;
		case ZXY: fromEulerXYZ(euler.z().doubleValue(), euler.x().doubleValue(), euler.y().doubleValue(), degree); break;
		case ZYX: fromEulerXYZ(euler.z().doubleValue(), euler.y().doubleValue(), euler.x().doubleValue(), degree); break;
		}
		return this;
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
	
	@Override
	public Vec3d transform(IVector3<? extends Number> vector) {
		Quaterniond qvec = this.mul(new Quaterniond(vector, 0.0)).mulI(conj());
		return new Vec3d(qvec.i(), qvec.j(), qvec.k());
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
		return 	Double.doubleToLongBits(i) == Double.doubleToLongBits(other.i) &&
				Double.doubleToLongBits(j) == Double.doubleToLongBits(other.j) &&
				Double.doubleToLongBits(k) == Double.doubleToLongBits(other.k) &&
				Double.doubleToLongBits(r) == Double.doubleToLongBits(other.r);
	}

	@Override
	public String toString() {
		return String.format("[%f  %f  %f  %f]", this.i, this.j, this.k, this.r);
	}
	
}
