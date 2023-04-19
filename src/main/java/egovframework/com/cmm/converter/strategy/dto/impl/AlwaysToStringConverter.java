package egovframework.com.cmm.converter.strategy.dto.impl;

import java.beans.PropertyDescriptor;

import egovframework.com.cmm.converter.strategy.dto.DtoConvertStrategy;

public class AlwaysToStringConverter implements DtoConvertStrategy {

    @Override
    public Object convertValue(final PropertyDescriptor pd, final String key, final Object value) {
        if(value == null) {
            return "";
        }

        return value.toString();
    }

}
