package egovframework.com.uss.ion.rss.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.uss.ion.rss.service.RssManage;
import egovframework.com.utl.fcc.service.EgovStringUtil;

/**
 * RSS태그관리를 처리하는 Dao Class 구현
 * @author 공통콤포넌트 장동한
 * @since 2010.06.16
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *   2011.10.18  서준식          Altibase DB 처리를 위한 코드 추가
 *   2018.10.22  신용호          connection close 관련 수정
 *   2018.12.05  신용호          selectRssTagManageTableList(),selectRssTagManageTableColumnList() 테이블 목록 화이트리스트 제약
 *   2019.05.10  신용호          WhiteList 기능 보완
 * </pre>
 */
@Repository("rssManageDao")
public class RssTagManageDao extends EgovComAbstractDAO {

	// RSS에 허용할 테이블 목록 - context-whitelist.xml에서 관리
	@Resource(name = "egovRSSWhitelist")
	protected List<String> tableWhiteList;

	/**
	 * JDBC 테이블 목록을조회한다.
	 * @return List -조회한목록이담긴List
	 * @throws Exception
	 */
	public List<ComDefaultCodeVO> selectRssTagManageTableList() throws Exception {

		String TABLE_NAME = "TABLE_NAME";
		String TABLE_SCHEMA = "TABLE_SCHEM";
		String[] TABLE_AND_VIEW_TYPES = { "TABLE", "VIEW" };
		ArrayList<ComDefaultCodeVO> arrListResult = new ArrayList<ComDefaultCodeVO>();

		Connection conn = null;
		DatabaseMetaData dbmd = null;
		ResultSet tables = null;

		try {

			conn = getSqlSession().getConnection(); // getSqlMapClientTemplate().getDataSource().getConnection();
			dbmd = conn.getMetaData();

			tables = dbmd.getTables(null, null, null, TABLE_AND_VIEW_TYPES);
			while (tables.next()) {

				// KISA 보안약점 조치 (2018-12-05, 신용호)
				String tableName = tables.getString(TABLE_NAME);
				if (tableName==null) tableName="";
				// WhiteList 기능 보완 (2019-05-10, 신용호)
				if ( tableWhiteList.contains(tableName.toLowerCase()) == true ) {
					ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
					codeVO.setCode(tables.getString(TABLE_NAME));
					codeVO.setCodeNm(tables.getString(TABLE_SCHEMA));
					arrListResult.add((ComDefaultCodeVO) codeVO);
				}
			}

		} finally {
			//EgovResourceCloseHelper.closeDBObjects(tables, conn);
			EgovResourceCloseHelper.closeDBObjects(tables);
		}

		return arrListResult;

	}

	/**
	 * JDBC 테이블 컬럼 목록을 조회한다.
	 * @param map - 컬럼조회정보
	 * @return List -조회한목록이담긴List
	 * @throws Exception
	 */
	public List<Map<String, String>> selectRssTagManageTableColumnList(Map<String, String> map) throws Exception {

		String sTableName = (String) map.get("tableName");
		String sDbType = (String) map.get("dbType");
		String sSQL = "";
		ArrayList<Map<String, String>> arrListResult = new ArrayList<Map<String, String>>();

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = getSqlSession().getConnection(); //getSqlMapClientTemplate().getDataSource().getConnection();

			// KISA 보안약점 조치 (2018-12-05, 신용호)
			// WhiteList 기능 보완 (2019-05-10, 신용호)
			if ( tableWhiteList.contains(sTableName.toLowerCase()) == true ) {
			
				if ( sDbType.equals("mysql") || sDbType.equals("maria") || sDbType.equals("postgres") ) {
					sSQL = "SELECT * FROM (sTableName) LIMIT 1 ";
					sSQL = sSQL.replace("(sTableName)",sTableName);
					
				} else {
					sSQL = "SELECT * FROM (sTableName) WHERE ROWNUM <= 1 ";
					sSQL = sSQL.replace("(sTableName)",sTableName);
				}
	
				if (!sDbType.equals("altibase")) {//2011.10.18
					st = conn.prepareStatement(sSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				} else {
					st = conn.prepareStatement(sSQL);
				}

				rs = st.executeQuery();
	
				ResultSetMetaData rsMetaData = rs.getMetaData();
				int numberOfColumns = rsMetaData == null ? 0 : rsMetaData.getColumnCount();
	
				for (int i = 1; i < numberOfColumns + 1; i++) {
					Map<String, String> hmResult = new HashMap<String, String>();
					hmResult.put("code", (String) (rsMetaData == null ? "" : EgovStringUtil.isNullToString(rsMetaData.getTableName(i))) );
					hmResult.put("codeNm", (String) (rsMetaData == null ? "" : EgovStringUtil.isNullToString(rsMetaData.getColumnName(i))) );
	
					arrListResult.add(hmResult);
				}
			}
		} finally {
			//EgovResourceCloseHelper.closeDBObjects(rs, st, conn);
			EgovResourceCloseHelper.closeDBObjects(rs, st);
		}

		return arrListResult;
	}

	/**
	 * RSS태그관리를(을) 목록을 한다.
	 * @param rssManage -조회할 정보가 담긴 객체
	 * @return -조회한목록이담긴List
	 * @throws Exception
	 */
	public List<?> selectRssTagManageList(RssManage rssManage) throws Exception {
		return (List<?>) selectList("RssTagManage.selectRssTagManage", rssManage);

	}

	/**
	 * RSS태그관리를(을) 목록 전체 건수를(을) 조회한다.
	 * @param rssManage -조회할 정보가 담긴 객체
	 * @return -조회한건수가담긴Integer
	 * @throws Exception
	 */
	public int selectRssTagManageListCnt(RssManage rssManage) throws Exception {
		return (Integer) selectOne("RssTagManage.selectRssTagManageCnt", rssManage);
	}

	/**
	 * RSS태그관리를(을) 상세조회 한다.
	 * @param rssManage -RSS태그관리 정보가 담김 객체
	 * @return RssManage -RSS태그관리 정보가 담김 객체
	 * @throws Exception
	 */
	public RssManage selectRssTagManageDetail(RssManage rssManage) throws Exception {
		return (RssManage) selectOne("RssTagManage.selectRssTagManageDetail", rssManage);
	}

	/**
	 * RSS태그관리를(을) 등록한다.
	 * @param rssManage -RSS태그관리 정보가 담김 객체
	 * @throws Exception
	 */
	public void insertRssTagManage(RssManage rssManage) throws Exception {
		insert("RssTagManage.insertRssTagManage", rssManage);
	}

	/**
	 * RSS태그관리를(을) 수정한다.
	 * @param rssManage -RSS태그관리 정보가 담김 객체
	 * @throws Exception
	 */
	public void updateRssTagManage(RssManage rssManage) throws Exception {
		update("RssTagManage.updateRssTagManage", rssManage);
	}

	/**
	 * RSS태그관리를(을) 삭제한다.
	 * @param rssManage -RSS태그관리 정보가 담김 객체
	 * @throws Exception
	 */
	public void deleteRssTagManage(RssManage rssManage) throws Exception {
		delete("RssTagManage.deleteRssTagManage", rssManage);
	}

}
