package egovframework.com.utl.sys;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.InputStream;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.utl.sys.dbm.service.DbMntrng;
import egovframework.com.utl.sys.trm.service.TrsmrcvMntrng;

/**
 * DB모니터링·송수신모니터링의 목록과 총 건수가 8개 DB 방언 모두에서 같은 검색조건을 거는지 검증한다.
 *
 * <p>두 화면 모두 목록을 {@code select...List} 로, 페이저에 넣을 총 건수를 {@code select...ListCnt} 로
 * 각각 조회한다. 두 구문의 조건이 어긋나면 그 검색조건을 고른 화면에서만 페이저가 목록과 다른 페이지 수를
 * 그린다.</p>
 *
 * <p>{@code context-mapper.xml} 의 {@code mapperLocations} 가
 * {@code classpath:/egovframework/mapper/com/**}{@code /*_${Globals.DbType}.xml} 이라 런타임에는
 * 방언 매퍼가 하나만 로드된다. 방언별로 매퍼를 직접 파싱해 DB 연결 없이 검증한다.</p>
 */
class EgovMntrngListCntConditionTest {


	private Configuration loadMapper(String resource) throws Exception {
		Configuration configuration = new Configuration();
		configuration.getTypeAliasRegistry().registerAlias("comDefaultVO", ComDefaultVO.class);
		configuration.getTypeAliasRegistry().registerAlias("egovMap", EgovMap.class);
		try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
			new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments()).parse();
		}
		return configuration;
	}

	private String sqlOf(Configuration configuration, String statementId, Object parameter) {
		return configuration.getMappedStatement(statementId).getBoundSql(parameter).getSql();
	}

	/**
	 * 어느 {@code <if>} 에도 걸리지 않는 검색조건이다. 빈 문자열은 쓸 수 없다 — OGNL 이
	 * {@code "" == 0} 을 참으로 보아 첫 분기가 켜진다.
	 */
	private static final String NO_CONDITION = "9";

	/**
	 * 검색조건이 얹은 절만 뽑는다. 검색조건을 넣은 SQL 과 아무 조건도 걸리지 않는 SQL 의 공통 앞·뒤를
	 * 걷어내면 남는 것이 {@code <if>} 가 끼워넣은 절이다. 구문마다 다른 고정 조건·정렬·페이징 껍데기에
	 * 기대지 않는 방법이다.
	 */
	private String searchClauseOf(Configuration configuration, String statementId, Object withSearch, Object noSearch) {
		String full = sqlOf(configuration, statementId, withSearch);
		String bare = sqlOf(configuration, statementId, noSearch);

		int head = 0;
		while (head < full.length() && head < bare.length() && full.charAt(head) == bare.charAt(head)) {
			head++;
		}
		int tail = 0;
		while (tail < full.length() - head && tail < bare.length() - head
				&& full.charAt(full.length() - 1 - tail) == bare.charAt(bare.length() - 1 - tail)) {
			tail++;
		}
		return full.substring(head, full.length() - tail).replaceAll("\\s+", " ").trim();
	}

	private void assertSameSearchClause(String dialect, String resource, String listId, String cntId, Object withSearch,
			Object noSearch) throws Exception {
		Configuration configuration = loadMapper(resource);

		String listClause = searchClauseOf(configuration, listId, withSearch, noSearch);
		String cntClause = searchClauseOf(configuration, cntId, withSearch, noSearch);

		System.out.println("[" + dialect + "] 목록 " + listClause);
		System.out.println("[" + dialect + "] 건수 " + cntClause);

		// 두 구문에서 조건이 함께 사라지면 양쪽 다 빈 문자열이 되어 조용히 통과한다.
		assertFalse(listClause.isEmpty(), dialect + " 매퍼의 목록이 검색조건을 걸지 않는다");
		assertEquals(listClause, cntClause, dialect + " 매퍼의 총 건수가 목록과 다른 조건을 센다");
	}

	@ParameterizedTest(name = "{0}")
	@ValueSource(strings = { "mysql", "maria", "oracle", "postgres", "tibero", "altibase", "cubrid", "goldilocks" })
	@DisplayName("상태로 거른 DB모니터링 목록과 총 건수는 방언과 무관하게 같은 조건을 건다")
	void dbMntrngListAndCountFilterOnTheSameCondition(String dialect) throws Exception {
		// 목록 화면의 검색조건 셀렉트에서 '상태'(EgovDbMntrngList.jsp 의 value="3")를 골라 조회한 상태다.
		DbMntrng withSearch = new DbMntrng();
		withSearch.setSearchCondition("3");
		withSearch.setSearchKeyword("정상");

		DbMntrng noSearch = new DbMntrng();
		noSearch.setSearchCondition(NO_CONDITION);

		assertSameSearchClause(dialect, "egovframework/mapper/com/utl/sys/dbm/EgovDbMntrng_SQL_" + dialect + ".xml",
				"DbMntrngDao.selectDbMntrngList", "DbMntrngDao.selectDbMntrngListCnt", withSearch, noSearch);
	}

	@ParameterizedTest(name = "{0}")
	@ValueSource(strings = { "mysql", "maria", "oracle", "postgres", "tibero", "altibase", "cubrid", "goldilocks" })
	@DisplayName("상태로 거른 송수신모니터링 목록과 총 건수는 방언과 무관하게 같은 조건을 건다")
	void trsmrcvMntrngListAndCountFilterOnTheSameCondition(String dialect) throws Exception {
		// 목록 화면의 검색조건 셀렉트에서 '상태'(EgovTrsmrcvMntrngList.jsp 의 value="3")를 골라 조회한 상태다.
		TrsmrcvMntrng withSearch = new TrsmrcvMntrng();
		withSearch.setSearchCondition("3");
		withSearch.setSearchKeyword("정상");

		TrsmrcvMntrng noSearch = new TrsmrcvMntrng();
		noSearch.setSearchCondition(NO_CONDITION);

		assertSameSearchClause(dialect,
				"egovframework/mapper/com/utl/sys/trm/EgovTrsmrcvMntrng_SQL_" + dialect + ".xml",
				"TrsmrcvMntrngDao.selectTrsmrcvMntrngList", "TrsmrcvMntrngDao.selectTrsmrcvMntrngListCnt", withSearch,
				noSearch);
	}

}
