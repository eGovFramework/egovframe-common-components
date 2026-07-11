package egovframework.com.cop.bbs.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.Configuration;
import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EgovArticleServiceImplTest_selectArticleDetailCn_getResultMap {

	// https://www.programcreek.com/java-api-examples/?api=org.apache.ibatis.builder.xml.XMLMapperBuilder

	@Test
	public void printResultMapInfo() {
		Configuration configuration = new Configuration();
		String resource = "egovframework/mapper/com/cop/bbs/EgovArticle_SQL_mysql.xml";
		try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
			XMLMapperBuilder builder = new XMLMapperBuilder(inputStream, configuration, resource,
					configuration.getSqlFragments());
			builder.parse();

//		String id = "detailCn";
			String id = "boardMasterList";
			ResultMap resultMap = configuration.getResultMap(id);

			log.debug("\n-------------------+✨ mappedColumns ✨+--------------------");
			log.debug("{}", resultMap.getMappedColumns());

			log.debug("\n------------------+✨ mappedProperties ✨+------------------");
			log.debug("{}", resultMap.getMappedProperties());

			log.debug("\n------------------+✨ idResultMappings ✨+------------------");
			resultMap.getIdResultMappings().forEach(rm -> log.debug("{}", rm));

			log.debug("\n---------------+✨ propertyResultMappings ✨+---------------");
			resultMap.getPropertyResultMappings().forEach(rm -> log.debug("{}", rm));

			log.debug("\n-------------+✨ constructorResultMappings ✨+--------------");
			resultMap.getConstructorResultMappings().forEach(rm -> log.debug("{}", rm));

			log.debug("\n------------------+✨ resultMappings ✨+--------------------");
			resultMap.getResultMappings().forEach(rm -> log.debug("{}", rm));

			resultMap.getResultMappings().forEach(rm -> log.debug(rm.getProperty()));

			StringBuilder sb = new StringBuilder("\n");
			resultMap.getResultMappings().forEach(rm -> {
				String property = rm.getProperty();
				String propertyUpper = property.toUpperCase().substring(0, 1)
						+ property.substring(1, property.length());

				sb.append("log.debug(\"");
				sb.append(property);
				sb.append("={}\", ");
				sb.append("boardVO.get");
				sb.append(propertyUpper);
				sb.append("());\n");
			});

			log.debug("{}", sb);

			log.debug("");
		} catch (IOException e) {
			throw new BaseRuntimeException(e);
		}
	}

}