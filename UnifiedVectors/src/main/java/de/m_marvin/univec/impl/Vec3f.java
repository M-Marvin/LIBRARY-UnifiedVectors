package de.m_marvin.univec.impl;

import de.m_marvin.unimat.impl.Quaternionf;
import de.m_marvin.univec.VectorParser;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector3Math;

/*
 * Implementation of a 3 dimensional float vector
 */
public class Vec3f implements IVector3Math<Float, Vec3f, IVector3<? extends Number>, Quaternionf> {

	public float x;
	public float y;
	public float z;

	public Vec3f(IVector2<? extends Number> vec2, float z) {
		this.x = vec2.x().floatValue();
		this.y = vec2.y().floatValue();
		this.z = z;
	}
	
	public Vec3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec3f() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public Vec3f(IVector3<? extends Number> vec) {
		this.x = vec.x().floatValue();
		this.y = vec.y().floatValue();
		this.z = vec.z().floatValue();
	}
	
	public static Vec3f fromVec(Object vectorObject) {
		return new Vec3f(0, 0, 0).readFrom(vectorObject);
	}
	
	@Override
	public <T> Vec3f readFrom(T vectorObject) {
		try {
			VectorParser.parseFromVectorObject(vectorObject, this);
		} catch (IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	@Override
	public <T> T writeTo(T vectorObject) {
		try {
			return VectorParser.parseToVectorObject(vectorObject, this);
		} catch (IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
			return vectorObject;
		}
	}
	
	@Override
	public Float x() {
		return x;
	}

	@Override
	public Float y() {
		return y;
	}
	
	@Override
	public Float z() {
		return z;
	}

	@Override
	public void setX(Float x) {
		this.x = x;
	}

	@Override
	public void setY(Float y) {
		this.y = y;
	}
	
	@Override
	public void setZ(Float z) {
		this.z = z;
	}

	@Override
	public Vec3f setI(Float x, Float y, Float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	@Override
	public Vec3f setI(IVector3<? extends Number> vec) {
		this.x = vec.x().floatValue();
		this.y = vec.y().floatValue();
		this.z = vec.z().floatValue();
		return this;
	}

	@Override
	public Vec3f copy() {
		return new Vec3f(this.x, this.y, this.z);
	}

	@Override
	public Vec3f add(IVector3<? extends Number> vec) {
		return new Vec3f(this.x + vec.x().floatValue(), this.y + vec.y().floatValue(), this.z + vec.z().floatValue());
	}

	@Override
	public Vec3f add(Float x, Float y, Float z) {
		return new Vec3f(this.x + x, this.y + y, this.z + z);
	}

	@Override
	public Vec3f sub(IVector3<? extends Number> vec) {
		return new Vec3f(this.x - vec.x().floatValue(), this.y - vec.y().floatValue(), this.z - vec.z().floatValue());
	}

	@Override
	public Vec3f sub(Float x, Float y, Float z) {
		return new Vec3f(this.x - x, this.y - y, this.z - z);
	}

	@Override
	public Vec3f mul(IVector3<? extends Number> vec) {
		return new Vec3f(this.x * vec.x().floatValue(), this.y * vec.y().floatValue(), this.z * vec.z().floatValue());
	}

	@Override
	public Vec3f mul(Float x, Float y, Float z) {
		return new Vec3f(this.x * x, this.y * y, this.z * z);
	}

	@Override
	public Vec3f mul(Float n) {
		return new Vec3f(this.x * n, this.y * n, this.z * n);
	}
	
	@Override
	public Vec3f div(IVector3<? extends Number> vec) {
		return new Vec3f(this.x / vec.x().floatValue(), this.y / vec.y().floatValue(), this.z / vec.z().floatValue());
	}

	@Override
	public Vec3f div(Float x, Float y, Float z) {
		return new Vec3f(this.x / x, this.y / y, this.z / z);
	}
	
	@Override
	public Vec3f div(Float n) {
		return new Vec3f(this.x / n, this.y / n, this.z / n);
	}

	@Override
	public Vec3f module(Float m) {
		return new Vec3f(this.x % m, this.y % m, this.z % m);
	}

	@Override
	public Vec3f min(Float value) {
		return new Vec3f(Math.min(this.x, value), Math.min(this.y, value), Math.min(this.z, value));
	}
	
	@Override
	public Vec3f min(IVector3<? extends Number> vec) {
		return new Vec3f(Math.min(this.x,  vec.x().floatValue()), Math.min(this.y,  vec.y().floatValue()), Math.min(this.z,  vec.z().floatValue()));
	}
	
	@Override
	public Vec3f max(Float value) {
		return new Vec3f(Math.max(this.x, value), Math.max(this.y, value), Math.max(this.z, value));
	}
	
	@Override
	public Vec3f max(IVector3<? extends Number> vec) {
		return new Vec3f(Math.max(this.x,  vec.x().floatValue()), Math.max(this.y,  vec.y().floatValue()), Math.max(this.z,  vec.z().floatValue()));
	}
	
	@Override
	public Vec3f clamp(IVector3<? extends Number> min, IVector3<? extends Number> max) {
		return new Vec3f(
				Math.max(min.x().floatValue(), Math.min(this.x, max.x().floatValue())),
				Math.max(min.y().floatValue(), Math.min(this.y, max.y().floatValue())),
				Math.max(min.z().floatValue(), Math.min(this.z, max.z().floatValue()))
			);
	}
	
	@Override
	public Vec3f clamp(Float min, Float max) {
		return new Vec3f(
				Math.max((Float) min, Math.min(this.x, (Float) max)),
				Math.max((Float) min, Math.min(this.y, (Float) max)),
				Math.max((Float) min, Math.min(this.z, (Float) max))
			);
	}

	@Override
	public Vec3f abs() {
		return new Vec3f(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
	}

	@Override
	public boolean isFinite() {
		return Float.isFinite(x) && Float.isFinite(y) && Float.isFinite(z);
	}
	
	@Override
	public double angle(IVector3<? extends Number> vec) {
		double f1 = this.dot(vec);
		double f2 = this.length() * (Float) vec.length();
		return Math.acos(f1 / f2);
	}
	
	@Override
	public Vec3f cross(IVector3<? extends Number> vec) {
		return new Vec3f(
				this.y * vec.z().floatValue() - this.z * vec.y().floatValue(),
				this.z * vec.x().floatValue() - this.x * vec.z().floatValue(),
				this.x * vec.y().floatValue() - this.y * vec.x().floatValue()
			);
	}
	
	@Override
	public Float dot(IVector3<? extends Number> vec) {
		return this.x * vec.x().floatValue() + this.y * vec.y().floatValue() + this.z * vec.z().floatValue();
	}
	
	@Override
	public Float length() {
		return (float) Math.sqrt(this.lengthSqr());
	}

	@Override
	public Float lengthSqr() {
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}
	
	@Override
	public Vec3f normalize() {
		float f = this.length();
		if (f == 0) throw new ArithmeticException("Division trough zero, cant normalize vector of length 0!");
		return this.div(f);
	}
	
	@Override
	public Vec3f lerp(IVector3<? extends Number> vec, Float delta) {
		float f = 1.0F - delta;
		return new Vec3f(
				this.x * f + vec.x().floatValue() * delta,
				this.y * f + vec.y().floatValue() * delta,
				this.z * f + vec.z().floatValue() * delta
			);
	}

	@Override
	public Float distSqr(IVector3<? extends Number> vec) {
		Float d1 = this.getX().floatValue() - vec.getX().floatValue();
		Float d2 = this.getY().floatValue() - vec.getY().floatValue();
		Float d3 = this.getZ().floatValue() - vec.getZ().floatValue();
		return d1 * d1 + d2 * d2 + d3 * d3;
	}

	@Override
	public Float dist(IVector3<? extends Number> vec) {
		return (float) Math.sqrt(this.distSqr(vec));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vec3f) {
			return	((Vec3f) obj).x == x && 
					((Vec3f) obj).y == y &&
					((Vec3f) obj).z == z;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = result * 31 + Float.hashCode(this.x);
		result = result * 31 + Float.hashCode(this.y);
		result = result * 32 + Float.hashCode(this.z);
		return result;
	}
	
	@Override
	public String toString() {
		return "Vec3f[" + this.x + "," + this.y + "," + this.z + "]";
	}

	@Override
	public Vec3f transform(Quaternionf quaternion) {
		Quaternionf q = quaternion.mul(new Quaternionf(x, y, z, 0.0F)).mul(quaternion.conj());;
		return new Vec3f(q.i(), q.j(), q.k());
	}

	@Override
	public Quaternionf relativeRotationQuat(IVector3<? extends Number> reference) {
		Vec3f v = new Vec3f(reference.x().floatValue(), reference.y().floatValue(), reference.z().floatValue()).cross(this);
		if (v.length() == 0) {
			v = new Vec3f(reference.y().floatValue(), reference.z().floatValue(), reference.x().floatValue());
		} else {
			v.normalizeI();
		}
		float angle = (float) Math.acos(this.dot(reference) / (this.length() * reference.length().floatValue()));
		return new Quaternionf(v, angle);
	}

	@Override
	public Class<? extends Number> getTypeClass() {
		return Float.class;
	}

	@Override
	public Vec3f anyOrthogonal() {
		return new Vec3f(-(z / x), 0, 1).normalize();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Vec3f[] orthogonals(IVector3<? extends Number> vec2) {
		return new Vec3f[] {this.cross(vec2), new Vec3f(((IVector3Math) vec2).cross(this))};
	}
	
}
