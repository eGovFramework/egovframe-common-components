package egovframework.com.cmm.service;

import egovframework.com.cmm.service.EgovProperties;

public class TestLoadFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*EgovWhiteList.check("");
		List<String> result = EgovWhiteList.loadWhiteList("");
		for (String whiteListData : result) {
			System.out.println("===> whiteList Data = "+ whiteListData);
		}*/
		
		// Run As > Java Application 실행시 경로가 Web Server 배포 경로와 다르므로 별도 지정 필요
		//System.out.println(">>> ORG RELATIVE_PATH_PREFIX = "+EgovWhiteList.RELATIVE_PATH_PREFIX);
		//EgovWhiteList.RELATIVE_PATH_PREFIX = "C:/eGovFrameDev-3.7.0-64bit_dev/workspace/egovframework-all-in-one-AllNew/target/classes/egovframework";
		System.out.println(">>> "+EgovProperties.class.getResource(".").getPath());
		System.out.println(">>> "+TestLoadFile.class.getResource(".").getPath());
		System.out.println(">>> TEST RELATIVE_PATH_PREFIX = "+EgovWhiteList.RELATIVE_PATH_PREFIX);
		
		boolean result = EgovWhiteList.check("/egovframework/com/main_bottom");
		System.out.println("===>>> result = "+result);
		
		System.out.println(">>> "+EgovProperties.class.getResource(".").getPath());
		
	}
}
