package egovframework.com.validation.nullcheck;

import egovframework.com.cmm.LoginVO;

/**
 * NullCheck Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.11.29
 * @version 3.9
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2019.11.29  신용호          최초 생성
 *
 * # Java Lang - Null Check 관련 특성을 테스트한다.
 * </pre>
 */

public class NullCheckTest {

	public static void main(String[] args) {
		
		LoginVO loginVO = new LoginVO();
		loginVO.setName("");
		//loginVO.setEmail("");
		//LoginVO loginVO = null;

		/*if ( loginVO == null && loginVO.getName() == null && loginVO.getName().equals("") && loginVO.getEmail() == null && loginVO.getEmail().equals("") ) {
			System.out.println("Null Check = 조건에 걸림");
		}*/
		
		if ( loginVO == null || loginVO.getName() == null || loginVO.getName().equals("") && loginVO.getEmail() == null || loginVO.getEmail().equals("") ) {
			System.out.println("Null Check = 조건에 걸림");
		}

	}

}
