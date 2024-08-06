package egovframework.com.cop.bbs.service;

import java.io.IOException;
import java.nio.file.Paths;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Blog JavaParser
 * 
 * @author 이배행
 * @since 2023-04-17
 *
 */
@UtilityClass
@Slf4j
public class BlogTestJavaParser {

	/**
	 * Blog JavaParser
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("{}", Blog.class.getName());
			log.debug("{}", Blog.class.getCanonicalName());
			log.debug("{}", Blog.class.getSimpleName());
			log.debug("{}", Blog.class.getTypeName());
		}

		final CompilationUnit compilationUnit = StaticJavaParser
				.parse(Paths.get("src/main/java/" + Blog.class.getName().replaceAll("\\.", "/") + ".java"));

		final StringBuffer stringBuffer = new StringBuffer();

		compilationUnit.getClassByName(Blog.class.getSimpleName()).ifPresent(coid -> {
			coid.getMethods().forEach(method -> {
				final String methodName = method.getNameAsString();

				if (methodName.startsWith("set")) {
					stringBuffer.append("blog.").append(methodName);

					method.getParameters().forEach(parameter -> {
						final String parameterType = parameter.getTypeAsString();

						if (parameterType.startsWith("String")) {
							stringBuffer.append("(\"\");");
						}
					});

					stringBuffer.append('\n');
				}
			});
		});

		if (log.isDebugEnabled()) {
			log.debug("sb={}", stringBuffer);
		}
	}

}