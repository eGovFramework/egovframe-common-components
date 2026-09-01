package egovframework.com.utl.sys.srm.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import egovframework.com.utl.sys.srm.service.ServerResrceMntrngVO;

class EgovMntrngServerListPagingTest {

	private static final String STATEMENT_ID = "serverResrceMntrngDAO.selectMntrngServerList";

	private static final String JDBC_URL = "jdbc:hsqldb:mem:mntrngServerListPaging";

	private static SqlSessionFactory sqlSessionFactory;

	@BeforeAll
	static void seedDatabase() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		try (Connection connection = DriverManager.getConnection(JDBC_URL, "sa", "");
				Statement statement = connection.createStatement()) {
			statement.execute("CREATE TABLE COMTNSERVEREQPMNINFO ("
					+ "SERVER_EQPMN_ID VARCHAR(20) NOT NULL PRIMARY KEY,"
					+ "SERVER_EQPMN_NM VARCHAR(60), SERVER_EQPMN_IP VARCHAR(23), MNGR_EMAIL_ADRES VARCHAR(50))");
			statement.execute("CREATE TABLE COMTNSERVEREQPMNRELATE ("
					+ "SERVER_EQPMN_ID VARCHAR(20) NOT NULL, SERVER_ID VARCHAR(20) NOT NULL,"
					+ "PRIMARY KEY (SERVER_EQPMN_ID, SERVER_ID))");
			for (int i = 1; i <= 12; i++) {
				String eqpmnId = String.format("EQP%02d", i);
				statement.execute("INSERT INTO COMTNSERVEREQPMNINFO VALUES ('" + eqpmnId + "', 'server" + eqpmnId
						+ "', '10.0.0." + i + "', 'adm" + i + "@example.org')");
				statement.execute("INSERT INTO COMTNSERVEREQPMNRELATE VALUES ('" + eqpmnId + "', 'SRV01')");
			}
		}

		Configuration configuration = newConfiguration("mysql");
		configuration.setEnvironment(new Environment("test", new JdbcTransactionFactory(),
				new UnpooledDataSource("org.hsqldb.jdbcDriver", JDBC_URL, "sa", "")));
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
	}

	private static Configuration newConfiguration(String dialect) throws Exception {
		String resource = "egovframework/mapper/com/utl/sys/srm/EgovServerResrceMntrng_SQL_" + dialect + ".xml";
		Configuration configuration = new Configuration();
		configuration.getTypeAliasRegistry().registerAlias("egovMap", EgovMap.class);
		try (InputStream inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(resource)) {
			new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments()).parse();
		}
		return configuration;
	}

	/** EgovServerResrceMntrngController.selectMntrngServerList 의 페이징 계산을 그대로 옮긴 것. */
	private static ServerResrceMntrngVO searchConditionOfPage(int pageIndex) {
		ServerResrceMntrngVO serverResrceMntrngVO = new ServerResrceMntrngVO();
		serverResrceMntrngVO.setPageIndex(pageIndex);

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(serverResrceMntrngVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(serverResrceMntrngVO.getPageUnit());
		paginationInfo.setPageSize(serverResrceMntrngVO.getPageSize());

		serverResrceMntrngVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		serverResrceMntrngVO.setLastIndex(paginationInfo.getLastRecordIndex());
		serverResrceMntrngVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		return serverResrceMntrngVO;
	}

	private static List<String> equipmentIdsOf(Object parameter) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			List<ServerResrceMntrngVO> rows = sqlSession.selectList(STATEMENT_ID, parameter);
			List<String> ids = new ArrayList<>();
			for (ServerResrceMntrngVO row : rows) {
				ids.add(row.getServerEqpmnId());
			}
			return ids;
		}
	}

	@Test
	@DisplayName("등록서버 12건 중 2페이지는 11~12번째 서버만 보여준다")
	void secondPageShowsTheRowsAfterTheFirstPage() {
		List<String> firstPage = equipmentIdsOf(searchConditionOfPage(1));
		List<String> secondPage = equipmentIdsOf(searchConditionOfPage(2));

		System.out.println("[1페이지] " + firstPage);
		System.out.println("[2페이지] " + secondPage);

		assertEquals(List.of("EQP01", "EQP02", "EQP03", "EQP04", "EQP05", "EQP06", "EQP07", "EQP08", "EQP09", "EQP10"),
				firstPage, "1페이지에 10건이 아니라 전체가 실렸다");
		assertEquals(List.of("EQP11", "EQP12"), secondPage, "2페이지가 1페이지와 같은 목록을 보여준다");
	}

	@ParameterizedTest(name = "{0}")
	@ValueSource(strings = { "mysql", "maria", "oracle", "postgres", "tibero", "altibase", "cubrid", "goldilocks" })
	@DisplayName("등록서버 목록 조회는 방언과 무관하게 firstIndex 로 행을 건너뛴다")
	void everyDialectSkipsRowsOfPrecedingPages(String dialect) throws Exception {
		MappedStatement mappedStatement = newConfiguration(dialect).getMappedStatement(STATEMENT_ID);

		List<String> boundProperties = new ArrayList<>();
		for (ParameterMapping parameterMapping : mappedStatement.getBoundSql(searchConditionOfPage(2))
				.getParameterMappings()) {
			boundProperties.add(parameterMapping.getProperty());
		}

		assertTrue(boundProperties.contains("firstIndex"),
				dialect + " 매퍼가 페이지를 건너뛰지 않는다: " + mappedStatement.getBoundSql(searchConditionOfPage(2)).getSql());
	}

	@Test
	@DisplayName("스케줄러가 null 을 넘기면 등록서버 전체를 돌려준다")
	void schedulerWithoutSearchConditionStillGetsEveryServer() {
		// EgovServerResrceMntrngScheduling.monitorServerResrce 는 null 을 넘긴다.
		List<String> monitored = equipmentIdsOf(null);

		System.out.println("[스케줄러] " + monitored.size() + "건 " + monitored);

		assertEquals(12, monitored.size(), "모니터링 대상 서버가 누락됐다");
	}

}
