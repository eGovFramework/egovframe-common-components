package egovframework.com.uss.ion.vct.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;
import java.util.Set;
import java.util.TreeSet;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * 휴가관리 상세조회는 방언과 무관하게 신청자·휴가구분·시작일·종료일 네 키로 한 건을 찾아야 한다.
 *
 * <p>{@code EgovVcatnManageController.selectVcatnManage}(상세보기 화면 진입점)는
 * {@code vcatnManageDAO.selectVcatnManage} 를 MyBatis {@code selectOne} 으로 호출한다.
 * 이 구문의 WHERE 절이 키 하나를 빼면, 같은 신청자·구분·시작일로 두 건 이상이 존재할 때
 * (반려 후 재신청 등으로 시작일은 같고 종료일만 다른 경우) 그 방언에서만
 * {@code TooManyResultsException} 이 나 상세보기가 깨진다.</p>
 *
 * <p>{@code context-mapper.xml} 의 {@code mapperLocations} 가
 * {@code classpath:/egovframework/mapper/com/**}{@code /*_${Globals.DbType}.xml} 이라 런타임에는
 * 방언 매퍼가 하나만 로드된다. 방언별로 매퍼를 직접 파싱해 DB 연결 없이 검증한다.</p>
 */
class EgovVcatnManageDetailKeyTest {

	private static final String STATEMENT_ID = "vcatnManageDAO.selectVcatnManage";

	private static final Set<String> EXPECTED_KEYS = new TreeSet<>(Set.of("applcntId", "vcatnSe", "bgnde", "endde"));

	private Configuration loadMapper(String dialect) throws Exception {
		String resource = "egovframework/mapper/com/uss/ion/vct/EgovVcatnManage_SQL_" + dialect + ".xml";
		Configuration configuration = new Configuration();
		try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
			new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments()).parse();
		}
		return configuration;
	}

	@ParameterizedTest(name = "{0}")
	@ValueSource(strings = { "mysql", "maria", "oracle", "postgres", "tibero", "altibase", "cubrid", "goldilocks" })
	@DisplayName("휴가 상세조회는 방언과 무관하게 신청자·구분·시작일·종료일 네 키를 모두 바인딩한다")
	void selectVcatnManageBindsAllFourKeys(String dialect) throws Exception {
		Configuration configuration = loadMapper(dialect);
		MappedStatement statement = configuration.getMappedStatement(STATEMENT_ID);

		Set<String> boundKeys = new TreeSet<>();
		for (ParameterMapping parameterMapping : statement.getBoundSql(null).getParameterMappings()) {
			boundKeys.add(parameterMapping.getProperty());
		}

		assertEquals(EXPECTED_KEYS, boundKeys, dialect + " 이 selectVcatnManage 조회조건에서 키를 뺀다: " + boundKeys);
	}

}
