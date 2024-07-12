package egovframework.com.cmm.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import egovframework.com.cmm.EgovWebUtil;

public class EgovWhiteList {

	//파일구분자
	final static  String FILE_SEPARATOR = System.getProperty("file.separator");
	public static final String RELATIVE_PATH_PREFIX = EgovProperties.RELATIVE_PATH_PREFIX;

	public static boolean checkNew(String keyword) {

		String fName = EgovProperties.getProperty("Globals.linkWhitelistFile");
		if ( fName == null || "".equals(fName) ) {
			throw new RuntimeException("Globals.linkWhitelistFile is not defined!");
		}
		return checkNew(keyword, fName);
	}

	public static boolean checkNew(String keyword, String whiteListFile) {

		String filePath = RELATIVE_PATH_PREFIX+"egovProps"+FILE_SEPARATOR+whiteListFile;

		List<String> whiteList = loadWhiteListNew(EgovWebUtil.filePathBlackList(filePath));
		if ( whiteList == null ) return false;

		for (String whiteListData : whiteList) {

			if ( whiteListData != null ) {
				if ( !whiteListData.trim().equals("") ) {
					if ("#".equals(whiteListData.substring(0,1))) {
						// 주석으로 인식 - Pass
					} else {
						if (whiteListData.equals(keyword)) return true;
					}
				}
			}
		}

		return false;
	}
	
	public static boolean check(String keyword) {
		
		String fName = EgovProperties.getProperty("Globals.linkWhitelistFile");
		if ( fName == null || "".equals(fName) ) {
			throw new RuntimeException("Globals.linkWhitelistFile is not defined!");
		}
		return check(keyword, fName);
	}
	
	public static boolean check(String keyword, String whiteListFile) {
		
		String filePath = RELATIVE_PATH_PREFIX+"egovProps"+FILE_SEPARATOR+whiteListFile;
		
		List<String> whiteList = loadWhiteList(EgovWebUtil.filePathBlackList(filePath));
		if ( whiteList == null ) return false;
		
		for (String whiteListData : whiteList) {
			
			if ( whiteListData != null ) {
				if ( !whiteListData.trim().equals("") ) {
					if ("#".equals(whiteListData.substring(0,1))) {
						// 주석으로 인식 - Pass
					} else {
						if (whiteListData.equals(keyword)) return true;
					}
				}
			}
		}
		
		return false;
	}
	
	// JDK 1.7 이상
	// 하위버전의 경우 loadWhiteListOldJDK() 사용
	private static List<String> loadWhiteListNew(String filePath) {
		// Windows의 경우 /C:/로 표현되는 경우 /제거
		filePath = filePath.replaceFirst("^/(.:/)", "$1");
		
		Path path = Paths.get(EgovWebUtil.filePathBlackList(filePath));
		// 캐릭터셋 지정
		Charset cs = StandardCharsets.UTF_8;
		//파일 내용담을 리스트
		List<String> list = new ArrayList<String>();
		try{
		    list = Files.readAllLines(path,cs);
		}catch(IOException e){
		    //e.printStackTrace();
			throw new RuntimeException("Link WhiteList config file not found!");
		}
		/*for(String readLine : list){
		    System.out.println(readLine);
		}*/
		
		return list;
	}

	private static List<String> loadWhiteList(String filePath) {
		
		File inFile = new File(EgovWebUtil.filePathBlackList(filePath));
		List<String> list = new ArrayList<String>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inFile));
			String line;
			while ((line = br.readLine()) != null) {
				list.add(line);
				//System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			throw new RuntimeException("Link WhiteList config file not found!");
		} catch (IOException e) {
			//e.printStackTrace();
			throw new RuntimeException("Link WhiteList config file not found!");
		}finally {
			if(br != null) try {br.close(); } catch (IOException e) {}
		}

		return list;
	}
	
}
