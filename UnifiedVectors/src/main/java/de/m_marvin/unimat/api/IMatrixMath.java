package de.m_marvin.unimat.api;

import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector4;

/**
 * Advanced matrix operations.<be>
 * These define are all the operations that can be done to matrices, including conversion to and from vectors.
 * @param <N> The data type of the numerical values
 * @param <MO> The data type of the resulting matrices of operations
 * @param <VO2> The data type of the 2D resulting vectors of operations
 * @param <VO3> The data type of the 3D resulting vectors of operations
 * @param <VO4> The data type of the 4D resulting vectors of operations
 */
public interface IMatrixMath<N extends Number, MO extends IMatrix<N>, VO2 extends IVector2<N>, VO3 extends IVector3<N>, VO4 extends IVector4<N>> extends IMatrix<N> {
	
	public MO setI(IMatrix<? extends Number> mat);
	
	public MO copy();
	
	public MO setRowVecI(IVector2<? extends Number> vec);
	public MO setRowVecI(IVector3<? extends Number> vec);
	public MO setRowVecI(IVector4<? extends Number> vec);
	public MO setColVecI(IVector2<? extends Number> vec);
	public MO setColVecI(IVector3<? extends Number> vec);
	public MO setColVecI(IVector4<? extends Number> vec);
	
	public VO2 getRowVec2();
	public VO3 getRowVec3();
	public VO4 getRowVec4();
	public VO2 getColVec2();
	public VO3 getColVec3();
	public VO4 getColVec4();
	
	public VO2 transformVec2(IVector2<? extends Number> vec);
	public VO2 transformVec2(IVector3<? extends Number> vec);
	public VO2 transformVec2(IVector4<? extends Number> vec);
	public VO3 transformVec3(IVector2<? extends Number> vec);
	public VO3 transformVec3(IVector3<? extends Number> vec);
	public VO3 transformVec3(IVector4<? extends Number> vec);
	public VO4 transformVec4(IVector2<? extends Number> vec);
	public VO4 transformVec4(IVector3<? extends Number> vec);
	public VO4 transformVec4(IVector4<? extends Number> vec);
	
	public MO mul(IMatrix<? extends Number> mat);
	public default MO mulI(IMatrix<? extends Number> mat) {
		if (!isSquare() && !mat.isSquare())
			throw new IllegalArgumentException("self multiplication not supported for non square matrices");
		
		return setI( mul(mat));
	}
	
	public MO add(IMatrix<? extends Number> mat);
	public default MO addI(IMatrix<? extends Number> mat) { return setI( add(mat)); }
	
	public MO sub(IMatrix<? extends Number> mat);
	public default MO subI(IMatrix<? extends Number> mat) { return setI( sub(mat)); }
	
	public MO scalarDiv(IMatrix<? extends Number> mat);
	public default MO scalarDivI(IMatrix<? extends Number> mat) { return setI( scalarDiv(mat)); }
	
	public MO scalarDiv(N n);
	public default MO scalarDivI(N n) { return setI( scalarDiv(n)); }
	
	public MO scalarMul(IMatrix<? extends Number> mat);
	public default MO scalarMulI(IMatrix<? extends Number> mat) { return setI( scalarMul(mat)); }
	
	public MO scalarMul(N n);
	public default MO scalarMulI(N n) { return setI( scalarMul(n)); }
	
	public MO invert();
	public default MO invertI() { return setI( invert()); }
	
	public MO transpose();
	public default MO transposeI() {
		if (!isSquare())
			throw new IllegalArgumentException("self transpose not supported for non square matrices");
		
		return setI( transpose());
	}
	
	public MO adjungate();
	public default MO adjungateI() { return setI( adjungate()); }
	
	public MO identityI();

	public N determinant();

	public MO develop(int x, int y);
	public N developAndDet(int x, int y);
	
}
