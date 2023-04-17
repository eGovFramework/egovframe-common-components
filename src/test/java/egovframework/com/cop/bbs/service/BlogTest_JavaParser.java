package egovframework.com.cop.bbs.service;

import java.nio.file.Paths;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class BlogTest_JavaParser {

	@Test
	public void test() throws Exception {
		CompilationUnit cu = StaticJavaParser.parse(Paths.get(
				"C:\\EGOVFRAME-3.10.0\\git\\egovframe-common-components\\src\\main\\java\\egovframework\\com\\cop\\bbs\\service\\Blog.java"));

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

		System.out.println(sb);
	}

}