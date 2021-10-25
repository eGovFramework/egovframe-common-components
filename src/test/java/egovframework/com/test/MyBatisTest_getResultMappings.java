package egovframework.com.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.Configuration;
import org.junit.Test;

import egovframework.com.cmm.util.EgovResourceCloseHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyBatisTest_getResultMappings {

	@Test
	public void test() {
		log.debug("test");

		InputStream inputStream = null;

		try {
//			String resource = "egovframework/mapper/com/cop/bbs/EgovArticle_SQL_mysql.xml";
//			String resource = "egovframework/mapper/com/cop/bbs/EgovBBSMaster_SQL_mysql.xml";
			String resource = "egovframework/mapper/com/cop/cmt/EgovArticleComment_SQL_mysql.xml";
			inputStream = Resources.getResourceAsStream(resource);

			Configuration configuration = new Configuration();

			XMLMapperBuilder builder = new XMLMapperBuilder(inputStream, configuration, resource,
					configuration.getSqlFragments());

			builder.parse();

			StringBuffer sb = new StringBuffer("\n");
			StringBuffer sb2 = new StringBuffer("\n");
			StringBuffer sb3 = new StringBuffer("\n");

			configuration.getResultMapNames().forEach(id -> {
				sb.setLength(0);
				sb2.setLength(0);
				getResultMappings(configuration, sb, sb2, sb3, id);
			});

			System.out.println(sb);
			System.out.println(sb2);
			System.out.println(sb3);

			writeStringToFile(resource, sb3);
		} catch (IOException e) {
			log.error(e.getMessage());
			EgovResourceCloseHelper.close(inputStream);
		}
	}

	private void getResultMappings(Configuration configuration, StringBuffer sb, StringBuffer sb2, StringBuffer sb3,
			String id) {
		ResultMap resultMap = configuration.getResultMap(id);

		sb3.append(id);
		sb3.append("\n\n");

		resultMap.getResultMappings().forEach(rm -> {
			String property = rm.getProperty();
			String propertyUpper = property.toUpperCase().substring(0, 1) + property.substring(1, property.length());

			sb.append("log.debug(\"");
			sb.append(property);
			sb.append("={}\", ");
			sb.append("boardVO.get");
			sb.append(propertyUpper);
			sb.append("());\n");

			sb2.append("assertEquals(resultList.get(0).get");
			sb2.append(propertyUpper);
			sb2.append("(), boardVO.get");
			sb2.append(propertyUpper);
			sb2.append("());\n");
		});

		sb3.append(sb);
		sb3.append("\n");
		sb3.append(sb2);

		sb3.append("\n");
		sb3.append("---");
		sb3.append("\n\n");
	}

	private void writeStringToFile(String resource, StringBuffer sb) {
		String name = FilenameUtils.getName(resource) + "-getResultMappings";
		try {
			FileUtils.writeStringToFile(new File(SystemUtils.USER_HOME + "/Desktop/god.codegen/" + name + ".txt"),
					sb.toString(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

}