package egovframework.com.cop.bbs.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EgovArticleServiceImplTest_AAA_JavaParser {

	@Test
	public void test() {
		try {

//			String first = "C:\\EGOVFRAME-3.10.0\\git\\egovframe-common-components\\src\\main\\java\\egovframework\\com\\cop\\bbs\\service\\impl\\EgovArticleServiceImpl.java";
			String first = "src/main/java/egovframework/com/cop/bbs/service/impl/EgovBBSMasterDAO.java";

			CompilationUnit cu = StaticJavaParser.parse(Paths.get(first));

			StringBuffer sb = new StringBuffer();

			cu.getPrimaryTypeName().ifPresent(primaryTypeName -> {

				log.debug("primaryTypeName={}", primaryTypeName);

				cu.getClassByName(primaryTypeName).ifPresent(coid -> {

					coid.getMethods().forEach(method -> {

						String methodName = method.getNameAsString();

						log.debug("methodName={}", methodName);

						sb.append(primaryTypeName);
						sb.append("Test_");
						sb.append(methodName);
						sb.append("\n");

					});

				});

				try {
					FileUtils.writeStringToFile(
							new File(SystemUtils.USER_HOME + "/Desktop/god.codegen/" + primaryTypeName + ".txt"),
							sb.toString(), StandardCharsets.UTF_8);
				} catch (IOException e) {
					log.error(e.getMessage());
				}

			});

			log.debug("sb={}", sb);

		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

}