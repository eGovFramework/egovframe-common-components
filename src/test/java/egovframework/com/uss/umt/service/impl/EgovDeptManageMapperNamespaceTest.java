package egovframework.com.uss.umt.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * 부서관리 매퍼가 8개 DB 방언 모두에서 {@link DeptManageDAO}가 호출하는 statement id를
 * 등록하는지 검증한다.
 *
 * <p>{@code context-mapper.xml}의 {@code mapperLocations}가
 * {@code classpath:/egovframework/mapper/com/**}{@code /*_${Globals.DbType}.xml}이므로 런타임에는
 * 방언 매퍼가 하나만 로드된다. 따라서 특정 방언의 매퍼 namespace가 다르면 그 DB에서만
 * {@code Mapped Statements collection does not contain value} 로 기능 전체가 실패한다.</p>
 *
 * <p>매퍼가 완전수식 클래스명과 로컬 resultMap만 사용하므로 실제 DB 연결 없이 매퍼 파싱만으로
 * 검증된다.</p>
 */
class EgovDeptManageMapperNamespaceTest {

	/** DeptManageDAO가 하드코딩해 호출하는 statement id. */
	private static final String[] CALLED_STATEMENT_IDS = {
		"deptManageDAO.selectDeptManageList",
		"deptManageDAO.selectDeptManageListTotCnt",
		"deptManageDAO.selectDeptManage",
		"deptManageDAO.insertDeptManage",
		"deptManageDAO.updateDeptManage",
		"deptManageDAO.deleteDeptManage"
	};

	private Configuration loadMapper(String dialect) throws Exception {
		String resource = "egovframework/mapper/com/uss/umt/EgovDeptManage_SQL_" + dialect + ".xml";
		Configuration configuration = new Configuration();
		try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
			new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments()).parse();
		}
		return configuration;
	}

	@ParameterizedTest(name = "{0}")
	@ValueSource(strings = { "mysql", "maria", "oracle", "postgres", "tibero", "altibase", "cubrid", "goldilocks" })
	@DisplayName("모든 방언 매퍼가 DeptManageDAO가 호출하는 statement id를 등록한다")
	void mapperRegistersStatementIdsCalledByDao(String dialect) throws Exception {
		Configuration configuration = loadMapper(dialect);

		List<String> missing = new ArrayList<>();
		for (String statementId : CALLED_STATEMENT_IDS) {
			if (!configuration.hasStatement(statementId, false)) {
				missing.add(statementId);
			}
		}

		assertTrue(missing.isEmpty(),
			dialect + " 매퍼에 DeptManageDAO가 호출하는 statement가 등록되지 않았다: " + missing);
	}
}
