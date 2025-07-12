package egovframework.com.cmm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jndi.JndiObjectFactoryBean;

/**
 * JNDI 데이터소스 설정
 * 
 * <pre>
 * VM arguments
 * -Dspring.profiles.active=mysql-jndi,security
 * -Dspring.profiles.active=oracle-jndi,security
 * -Dspring.profiles.active=altibase-jndi,security
 * -Dspring.profiles.active=tibero-jndi,security
 * -Dspring.profiles.active=cubrid-jndi,security
 * -Dspring.profiles.active=maria-jndi,security
 * -Dspring.profiles.active=postgres-jndi,security
 * -Dspring.profiles.active=goldilocks-jndi,security
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
public class EgovConfigDatasourceJndi {

// Apache Tomcat 9
// JNDI Datasource How-To
// 
//  <Resource name="jdbc/ComDB" auth="Container" type="javax.sql.DataSource"
//               maxTotal="100" maxIdle="30" maxWaitMillis="10000"
//               username="com" password="com01" driverClassName="net.sf.log4jdbc.DriverSpy"
//               url="jdbc:log4jdbc:mysql://127.0.0.1:3306/com"/>
// 
// https://tomcat.apache.org/tomcat-9.0-doc/jndi-datasource-examples-howto.html

	@Profile("mysql-jndi")
	@Bean(name = "dataSource")
	public JndiObjectFactoryBean dataSourceMysql() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();

		// Apache Tomcat 9
		jndiObjectFactoryBean.setJndiName("java:/comp/env/jdbc/ComDB");

//		jndiObjectFactoryBean.setJndiName("jdbc/ComDB");

		return jndiObjectFactoryBean;
	}

	@Profile("oracle-jndi")
	@Bean(name = "dataSource")
	public JndiObjectFactoryBean dataSourceOracle() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();

		// Apache Tomcat 9
		jndiObjectFactoryBean.setJndiName("java:/comp/env/jdbc/ComDB");

//		jndiObjectFactoryBean.setJndiName("jdbc/ComDB");

		return jndiObjectFactoryBean;
	}

	@Profile("altibase-jndi")
	@Bean(name = "dataSource")
	public JndiObjectFactoryBean dataSourceAltibase() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();

		// Apache Tomcat 9
		jndiObjectFactoryBean.setJndiName("java:/comp/env/jdbc/ComDB");

//		jndiObjectFactoryBean.setJndiName("jdbc/ComDB");

		return jndiObjectFactoryBean;
	}

	@Profile("tibero-jndi")
	@Bean(name = "dataSource")
	public JndiObjectFactoryBean dataSourceTibero() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();

		// Apache Tomcat 9
		jndiObjectFactoryBean.setJndiName("java:/comp/env/jdbc/ComDB");

//		jndiObjectFactoryBean.setJndiName("jdbc/ComDB");

		return jndiObjectFactoryBean;
	}

	@Profile("cubrid-jndi")
	@Bean(name = "dataSource")
	public JndiObjectFactoryBean dataSourceCubrid() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();

		// Apache Tomcat 9
		jndiObjectFactoryBean.setJndiName("java:/comp/env/jdbc/ComDB");

//		jndiObjectFactoryBean.setJndiName("jdbc/ComDB");

		return jndiObjectFactoryBean;
	}

	@Profile("maria-jndi")
	@Bean(name = "dataSource")
	public JndiObjectFactoryBean dataSourceMaria() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();

		// Apache Tomcat 9
		jndiObjectFactoryBean.setJndiName("java:/comp/env/jdbc/ComDB");

//		jndiObjectFactoryBean.setJndiName("jdbc/ComDB");

		return jndiObjectFactoryBean;
	}

	@Profile("postgres-jndi")
	@Bean(name = "dataSource")
	public JndiObjectFactoryBean dataSourcePostgres() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();

		// Apache Tomcat 9
		jndiObjectFactoryBean.setJndiName("java:/comp/env/jdbc/ComDB");

//		jndiObjectFactoryBean.setJndiName("jdbc/ComDB");

		return jndiObjectFactoryBean;
	}

	@Profile("goldilocks-jndi")
	@Bean(name = "dataSource")
	public JndiObjectFactoryBean dataSourceGoldilocks() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();

		// Apache Tomcat 9
		jndiObjectFactoryBean.setJndiName("java:/comp/env/jdbc/ComDB");

//		jndiObjectFactoryBean.setJndiName("jdbc/ComDB");

		return jndiObjectFactoryBean;
	}

}