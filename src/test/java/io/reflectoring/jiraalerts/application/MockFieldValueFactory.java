package io.reflectoring.jiraalerts.application;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.injection.IFieldValueFactory;
import org.mockito.Mock;
import org.springframework.util.ReflectionUtils;

public class MockFieldValueFactory implements IFieldValueFactory {

	private Map<Class<?>, Object> repository = new HashMap<>();

	public MockFieldValueFactory(Object objectWithMockAnnotations) {
		try {
			Class<?> clazz = objectWithMockAnnotations.getClass();
			while (clazz != null) {
				getMockedFieldValues(objectWithMockAnnotations, clazz.getDeclaredFields());
				clazz = clazz.getSuperclass();
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private void getMockedFieldValues(Object objectWithMockAnnotations, Field[] fields) throws IllegalAccessException {
		for (Field field : fields) {
			if (field.isAnnotationPresent(Mock.class)) {
				ReflectionUtils.makeAccessible(field);
				repository.put(field.getType(), field.get(objectWithMockAnnotations));
			}
		}
	}

	@Override
	public Object getFieldValue(Field field, Object fieldOwner) {
		return repository.get(field.getType());
	}

	@Override
	public boolean supportsField(Field field) {
		return repository.containsKey(field.getType());
	}
}
