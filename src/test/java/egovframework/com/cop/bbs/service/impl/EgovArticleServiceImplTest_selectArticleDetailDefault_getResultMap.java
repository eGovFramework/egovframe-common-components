package egovframework.com.cop.bbs.service.impl;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.Configuration;
import org.junit.jupiter.api.Test;

@Slf4j
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

		log.debug("\n-------------------+✨ mappedColumns ✨+--------------------");
		log.debug(String.valueOf(resultMap.getMappedColumns()));

		log.debug("\n------------------+✨ mappedProperties ✨+------------------");
		log.debug(String.valueOf(resultMap.getMappedProperties()));

		log.debug("\n------------------+✨ idResultMappings ✨+------------------");
		resultMap.getIdResultMappings().forEach(rm -> log.debug(String.valueOf(rm)));

		log.debug("\n---------------+✨ propertyResultMappings ✨+---------------");
		resultMap.getPropertyResultMappings().forEach(rm -> log.debug(String.valueOf(rm)));

		log.debug("\n-------------+✨ constructorResultMappings ✨+--------------");
		resultMap.getConstructorResultMappings().forEach(rm -> log.debug(String.valueOf(rm)));

		log.debug("\n------------------+✨ resultMappings ✨+--------------------");
		resultMap.getResultMappings().forEach(rm -> log.debug(String.valueOf(rm)));

		resultMap.getResultMappings().forEach(rm -> log.debug(rm.getProperty()));

		log.debug("");
		inputStream.close();
	}

}
