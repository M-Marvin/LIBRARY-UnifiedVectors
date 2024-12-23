package de.m_marvin.univec.impl;

import de.m_marvin.unimat.impl.Quaterniond;
import de.m_marvin.univec.VectorParser;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector3Math;

/*
 * Implementation of a 3 dimensional double vector
 */
public class Vec3d implements IVector3Math<Double, Vec3d, IVector3<? extends Number>, Quaterniond> {

	public double x;
	public double y;
	public double z;
	
	public Vec3d(IVector2<? extends Number> vec2, double z) {
		this.x = vec2.x().doubleValue();
		this.y = vec2.y().doubleValue();
		this.z = z;
	}
	
	public Vec3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec3d() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public Vec3d(IVector3<? extends Number> vec) {
		this.x = vec.x().doubleValue();
		this.y = vec.y().doubleValue();
		this.z = vec.z().doubleValue();
	}
	
	public static Vec3d fromVec(Object vectorObject) {
		return new Vec3d(0, 0, 0).readFrom(vectorObject);
	}
	
	@Override
	public <T> Vec3d readFrom(T vectorObject) {
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
	public Vec3d min(Double value) {
		return new Vec3d(Math.min(this.x, value), Math.min(this.y, value), Math.min(this.z, value));
	}
	
	@Override
	public Vec3d min(IVector3<? extends Number> vec) {
		return new Vec3d(Math.min(this.x,  vec.x().doubleValue()), Math.min(this.y,  vec.y().doubleValue()), Math.min(this.z,  vec.z().doubleValue()));
	}
	
	@Override
	public Vec3d max(Double value) {
		return new Vec3d(Math.max(this.x, value), Math.max(this.y, value), Math.max(this.z, value));
	}
	
	@Override
	public Vec3d max(IVector3<? extends Number> vec) {
		return new Vec3d(Math.max(this.x,  vec.x().doubleValue()), Math.max(this.y,  vec.y().doubleValue()), Math.max(this.z,  vec.z().doubleValue()));
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
	public Vec3d abs() {
		return new Vec3d(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
	}

	@Override
	public boolean isFinite() {
		return Double.isFinite(x) && Double.isFinite(y) && Double.isFinite(z);
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
		return (Double) Math.sqrt(this.lengthSqr());
	}

	@Override
	public Double lengthSqr() {
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}
	
	@Override
	public Vec3d normalize() {
		double f = this.length();
		if (f == 0) throw new ArithmeticException("Division trough zero, cant normalize vector of length 0!");
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
	public Double distSqr(IVector3<? extends Number> vec) {
		Double d1 = this.getX().doubleValue() - vec.getX().doubleValue();
		Double d2 = this.getY().doubleValue() - vec.getY().doubleValue();
		Double d3 = this.getZ().doubleValue() - vec.getZ().doubleValue();
		return d1 * d1 + d2 * d2 + d3 * d3;
	}

	@Override
	public Double dist(IVector3<? extends Number> vec) {
		return Math.sqrt(this.distSqr(vec));
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
		result = result * 31 + Double.hashCode(this.x);
		result = result * 31 + Double.hashCode(this.y);
		result = result * 32 + Double.hashCode(this.z);
		return result;
	}
	
	@Override
	public String toString() {
		return "Vec3d[" + this.x + "," + this.y + "," + this.z + "]";
	}
	
	@Override
	public Vec3d transform(Quaterniond quaternion) {
		Quaterniond q = quaternion.mul(new Quaterniond(x, y, z, 0.0)).mul(quaternion.conj());
		return new Vec3d(q.i(), q.j(), q.k());
	}

	@Override
	public Quaterniond relativeRotationQuat(IVector3<? extends Number> reference) {		
		Vec3d v = new Vec3d(reference.x().doubleValue(), reference.y().doubleValue(), reference.z().doubleValue()).cross(this);
		if (v.length() == 0) {
			v = new Vec3d(reference.y().doubleValue(), reference.z().doubleValue(), reference.x().doubleValue());
		} else {
			v.normalizeI();
		}
		double angle = Math.acos(this.dot(reference) / (this.length() * reference.length().doubleValue()));
		return new Quaterniond(v, angle);
	}

	@Override
	public Class<? extends Number> getTypeClass() {
		return Double.class;
	}

	@Override
	public Vec3d anyOrthogonal() {
		// FIXME random orthogonal vector
		return new Vec3d(-(z / x), 0, 1).normalize();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Vec3d[] orthogonals(IVector3<? extends Number> vec2) {
		return new Vec3d[] {this.cross(vec2), new Vec3d(((IVector3Math) vec2).cross(this))};
	}
	
}
