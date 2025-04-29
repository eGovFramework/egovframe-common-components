package egovframework.com.cmm.aop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import egovframework.com.cmm.service.EgovProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * EgovFileBasePathSecurityValidator Class 구현
 * @author 표준프레임워크 신용호
 * @since 2019.04.25
 * @version 4.3
 * @see
 * <pre>
 *
 *  수정일         수정자          수정내용
 *  ----------   -----------   ---------------------------
 *  2025.04.01   신용호          최초 생성
 *  
 *  - String basePath 파라미터에 대해 보안강화 체크를 한다.
 *  - 보안성을 위해 basePath는 ROOT Path를 지정할수 없다.
 *  - basePath에 대해 다음 경로가 추가되어 화이트리스트 방식으로 점검한다. (필요시 화이트리스트를 추가한다)
 *    basePath가 다음 제한된 경로의 하위에 위치하는지 점검한다.
 *    1) Globals.fileStorePath # 파일 업로드 경로
 *    2) Globals.SynchrnServerPath # 파일 동기화 컴포넌트에서 사용할 파일 업로드 경로
 *
 */

@Slf4j
public class EgovFileBasePathSecurityValidator {

    public static boolean validate(String basePath) {
    	
    	boolean validateResult = false;

    	ArrayList<String> whiteList = new ArrayList<String>();
    	// 파일 업로드 경로
    	whiteList.add(EgovProperties.getProperty("Globals.fileStorePath"));
    	// 파일 동기화 컴포넌트에서 사용할 파일 업로드 경로
    	whiteList.add(EgovProperties.getProperty("Globals.SynchrnServerPath"));
    	// 테스트용 Base Path - Windows OS
    	//whiteList.add("D:/TEMP/");
    	// 테스트용 Base Path - Linux, Mac OS
    	//whiteList.add("/Users/EgovStoredFiles");
    	
        if (basePath == null || basePath.trim().isEmpty()) {
            log.error("ERROR : The base path is empty.");
            return false;
        }
        

        String normalizedBasePath = basePath.trim().replace("\\", "/"); // 윈도우 경로도 동일하게 처리

        // 루트 경로 제한 (리눅스 / 또는 윈도우 드라이브 루트 경로들)
        if (normalizedBasePath.matches("(?i)^[a-z]:/$") || normalizedBasePath.equals("/")) {
            log.error("ERROR : Root base paths are not allowed. basePath = {}", basePath);
            return false;
        }

		try {
	        // 입력 경로 정규화 (Canonical Path로 변환)
			File base = new File(normalizedBasePath).getCanonicalFile();

	        // 허용된 디렉토리 내인지 검증
	        for (String whiteBasePath : whiteList) {
	            File file = new File(whiteBasePath).getCanonicalFile();
	            if (file.getPath().startsWith(base.getPath())) {
	            	validateResult = true;
	            }
	        }
		} catch (IOException e) {
			log.debug("Base Path IO Exception!");
		}

        if (validateResult == false)
        	log.error("ERROR : Unacceptable base path: {} " , basePath);

        return validateResult;
    }
}