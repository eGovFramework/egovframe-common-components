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

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
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
//			String resource = "egovframework/mapper/com/cop/cmt/EgovArticleComment_SQL_mysql.xml";
//			String resource = "egovframework/mapper/com/cmm/fms/EgovFile_SQL_mysql.xml";
//			String resource = "egovframework/mapper/com/cop/bbs/EgovBBSAddedOptions_SQL_mysql.xml";
//			String resource = "egovframework/mapper/com/cop/cmy/EgovCommuBBSMaster_SQL_mysql.xml";
//			String resource = "egovframework/mapper/com/cop/cmy/EgovCommuManage_SQL_mysql.xml";
//			String resource = "egovframework/mapper/com/cop/cmy/EgovCommuMaster_SQL_mysql.xml";
//			String resource = "egovframework/mapper/com/cop/com/EgovUserInf_SQL_mysql.xml";
//			String resource = "egovframework/mapper/com/uss/umt/EgovMberManage_SQL_mysql.xml";
//			String resource = "egovframework/mapper/com/cop/ems/EgovSndngMailDetail_SQL_mysql.xml";
//			String resource = "egovframework/mapper/com/cop/ems/EgovSndngMailRegist_SQL_mysql.xml";
//			String resource = "egovframework/mapper/com/cop/ems/EgovSndngMailDtls_SQL_mysql.xml";
//			String resource = "egovframework/mapper/com/cop/ncm/EgovNcrd_SQL_mysql.xml";
			String resource = "egovframework/mapper/com/cop/scp/EgovArticleScrap_SQL_mysql.xml";
			inputStream = Resources.getResourceAsStream(resource);

			Configuration configuration = new Configuration();

			configuration.getTypeAliasRegistry().registerAlias("egovMap", EgovMap.class);
			configuration.getTypeAliasRegistry().registerAlias("FileVO", FileVO.class);
			configuration.getTypeAliasRegistry().registerAlias("ComDefaultCodeVO", ComDefaultCodeVO.class);
			configuration.getTypeAliasRegistry().registerAlias("comDefaultVO", ComDefaultVO.class);

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

			String expected = "results.get(0).get" + propertyUpper + "()";
			String actual = "vo.get" + propertyUpper + "()";

			sb.append("log.debug(\"");
			sb.append(property);
			sb.append("={}, {}\", ");
			sb.append(expected);
			sb.append(", ");
			sb.append(actual);
			sb.append(");\n");

			sb2.append("assertEquals(");
			sb2.append(expected);
			sb2.append(", ");
			sb2.append(actual);
			sb2.append(");\n");
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