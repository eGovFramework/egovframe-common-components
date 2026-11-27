package egovframework.com.uss.ion.evt.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.Configuration;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.ion.evt.service.EventManageVO;

/**
 * 행사접수 목록과 총 건수가 8개 DB 방언 모두에서 같은 신청자로 참가행을 붙이는지 검증한다.
 *
 * <p>{@code EgovEventManageController.selectEventAtdrnList} 는 한 VO 로 목록을
 * {@code selectEventAtdrnList} 로, 페이저에 넣을 총 건수를 {@code selectEventAtdrnListTotCnt} 로
 * 각각 조회한다. 두 구문이 참가행을 다른 값으로 붙이면 목록에 있는 행이 건수에서 빠져
 * 페이저가 목록과 어긋난 페이지 수를 그린다.</p>
 *
 * <p>{@code context-mapper.xml} 의 {@code mapperLocations} 가
 * {@code classpath:/egovframework/mapper/com/**}{@code /*_${Globals.DbType}.xml} 이라 런타임에는
 * 방언 매퍼가 하나만 로드된다. 방언별로 매퍼를 직접 파싱해 DB 연결 없이 검증한다.</p>
 */
class EgovEventAtdrnListTotCntJoinTest {

	private static final String LIST_STATEMENT_ID = "eventManageDAO.selectEventAtdrnList";

	private static final String TOT_CNT_STATEMENT_ID = "eventManageDAO.selectEventAtdrnListTotCnt";

	/** 두 구문이 참가행을 붙이는 구간이다. 조인으로 시작해 검색조건이 열리는 자리에서 끝난다. */
	private static final Pattern ATDRN_JOIN = Pattern
			.compile("(left\\s+join\\s+COMTNEVENTATDRN.*?)WHERE", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

	private Configuration loadMapper(String dialect) throws Exception {
		String resource = "egovframework/mapper/com/uss/ion/evt/EgovEventManage_SQL_" + dialect + ".xml";
		Configuration configuration = new Configuration();
		configuration.getTypeAliasRegistry().registerAlias("comDefaultVO", ComDefaultVO.class);
		configuration.getTypeAliasRegistry().registerAlias("egovMap", EgovMap.class);
		try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
			new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments()).parse();
		}
		return configuration;
	}

	/**
	 * 바인딩 SQL 은 {@code #{}} 를 {@code ?} 로 바꿔 어느 값을 묶었는지를 지운다. 이 검증의 대상이
	 * 바로 그 "어느 값" 이라 파라미터 이름을 자리표시자에 되돌려 놓는다.
	 */
	private String atdrnJoinOf(Configuration configuration, String statementId, Object parameter) {
		MappedStatement statement = configuration.getMappedStatement(statementId);
		BoundSql boundSql = statement.getBoundSql(parameter);
		String sql = boundSql.getSql();

		StringBuilder named = new StringBuilder();
		int cursor = 0;
		for (ParameterMapping parameterMapping : boundSql.getParameterMappings()) {
			int placeholder = sql.indexOf('?', cursor);
			named.append(sql, cursor, placeholder).append("#{").append(parameterMapping.getProperty()).append('}');
			cursor = placeholder + 1;
		}
		named.append(sql.substring(cursor));

		Matcher matcher = ATDRN_JOIN.matcher(named);
		assertTrue(matcher.find(), statementId + " 이 참가행을 조인하지 않는다: " + named);
		return matcher.group(1).replaceAll("\\s+", " ").trim();
	}

	@ParameterizedTest(name = "{0}")
	@ValueSource(strings = { "mysql", "maria", "oracle", "postgres", "tibero", "altibase", "cubrid", "goldilocks" })
	@DisplayName("행사접수 목록과 총 건수는 방언과 무관하게 같은 신청자로 참가행을 붙인다")
	void listAndTotalCountJoinOnTheSameApplicant(String dialect) throws Exception {
		Configuration configuration = loadMapper(dialect);

		// 로그인한 사용자가 2026년 9월 행사접수관리 목록을 연 상태다. 컨트롤러가 조회 직전에
		// searchKeyword 에는 검색 연월을, applcntId 에는 그 사용자의 고유 ID 를 넣는다.
		EventManageVO eventManageVO = new EventManageVO();
		eventManageVO.setSearchYear("2026");
		eventManageVO.setSearchMonth("09");
		eventManageVO.setSearchKeyword("202609");
		eventManageVO.setApplcntId("USRCNFRM_00000000000");

		String listJoin = atdrnJoinOf(configuration, LIST_STATEMENT_ID, eventManageVO);
		String totCntJoin = atdrnJoinOf(configuration, TOT_CNT_STATEMENT_ID, eventManageVO);

		System.out.println("[" + dialect + "] 목록 " + listJoin);
		System.out.println("[" + dialect + "] 건수 " + totCntJoin);

		assertEquals(listJoin, totCntJoin, dialect + " 매퍼의 총 건수가 목록과 다른 신청자로 참가행을 붙인다");
	}

}
