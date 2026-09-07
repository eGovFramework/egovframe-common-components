package egovframework.com.cop.adb.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cop.adb.service.AddressBookVO;

/**
 * 주소록 목록과 총 건수가 8개 DB 방언 모두에서 같은 공개범위 값으로 회사 공개를 가리는지 검증한다.
 *
 * <p>{@code EgovAddressBookController.selectAdressBookList} 는 목록을 {@code selectAdressBookList} 로,
 * 페이저에 넣을 총 건수를 {@code selectAdressBookListCnt} 로 각각 조회한다. 두 구문이 회사 공개를
 * 다른 값으로 판단하면 한쪽에만 잡히는 주소록이 생겨 페이저가 목록과 어긋난다.</p>
 *
 * <p>{@code context-mapper.xml} 의 {@code mapperLocations} 가
 * {@code classpath:/egovframework/mapper/com/**}{@code /*_${Globals.DbType}.xml} 이라 런타임에는
 * 방언 매퍼가 하나만 로드된다. 방언별로 매퍼를 직접 파싱해 DB 연결 없이 검증한다.</p>
 */
class EgovAdressBookOthbcScopeTest {

	private static final String LIST_STATEMENT_ID = "AdressBookDAO.selectAdressBookList";

	private static final String CNT_STATEMENT_ID = "AdressBookDAO.selectAdressBookListCnt";

	/** 공개범위로 볼 수 있는 주소록을 가리는 절에서 회사 공개를 판단하는 리터럴이다. */
	private static final Pattern COMPANY_SCOPE = Pattern.compile("OTHBC_SCOPE\\s*=\\s*'([^']*)'");

	/**
	 * 어느 {@code <if>} 에도 걸리지 않는 검색조건이다. 빈 문자열은 쓸 수 없다 — OGNL 이
	 * {@code "" == 0} 을 참으로 보아 첫 분기가 켜진다.
	 */
	private static final String NO_CONDITION = "9";

	private Configuration loadMapper(String dialect) throws Exception {
		String resource = "egovframework/mapper/com/cop/adb/EgovAdbk_SQL_" + dialect + ".xml";
		Configuration configuration = new Configuration();
		configuration.getTypeAliasRegistry().registerAlias("comDefaultVO", ComDefaultVO.class);
		configuration.getTypeAliasRegistry().registerAlias("egovMap", EgovMap.class);
		try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
			new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments()).parse();
		}
		return configuration;
	}

	private String companyScopeOf(Configuration configuration, String statementId, Object parameter) {
		String sql = configuration.getMappedStatement(statementId).getBoundSql(parameter).getSql();

		Matcher matcher = COMPANY_SCOPE.matcher(sql);
		assertTrue(matcher.find(), statementId + " 이 공개범위로 가리지 않는다: " + sql);
		return matcher.group(1);
	}

	@ParameterizedTest(name = "{0}")
	@ValueSource(strings = { "mysql", "maria", "oracle", "postgres", "tibero", "altibase", "cubrid", "goldilocks" })
	@DisplayName("주소록 목록과 총 건수는 방언과 무관하게 같은 값으로 회사 공개를 가린다")
	void listAndTotalCountUseTheSameCompanyScope(String dialect) throws Exception {
		Configuration configuration = loadMapper(dialect);

		// 로그인한 사용자가 주소록 목록을 검색 없이 연 상태다.
		AddressBookVO addressBookVO = new AddressBookVO();
		addressBookVO.setSearchCnd(NO_CONDITION);
		addressBookVO.setWrterId("USRCNFRM_00000000000");
		addressBookVO.setTrgetOrgnztId("ORGNZT_0000000000000");

		String listScope = companyScopeOf(configuration, LIST_STATEMENT_ID, addressBookVO);
		String cntScope = companyScopeOf(configuration, CNT_STATEMENT_ID, addressBookVO);

		System.out.println("[" + dialect + "] 목록 " + escape(listScope) + " / 건수 " + escape(cntScope));

		assertEquals(cntScope, listScope, dialect + " 매퍼의 목록이 총 건수와 다른 값으로 회사 공개를 가린다");
	}

	/** 탭은 눈에 보이지 않으므로 출력에서 드러나게 바꾼다. */
	private String escape(String scope) {
		return '\'' + scope.replace("\t", "\\t") + '\'';
	}

}
