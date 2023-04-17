package egovframework.com.cmm.service;

/**
 * Class Path 가져오기 Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.04.25
 * @version 3.8
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2019.04.25  신용호          최초 생성
 *
 */

public class TestClassPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// classpath 가져오기 - 기본 방식 (AS IS)
		System.out.println(">>> TEST CLASS PATH1 = "+EgovProperties.class.getResource(""));
		// classpath 가져오기 - 새로운 방법 (TO BE)
		System.out.println(">>> TEST CLASS PATH2 = "+EgovProperties.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		// * 주의 항상 동일한 Path를 반환하지는 않음.
		// ex)
		//  /C:/eGovFrameDev-3.7.0-64bit_dev/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/egovframework-all-in-one-AllNew/WEB-INF/classes/
		//  /C:/eGovFrameDev-3.6.0-64bit/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/egovframework-all-in-one-AllNew/WEB-INF/classes/egovframework/com/cmm/service/EgovProperties.class
		
		String path1 = EgovProperties.class.getResource("").getPath().substring(0, EgovProperties.class.getResource("").getPath().lastIndexOf("com"));
		System.out.println(">>> TEST CLASS PATH3 = "+path1);
		
		System.out.println(">>> TEST CLASS PATH4 = "+EgovProperties.class.getResource("").getPath().lastIndexOf("com"));
		System.out.println(">>> TEST CLASS PATH5 = "+EgovProperties.class.getResource("/").getPath());
		
		System.out.println(">>> TEST CLASS PATH6 = "+EgovProperties.class.getResource("").getPath());
		
		// 개선된 방법
		System.out.println(">>> TEST CLASS PATH7 = "+
				EgovProperties.class.getProtectionDomain().getCodeSource().getLocation().getPath()+
				EgovProperties.class.getResource("").getPath().substring(0, EgovProperties.class.getResource("").getPath().lastIndexOf("com"))
				.replace(EgovProperties.class.getResource("/").getPath(), "")
				);
		
		//String path2 = EgovProperties.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(0, EgovProperties.class.getProtectionDomain().getCodeSource().getLocation().getPath().lastIndexOf("com"));
		//System.out.println(">>> TEST RELATIVE_PATH_PREFIX3 = "+path);
		
		//String txt = "/C:/eGovFrameDev-3.6.0-64bit/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/egovframework-all-in-one-AllNew/WEB-INF/classes/egovframework/com/cmm/service/EgovProperties.class";
		//System.out.println("===>>> "+EgovProperties.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(0,EgovProperties.class.getProtectionDomain().getCodeSource().getLocation().getPath().indexOf("WEB-INF/classes/")+"WEB-INF/classes/".length())+"egovframework/");
	}

}
