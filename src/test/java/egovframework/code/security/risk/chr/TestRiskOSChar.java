package egovframework.code.security.risk.chr;

import lombok.extern.slf4j.Slf4j;

/**
 * OS 위험한 문자 Test Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.11.29
 * @version 3.9
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2023.11.23  신용호          최초 생성
 *
 * </pre>
 */

@Slf4j
public class TestRiskOSChar {

	public static void main(String[] args) {

		String cmd = "dir aa|bbb;ccc&ddd&&eee:fff>ggg*hhh";
		cmd = cmd.replaceAll("\\|","");
		cmd = cmd.replaceAll(";","");
		cmd = cmd.replaceAll("&","");
		cmd = cmd.replaceAll(":","");
		cmd = cmd.replaceAll(">","");
		cmd = cmd.replaceAll("\\*","");
		
		log.debug("===>>> "+cmd);
		
		String cmd2 = "dir aa  bbb";
		cmd2 = cmd2.replaceAll("\\p{Space}","");
		
		log.debug("===>>> "+cmd2);
	}

}
