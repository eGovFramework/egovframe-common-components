package egovframework.com.uss.ion.rss.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
import egovframework.com.uss.ion.rss.service.RssManage;

/**
 * RSS태그관리 목록 구문과 건수 구문이 같은 검색조건을 같은 컬럼으로 거르는지 8개 DB 방언에서 검증한다.
 *
 * <p>{@link RssTagManageDao#selectRssTagManageList} 와 {@link RssTagManageDao#selectRssTagManageListCnt} 는
 * 컨트롤러가 화면에서 온 searchCondition 을 가공 없이 담아 넘긴 같은 VO 를 받는다. 그래서 두 구문의
 * 조건 분기가 보는 리터럴이 어긋나면 목록만 걸러지고 건수는 테이블 전체를 세어 페이저가 실제보다
 * 많은 페이지를 그린다.</p>
 *
 * <p>{@code context-mapper.xml} 의 {@code mapperLocations} 가
 * {@code classpath:/egovframework/mapper/com/**}{@code /*_${Globals.DbType}.xml} 이라 런타임에는
 * 방언 매퍼가 하나만 로드된다. 방언별로 매퍼를 직접 파싱해 DB 연결 없이 검증한다.</p>
 */
class EgovRssTagManageCountFilterTest {

	private static final String LIST_STATEMENT_ID = "RssTagManage.selectRssTagManage";

	private static final String COUNT_STATEMENT_ID = "RssTagManage.selectRssTagManageCnt";

	/** EgovRssTagManageList.jsp 의 검색조건 드롭다운이 보내는 값. */
	private static final String[] SEARCH_CONDITIONS = {
		"A.TRGET_SVC_NM", "A.TRGET_SVC_TABLE", "A.HDER_TITLE", "A.HDER_LINK", "A.HDER_DESCRIPTION",
		"A.HDER_TAG", "A.HDER_ETC", "A.BDT_LINK", "A.BDT_DESCRIPTION", "A.BDT_TAG", "A.BDT_ETC"
	};

	/** 방언마다 뒤에 붙는 문자열 결합 표기가 달라 컬럼명까지만 본다. */
	private static final Pattern FILTER_COLUMN = Pattern.compile("AND\\s+(\\S+)\\s+LIKE", Pattern.CASE_INSENSITIVE);

	private static final String NO_FILTER = "조건없음";

	private Configuration loadMapper(String dialect) throws Exception {
		String resource = "egovframework/mapper/com/uss/ion/rss/EgovRssTagManage_SQL_" + dialect + ".xml";
		Configuration configuration = new Configuration();
		configuration.getTypeAliasRegistry().registerAlias("comDefaultVO", ComDefaultVO.class);
		configuration.getTypeAliasRegistry().registerAlias("egovMap", EgovMap.class);
		try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
			new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments()).parse();
		}
		return configuration;
	}

	private String filterColumnOf(Configuration configuration, String statementId, RssManage rssManage) {
		MappedStatement statement = configuration.getMappedStatement(statementId);
		Matcher filter = FILTER_COLUMN.matcher(statement.getBoundSql(rssManage).getSql());
		return filter.find() ? filter.group(1) : NO_FILTER;
	}

	@ParameterizedTest(name = "{0}")
	@ValueSource(strings = { "mysql", "maria", "oracle", "postgres", "tibero", "altibase", "cubrid", "goldilocks" })
	@DisplayName("검색조건 열한 개 모두 목록 구문과 건수 구문이 같은 컬럼을 거른다")
	void countStatementFiltersTheSameColumnAsListStatement(String dialect) throws Exception {
		Configuration configuration = loadMapper(dialect);

		List<String> mismatched = new ArrayList<>();
		for (String searchCondition : SEARCH_CONDITIONS) {
			RssManage rssManage = new RssManage();
			rssManage.setSearchCondition(searchCondition);
			rssManage.setSearchKeyword("rss");

			String listColumn = filterColumnOf(configuration, LIST_STATEMENT_ID, rssManage);
			String countColumn = filterColumnOf(configuration, COUNT_STATEMENT_ID, rssManage);

			if (NO_FILTER.equals(listColumn) || !listColumn.equals(countColumn)) {
				mismatched.add(searchCondition + " 목록=" + listColumn + " 건수=" + countColumn);
			}
		}

		assertTrue(mismatched.isEmpty(), dialect + " 매퍼에서 건수 구문이 목록과 다른 컬럼을 거른다: " + mismatched);
	}

}
