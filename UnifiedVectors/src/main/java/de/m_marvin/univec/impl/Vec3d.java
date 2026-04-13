package de.m_marvin.univec.impl;

import de.m_marvin.unimat.impl.Quaterniond;
import de.m_marvin.univec.VectorParser;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector3Math;

/*
 * Implementation of a 3 dimensional double vector
 */
public class Vec3d implements IVector3Math<Double, Vec3d, Quaterniond> {

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

	@Override
	public Class<? extends Number> getTypeClass() {
		return Double.class;
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
	public Vec3d reset() {
		this.x = this.y = this.z = 0;
		return this;
	}
	
	@Override
	public Vec3d copy() {
		return new Vec3d(this.x, this.y, this.z);
	}

	@Override
	public Vec3d addI(IVector3<? extends Number> vec) {
		this.x += vec.x().doubleValue();
		this.y += vec.y().doubleValue();
		this.z += vec.z().doubleValue();
		return this;
	}

	@Override
	public Vec3d addI(Double x, Double y, Double z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	@Override
	public Vec3d subI(IVector3<? extends Number> vec) {
		this.x -= vec.x().doubleValue();
		this.y -= vec.y().doubleValue();
		this.z -= vec.z().doubleValue();
		return this;
	}

	@Override
	public Vec3d subI(Double x, Double y, Double z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	@Override
	public Vec3d mulI(IVector3<? extends Number> vec) {
		this.x *= vec.x().doubleValue();
		this.y *= vec.y().doubleValue();
		this.z *= vec.z().doubleValue();
		return this;
	}

	@Override
	public Vec3d mulI(Double x, Double y, Double z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}

	@Override
	public Vec3d mulI(Double n) {
		this.x *= n;
		this.y *= n;
		this.z *= n;
		return this;
	}
	
	@Override
	public Vec3d divI(IVector3<? extends Number> vec) {
		this.x /= vec.x().doubleValue();
		this.y /= vec.y().doubleValue();
		this.z /= vec.z().doubleValue();
		return this;
	}

	@Override
	public Vec3d divI(Double x, Double y, Double z) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		return this;
	}
	
	@Override
	public Vec3d divI(Double n) {
		this.x /= n;
		this.y /= n;
		this.z /= n;
		return this;
	}

	@Override
	public Vec3d moduleI(Double m) {
		this.x %= m;
		this.y %= m;
		this.z %= m;
		return this;
	}

	@Override
	public Vec3d clampI(IVector3<? extends Number> min, IVector3<? extends Number> max) {
		this.x = Math.max(min.x().doubleValue(), Math.min(this.x, max.x().doubleValue()));
		this.y = Math.max(min.y().doubleValue(), Math.min(this.y, max.y().doubleValue()));
		this.z = Math.max(min.z().doubleValue(), Math.min(this.z, max.z().doubleValue()));
		return this;
	}

	@Override
	public Vec3d minI(Double value) {
		this.x = Math.min(this.x, value);
		this.y = Math.min(this.y, value);
		this.z = Math.min(this.z, value);
		return this;
	}
	
	@Override
	public Vec3d minI(IVector3<? extends Number> vec) {
		this.x = Math.min(this.x,  vec.x().doubleValue());
		this.y = Math.min(this.y,  vec.y().doubleValue());
		this.z = Math.min(this.z,  vec.z().doubleValue());
		return this;
	}
	
	@Override
	public Vec3d maxI(Double value) {
		this.x = Math.max(this.x, value);
		this.y = Math.max(this.y, value);
		this.z = Math.max(this.z, value);
		return this;
	}
	
	@Override
	public Vec3d maxI(IVector3<? extends Number> vec) {
		this.x = Math.max(this.x,  vec.x().doubleValue());
		this.y = Math.max(this.y,  vec.y().doubleValue());
		this.z = Math.max(this.z,  vec.z().doubleValue());
		return this;
	}
	
	@Override
	public Vec3d clampI(Double min, Double max) {
		this.x = Math.max((Double) min, Math.min(this.x, (Double) max));
		this.y = Math.max((Double) min, Math.min(this.y, (Double) max));
		this.z = Math.max((Double) min, Math.min(this.z, (Double) max));
		return this;
	}

	@Override
	public Vec3d absI() {
		this.x = Math.abs(this.x);
		this.y = Math.abs(this.y);
		this.z = Math.abs(this.z);
		return this;
	}

	@Override
	public Vec3d negateI() {
		this.x = -this.x;
		this.y = -this.y;
		this.z = -this.z;
		return this;
	}
	
	@Override
	public Double sum() {
		return this.x + this.y + this.z;
	}
	
	@Override
	public Vec3d signI() {
		this.x = this.x > 0 ? 1 : this.x < 0 ? -1 : 0;
		this.y = this.y > 0 ? 1 : this.y < 0 ? -1 : 0;
		this.z = this.z > 0 ? 1 : this.z < 0 ? -1 : 0;
		return this;
	}

	@Override
	public Vec3d floorI() {
		this.x = Math.floor(this.x);
		this.y = Math.floor(this.y);
		this.z = Math.floor(this.z);
		return this;
	}

	@Override
	public Vec3d ceilI() {
		this.x = Math.ceil(this.x);
		this.y = Math.ceil(this.y);
		this.z = Math.ceil(this.z);
		return this;
	}

	@Override
	public Vec3d roundI() {
		this.x = Math.round(this.x);
		this.y = Math.round(this.y);
		this.z = Math.round(this.z);
		return this;
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
	public Vec3d crossI(IVector3<? extends Number> vec) {
		double x = this.y * vec.z().doubleValue() - this.z * vec.y().doubleValue();
		double y = this.z * vec.x().doubleValue() - this.x * vec.z().doubleValue();
		this.z = this.x * vec.y().doubleValue() - this.y * vec.x().doubleValue();
		this.y = y;
		this.x = x;
		return this;
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
	public Vec3d normalizeI() {
		double f = this.length();
		if (f == 0) throw new ArithmeticException("division trough zero, cant normalize vector of length 0");
		return this.divI(f);
	}

	@Override
	public Vec3d tryNormalizeI() {
		double f = this.length();
		if (f == 0) return this.setI(0D, 0D, 0D);
		return this.divI(f);
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
	public Vec3d anyOrthogonal() {
		return new Vec3d(-z, x, y).cross(this).normalize();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Vec3d[] orthogonals(IVector3<? extends Number> vec2) {
		return new Vec3d[] {this.cross(vec2), new Vec3d(((IVector3Math) vec2).cross(this))};
	}
	
	@Override
	public String toString() {
		return String.format("[%f  %f  %f]", this.x, this.y, this.z);
	}
	
}
