package de.m_marvin.univec.impl;

import de.m_marvin.unimat.api.IQuaternion;
import de.m_marvin.unimat.impl.Quaternion;
import de.m_marvin.univec.VectorParser;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector3Math;

/*
 * Implementation of a 3 dimensional integer vector
 */
public class Vec3i implements IVector3Math<Integer, Vec3i, IVector3<? extends Number>, Quaternion> {

	public int x;
	public int y;
	public int z;
	
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

	public static Vec3i fromVec(Object vectorObject) {
		return new Vec3i(0, 0, 0).readFrom(vectorObject);
	}
	
	@Override
	public <T> Vec3i readFrom(T vectorObject) {
		try {
			VectorParser.parseVectorObject(vectorObject, this);
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
	public Vec3i copy() {
		return new Vec3i(this.x, this.y, this.z);
	}

	@Override
	public Vec3i add(IVector3<? extends Number> vec) {
		return new Vec3i(this.x + vec.x().intValue(), this.y + vec.y().intValue(), this.z + vec.z().intValue());
	}

	@Override
	public Vec3i add(Integer x, Integer y, Integer z) {
		return new Vec3i(this.x + x, this.y + y, this.z + z);
	}

	@Override
	public Vec3i sub(IVector3<? extends Number> vec) {
		return new Vec3i(this.x - vec.x().intValue(), this.y - vec.y().intValue(), this.z - vec.z().intValue());
	}

	@Override
	public Vec3i sub(Integer x, Integer y, Integer z) {
		return new Vec3i(this.x - x, this.y - y, this.z - z);
	}

	@Override
	public Vec3i mul(IVector3<? extends Number> vec) {
		return new Vec3i(this.x * vec.x().intValue(), this.y * vec.y().intValue(), this.z * vec.z().intValue());
	}

	@Override
	public Vec3i mul(Integer x, Integer y, Integer z) {
		return new Vec3i(this.x * x, this.y * y, this.z * z);
	}

	@Override
	public Vec3i mul(Integer n) {
		return new Vec3i(this.x * n, this.y * n, this.z * n);
	}
	
	@Override
	public Vec3i div(IVector3<? extends Number> vec) {
		return new Vec3i(this.x / vec.x().intValue(), this.y / vec.y().intValue(), this.z / vec.z().intValue());
	}

	@Override
	public Vec3i div(Integer x, Integer y, Integer z) {
		return new Vec3i(this.x / x, this.y / y, this.z / z);
	}
	
	@Override
	public Vec3i div(Integer n) {
		return new Vec3i(this.x / n, this.y / n, this.z / n);
	}

	@Override
	public Vec3i module(Integer m) {
		return new Vec3i(this.x % m, this.y % m, this.z % m);
	}

	@Override
	public Vec3i clamp(IVector3<? extends Number> min, IVector3<? extends Number> max) {
		return new Vec3i(
				Math.max(min.x().intValue(), Math.min(this.x, max.x().intValue())),
				Math.max(min.y().intValue(), Math.min(this.y, max.y().intValue())),
				Math.max(min.z().intValue(), Math.min(this.z, max.z().intValue()))
			);
	}
	
	@Override
	public Vec3i clamp(Integer min, Integer max) {
		return new Vec3i(
				Math.max((Integer) min, Math.min(this.x, (Integer) max)),
				Math.max((Integer) min, Math.min(this.y, (Integer) max)),
				Math.max((Integer) min, Math.min(this.z, (Integer) max))
			);
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
	public Vec3i cross(IVector3<? extends Number> vec) {
		return new Vec3i(
				this.y * vec.z().intValue() - this.z * vec.y().intValue(),
				this.z * vec.x().intValue() - this.x * vec.z().intValue(),
				this.x * vec.y().intValue() - this.y * vec.x().intValue()
			);
	}
	
	@Override
	public Integer dot(IVector3<? extends Number> vec) {
		return this.x * vec.x().intValue() + this.y * vec.y().intValue() + this.z * vec.z().intValue();
	}
	
	@Override
	public Integer length() {
		return (int) Math.sqrt(this.lengthSqrt());
	}

	@Override
	public Integer lengthSqrt() {
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}
	
	@Override
	public Vec3i normalize() {
		int f = this.length();
		return this.div(f);
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
	public String toString() {
		return "Vec3i[" + this.x + "," + this.y + "," + this.z + "]";
	}

	@Override
	public IQuaternion<Quaternion> rotationRadians(Integer angle) {
		return new Quaternion(this, angle.floatValue());
	}

	@Override
	public Vec3i transform(Quaternion quaternion) {
		Quaternion quaternion2 = quaternion.copy().mulI(new Quaternion(x, y, z, 0F));
		Quaternion quaternion3 = quaternion.copy().conjI();
		quaternion2.mulI(quaternion3);
		return new Vec3i((int) quaternion2.i(), (int) quaternion2.j(), (int) quaternion2.k());
	}

	@Override
	public Quaternion relativeRotationQuat(IVector3<? extends Number> reference) {
		Vec3i v = new Vec3i(reference.x().intValue(), reference.y().intValue(), reference.z().intValue()).cross(this);
		if (v.length() == 0) {
			v = new Vec3i(reference.y().intValue(), reference.z().intValue(), reference.x().intValue());
		} else {
			v.normalizeI();
		}
		float angle = (float) Math.acos(this.dot(reference));
		return new Quaternion(v, angle);
	}

	@Override
	public Class<? extends Number> getTypeClass() {
		return Integer.class;
	}

	@Override
	public Vec3i anyOrthogonal() {
		return new Vec3i(-(z / x), 0, 1).normalize();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Vec3i[] orthogonals(IVector3<? extends Number> vec2) {
		return new Vec3i[] {this.cross(vec2), new Vec3i(((IVector3Math) vec2).cross(this))};
	}
	
}
