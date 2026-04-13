package de.m_marvin.univec.impl;

import de.m_marvin.univec.VectorParser;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector2Math;

/*
 * Implementation of a 2 dimensional integer vector
 */
public class Vec2i implements IVector2Math<Integer, Vec2i> {
	
	public int x;
	public int y;
	
	public Vec2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vec2i() {
		this.x = 0;
		this.y = 0;
	}

	public Vec2i(IVector2<? extends Number> vec) {
		this.x = vec.x().intValue();
		this.y = vec.y().intValue();
	}

	@Override
	public Class<? extends Number> getTypeClass() {
		return Integer.class;
	}

	public static Vec2i fromVec(Object vectorObject) {
		return new Vec2i(0, 0).readFrom(vectorObject);
	}
	
	@Override
	public <T> Vec2i readFrom(T vectorObject) {
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
	public void setX(Integer x) {
		this.x = x;
	}

	@Override
	public void setY(Integer y) {
		this.y = y;
	}

	@Override
	public Vec2i setI(Integer x, Integer y) {
		this.x = x;
		this.y = y;
		return this;
	}

	@Override
	public Vec2i setI(IVector2<? extends Number> vec) {
		this.x = vec.x().intValue();
		this.y = vec.y().intValue();
		return this;
	}

	@Override
	public Vec2i reset() {
		this.x = this.y = 0;
		return this;
	}
	
	@Override
	public Vec2i copy() {
		return new Vec2i(this.x, this.y);
	}

	@Override
	public Vec2i addI(IVector2<? extends Number> vec) {
		this.x += vec.x().doubleValue();
		this.y += vec.y().doubleValue();
		return this;
	}

	@Override
	public Vec2i addI(Integer x, Integer y) {
		this.x += x;
		this.y += y;
		return this;
	}

	@Override
	public Vec2i subI(IVector2<? extends Number> vec) {
		this.x -= vec.x().doubleValue();
		this.y -= vec.y().doubleValue();
		return this;
	}

	@Override
	public Vec2i subI(Integer x, Integer y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	@Override
	public Vec2i mulI(IVector2<? extends Number> vec) {
		this.x *= vec.x().doubleValue();
		this.y *= vec.y().doubleValue();
		return this;
	}

	@Override
	public Vec2i mulI(Integer x, Integer y) {
		this.x *= x;
		this.y *= y;
		return this;
	}

	@Override
	public Vec2i mulI(Integer n) {
		this.x *= n;
		this.y *= n;
		return this;
	}
	
	@Override
	public Vec2i divI(IVector2<? extends Number> vec) {
		this.x /= vec.x().doubleValue();
		this.y /= vec.y().doubleValue();
		return this;
	}

	@Override
	public Vec2i divI(Integer x, Integer y) {
		this.x /= x;
		this.y /= y;
		return this;
	}
	
	@Override
	public Vec2i divI(Integer n) {
		this.x /= n;
		this.y /= n;
		return this;
	}

	@Override
	public Vec2i moduleI(Integer m) {
		this.x %= m;
		this.y %= m;
		return this;
	}

	@Override
	public Vec2i clampI(IVector2<? extends Number> min, IVector2<? extends Number> max) {
		this.x = (int) Math.max(min.x().doubleValue(), Math.min(this.x, max.x().doubleValue()));
		this.y = (int) Math.max(min.y().doubleValue(), Math.min(this.y, max.y().doubleValue()));
		return this;
	}

	@Override
	public Vec2i minI(Integer value) {
		this.x = Math.min(this.x, value);
		this.y = Math.min(this.y, value);
		return this;
	}
	
	@Override
	public Vec2i minI(IVector2<? extends Number> vec) {
		this.x = (int) Math.min(this.x,  vec.x().doubleValue());
		this.y = (int) Math.min(this.y,  vec.y().doubleValue());
		return this;
	}
	
	@Override
	public Vec2i maxI(Integer value) {
		this.x = Math.max(this.x, value);
		this.y = Math.max(this.y, value);
		return this;
	}
	
	@Override
	public Vec2i maxI(IVector2<? extends Number> vec) {
		this.x = (int) Math.max(this.x,  vec.x().doubleValue());
		this.y = (int) Math.max(this.y,  vec.y().doubleValue());
		return this;
	}
	
	@Override
	public Vec2i clampI(Integer min, Integer max) {
		this.x = Math.max((Integer) min, Math.min(this.x, (Integer) max));
		this.y = Math.max((Integer) min, Math.min(this.y, (Integer) max));
		return this;
	}

	@Override
	public Vec2i absI() {
		this.x = Math.abs(this.x);
		this.y = Math.abs(this.y);
		return this;
	}

	@Override
	public Vec2i negateI() {
		this.x = -this.x;
		this.y = -this.y;
		return this;
	}
	
	@Override
	public Integer sum() {
		return this.x + this.y;
	}
	
	@Override
	public Vec2i signI() {
		this.x = this.x > 0 ? 1 : this.x < 0 ? -1 : 0;
		this.y = this.y > 0 ? 1 : this.y < 0 ? -1 : 0;
		return this;
	}

	@Override
	public Vec2i floorI() {
		this.x = (int) Math.floor(this.x);
		this.y = (int) Math.floor(this.y);
		return this;
	}

	@Override
	public Vec2i ceilI() {
		this.x = (int) Math.ceil(this.x);
		this.y = (int) Math.ceil(this.y);
		return this;
	}

	@Override
	public Vec2i roundI() {
		this.x = Math.round(this.x);
		this.y = Math.round(this.y);
		return this;
	}
	
	@Override
	public boolean isFinite() {
		return true;
	}
	
	@Override
	public double angle(IVector2<? extends Number> vec) {
		return Math.atan2(this.y, this.x)-Math.atan2(vec.y().intValue(), vec.x().intValue());
	}
	
	@Override
	public Integer cross(IVector2<? extends Number> vec) {
		return this.x * vec.y().intValue() - this.y * vec.x().intValue();	
	}
	
	@Override
	public Integer dot(IVector2<? extends Number> vec) {
		return this.x * vec.x().intValue() + this.y * vec.y().intValue();
	}
	
	@Override
	public Integer length() {
		return (int) Math.sqrt(this.lengthSqr());
	}

	@Override
	public Integer lengthSqr() {
		return this.x * this.x + this.y * this.y;
	}

	@Override
	public Vec2i normalizeI() {
		int f = this.length();
		if (f == 0) throw new ArithmeticException("division trough zero, cant normalize vector of length 0");
		return this.divI(f);
	}

	@Override
	public Vec2i tryNormalizeI() {
		int f = this.length();
		if (f == 0) return this.setI(0, 0);
		return this.divI(f);
	}
	
	@Override
	public Vec2i lerp(IVector2<? extends Number> vec, Integer delta) {
		float f = 1.0F - delta;
		return new Vec2i(
				(int) (this.x * f + vec.x().intValue() * delta),
				(int) (this.y * f + vec.y().intValue() * delta)
			);
	}

	@Override
	public Integer distSqr(IVector2<? extends Number> vec) {
		Integer d1 = this.getX().intValue() - vec.getX().intValue();
		Integer d2 = this.getY().intValue() - vec.getY().intValue();
		return d1 * d1 + d2 * d2;
	}
	
	@Override
	public Integer dist(IVector2<? extends Number> vec) {
		 return (int) Math.sqrt(this.distSqr(vec).intValue());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vec2i) {
			return	((Vec2i) obj).x == x && 
					((Vec2i) obj).y == y;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = result * 31 + Integer.hashCode(this.x);
		result = result * 31 + Integer.hashCode(this.y);
		return result;
	}

	@Override
	public Vec2i anyOrthogonal() {
		return new Vec2i(-y, x);
	}
	
	@Override
	public Vec2i[] orthogonals() {
		return new Vec2i[] {new Vec2i(-y, x), new Vec2i(y, -x)};
	}
	
	@Override
	public String toString() {
		return String.format("[%d  %d]", this.x, this.y);
	}

}
