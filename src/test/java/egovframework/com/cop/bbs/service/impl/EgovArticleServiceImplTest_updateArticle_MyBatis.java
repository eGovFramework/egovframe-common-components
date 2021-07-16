package egovframework.com.cop.bbs.service.impl;

import java.io.InputStream;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.junit.Test;

import egovframework.com.cop.bbs.service.BoardVO;

public class EgovArticleServiceImplTest_updateArticle_MyBatis {

	// https://www.programcreek.com/java-api-examples/?api=org.apache.ibatis.builder.xml.XMLMapperBuilder

	@Test
	public void printResultMapInfo() throws Exception {
		Configuration configuration = new Configuration();
		String resource = "egovframework/mapper/com/cop/bbs/EgovArticle_SQL_mysql.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		XMLMapperBuilder builder = new XMLMapperBuilder(inputStream, configuration, resource,
				configuration.getSqlFragments());
		builder.parse();

//		ParameterMap parameterMap = configuration.getParameterMap("updateArticle");
//		log.debug("getId={}", parameterMap.getId());

//		configuration.getParameterMapNames().forEach(action -> System.out.println(action));

//		configuration.getParameterMaps().forEach(action->System.out.println(action));

//		System.out.println(configuration.getMappedStatement("updateArticle").getId());
//		System.out.println(configuration.getMappedStatement("updateArticle").getResource());
////		System.out.println(configuration.getMappedStatement("updateArticle").getParameterMap());
//		System.out.println(configuration.getMappedStatement("updateArticle").getParameterMap().getId());
//		System.out.println(configuration.getMappedStatement("updateArticle").getParameterMap().getParameterMappings());
//		System.out.println(configuration.getMappedStatement("updateArticle").getSqlCommandType());
//		System.out.println(configuration.getMappedStatement("updateArticle").getSqlSource());
//		System.out.println(configuration.getMappedStatement("updateArticle").getStatementType());

//		System.out.println(configuration.getMappedStatementNames());
//		System.out.println(configuration.getMappedStatements());
//		configuration.getMappedStatements().forEach(action->System.out.println(action));
//		configuration.getMappedStatements().forEach(action->System.out.println(action.getId()));
//		configuration.getMappedStatements().forEach(action->System.out.println(action.getResource()));
//		configuration.getMappedStatements().forEach(action->System.out.println(action.getDatabaseId()));
//		configuration.getMappedStatements().forEach(action->System.out.println(action.getParameterMap().getId()));
//		configuration.getMappedStatements().forEach(action->System.out.println(action.getParameterMap().getParameterMappings()));

//		System.out.println(configuration.getMapperRegistry());
//		System.out.println(configuration.getMapperRegistry().getMappers());
//		System.out.println(configuration.getObjectFactory());
//		System.out.println(configuration.getTypeAliasRegistry().getTypeAliases());
//		System.out.println(configuration.getVariables());

//		configuration.getResultMaps().forEach(action -> System.out.println(action.getId()));

//		System.out.println(configuration.getMappedStatement("updateArticle").getBoundSql(BoardVO.class).getParameterObject());
//		System.out.println(configuration.getMappedStatement("updateArticle").getBoundSql(BoardVO.class).getSql());
//		System.out.println(configuration.getMappedStatement("updateArticle").getBoundSql(BoardVO.class).getParameterMappings());
		configuration.getMappedStatement("updateArticle").getBoundSql(BoardVO.class).getParameterMappings()
				.forEach(action -> System.out.println(action));
		configuration.getMappedStatement("updateArticle").getBoundSql(BoardVO.class).getParameterMappings()
				.forEach(action -> System.out.println(action.getProperty()));
		configuration.getMappedStatement("updateArticle").getBoundSql(BoardVO.class).getParameterMappings()
				.forEach(action -> System.out.println(action.getJavaType()));

		inputStream.close();
	}

}