package de.m_marvin.univec.impl;

import de.m_marvin.univec.api.IMatrix;

public class Matrix4f implements IMatrix<Float> {

	public float m00;
	public float m01;
	public float m02;
	public float m03;
	public float m10;
	public float m11;
	public float m12;
	public float m13;
	public float m20;
	public float m21;
	public float m22;
	public float m23;
	public float m30;
	public float m31;
	public float m32;
	public float m33;
	
	public Matrix4f() {
	}
	
	public Matrix4f(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20,
			float m21, float m22, float m23, float m30, float m31, float m32, float m33) {
		this.m00 = m00;
		this.m01 = m01;
		this.m02 = m02;
		this.m03 = m03;
		this.m10 = m10;
		this.m11 = m11;
		this.m12 = m12;
		this.m13 = m13;
		this.m20 = m20;
		this.m21 = m21;
		this.m22 = m22;
		this.m23 = m23;
		this.m30 = m30;
		this.m31 = m31;
		this.m32 = m32;
		this.m33 = m33;
	}

	@Override
	public Float get(int x, int y) {
		switch (x) {
		case 0:
			switch (y) {
			case 0: return m00;
			case 1: return m01;
			case 2: return m02;
			case 3: return m03;
			}
		case 1:
			switch (y) {
			case 0: return m10;
			case 1: return m11;
			case 2: return m12;
			case 3: return m13;
			}
		case 2:
			switch (y) {
			case 0: return m20;
			case 1: return m21;
			case 2: return m22;
			case 3: return m23;
			}
		case 3:
			switch (y) {
			case 0: return m30;
			case 1: return m31;
			case 2: return m32;
			case 3: return m33;
			}
		}
		throw new IndexOutOfBoundsException();
	}
	
	@Override
	public void set(int x, int y, Float value) {
		switch (x) {
		case 0:
			switch (y) {
			case 0: m00 = value; break;
			case 1: m01 = value; break;
			case 2: m02 = value; break;
			case 3: m03 = value; break;
			}
		case 1:
			switch (y) {
			case 0: m10 = value; break;
			case 1: m11 = value; break;
			case 2: m12 = value; break;
			case 3: m13 = value; break;
			}
		case 2:
			switch (y) {
			case 0: m20 = value; break;
			case 1: m21 = value; break;
			case 2: m22 = value; break;
			case 3: m23 = value; break;
			}
		case 3:
			switch (y) {
			case 0: m30 = value; break;
			case 1: m31 = value; break;
			case 2: m32 = value; break;
			case 3: m33 = value; break;
			}
		}
		throw new IndexOutOfBoundsException();
	}
	
}
