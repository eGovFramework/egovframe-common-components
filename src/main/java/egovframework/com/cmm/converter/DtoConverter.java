package egovframework.com.cmm.converter;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import egovframework.com.cmm.config.EgovWebApplicationInitializer;
import egovframework.com.cmm.converter.strategy.dto.DtoConvertStrategy;

/**
 * custom Dto 및 Map, List 내용 변경하는 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일     수정자              수정내용
 *  -----------  --------    ---------------------------
 *   2023.04.19  장동선              최초 생성
 *
 * </pre>
 */

public class DtoConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(EgovWebApplicationInitializer.class);

    private CustomPackageValidator _customPackageValidator = null;
    private DtoConvertStrategy[]   _dtoConvertStrategies   = null;


    private DtoConverter(CustomPackageValidator customPackageValidator, DtoConvertStrategy... dtoConvertStrategies) {
        this._customPackageValidator = customPackageValidator;
        this._dtoConvertStrategies   = dtoConvertStrategies;
    }


    public static void convertValue(Object srcDto, DtoConvertStrategy... dtoConvertStrategies) {
        convertValue(new DefaultCustomPackageValidator(), srcDto, dtoConvertStrategies);
    }

    public static void convertValue(CustomPackageValidator customPackageValidator, Object srcDto, DtoConvertStrategy... dtoConvertStrategies) {
        if(ObjectUtils.isEmpty(srcDto) == true) {
            LOGGER.warn("srcDto must not null");
            return;
        }

        if(ObjectUtils.isEmpty(dtoConvertStrategies) == true) {
            LOGGER.warn("dtoConvertStrategies must not null");
            return;
        }


        DtoConverter dtoConverter = new DtoConverter(customPackageValidator, dtoConvertStrategies);
        dtoConverter.commonConvertValue(null, null, srcDto, null);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void mapValueConvert(Map map) {
        if(CollectionUtils.isEmpty(map) == true) {
            return;
        }

        // 새로운 값 map에 넣기
        DtoConvertStrategy mapPutter = (_propertyDescriptor, _key, _value) -> map.replace(_key, _value);

        Set<String> keys = map.keySet();
        for(String key : keys) {
            Object mapValue = map.get(key);

            commonConvertValue(null, key, mapValue, mapPutter);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void listValueConvert(List list) {
        if(CollectionUtils.isEmpty(list) == true) {
            return;
        }

        // 새로운 값 list에 넣기
        DtoConvertStrategy listSetter = (_propertyDescriptor, _key, _value) -> {
            int index = Integer.parseInt(_key);
            list.set(index, _value);
            return null;
        };

        int index = 0;
        for(Object listValue : list) {
            commonConvertValue(null, String.valueOf(index), listValue, listSetter);
            index++;
        }
    }

    private void arrayValueConvert(Object[] array) {
        if(ObjectUtils.isEmpty(array) == true) {
            return;
        }

        // 새로운 값 array에 넣기
        DtoConvertStrategy arraySetter = (_propertyDescriptor, _key, _value) -> {
            int index = Integer.parseInt(_key);
            array[index] = _value;
            return null;
        };

        int index = 0;
        for(Object arrayValue : array) {
            commonConvertValue(null, String.valueOf(index), arrayValue, arraySetter);
            index++;
        }
    }

    private void dtoValueConvert(Object dtoObject) {
        if(Objects.isNull(dtoObject)) {
            return;
        }

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(dtoObject.getClass());
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for(PropertyDescriptor pd : pds) {
//                Class<?> fieldType     = null;
//                String   fieldTypeName = null;
                String   fieldName     = null;
                Object   oriValue      = null;
                Method   getMethod     = pd.getReadMethod();

                if(getMethod != null) {
//                    fieldType     = pd.getPropertyType();
//                    fieldTypeName = fieldType.getName();
                    fieldName     = pd.getName();
                    oriValue      = getMethod.invoke(dtoObject);
                }
//                LOGGER.debug("[{}[{}], {}]"  , pd.getName(), pd.getPropertyType(), oriValue);
//                LOGGER.debug("[{}[{}], {}]"  , fieldName   , fieldTypeName       , oriValue);
//                LOGGER.debug("??[{}[{}], {}]", fieldName   , fieldTypeName       , (oriValue == null ? null : oriValue.getClass().getName()));

                Method setMethod = pd.getWriteMethod();

                // 새로운 값 넣기
                DtoConvertStrategy dtoSetter = (_propertyDescriptor, _key, _value) -> {
                    try {
                        if(_value != null && setMethod != null) {
                            setMethod.invoke(dtoObject, _value);
                        }
                    } catch(IllegalAccessException e) {
                        LOGGER.error("setMethod", e);
                    } catch(IllegalArgumentException e) {
                        LOGGER.error("setMethod", e);
                    }  catch(InvocationTargetException e) {
                        LOGGER.error("setMethod", e);
                    }
                    return null;
                };

                commonConvertValue(pd, fieldName, oriValue, dtoSetter);
            }
        } catch(IntrospectionException e) {
            LOGGER.error("dtoValueConvert[IntrospectionException]", e);
        } catch(IllegalAccessException e) {
            LOGGER.error("dtoValueConvert[IllegalAccessException]", e);
        } catch(IllegalArgumentException e) {
            LOGGER.error("dtoValueConvert[IllegalArgumentException]", e);
        } catch(InvocationTargetException e) {
            LOGGER.error("dtoValueConvert[InvocationTargetException]", e);
        } catch(Exception e) {
            LOGGER.error("dtoValueConvert[Exception]", e);
        }
    }

    @SuppressWarnings({ "unchecked" })
    private void commonConvertValue(PropertyDescriptor pd, String key, Object value, DtoConvertStrategy putterOrSetterStrategy) {
        if(Objects.isNull(value)) {
            return;
        }

        if( Map.class.isInstance(value) ) {
            mapValueConvert((Map<Object, Object>)value);
        } else if( Collection.class.isInstance(value) ) {
            listValueConvert((List<?>)value);
        } else if( Object[].class.isInstance(value) ) {
            arrayValueConvert((Object[])value);
        } else if( _customPackageValidator.isCustomPackage(value) ){ // custom dto도 Map을 상속 받아 구현할 수 있으므로 Map 이후에 체크
            dtoValueConvert(value);
        } else {
            Object newValue = value;

            for(DtoConvertStrategy _dtoConvertStrategy : _dtoConvertStrategies) {
                if(_dtoConvertStrategy == null) {
                    continue;
                }
                newValue = _dtoConvertStrategy.convertValue(pd, key, newValue); // 기존 값을 새로운 값으로 변경
            }

            putterOrSetterStrategy.convertValue(pd, key, newValue); // 새로운 값을 map이나 list, dto에 넣기
        }
    }

}
