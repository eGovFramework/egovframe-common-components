package egovframework.code.security.file;

import org.apache.commons.io.FilenameUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 경로 문자열에서 안전하게 파일명을 추출하는 동작을 확인한다.
 *
 * <p>상위 경로를 포함한 입력에서는 마지막 파일명만 추출하고,
 * 널 바이트가 포함된 비정상 입력에서는 예외가 발생하는지 확인한다.</p>
 * 
 * @author 표준프레임워크 신용호
 * @since 2022.11.16
 * @version 4.0
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2022.11.16  신용호          최초 생성
 *
   
 * </pre>
 */

@Slf4j
public class TestFileName {

	/**
	 * 경로 조작 및 널 바이트가 포함된 파일명 처리 결과를 출력한다.
	 *
	 * @param args 명령행 인수(사용하지 않음)
	 */
	public static void main(String[] args) {
		// 상위 경로가 포함된 일반 파일 및 확장자가 없는 파일을 준비한다.
		String file1 = "../../../config/test.png";
		String file2 = "../../../config/overide_file";
		// 확장자를 위장하기 위해 널 바이트를 삽입한 비정상 파일명이다.
		String file3 = "shell.jsp\u0000expected.gif";

		// 전체 경로를 제거하고 마지막 파일명 부분만 추출한다.
		String fn1 = FilenameUtils.getName(file1);
		String fn2 = FilenameUtils.getName(file2);

		// 비정상 입력 처리에 실패한 경우에도 출력할 기본값을 지정한다.
		String fn3 = "-";
		try {
			fn3 = FilenameUtils.getName(file3);
		} catch (IllegalArgumentException e) {
			log.error("FilenameUtils가 널 바이트 입력을 거부하면 예외 정보를 기록한다.", e);
		}
		log.debug("safe filename1 = {}", fn1);
		log.debug("safe filename2 = {}", fn2);
		log.debug("safe filename3 = {}", fn3);
	}

}
