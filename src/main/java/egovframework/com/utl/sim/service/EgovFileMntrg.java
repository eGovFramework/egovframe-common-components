/**
 *  Class Name : EgovFileMntrg.java
 *  Description : 시스템 네트워크 정보를 확인하여 제공하는  Business class
 *  Modification Information
 *
 *     수정일         수정자                   수정내용
 *   -------    --------    ---------------------------
 *   2009.01.13    조재영          최초 생성
 *   2017.03.06    조성원          시큐어코딩(ES)-Null Pointer 역참조[CWE-476]
 *
 *  @author 공통 서비스 개발팀 조재영
 *  @since 2009. 01. 13
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2009 by EGOV  All right reserved.
 */
package egovframework.com.utl.sim.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import egovframework.com.cmm.util.EgovBasicLogger;
import egovframework.com.cmm.util.EgovResourceCloseHelper;

public class EgovFileMntrg extends Thread {

	/**
	 * <p>
	 * 해당 파일의 변경 유무를 체크하기 위한 Default 초의 stati final 변수, 기본 적용은 값은 60초
	 * </p>
	 */
	//static final public long DEFAULT_DELAY = 60000; // 60초
	static final public long DEFAULT_DELAY = 30000; // 30초

	/**
	 * 최대 문자길이
	 **/
	static final int MAX_STR_LEN = 1024;

	/**
	 * <p>
	 * 파일의 변경 유무를 체크하기 위한 해당파일명 변수
	 * </p>
	 */
	protected String filename;

	/**
	 * <p>
	 * 해당 파일의 변경 유무를 체크하기 위한 Default 초의 stati final 변수, 기본 적용은 값은 60초{@link
	 * #DEFAULT_DELAY}.
	 * </p>
	 */
	protected long delay = DEFAULT_DELAY;

	File file; // 타겟(감시대상) 디렉토리
	File logFile; // 감시정보보관용 로그파일
	long lastModif = 0;
	boolean warnedAlready = false;
	boolean interrupted = false;
	List<String> realOriginalList = new ArrayList<String>(); 		// 최초의 원본리스트
	List<String> originalList = new ArrayList<String>(); 			// 직전리스트는 주기적으로 직전목록정보로 갱신된다.
	List<String> currentList = new ArrayList<String>(); 			// 직전리스트와 비교할 현시점 리스트
	List<String> changedList = new ArrayList<String>(); 			// 직전리스트와 비교한 시점에 발생된 변경리스트
	List<String> totalChangedList = new ArrayList<String>(); 	// 최초리스트와 비교한 변경 리스트
																							// totalChangedList는 필요시 checkAndConfigure함수 내에서 주석해제후 사용(부하량을 고려하여 사용)
	int cnt = 0;

	/**
	 * <p>
	 * 감시 하고자 하는 파일명을 파라메타로 받는 기본 컨스트럭터(Constructor).
	 * </p>
	 *
	 * @param filename
	 */
	protected EgovFileMntrg(String filename, File logFile) {
		//log.debug("EgovFileMntrg start");
		this.logFile = logFile;
		this.filename = filename;
		file = new File(filename);
		// 1. 최초생성시 현재 디렉토리의 하위정보를 ArrayList에 보관한다. 보관정보 ==>  절대경로 + "," + 최종수정일시 + "," + 사이즈
		File[] fList = file.listFiles();
		//2017.03.06 	조성원 	시큐어코딩(ES)-Null Pointer 역참조[CWE-476]
		if(fList == null){
			fList = new File[0];
		}
		for (int i = 0; i < fList.length; i++) {
			realOriginalList.add(fList[i].getAbsolutePath() + "$" + getLastModifiedTime(fList[i]) + "$" + ((fList[i].length() / 1024) > 0 ? (fList[i].length() / 1024) : 1) + "KB");
			writeLog("ORI_" + fList[i].getAbsolutePath() + "$" + getLastModifiedTime(fList[i]) + "$" + ((fList[i].length() / 1024) > 0 ? (fList[i].length() / 1024) : 1) + "KB");
		}
		
		originalList = new ArrayList<String>(realOriginalList);
		writeLog("START");
		setDaemon(true);
		checkAndConfigure();
		//log.debug("EgovFileMntrg end");
	}

	/**
	 * <p>
	 * 감시 하고자 하는 파일의 변경 유무를 체크 하고자 하는 delay 초를 set.
	 * </p>
	 *
	 * @param delay 감시 주기 초
	 */
	public void setDelay(long delay) {
		this.delay = delay;
	}

	/**
	 * <p>
	 * 해당 파일의 변경시 작업 할 내용을 기술 할 추상(abstract) 메소드
	 * </p>
	 */
	//abstract protected void doOnChange();
	protected void doOnChange(List<String> changedList) {
		//log.debug("doOnChange() start");
		for (int i = 0; i < changedList.size(); i++) {
			writeLog((String) changedList.get(i));
		}
		changedList.clear(); //직전리스트와 비교해서 변경된 내역은 로그처리한 후 초기화한다.
		originalList = new ArrayList<String>(currentList); //현재리스트가 직전리스트가 된다.(새로 생성해야 함!)
		cnt++;

		//log.debug("doOnChange() end");
	}

	/**
	 * <p>
	 * 파일의 변경 유무를 체크하는 메소드
	 * </p>
	 */
	protected void checkAndConfigure() {
		//log.debug("checkAndConfigure start");
		try {
			currentList.clear();
			file = new File(filename);
			// 현재정보를 ArrayList에 담는다.
			File[] fList = file.listFiles();
			//2017.03.06 	조성원 	시큐어코딩(ES)-Null Pointer 역참조[CWE-476]
			if(fList == null){
				fList = new File[0];
			}
			
			for (int i = 0; i < fList.length; i++) {
				currentList.add(fList[i].getAbsolutePath() + "$" + getLastModifiedTime(fList[i]) + "$" + ((fList[i].length() / 1024) > 0 ? (fList[i].length() / 1024) : 1) + "KB");
			}
			/*
			for(int i = 0; i<originalList.size(); i++ ){
			    //log.debug("in checkAndConfigure() ::: originalList:" + originalList.get(i));
			}
			for(int i = 0; i<currentList.size(); i++ ){
			    //log.debug("in checkAndConfigure() ::: currentList:" + currentList.get(i));
			}
			*/
			boolean isSame = false;
			boolean isNew = true;
			boolean isDel = true;
			String str1 = "";
			String str2 = "";
			//int tmpCnt = 0;

			// 현재하위디렉토리정보와 초최하위디렉토리 정보를 비교한다. 삭제된 경우를 확인함
			for (int i = 0; i < originalList.size(); i++) {
				for (int j = 0; j < currentList.size(); j++) {
					str1 = (String) originalList.get(i);
					str2 = (String) currentList.get(j);
					if (str1.substring(0, str1.indexOf("$")).equals(str2.substring(0, str2.indexOf("$")))) {
						isDel = false;
					}
				}
				if (isDel) {
					changedList.add("DEL$" + originalList.get(i));
				}
				isDel = true; // 초기화
			}

			// 현재하위디렉토리 정보와 최초하위디렉토리 정보를 비교한다.(신규로 생성되었거나 수정된 경우를 확인함)
			for (int i = 0; i < currentList.size(); i++) {
				for (int j = 0; j < originalList.size(); j++) {
					if (((String) currentList.get(i)).equals((String) originalList.get(j))) {
						isSame = true;
					}
					str1 = (String) currentList.get(i);
					str2 = (String) originalList.get(j);
					if (str1.substring(0, str1.indexOf("$")).equals(str2.substring(0, str2.indexOf("$")))) {
						isNew = false;
					}
				}
				if (!isSame) {
					if (isNew) {
						changedList.add("NEW$" + currentList.get(i));
						//totalChangedList.add("NEW$"+currentList.get(i));
					} else {
						changedList.add("MODI$" + currentList.get(i));
						//totalChangedList.add("MODI$"+currentList.get(i));
					}
				}
				isSame = false; // 초기화
				isNew = true; // 초기화
			}

		} catch (RuntimeException e) {
			//interrupted = true; // there is no point in continuing

			EgovBasicLogger.debug("Checking error", e);
		}

		if (changedList.size() > 0) {
			//log.debug("change occur , changed file check count:"+cnt+ " , changed file count:"+changedList.size());
			doOnChange(changedList);
		}

		if (isEnd()) {
			//log.debug("Thread Process END !!! (CNT :"+cnt+")");
			interrupted = true;
		}
		//log.debug("checkAndConfigure end"+changedList.size());
	}

	/**
	 * <p>
	 * 파일의 변경 유무의 체크를 주기적 초 단위로 실행 시키는 메소드
	 * </p>
	 */
	public void run() {
		while (!interrupted) {
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				EgovBasicLogger.ignore("Interrupted Exception", e);
			}
			checkAndConfigure();
		}
		if (interrupted) {
			this.interrupt();
		}
	}

	/**
	 * <pre>
	 * Comment : 디렉토리(파일)의 최종 수정시간를 확인한다.(기본로케일 java.util.Locale.KOREA 기준)
	 * </pre>
	 * @param File f 수정일자를 확인할 대상파일
	 * @return String result 최종수정일자를 문자열로 리턴한다.
	 */
	public static String getLastModifiedTime(File f) {
		long date = f.lastModified();
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyyMMdd:HH:mm:ss", java.util.Locale.KOREA);
		return dateFormat.format(new java.util.Date(date));
	}

	/**
	 * <pre>
	 * Comment : 디렉토리(파일)의  로그정보를 기록한다.
	 * </pre>
	 * @param String  logStr  추가할 로그정보(라인단위)
	 * @return boolean result  로그추가 성공여부
	 */
	public boolean writeLog(String logStr) {
		boolean result = false;

		FileWriter fWriter = null;
		BufferedWriter bWriter = null;
		BufferedReader br = null;
		try {
			fWriter = new FileWriter(logFile, true);
			bWriter = new BufferedWriter(fWriter);
			br = new BufferedReader(new StringReader(logStr));
			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.length() <= MAX_STR_LEN) {
					bWriter.write(line + "\n", 0, line.length() + 1);
				}
			}
			result = true;
		} catch (IOException e) {
			throw new RuntimeException("File IO exception", e);
		} finally {
			EgovResourceCloseHelper.close(br, bWriter, fWriter);
		}

		return result;
	}

	/**
	 * <pre>
	 * Comment : 디렉토리감시 종료여부를 확인한다. 해당 디렉토리에 대한 로그파일이 삭제된 경우는 감시를 종료한다.
	 * </pre>
	 * @return boolean isEnd 감시종료여부 중단하려면  true 리턴, 계속하려면 false 리턴
	 */
	public boolean isEnd() {
		//log.debug("isEnd start");
		boolean isEnd = false;
		String lastStr = "";
		BufferedReader br = null;
		FileReader fr = null;

		try {
			if (logFile.exists()) {
				//로그파일을 읽어서 마지막 끝에 END가 있으면 종료된것임

				fr = new FileReader(logFile);
				br = new BufferedReader(fr);
				//int ch = 0;
				String line = "";
				while ((line = br.readLine()) != null) {
					if (line.length() <= MAX_STR_LEN) {
						lastStr = line;
					}
				}
				if (lastStr.equals("END")) {
					isEnd = true;
				}
			} else {
				//로그파일이 없는 경우(삭제된 경우)도 종료한다.
				isEnd = true;
			}
		} catch (IOException e) {
			throw new RuntimeException("File IO exception", e);
		} finally {
			EgovResourceCloseHelper.close(br, fr);
		}
		return isEnd;
	}
}
