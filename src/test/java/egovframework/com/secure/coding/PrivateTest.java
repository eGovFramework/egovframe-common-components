package egovframework.com.secure.coding;

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
 *  2020.08.24  신용호          시큐어코딩 테스트 (Private 배열에 Public 데이터 할당)
 *
 * </pre>
 */

public class PrivateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyUserVO myUser = new MyUserVO();
		
		// Private String TEST
		String myName = "SYH";
		myUser.setName(myName);
		System.out.println(myName);
		System.out.println(myUser.getName());

		myName = "Change Name";
		System.out.println(myUser.getName());
		
		
		// Private Array TEST
		String[] myColor = {"red","green","blue"};
		
		myUser.setMyColor(myColor);
		
		System.out.println(myColor[0]);
		System.out.println(myUser.getMyColor()[0]);

		myColor[0] = "direct access";
		System.out.println(myUser.getMyColor()[0]); //<=== 직접 접근 가능
		
	}

}
