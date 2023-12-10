package egovframework.com.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import egovframework.com.cop.stf.service.impl.BBSSatisfactionDAO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 자바 파서
 * 
 * @author 이백행
 * @since 2023-12-11
 *
 */
@NoArgsConstructor
@Slf4j
public class EgovTestJavaParser {

	/**
	 * test
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void test() {
		final Class<?> clazz = BBSSatisfactionDAO.class;

		if (log.isDebugEnabled()) {
			log.debug("getName={}", clazz.getName());
			log.debug("getCanonicalName={}", clazz.getCanonicalName());
			log.debug("getSimpleName={}", clazz.getSimpleName());
			log.debug("getTypeName={}", clazz.getTypeName());
			log.debug("\n");
		}

		final File file = new File("src/main/java/" + clazz.getName().replaceAll("\\.", "/") + ".java");

		CompilationUnit compilationUnit;
		try {
			compilationUnit = StaticJavaParser.parse(file);
		} catch (FileNotFoundException e) {
			throw new BaseRuntimeException("FileNotFoundException parse 에러가 발생했습니다!", e);
		}

		final Optional<ClassOrInterfaceDeclaration> classOrInterfaceDeclaration = compilationUnit
				.getClassByName(clazz.getSimpleName());

		if (!classOrInterfaceDeclaration.isPresent()) {
			throw new BaseRuntimeException("classOrInterfaceDeclaration getClassByName 에러가 발생했습니다!");
		}

		debugMethods(classOrInterfaceDeclaration);
		debugMethods2(classOrInterfaceDeclaration);

		assertEquals("", "", "");
	}

	private void debugMethods(final Optional<ClassOrInterfaceDeclaration> classOrInterfaceDeclaration) {
		final StringBuffer buffer = new StringBuffer("\n");

		classOrInterfaceDeclaration.get().getMethods().forEach(method -> {
//			log.debug("method={}", method);

//			log.debug("getNameAsString={}", method.getNameAsString());

//			method.getParameters().forEach(parameter -> {
//				log.debug("parameter={}", parameter);
//			});
			//
//			log.debug("getType={}", method.getType());

//			log.debug("getDescription={}", method.getJavadoc().get().getDescription().toText());

			buffer.append(classOrInterfaceDeclaration.get().getNameAsString());
			buffer.append("Test.");
			buffer.append(method.getNameAsString());
			buffer.append('\n');
		});

		log.debug("buffer={}", buffer);
	}

	private void debugMethods2(final Optional<ClassOrInterfaceDeclaration> classOrInterfaceDeclaration) {
		final StringBuffer buffer = new StringBuffer("\n");

		final List<MethodDeclaration> methods = classOrInterfaceDeclaration.get().getMethods();

		int index = 1;

		for (final MethodDeclaration method : methods) {
			buffer.append('a');
			buffer.append(String.format("%02d", index));
			buffer.append(method.getNameAsString());
			buffer.append('\n');

			index++;
		}

		log.debug("buffer={}", buffer);
	}

}