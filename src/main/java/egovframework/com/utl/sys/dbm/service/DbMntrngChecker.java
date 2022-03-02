package egovframework.com.utl.sys.dbm.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import egovframework.com.cmm.util.EgovResourceCloseHelper;

/**
 * @Class Name : EgovDbMntrngChecker.java
 * @Description : DB서비스모니터링을 위한 Check 클래스
 * @Modification Information
 *
 *    수정일       	    수정자         수정내용
 *    ----------  -------   -------------------
 *    2010.07.13   김진만         	최초생성
 *    2017-02-08   이정은         	시큐어코딩(ES) - 시큐어코딩 부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *    2019-12-11   신용호         	KISA 보안약점 조치 (오류 상황 대응 부재)
 *
 * @author  김진만
 * @since 2010.07.13
 * @version
 * @see
 *
 */

public class DbMntrngChecker {

	private static final Logger LOGGER = LoggerFactory.getLogger(DbMntrngChecker.class);

	/**
	 * DB서비스모니터링을 수행한다.
	 * @return 모니터링결과
	 *
	 * @param context 데이타소스 빈을 얻기위한 ApplicationContext
	 * @param dataSourcNm		데이타소스빈 이름
	 * @param ceckSql		수행할 체크SQL
	 *
	 */
	public static DbMntrngResult check(ApplicationContext context, String dataSourcNm, String ceckSql) {

		Connection conn = null;
		PreparedStatement stmt = null;
		DataSource datasource = null;
		ResultSet rs = null;

		try {
			datasource = (DataSource) context.getBean(dataSourcNm);
			conn = datasource.getConnection();
			stmt = conn.prepareStatement(ceckSql);

			//2017.02.08 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			rs = stmt.executeQuery();

			return new DbMntrngResult(true, null);
		} catch (SQLException e) {
			LOGGER.error("DB서비스모니터링 에러", e);
			return new DbMntrngResult(false, e);
		} catch (Exception e) {
			LOGGER.error("DB서비스모니터링 에러", e);
			return new DbMntrngResult(false, e);
		} finally {

			EgovResourceCloseHelper.closeDBObjects(rs, stmt, conn);
			//2022.01 "Exception" should not be caught when not required by called methods 조치
//			if( rs != null ) try {rs.close();}catch(Exception e){ LOGGER.error("rs.close Exception", e); }
//			if( stmt != null ) try {stmt.close();}catch(Exception e){ LOGGER.error("stmt.close Exception", e); }
//			if( conn != null ) try {conn.close();}catch(Exception e){ LOGGER.error("conn.close Exception", e); }
		}

	}

}
