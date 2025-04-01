package de.m_marvin.unimat;

import de.m_marvin.unimat.impl.Matrix3d;
import de.m_marvin.unimat.impl.Matrix3f;
import de.m_marvin.unimat.impl.Matrix4d;
import de.m_marvin.unimat.impl.Matrix4f;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.impl.Vec3d;
import de.m_marvin.univec.impl.Vec3f;

/**
 * Contains different utility functions that might be useful
 */
public class MatUtil {

	private MatUtil() {}
	
	public static Matrix4d perspectiveD(double fovDegrees, double aspect, double near, double far) {
		double frustumLength = far - near;
		double yScale = (double) ((1 / Math.tan(Math.toRadians(fovDegrees / 2))) * aspect);
		double xScale = yScale / aspect;
		Matrix4d matrix4f = new Matrix4d();
		matrix4f.m00 = xScale;
		matrix4f.m11 = yScale;
		matrix4f.m22 = -((far + near) / frustumLength);
		matrix4f.m23 = -1;
		matrix4f.m32 = -((2 * near * far) / frustumLength);
		matrix4f.m33 = 0;
		return matrix4f;
	}

	public static Matrix4d orthographic(double left, double right, double bottom, double top, double near, double far) {
		Matrix4d matrix4f = new Matrix4d();
		double f = right - left;
		double f1 = bottom - top;
		double f2 = far - near;
		matrix4f.m00 = 2.0F / f;
		matrix4f.m11 = 2.0F / f1;
		matrix4f.m22 = 2.0F / f2;
		matrix4f.m03 = -(right + left) / f;
		matrix4f.m13 = -(bottom + top) / f1;
		matrix4f.m23 = -(far + near) / f2;
		matrix4f.m33 = 1.0F;
		return matrix4f;
	}

	public static Matrix4d scaleMatrixD(double sx, double sy, double sz) {
		return new Matrix4d(
				sx, 0, 0, 0,
				0, sy, 0, 0,
				0, 0, sz, 0,
				0, 0, 0, 1
				);
	}

	public static Matrix4d translateMatrixD(double x, double y, double z) {
		return new Matrix4d(
				1, 0, 0, x,
				0, 1, 0, y,
				0, 0, 1, z,
				0, 0, 0, 1
				);
	}

	public static Matrix4d translateMatrixD(IVector3<Double> vec) {
		return new Matrix4d(
				1, 0, 0, vec.x(),
				0, 1, 0, vec.y(),
				0, 0, 1, vec.z(),
				0, 0, 0, 1
				);
	}

	public static Matrix4d rotationMatrixXD(double radians) {
		double crad = (double) Math.cos(radians);
		double srad = (double) Math.sin(radians);
		return new Matrix4d(
				1, 0, 0, 0,
				0, crad, srad, 0,
				0, -srad, crad, 0,
				0, 0, 0, 1
				);
	}

	public static Matrix4d rotationMatrixYD(double radians) {
		double crad = (double) Math.cos(radians);
		double srad = (double) Math.sin(radians);
		return new Matrix4d(
				crad, 0, -srad, 0,
				0, 1, 0, 0,
				srad, 0, crad, 0,
				0, 0, 0, 1
				);
	}

	public static Matrix4d rotationMatrixZD(double radians) {
		double crad = (double) Math.cos(radians);
		double srad = (double) Math.sin(radians);
		return new Matrix4d(
				crad, -srad, 0, 0,
				srad, crad, 0, 0,
				0, 0, 1, 0,
				0, 0, 0, 1
				);
	}
	
	public static Matrix4d rotationMatrixD(double rx, double ry, double rz) {
		return rotationMatrixZD(rz).mul(rotationMatrixYD(ry)).mul(rotationMatrixXD(rx));
	}

	public static Matrix4d rotationMatrixD(IVector3<Double> rotation) {
		return rotationMatrixD(rotation.x(), rotation.y(), rotation.z());
	}

	public static Matrix4f perspectiveF(float fovDegrees, float aspect, float near, float far) {
		float frustumLength = far - near;
		float yScale = (float) ((1 / Math.tan(Math.toRadians(fovDegrees / 2))) * aspect);
		float xScale = yScale / aspect;
		Matrix4f matrix4f = new Matrix4f();
		matrix4f.m00 = xScale;
		matrix4f.m11 = yScale;
		matrix4f.m22 = -((far + near) / frustumLength);
		matrix4f.m23 = -1;
		matrix4f.m32 = -((2 * near * far) / frustumLength);
		matrix4f.m33 = 0;
		return matrix4f;
	}

	public static Matrix4f orthographicF(float left, float right, float bottom, float top, float near, float far) {
		Matrix4f matrix4f = new Matrix4f();
		float f = right - left;
		float f1 = bottom - top;
		float f2 = far - near;
		matrix4f.m00 = 2.0F / f;
		matrix4f.m11 = 2.0F / f1;
		matrix4f.m22 = 2.0F / f2;
		matrix4f.m03 = -(right + left) / f;
		matrix4f.m13 = -(bottom + top) / f1;
		matrix4f.m23 = -(far + near) / f2;
		matrix4f.m33 = 1.0F;
		return matrix4f;
	}

	public static Matrix4f scaleMatrixF(float sx, float sy, float sz) {
		return new Matrix4f(
				sx, 0, 0, 0,
				0, sy, 0, 0,
				0, 0, sz, 0,
				0, 0, 0, 1
				);
	}

	public static Matrix4f translateMatrixF(float x, float y, float z) {
		return new Matrix4f(
				1, 0, 0, x,
				0, 1, 0, y,
				0, 0, 1, z,
				0, 0, 0, 1
				);
	}

	public static Matrix4f translateMatrixF(IVector3<Float> vec) {
		return new Matrix4f(
				1, 0, 0, vec.x(),
				0, 1, 0, vec.y(),
				0, 0, 1, vec.z(),
				0, 0, 0, 1
				);
	}

	public static Matrix4f rotationMatrixXF(float radians) {
		float crad = (float) Math.cos(radians);
		float srad = (float) Math.sin(radians);
		return new Matrix4f(
				1, 0, 0, 0,
				0, crad, srad, 0,
				0, -srad, crad, 0,
				0, 0, 0, 1
				);
	}

	public static Matrix4f rotationMatrixYF(float radians) {
		float crad = (float) Math.cos(radians);
		float srad = (float) Math.sin(radians);
		return new Matrix4f(
				crad, 0, -srad, 0,
				0, 1, 0, 0,
				srad, 0, crad, 0,
				0, 0, 0, 1
				);
	}

	public static Matrix4f rotationMatrixZF(float radians) {
		float crad = (float) Math.cos(radians);
		float srad = (float) Math.sin(radians);
		return new Matrix4f(
				crad, -srad, 0, 0,
				srad, crad, 0, 0,
				0, 0, 1, 0,
				0, 0, 0, 1
				);
	}
	
	public static Matrix4f rotationMatrixF(float rx, float ry, float rz) {
		return rotationMatrixZF(rz).mul(rotationMatrixYF(ry)).mul(rotationMatrixXF(rx));
	}

	public static Matrix4f rotationMatrixF(IVector3<Float> rotation) {
		return rotationMatrixF(rotation.x(), rotation.y(), rotation.z());
	}

	public static Matrix3d createScaleMatrixD(double sx, double sy, double sz) {
		return new Matrix3d(
				sx, 0, 0,
				0, sy, 0,
				0, 0, sz
				);
	}
	
	public static Matrix3d createScaleMatrixD(double sx, double sy) {
		return new Matrix3d(
				sx, 0, 0,
				0, sy, 0,
				0, 0, 1
				);
	}
	
	public static Matrix3d createTranslationMatrixD(double x, double y) {
		return new Matrix3d(
				1, 0, x,
				0, 1, y,
				0, 0, 1
				);
	}

	public static Matrix3f createScaleMatrixF(float sx, float sy, float sz) {
		return new Matrix3f(
				sx, 0, 0,
				0, sy, 0,
				0, 0, sz
				);
	}
	
	public static Matrix3f createScaleMatrixF(float sx, float sy) {
		return new Matrix3f(
				sx, 0, 0,
				0, sy, 0,
				0, 0, 1
				);
	}
	
	public static Matrix3f createTranslationMatrixF(float x, float y) {
		return new Matrix3f(
				1, 0, x,
				0, 1, y,
				0, 0, 1
				);
	}

	public static Matrix3f rotationMatrix3XF(float radians) {
		float crad = (float) Math.cos(radians);
		float srad = (float) Math.sin(radians);
		return new Matrix3f(
				1, 0, 0,
				0, crad, srad,
				0, -srad, crad
				);
	}

	public static Matrix3f rotationMatrix3YF(float radians) {
		float crad = (float) Math.cos(radians);
		float srad = (float) Math.sin(radians);
		return new Matrix3f(
				crad, 0, -srad,
				0, 1, 0,
				srad, 0, crad
				);
	}

	public static Matrix3f rotationMatrix3ZF(float radians) {
		float crad = (float) Math.cos(radians);
		float srad = (float) Math.sin(radians);
		return new Matrix3f(
				crad, -srad, 0,
				srad, crad, 0,
				0, 0, 1
				);
	}
	
	public static Matrix3f rotationMatrix3F(float rx, float ry, float rz) {
		return rotationMatrix3ZF(rz).mul(rotationMatrix3YF(ry)).mul(rotationMatrix3XF(rx));
	}

	public static Matrix3f rotationMatrix3F(IVector3<Float> rotation) {
		return rotationMatrix3F(rotation.x(), rotation.y(), rotation.z());
	}
	
	public static Matrix3d rotationMatrix3XD(double radians) {
		double crad = (double) Math.cos(radians);
		double srad = (double) Math.sin(radians);
		return new Matrix3d(
				1, 0, 0,
				0, crad, srad,
				0, -srad, crad
				);
	}

	public static Matrix3d rotationMatrix3YD(double radians) {
		double crad = (double) Math.cos(radians);
		double srad = (double) Math.sin(radians);
		return new Matrix3d(
				crad, 0, -srad,
				0, 1, 0,
				srad, 0, crad
				);
	}

	public static Matrix3d rotationMatrix3ZD(double radians) {
		double crad = (double) Math.cos(radians);
		double srad = (double) Math.sin(radians);
		return new Matrix3d(
				crad, -srad, 0,
				srad, crad, 0,
				0, 0, 1
				);
	}
	
	public static Matrix3d rotationMatrix3D(double rx, double ry, double rz) {
		return rotationMatrix3ZD(rz).mul(rotationMatrix3YD(ry)).mul(rotationMatrix3XD(rx));
	}

	public static Matrix3d rotationMatrix3D(IVector3<Double> rotation) {
		return rotationMatrix3D(rotation.x(), rotation.y(), rotation.z());
	}

	public static void decomposeD(Vec3d translation, Vec3d scale, Matrix3d rotation, Matrix4d matrix) {
		translation.setI(matrix.m03, matrix.m13, matrix.m23);
		scale.x = new Vec3d(matrix.m00, matrix.m01, matrix.m02).length();
		scale.y = new Vec3d(matrix.m10, matrix.m11, matrix.m12).length();
		scale.z = new Vec3d(matrix.m20, matrix.m21, matrix.m22).length();
		rotation.m00 = matrix.m00 / scale.x; rotation.m01 = matrix.m01 / scale.x; rotation.m02 = matrix.m02 / scale.x;
		rotation.m10 = matrix.m10 / scale.y; rotation.m11 = matrix.m11 / scale.y; rotation.m12 = matrix.m12 / scale.y;
		rotation.m20 = matrix.m20 / scale.z; rotation.m21 = matrix.m21 / scale.z; rotation.m22 = matrix.m22 / scale.z;
	}

	public static Vec3d extractTranslationD(Matrix4d matrix) {
		return new Vec3d(matrix.m03, matrix.m13, matrix.m23);
	}

	public static void decomposeF(Vec3f translation, Vec3f scale, Matrix3f rotation, Matrix4f matrix) {
		translation.setI(matrix.m03, matrix.m13, matrix.m23);
		scale.x = new Vec3f(matrix.m00, matrix.m01, matrix.m02).length();
		scale.y = new Vec3f(matrix.m10, matrix.m11, matrix.m12).length();
		scale.z = new Vec3f(matrix.m20, matrix.m21, matrix.m22).length();
		rotation.m00 = matrix.m00 / scale.x; rotation.m01 = matrix.m01 / scale.x; rotation.m02 = matrix.m02 / scale.x;
		rotation.m10 = matrix.m10 / scale.y; rotation.m11 = matrix.m11 / scale.y; rotation.m12 = matrix.m12 / scale.y;
		rotation.m20 = matrix.m20 / scale.z; rotation.m21 = matrix.m21 / scale.z; rotation.m22 = matrix.m22 / scale.z;
	}
	
	public static Vec3f extractTranslationF(Matrix4f matrix) {
		return new Vec3f(matrix.m03, matrix.m13, matrix.m23);
	}
	
}
