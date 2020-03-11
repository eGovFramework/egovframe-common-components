package egovframework.com.cop.sms.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import egovframework.com.cop.sms.service.Sms;
import egovframework.com.cop.sms.service.SmsRecptn;
import egovframework.com.cop.sms.service.SmsVO;

/**
 * 문자메시지를 위한 데이터 접근 클래스 (프레임워크 비종속 버전)
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.11.24
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.11.24  한성곤          최초 생성
 *
 * </pre>
 */
public class SmsBasicDAO {
	/**
	 * 문자메시지 목록을 조회한다.
	 * 
	 * @param SmsVO
	 */
	public List<SmsVO> selectSmsInfs(SmsVO vo) throws Exception {
		List<SmsVO> list = new ArrayList<SmsVO>();

		//variables
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer buffer = new StringBuffer();

		try {
			// for mySql
			buffer.append("SELECT\n");
			buffer.append("  a.SMS_ID, a.TRNSMIS_TELNO, a.TRNSMIS_CN,\n");
			buffer.append("  (SELECT COUNT(*) FROM COMTNSMSRECPTN s WHERE s.SMS_ID = a.SMS_ID) as RECPTN_CNT,\n");
			buffer.append("  DATE_FORMAT(a.FRST_REGIST_PNTTM, '%Y-%m-%d %H:%i:%S') as FRST_REGIST_PNTTM\n");
			buffer.append("FROM COMTNSMS a\n");
			buffer.append("WHERE 1=1\n");

			if ("0".equals(vo.getSearchCnd())) {
				if (!"".equals(vo.getSearchWrd())) {
					buffer.append("  AND a.SMS_ID in (SELECT SMS_ID FROM COMTNSMSRECPTN WHERE RECPTN_TELNO LIKE CONCAT ('%', ?,'%'))\n");
				}
			} else if ("1".equals(vo.getSearchCnd())) {
				buffer.append("  AND a.TRNSMIS_CN LIKE CONCAT ('%', #searchWrd#,'%')\n");
			}

			buffer.append("ORDER BY a.FRST_REGIST_PNTTM DESC\n");
			buffer.append("LIMIT ? OFFSET ?");

			// for Oracle
			/*
			buffer.append("SELECT * FROM ( SELECT rownum rn, TB.* FROM (\n");
			buffer.append("SELECT\n");
			buffer.append("  a.SMS_ID, a.TRNSMIS_TELNO, a.TRNSMIS_CN,\n");
			buffer.append("  (SELECT COUNT(*) FROM COMTNSMSRECPTN s WHERE s.SMS_ID = a.SMS_ID) as RECPTN_CNT,\n");
			buffer.append("  TO_CHAR(a.FRST_REGIST_PNTTM, 'YYYY-MM-DD HH24:MI:SS') as FRST_REGIST_PNTTM\n");
			buffer.append("FROM COMTNSMS a\n");
			buffer.append("WHERE 1=1\n");

			if ("0".equals(vo.getSearchCnd())) {
			if (!"".equals(vo.getSearchWrd())) {
			    buffer.append("  AND a.SMS_ID in (SELECT SMS_ID FROM COMTNSMSRECPTN WHERE RECPTN_TELNO LIKE '%' || ? || '%')\n");
			}
			} else if ("1".equals(vo.getSearchCnd())) {
			buffer.append("  AND a.TRNSMIS_CN LIKE '%' || ? || '%'\n");
			}

			buffer.append("ORDER BY a.FRST_REGIST_PNTTM DESC\n");
			buffer.append(") TB ) WHERE rn BETWEEN ? + 1 AND ? + ?");
			*/

			conn = SmsBasicDBUtil.getConnection();

			pstmt = conn.prepareStatement(buffer.toString());

			int index = 0;

			if ("0".equals(vo.getSearchCnd())) {
				if (!"".equals(vo.getSearchWrd())) {
					pstmt.setString(++index, vo.getSearchWrd());
				}
			} else if ("1".equals(vo.getSearchCnd())) {
				pstmt.setString(++index, vo.getSearchWrd());
			}

			// for mySql
			pstmt.setInt(++index, vo.getRecordCountPerPage());
			pstmt.setInt(++index, vo.getFirstIndex());

			// for Oracle
			/*
			pstmt.setInt(++index, vo.getFirstIndex());
			pstmt.setInt(++index, vo.getFirstIndex());
			pstmt.setInt(++index, vo.getRecordCountPerPage());
			*/

			rs = pstmt.executeQuery();

			SmsVO result = null;

			while (rs.next()) {
				result = new SmsVO();

				result.setSmsId(rs.getString("SMS_ID"));
				result.setTrnsmitTelno(rs.getString("TRNSMIS_TELNO"));
				result.setTrnsmitCn(rs.getString("TRNSMIS_CN"));
				result.setRecptnCnt(rs.getInt("RECPTN_CNT"));
				result.setFrstRegisterPnttm(rs.getString("FRST_REGIST_PNTTM"));

				list.add(result);
			}

			return list;

		} finally {
			SmsBasicDBUtil.close(rs, pstmt, conn);
		}
	}

	/**
	 * 문자메시지 목록 숫자를 조회한다
	 * 
	 * @param SmsVO
	 * @return
	 * @throws Exception
	 */
	public int selectSmsInfsCnt(SmsVO vo) throws Exception {
		//variables
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer buffer = new StringBuffer();

		try {
			// for mySql
			buffer.append("SELECT\n");
			buffer.append("  COUNT(a.SMS_ID) as cnt\n");
			buffer.append("FROM COMTNSMS a\n");
			buffer.append("WHERE 1=1\n");

			if ("0".equals(vo.getSearchCnd())) {
				if (!"".equals(vo.getSearchWrd())) {
					buffer.append("  AND a.SMS_ID in (SELECT SMS_ID FROM COMTNSMSRECPTN WHERE RECPTN_TELNO LIKE CONCAT ('%', ?,'%'))\n");
				}
			} else if ("1".equals(vo.getSearchCnd())) {
				buffer.append("  AND a.TRNSMIS_CN LIKE CONCAT ('%', #searchWrd#,'%')\n");
			}

			// for Oracle
			/*
			buffer.append("SELECT\n");
			buffer.append("  COUNT(a.SMS_ID) as cnt\n");
			buffer.append("FROM COMTNSMS a\n");
			buffer.append("WHERE 1=1\n");

			if ("0".equals(vo.getSearchCnd())) {
			if (!"".equals(vo.getSearchWrd())) {
			    buffer.append("  AND a.SMS_ID in (SELECT SMS_ID FROM COMTNSMSRECPTN WHERE RECPTN_TELNO LIKE '%' || ? || '%')\n");
			}
			} else if ("1".equals(vo.getSearchCnd())) {
			buffer.append("  AND a.TRNSMIS_CN LIKE '%' || ? || '%'\n");
			}
			*/

			conn = SmsBasicDBUtil.getConnection();

			pstmt = conn.prepareStatement(buffer.toString());

			int index = 0;

			if ("0".equals(vo.getSearchCnd())) {
				if (!"".equals(vo.getSearchWrd())) {
					pstmt.setString(++index, vo.getSearchWrd());
				}
			} else if ("1".equals(vo.getSearchCnd())) {
				pstmt.setString(++index, vo.getSearchWrd());
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("cnt");
			}

			return 0;

		} finally {
			SmsBasicDBUtil.close(rs, pstmt, conn);
		}
	}

	/**
	 * 문자메시지 정보를 등록한다.
	 * 
	 * @param notification
	 * @return
	 * @throws Exception
	 */
	public String insertSmsInf(Sms sms) throws Exception {
		String smsId = null;

		//variables
		Connection conn = null;
		PreparedStatement pstmt = null;

		StringBuffer buffer = new StringBuffer();

		try {
			// for mySql
			buffer.append("INSERT INTO COMTNSMS\n");
			buffer.append("  (SMS_ID, TRNSMIS_TELNO, TRNSMIS_CN,\n");
			buffer.append("   FRST_REGISTER_ID, FRST_REGIST_PNTTM )\n");
			buffer.append("VALUES\n");
			buffer.append("(?, ?, ?, ?, SYSDATE())");

			// for Oracle
			/*
			buffer.append("INSERT INTO COMTNSMS\n");
			buffer.append("  (SMS_ID, TRNSMIS_TELNO, TRNSMIS_CN,\n");
			buffer.append("   FRST_REGISTER_ID, FRST_REGIST_PNTTM )\n");
			buffer.append("VALUES\n");
			buffer.append("(?, ?, ?, ?, SYSDATE)");
			*/

			conn = SmsBasicDBUtil.getConnection();

			conn.setAutoCommit(false);

			smsId = getNextId(conn); // SMS_ID 생성...

			pstmt = conn.prepareStatement(buffer.toString());

			int index = 0;

			pstmt.setString(++index, smsId);
			pstmt.setString(++index, sms.getTrnsmitTelno());
			pstmt.setString(++index, sms.getTrnsmitCn());
			pstmt.setString(++index, sms.getFrstRegisterId());

			pstmt.executeUpdate();

			conn.commit();

			return smsId;
		} catch (SQLException ex) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
			if (conn != null) {
				conn.rollback();
			}
			throw ex;
		} catch (Exception ex) {
			if (conn != null) {
				conn.rollback();
			}
			throw ex;

		} finally {
			SmsBasicDBUtil.close(null, pstmt, conn);
		}
	}

	/**
	 * 문자메시지 수신정보 및 결과 정보를 등록한다.
	 * @param smsRecptn
	 * @throws Exception
	 */
	public void insertSmsRecptnInf(SmsRecptn smsRecptn) throws Exception {
		//variables
		Connection conn = null;
		PreparedStatement pstmt = null;

		StringBuffer buffer = new StringBuffer();

		try {
			// for mySql & Oracle
			buffer.append("INSERT INTO COMTNSMSRECPTN\n");
			buffer.append("  (SMS_ID, RECPTN_TELNO, RESULT_CODE, RESULT_MSSAGE)\n");
			buffer.append("VALUES\n");
			buffer.append("(?, ?, ?, ?)");

			conn = SmsBasicDBUtil.getConnection();

			pstmt = conn.prepareStatement(buffer.toString());

			int index = 0;

			pstmt.setString(++index, smsRecptn.getSmsId());
			pstmt.setString(++index, smsRecptn.getRecptnTelno());
			pstmt.setString(++index, smsRecptn.getResultCode());
			pstmt.setString(++index, smsRecptn.getResultMssage());

			pstmt.executeUpdate();

		} finally {
			SmsBasicDBUtil.close(null, pstmt, conn);
		}
	}

	/**
	 * 문자메시지에 대한 상세정보를 조회한다.
	 * 
	 * @param searchVO
	 * @return
	 */
	public SmsVO selectSmsInf(SmsVO searchVO) throws Exception {
		SmsVO smsVO = new SmsVO();

		//variables
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer buffer = new StringBuffer();

		try {
			// for mySql
			buffer.append("SELECT\n");
			buffer.append("  a.SMS_ID, a.TRNSMIS_TELNO, a.TRNSMIS_CN,\n");
			buffer.append("  a.FRST_REGISTER_ID, b.USER_NM as FRST_REGISTER_NM,\n");
			buffer.append("  DATE_FORMAT(a.FRST_REGIST_PNTTM, '%Y-%m-%d') as FRST_REGIST_PNTTM\n");
			buffer.append("FROM COMTNSMS a\n");
			buffer.append("LEFT OUTER JOIN COMVNUSERMASTER b\n");
			buffer.append("  ON a.FRST_REGISTER_ID = b.ESNTL_ID\n");
			buffer.append("WHERE a.SMS_ID = ?\n");

			// for Oracle
			/*
			buffer.append("SELECT\n");
			buffer.append("  a.SMS_ID, a.TRNSMIS_TELNO, a.TRNSMIS_CN,\n");
			buffer.append("  a.FRST_REGISTER_ID, b.USER_NM as FRST_REGISTER_NM,\n");
			buffer.append("  TO_CHAR(a.FRST_REGIST_PNTTM, 'YYYY-MM-DD') as FRST_REGIST_PNTTM\n");
			buffer.append("FROM COMTNSMS a\n");
			buffer.append("LEFT OUTER JOIN COMVNUSERMASTER b\n");
			buffer.append("  ON a.FRST_REGISTER_ID = b.ESNTL_ID\n");
			buffer.append("WHERE a.SMS_ID = ?\n");
			*/

			conn = SmsBasicDBUtil.getConnection();

			pstmt = conn.prepareStatement(buffer.toString());

			int index = 0;

			pstmt.setString(++index, searchVO.getSmsId());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				smsVO.setSmsId(rs.getString("SMS_ID"));
				smsVO.setTrnsmitTelno(rs.getString("TRNSMIS_TELNO"));
				smsVO.setTrnsmitCn(rs.getString("TRNSMIS_CN"));
				smsVO.setFrstRegisterPnttm(rs.getString("FRST_REGIST_PNTTM"));
			}

			return smsVO;

		} finally {
			SmsBasicDBUtil.close(rs, pstmt, conn);
		}
	}

	/**
	 * 문자메시지 수신 및 결과 목록을 조회한다.
	 * 
	 * @param SmsRecptn
	 */
	public List<SmsRecptn> selectSmsRecptnInfs(SmsRecptn vo) throws Exception {
		List<SmsRecptn> list = new ArrayList<SmsRecptn>();

		//variables
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer buffer = new StringBuffer();

		try {
			// for mySql & Oracle
			buffer.append("SELECT\n");
			buffer.append("  a.SMS_ID, a.RECPTN_TELNO, a.RESULT_CODE, a.RESULT_MSSAGE\n");
			buffer.append("FROM COMTNSMSRECPTN a\n");
			buffer.append("WHERE a.SMS_ID = ?");

			conn = SmsBasicDBUtil.getConnection();

			pstmt = conn.prepareStatement(buffer.toString());

			int index = 0;

			pstmt.setString(++index, vo.getSmsId());

			rs = pstmt.executeQuery();

			SmsRecptn result = null;

			while (rs.next()) {
				result = new SmsRecptn();

				result.setSmsId(rs.getString("SMS_ID"));
				result.setRecptnTelno(rs.getString("RECPTN_TELNO"));
				result.setResultCode(rs.getString("RESULT_CODE"));
				result.setResultMssage(rs.getString("RESULT_MSSAGE"));

				list.add(result);
			}

			return list;

		} finally {
			SmsBasicDBUtil.close(rs, pstmt, conn);
		}
	}

	/**
	 * 문자메시지 전송 결과 수신을 처리한다.
	 * EgovSmsInfoReceiver(Schedule job)에 의해 호출된다.
	 * 
	 * @param smsRecptn
	 * @return
	 * @throws Exception
	 */
	public void updateSmsRecptnInf(SmsRecptn smsRecptn) throws Exception {
		//variables
		Connection conn = null;
		PreparedStatement pstmt = null;

		StringBuffer buffer = new StringBuffer();

		try {
			// for mySql & Oracle
			buffer.append("UPDATE COMTNSMSRECPTN SET\n");
			buffer.append("  RESULT_CODE = ?,\n");
			buffer.append("  RESULT_MSSAGE = ?\n");
			buffer.append("WHERE \n");
			buffer.append("  SMS_ID = ? AND RECPTN_TELNO = ?");

			conn = SmsBasicDBUtil.getConnection();

			pstmt = conn.prepareStatement(buffer.toString());

			int index = 0;

			pstmt.setString(++index, smsRecptn.getResultCode());
			pstmt.setString(++index, smsRecptn.getResultMssage());
			pstmt.setString(++index, smsRecptn.getSmsId());
			pstmt.setString(++index, smsRecptn.getRecptnTelno());

			pstmt.executeUpdate();

		} finally {
			SmsBasicDBUtil.close(null, pstmt, conn);
		}
	}

	/**
	 * ID 처리.
	 * transaction 처리를 위해 Connection을 파라미터로 넘겨받음
	 * 
	 * @return
	 * @throws Exception
	 */
	protected String getNextId(Connection conn) throws Exception {
		//variables
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer buffer = new StringBuffer();

		try {
			// for mySql
			buffer.append("SELECT CONCAT('SMSID_', LPAD(IFNULL(MAX(SUBSTR(SMS_ID, 7, 14)), 0) + 1, 14, '0')) as SMS_ID from COMTNSMS\n");
			buffer.append("WHERE SMS_ID LIKE 'SMSID_%'");

			// for Oracle
			/*
			buffer.append("SELECT CONCAT('SMSID_', LPAD(IFNULL(MAX(SUBSTR(SMS_ID, 7, 14)), 0) + 1, 14, '0')) as SMS_ID from COMTNSMS\n");
			buffer.append("WHERE SMS_ID LIKE 'SMSID_%'");
			*/

			pstmt = conn.prepareStatement(buffer.toString());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getString("SMS_ID");
			}

			return null;

		} finally {
			SmsBasicDBUtil.close(rs, pstmt, null);
		}
	}
}
