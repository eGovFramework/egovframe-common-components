package egovframework.com.cmm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLoadFile {

	protected static Logger egovLogger = LoggerFactory.getLogger(TestLoadFile.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*EgovWhiteList.check("");
		List<String> result = EgovWhiteList.loadWhiteList("");
		for (String whiteListData : result) {
			egovLogger.debug("===> whiteList Data = "+ whiteListData);
		}*/

		// Run As > Java Application 실행시 경로가 Web Server 배포 경로와 다르므로 별도 지정 필요
		//egovLogger.debug(">>> ORG RELATIVE_PATH_PREFIX = "+EgovWhiteList.RELATIVE_PATH_PREFIX);
		//EgovWhiteList.RELATIVE_PATH_PREFIX = "C:/eGovFrameDev-3.7.0-64bit_dev/workspace/egovframework-all-in-one-AllNew/target/classes/egovframework";
		egovLogger.debug(">>> "+EgovProperties.class.getResource(".").getPath());
		egovLogger.debug(">>> "+TestLoadFile.class.getResource(".").getPath());
		egovLogger.debug(">>> TEST RELATIVE_PATH_PREFIX = "+EgovWhiteList.RELATIVE_PATH_PREFIX);

		boolean result = EgovWhiteList.check("/egovframework/com/main_bottom");
		egovLogger.debug("===>>> result = "+result);
		
		boolean resultNew = EgovWhiteList.checkNew("/egovframework/com/main_bottom");
		egovLogger.debug("===>>> resultNew = "+resultNew);

		egovLogger.debug(">>> "+EgovProperties.class.getResource(".").getPath());

	}
}