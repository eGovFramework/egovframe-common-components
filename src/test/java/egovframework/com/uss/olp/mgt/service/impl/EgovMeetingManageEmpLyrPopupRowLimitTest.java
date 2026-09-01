package egovframework.com.uss.olp.mgt.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
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

/**
 * 직원검색 팝업 목록이 8개 DB 방언 모두에서 100행 상한을 거는지 검증한다.
 *
 * <p>{@link MeetingManageDao#egovMeetingManageLisEmpLyrPopup} 이 호출하는 목록은 팝업 JSP 가
 * 페이징 없이 {@code c:forEach} 로 전부 렌더한다. 그래서 매퍼가 상한을 걸지 않으면 그 방언으로
 * 기동했을 때만 직원 테이블 전체가 응답에 실린다.</p>
 *
 * <p>{@code context-mapper.xml} 의 {@code mapperLocations} 가
 * {@code classpath:/egovframework/mapper/com/**}{@code /*_${Globals.DbType}.xml} 이라 런타임에는
 * 방언 매퍼가 하나만 로드된다. 방언별로 매퍼를 직접 파싱해 DB 연결 없이 검증한다.</p>
 */
class EgovMeetingManageEmpLyrPopupRowLimitTest {

	private static final String STATEMENT_ID = "MeetingManage.EgovMeetingManageLisEmpLyrPopup";

	/** 방언마다 표기가 다른 100행 상한. */
	private static final Pattern ROW_LIMIT = Pattern.compile(
			"LIMIT\\s+(?:0\\s*,\\s*)?100\\b|R(?:OW)?NUM\\s*<=\\s*100\\b", Pattern.CASE_INSENSITIVE);

	private Configuration loadMapper(String dialect) throws Exception {
		String resource = "egovframework/mapper/com/uss/olp/mgt/EgovMeetingManage_SQL_" + dialect + ".xml";
		Configuration configuration = new Configuration();
		configuration.getTypeAliasRegistry().registerAlias("comDefaultVO", ComDefaultVO.class);
		configuration.getTypeAliasRegistry().registerAlias("egovMap", EgovMap.class);
		try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
			new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments()).parse();
		}
		return configuration;
	}

	@ParameterizedTest(name = "{0}")
	@ValueSource(strings = { "mysql", "maria", "oracle", "postgres", "tibero", "altibase", "cubrid", "goldilocks" })
	@DisplayName("검색어 없이 연 직원검색 팝업 목록은 방언과 무관하게 100행 상한을 건다")
	void empLyrPopupListCapsRowsWhenSearchKeywordIsEmpty(String dialect) throws Exception {
		MappedStatement statement = loadMapper(dialect).getMappedStatement(STATEMENT_ID);

		// ComDefaultVO 의 searchKeyword 기본값이 빈 문자열이다. 팝업을 막 열었을 때와 같다.
		String sql = statement.getBoundSql(new ComDefaultVO()).getSql();

		assertTrue(ROW_LIMIT.matcher(sql).find(), dialect + " 매퍼에 100행 상한이 없다: " + sql);
	}

}
