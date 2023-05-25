package egovframework.com.test;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SystemUtils;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TodoTest {

	@Test
	public void test() {
		String pathname = "src/main/java/egovframework/com";

		final File directory = new File(pathname);
		final String[] extensions = { "java" };
		final boolean recursive = true;

		Collection<File> listFiles = FileUtils.listFiles(directory, extensions, recursive);
		int size = listFiles.size();

		StringBuffer sb = new StringBuffer();
		sb.append(SystemUtils.LINE_SEPARATOR);

		int i = 1;

		for (File listFile : listFiles) {
			String name = listFile.getName();
			if (name.endsWith("DAO.java")) {
				sb.append(i);
				sb.append(". ");
				sb.append(name.replaceAll(".java", ""));
				sb.append(SystemUtils.LINE_SEPARATOR);

				i++;
			}
		}

		log.debug(sb.toString());
		log.debug("size={}", size);
	}

}