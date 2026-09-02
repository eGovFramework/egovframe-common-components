package egovframework.com.cop.bbs.service;

import java.io.IOException;
import java.nio.file.Paths;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.junit.jupiter.api.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class BlogTest_JavaParser {

	@Test
	void test() {
		CompilationUnit cu;
		try {
			cu = StaticJavaParser.parse(Paths.get("src\\main\\java\\egovframework\\com\\cop\\bbs\\service\\Blog.java"));
		} catch (IOException e) {
			throw new BaseRuntimeException(e);
		}

		StringBuffer sb = new StringBuffer();

		cu.getClassByName("Blog").ifPresent(coid -> {
			coid.getMethods().forEach(method -> {
				String methodName = method.getNameAsString();

				if (methodName.startsWith("set")) {
					sb.append("blog.");
					sb.append(methodName);

					method.getParameters().forEach(parameter -> {
						String parameterType = parameter.getTypeAsString();

						if (parameterType.startsWith("String")) {
							sb.append("(\"\");");
						}
					});

					sb.append("\n");
				}
			});
		});

		log.debug(sb.toString());
	}

}
