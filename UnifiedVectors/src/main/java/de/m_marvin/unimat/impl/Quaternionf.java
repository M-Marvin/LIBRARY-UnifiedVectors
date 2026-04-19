package de.m_marvin.unimat.impl;

import java.util.Objects;

import de.m_marvin.unimat.api.IQuaternion;
import de.m_marvin.unimat.api.IQuaternionMath;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector4;
import de.m_marvin.univec.impl.Vec3f;

public class Quaternionf implements IQuaternionMath<Float, Quaternionf, IQuaternion<? extends Number>, Vec3f> {
	
	public float i;
	public float j;
	public float k;
	public float r;
	
	public Quaternionf(IVector2<? extends Number> vec2, float z, float w) {
		this.i = vec2.x().floatValue();
		this.j = vec2.y().floatValue();
		this.k = z;
		this.r = w;
	}

	public Quaternionf(IVector3<? extends Number> vec3, float w) {
		this.i = vec3.x().floatValue();
		this.j = vec3.y().floatValue();
		this.k = vec3.z().floatValue();
		this.r = w;
	}
	
	public Quaternionf(float x, float y, float z, float w) {
		this.i = x;
		this.j = y;
		this.k = z;
		this.r = w;
	}

	public Quaternionf() {
		this.i = 0;
		this.j = 0;
		this.k = 0;
		this.r = 0;
	}

	public Quaternionf(IVector4<? extends Number> vec) {
		this.i = vec.x().floatValue();
		this.j = vec.y().floatValue();
		this.k = vec.z().floatValue();
		this.r = vec.w().floatValue();
	}

	@Override
	public Float i() {
		return this.i;
	}

	@Override
	public Float j() {
		return this.j;
	}

	@Override
	public Float k() {
		return this.k;
	}

	@Override
	public Float r() {
		return this.r;
	}

	@Override
	public void setI(Float i) {
		this.i = i;
	}

	@Override
	public void setJ(Float j) {
		this.j  = j;
	}

	@Override
	public void setK(Float k) {
		this.k = k;
	}

	@Override
	public void setR(Float r) {
		this.r = r;
	}

	@Override
	public Quaternionf mulI(IQuaternion<? extends Number> quat) {
		float i = this.r() * quat.i().floatValue() + this.i() * quat.r().floatValue() + this.j() * quat.k().floatValue() - this.k() * quat.j().floatValue();
		float j = this.r() * quat.j().floatValue() - this.i() * quat.k().floatValue() + this.j() * quat.r().floatValue() + this.k() * quat.i().floatValue();
		float k = this.r() * quat.k().floatValue() + this.i() * quat.j().floatValue() - this.j() * quat.i().floatValue() + this.k() * quat.r().floatValue();
		this.r = this.r() * quat.r().floatValue() - this.i() * quat.i().floatValue() - this.j() * quat.j().floatValue() - this.k() * quat.k().floatValue();
		this.i = i;
		this.j = j;
		this.k = k;
		return this;
	}
	
	@Override
	public Quaternionf mulI(Float f) {
		this.i *= f;
		this.j *= f;
		this.k *= f;
		this.r *= f;
		return this;
	}
	
	@Override
	public Quaternionf divI(Float f) {
		this.i /= f;
		this.j /= f;
		this.k /= f;
		this.r /= f;
		return this;
	}
	
	@Override
	public Quaternionf addI(IQuaternion<? extends Number> quat) {
		this.i += quat.i().floatValue();
		this.j += quat.j().floatValue();
		this.k += quat.k().floatValue();
		this.r += quat.r().floatValue();
		return this;
	}
	
	@Override
	public Quaternionf subI(IQuaternion<? extends Number> quat) {
		this.i -= quat.i().floatValue();
		this.j -= quat.j().floatValue();
		this.k -= quat.k().floatValue();
		this.r -= quat.r().floatValue();
		return this;
	}

	@Override
	public boolean isFinite() {
		return Float.isFinite(this.i) && Float.isFinite(this.j) && Float.isFinite(this.k) && Float.isFinite(this.r);
	}

	@Override
	public Quaternionf setI(Float i, Float j, Float k, Float r) {
		this.i = i;
		this.j = j;
		this.k = k;
		this.r = r;
		return this;
	}

	@Override
	public Quaternionf setI(IQuaternion<? extends Number> quat) {
		this.i = quat.i().floatValue();
		this.j = quat.j().floatValue();
		this.k = quat.k().floatValue();
		this.r = quat.r().floatValue();
		return this;
	}

	@Override
	public Float length() {
		return (float) Math.sqrt(this.lengthSqr());
	}

	@Override
	public Float lengthSqr() {
		return this.i * this.i + this.j * this.j + this.k * this.k + this.r * this.r;
	}

	@Override
	public Quaternionf normalizeI() {
		float f = this.length();
		if (f == 0) throw new ArithmeticException("division trough zero, cant normalize vector of length 0");
		return this.divI(f);
	}

	@Override
	public Quaternionf tryNormalizeI() {
		float f = this.length();
		if (f == 0) return this.setI(0F, 0F, 0F, 0F);
		return this.divI(f);
	}
	
	@Override
	public Quaternionf copy() {
		return new Quaternionf(this.i, this.j, this.k, this.r);
	}

	@Override
	public Quaternionf conjI() {
		this.i = -this.i;
		this.j = -this.j;
		this.k = -this.k;
		return this;
	}

	@Override
	public Quaternionf setVectorI(IVector3<?> axis, boolean degree) {
		float angle = axis.length().floatValue();
		if (degree) angle = (float) Math.toRadians(angle);
		float f = (float) Math.sin(angle / 2);
		this.r = (float) Math.cos(angle / 2);
		this.i = f * axis.x().floatValue() / angle;
		this.j = f * axis.y().floatValue() / angle;
		this.k = f * axis.z().floatValue() / angle;
		return this;
	}

	@Override
	public Quaternionf setVectorAngleI(IVector3<?> axis, Float angle, boolean degree) {
		if (degree) angle = (float) Math.toRadians(angle);
		float f = (float) Math.sin(angle / 2);
		this.r = (float) Math.cos(angle / 2);
		this.i = f * axis.x().floatValue();
		this.j = f * axis.y().floatValue();
		this.k = f * axis.z().floatValue();
		return this;
	}

	@Override
	public Vec3f vector(boolean degree) {
		float angle = (float) Math.acos(this.r) * 2;
		if (degree) angle = (float) Math.toDegrees(angle);
		return new Vec3f(this.i, this.j, this.k).tryNormalizeI().mulI(angle);
	}

	protected void fromEulerXYZ(float x, float y, float z, boolean degree) {
		float cx = (float) Math.cos((degree ? Math.toRadians(x) : x) * 0.5F);
		float sx = (float) Math.sin((degree ? Math.toRadians(x) : x) * 0.5F);
		float cy = (float) Math.cos((degree ? Math.toRadians(y) : y) * 0.5F);
		float sy = (float) Math.sin((degree ? Math.toRadians(y) : y) * 0.5F);
		float cz = (float) Math.cos((degree ? Math.toRadians(z) : z) * 0.5F);
		float sz = (float) Math.sin((degree ? Math.toRadians(z) : z) * 0.5F);
		
		float cycz = cy * cz;
		float sysz = sy * sz;
		float sycz = sy * cz;
		float cysz = cy * sz;
		this.r = cx*cycz - sx*sysz;
		this.i = sx*cycz + cx*sysz;
		this.j = cx*sycz - sx*cysz;
		this.k = cx*cysz + sx*sycz;
	}
		
	protected Vec3f toEulerXYZ(boolean degree) {
		Vec3f euler = new Vec3f(
			(float) Math.atan2(this.i * this.r - this.j * this.k, 0.5 - this.i * this.i - this.j * this.j),
			(float) Math.asin(2.0 * (this.i * this.k + this.j * this.r)),
			(float) Math.atan2(this.k * this.r - this.i * this.j, 0.5 - this.j * this.j - this.k * this.k)
		);
		return degree ? new Vec3f((float) Math.toDegrees(euler.x()), (float) Math.toDegrees(euler.y()), (float) Math.toDegrees(euler.z())) : euler;
	}
	
	@Override
	public Quaternionf setEulerI(IVector3<?> euler, EulerOrder order, boolean degree) {
		switch (order) {
		case XYZ: fromEulerXYZ(euler.x().floatValue(), euler.y().floatValue(), euler.z().floatValue(), degree); break;
		case YXZ: fromEulerXYZ(euler.y().floatValue(), euler.x().floatValue(), euler.z().floatValue(), degree); break;
		case ZXY: fromEulerXYZ(euler.z().floatValue(), euler.x().floatValue(), euler.y().floatValue(), degree); break;
		case ZYX: fromEulerXYZ(euler.z().floatValue(), euler.y().floatValue(), euler.x().floatValue(), degree); break;
		}
		return this;
	}

	@Override
	public Vec3f euler(EulerOrder order, boolean degree) {
		Vec3f euler = toEulerXYZ(degree);
		switch (order) {
		case YXZ: return new Vec3f(euler.y, euler.x, euler.z);
		case ZXY: return new Vec3f(euler.z, euler.y, euler.y);
		case ZYX: return new Vec3f(euler.z, euler.y, euler.x);
		default:
		case XYZ: return euler;
		}
	}

	@Override
	public Vec3f transform(Vec3f vector) {
		Quaternionf qvec = this.mul(new Quaternionf(vector, 0.0F)).mulI(conj());
		return new Vec3f(qvec.i(), qvec.j(), qvec.k());
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
