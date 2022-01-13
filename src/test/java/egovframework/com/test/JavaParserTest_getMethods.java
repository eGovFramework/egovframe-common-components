package egovframework.com.test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import egovframework.com.cop.sms.service.impl.SmsBasicDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaParserTest_getMethods {

	@Test
	public void test() {
		try {
//			String first = getFirst(EgovArticleCommentDAO.class);
//			String first = getFirst(EgovArticleCommentService.class);
//			String first = getFirst(EgovArticleCommentServiceImpl.class);
//			String first = getFirst(EgovCommuBBSMasterDAO.class);
//			String first = getFirst(EgovCommuManageDAO.class);
//			String first = getFirst(EgovCommuBBSMasterServiceImpl.class);
//			String first = getFirst(EgovCommuMasterDAO.class);
//			String first = getFirst(EgovUserInfManageDAO.class);
//			String first = getFirst(SndngMailDetailDAO.class);
//			String first = getFirst(SndngMailDtlsDAO.class);
//			String first = getFirst(SndngMailRegistDAO.class);
//			String first = getFirst(NcrdManageDAO.class);
//			String first = getFirst(EgovArticleScrapDAO.class);
			String first = getFirst(SmsBasicDAO.class);

			CompilationUnit cu = StaticJavaParser.parse(Paths.get(first));

			StringBuffer sb = new StringBuffer();

			cu.getPrimaryTypeName().ifPresent(primaryTypeName -> {

				log.debug("primaryTypeName={}", primaryTypeName);

				cu.getClassByName(primaryTypeName).ifPresent(coid -> {
					getMethods(coid, sb, primaryTypeName);
				});

				cu.getInterfaceByName(primaryTypeName).ifPresent(coid -> {
					getMethods(coid, sb, primaryTypeName);
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

	private String getFirst(Class<?> clazz) {
		return "src/main/java/" + clazz.getCanonicalName().replaceAll("\\.", "/") + ".java";
	}

	private void getMethods(ClassOrInterfaceDeclaration coid, StringBuffer sb, String primaryTypeName) {
		coid.getMethods().forEach(method -> {

			String methodName = method.getNameAsString();

			log.debug("methodName={}", methodName);

			sb.append("||");
			sb.append(primaryTypeName);
			sb.append("Test_");
			sb.append(methodName);
			sb.append("|");
			sb.append("\n");
		});
	}

}