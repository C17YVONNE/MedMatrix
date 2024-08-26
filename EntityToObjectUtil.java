package com.emrsys.medmatrix.util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class EntityToObjectUtil {
	// 单个 Entity 转换为 Dto
	public static <T, U> U convertEntityToDto(T entity, Class<U> dtoClass) {
        if (entity == null) {
            return null;
        }
        try {
            U dto = dtoClass.getDeclaredConstructor().newInstance();
            for (Field entityField : entity.getClass().getDeclaredFields()) {
                entityField.setAccessible(true);
                Object value = entityField.get(entity);

                try {
                    Field dtoField = dtoClass.getDeclaredField(entityField.getName());
                    dtoField.setAccessible(true);
                    dtoField.set(dto, value);
                } catch (NoSuchFieldException e) {
                    // Field does not exist in DTO, continue
                }
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	// 列表转换
    public static <T, U> List<U> convertEntityListToDtoList(List<T> entityList, Class<U> dtoClass) {
        return entityList.stream()
                .map(entity -> convertEntityToDto(entity, dtoClass))
                .collect(Collectors.toList());
    }
}
