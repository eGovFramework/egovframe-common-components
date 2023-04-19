package egovframework.com.cmm.converter;

/**
 * 해당 객체가 패키지인지 체크하는 메소드 제공 인터페이스
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
public interface CustomPackageValidator {

    boolean isCustomPackage(Object object);
}
