package de.m_marvin.unimat.impl;

import de.m_marvin.unimat.MatrixMathException;
import de.m_marvin.unimat.api.IMatrix;
import de.m_marvin.unimat.api.IMatrixMath;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector4;
import de.m_marvin.univec.impl.Vec2d;
import de.m_marvin.univec.impl.Vec3d;
import de.m_marvin.univec.impl.Vec4d;

public abstract class BaseDoubleMatrix<M extends BaseDoubleMatrix<M>> implements IMatrix<Double>, IMatrixMath<Double, M, Vec2d, Vec3d, Vec4d> {
	
	public final Double[][] m;
	
	public BaseDoubleMatrix(int w, int h) {
		this.m = new Double[h][w];
		for (int y = 0; y < h; y++) {
			this.m[y] = new Double[w];
			for (int x = 0; x < w; x++)
				this.m[y][x] = 0.0;
		}
	}
	
	public BaseDoubleMatrix(Double[][] m) {
		if (m.length == 0)
			throw new MatrixMathException("matrix can not be empty");
		int w = m[0].length;
		if (w == 0)
			throw new MatrixMathException("matrix can not be empty");
		for (Object[] l : m)
			if (w != l.length)
				throw new MatrixMathException("matrix values do not foram an rectangular array");
		
		this.m = m;
	}
	
	protected abstract M newMatrix(int width, int height);
	
	@Override
	public Class<? extends Number> getTypeClass() {
		return Double.class;
	}

	@Override
	public int width() {
		return this.m[0].length;
	}

	@Override
	public int height() {
		return this.m.length;
	}

	@Override
	public boolean isSquare() {
		return width() == height();
	}
	
	@Override
	public Double m(int x, int y) {
		if (x < 0 || x >= width())
			throw new IndexOutOfBoundsException("matrix element index out of bounds: x " + x);
		if (y < 0 || y >= height())
			throw new IndexOutOfBoundsException("matrix element index out of bounds: y " + y);
		
		return this.m[y][x];
	}

	@Override
	public void set(int x, int y, Double m) {
		if (x < 0 || x >= width())
			throw new IndexOutOfBoundsException("matrix element index out of bounds: x " + x);
		if (y < 0 || y >= height())
			throw new IndexOutOfBoundsException("matrix element index out of bounds: y " + y);
		
		this.m[y][x] = m;
	}

	@SuppressWarnings("unchecked")
	@Override
	public M setI(IMatrix<? extends Number> mat) {
		if (this.width() != mat.height() || this.height() != mat.height())
			throw new MatrixMathException("incompatible matrix dimensions", this, mat);
		
		for (int x = 0; x < width(); x++)
			for (int y = 0; y < height(); y++)
				this.m[y][x] = mat.m(x, y).doubleValue();
		return (M) this;
	}

	public M copy() {
		return newMatrix(width(), height()).setI(this);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public M setRowVecI(IVector2<? extends Number> vec) {
		if (width() != 2 && height() != 1)
			throw new MatrixMathException("matrix does not match vector2 dimensions", this);
		set(0, 0, vec.x().doubleValue());
		set(0, 1, vec.y().doubleValue());
		return (M) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public M setRowVecI(IVector3<? extends Number> vec) {
		if (width() != 3 && height() != 1)
			throw new MatrixMathException("matrix does not match vector3 dimensions", this);
		set(0, 0, vec.x().doubleValue());
		set(0, 1, vec.y().doubleValue());
		set(0, 2, vec.z().doubleValue());
		return (M) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public M setRowVecI(IVector4<? extends Number> vec) {
		if (width() != 4 && height() != 1)
			throw new MatrixMathException("matrix does not match vector4 dimensions", this);
		set(0, 0, vec.x().doubleValue());
		set(0, 1, vec.y().doubleValue());
		set(0, 2, vec.z().doubleValue());
		set(0, 3, vec.w().doubleValue());
		return (M) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public M setColVecI(IVector2<? extends Number> vec) {
		if (width() != 1 && height() != 2)
			throw new MatrixMathException("matrix does not match vector2 dimensions", this);
		set(0, 0, vec.x().doubleValue());
		set(1, 0, vec.y().doubleValue());
		return (M) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public M setColVecI(IVector3<? extends Number> vec) {
		if (width() != 1 && height() != 3)
			throw new MatrixMathException("matrix does not match vector3 dimensions", this);
		set(0, 0, vec.x().doubleValue());
		set(1, 0, vec.y().doubleValue());
		set(2, 0, vec.z().doubleValue());
		return (M) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public M setColVecI(IVector4<? extends Number> vec) {
		if (width() != 1 && height() != 4)
			throw new MatrixMathException("matrix does not match vector4 dimensions", this);
		set(0, 0, vec.x().doubleValue());
		set(1, 0, vec.y().doubleValue());
		set(2, 0, vec.z().doubleValue());
		set(3, 0, vec.w().doubleValue());
		return (M) this;
	}

	@Override
	public Vec2d getRowVec2() {
		if (width() != 2 && height() != 1)
			throw new MatrixMathException("matrix does not match vector2 dimensions", this);
		return new Vec2d(m(0, 0), m(1, 0));
	}

	@Override
	public Vec3d getRowVec3() {
		if (width() != 3 && height() != 1)
			throw new MatrixMathException("matrix does not match vector3 dimensions", this);
		return new Vec3d(m(0, 0), m(1, 0), m(2, 0));
	}

	@Override
	public Vec4d getRowVec4() {
		if (width() != 4 && height() != 1)
			throw new MatrixMathException("matrix does not match vector4 dimensions", this);
		return new Vec4d(m(0, 0), m(1, 0), m(2, 0), m(3, 0));
	}

	@Override
	public Vec2d getColVec2() {
		if (width() != 1 && height() != 2)
			throw new MatrixMathException("matrix does not match vector2 dimensions", this);
		return new Vec2d(m(0, 0), m(0, 1));
	}

	@Override
	public Vec3d getColVec3() {
		if (width() != 1 && height() != 3)
			throw new MatrixMathException("matrix does not match vector3 dimensions", this);
		return new Vec3d(m(0, 0), m(0, 1), m(0, 2));
	}

	@Override
	public Vec4d getColVec4() {
		if (width() != 1 && height() != 4)
			throw new MatrixMathException("matrix does not match vector4 dimensions", this);
		return new Vec4d(m(0, 0), m(0, 1), m(0, 2), m(0, 3));
	}

	@Override
	public Vec2d transformVec2(IVector2<? extends Number> vec) {
		if (width() != 2 || height() != 2)
			throw new MatrixMathException("matrix does not match vector2 -> vector2 dimensions", this);
		
		return new Vec2d(
				m(0, 0) * vec.x().doubleValue() + m(1, 0) * vec.y().doubleValue(),
				m(0, 1) * vec.x().doubleValue() + m(1, 1) * vec.y().doubleValue()
		);
	}

	@Override
	public Vec2d transformVec2(IVector3<? extends Number> vec) {
		if (width() != 3 || height() != 2)
			throw new MatrixMathException("matrix does not match vector3 -> vector2 dimensions", this);
		
		return new Vec2d(
				m(0, 0) * vec.x().doubleValue() + m(1, 0) * vec.y().doubleValue() + m(2, 0) * vec.z().doubleValue(),
				m(0, 1) * vec.x().doubleValue() + m(1, 1) * vec.y().doubleValue() + m(2, 1) * vec.z().doubleValue()
		);
	}

	@Override
	public Vec2d transformVec2(IVector4<? extends Number> vec) {
		if (width() != 4 || height() != 2)
			throw new MatrixMathException("matrix does not match vector4 -> vector2 dimensions", this);
		
		return new Vec2d(
				m(0, 0) * vec.x().doubleValue() + m(1, 0) * vec.y().doubleValue() + m(2, 0) * vec.z().doubleValue() + m(3, 0) * vec.w().doubleValue(),
				m(0, 1) * vec.x().doubleValue() + m(1, 1) * vec.y().doubleValue() + m(2, 1) * vec.z().doubleValue() + m(3, 1) * vec.w().doubleValue()
		);
	}

	@Override
	public Vec3d transformVec3(IVector2<? extends Number> vec) {
		if (width() != 2 || height() != 3)
			throw new MatrixMathException("matrix does not match vector2 -> vector3 dimensions", this);
		
		return new Vec3d(
				m(0, 0) * vec.x().doubleValue() + m(1, 0) * vec.y().doubleValue(),
				m(0, 1) * vec.x().doubleValue() + m(1, 1) * vec.y().doubleValue(),
				m(0, 2) * vec.x().doubleValue() + m(1, 2) * vec.y().doubleValue()
		);
	}

	@Override
	public Vec3d transformVec3(IVector3<? extends Number> vec) {
		if (width() != 3 || height() != 3)
			throw new MatrixMathException("matrix does not match vector3 -> vector3 dimensions", this);
		
		return new Vec3d(
				m(0, 0) * vec.x().doubleValue() + m(1, 0) * vec.y().doubleValue() + m(2, 0) * vec.z().doubleValue(),
				m(0, 1) * vec.x().doubleValue() + m(1, 1) * vec.y().doubleValue() + m(2, 1) * vec.z().doubleValue(),
				m(0, 2) * vec.x().doubleValue() + m(1, 2) * vec.y().doubleValue() + m(2, 2) * vec.z().doubleValue()
		);
	}

	@Override
	public Vec3d transformVec3(IVector4<? extends Number> vec) {
		if (width() != 4 || height() != 3)
			throw new MatrixMathException("matrix does not match vector4 -> vector3 dimensions", this);
		
		return new Vec3d(
				m(0, 0) * vec.x().doubleValue() + m(1, 0) * vec.y().doubleValue() + m(2, 0) * vec.z().doubleValue() + m(3, 0) * vec.w().doubleValue(),
				m(0, 1) * vec.x().doubleValue() + m(1, 1) * vec.y().doubleValue() + m(2, 1) * vec.z().doubleValue() + m(3, 1) * vec.w().doubleValue(),
				m(0, 2) * vec.x().doubleValue() + m(1, 2) * vec.y().doubleValue() + m(2, 2) * vec.z().doubleValue() + m(3, 2) * vec.w().doubleValue()
		);
	}

	@Override
	public Vec4d transformVec4(IVector2<? extends Number> vec) {
		if (width() != 2 || height() != 4)
			throw new MatrixMathException("matrix does not match vector2 -> vector4 dimensions", this);
		
		return new Vec4d(
				m(0, 0) * vec.x().doubleValue() + m(1, 0) * vec.y().doubleValue(),
				m(0, 1) * vec.x().doubleValue() + m(1, 1) * vec.y().doubleValue(),
				m(0, 2) * vec.x().doubleValue() + m(1, 2) * vec.y().doubleValue(),
				m(0, 3) * vec.x().doubleValue() + m(1, 3) * vec.y().doubleValue()
		);
	}

	@Override
	public Vec4d transformVec4(IVector3<? extends Number> vec) {
		if (width() != 3 || height() != 4)
			throw new MatrixMathException("matrix does not match vector3 -> vector4 dimensions", this);
		
		return new Vec4d(
				m(0, 0) * vec.x().doubleValue() + m(1, 0) * vec.y().doubleValue() + m(2, 0) * vec.z().doubleValue(),
				m(0, 1) * vec.x().doubleValue() + m(1, 1) * vec.y().doubleValue() + m(2, 1) * vec.z().doubleValue(),
				m(0, 2) * vec.x().doubleValue() + m(1, 2) * vec.y().doubleValue() + m(2, 2) * vec.z().doubleValue(),
				m(0, 3) * vec.x().doubleValue() + m(1, 3) * vec.y().doubleValue() + m(2, 3) * vec.z().doubleValue()
		);
	}

	@Override
	public Vec4d transformVec4(IVector4<? extends Number> vec) {
		if (width() != 4 || height() != 4)
			throw new MatrixMathException("matrix does not match vector4 -> vector4 dimensions", this);
		
		return new Vec4d(
				m(0, 0) * vec.x().doubleValue() + m(1, 0) * vec.y().doubleValue() + m(2, 0) * vec.z().doubleValue() + m(3, 0) * vec.w().doubleValue(),
				m(0, 1) * vec.x().doubleValue() + m(1, 1) * vec.y().doubleValue() + m(2, 1) * vec.z().doubleValue() + m(3, 1) * vec.w().doubleValue(),
				m(0, 2) * vec.x().doubleValue() + m(1, 2) * vec.y().doubleValue() + m(2, 2) * vec.z().doubleValue() + m(3, 2) * vec.w().doubleValue(),
				m(0, 3) * vec.x().doubleValue() + m(1, 3) * vec.y().doubleValue() + m(2, 3) * vec.z().doubleValue() + m(3, 3) * vec.w().doubleValue()
		);
	}

	@Override
	public void addM(int x, int y, Double n) {
		if (x < 0 || x >= width())
			throw new IndexOutOfBoundsException("matrix element index out of bounds: x " + x);
		if (y < 0 || y >= height())
			throw new IndexOutOfBoundsException("matrix element index out of bounds: y " + y);
		
		this.m[y][x] += n;
	}
	
	@Override
	public void subM(int x, int y, Double n) {
		if (x < 0 || x >= width())
			throw new IndexOutOfBoundsException("matrix element index out of bounds: x " + x);
		if (y < 0 || y >= height())
			throw new IndexOutOfBoundsException("matrix element index out of bounds: y " + y);
		
		this.m[y][x] -= n;
	}
	
	@Override
	public void mulM(int x, int y, Double n) {
		if (x < 0 || x >= width())
			throw new IndexOutOfBoundsException("matrix element index out of bounds: x " + x);
		if (y < 0 || y >= height())
			throw new IndexOutOfBoundsException("matrix element index out of bounds: y " + y);
		
		this.m[y][x] *= n;
	}
	
	@Override
	public void divM(int x, int y, Double n) {
		if (x < 0 || x >= width())
			throw new IndexOutOfBoundsException("matrix element index out of bounds: x " + x);
		if (y < 0 || y >= height())
			throw new IndexOutOfBoundsException("matrix element index out of bounds: y " + y);
		
		this.m[y][x] /= n;
	}

	@Override
	public M mul(IMatrix<? extends Number> mat) {
		if (this.width() != mat.height())
			throw new MatrixMathException("incompatible matrix dimensions for multiplication", this, mat);
		
		M result = newMatrix(mat.width(), this.height());
		for (int y = 0; y < result.height(); y++)
			for (int x = 0; x < result.width(); x++)
				for (int j = 0; j < this.width(); j++)
					result.m[y][x] += this.m(j, y) * mat.m(x, j).doubleValue();
		return result;
	}
	
	@Override
	public M add(IMatrix<? extends Number> mat) {
		if (this.width() != mat.width() || this.height() != mat.height())
			throw new MatrixMathException("incompatible matrix dimensions for addition", this, mat);
		
		M m = newMatrix(width(), height());
		for (int x = 0; x < width(); x++)
			for (int y = 0; y < height(); y++)
				m.set(x, y, this.m(x, y) + mat.m(x, y).doubleValue());
		return m;
	}

	@Override
	public M sub(IMatrix<? extends Number> mat) {
		if (this.width() != mat.width() || this.height() != mat.height())
			throw new MatrixMathException("incompatible matrix dimensions for subtraction", this, mat);
		
		M m = newMatrix(width(), height());
		for (int x = 0; x < width(); x++)
			for (int y = 0; y < height(); y++)
				m.set(x, y, this.m(x, y) - mat.m(x, y).doubleValue());
		return m;
	}

	@Override
	public M scalarDiv(IMatrix<? extends Number> mat) {
		if (this.width() != mat.width() || this.height() != mat.height())
			throw new MatrixMathException("incompatible matrix dimensions for scalar division", this, mat);
		
		M m = newMatrix(width(), height());
		for (int x = 0; x < width(); x++)
			for (int y = 0; y < height(); y++)
				m.set(x, y, this.m(x, y) / mat.m(x, y).doubleValue());
		return m;
	}

	@Override
	public M scalarDiv(Double n) {
		M m = newMatrix(width(), height());
		for (int x = 0; x < width(); x++)
			for (int y = 0; y < height(); y++)
				m.set(x, y, this.m(x, y) / n);
		return m;
	}

	@Override
	public M scalarMul(IMatrix<? extends Number> mat) {
		if (this.width() != mat.width() || this.height() != mat.height())
			throw new MatrixMathException("incompatible matrix dimensions for scalar multiplication", this, mat);
		
		M m = newMatrix(width(), height());
		for (int x = 0; x < width(); x++)
			for (int y = 0; y < height(); y++)
				m.set(x, y, this.m(x, y) * mat.m(x, y).doubleValue());
		return m;
	}

	@Override
	public M scalarMul(Double n) {
		M m = newMatrix(width(), height());
		for (int x = 0; x < width(); x++)
			for (int y = 0; y < height(); y++)
				m.set(x, y, this.m(x, y) * n);
		return m;
	}

	@Override
	public M invert() {
		if (!isSquare())
			throw new MatrixMathException("inverse not defined for non square matrix", this);
		
		return adjungate().scalarDiv(determinant());
	}

	@Override
	public M transpose() {
		M m = newMatrix(height(), width());
		for (int x = 0; x < width(); x++)
			for (int y = 0; y < height(); y++)
				m.set(y, x, this.m(x, y));
		return m;
	}

	@Override
	public M adjungate() {
		if (!isSquare())
			throw new MatrixMathException("adjungate not defined for non square matrix", this);
		
		M m = newMatrix(width(), height());
		for (int x = 0; x < width(); x++)
			for (int y = 0; y < height(); y++)
				m.set(x, y, this.developAndDet(y, x));
		return m;
	}

	@SuppressWarnings("unchecked")
	@Override
	public M identityI() {
		if (!isSquare())
			throw new MatrixMathException("identity not defined for non square matrix", this);
		
		for (int x = 0; x < width(); x++)
			for (int y = 0; y < height(); y++)
				this.set(y, x, x == y ? 1.0 : 0.0);
		return (M) this;
	}
	
	@Override
	public Double determinant() {
		if (!isSquare())
			throw new MatrixMathException("determinant not defined for non square matrix", this);
		
		if (width() > 2) {
			// find row or column with most zeros
			int zeros = 0;
			int index = 0;
			boolean isCol = false;
			for (int i = 0; i < width(); i++) {
				int zeroesInRow = 0;
				for (int col = 0; col < width(); col++)
					if (m(col, i) == 0) zeroesInRow++;
				int zeroesInCol = 0;
				for (int row = 0; row < height(); row++)
					if (m(i, row) == 0) zeroesInCol++;
				if (zeroesInCol > zeros) {
					zeros = zeroesInCol;
					index = i;
					isCol = true;
				} else if (zeroesInRow > zeros) {
					zeros = zeroesInRow;
					index = i;
					isCol = false;
				}
				if (zeros == width()) break;
			}
			
			// generate develop matrices for each element and calculate determinant by multiplying them with their respective factors
			double det = 0.0;
			for (int i = 0; i < height(); i++) {
				double f = isCol ? this.m(index, i) : this.m(i, index);
				if (f == 0.0)
					continue;
				det += f * (isCol ? this.developAndDet(index, i) : this.developAndDet(i, index));
			}	
			return det;
		} else if (width() == 2) {
			return m(0, 0) * m(1, 1) - m(0, 1) * m(1, 0);
		} else if (width() == 1) {
			return m(0, 0);
		}
		return 0.0;
	}

	@Override
	public M develop(int x, int y) {
		if (!isSquare())
			throw new MatrixMathException("not defined for non square matrix", this);
		
		M md = newMatrix(width() - 1, height() - 1);
		for (int ix = 0; ix < width() - 1; ix++)
			for (int iy = 0; iy < width() - 1; iy++)
				md.set(ix, iy, m(ix >= x ? ix + 1 : ix, iy >= y ? iy + 1 : iy));
		return md;
	}

	@Override
	public Double developAndDet(int x, int y) {
		if (!isSquare())
			throw new MatrixMathException("not defined for non square matrix", this);
		
		return develop(x, y).determinant() * ((x + y) % 2 == 0 ? 1 : -1);
	}
	
	@Override
	public String toString() {
		int[] tw = new int[width()];
		for (int x = 0; x < width(); x++) {
			for (int y = 0; y < height(); y++) {
				double f = m(x, y);
				int w = String.format("%s%f", f > 0.0 ? "+" : "", f).length();
				if (tw[x] < w)
					tw[x] = w;
			}
		}
		StringBuffer sb = new StringBuffer();
		for (int y = 0; y < height(); y++) {
			sb.append('[');
			for (int x = 0; x < width(); x++) {
				double f = m(x, y);
				String fs = String.format("%s%f", f > 0.0 ? "+" : "", f);
				int w = tw[x] - fs.length();
				sb.append(fs);
				for (int i = 0; i < w + 4; i++)
					sb.append(' ');
			}
			sb.append("]\n");
		}
		return sb.toString();
	}
	
}
