package egovframework.com.cmm.converter;

/**
 * 해당 객체가 egovframework 패키지인지 체크하는 클래스
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
class DefaultCustomPackageValidator implements CustomPackageValidator {

    public static final String BASE_PKG = "egovframework";

    @Override
    public boolean isCustomPackage(Object object) {
        if(object != null && object.getClass() != null) {
            String typeName = object.getClass().getName();

            if( typeName != null && typeName.startsWith(BASE_PKG) ) {
                return true;
            }
        }

        return false;
    }

}
