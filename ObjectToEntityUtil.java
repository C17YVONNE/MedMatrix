package com.emrsys.medmatrix.util;

import java.lang.reflect.Field;

public class ObjectToEntityUtil {
	
	public static <T, U> U convertDtoToEntity(T dto, Class<U> entityClass) {
        if (dto == null) {
            return null;
        }
        try {
            U entity = entityClass.getDeclaredConstructor().newInstance();
            for (Field dtoField : dto.getClass().getDeclaredFields()) {
                dtoField.setAccessible(true);
                Object value = dtoField.get(dto);

                try {
                    Field entityField = entityClass.getDeclaredField(dtoField.getName());
                    entityField.setAccessible(true);
                    entityField.set(entity, value);
                } catch (NoSuchFieldException e) {
                    // Field does not exist in Entity, continue
                }
            }
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
