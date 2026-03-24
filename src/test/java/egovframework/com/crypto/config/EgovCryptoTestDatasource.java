package egovframework.com.crypto.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.egovframe.rte.fdl.crypto.EgovEnvCryptoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Test Crypto DataSource Configuration
 * <p>
 * (라이브러리 원본과 동일 — 현재 통합 테스트 Context에는 미포함 가능)
 * </p>
 *
 * @author 유지보수
 * @version 5.0
 * @since 2025.06.01
 */
@Configuration
public class EgovCryptoTestDatasource {

    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource(EgovEnvCryptoService egovEnvCryptoService) throws Exception {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(egovEnvCryptoService.getDriverClassName());
        ds.setUrl(egovEnvCryptoService.getUrl());
        ds.setUsername(egovEnvCryptoService.getUsername());
        ds.setPassword(egovEnvCryptoService.getPassword() != null ? egovEnvCryptoService.getPassword() : "");
        ds.setDefaultAutoCommit(true);
        ds.setPoolPreparedStatements(true);

        try (Connection conn = ds.getConnection()) {
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("egovframework/crypto/testdb.sql"));
        } catch (Exception e) {
            throw new IllegalStateException("Test database initialization failed", e);
        }

        return ds;
    }
}
