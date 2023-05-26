package egovframework.com.cop.sms.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.DataSourceConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.Globals;

/**
 * 문자메시지를 위한 DB Util 클래스 (프레임워크 비종속 버전)
 * Apache commons의 DBCP를 활용한 예로 각 프로젝트에 맞게 수정 필요
 * (EX : DataSource 사용 등)
 *
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.11.24
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일                수정자           수정내용
 *  ----------   --------   ---------------------------
 *  2009.11.24   한성곤            최초 생성
 *  2017-02-13   이정은            시큐어코딩(ES) - 시큐어코딩 부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
 *  2020-07-01   신용호            DBCP2 관련 변경사항 적용
 *
 * </pre>
 */
public class SmsBasicDBUtil {
	/** Driver load 여부 */
	private static boolean isDriverLoaded = false;

	/** Connection Pool Alias */
	private static final String JDBC_ALIAS = EgovProperties.getProperty(Globals.SMSDB_CONF_PATH, "JDBC_ALIAS");
	/** JDBC Driver 명 */
	private static final String JDBC_DRIVER = EgovProperties.getProperty(Globals.SMSDB_CONF_PATH, "JDBC_DRIVER");
	/** JDBC 접속 URL */
	private static final String JDBC_URL = EgovProperties.getProperty(Globals.SMSDB_CONF_PATH, "JDBC_URL");
	/** JDBC 접속 사용자ID */
	private static final String JDBC_USER = EgovProperties.getProperty(Globals.SMSDB_CONF_PATH, "JDBC_USER");
	/** JDBC 접속 패스워드 */
	private static final String JDBC_PASSWORD = EgovProperties.getProperty(Globals.SMSDB_CONF_PATH, "JDBC_PASSWORD");
	/** 한번에 pool에서 갖다 쓸 수 있는 최대 커넥션 개수 */
	private static final int MAX_TOTAL = 20;
	/** 반납직후 pool에 저정될 수 있는 최대 유휴커넥션 개수 */
	private static final int MAX_IDLE = 10;
	/** 사용되지 않고 pool에 유지할 최소한의 커넥션 개수 */
	private static final int MIN_IDLE = 5;
	// 최대 커넥션이 20이고 maxIdle이 10인경우
	// DB요청이 유휴상태가 되면 20개까지 생성된 커넥션풀은 10개까지 유휴커넥션으로 줄어들수 있다. (10~20개까지 커넥션풀의 개수가 생성및 반납을 반복한다.)
	// 이후 최소 IDLE까지 줄어들수 있다.
	/** 커넥션 timeout */
	private static final int MAX_WAIT_MILLIS = 20000;
	/** auto commit 여부 */
	private static final boolean DEFAULT_AUTOCOMMIT = true;
	/** read only 여부 */
	private static final boolean DEFAULT_READONLY = false;

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(SmsBasicDBUtil.class);

	/**
	 * Connection Pool 생성.
	 *
	 * @param alias
	 * @param bds
	 * @throws Exception
	 */
	protected static void createPools(String alias, BasicDataSource bds) {
		
		DataSourceConnectionFactory factory = new DataSourceConnectionFactory(bds);
		PoolableConnectionFactory poolableConnectionFactory;

		poolableConnectionFactory = new PoolableConnectionFactory(factory, null);

		//커넥션이 유효한지 확인
		poolableConnectionFactory.setValidationQuery(" SELECT 1 FROM DUAL ");
		//커넥션 풀의 설정 정보를 생성
		GenericObjectPoolConfig<PoolableConnection> poolConfig = new GenericObjectPoolConfig<>();
		//유효 커넥션 검사 주기
		poolConfig.setTimeBetweenEvictionRuns(Duration.ofMillis(1000L * 60L * 1L));
		//풀에 있는 커넥션이 유효한지 검사 유무 설정
		poolConfig.setTestWhileIdle(true);
		//기본값  : false /true 일 경우 validationQuery 를 매번 수행한다.
		poolConfig.setTestOnBorrow(false);
		//커넥션 최소개수 설정
		poolConfig.setMinIdle(bds.getMinIdle());
		//반납직후 커넥션 최소개수 설정
		poolConfig.setMaxIdle(bds.getMaxIdle());
		//커넥션 최대 개수 설정
		poolConfig.setMaxTotal(bds.getMaxTotal());
		GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<PoolableConnection>(poolableConnectionFactory,poolConfig);
		//PoolableConnectionFactory 커넥션 풀 연결
		poolableConnectionFactory.setPool(connectionPool);
		
		LOGGER.info("Pool : {}", poolableConnectionFactory.getClass().getName());

	}

	protected static synchronized void loadDriver() {
		BasicDataSource bds = new BasicDataSource();

		bds.setDriverClassName(JDBC_DRIVER);
		bds.setUrl(JDBC_URL);
		bds.setUsername(JDBC_USER);
		bds.setPassword(JDBC_PASSWORD);
		bds.setMaxTotal(MAX_TOTAL);
		bds.setMaxIdle(MAX_IDLE);
		bds.setMinIdle(MIN_IDLE);
		bds.setMaxWaitMillis(MAX_WAIT_MILLIS);
		bds.setDefaultAutoCommit(DEFAULT_AUTOCOMMIT);
		bds.setDefaultReadOnly(DEFAULT_READONLY);

		createPools(JDBC_ALIAS, bds);
		isDriverLoaded = true;
		LOGGER.info("Initialized pool : {}", JDBC_ALIAS);
	}

	public static Connection getConnection() throws Exception {
		if (!isDriverLoaded) {
			loadDriver();
		}

		Connection connection = DriverManager.getConnection("jdbc:apache:commons:dbcp:" + JDBC_ALIAS);
		return connection;
	}

	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null)
			try {
				rs.close();
			//2017.02.08 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			} catch (SQLException ignore) {
				LOGGER.error("[SQLExceptionException] : database access error occurs");
			}
		if (stmt != null)
			try {
				stmt.close();
			//2017.02.08 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			} catch (SQLException ignore) {
				LOGGER.error("[SQLExceptionException] : database access error occurs");
			}
		if (conn != null)
			try {
				conn.close();
			//2017.02.08 	이정은 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
			} catch (SQLException ignore) {
				LOGGER.error("[SQLExceptionException] : database access error occurs");
			}
	}
}
