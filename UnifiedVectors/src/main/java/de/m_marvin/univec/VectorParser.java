package de.m_marvin.univec;

import java.lang.reflect.Field;

import de.m_marvin.univec.api.IVector;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector4;

public class VectorParser {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T, V extends IVector, N extends Number> IVector parseVectorObject(T vectorObject, IVector outputVector) throws IllegalAccessException, IllegalArgumentException, IllegalArgumentException {
		Class<?> vectorClass = vectorObject.getClass();
		Field[] vectorFields = vectorClass.getDeclaredFields();
		
		if (outputVector instanceof IVector4 vec4) {
			vec4.setX(findField(vectorFields, vectorObject, "x"));
			vec4.setY(findField(vectorFields, vectorObject, "y"));
			vec4.setZ(findField(vectorFields, vectorObject, "z"));
			vec4.setW(findField(vectorFields, vectorObject, "w"));
			return vec4;
		} else if (outputVector instanceof IVector3 vec3) {
			vec3.setX(findField(vectorFields, vectorObject, "x"));
			vec3.setY(findField(vectorFields, vectorObject, "y"));
			vec3.setZ(findField(vectorFields, vectorObject, "z"));
			return vec3;
		} else if (outputVector instanceof IVector2 vec2) {
			vec2.setX(findField(vectorFields, vectorObject, "x"));
			vec2.setY(findField(vectorFields, vectorObject, "y"));
			return vec2;
		}
		
		throw new IllegalArgumentException(outputVector + " is not a valid univector type!");
	}

	@SuppressWarnings("rawtypes")
	public static <T, V extends IVector, N extends Number> T parseToVectorObject(T vectorObject, IVector outputVector) throws IllegalAccessException, IllegalArgumentException, IllegalArgumentException {
		Class<?> vectorClass = vectorObject.getClass();
		Field[] vectorFields = vectorClass.getDeclaredFields();
		
		if (outputVector instanceof IVector4 vec4) {
			writeField(vectorFields, vectorObject, "x", vec4.getX());
			writeField(vectorFields, vectorObject, "y", vec4.getY());
			writeField(vectorFields, vectorObject, "z", vec4.getZ());
			writeField(vectorFields, vectorObject, "w", vec4.getW());
			return vectorObject;
		} else if (outputVector instanceof IVector3 vec3) {
			writeField(vectorFields, vectorObject, "x", vec3.getX());
			writeField(vectorFields, vectorObject, "y", vec3.getY());
			writeField(vectorFields, vectorObject, "z", vec3.getZ());
			return vectorObject;
		} else if (outputVector instanceof IVector2 vec2) {
			writeField(vectorFields, vectorObject, "x", vec2.getX());
			writeField(vectorFields, vectorObject, "y", vec2.getY());
			return vectorObject;
		}
		
		throw new IllegalArgumentException(outputVector + " is not a valid univector type!");
	}
	
	public static <N extends Number> void writeField(Field[] fields, Object vectorObject, String valueName, N value) throws IllegalAccessException, IllegalArgumentException {
		String[] matchList = { valueName };
		for (Field field : fields) {
			for (String matchName : matchList) {
				if (field.getName().equalsIgnoreCase(matchName)) {
					field.setAccessible(true);
					Object rawValue = field.get(vectorObject);
					if (rawValue instanceof Number) {
						field.set(vectorObject, value);
						field.setAccessible(false);
						return;
					}
					field.setAccessible(false);
				}
			}
		}
		throw new IllegalArgumentException("The vector object " + vectorObject + " is missing the " + valueName + " value!");
	}
	
	@SuppressWarnings("unchecked")
	public static <N extends Number> N findField(Field[] fields, Object vectorObject, String valueName) throws IllegalAccessException, IllegalArgumentException, IllegalArgumentException {
		String[] matchList = { valueName };
		for (Field field : fields) {
			for (String matchName : matchList) {
				if (field.getName().equalsIgnoreCase(matchName)) {
					field.setAccessible(true);
					Object rawValue = field.get(vectorObject);
					field.setAccessible(false);
					if (rawValue instanceof Number) {
						return (N) rawValue;
					}
				}
			}
		}
		throw new IllegalArgumentException("The vector object " + vectorObject + " is missing the " + valueName + " value!");
	}
	
}
