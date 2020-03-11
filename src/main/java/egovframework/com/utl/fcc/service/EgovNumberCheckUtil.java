package egovframework.com.utl.fcc.service;

/**
 *
 * 번호유효성체크 에 대한 Util 클래스
 * @author 공통컴포넌트 개발팀 윤성록
 * @since 2009.06.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.10  윤성록          최초 생성
 *   2012.02.27  이기하          법인번호 체크로직 수정
 *
 * </pre>
 */
public class EgovNumberCheckUtil {

	 /**
     * <p>XXXXXX - XXXXXXX 형식의 주민번호 앞, 뒤 문자열 2개 입력 받아 유효한 주민번호인지 검사.</p>
     *
     *
     * @param   6자리 주민앞번호 문자열 , 7자리 주민뒷번호 문자열
     * @return  유효한 주민번호인지 여부 (True/False)
     */
    @SuppressWarnings("static-access")
	public static boolean checkJuminNumber(String jumin1, String jumin2) {

    	EgovDateUtil egovDateUtil = new EgovDateUtil();
    	String juminNumber = jumin1 + jumin2;
    	String  IDAdd = "234567892345"; 	// 주민등록번호에 가산할 값

		int count_num = 0;
    	int add_num = 0;
        int total_id = 0;      //검증을 위한 변수선언

        if (juminNumber.length() != 13) return false;	 // 주민등록번호 자리수가 맞는가를 확인

       	for (int i = 0; i <12 ; i++){
       		if(juminNumber.charAt(i)< '0' || juminNumber.charAt(i) > '9') return false;		//숫자가 아닌 값이 들어왔는지를 확인
       		count_num = Character.getNumericValue(juminNumber.charAt(i));
       		add_num = Character.getNumericValue(IDAdd.charAt(i));
        	total_id += count_num * add_num;      //유효자리 검증식을 적용
        }

       	if(Character.getNumericValue(juminNumber.charAt(0)) == 0 || Character.getNumericValue(juminNumber.charAt(0)) == 1){
       		if(Character.getNumericValue(juminNumber.charAt(6)) > 4) return false;
       		String temp = "20" + juminNumber.substring(0,6);
       		if(!egovDateUtil.checkDate(temp)) return false;
       	}else{
       		if(Character.getNumericValue(juminNumber.charAt(6)) > 2) return false;
       		String temp = "19" + juminNumber.substring(0,6);
       		if(!egovDateUtil.checkDate(temp)) return false;
       	}	//주민번호 앞자리 날짜유효성체크 & 성별구분 숫자 체크

       	if(Character.getNumericValue(juminNumber.charAt(12)) == (11 - (total_id % 11)) % 10) //마지막 유효숫자와 검증식을 통한 값의 비교
        	return true;
        else
        	return false;
    }

    /**
     * <p>XXXXXXXXXXXXX 형식의 13자리 주민번호 1개를 입력 받아 유효한 주민번호인지 검사.</p>
     *
     *
     * @param   13자리 주민번호 문자열
     * @return  유효한 주민번호인지 여부 (True/False)
     */
    public static boolean checkJuminNumber(String jumin) {

    	if(jumin.length() != 13) return false;

        return checkJuminNumber(jumin.substring(0,6), jumin.substring(6,13));	//주민번호
    }

    /**
     * <p>XXXXXX - XXXXXXX 형식의 법인번호 앞, 뒤 문자열 2개 입력 받아 유효한 법인번호인지 검사.</p>
     *
     *
     * @param   6자리 법인앞번호 문자열 , 7자리 법인뒷번호 문자열
     * @return  유효한 법인번호인지 여부 (True/False)
     */
    public static boolean checkBubinNumber(String bubin1, String bubin2) {

    	String bubinNumber = bubin1 + bubin2;

    	int hap = 0;
    	int temp = 1;	//유효검증식에 사용하기 위한 변수

    	if(bubinNumber.length() != 13) return false;	//법인번호의 자리수가 맞는 지를 확인

    	for(int i=0; i < 13; i++){
    		if (bubinNumber.charAt(i) < '0' || bubinNumber.charAt(i) > '9') //숫자가 아닌 값이 들어왔는지를 확인
    			return false;
    	}


    	// 2012.02.27  법인번호 체크로직 수정( i<13 -> i<12 )
    	// 맨끝 자리 수는 전산시스템으로 오류를 검증하기 위해 부여되는 검증번호임
    	for ( int i=0; i<12; i++){
    		if(temp ==3) temp = 1;
    		hap = hap + (Character.getNumericValue(bubinNumber.charAt(i)) * temp);
    		temp++;
    	}	//검증을 위한 식의 계산

    	if ((10 - (hap%10))%10 == Character.getNumericValue(bubinNumber.charAt(12))) //마지막 유효숫자와 검증식을 통한 값의 비교
    		return true;
    	else
    		return false;
    	}

    /**
     * <p>XXXXXXXXXXXXX 형식의 13자리 법인번호 1개를 입력 받아 유효한 법인번호인지 검사.</p>
     *
     *
     * @param   13자리 법인번호 문자열
     * @return  유효한 법인번호인지 여부 (True/False)
     */
    public static boolean checkBubinNumber(String bubin) {

    	if(bubin.length() != 13) return false;

    	return checkBubinNumber(bubin.substring(0,6), bubin.substring(6,13));
    	}


    /**
     * <p>xxx - xx - xxxx 형식의 사업자번호 앞,중간, 뒤 문자열 3개 입력 받아 유효한 사업자번호인지 검사.</p>
     *
     *
     * @param   3자리 사업자앞번호 문자열 , 2자리 사업자중간번호 문자열, 5자리 사업자뒷번호 문자열
     * @return  유효한 사업자번호인지 여부 (True/False)
     */
	public static boolean checkCompNumber(String comp1, String comp2, String comp3) {

		String compNumber = comp1 + comp2 + comp3;

		int hap = 0;
		int temp = 0;
		int check[] = {1,3,7,1,3,7,1,3,5};  //사업자번호 유효성 체크 필요한 수

		if(compNumber.length() != 10)    //사업자번호의 길이가 맞는지를 확인한다.
			return false;

		for(int i=0; i < 9; i++){
			if(compNumber.charAt(i) < '0' || compNumber.charAt(i) > '9')  //숫자가 아닌 값이 들어왔는지를 확인한다.
				return false;

			hap = hap + (Character.getNumericValue(compNumber.charAt(i)) * check[temp]); //검증식 적용
			temp++;
		}

		hap += (Character.getNumericValue(compNumber.charAt(8))*5)/10;

		if ((10 - (hap%10))%10 == Character.getNumericValue(compNumber.charAt(9))) //마지막 유효숫자와 검증식을 통한 값의 비교
			return true;
		else
			return false;
 	}

	 /**
     * <p>XXXXXXXXXX 형식의 10자리 사업자번호 3개를 입력 받아 유효한 사업자번호인지 검사.</p>
     *
     *
     * @param   10자리 사업자번호 문자열
     * @return  유효한 사업자번호인지 여부 (True/False)
     */
	public static boolean checkCompNumber(String comp) {

		if(comp.length() != 10) return false;
		return checkCompNumber(comp.substring(0,3), comp.substring(3,5), comp.substring(5,10));
 	}

	 /**
     * <p>XXXXXX - XXXXXXX 형식의 외국인등록번호 앞, 뒤 문자열 2개 입력 받아 유효한 외국인등록번호인지 검사.</p>
     *
     *
     * @param   6자리 외국인등록앞번호 문자열 , 7자리 외국인등록뒷번호 문자열
     * @return  유효한 외국인등록번호인지 여부 (True/False)
     */
	@SuppressWarnings("static-access")
	public static boolean checkforeignNumber( String foreign1, String foreign2  ) {

		EgovDateUtil egovDateUtil = new EgovDateUtil();
		String foreignNumber = foreign1 + foreign2;
		int check = 0;

		if( foreignNumber.length() != 13 )   //외국인등록번호의 길이가 맞는지 확인한다.
			return false;

		for(int i=0; i < 13; i++){
    		if (foreignNumber.charAt(i) < '0' || foreignNumber.charAt(i) > '9') //숫자가 아닌 값이 들어왔는지를 확인한다.
    			return false;
    	}

     	if(Character.getNumericValue(foreignNumber.charAt(0)) == 0 || Character.getNumericValue(foreignNumber.charAt(0)) == 1){
       		if(Character.getNumericValue(foreignNumber.charAt(6)) == 5 && Character.getNumericValue(foreignNumber.charAt(6)) == 6) return false;
       		String temp = "20" + foreignNumber.substring(0,6);
       		if(!egovDateUtil.checkDate(temp)) return false;
       	}else{
       		if(Character.getNumericValue(foreignNumber.charAt(6)) == 5 && Character.getNumericValue(foreignNumber.charAt(6)) == 6) return false;
       		String temp = "19" + foreignNumber.substring(0,6);
       		if(!egovDateUtil.checkDate(temp)) return false;
       	}	//외국인등록번호 앞자리 날짜유효성체크 & 성별구분 숫자 체크

		for( int i = 0 ; i < 12 ; i++ ) {
			check += ( ( 9 - i % 8 ) * Character.getNumericValue( foreignNumber.charAt( i ) ) );
		}

		if ( check % 11 == 0 ){
			check = 1;
		}else if ( check % 11==10 ){
			check = 0;
		}else
			check = check % 11;

		if ( check + 2 > 9 ){
			check = check + 2- 10;
		}else check = check+2;	//검증식을 통합 값의 도출

		if( check == Character.getNumericValue( foreignNumber.charAt( 12 ) ) ) //마지막 유효숫자와 검증식을 통한 값의 비교
			return true;
		else
			return false;
		}


	 /**
     * <p>XXXXXXXXXXXXX 형식의 13자리 외국인등록번호 1개를 입력 받아 유효한 외국인등록번호인지 검사.</p>
     *
     *
     * @param   13자리 외국인등록번호 문자열
     * @return  유효한 외국인등록번호인지 여부 (True/False)
     */
	public static boolean checkforeignNumber( String foreign  ) {

		if(foreign.length() != 13) return false;
		return checkforeignNumber(foreign.substring(0,6), foreign.substring(6,13));
	}
}


