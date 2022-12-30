package de.m_marvin.univec.impl;

import de.m_marvin.unimat.api.IQuaternion;
import de.m_marvin.unimat.impl.Quaternion;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector3Math;

/*
 * Implementation of a 3 dimensional double vector
 */
public class Vec3d implements IVector3Math<Double, Vec3d, IVector3<? extends Number>, Quaternion> {

	public double x;
	public double y;
	public double z;
	
	public Vec3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec3d(IVector3<? extends Number> vec) {
		this.x = vec.x().doubleValue();
		this.y = vec.y().doubleValue();
		this.z = vec.z().doubleValue();
	}
	
	@Override
	public Double x() {
		return x;
	}

	@Override
	public Double y() {
		return y;
	}
	
	@Override
	public Double z() {
		return z;
	}

	@Override
	public void setX(Double x) {
		this.x = x;
	}

	@Override
	public void setY(Double y) {
		this.y = y;
	}
	
	@Override
	public void setZ(Double z) {
		this.z = z;
	}

	@Override
	public Vec3d setI(Double x, Double y, Double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	@Override
	public Vec3d setI(IVector3<? extends Number> vec) {
		this.x = vec.x().doubleValue();
		this.y = vec.y().doubleValue();
		this.z = vec.z().doubleValue();
		return this;
	}

	@Override
	public Vec3d copy() {
		return new Vec3d(this.x, this.y, this.z);
	}

	@Override
	public Vec3d add(IVector3<? extends Number> vec) {
		return new Vec3d(this.x + vec.x().doubleValue(), this.y + vec.y().doubleValue(), this.z + vec.z().doubleValue());
	}

	@Override
	public Vec3d add(Double x, Double y, Double z) {
		return new Vec3d(this.x + x, this.y + y, this.z + z);
	}

	@Override
	public Vec3d sub(IVector3<? extends Number> vec) {
		return new Vec3d(this.x - vec.x().doubleValue(), this.y - vec.y().doubleValue(), this.z - vec.z().doubleValue());
	}

	@Override
	public Vec3d sub(Double x, Double y, Double z) {
		return new Vec3d(this.x - x, this.y - y, this.z - z);
	}

	@Override
	public Vec3d mul(IVector3<? extends Number> vec) {
		return new Vec3d(this.x * vec.x().doubleValue(), this.y * vec.y().doubleValue(), this.z * vec.z().doubleValue());
	}

	@Override
	public Vec3d mul(Double x, Double y, Double z) {
		return new Vec3d(this.x * x, this.y * y, this.z * z);
	}

	@Override
	public Vec3d mul(Double n) {
		return new Vec3d(this.x * n, this.y * n, this.z * n);
	}
	
	@Override
	public Vec3d div(IVector3<? extends Number> vec) {
		return new Vec3d(this.x / vec.x().doubleValue(), this.y / vec.y().doubleValue(), this.z / vec.z().doubleValue());
	}

	@Override
	public Vec3d div(Double x, Double y, Double z) {
		return new Vec3d(this.x / x, this.y / y, this.z / z);
	}
	
	@Override
	public Vec3d div(Double n) {
		return new Vec3d(this.x / n, this.y / n, this.z / n);
	}

	@Override
	public Vec3d module(Double m) {
		return new Vec3d(this.x % m, this.y % m, this.z % m);
	}

	@Override
	public Vec3d clamp(IVector3<? extends Number> min, IVector3<? extends Number> max) {
		return new Vec3d(
				Math.max(min.x().doubleValue(), Math.min(this.x, max.x().doubleValue())),
				Math.max(min.y().doubleValue(), Math.min(this.y, max.y().doubleValue())),
				Math.max(min.z().doubleValue(), Math.min(this.z, max.z().doubleValue()))
			);
	}
	
	@Override
	public Vec3d clamp(Double min, Double max) {
		return new Vec3d(
				Math.max((Double) min, Math.min(this.x, (Double) max)),
				Math.max((Double) min, Math.min(this.y, (Double) max)),
				Math.max((Double) min, Math.min(this.z, (Double) max))
			);
	}

	@Override
	public double angle(IVector3<? extends Number> vec) {
		double f1 = this.dot(vec);
		double f2 = this.length() * (Double) vec.length();
		return Math.acos(f1 / f2);
	}
	
	@Override
	public Vec3d cross(IVector3<? extends Number> vec) {
		return new Vec3d(
				this.y * vec.z().doubleValue() - this.z * vec.y().doubleValue(),
				this.z * vec.x().doubleValue() - this.x * vec.z().doubleValue(),
				this.x * vec.y().doubleValue() - this.y * vec.x().doubleValue()
			);
	}
	
	@Override
	public Double dot(IVector3<? extends Number> vec) {
		return this.x * vec.x().doubleValue() + this.y * vec.y().doubleValue() + this.z * vec.z().doubleValue();
	}
	
	@Override
	public Double length() {
		return (Double) Math.sqrt(this.lengthSqrt());
	}

	@Override
	public Double lengthSqrt() {
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}
	
	@Override
	public Vec3d normalize() {
		double f = this.length();
		return this.div(f);
	}
	
	@Override
	public Vec3d lerp(IVector3<? extends Number> vec, Double delta) {
		double f = 1.0F - delta;
		return new Vec3d(
				this.x * f + vec.x().doubleValue() * delta,
				this.y * f + vec.y().doubleValue() * delta,
				this.z * f + vec.z().doubleValue() * delta
			);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vec3d) {
			return	((Vec3d) obj).x == x && 
					((Vec3d) obj).y == y &&
					((Vec3d) obj).z == z;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = result * 31 + Double.hashCode(this.x); // Could be just 31 + x/y/z
		result = result * 31 + Double.hashCode(this.y);
		result = result * 32 + Double.hashCode(this.z);
		return result;
	}
	
	@Override
	public String toString() {
		return "Vec3i[" + this.x + "," + this.y + "," + this.z + "]";
	}

	@Override
	public IQuaternion<Quaternion> rotationRadians(Double angle) {
		return new Quaternion(new Vec3i(this), angle.floatValue());
	}

	@Override
	public Vec3d transform(Quaternion quaternion) {
		Quaternion quaternion2 = quaternion.copy().mulI(new Quaternion((float) x, (float) y, (float) z, 0F));
		Quaternion quaternion3 = quaternion.copy().conjI();
		quaternion2.mulI(quaternion3);
		return new Vec3d((double) quaternion2.i(), (double) quaternion2.j(), (double) quaternion2.k());
	}
	
}
