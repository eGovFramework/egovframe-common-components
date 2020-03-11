package egovframework.com.utl.fcc.service;

/**
 * 
 * 포맷유효성체크 에 대한 Util 클래스 
 * @author 공통컴포넌트 개발팀 윤성록
 * @since 2009.06.23
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.23  윤성록          최초 생성
 *
 * </pre>
 */
public class EgovFormatCheckUtil {
	
    /**
     * <pxxx - xxx- xxxx 형식의 전화번호 앞, 중간, 뒤 문자열 3개 입력 받아 유요한 전화번호형식인지 검사.</p>
     * 
     * 
     * @param   전화번호 문자열( 3개 )
     * @return  유효한 전화번호 형식인지 여부 (True/False)
     */
    public static boolean checkFormatTell(String tell1, String tell2, String tell3) {
		 
	 String[] check = {"02", "031", "032", "033", "041", "042", "043", "051", "052", "053", "054", "055", "061", 
				 "062", "063", "070", "080", "0505"};	//존재하는 국번 데이터
	 String temp = tell1 + tell2 + tell3;
		 
	 for(int i=0; i < temp.length(); i++){
    		if (temp.charAt(i) < '0' || temp.charAt(i) > '9')	
    			return false;    		
	 }	//숫자가 아닌 값이 들어왔는지를 확인
 		 
	 for(int i = 0; i < check.length; i++){
		 if(tell1.equals(check[i])) break;			 
		 if(i == check.length - 1) return false;
	 }	//국번입력이 제대로 되었는지를 확인
		 
	 if(tell2.charAt(0) == '0') return false; 
		
	 if(tell1.equals("02")){
		 if(tell2.length() != 3 && tell2.length() !=4) return false;
		 if(tell3.length() != 4) return false;	//서울지역(02)국번 입력때의 전화 번호 형식유효성 체크			 
	 }else{
		 if(tell2.length() != 3) return false;
		 if(tell3.length() != 4) return false;
	 }	//서울을 제외한 지역(국번 입력때의 전화 번호 형식유효성 체크	 
	 
	 return true;
    }
	
    /**
     * <p>xxx - xxx- xxxx 형식의 전화번호 하나를 입력 받아 유요한 전화번호형식인지 검사.</p>
     * 
     * 
     * @param   전화번호 문자열 (1개)
     * @return  유효한 전화번호 형식인지 여부 (True/False)
     */
    public static boolean checkFormatTell(String tellNumber) {
	 
	 String temp1;
	 String temp2;
	 String temp3;
	 String tell = tellNumber;
	 
	 tell = tell.replace("-", "");	
	 
	 if(tell.length() < 9 || tell.length() > 11  || tell.charAt(0) != '0') return false;	//전화번호 길이에 대한 체크
		 
	 if(tell.charAt(1) =='2'){	//서울지역 (02)국번의 경우일때
		 temp1 = tell.substring(0,2);
		 if(tell.length() == 9){
			 temp2 = tell.substring(2,5);
			 temp3 = tell.substring(5,9);
		 }else if(tell.length() == 10){
			 temp2 = tell.substring(2,6);
			 temp3 = tell.substring(6,10);
		 }else
			 return false;	
	 } else if(tell.substring(0,4).equals("0505")){ //평생번호(0505)국번의 경우일때
		 if(tell.length() != 11) return false;
		 temp1 = tell.substring(0,4);
		 temp2 = tell.substring(4,7);
		 temp3 = tell.substring(7,11);
	 } else {	// 서울지역 및 "0505" 를 제외한 일반적인 경우일때
		 if(tell.length() != 10) return false;
		 temp1 = tell.substring(0,3);
		 temp2 = tell.substring(3,6);
		 temp3 = tell.substring(6,10); 
	 }
		 		 
	 return checkFormatTell(temp1, temp2, temp3);
    }
	
    /**
     * <p>xxx - xxx- xxxx 형식의 휴대폰번호 앞, 중간, 뒤 문자열 3개 입력 받아 유요한 휴대폰번호형식인지 검사.</p>
     * 
     * 
     * @param   휴대폰번호 문자열,(3개)
     * @return  유효한 휴대폰번호 형식인지 여부 (True/False)
     */
    public static boolean checkFormatCell(String cell1, String cell2, String cell3) {
	 String[] check = {"010", "011", "016", "017", "018", "019"}; //유효한 휴대폰 첫자리 번호 데이터
	 String temp = cell1 + cell2 + cell3;
	 
	 for(int i=0; i < temp.length(); i++){
    		if (temp.charAt(i) < '0' || temp.charAt(i) > '9') 
    			return false;    		
         }	//숫자가 아닌 값이 들어왔는지를 확인
	 		 
	 for(int i = 0; i < check.length; i++){
	     if(cell1.equals(check[i])) break;			 
	     if(i == check.length - 1) return false;
	 }	// 휴대폰 첫자리 번호입력의 유효성 체크
		 
	 if(cell2.charAt(0) == '0') return false;
		
	 if(cell2.length() != 3 && cell2.length() !=4) return false;
	 if(cell3.length() != 4) return false;
				 
	 return true;
    }
	 
    /**
     * <p>XXXXXXXXXX 형식의 휴대폰번호 문자열 3개 입력 받아 유요한 휴대폰번호형식인지 검사.</p>
     * 
     * 
     * @param   휴대폰번호 문자열(1개)
     * @return  유효한 휴대폰번호 형식인지 여부 (True/False)
     */
    public static boolean checkFormatCell(String cellNumber) {
		 
	 String temp1;
	 String temp2;
	 String temp3;
	
	 String cell = cellNumber;
	 cell = cell.replace("-", "");		
	 
	 if(cell.length() < 10 || cell.length() > 11  || cell.charAt(0) != '0') return false;
	 
	 if(cell.length() == 10){	//전체 10자리 휴대폰 번호일 경우
		 temp1 = cell.substring(0,3);
		 temp2 = cell.substring(3,6);
		 temp3 = cell.substring(6,10);
	 }else{		//전체 11자리 휴대폰 번호일 경우
		 temp1 = cell.substring(0,3);
		 temp2 = cell.substring(3,7);
		 temp3 = cell.substring(7,11);
	 }
		 
	 return checkFormatCell(temp1, temp2, temp3);
    }
	 
    /**
     * <p> 이메일의  앞, 뒤 문자열 2개 입력 받아 유요한 이메일형식인지 검사.</p>
     * 
     * 
     * @param   이메일 문자열 (2개)
     * @return  유효한 이메일 형식인지 여부 (True/False)
     */
    public static boolean checkFormatMail(String mail1, String mail2) {
		 
	 int count = 0;
		 
	 for(int i = 0; i < mail1.length(); i++){
		 if(mail1.charAt(i) <= 'z' && mail1.charAt(i) >= 'a') continue;		
		 else if(mail1.charAt(i) <= 'Z' && mail1.charAt(i) >= 'A') continue;	
		 else if(mail1.charAt(i) <= '9' && mail1.charAt(i) >= '0') continue;	
		 else if(mail1.charAt(i) == '-' && mail1.charAt(i) == '_') continue;	
		 else return false;
	 }	// 유효한 문자, 숫자인지 체크
		 		 		 
	 for(int i = 0; i < mail2.length(); i++){	
		 if(mail2.charAt(i) <= 'z' && mail2.charAt(i) >= 'a') continue;
		 else if(mail2.charAt(i) == '.'){ count++;  continue;}
		 else return false;
	 }	// 메일 주소의 형식 체크(xxx.xxx 형태)		
		 
	 if(count == 1) return true;
	 else  return false;	 
		 
    }
	
    /**
     * <p> 이메일의 전체문자열 1개 입력 받아 유요한 이메일형식인지 검사.</p>
     * 
     * 
     * @param   이메일 문자열 (1개)
     * @return  유효한 이메일 형식인지 여부 (True/False)
     */
    public static boolean checkFormatMail(String mail) {
		 
	 String[] temp = mail.split("@");	// '@' 를 기점으로 앞, 뒤 문자열 구분
	 
	 if(temp.length == 2) return checkFormatMail(temp[0], temp[1]);
	 else return false;
    }

} 

