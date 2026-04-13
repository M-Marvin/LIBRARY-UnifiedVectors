package de.m_marvin.univec.impl;

import de.m_marvin.unimat.impl.Quaternionf;
import de.m_marvin.univec.VectorParser;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector3Math;

/*
 * Implementation of a 3 dimensional integer vector
 */
public class Vec3i implements IVector3Math<Integer, Vec3i, Quaternionf> {

	public int x;
	public int y;
	public int z;

	public Vec3i(IVector2<? extends Number> vec2, int z) {
		this.x = vec2.x().intValue();
		this.y = vec2.y().intValue();
		this.z = z;
	}
	
	public Vec3i(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec3i() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public Vec3i(IVector3<? extends Number> vec) {
		this.x = vec.x().intValue();
		this.y = vec.y().intValue();
		this.z = vec.z().intValue();
	}

	@Override
	public Class<? extends Number> getTypeClass() {
		return Integer.class;
	}

	public static Vec3i fromVec(Object vectorObject) {
		return new Vec3i(0, 0, 0).readFrom(vectorObject);
	}
	
	@Override
	public <T> Vec3i readFrom(T vectorObject) {
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
	public Integer x() {
		return x;
	}

	@Override
	public Integer y() {
		return y;
	}
	
	@Override
	public Integer z() {
		return z;
	}

	@Override
	public void setX(Integer x) {
		this.x = x;
	}

	@Override
	public void setY(Integer y) {
		this.y = y;
	}
	
	@Override
	public void setZ(Integer z) {
		this.z = z;
	}

	@Override
	public Vec3i setI(Integer x, Integer y, Integer z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	@Override
	public Vec3i setI(IVector3<? extends Number> vec) {
		this.x = vec.x().intValue();
		this.y = vec.y().intValue();
		this.z = vec.z().intValue();
		return this;
	}

	@Override
	public Vec3i reset() {
		this.x = this.y = this.z = 0;
		return this;
	}
	
	@Override
	public Vec3i copy() {
		return new Vec3i(this.x, this.y, this.z);
	}

	@Override
	public Vec3i addI(IVector3<? extends Number> vec) {
		this.x += vec.x().doubleValue();
		this.y += vec.y().doubleValue();
		this.z += vec.z().doubleValue();
		return this;
	}

	@Override
	public Vec3i addI(Integer x, Integer y, Integer z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	@Override
	public Vec3i subI(IVector3<? extends Number> vec) {
		this.x -= vec.x().doubleValue();
		this.y -= vec.y().doubleValue();
		this.z -= vec.z().doubleValue();
		return this;
	}

	@Override
	public Vec3i subI(Integer x, Integer y, Integer z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	@Override
	public Vec3i mulI(IVector3<? extends Number> vec) {
		this.x *= vec.x().doubleValue();
		this.y *= vec.y().doubleValue();
		this.z *= vec.z().doubleValue();
		return this;
	}

	@Override
	public Vec3i mulI(Integer x, Integer y, Integer z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}

	@Override
	public Vec3i mulI(Integer n) {
		this.x *= n;
		this.y *= n;
		this.z *= n;
		return this;
	}
	
	@Override
	public Vec3i divI(IVector3<? extends Number> vec) {
		this.x /= vec.x().doubleValue();
		this.y /= vec.y().doubleValue();
		this.z /= vec.z().doubleValue();
		return this;
	}

	@Override
	public Vec3i divI(Integer x, Integer y, Integer z) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		return this;
	}
	
	@Override
	public Vec3i divI(Integer n) {
		this.x /= n;
		this.y /= n;
		this.z /= n;
		return this;
	}

	@Override
	public Vec3i moduleI(Integer m) {
		this.x %= m;
		this.y %= m;
		this.z %= m;
		return this;
	}

	@Override
	public Vec3i clampI(IVector3<? extends Number> min, IVector3<? extends Number> max) {
		this.x = (int) Math.max(min.x().doubleValue(), Math.min(this.x, max.x().doubleValue()));
		this.y = (int) Math.max(min.y().doubleValue(), Math.min(this.y, max.y().doubleValue()));
		this.z = (int) Math.max(min.z().doubleValue(), Math.min(this.z, max.z().doubleValue()));
		return this;
	}

	@Override
	public Vec3i minI(Integer value) {
		this.x = Math.min(this.x, value);
		this.y = Math.min(this.y, value);
		this.z = Math.min(this.z, value);
		return this;
	}
	
	@Override
	public Vec3i minI(IVector3<? extends Number> vec) {
		this.x = (int) Math.min(this.x,  vec.x().doubleValue());
		this.y = (int) Math.min(this.y,  vec.y().doubleValue());
		this.z = (int) Math.min(this.z,  vec.z().doubleValue());
		return this;
	}
	
	@Override
	public Vec3i maxI(Integer value) {
		this.x = Math.max(this.x, value);
		this.y = Math.max(this.y, value);
		this.z = Math.max(this.z, value);
		return this;
	}
	
	@Override
	public Vec3i maxI(IVector3<? extends Number> vec) {
		this.x = (int) Math.max(this.x,  vec.x().doubleValue());
		this.y = (int) Math.max(this.y,  vec.y().doubleValue());
		this.z = (int) Math.max(this.z,  vec.z().doubleValue());
		return this;
	}
	
	@Override
	public Vec3i clampI(Integer min, Integer max) {
		this.x = Math.max((Integer) min, Math.min(this.x, (Integer) max));
		this.y = Math.max((Integer) min, Math.min(this.y, (Integer) max));
		this.z = Math.max((Integer) min, Math.min(this.z, (Integer) max));
		return this;
	}

	@Override
	public Vec3i absI() {
		this.x = Math.abs(this.x);
		this.y = Math.abs(this.y);
		this.z = Math.abs(this.z);
		return this;
	}

	@Override
	public Vec3i negateI() {
		this.x = -this.x;
		this.y = -this.y;
		this.z = -this.z;
		return this;
	}
	
	@Override
	public Integer sum() {
		return this.x + this.y + this.z;
	}
	
	@Override
	public Vec3i signI() {
		this.x = this.x > 0 ? 1 : this.x < 0 ? -1 : 0;
		this.y = this.y > 0 ? 1 : this.y < 0 ? -1 : 0;
		this.z = this.z > 0 ? 1 : this.z < 0 ? -1 : 0;
		return this;
	}

	@Override
	public Vec3i floorI() {
		this.x = (int) Math.floor(this.x);
		this.y = (int) Math.floor(this.y);
		this.z = (int) Math.floor(this.z);
		return this;
	}

	@Override
	public Vec3i ceilI() {
		this.x = (int) Math.ceil(this.x);
		this.y = (int) Math.ceil(this.y);
		this.z = (int) Math.ceil(this.z);
		return this;
	}

	@Override
	public Vec3i roundI() {
		this.x = Math.round(this.x);
		this.y = Math.round(this.y);
		this.z = Math.round(this.z);
		return this;
	}
	
	@Override
	public boolean isFinite() {
		return true;
	}
	
	@Override
	public double angle(IVector3<? extends Number> vec) {
		double f1 = this.dot(vec);
		double f2 = this.length() * (Integer) vec.length();
		return Math.acos(f1 / f2);
	}

	@Override
	public Vec3i crossI(IVector3<? extends Number> vec) {
		int x = this.y * vec.z().intValue() - this.z * vec.y().intValue();
		int y = this.z * vec.x().intValue() - this.x * vec.z().intValue();
		this.z = this.x * vec.y().intValue() - this.y * vec.x().intValue();
		this.y = y;
		this.x = x;
		return this;
	}
	
	@Override
	public Integer dot(IVector3<? extends Number> vec) {
		return this.x * vec.x().intValue() + this.y * vec.y().intValue() + this.z * vec.z().intValue();
	}
	
	@Override
	public Integer length() {
		return (int) Math.sqrt(this.lengthSqr());
	}

	@Override
	public Integer lengthSqr() {
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}

	@Override
	public Vec3i normalizeI() {
		int f = this.length();
		if (f == 0) throw new ArithmeticException("division trough zero, cant normalize vector of length 0");
		return this.divI(f);
	}

	@Override
	public Vec3i tryNormalizeI() {
		int f = this.length();
		if (f == 0) return this.setI(0, 0, 0);
		return this.divI(f);
	}
	
	@Override
	public Vec3i lerp(IVector3<? extends Number> vec, Integer delta) {
		float f = 1.0F - delta;
		return new Vec3i(
				(int) (this.x * f + vec.x().intValue() * delta),
				(int) (this.y * f + vec.y().intValue() * delta),
				(int) (this.z * f + vec.z().intValue() * delta)
			);
	}

	@Override
	public Integer distSqr(IVector3<? extends Number> vec) {
		Integer d1 = this.getX().intValue() - vec.getX().intValue();
		Integer d2 = this.getY().intValue() - vec.getY().intValue();
		Integer d3 = this.getZ().intValue() - vec.getZ().intValue();
		return d1 * d1 + d2 * d2 + d3 * d3;
	}
	
	@Override
	public Integer dist(IVector3<? extends Number> vec) {
		return (int) Math.sqrt(this.distSqr(vec));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vec3i) {
			return	((Vec3i) obj).x == x && 
					((Vec3i) obj).y == y &&
					((Vec3i) obj).z == z;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = result * 31 + Integer.hashCode(this.x);
		result = result * 31 + Integer.hashCode(this.y);
		result = result * 32 + Integer.hashCode(this.z);
		return result;
	}

	@Override
	public Vec3i transform(Quaternionf quaternion) {
		Quaternionf q = quaternion.mul(new Quaternionf(x, y, z, 0F)).mul(quaternion.conj());;
		return new Vec3i(q.i().intValue(), q.j().intValue(), q.k().intValue());
	}

	@Override
	public Quaternionf relativeRotationQuat(IVector3<? extends Number> reference) {
		Vec3i v = new Vec3i(reference.x().intValue(), reference.y().intValue(), reference.z().intValue()).cross(this);
		if (v.length() == 0) {
			v = new Vec3i(reference.y().intValue(), reference.z().intValue(), reference.x().intValue());
		} else {
			v.normalizeI();
		}
		float angle = (float) Math.acos(this.dot(reference) / (this.length() * reference.length().floatValue()));
		return new Quaternionf(v, angle);
	}

	@Override
	public Vec3i anyOrthogonal() {
		return new Vec3i(-z, x, y).cross(this).normalize();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Vec3i[] orthogonals(IVector3<? extends Number> vec2) {
		return new Vec3i[] {this.cross(vec2), new Vec3i(((IVector3Math) vec2).cross(this))};
	}
	
	@Override
	public String toString() {
		return String.format("[%d  %d  %d]", this.x, this.y, this.z);
	}
	
}
