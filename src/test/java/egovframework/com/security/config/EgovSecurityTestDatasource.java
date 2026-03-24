package egovframework.com.security.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 * Test Security Java Configuration
 *
 * @author 유지보수
 * @version 5.0
 * @since 2025.06.01
 */
@Configuration
@PropertySource("classpath:egovframework/security/globals.properties")
public class EgovSecurityTestDatasource {

    @Bean(name = "jdbcProperties")
    public Properties jdbcProperties() throws IOException {
        ResourcePropertySource source = new ResourcePropertySource("classpath:egovframework/security/jdbc.properties");
        Properties props = new Properties();
        props.putAll(source.getSource());
        return props;
    }

    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource(@Qualifier("jdbcProperties") Properties properties) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(properties.getProperty("jdbc.driver"));
        ds.setUrl(properties.getProperty("jdbc.url"));
        ds.setUsername(properties.getProperty("jdbc.username"));
        ds.setPassword(properties.getProperty("jdbc.password"));
        ds.setDefaultAutoCommit(true);
        ds.setPoolPreparedStatements(true);

        try (Connection conn = ds.getConnection()) {
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("egovframework/security/testdb.sql"));
        } catch (Exception e) {
            throw new IllegalStateException("Test database initialization failed", e);
        }

        return ds;
    }
}
