package de.m_marvin.univec.impl;

import de.m_marvin.univec.VectorParser;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector4;
import de.m_marvin.univec.api.IVector4Math;

public class Vec4d implements IVector4Math<Double, Vec4d> {
	
	public double x;
	public double y;
	public double z;
	public double w;

	public Vec4d(IVector2<? extends Number> vec2, double z, double w) {
		this.x = vec2.x().doubleValue();
		this.y = vec2.y().doubleValue();
		this.z = z;
		this.w = w;
	}

	public Vec4d(IVector3<? extends Number> vec3, double w) {
		this.x = vec3.x().doubleValue();
		this.y = vec3.y().doubleValue();
		this.z = vec3.z().doubleValue();
		this.w = w;
	}
	
	public Vec4d(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public Vec4d() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.w = 0;
	}

	public Vec4d(IVector4<? extends Number> vec) {
		this.x = vec.x().doubleValue();
		this.y = vec.y().doubleValue();
		this.z = vec.z().doubleValue();
		this.w = vec.w().doubleValue();
	}

	@Override
	public Class<? extends Number> getTypeClass() {
		return Double.class;
	}
	
	public static Vec4d fromVec(Object vectorObject) {
		return new Vec4d(0, 0, 0, 0).readFrom(vectorObject);
	}
	
	@Override
	public <T> Vec4d readFrom(T vectorObject) {
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
	public Double w() {
		return w;
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
	public void setW(Double w) {
		this.w = w;
	}

	@Override
	public Vec4d setI(Double x, Double y, Double z, Double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		return this;
	}

	@Override
	public Vec4d setI(IVector4<? extends Number> vec) {
		this.x = vec.x().doubleValue();
		this.y = vec.y().doubleValue();
		this.z = vec.z().doubleValue();
		this.w = vec.w().doubleValue();
		return this;
	}

	@Override
	public Vec4d reset() {
		this.x = this.y = this.z = this.w = 0;
		return this;
	}
	
	@Override
	public Vec4d copy() {
		return new Vec4d(this.x, this.y, this.z, this.w);
	}

	@Override
	public Vec4d addI(IVector4<? extends Number> vec) {
		this.x += vec.x().doubleValue();
		this.y += vec.y().doubleValue();
		this.z += vec.z().doubleValue();
		this.w += vec.w().doubleValue();
		return this;
	}

	@Override
	public Vec4d addI(Double x, Double y, Double z, Double w) {
		this.x += x;
		this.y += y;
		this.z += z;
		this.w += w;
		return this;
	}

	@Override
	public Vec4d subI(IVector4<? extends Number> vec) {
		this.x -= vec.x().doubleValue();
		this.y -= vec.y().doubleValue();
		this.z -= vec.z().doubleValue();
		this.w -= vec.w().doubleValue();
		return this;
	}

	@Override
	public Vec4d subI(Double x, Double y, Double z, Double w) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		this.w -= w;
		return this;
	}

	@Override
	public Vec4d mulI(IVector4<? extends Number> vec) {
		this.x *= vec.x().doubleValue();
		this.y *= vec.y().doubleValue();
		this.z *= vec.z().doubleValue();
		this.w *= vec.w().doubleValue();
		return this;
	}

	@Override
	public Vec4d mulI(Double x, Double y, Double z, Double w) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		this.w *= w;
		return this;
	}

	@Override
	public Vec4d mulI(Double n) {
		this.x *= n;
		this.y *= n;
		this.z *= n;
		this.w *= n;
		return this;
	}
	
	@Override
	public Vec4d divI(IVector4<? extends Number> vec) {
		this.x /= vec.x().doubleValue();
		this.y /= vec.y().doubleValue();
		this.z /= vec.z().doubleValue();
		this.w /= vec.w().doubleValue();
		return this;
	}

	@Override
	public Vec4d divI(Double x, Double y, Double z, Double w) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		this.w /= w;
		return this;
	}
	
	@Override
	public Vec4d divI(Double n) {
		this.x /= n;
		this.y /= n;
		this.z /= n;
		this.w /= n;
		return this;
	}

	@Override
	public Vec4d moduleI(Double m) {
		this.x %= m;
		this.y %= m;
		this.z %= m;
		this.w %= m;
		return this;
	}

	@Override
	public Vec4d clampI(IVector4<? extends Number> min, IVector4<? extends Number> max) {
		this.x = Math.max(min.x().doubleValue(), Math.min(this.x, max.x().doubleValue()));
		this.y = Math.max(min.y().doubleValue(), Math.min(this.y, max.y().doubleValue()));
		this.z = Math.max(min.z().doubleValue(), Math.min(this.z, max.z().doubleValue()));
		this.w = Math.max(min.w().doubleValue(), Math.min(this.w, max.w().doubleValue()));
		return this;
	}

	@Override
	public Vec4d minI(Double value) {
		this.x = Math.min(this.x, value);
		this.y = Math.min(this.y, value);
		this.z = Math.min(this.z, value);
		this.w = Math.min(this.w, value);
		return this;
	}
	
	@Override
	public Vec4d minI(IVector4<? extends Number> vec) {
		this.x = Math.min(this.x,  vec.x().doubleValue());
		this.y = Math.min(this.y,  vec.y().doubleValue());
		this.z = Math.min(this.z,  vec.z().doubleValue());
		this.w = Math.min(this.w,  vec.w().doubleValue());
		return this;
	}
	
	@Override
	public Vec4d maxI(Double value) {
		this.x = Math.max(this.x, value);
		this.y = Math.max(this.y, value);
		this.z = Math.max(this.z, value);
		this.w = Math.max(this.w, value);
		return this;
	}
	
	@Override
	public Vec4d maxI(IVector4<? extends Number> vec) {
		this.x = Math.max(this.x,  vec.x().doubleValue());
		this.y = Math.max(this.y,  vec.y().doubleValue());
		this.z = Math.max(this.z,  vec.z().doubleValue());
		this.w = Math.max(this.w,  vec.w().doubleValue());
		return this;
	}
	
	@Override
	public Vec4d clampI(Double min, Double max) {
		this.x = Math.max(min, Math.min(this.x, max));
		this.y = Math.max(min, Math.min(this.y, max));
		this.z = Math.max(min, Math.min(this.z, max));
		this.w = Math.max(min, Math.min(this.w, max));
		return this;
	}

	@Override
	public Vec4d absI() {
		this.x = Math.abs(this.x);
		this.y = Math.abs(this.y);
		this.z = Math.abs(this.z);
		this.w = Math.abs(this.w);
		return this;
	}

	@Override
	public Vec4d negateI() {
		this.x = -this.x;
		this.y = -this.y;
		this.z = -this.z;
		this.w = -this.w;
		return this;
	}
	
	@Override
	public Double sum() {
		return this.x + this.y + this.z + this.w;
	}
	
	@Override
	public Vec4d signI() {
		this.x = this.x > 0 ? 1 : this.x < 0 ? -1 : 0;
		this.y = this.y > 0 ? 1 : this.y < 0 ? -1 : 0;
		this.z = this.z > 0 ? 1 : this.z < 0 ? -1 : 0;
		this.w = this.w > 0 ? 1 : this.w < 0 ? -1 : 0;
		return this;
	}

	@Override
	public Vec4d floorI() {
		this.x = Math.floor(this.x);
		this.y = Math.floor(this.y);
		this.z = Math.floor(this.z);
		this.w = Math.floor(this.w);
		return this;
	}

	@Override
	public Vec4d ceilI() {
		this.x = Math.ceil(this.x);
		this.y = Math.ceil(this.y);
		this.z = Math.ceil(this.z);
		this.w = Math.ceil(this.w);
		return this;
	}

	@Override
	public Vec4d roundI() {
		this.x = Math.round(this.x);
		this.y = Math.round(this.y);
		this.z = Math.round(this.z);
		this.w = Math.round(this.w);
		return this;
	}
	
	@Override
	public boolean isFinite() {
		return Double.isFinite(x) && Double.isFinite(y) && Double.isFinite(z) && Double.isFinite(w);
	}

	@Override
	public Vec4d crossI(IVector3<? extends Number> vec) {
		double x = this.y * vec.z().doubleValue() - this.z * vec.y().doubleValue();
		double y = this.z * vec.x().doubleValue() - this.x * vec.z().doubleValue();
		this.z = this.x * vec.y().doubleValue() - this.y * vec.x().doubleValue();
		this.y = y;
		this.x = x;
		this.w = 0;
		return this;
	}
	
	@Override
	public Double dot(IVector4<? extends Number> vec) {
		return this.x * vec.x().doubleValue() + this.y * vec.y().doubleValue() + this.z * vec.z().doubleValue() + this.w * vec.w().doubleValue();
	}
	
	@Override
	public Double length() {
		return Math.sqrt(this.lengthSqr());
	}

	@Override
	public Double lengthSqr() {
		return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
	}

	@Override
	public Vec4d normalizeI() {
		double f = this.length();
		if (f == 0) throw new ArithmeticException("division trough zero, cant normalize vector of length 0");
		return this.divI(f);
	}

	@Override
	public Vec4d tryNormalizeI() {
		double f = this.length();
		if (f == 0) return this.setI(0D, 0D, 0D, 0D);
		return this.divI(f);
	}
	
	@Override
	public Vec4d lerp(IVector4<? extends Number> vec, Double delta) {
		double f = 1.0F - delta;
		return new Vec4d(
				this.x * f + vec.x().doubleValue() * delta,
				this.y * f + vec.y().doubleValue() * delta,
				this.z * f + vec.z().doubleValue() * delta,
				this.w * f + vec.w().doubleValue() * delta
			);
	}

	@Override
	public Double distSqr(IVector4<? extends Number> vec) {
		Double d1 = this.getX().doubleValue() - vec.getX().doubleValue();
		Double d2 = this.getY().doubleValue() - vec.getY().doubleValue();
		Double d3 = this.getZ().doubleValue() - vec.getZ().doubleValue();
		Double d4 = this.getW().doubleValue() - vec.getW().doubleValue();
		return d1 * d1 + d2 * d2 + d3 * d3 + d4 * d4;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vec4d) {
			return	((Vec4d) obj).x == x && 
					((Vec4d) obj).y == y &&
					((Vec4d) obj).z == z &&
					((Vec4d) obj).w == w;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = result * 31 + Double.hashCode(this.x);
		result = result * 31 + Double.hashCode(this.y);
		result = result * 31 + Double.hashCode(this.z);
		result = result * 31 + Double.hashCode(this.w);
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("[%f  %f  %f  %f]", this.x, this.y, this.z, this.w);
	}

}
