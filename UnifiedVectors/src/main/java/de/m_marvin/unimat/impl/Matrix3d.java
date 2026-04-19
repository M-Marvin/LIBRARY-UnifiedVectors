package de.m_marvin.unimat.impl;

import java.util.Objects;

import de.m_marvin.unimat.api.IMatrix;
import de.m_marvin.unimat.api.IQuaternion;
import de.m_marvin.univec.impl.Vec2d;
import de.m_marvin.univec.impl.Vec3d;

public class Matrix3d extends BaseDoubleMatrix<Matrix3d> {
	
	public Matrix3d() {
		super(3, 3, false);
	}
	
	public Matrix3d(Matrix3f mat) {
		this();
		setI(mat);
	}
	
	public Matrix3d(
			double m00, double m10, double m20,
			double m01, double m11, double m21,
			double m02, double m12, double m22
	) {
		super(new double[][] {
			new double[] { m00, m10, m20 },
			new double[] { m01, m11, m21 },
			new double[] { m02, m12, m22 }
		});
	}
	
	public double m00() {
		return m(0, 0);
	}

	public double m10() {
		return m(1, 0);
	}

	public double m20() {
		return m(2, 0);
	}
	
	public double m01() {
		return m(0, 1);
	}

	public double m11() {
		return m(1, 1);
	}

	public double m21() {
		return m(2, 1);
	}
	
	public double m02() {
		return m(0, 2);
	}

	public double m12() {
		return m(1, 2);
	}

	public double m22() {
		return m(2, 2);
	}

	@Override
	protected Matrix3d newMatrix(int width, int height, boolean sparse) {
		assert sparse == false : "matrix can not be sparse";
		assert width == 3 && height == 3 : "matrix dimensions have to be 3x3";
		return new Matrix3d();
	}
	
	public MatrixNd getGeneric() {
		return new MatrixNd(get2DArray());
	}
	
	public static Matrix3d scale(Vec3d vec) {
		return new Matrix3d(
				vec.x,	0,		0,
				0,		vec.y, 	0,
				0,		0,		vec.z
		);
	}
	
	public static Matrix3d rotationX(double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		return new Matrix3d(
				1,		0,		0,
				0,		cos,	sin,
				0,		-sin,	cos
		);
	}

	public static Matrix3d rotationY(double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		return new Matrix3d(
				cos,	0,		-sin,
				0,		1,		0,
				sin,	0,		cos
		);
	}
	
	public static Matrix3d rotationZ(double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		return new Matrix3d(
				cos,	-sin,	0,
				sin,	cos,	0,
				0,		0,		1
		);
	}

	public static Matrix3d rotation(IQuaternion<? extends Number> quat) {
		double qi = quat.i().doubleValue();
		double qj = quat.j().doubleValue();
		double qk = quat.k().doubleValue();
		double qr = quat.r().doubleValue();
		double qi2 = 2.0F * qi * qi;
		double qj2 = 2.0F * qj * qj;
		double qk2 = 2.0F * qk * qk;
		double qij = qi * qj;
		double qjk = qj * qk;
		double qki = qk * qi;
		double qir = qi * qr;
		double qjr = qj * qr;
		double qkr = qk * qr;
		return new Matrix3d(
				1.0F - qj2 - qk2,		2.0F * (qij + qkr),		2.0F * (qki - qjr),
				2.0F * (qij - qkr),		1.0F - qk2 - qi2,		2.0F * (qjk + qir),
				2.0F * (qki + qjr),		2.0F * (qjk - qir),		1.0F - qi2 - qj2
		);
	}
	
	public static Matrix3d translation(Vec2d vec) {
		return new Matrix3d(
				1,		0,		vec.x,
				0,		1, 		vec.y,
				0,		0,		1
		);
	}
	
	public static Matrix3d scale(Vec2d vec) {
		return new Matrix3d(
				vec.x,	0,		0,
				0,		vec.y, 	0,
				0,		0,		1
		);
	}

	public static void decompose(Vec3d scale, IMatrix<Double> rotation, IMatrix<Double> matrix) {
		Objects.requireNonNull(matrix, "matrix can not be null");
		if (rotation != null && (rotation.width() != 3 || rotation.height() != 3))
			throw new IllegalArgumentException("rotation matrix must be 3x3");
		if ((matrix.width() != 4 || matrix.height() != 4) && (matrix.width() != 3 || matrix.height() != 3))
			throw new IllegalArgumentException("input matrix must be 3x3 or 4x4");
		
		if (scale != null || rotation != null) {
			if (scale == null)
				scale = new Vec3d();
			scale.setX(new Vec3d(matrix.m(0, 0).doubleValue(), matrix.m(1, 0).doubleValue(), matrix.m(2, 0).doubleValue()).length());
			scale.setY(new Vec3d(matrix.m(0, 1).doubleValue(), matrix.m(1, 1).doubleValue(), matrix.m(2, 1).doubleValue()).length());
			scale.setZ(new Vec3d(matrix.m(0, 2).doubleValue(), matrix.m(1, 2).doubleValue(), matrix.m(2, 2).doubleValue()).length());
		}
		
		if (rotation != null) {
			rotation.set(0, 1, matrix.m(0, 1).doubleValue() / scale.y());
			rotation.set(0, 0, matrix.m(0, 0).doubleValue() / scale.x());
			rotation.set(0, 2, matrix.m(0, 2).doubleValue() / scale.z());
			rotation.set(1, 0, matrix.m(1, 0).doubleValue() / scale.x());
			rotation.set(1, 1, matrix.m(1, 1).doubleValue() / scale.y());
			rotation.set(1, 2, matrix.m(1, 2).doubleValue() / scale.z());
			rotation.set(2, 0, matrix.m(2, 0).doubleValue() / scale.x());
			rotation.set(2, 1, matrix.m(2, 1).doubleValue() / scale.y());
			rotation.set(2, 2, matrix.m(2, 2).doubleValue() / scale.z());
		}
	}

	public static void decompose(Vec3d scale, Quaterniond rotation, IMatrix<Double> matrix) {
		Matrix3d rotationMatrix = rotation != null ? new Matrix3d() : null;
		decompose(scale, rotationMatrix, matrix);
		
		if (rotation != null) {

			double trace = rotationMatrix.trace();
			
			if (trace > 0) {
				
				double w2 = Math.sqrt(1 + trace);
				double w4 = w2 * 2;
				double x = (rotationMatrix.m21() - rotationMatrix.m12()) / w4;
				double y = (rotationMatrix.m02() - rotationMatrix.m20()) / w4;
				double z = (rotationMatrix.m10() - rotationMatrix.m01()) / w4;
				rotation.setI(x, y, z, w2 * 0.5);
				
			} else if (rotationMatrix.m00() > rotationMatrix.m11() && rotationMatrix.m00() > rotationMatrix.m22()) {

				double x2 = Math.sqrt(1 + rotationMatrix.m00() - rotationMatrix.m11() - rotationMatrix.m22());
				double x4 = x2 * 2;
				double w = (rotationMatrix.m21() - rotationMatrix.m12()) / x4;
				double y = (rotationMatrix.m01() + rotationMatrix.m10()) / x4;
				double z = (rotationMatrix.m02() + rotationMatrix.m20()) / x4;
				rotation.setI(x2 * 0.5, y, z, w);
				
			} else if (rotationMatrix.m11() > rotationMatrix.m00() && rotationMatrix.m11() > rotationMatrix.m22()) {

				double y2 = Math.sqrt(1 + rotationMatrix.m11() - rotationMatrix.m00() - rotationMatrix.m22());
				double y4 = y2 * 2;
				double w = (rotationMatrix.m02() - rotationMatrix.m20()) / y4;
				double x = (rotationMatrix.m01() + rotationMatrix.m10()) / y4;
				double z = (rotationMatrix.m12() + rotationMatrix.m21()) / y4;
				rotation.setI(x, y2 * 0.5, z, w);
				
			} else {

				double z2 = Math.sqrt(1 + rotationMatrix.m22() - rotationMatrix.m00() - rotationMatrix.m11());
				double z4 = z2 * 2;
				double w = (rotationMatrix.m10() - rotationMatrix.m01()) / z4;
				double x = (rotationMatrix.m02() + rotationMatrix.m20()) / z4;
				double y = (rotationMatrix.m12() + rotationMatrix.m21()) / z4;
				rotation.setI(x, y, z2 * 0.5, w);
				
			}
			
			rotation.normalizeI();
			
		}
		
	}
	
}
