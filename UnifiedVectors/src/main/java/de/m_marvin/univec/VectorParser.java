package de.m_marvin.univec;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import de.m_marvin.univec.api.IVector;
import de.m_marvin.univec.api.IVector2;
import de.m_marvin.univec.api.IVector3;
import de.m_marvin.univec.api.IVector4;

public class VectorParser {
	
	private static BiFunction<Class<?>, String, Optional<String>> obfuscationResolver = (clazz, fieldName) -> Optional.of(fieldName);
	
	private static Map<Class<?>, BiFunction<IVector, Object, IVector>> memorizedReader = new HashMap<>();
	private static Map<Class<?>, BiFunction<IVector, Object, Object>> memorizedWriter = new HashMap<>();
	
	public static void setObfuscationResolver(BiFunction<Class<?>, String, Optional<String>> resolverFunction) {
		obfuscationResolver = resolverFunction;
	}
	
	public static <T, V extends IVector> IVector parseFromVectorObject(T vectorObject, IVector outputVector) throws IllegalAccessException, IllegalArgumentException, IllegalArgumentException {
		BiFunction<IVector, Object, IVector> vectorReader = memorizedReader.get(vectorObject.getClass());
		if (vectorReader == null) {
			vectorReader = findVectorReader(vectorObject, outputVector);
			memorizedReader.put(vectorObject.getClass(), vectorReader);
		}
		return vectorReader.apply(outputVector, vectorObject);
	}
	
	@SuppressWarnings({ "unchecked" })
	public static <T, V extends IVector> T parseToVectorObject(T vectorObject, IVector inputVector) throws IllegalAccessException, IllegalArgumentException, IllegalArgumentException {
		BiFunction<IVector, Object, Object> vectorWriter = memorizedWriter.get(vectorObject.getClass());
		if (vectorWriter == null) {
			vectorWriter = findVectorWriter(vectorObject, inputVector);
			memorizedWriter.put(vectorObject.getClass(), vectorWriter);
		}
		return (T) vectorWriter.apply(inputVector, vectorObject);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T, V extends IVector, N extends Number> BiFunction<IVector, Object, IVector> findVectorReader(T vectorObject, IVector outputVector) {
		Field[] vectorFields = listFields(vectorObject.getClass());
		if (outputVector instanceof IVector4 vec4) {
			Function<Object, N> fieldXreader = findFieldReader(vectorFields, vectorObject, "x");
			Function<Object, N> fieldYreader = findFieldReader(vectorFields, vectorObject, "y");
			Function<Object, N> fieldZreader = findFieldReader(vectorFields, vectorObject, "z");
			Function<Object, N> fieldWreader = findFieldReader(vectorFields, vectorObject, "w");
			BiFunction<IVector, Object, IVector> vectorReader = (vecInst, vecObj) -> {
				if (vecInst instanceof IVector4 castVecInst) {
					castVecInst.setX(castValue(fieldXreader.apply(vecObj), castVecInst.getTypeClass()));
					castVecInst.setY(castValue(fieldYreader.apply(vecObj), castVecInst.getTypeClass()));
					castVecInst.setZ(castValue(fieldZreader.apply(vecObj), castVecInst.getTypeClass()));
					castVecInst.setW(castValue(fieldWreader.apply(vecObj), castVecInst.getTypeClass()));
					return castVecInst;
				}
				throw new IllegalArgumentException("The passed output vector is invalid! It has to be of type IVector4");
			};
			return vectorReader;
		} else if (outputVector instanceof IVector3 vec3) {
			Function<Object, N> fieldXreader = findFieldReader(vectorFields, vectorObject, "x");
			Function<Object, N> fieldYreader = findFieldReader(vectorFields, vectorObject, "y");
			Function<Object, N> fieldZreader = findFieldReader(vectorFields, vectorObject, "z");
			BiFunction<IVector, Object, IVector> vectorReader = (vecInst, vecObj) -> {
				if (vecInst instanceof IVector3 castVecInst) {
					castVecInst.setX(castValue(fieldXreader.apply(vecObj), castVecInst.getTypeClass()));
					castVecInst.setY(castValue(fieldYreader.apply(vecObj), castVecInst.getTypeClass()));
					castVecInst.setZ(castValue(fieldZreader.apply(vecObj), castVecInst.getTypeClass()));
					return castVecInst;
				}
				throw new IllegalArgumentException("The passed output vector is invalid! It has to be of type IVector3");
			};
			return vectorReader;
		} else if (outputVector instanceof IVector2 vec2) {
			Function<Object, N> fieldXreader = findFieldReader(vectorFields, vectorObject, "x");
			Function<Object, N> fieldYreader = findFieldReader(vectorFields, vectorObject, "y");
			BiFunction<IVector, Object, IVector> vectorReader = (vecInst, vecObj) -> {
				if (vecInst instanceof IVector2 castVecInst) {
					castVecInst.setX(castValue(fieldXreader.apply(vecObj), castVecInst.getTypeClass()));
					castVecInst.setY(castValue(fieldYreader.apply(vecObj), castVecInst.getTypeClass()));
					return castVecInst;
				}
				throw new IllegalArgumentException("The passed output vector is invalid! It has to be of type IVector2");
			};
			return vectorReader;
		}
		throw new IllegalArgumentException("Could not find reader for the vector object, is it realy a vector?");
	}

	@SuppressWarnings({ "rawtypes" })
	private static <T, V extends IVector, N extends Number> BiFunction<IVector, Object, Object> findVectorWriter(T vectorObject, IVector inputVector) {
		Field[] vectorFields = listFields(vectorObject.getClass());
		if (inputVector instanceof IVector4 vec4) {
			BiConsumer<Number, Object> fieldXwriter = findFieldWriter(vectorFields, vectorObject, "x");
			BiConsumer<Number, Object> fieldYwriter = findFieldWriter(vectorFields, vectorObject, "y");
			BiConsumer<Number, Object> fieldZwriter = findFieldWriter(vectorFields, vectorObject, "z");
			BiConsumer<Number, Object> fieldWwriter = findFieldWriter(vectorFields, vectorObject, "w");
			BiFunction<IVector, Object, Object> vectorReader = (vecInst, vecObj) -> {
				if (vecInst instanceof IVector4 castVecInst) {
					fieldXwriter.accept(castVecInst.x(), vecObj);
					fieldYwriter.accept(castVecInst.y(), vecObj);
					fieldZwriter.accept(castVecInst.z(), vecObj);
					fieldWwriter.accept(castVecInst.w(), vecObj);
					return vecObj;
				}
				throw new IllegalArgumentException("The passed input vector is invalid! It has to be of type IVector4");
			};
			return vectorReader;
		} else if (inputVector instanceof IVector3 vec3) {
			BiConsumer<Number, Object> fieldXwriter = findFieldWriter(vectorFields, vectorObject, "x");
			BiConsumer<Number, Object> fieldYwriter = findFieldWriter(vectorFields, vectorObject, "y");
			BiConsumer<Number, Object> fieldZwriter = findFieldWriter(vectorFields, vectorObject, "z");
			BiFunction<IVector, Object, Object> vectorReader = (vecInst, vecObj) -> {
				if (vecInst instanceof IVector3 castVecInst) {
					fieldXwriter.accept(castVecInst.x(), vecObj);
					fieldYwriter.accept(castVecInst.y(), vecObj);
					fieldZwriter.accept(castVecInst.z(), vecObj);
					return vecObj;
				}
				throw new IllegalArgumentException("The passed input vector is invalid! It has to be of type IVector3");
			};
			return vectorReader;
		} else if (inputVector instanceof IVector2 vec2) {
			BiConsumer<Number, Object> fieldXwriter = findFieldWriter(vectorFields, vectorObject, "x");
			BiConsumer<Number, Object> fieldYwriter = findFieldWriter(vectorFields, vectorObject, "y");
			BiFunction<IVector, Object, Object> vectorReader = (vecInst, vecObj) -> {
				if (vecInst instanceof IVector2 castVecInst) {
					fieldXwriter.accept(castVecInst.x(), vecObj);
					fieldYwriter.accept(castVecInst.y(), vecObj);
					return vecObj;
				}
				throw new IllegalArgumentException("The passed input vector is invalid! It has to be of type IVector2");
			};
			return vectorReader;
		}
		throw new IllegalArgumentException("Could not find writer for the vector object, is it realy a vector?");
	}
	
	@SuppressWarnings("unchecked")
	private static <N extends Number> Function<Object, N> findFieldReader(Field[] fields, Object vectorObject, String... valueName) {
		for (Field field : fields) {
			for (String matchName : valueName) {
				Optional<String> deobfFieldName = obfuscationResolver.apply(field.getDeclaringClass(), field.getName());
				if (deobfFieldName.isPresent() && deobfFieldName.get().equalsIgnoreCase(matchName)) {
					field.setAccessible(true);
					return (vecObj) -> {
						try {
							Object rawValue = field.get(vecObj);
							if (rawValue instanceof Number) {
								return (N) rawValue;
							}
						} catch (IllegalArgumentException | IllegalAccessException e) {
							throw new IllegalArgumentException("An error occured while reading an (most likely) invalid vector object!", e);
						}
						throw new IllegalArgumentException("An error occured while reading an invalid vector object!");
					};
				}
			}
		}
		throw new IllegalArgumentException("The vector object " + vectorObject + " is missing the " + valueName + " value!");
	}

	@SuppressWarnings("unchecked")
	private static <N extends Number> BiConsumer<N, Object> findFieldWriter(Field[] fields, Object vectorObject, String... valueName) {
		for (Field field : fields) {
			for (String matchName : valueName) {
				Optional<String> deobfFieldName = obfuscationResolver.apply(field.getDeclaringClass(), field.getName());
				if (deobfFieldName.isPresent() && deobfFieldName.get().equalsIgnoreCase(matchName)) {
					field.setAccessible(true);
					return (value, vecObj) -> {
						try {
							field.set(vecObj, castValue(value, (Class<? extends Number>) field.getType()));
						} catch (IllegalArgumentException | IllegalAccessException e) {
							throw new IllegalArgumentException("An error occured while writing an (most likely) invalid vector object!", e);
						}
					};
				}
			}
		}
		throw new IllegalArgumentException("The vector object " + vectorObject + " is missing the " + valueName[0] + " value!");
	}
	
//	private static String[] obfuscateFields(Class<?> clazz, String... fields) {
//		List<String> obfuscatedFieldNames = new ArrayList<>();
//		for (int i = 0; i < fields.length; i++) {
//			Optional<String> obfuscatedField = obfuscationResolver.apply(clazz, fields[i]);
//			if (obfuscatedField.isPresent()) obfuscatedFieldNames.add(obfuscatedField.get());
//		}
//		return obfuscatedFieldNames.toArray(l -> new String[l]);
//	}
	
	private static Field[] listFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<Field>();
		listFields0(clazz, fields);
		return fields.toArray(new Field[] {});
	}
	
	private static void listFields0(Class<?> clazz, List<Field> fields) {
		for (Field field : clazz.getDeclaredFields()) {
			fields.add(field);
		}
		if (clazz.getSuperclass() != null) {
			listFields0(clazz.getSuperclass(), fields);
		}
	}

	private static Number castValue(Number value, Class<? extends Number> clazz) {
		if (clazz == Double.class || clazz == double.class) {
			return value.doubleValue();
		} else if (clazz == Float.class || clazz == float.class) {
			return value.floatValue();
		} else if (clazz == Integer.class || clazz == int.class) {
			return value.intValue();
		}
		return 0;
	}
	
}
