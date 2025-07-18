package com.ltfreire.bluecard_api.application.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class NullPropertyUtils {

    public static String[] getNullPropertyNames(Object source) {
        final PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(source.getClass());
        Set<String> emptyNames = new HashSet<>();

        for (PropertyDescriptor pd : pds) {
            try {
                Object value = pd.getReadMethod().invoke(source);
                if (value == null) {
                    emptyNames.add(pd.getName());
                }
            } catch (Exception e) {
            }
        }

        return emptyNames.toArray(new String[0]);
    }

    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }
}
