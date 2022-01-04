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
import org.apache.ibatis.session.Configuration;
import org.junit.Test;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyBatisTest_getParameterMappings {

	// https://www.programcreek.com/java-api-examples/?api=org.apache.ibatis.builder.xml.XMLMapperBuilder

	@Test
	public void test() {
		InputStream inputStream = null;
		try {
//			String resource = "egovframework/mapper/com/cop/bbs/EgovArticle_SQL_mysql.xml";
//			String resource = "egovframework/mapper/com/cop/bbs/EgovBBSMaster_SQL_mysql.xml";
//			String resource = "egovframework/mapper/com/cop/cmt/EgovArticleComment_SQL_mysql.xml";
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

			writeStringToFile(resource, sb);
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			EgovResourceCloseHelper.close(inputStream);
		}
	}

	private void writeStringToFile(String resource, StringBuffer sb) {
		String name = FilenameUtils.getName(resource) + "-getParameterMappings";
		try {
			FileUtils.writeStringToFile(new File(SystemUtils.USER_HOME + "/Desktop/god.codegen/" + name + ".txt"),
					sb.toString(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

}