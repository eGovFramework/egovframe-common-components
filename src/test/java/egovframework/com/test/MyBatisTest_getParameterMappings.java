package egovframework.com.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.junit.Test;

import egovframework.com.cmm.util.EgovResourceCloseHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyBatisTest_getParameterMappings {

	// https://www.programcreek.com/java-api-examples/?api=org.apache.ibatis.builder.xml.XMLMapperBuilder

	@Test
	public void test() {
		InputStream inputStream = null;
		try {
//			String resource = "egovframework/mapper/com/cop/bbs/EgovArticle_SQL_mysql.xml";
			String resource = "egovframework/mapper/com/cop/bbs/EgovBBSMaster_SQL_mysql.xml";
			inputStream = Resources.getResourceAsStream(resource);
			Configuration configuration = new Configuration();

			XMLMapperBuilder builder = new XMLMapperBuilder(inputStream, configuration, resource,
					configuration.getSqlFragments());

			builder.parse();

			StringBuffer sb = new StringBuffer();

			configuration.getMappedStatements().forEach(ms -> {
//				System.out.println(ms.getId());
//				System.out.println(ms.getBoundSql(null).getParameterMappings());

				sb.append(ms.getId());
				sb.append("\n");

				ms.getBoundSql(null).getParameterMappings().forEach(pm -> {
//					System.out.println(pm.getProperty());
//					System.out.println(pm.getJavaType().getName());

					String property = pm.getProperty();
					String propertyUpper = property.toUpperCase().substring(0, 1)
							+ property.substring(1, property.length());

					String javaTypeName = pm.getJavaType().getName();

					sb.append("vo.set");
					sb.append(propertyUpper);
					if ("java.lang.String".equals(javaTypeName)) {
						sb.append("(\"\");");
					} else if ("long".equals(javaTypeName)) {
						sb.append("(0l);");
					} else if ("int".equals(javaTypeName) || "java.lang.Object".equals(javaTypeName)) {
						sb.append("(0);");
					} else {
						sb.append(javaTypeName);
					}
					sb.append("\n");
				});

//				System.out.println("");

				sb.append("\n");
			});

			System.out.println(sb);
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			EgovResourceCloseHelper.close(inputStream);
		}
	}

}