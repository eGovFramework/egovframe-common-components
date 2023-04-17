package egovframework.com.cop.bbs.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.Configuration;
import org.junit.Test;

import egovframework.com.cmm.util.EgovResourceCloseHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EgovArticleServiceImplTest_selectNoticeArticleList_MyBatis {

	@Test
	public void test() {
		log.debug("test");

		InputStream inputStream = null;

		try {
			String resource = "egovframework/mapper/com/cop/bbs/EgovArticle_SQL_mysql.xml";
			inputStream = Resources.getResourceAsStream(resource);

			Configuration configuration = new Configuration();

			XMLMapperBuilder builder = new XMLMapperBuilder(inputStream, configuration, resource,
					configuration.getSqlFragments());

			builder.parse();

//			ResultMap resultMap = configuration.getResultMap("boardList");
			ResultMap resultMap = configuration.getResultMap("boardBlogManager");

			StringBuffer sb = new StringBuffer("\n");
			StringBuffer sb2 = new StringBuffer("\n");
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
				
				sb2.append("assertEquals(resultList.get(0).get");
				sb2.append(propertyUpper);
				sb2.append("(), boardVO.get");
				sb2.append(propertyUpper);
				sb2.append("());\n");
			});

			System.out.println(sb);
			System.out.println(sb2);
		} catch (IOException e) {
			log.error(e.getMessage());
			EgovResourceCloseHelper.close(inputStream);
		}
	}

}