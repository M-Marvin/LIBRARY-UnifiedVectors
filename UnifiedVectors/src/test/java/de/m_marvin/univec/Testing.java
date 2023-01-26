package de.m_marvin.univec;

import de.m_marvin.univec.impl.Vec3d;

public class Testing {
	
	public static void main(String... args) {
		
		System.out.println("Testing ...");
		
		TestVec test = new ITest(2, 3, 5);
		
		Vec3d vec = Vec3d.fromVec(test);
		
		System.out.println(vec);
		
		TestVec test2 = vec.writeTo(new TestVec(0, 0, 0));
		
		System.out.println(test2.getX() + " " + test2.getY() + " " + test2.getZ());
		
	}
	
	public static class ITest extends TestVec {

		public ITest(float x, float y, float z) {
			super(x, y, z);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	public static class TestVec {
		
		protected float x;
		protected float y;
		protected float z;
		
		public TestVec(float x, float y, float z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		public float getX() {
			return x;
		}
		
		public float getZ() {
			return z;
		}
		
		public float getY() {
			return y;
		}
				
	}
	
}
