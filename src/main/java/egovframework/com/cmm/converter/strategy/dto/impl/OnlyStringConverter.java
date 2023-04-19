package egovframework.com.cmm.converter.strategy.dto.impl;

import java.beans.PropertyDescriptor;

import egovframework.com.cmm.converter.strategy.dto.DtoConvertStrategy;

public abstract class OnlyStringConverter implements DtoConvertStrategy {

    @Override
    final public Object convertValue(final PropertyDescriptor pd, final String key, final Object value) {
        if( String.class.isInstance(value) ) {
            return convertString(pd, key, (String)value);
        }

        return value;
    }

    abstract public String convertString(final PropertyDescriptor pd, final String key, final String value);
}
