package egovframework.com.sym.ccm.icr.service.impl;

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
import egovframework.com.sym.ccm.acr.service.impl.EgovAdministCodeRecptnServiceImpl;
import egovframework.com.sym.ccm.icr.service.EgovInsttCodeRecptnService;
import egovframework.com.sym.ccm.icr.service.InsttCodeRecptn;
import egovframework.com.sym.ccm.icr.service.InsttCodeRecptnVO;

/**
 *
 * 기관코드에 대한 서비스 구현클래스를 정의한다.
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
 *   2009.04.01  이중호          최초 생성
 *   2011.09.05	 서준식          파일 읽기 무한 루프 오류 수정
 *   2011.10.07  이기하          finally문을 추가하여 에러시 자원반환할 수 있도록 추가
 *   2017.02.08	 이정은          시큐어코딩(ES) - 시큐어코딩 부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *   2022.11.11  김혜준          시큐어코딩 처리
 *   2023.08.10  정진오          기관코드수신 방식 수정(공공데이터포털 이용)
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Service("InsttCodeRecptnService")
public class EgovInsttCodeRecptnServiceImpl extends EgovAbstractServiceImpl implements EgovInsttCodeRecptnService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovInsttCodeRecptnServiceImpl.class);
	
	@Resource(name = "InsttCodeRecptnDAO")
	private InsttCodeRecptnDAO insttCodeRecptnDAO;

	/** EgovIdGnrService */
	@Resource(name = "egovInsttCodeRecptnIdGnrService")
	private EgovIdGnrService idgenService;

	/**
	 * 기관코드수신을 처리한다.
	 */
	public void insertInsttCodeRecptn() throws Exception {
		List<HashMap<String, String>> list = apiLink();
		for(int i = 0; i < list.size(); i++) {
			HashMap<String, String> row = list.get(i);
			InsttCodeRecptn insttCodeRecptn = new InsttCodeRecptn();
			insttCodeRecptn.setOccrrDe(ObjectUtils.isEmpty(row.get("crtDe")) ? "20000101" : row.get("crtDe")); // 날짜 >> crt_de 생성일 x 20000101
			insttCodeRecptn.setInsttCode(row.get("orgCd")); // 기관코드 >> org_cd 기관코드
			insttCodeRecptn.setOpertSn(idgenService.getNextIntegerId()); // 작업일련번호 >> idgenService.getNextIntegerId()
			insttCodeRecptn.setChangeSeCode("01"); // 변경구분코드 01 코드생성 02 코드변경 03 코드말소 >> 01 / 02
			insttCodeRecptn.setProcessSe("00"); // 작업구분 00 수신처리 01 처리완료 11 생성오류 12 변경오류 13 말소오류>> 00
			insttCodeRecptn.setEtcCode(row.get("locatstdCd")); // 기타코드 >> locatstd_cd 소재지코드
			insttCodeRecptn.setAllInsttNm(row.get("fullNm")); // 전체기관명 >> full_nm 기관명전체
			insttCodeRecptn.setLowestInsttNm(row.get("lowNm")); // 최하위기관명 >> low_nm 기관명최하위
			insttCodeRecptn.setInsttAbrvNm(row.get("abbrNm")); // 기관약칭명 >> abbr_nm 기관명약어
			insttCodeRecptn.setOdr(row.get("gapNo")); // 차수 >> gap_no 차수
			insttCodeRecptn.setOrd(row.get("rankNo")); // 서열 >> rank_no 서열
			insttCodeRecptn.setInsttOdr(row.get("subChasu")); // 소속기관차수 >> sub_chasu 소속기관차수
			insttCodeRecptn.setUpperInsttCode(row.get("highCd")); // 차상위기관코드 >> high_cd 상위기관코드
			insttCodeRecptn.setBestInsttCode(row.get("highstCd")); // 최상위기관코드 >> highst_cd 최상위기관코드
			insttCodeRecptn.setReprsntInsttCode(row.get("repCd")); // 대표기관코드 >> rep_cd 대표기관코드
			insttCodeRecptn.setInsttTyLclas(row.get("typebigNm")); // 기관유형(대) >> typebig_nm 기관대분류
			insttCodeRecptn.setInsttTyMclas(row.get("typemidNm")); // 기관유형(중) >> typemid_nm 기관중분류
			insttCodeRecptn.setInsttTySclas(row.get("typesmlNm")); // 기관유형(소) >> typesml_nm 기관소분류
			insttCodeRecptn.setTelno(""); // 전화번호 >> x
			insttCodeRecptn.setFxnum(""); // 팩스번호 >> x
			insttCodeRecptn.setCreatDe(row.get("crtDe")); // 생성일자 >> crt_de 생성일
			insttCodeRecptn.setAblDe(row.get("clsDe")); // 폐지일자 >> cls_de 폐지일
			insttCodeRecptn.setAblEnnc(row.get("stopSelt")); // 폐지구분 >> stop_selt 폐지구분
			insttCodeRecptn.setChangede(row.get("chgDe")); // 변경일자 >> chg_de 변경일
			insttCodeRecptn.setChangeTime(""); // 변경시간 >> x
			insttCodeRecptn.setBsisDe(row.get("baseDate")); // 기초날짜 >> base_date 기초일자
			insttCodeRecptn.setSortOrdr(0); // 정렬순서 >> x
			insttCodeRecptn.setFrstRegisterId("System Batch"); // 등록자 Batch System
			insttCodeRecptn.setLastUpdusrId("System Batch"); // 수정자 Batch System

			InsttCodeRecptnVO vo = new InsttCodeRecptnVO();
			vo.setSearchCondition("CodeList");
			vo.setInsttCode(row.get("orgCd"));
			int count = insttCodeRecptnDAO.selectInsttCodeRecptnListTotCnt(vo);
			if (count > 0) {
				insttCodeRecptnDAO.updateInsttCode(insttCodeRecptn);
			} else {
				insttCodeRecptnDAO.insertInsttCodeRecptn(insttCodeRecptn);
				insttCodeRecptnDAO.insertInsttCode(insttCodeRecptn);
			}
		}
	}

	/**
	 * 기관코드를 수신하기 위한 요청을 설정한다.
	 */
	public static String requestString(int pageNo, int numOfRows) throws IOException {
		String serviceKey = EgovProperties.getProperty("Globals.data.serviceKey");
		StringBuilder sb = new StringBuilder();
		sb.append("http://apis.data.go.kr/1741000/StanOrgCd2/getStanOrgCdList2"); /*URL*/
		sb.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
		sb.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(Integer.toString(pageNo), "UTF-8")); /*페이지번호*/
		sb.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(Integer.toString(numOfRows), "UTF-8")); /*한 페이지 결과 수*/
		sb.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
		sb.append("&" + URLEncoder.encode("full_nm","UTF-8") + "=" + URLEncoder.encode("행정안전부", "UTF-8")); /*기관명(옵션)*/
		sb.append("&" + URLEncoder.encode("stop_selt","UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")); /*사용:0, 폐지:1(옵션)*/
        return sb.toString();
	}

	/**
	 * 기관코드 페이지수를 확인한다.
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
            JSONArray jsonArray = (JSONArray) jsonObject.get("StanOrgCd");
            JSONObject headObject = (JSONObject) jsonArray.get(0);
            JSONArray headArray = (JSONArray) headObject.get("head");
            JSONObject object = (JSONObject) headArray.get(0);
    		int totalCount = Integer.parseInt(object.get("totalCount").toString());
    		pageNo = (int) Math.ceil((double) totalCount/1000);

        } else {
        	LOGGER.debug("##### InsttCodeRecptnService.numberOfRows() Error Code >>> " + conn.getResponseCode());
        }

        conn.disconnect();

        return pageNo;
	}

	/**
	 * 기관코드를 수신한다.
	 */
	public static List<HashMap<String, String>> apiLink() throws IOException, ParseException {
		List<HashMap<String, String>> organizationCodeList = new ArrayList<>();

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
	            JSONArray jsonArray = (JSONArray) jsonObject.get("StanOrgCd");
	            JSONObject bodyObject = (JSONObject) jsonArray.get(1);
	            JSONArray row = (JSONArray) bodyObject.get("row");

	            for (int r = 0; r < row.size(); r++) {
	            	JSONObject object = (JSONObject) row.get(r);
	            	HashMap<String, String> organizationCode = new HashMap<>();
	            	organizationCode.put("orgCd", stringValueOf(object.get("org_cd")));
	    			organizationCode.put("fullNm",stringValueOf(object.get("full_nm")));
	    			organizationCode.put("lowNm", stringValueOf(object.get("low_nm")));
	    			organizationCode.put("abbrNm", stringValueOf(object.get("abbr_nm")));
	    			organizationCode.put("gapNo", stringValueOf(object.get("gap_no")));
	    			organizationCode.put("rankNo", stringValueOf(object.get("rank_no")));
	    			organizationCode.put("subChasu", stringValueOf(object.get("sub_chasu")));
	    			organizationCode.put("highCd", stringValueOf(object.get("high_cd")));
	    			organizationCode.put("highstCd", stringValueOf(object.get("highst_cd")));
	    			organizationCode.put("repCd", stringValueOf(object.get("rep_cd")));
	    			organizationCode.put("typebigNm", stringValueOf(object.get("typebig_nm")));
	    			organizationCode.put("typemidNm",  stringValueOf(object.get("typemid_nm")));
	    			organizationCode.put("typesmlNm",  stringValueOf(object.get("typesml_nm")));
	    			organizationCode.put("locatstdCd", stringValueOf(object.get("locatstd_cd")));
	    			organizationCode.put("useCd", stringValueOf(object.get("use_cd")));
	    			organizationCode.put("crtDe", stringValueOf(object.get("crt_de")));
	    			organizationCode.put("clsDe", stringValueOf(object.get("cls_de")));
	    			organizationCode.put("stopSelt", stringValueOf(object.get("stop_selt")));
	    			organizationCode.put("chgDe", stringValueOf(object.get("chg_de")));
	    			organizationCode.put("baseDate", stringValueOf(object.get("base_date")));
	    			organizationCode.put("adptDate", stringValueOf(object.get("adpt_date")));
	    			organizationCode.put("preorgCd", stringValueOf(object.get("preorg_cd")));
	    			organizationCodeList.add(organizationCode);
	    		}

	        } else {
	        	LOGGER.debug("##### InsttCodeRecptnService.apiLink() Error Code >>> " + conn.getResponseCode());
	        }

	        conn.disconnect();
		}

        return organizationCodeList;
	}

	private static String stringValueOf(Object object) {
		return object == null ? "" : String.valueOf(object);
	}

	/**
	 * 기관코드 상세내역을 조회한다.
	 */
	public InsttCodeRecptn selectInsttCodeDetail(InsttCodeRecptn insttCodeRecptn) throws Exception {
		InsttCodeRecptn ret = (InsttCodeRecptn) insttCodeRecptnDAO.selectInsttCodeDetail(insttCodeRecptn);
		return ret;
	}

	/**
	 * 기관코드수신 목록을 조회한다.
	 */
	public List<EgovMap> selectInsttCodeRecptnList(InsttCodeRecptnVO searchVO) throws Exception {
		return insttCodeRecptnDAO.selectInsttCodeRecptnList(searchVO);
	}

	/**
	 * 기관코드수신 총 개수를 조회한다.
	 */
	public int selectInsttCodeRecptnListTotCnt(InsttCodeRecptnVO searchVO) throws Exception {
		return insttCodeRecptnDAO.selectInsttCodeRecptnListTotCnt(searchVO);
	}

	/**
	 * 기관코드 목록을 조회한다.
	 */
	public List<EgovMap> selectInsttCodeList(InsttCodeRecptnVO searchVO) throws Exception {
		return insttCodeRecptnDAO.selectInsttCodeList(searchVO);
	}

	/**
	 * 기관코드 총 개수를 조회한다.
	 */
	public int selectInsttCodeListTotCnt(InsttCodeRecptnVO searchVO) throws Exception {
		return insttCodeRecptnDAO.selectInsttCodeListTotCnt(searchVO);
	}

}
