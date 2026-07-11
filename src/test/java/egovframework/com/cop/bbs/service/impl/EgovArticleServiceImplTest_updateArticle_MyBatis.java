package egovframework.com.cop.bbs.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EgovArticleServiceImplTest_updateArticle_MyBatis {

	// https://www.programcreek.com/java-api-examples/?api=org.apache.ibatis.builder.xml.XMLMapperBuilder

//	@Test
//	public void printResultMapInfo() {
//		Configuration configuration = new Configuration();
//		String resource = "egovframework/mapper/com/cop/bbs/EgovArticle_SQL_mysql.xml";
//
//		try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
//			XMLMapperBuilder builder = new XMLMapperBuilder(inputStream, configuration, resource,
//					configuration.getSqlFragments());
//			builder.parse();
//
//			ParameterMap parameterMap = configuration.getParameterMap("updateArticle");
//			log.debug("getId={}", parameterMap.getId());
//
//			configuration.getParameterMapNames().forEach(action -> log.debug(action));
//
//			configuration.getParameterMaps().forEach(action -> log.debug("{}", action));
//
//			log.debug(configuration.getMappedStatement("updateArticle").getId());
//			log.debug(configuration.getMappedStatement("updateArticle").getResource());
//			log.debug("{}", configuration.getMappedStatement("updateArticle").getParameterMap());
//			log.debug(configuration.getMappedStatement("updateArticle").getParameterMap().getId());
//			log.debug("{}", configuration.getMappedStatement("updateArticle").getParameterMap().getParameterMappings());
//			log.debug("{}", configuration.getMappedStatement("updateArticle").getSqlCommandType());
//			log.debug("{}", configuration.getMappedStatement("updateArticle").getSqlSource());
//			log.debug("{}", configuration.getMappedStatement("updateArticle").getStatementType());
//
//			log.debug("{}", configuration.getMappedStatementNames());
//			log.debug("{}", configuration.getMappedStatements());
//			configuration.getMappedStatements().forEach(action -> log.debug("{}", action));
//			configuration.getMappedStatements().forEach(action -> log.debug(action.getId()));
//			configuration.getMappedStatements().forEach(action -> log.debug(action.getResource()));
//			configuration.getMappedStatements().forEach(action -> log.debug(action.getDatabaseId()));
//			configuration.getMappedStatements().forEach(action -> log.debug(action.getParameterMap().getId()));
//			configuration.getMappedStatements()
//					.forEach(action -> log.debug("{}", action.getParameterMap().getParameterMappings()));
//
//			log.debug("{}", configuration.getMapperRegistry());
//			log.debug("{}", configuration.getMapperRegistry().getMappers());
//			log.debug("{}", configuration.getObjectFactory());
//			log.debug("{}", configuration.getTypeAliasRegistry().getTypeAliases());
//			log.debug("{}", configuration.getVariables());
//
//			configuration.getResultMaps().forEach(action -> log.debug(action.getId()));
//
//			log.debug("{}",
//					configuration.getMappedStatement("updateArticle").getBoundSql(BoardVO.class).getParameterObject());
//			log.debug(configuration.getMappedStatement("updateArticle").getBoundSql(BoardVO.class).getSql());
//			log.debug("{}", configuration.getMappedStatement("updateArticle").getBoundSql(BoardVO.class)
//					.getParameterMappings());
//			configuration.getMappedStatement("updateArticle").getBoundSql(BoardVO.class).getParameterMappings()
//					.forEach(action -> log.debug("{}", action));
//			configuration.getMappedStatement("updateArticle").getBoundSql(BoardVO.class).getParameterMappings()
//					.forEach(action -> log.debug(action.getProperty()));
//			configuration.getMappedStatement("updateArticle").getBoundSql(BoardVO.class).getParameterMappings()
//					.forEach(action -> log.debug("{}", action.getJavaType()));
//		} catch (IOException e) {
//			throw new BaseRuntimeException(e);
//		}
//	}

	@Test
	public void printParametersInfo() {
		Configuration configuration = new Configuration();
//			String resource = "egovframework/mapper/com/cop/bbs/EgovArticle_SQL_mysql.xml";
		String resource = "egovframework/mapper/com/cop/bbs/EgovBBSMaster_SQL_mysql.xml";

		try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
			XMLMapperBuilder builder = new XMLMapperBuilder(inputStream, configuration, resource,
					configuration.getSqlFragments());

			builder.parse();

//			configuration.getMappedStatement("updateArticle").getBoundSql(null).getParameterMappings()
//					.forEach(action -> log.debug(action));

//			configuration.getMappedStatementNames().forEach(action -> log.debug(action));

			StringBuilder sb = new StringBuilder();

			configuration.getMappedStatements().forEach(ms -> {
//				log.debug(ms.getId());
//				log.debug(ms.getBoundSql(null).getParameterMappings());

				sb.append(ms.getId());
				sb.append("\n");

				ms.getBoundSql(null).getParameterMappings().forEach(pm -> {
//					log.debug(pm.getProperty());
//					log.debug(pm.getJavaType().getName());

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
					} else if ("int".equals(javaTypeName)) {
						sb.append("(0);");
					} else {
						sb.append(javaTypeName);
					}
					sb.append("\n");
				});

//				log.debug("");

				sb.append("\n");
			});

			log.debug("{}", sb);
		} catch (IOException e) {
			throw new BaseRuntimeException(e);
		}
	}

}