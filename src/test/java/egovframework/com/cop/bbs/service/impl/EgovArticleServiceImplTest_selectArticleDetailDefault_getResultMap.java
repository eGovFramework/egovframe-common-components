package egovframework.com.cop.bbs.service.impl;

import java.io.InputStream;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.Configuration;
import org.junit.Test;

public class EgovArticleServiceImplTest_selectArticleDetailDefault_getResultMap {

	// https://www.programcreek.com/java-api-examples/?api=org.apache.ibatis.builder.xml.XMLMapperBuilder

	@Test
	public void printResultMapInfo() throws Exception {
		Configuration configuration = new Configuration();
		String resource = "egovframework/mapper/com/cop/bbs/EgovArticle_SQL_mysql.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		XMLMapperBuilder builder = new XMLMapperBuilder(inputStream, configuration, resource,
				configuration.getSqlFragments());
		builder.parse();

		ResultMap resultMap = configuration.getResultMap("boardSubJectList");

		System.out.println("\n-------------------+✨ mappedColumns ✨+--------------------");
		System.out.println(resultMap.getMappedColumns());

		System.out.println("\n------------------+✨ mappedProperties ✨+------------------");
		System.out.println(resultMap.getMappedProperties());

		System.out.println("\n------------------+✨ idResultMappings ✨+------------------");
		resultMap.getIdResultMappings().forEach(rm -> System.out.println(rm));

		System.out.println("\n---------------+✨ propertyResultMappings ✨+---------------");
		resultMap.getPropertyResultMappings().forEach(rm -> System.out.println(rm));

		System.out.println("\n-------------+✨ constructorResultMappings ✨+--------------");
		resultMap.getConstructorResultMappings().forEach(rm -> System.out.println(rm));

		System.out.println("\n------------------+✨ resultMappings ✨+--------------------");
		resultMap.getResultMappings().forEach(rm -> System.out.println(rm));

		resultMap.getResultMappings().forEach(rm -> System.out.println(rm.getProperty()));

		System.out.println();
		inputStream.close();
	}

}