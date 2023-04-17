package egovframework.com.cop.bbs.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EgovArticleDAOTest_JavaParser {

	@Test
	public void test() throws FileNotFoundException {
		File file = new File(
				"C:\\EGOVFRAME-3.10.0\\git\\egovframe-common-components\\src\\main\\java\\egovframework\\com\\cop\\bbs\\service\\impl\\EgovArticleDAO.java");

		CompilationUnit compilationUnit = StaticJavaParser.parse(file);

		Optional<ClassOrInterfaceDeclaration> classEgovArticleDAO = compilationUnit.getClassByName("EgovArticleDAO");

		if (classEgovArticleDAO.isPresent() == false) {
			return;
		}

		StringBuffer sb = new StringBuffer("\n");

		classEgovArticleDAO.get().getMethods().forEach(method -> {
//			log.debug("method={}", method);

//			log.debug("getNameAsString={}", method.getNameAsString());

//			method.getParameters().forEach(parameter -> {
//				log.debug("parameter={}", parameter);
//			});
			//
//			log.debug("getType={}", method.getType());

//			log.debug("getDescription={}", method.getJavadoc().get().getDescription().toText());

			sb.append(classEgovArticleDAO.get().getNameAsString());
			sb.append("Test_");
			sb.append(method.getNameAsString());
			sb.append("\n");
		});

		log.debug("sb={}", sb);
	}

}