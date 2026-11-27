package egovframework.com.uss.ion.bnt.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.ion.bnt.service.BndtCeckManageVO;

/**
 * 당직체크관리 목록과 총 건수가 8개 DB 방언 모두에서 같은 검색조건을 거는지 검증한다.
 *
 * <p>{@code EgovBndtManageController.selectBndtCeckManageList} 는 목록을
 * {@code selectBndtCeckManageList} 로, 페이저에 넣을 총 건수를
 * {@code selectBndtCeckManageListTotCnt} 로 각각 조회한다. 두 구문의 조건이 어긋나면 그 방언으로
 * 기동했을 때만 페이저가 목록에 없는 페이지를 그린다.</p>
 *
 * <p>{@code context-mapper.xml} 의 {@code mapperLocations} 가
 * {@code classpath:/egovframework/mapper/com/**}{@code /*_${Globals.DbType}.xml} 이라 런타임에는
 * 방언 매퍼가 하나만 로드된다. 방언별로 매퍼를 직접 파싱해 DB 연결 없이 검증한다.</p>
 */
class EgovBndtCeckManageTotCntConditionTest {

	private static final String LIST_STATEMENT_ID = "bndtManageDAO.selectBndtCeckManageList";

	private static final String TOT_CNT_STATEMENT_ID = "bndtManageDAO.selectBndtCeckManageListTotCnt";

	/**
	 * 두 구문의 검색조건은 {@code WHERE 1=1} 뒤에서 시작해 정렬 앞에서 끝난다.
	 * 건수 구문에는 열 목록도 정렬·페이징 꼬리도 없어 조건 부분만 비교한다.
	 */
	private static final Pattern SEARCH_CONDITION = Pattern.compile("WHERE\\s+1=1(.*?)(?:ORDER\\s+BY|$)",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

	private Configuration loadMapper(String dialect) throws Exception {
		String resource = "egovframework/mapper/com/uss/ion/bnt/EgovBndtManage_SQL_" + dialect + ".xml";
		Configuration configuration = new Configuration();
		configuration.getTypeAliasRegistry().registerAlias("comDefaultVO", ComDefaultVO.class);
		configuration.getTypeAliasRegistry().registerAlias("egovMap", EgovMap.class);
		try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
			new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments()).parse();
		}
		return configuration;
	}

	private String searchConditionOf(Configuration configuration, String statementId, Object parameter) {
		MappedStatement statement = configuration.getMappedStatement(statementId);
		String sql = statement.getBoundSql(parameter).getSql();

		Matcher matcher = SEARCH_CONDITION.matcher(sql);
		assertTrue(matcher.find(), statementId + " 이 WHERE 1=1 로 조건을 열지 않는다: " + sql);
		return matcher.group(1).replaceAll("\\s+", " ").trim();
	}

	@ParameterizedTest(name = "{0}")
	@ValueSource(strings = { "mysql", "maria", "oracle", "postgres", "tibero", "altibase", "cubrid", "goldilocks" })
	@DisplayName("사용여부로 거른 당직체크 목록과 총 건수는 방언과 무관하게 같은 조건을 건다")
	void listAndTotalCountFilterOnTheSameCondition(String dialect) throws Exception {
		Configuration configuration = loadMapper(dialect);

		// 목록 화면의 사용여부 select 로 '사용'만 골라 조회한 상태다.
		BndtCeckManageVO bndtCeckManageVO = new BndtCeckManageVO();
		bndtCeckManageVO.setSearchUseAt("Y");

		String listCondition = searchConditionOf(configuration, LIST_STATEMENT_ID, bndtCeckManageVO);
		String totCntCondition = searchConditionOf(configuration, TOT_CNT_STATEMENT_ID, bndtCeckManageVO);

		System.out.println("[" + dialect + "] 목록 " + listCondition);
		System.out.println("[" + dialect + "] 건수 " + totCntCondition);

		assertEquals(listCondition, totCntCondition,
				dialect + " 매퍼의 총 건수가 목록과 다른 조건을 센다");
	}

}
