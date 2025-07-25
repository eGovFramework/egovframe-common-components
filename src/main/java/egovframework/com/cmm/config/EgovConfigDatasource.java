package egovframework.com.cmm.config;

import org.egovframe.rte.fdl.cryptography.EgovEnvCryptoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import egovframework.com.cmm.service.EgovProperties;
import lombok.RequiredArgsConstructor;

/**
 * Java Config 기반 데이터소스 설정 (HikariCP)
 * 
 * <pre>
 * VM arguments
 * -Dspring.profiles.active=mysql-java-config,security
 * -Dspring.profiles.active=oracle-java-config,security
 * -Dspring.profiles.active=altibase-java-config,security
 * -Dspring.profiles.active=tibero-java-config,security
 * -Dspring.profiles.active=cubrid-java-config,security
 * -Dspring.profiles.active=maria-java-config,security
 * -Dspring.profiles.active=postgres-java-config,security
 * -Dspring.profiles.active=goldilocks-java-config,security
 * </pre>
 * 
 * @author 이백행
 * @since 2025.07.12
 * @version 4.3.1
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2025.07.12  이백행          2025년 컨트리뷰션 최초 생성
 *
 *      </pre>
 */
@Configuration
@RequiredArgsConstructor
public class EgovConfigDatasource {

	private final EgovEnvCryptoService egovEnvCryptoService;

	@Profile("mysql-java-config")
	@Bean(name = "dataSource", destroyMethod = "close")
	public HikariDataSource dataSourceMysql() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(EgovProperties.getProperty("Globals.mysql.DriverClassName"));
		config.setJdbcUrl(EgovProperties.getProperty("Globals.mysql.Url"));
		config.setUsername(EgovProperties.getProperty("Globals.mysql.UserName"));
		config.setPassword(egovEnvCryptoService.getPassword());

		config.setMaximumPoolSize(100);

		HikariDataSource ds = new HikariDataSource(config);

		return ds;
	}

	@Profile("oracle-java-config")
	@Bean(name = "dataSource", destroyMethod = "close")
	public HikariDataSource dataSourceOracle() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(EgovProperties.getProperty("Globals.oracle.DriverClassName"));
		config.setJdbcUrl(EgovProperties.getProperty("Globals.oracle.Url"));
		config.setUsername(EgovProperties.getProperty("Globals.oracle.UserName"));
		config.setPassword(egovEnvCryptoService.getPassword());

		config.setMaximumPoolSize(100);

		HikariDataSource ds = new HikariDataSource(config);

		return ds;
	}

	@Profile("altibase-java-config")
	@Bean(name = "dataSource", destroyMethod = "close")
	public HikariDataSource dataSourceAltibase() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(EgovProperties.getProperty("Globals.altibase.DriverClassName"));
		config.setJdbcUrl(EgovProperties.getProperty("Globals.altibase.Url"));
		config.setUsername(EgovProperties.getProperty("Globals.altibase.UserName"));
		config.setPassword(egovEnvCryptoService.getPassword());

		config.setMaximumPoolSize(100);

		HikariDataSource ds = new HikariDataSource(config);

		return ds;
	}

	@Profile("tibero-java-config")
	@Bean(name = "dataSource", destroyMethod = "close")
	public HikariDataSource dataSourceTibero() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(EgovProperties.getProperty("Globals.tibero.DriverClassName"));
		config.setJdbcUrl(EgovProperties.getProperty("Globals.tibero.Url"));
		config.setUsername(EgovProperties.getProperty("Globals.tibero.UserName"));
		config.setPassword(egovEnvCryptoService.getPassword());

		config.setMaximumPoolSize(100);

		HikariDataSource ds = new HikariDataSource(config);

		return ds;
	}

	@Profile("cubrid-java-config")
	@Bean(name = "dataSource", destroyMethod = "close")
	public HikariDataSource dataSourceCubrid() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(EgovProperties.getProperty("Globals.cubrid.DriverClassName"));
		config.setJdbcUrl(EgovProperties.getProperty("Globals.cubrid.Url"));
		config.setUsername(EgovProperties.getProperty("Globals.cubrid.UserName"));
		config.setPassword(egovEnvCryptoService.getPassword());

		config.setMaximumPoolSize(100);

		HikariDataSource ds = new HikariDataSource(config);

		return ds;
	}

	@Profile("maria-java-config")
	@Bean(name = "dataSource", destroyMethod = "close")
	public HikariDataSource dataSourceMaria() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(EgovProperties.getProperty("Globals.maria.DriverClassName"));
		config.setJdbcUrl(EgovProperties.getProperty("Globals.maria.Url"));
		config.setUsername(EgovProperties.getProperty("Globals.maria.UserName"));
		config.setPassword(egovEnvCryptoService.getPassword());

		config.setMaximumPoolSize(100);

		HikariDataSource ds = new HikariDataSource(config);

		return ds;
	}

	@Profile("postgres-java-config")
	@Bean(name = "dataSource", destroyMethod = "close")
	public HikariDataSource dataSourcePostgres() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(EgovProperties.getProperty("Globals.postgres.DriverClassName"));
		config.setJdbcUrl(EgovProperties.getProperty("Globals.postgres.Url"));
		config.setUsername(EgovProperties.getProperty("Globals.postgres.UserName"));
		config.setPassword(egovEnvCryptoService.getPassword());

		config.setMaximumPoolSize(100);

		HikariDataSource ds = new HikariDataSource(config);

		return ds;
	}

	@Profile("goldilocks-java-config")
	@Bean(name = "dataSource", destroyMethod = "close")
	public HikariDataSource dataSourceGoldilocks() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(EgovProperties.getProperty("Globals.goldilocks.DriverClassName"));
		config.setJdbcUrl(EgovProperties.getProperty("Globals.goldilocks.Url"));
		config.setUsername(EgovProperties.getProperty("Globals.goldilocks.UserName"));
		config.setPassword(egovEnvCryptoService.getPassword());

		config.setMaximumPoolSize(100);

		HikariDataSource ds = new HikariDataSource(config);

		return ds;
	}

}