package de.m_marvin.univec;

import de.m_marvin.unimat.api.IQuaternionMath.EulerOrder;
import de.m_marvin.unimat.impl.Quaterniond;
import de.m_marvin.univec.impl.Vec2d;
import de.m_marvin.univec.impl.Vec2f;
import de.m_marvin.univec.impl.Vec2i;
import de.m_marvin.univec.impl.Vec3d;
import de.m_marvin.univec.impl.Vec3f;
import de.m_marvin.univec.impl.Vec3i;
import de.m_marvin.univec.impl.Vec4d;

public class Testing {
	
	public static void main(String... args) {
//		
//		//VectorParser.setObfuscationResolver((clazz, field) -> Optional.of("dfsdf"));
//		
//		System.out.println("Testing ...");
//		
//		TestVec test = new ITest(2, 3, 5);
//		
//		Vec3d vec = Vec3d.fromVec(test);
//		
//		System.out.println(vec);
//
//		TestVec test2 = vec.writeTo(new TestVec(0, 0, 0));
//
//		TestVec test3 = vec.writeTo(new TestVec(0, 0, 0));
//
//		TestVec test4 = vec.writeTo(new TestVec(0, 0, 0));
//
//		TestVec test5 = vec.writeTo(new TestVec(0, 0, 0));
//		
//		System.out.println(test2.getX() + " " + test2.getY() + " " + test2.getZ());
//		System.out.println(test3.getX() + " " + test2.getY() + " " + test2.getZ());
//		System.out.println(test4.getX() + " " + test2.getY() + " " + test2.getZ());
//		System.out.println(test5.getX() + " " + test2.getY() + " " + test2.getZ());
//		System.out.println(test2.getX() + " " + test2.getY() + " " + test2.getZ());
//		
//		System.out.println(new Vec3f(1F, 2F, 3F).dist(new Vec3f(3F, 4F, 5F)));
//		
//		Vec3d vec1 = new Vec3d(23.00430, -325, 54).normalize();
//		Vec3d orthogonal1 = vec1.anyOrthogonal();
//		
//		System.out.println("ORTHO 1: " + orthogonal1.dot(vec1));
//		
//		Vec3d vec2 = new Vec3d(222, 4, 545).normalize();
//		Vec3d[] orthogonal23 = vec1.orthogonals(vec2);
//		
//		System.out.println("ORTHO 2: " + orthogonal23[0].dot(vec1));
//		System.out.println("ORTHO 3: " + orthogonal23[0].dot(vec2));
//		System.out.println("ORTHO 4: " + orthogonal23[1].dot(vec1));
//		System.out.println("ORTHO 5: " + orthogonal23[1].dot(vec2));
//
//		Vec2d vec3 = new Vec2d(23, 325).normalize();
//		Vec2d orthogonal4 = vec3.anyOrthogonal();
//		
//		System.out.println("ORTHO 6: " + orthogonal4.dot(vec3));
//		
//		Vec2d[] orthogonal56 = vec3.orthogonals();
//		
//		System.out.println("ORTHO 7: " + orthogonal56[0].dot(vec3));
//		System.out.println("ORTHO 8: " + orthogonal56[1].dot(vec3));
//		
//		Vec3i atest = new Vec3i(1, 2, 3);
//		Vec3d atest2 = new Vec3d(0.5, 0.24, 1);
//		Vec3d[] resulta = atest2.orthogonals(atest);
//		
//		System.out.println(resulta);
//		
//		System.out.println(new Vec3d(-3, 1, -3.45).abs().max(2.0));
//		
//		Vec2f a = new Vec2f(240, 130);
//		Vec2f b = new Vec2f(240, 130);
//		
//		System.out.println(a.equals(b));
//		
//		//Vec2i vd = new Vec2i(23, 23);
//		System.out.println(new Vec2i().dist(new Vec2i()));
//		
//		Vec3d v = new Vec3d(25, 5, 56);
//		Quaterniond quat = new Quaterniond(v, EulerOrder.YXZ, true);
//		
//		Vec3d c2 = quat.euler(EulerOrder.YXZ, true);
//		
//		System.out.println(v + " -> " + c2);
//		
		//new Vec3d(1.9999999931468058,8.045529741611852E-5,-1.4470563819479724E-4)
		Vec3d torque = calcTorque(new Vec3d(10, 0.0034, 10).normalize(), new Vec3d(0, 0, 1));
		
		System.out.println(torque);
				
	}
	
	public static Vec3d calcTorque(Vec3d current, Vec3d target) {
		
		double angleX = Math.acos(new Vec3d(0.0, current.y, current.z).normalize().dot(new Vec3d(0.0, target.y, target.z).normalize()));
		double angleY = Math.acos(new Vec3d(current.x, 0.0, current.z).normalize().dot(new Vec3d(target.x, 0.0, target.z).normalize()));
		double angleZ = Math.acos(new Vec3d(current.x, current.y, 0.0).normalize().dot(new Vec3d(target.x, target.y, 0.0).normalize()));
		
		return new Vec3d(
				Double.isFinite(angleX) ? angleX : 0.0, 
				Double.isFinite(angleY) ? angleY : 0.0, 
				Double.isFinite(angleZ) ? angleZ : 0.0
		);
		
	}
	
	public static class ITest extends TestVec {

		public ITest(float x, float y, float z) {
			super(x, y, z);
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
