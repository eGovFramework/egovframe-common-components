package egovframework.com.cop.bbs.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * EgovArticleDAO JavaParser
 * 
 * @author 이배행
 * @since 2023-04-17
 *
 */
@UtilityClass
@Slf4j
public class EgovArticleDAOTestJavaParser {

	/**
	 * EgovArticleDAO JavaParser
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		if (log.isDebugEnabled()) {
			log.debug("{}", EgovArticleDAO.class.getName());
			log.debug("{}", EgovArticleDAO.class.getCanonicalName());
			log.debug("{}", EgovArticleDAO.class.getSimpleName());
			log.debug("{}", EgovArticleDAO.class.getTypeName());
		}

		final File file = new File("src/main/java/" + EgovArticleDAO.class.getName().replaceAll("\\.", "/") + ".java");

		final CompilationUnit compilationUnit = StaticJavaParser.parse(file);

		final Optional<ClassOrInterfaceDeclaration> coid = compilationUnit
				.getClassByName(EgovArticleDAO.class.getSimpleName());

		if (!coid.isPresent()) {
			return;
		}

		final StringBuffer stringBuffer = new StringBuffer("\n");

		coid.get().getMethods().forEach(method -> {
//			log.debug("method={}", method);
//
//			log.debug("getNameAsString={}", method.getNameAsString());
//
//			method.getParameters().forEach(parameter -> {
//				log.debug("parameter={}", parameter);
//			});
//
//			log.debug("getType={}", method.getType());
//
//			log.debug("getDescription={}", method.getJavadoc().get().getDescription().toText());

			stringBuffer.append(coid.get().getNameAsString()).append("Test_").append(method.getNameAsString())
					.append('\n');
		});

		if (log.isDebugEnabled()) {
			log.debug("sb={}", stringBuffer);
		}
	}

}