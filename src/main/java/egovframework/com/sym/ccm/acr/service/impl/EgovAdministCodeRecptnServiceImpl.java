package egovframework.com.sym.ccm.acr.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cop.cmy.service.impl.EgovCommuManageServiceImpl;
import egovframework.com.sym.ccm.acr.service.AdministCodeRecptn;
import egovframework.com.sym.ccm.acr.service.AdministCodeRecptnVO;
import egovframework.com.sym.ccm.acr.service.EgovAdministCodeRecptnService;

/**
 *
 * 법정동코드에 대한 서비스 구현클래스를 정의한다.
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호			최초 생성
 *   2011.10.07  이기하			finally문을 추가하여 에러시 자원반환할 수 있도록 추가
 *   2017.02.08	 이정은			시큐어코딩(ES) - 시큐어코딩 부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *   2022.11.11  김혜준			시큐어코딩 처리
 *   2023.08.10  정진오			법정동코드수신 방식 수정(공공데이터포털 이용)
 *   2024.10.29  권태성			API 수신 데이터 등록 시 기본 사용여부 값 적용(insertAdministCodeRecptn())
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Service("AdministCodeRecptnService")
public class EgovAdministCodeRecptnServiceImpl extends EgovAbstractServiceImpl implements EgovAdministCodeRecptnService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovAdministCodeRecptnServiceImpl.class);
	
	@Resource(name = "AdministCodeRecptnDAO")
	private AdministCodeRecptnDAO administCodeRecptnDAO;

	/** EgovIdGnrService */
	@Resource(name = "egovAdministCodeRecptnIdGnrService")
	private EgovIdGnrService idgenService;

	/**
	 * 법정동코드수신을 처리한다.
	 */
	public void insertAdministCodeRecptn() throws Exception {
		List<HashMap<String, String>> list = apiLink();
		for(int i = 0; i < list.size(); i++) {
			HashMap<String, String> row = list.get(i);
			AdministCodeRecptn administCodeRecptn = new AdministCodeRecptn();
			administCodeRecptn.setOccrrDe(ObjectUtils.isEmpty(row.get("adptDe")) ? "20000101" : row.get("adptDe")); // 날짜 >> adpt_de 생성일 x 20000101
			administCodeRecptn.setAdministZoneSe("1"); // 행정구역부분 1 법정동 2 행정동
			administCodeRecptn.setAdministZoneCode(row.get("regionCd")); // 행정구역코드 >> region_cd
			administCodeRecptn.setOpertSn(idgenService.getNextIntegerId()); // 작업일련번호 >> idgenService.getNextIntegerId()
			administCodeRecptn.setChangeSeCode("01"); // 변경구분코드 01 코드생성 02 코드변경 03 코드말소 >> 01 / 02
			administCodeRecptn.setProcessSe("00"); // 작업구분 00 수신처리 01 처리완료 11 생성오류 12 변경오류 13 말소오류>> 00
			administCodeRecptn.setAdministZoneNm(row.get("locataddNm")); // 행정구역명 >> locatadd_nm 지역주소명
			administCodeRecptn.setLowestAdministZoneNm(row.get("locallowNm")); // 최하위행정구역명 >> locallow_nm 최하위지역명
			administCodeRecptn.setCtprvnCode(row.get("sidoCd")); // 시도코드 >> sido_cd 시도코드
			administCodeRecptn.setSignguCode(row.get("sggCd")); // 시군구코드 >> sgg_cd 시군구코드
			administCodeRecptn.setEmdCode(row.get("umdCd")); // 읍면동코드  >> umd_cd 읍면동코드
			administCodeRecptn.setLiCode(row.get("riCd")); // 리코드 >> ri_cd 리코드
			administCodeRecptn.setCreatDe(row.get("adptDe")); // 생성일자 >> adpt_de 생성일
			administCodeRecptn.setAblDe(""); // 폐지일자 >> x
			administCodeRecptn.setAblEnnc(""); // 폐지유무 >> x
			administCodeRecptn.setFrstRegisterId("Batch System"); // 등록자 Batch System
			administCodeRecptn.setLastUpdusrId("Batch System"); // 수정자 Batch System
			administCodeRecptn.setUseAt("Y"); // 사용여부 >> Y

			AdministCodeRecptnVO vo = new AdministCodeRecptnVO();
			vo.setSearchCondition("CodeList");
			vo.setAdministZoneSe("1");
			vo.setAdministZoneCode(row.get("regionCd"));
			int count = administCodeRecptnDAO.selectAdministCodeRecptnListTotCnt(vo);
			if (count > 0) {
				administCodeRecptnDAO.updateAdministCode(administCodeRecptn);
			} else {
				administCodeRecptnDAO.insertAdministCodeRecptn(administCodeRecptn);
				administCodeRecptnDAO.insertAdministCode(administCodeRecptn);
			}
		}
	}

	/**
	 * 법정동코드를 수신하기 위한 요청을 설정한다.
	 */
	public static String requestString(int pageNo, int numOfRows) throws IOException {
		String serviceKey = EgovProperties.getProperty("Globals.data.serviceKey");
		StringBuilder sb = new StringBuilder();
		sb.append("https://apis.data.go.kr/1741000/StanReginCd/getStanReginCdList"); /*URL*/
		sb.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
		sb.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(Integer.toString(pageNo), "UTF-8")); /*페이지번호*/
		sb.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(Integer.toString(numOfRows), "UTF-8")); /*한 페이지 결과 수*/
		sb.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
		sb.append("&" + URLEncoder.encode("locatadd_nm","UTF-8") + "=" + URLEncoder.encode("서울특별시", "UTF-8")); /*지역주소명(옵션)*/
        return sb.toString();
	}

	/**
	 * 법정동코드 페이지수를 확인한다.
	 */
	public static int numberOfRows() throws IOException, ParseException {
		int pageNo = 1;

		String requestString = requestString(1, 1);

        URL url = new URL(requestString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept", "*/*;q=0.9");
		conn.setDoOutput(true);
		conn.setUseCaches(false);

        BufferedReader br;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {

        	br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        	StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
            JSONArray jsonArray = (JSONArray) jsonObject.get("StanReginCd");
            JSONObject headObject = (JSONObject) jsonArray.get(0);
            JSONArray headArray = (JSONArray) headObject.get("head");
            JSONObject object = (JSONObject) headArray.get(0);
    		int totalCount = Integer.parseInt(object.get("totalCount").toString());
    		pageNo = (int) Math.ceil((double) totalCount/1000);

        } else {
        	LOGGER.debug("##### AdministCodeRecptnService.numberOfRows() Error Code >>> " + conn.getResponseCode());
        }

        conn.disconnect();

        return pageNo;
	}

	/**
	 * 법정동코드를 수신한다.
	 */
	public static List<HashMap<String, String>> apiLink() throws IOException, ParseException {
		List<HashMap<String, String>> administCodeList = new ArrayList<>();

		int pageNo = 1;
		int numOfRows = 1000;

		pageNo = numberOfRows();

		for (int p = 1; p <= pageNo; p++) {

			String requestString = requestString(p, numOfRows);

			URL url = new URL(requestString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "*/*;q=0.9");
			conn.setDoOutput(true);
			conn.setUseCaches(false);

	        BufferedReader br;
	        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {

	        	br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        	StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = br.readLine()) != null) {
	                sb.append(line);
	            }
	            br.close();

	            JSONParser jsonParser = new JSONParser();
	            JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
	            JSONArray jsonArray = (JSONArray) jsonObject.get("StanReginCd");
	            JSONObject bodyObject = (JSONObject) jsonArray.get(1);
	            JSONArray row = (JSONArray) bodyObject.get("row");

	            for (int r = 0; r < row.size(); r++) {
	            	JSONObject object = (JSONObject) row.get(r);
	            	HashMap<String, String> administCode = new HashMap<>();
	            	administCode.put("regionCd", stringValueOf(object.get("region_cd")));
	    			administCode.put("sidoCd", stringValueOf(object.get("sido_cd")));
	    			administCode.put("sggCd", stringValueOf(object.get("sgg_cd")));
	    			administCode.put("umdCd", stringValueOf(object.get("umd_cd")));
	    			administCode.put("riCd", stringValueOf(object.get("ri_cd")));
	    			administCode.put("locatjuminCd", stringValueOf(object.get("locatjumin_cd")));
	    			administCode.put("locatjijukCd", stringValueOf(object.get("locatjijuk_cd")));
	    			administCode.put("locataddNm", stringValueOf(object.get("locatadd_nm")));
	    			administCode.put("locatOrder", stringValueOf(object.get("locat_order")));
	    			administCode.put("locatRm", stringValueOf(object.get("locat_rm")));
	    			administCode.put("locathighCd", stringValueOf(object.get("locathigh_cd")));
	    			administCode.put("locallowNm", stringValueOf(object.get("locallow_nm")));
	    			administCode.put("adptDe", stringValueOf(object.get("adpt_de")));
	    			administCodeList.add(administCode);
	    		}

	        } else {
	        	LOGGER.debug("##### AdministCodeRecptnService.apiLink() Error Code >>> " + conn.getResponseCode());
	        }

	        conn.disconnect();
		}

        return administCodeList;
	}

	private static String stringValueOf(Object object) {
		return object == null ? "" : String.valueOf(object);
	}

	/**
	 * 법정동코드 상세내역을 조회한다.
	 */
	public AdministCodeRecptn selectAdministCodeDetail(AdministCodeRecptn administCodeRecptn) throws Exception {
		AdministCodeRecptn ret = (AdministCodeRecptn) administCodeRecptnDAO.selectAdministCodeDetail(administCodeRecptn);
		return ret;
	}

	/**
	 * 법정동코드수신 목록을 조회한다.
	 */
	public List<EgovMap> selectAdministCodeRecptnList(AdministCodeRecptnVO searchVO) throws Exception {
		return administCodeRecptnDAO.selectAdministCodeRecptnList(searchVO);
	}

	/**
	 * 법정동코드수신 총 개수를 조회한다.
	 */
	public int selectAdministCodeRecptnListTotCnt(AdministCodeRecptnVO searchVO) throws Exception {
		return administCodeRecptnDAO.selectAdministCodeRecptnListTotCnt(searchVO);
	}

	/**
	 * 법정동코드 목록을 조회한다.
	 */
	public List<EgovMap> selectAdministCodeList(AdministCodeRecptnVO searchVO) throws Exception {
		return administCodeRecptnDAO.selectAdministCodeList(searchVO);
	}

	/**
	 * 법정동코드 총 개수를 조회한다.
	 */
	public int selectAdministCodeListTotCnt(AdministCodeRecptnVO searchVO) throws Exception {
		return administCodeRecptnDAO.selectAdministCodeListTotCnt(searchVO);
	}

}
