package de.m_marvin.unimat.impl;

import java.util.Objects;

import de.m_marvin.unimat.api.IMatrix3f;
import de.m_marvin.unimat.api.IQuaternion;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.impl.Vec3f;

public class Quaternion implements IQuaternion<Quaternion> {
	
	public float i;
	public float j;
	public float k;
	public float r;

	public Quaternion(float i, float j, float k, float r) {
		this.i = i;
		this.j = j;
		this.k = k;
		this.r = r;
	}
	
	public Quaternion(IVector3<? extends Number> rotationAxis, float radians) {
		float f = (float) Math.sin(radians / 2F);
		this.r = (float) Math.cos(radians / 2F);
		this.i = f * rotationAxis.x().floatValue();
		this.j = f * rotationAxis.y().floatValue();
		this.k = f * rotationAxis.z().floatValue();
	}
	
	public static Quaternion fromOrientationMatrix(IMatrix3f<?> matrix) {
		float w = (float) (Math.sqrt(1.0 + matrix.getField(0, 0) + matrix.getField(1, 1) + matrix.getField(2, 2)) / 2.0);
		float w4 = (4F * w);
		float x = (matrix.getField(2, 1) - matrix.getField(1, 2)) / w4;
		float y = (matrix.getField(0, 2) - matrix.getField(2, 0)) / w4;
		float z = (matrix.getField(1, 0) - matrix.getField(0, 1)) / w4;
		return new Quaternion(x, y, z, w);
	}
	
	public static Quaternion fromXYZDegrees(IVector3<?> eulerVec) {
		return fromXYZDegrees((float) eulerVec.x(), (float) eulerVec.y(), (float) eulerVec.z());
	}
	public static Quaternion fromXYZDegrees(float x, float y, float z) {
		return fromXYZRadians(
				(float)Math.toRadians(x), 
				(float)Math.toRadians(y), 
				(float)Math.toRadians(z)
			);
	}

	public static Quaternion fromXYZRadians(IVector3<?> eulerVec) {
		return fromXYZRadians((float) eulerVec.x(), (float) eulerVec.y(), (float) eulerVec.z());
	}
	public static Quaternion fromXYZRadians(float x, float y, float z) {
		float cx = (float) Math.cos(x * 0.5F);
		float sx = (float) Math.sin(x * 0.5F);
		float cy = (float) Math.cos(y * 0.5F);
		float sy = (float) Math.sin(y * 0.5F);
		float cz = (float) Math.cos(z * 0.5F);
		float sz = (float) Math.sin(z * 0.5F);
		
		return new Quaternion(
				sx * cy * cz + cx * sy * sz,
				cx * sy * cz + sx * cy * sz,
				cx * cy * sz + sx * sy * cz,
				cx * cy * cz + sx * sy * sz
				);
	}
	
	public IVector3<Float> toXYZDegrees() {
		IVector3<Float> vector3f = this.toXYZRadians();
		return new Vec3f(
				(float)Math.toDegrees((double)vector3f.x()), 
				(float)Math.toDegrees((double)vector3f.y()), 
				(float)Math.toDegrees((double)vector3f.z())
			);
	}

	public IVector3<Float> toXYZRadians() {
		float f = this.r() * this.r();
		float f1 = this.i() * this.i();
		float f2 = this.j() * this.j();
		float f3 = this.k() * this.k();
		float f4 = f + f1 + f2 + f3;
		float f5 = 2.0F * this.r() * this.i() - 2.0F * this.j() * this.k();
		float f6 = (float)Math.asin((double)(f5 / f4));
		return Math.abs(f5) > 0.999F * f4 ? new Vec3f(2.0F * (float)Math.atan2((double)this.i(), (double)this.r()), f6, 0.0F) : new Vec3f((float)Math.atan2((double)(2.0F * this.j() * this.k() + 2.0F * this.i() * this.r()), (double)(f - f1 - f2 + f3)), f6, (float)Math.atan2((double)(2.0F * this.i() * this.j() + 2.0F * this.r() * this.k()), (double)(f + f1 - f2 - f3)));
	}

	@Override
	public float i() {
		return i;
	}

	@Override
	public float j() {
		return j;
	}

	@Override
	public float k() {
		return k;
	}

	@Override
	public float r() {
		return r;
	}

	@Override
	public Quaternion setI(float i, float j, float k, float r) {
		this.i = i;
		this.j = j;
		this.k = k;
		this.r = r;
		return this;
	}

	@Override
	public Quaternion mul(Quaternion quat) {
			float f = this.i();
			float f1 = this.j();
			float f2 = this.k();
			float f3 = this.r();
			float f4 = quat.i();
			float f5 = quat.j();
			float f6 = quat.k();
			float f7 = quat.r();
			float i = f3 * f4 + f * f7 + f1 * f6 - f2 * f5;
			float j = f3 * f5 - f * f6 + f1 * f7 + f2 * f4;
			float k = f3 * f6 + f * f5 - f1 * f4 + f2 * f7;
			float r = f3 * f7 - f * f4 - f1 * f5 - f2 * f6;
			return new Quaternion(i, j, k, r);
	}

	@Override
	public Quaternion mul(float f) {
		return new Quaternion(i * f, j * f, k, r);
	}

	@Override
	public Quaternion copy() {
		return new Quaternion(i, j, k, r);
	}

	@Override
	public Quaternion conj() {
		return new Quaternion(-i, -j, -k, r);
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
		Quaternion other = (Quaternion) obj;
		return Float.floatToIntBits(i) == Float.floatToIntBits(other.i)
				&& Float.floatToIntBits(j) == Float.floatToIntBits(other.j)
				&& Float.floatToIntBits(k) == Float.floatToIntBits(other.k)
				&& Float.floatToIntBits(r) == Float.floatToIntBits(other.r);
	}

	@Override
	public String toString() {
		return "Quternion[" + this.i + ", " + this.j + ", " + this.k + ", " + this.r + "]";
	}
	
}
