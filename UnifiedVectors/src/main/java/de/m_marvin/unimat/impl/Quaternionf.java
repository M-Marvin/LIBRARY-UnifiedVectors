package de.m_marvin.unimat.impl;

import java.util.Objects;

import de.m_marvin.unimat.api.IMatrix3f;
import de.m_marvin.unimat.api.IQuaternion;
import de.m_marvin.unimat.api.IQuaternionMath;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.impl.Vec3f;

public class Quaternionf implements IQuaternionMath<Float, Quaternionf, IQuaternion<? extends Number>, Vec3f> {
	
	public float i;
	public float j;
	public float k;
	public float r;

	public Quaternionf(float i, float j, float k, float r) {
		this.i = i;
		this.j = j;
		this.k = k;
		this.r = r;
	}
	
	public Quaternionf(IVector3<? extends Number> rotationAxis, float radians) {
		float f = (float) Math.sin(radians / 2F);
		this.r = (float) Math.cos(radians / 2F);
		this.i = f * rotationAxis.x().floatValue();
		this.j = f * rotationAxis.y().floatValue();
		this.k = f * rotationAxis.z().floatValue();
	}
	
	public Quaternionf(IVector3<? extends Number> euler, EulerOrder order, boolean degree) {
		switch (order) {
		case XYZ: fromEulerXYZ(euler.x().floatValue(), euler.y().floatValue(), euler.z().floatValue(), degree); break;
		case YXZ: fromEulerXYZ(euler.y().floatValue(), euler.x().floatValue(), euler.z().floatValue(), degree); break;
		case ZXY: fromEulerXYZ(euler.z().floatValue(), euler.x().floatValue(), euler.y().floatValue(), degree); break;
		case ZYX: fromEulerXYZ(euler.z().floatValue(), euler.y().floatValue(), euler.x().floatValue(), degree); break;
		}
	}
	
	public Quaternionf(IMatrix3f<?> matrix) {
		float w = (float) (Math.sqrt(1.0 + matrix.getField(0, 0) + matrix.getField(1, 1) + matrix.getField(2, 2)) / 2.0);
		float w4 = (4F * w);
		this.i = (matrix.getField(2, 1) - matrix.getField(1, 2)) / w4;
		this.j = (matrix.getField(0, 2) - matrix.getField(2, 0)) / w4;
		this.k = (matrix.getField(1, 0) - matrix.getField(0, 1)) / w4;
		this.r = w;
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
			(float) Math.atan2(i * r - j * k, 0.5 - i * i - j * j),
			(float) Math.asin(2.0 * (i * k + j * r)),
			(float) Math.atan2(k * r - i * j, 0.5 - j * j - k * k)
		);
		return degree ? new Vec3f((float) Math.toDegrees(euler.x()), (float) Math.toDegrees(euler.y()), (float) Math.toDegrees(euler.z())) : euler;
	}

	@Override
	public Float i() {
		return i;
	}

	@Override
	public Float j() {
		return j;
	}

	@Override
	public Float k() {
		return k;
	}

	@Override
	public Float r() {
		return r;
	}

	@Override
	public void setI(Float i) {
		this.i = i;
	}

	@Override
	public void setJ(Float j) {
		this.j = j;
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
	public boolean isFinite() {
		return Float.isFinite(i) && Float.isFinite(j) && Float.isFinite(k) && Float.isFinite(r);
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
	public Quaternionf setI(Float i, Float j, Float k, Float r) {
		this.i = i;
		this.j = j;
		this.k = k;
		this.r = r;
		return this;
	}

	@Override
	public Quaternionf mul(IQuaternion<? extends Number> quat) {
		float f = this.i();
		float f1 = this.j();
		float f2 = this.k();
		float f3 = this.r();
		float f4 = quat.i().floatValue();
		float f5 = quat.j().floatValue();
		float f6 = quat.k().floatValue();
		float f7 = quat.r().floatValue();
		float i = f3 * f4 + f * f7 + f1 * f6 - f2 * f5;
		float j = f3 * f5 - f * f6 + f1 * f7 + f2 * f4;
		float k = f3 * f6 + f * f5 - f1 * f4 + f2 * f7;
		float r = f3 * f7 - f * f4 - f1 * f5 - f2 * f6;
		return new Quaternionf(i, j, k, r);
	}

	@Override
	public Quaternionf add(IQuaternion<? extends Number> quat) {
		return new Quaternionf(this.i + quat.i().floatValue(), this.j + quat.j().floatValue(), this.k + quat.k().floatValue(), this.r + quat.r().floatValue());
	}

	@Override
	public Quaternionf sub(IQuaternion<? extends Number> quat) {
		return new Quaternionf(this.i - quat.i().floatValue(), this.j - quat.j().floatValue(), this.k - quat.k().floatValue(), this.r - quat.r().floatValue());
	}
	
	@Override
	public Quaternionf mul(Float f) {
		return new Quaternionf(i * f, j * f, k, r);
	}
	
	@Override
	public Quaternionf div(Float f) {
		return new Quaternionf(i / f, j / f, k, r);
	}

	@Override
	public Quaternionf copy() {
		return new Quaternionf(i, j, k, r);
	}

	@Override
	public Quaternionf conj() {
		return new Quaternionf(-i, -j, -k, r);
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
		Quaternionf other = (Quaternionf) obj;
		return Float.floatToIntBits(i) == Float.floatToIntBits(other.i)
				&& Float.floatToIntBits(j) == Float.floatToIntBits(other.j)
				&& Float.floatToIntBits(k) == Float.floatToIntBits(other.k)
				&& Float.floatToIntBits(r) == Float.floatToIntBits(other.r);
	}

	@Override
	public String toString() {
		return "Quterniond[" + this.i + ", " + this.j + ", " + this.k + ", " + this.r + "]";
	}
	
}
