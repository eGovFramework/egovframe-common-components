package egovframework.com.cmm.converter.strategy.dto;

import java.beans.PropertyDescriptor;

/**
 * DtoConverter를 이용하여 객체 값을 변경시 사용할 값변환 인터페이스
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
public interface DtoConvertStrategy {

    /**
     *
     * @param PropertyDescriptor - custom dto일 경우 활용(Map이나 List일 경우 null)
     * @param String - key
     * @param Object - value
     *
     * @return Object
     * */
    public Object convertValue(final PropertyDescriptor propertyDescriptor, final String key, final Object value);
}
