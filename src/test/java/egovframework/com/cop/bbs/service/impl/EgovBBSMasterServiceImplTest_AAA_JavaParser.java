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
public class EgovBBSMasterServiceImplTest_AAA_JavaParser {

	@Test
	public void test() throws IOException {
		CompilationUnit compilationUnit = StaticJavaParser
				.parse(Paths.get("src/main/java/egovframework/com/cop/bbs/service/impl/EgovBBSMasterServiceImpl.java"));

		StringBuffer sb = new StringBuffer("\n");

		compilationUnit.getPrimaryTypeName().ifPresent(primaryTypeName -> {
			log.debug("primaryTypeName={}", primaryTypeName);

			compilationUnit.getClassByName(primaryTypeName).ifPresent(classByName -> {
//				log.debug("classByName={}", classByName);
				classByName.getMethods().forEach(method -> {
//					log.debug("method={}", method);
//					log.debug("getNameAsString={}", method.getNameAsString());

					sb.append(primaryTypeName);
					sb.append("_");
					sb.append(method.getNameAsString());
					sb.append("\n");
				});
			});

			String data = sb.toString();
			log.debug("data={}", data);

			try {
				FileUtils.writeStringToFile(
						new File(SystemUtils.USER_HOME + "/Desktop/god.codegen/" + primaryTypeName + ".txt"), data,
						StandardCharsets.UTF_8);
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		});

	}

}