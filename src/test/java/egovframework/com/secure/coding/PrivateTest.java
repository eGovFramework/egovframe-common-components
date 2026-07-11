package egovframework.com.secure.coding;

import lombok.extern.slf4j.Slf4j;

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
 *   2020.08.24  신용호          시큐어코딩 테스트 (Private 배열에 Public 데이터 할당)
 *   2026.07.11  이백행          [2026년 컨트리뷰션] 디버그 출력에 log.debug 적용
 *
 * </pre>
 */
@Slf4j
public class PrivateTest {

	public static void main(String[] args) {
		MyUserVO myUser = new MyUserVO();
		
		// Private String TEST
		String myName = "SYH";
		myUser.setName(myName);
		log.debug(myName);
		log.debug(myUser.getName());

		myName = "Change Name";
		log.debug(myUser.getName());
		
		
		// Private Array TEST
		String[] myColor = {"red","green","blue"};
		
		myUser.setMyColor(myColor);
		
		log.debug(myColor[0]);
		log.debug(myUser.getMyColor()[0]);

		myColor[0] = "direct access";
		log.debug(myUser.getMyColor()[0]); //<=== 직접 접근 가능
		
	}

}
