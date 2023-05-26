package egovframework.com.utl.sys.fsm.service.impl;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")
public class EgovFileSysMntrngServiceImpl_테스트 {

	protected Logger egovLogger = LoggerFactory.getLogger(EgovFileSysMntrngServiceImpl_테스트.class);

	@Test
	public void test() {
		// freeSpaceKb
		long freeSpaceKb = 0;
		try {
			freeSpaceKb = FileSystemUtils.freeSpaceKb("");
		} catch (IOException e) {
			egovLogger.error("IOException");
		}

		long freeSpaceBytes = freeSpaceKb * 1024;

		egovLogger.debug("freeSpaceBytes={}", freeSpaceBytes);
		egovLogger.debug("freeSpaceKb={}", freeSpaceKb);
		egovLogger.debug("freeSpaceMb={}", FileUtils.byteCountToDisplaySize(freeSpaceBytes));

		// getUsableSpace
		Path path = Paths.get("");
		FileStore fs = null;
		long usableSpaceBytes = 0;
		try {
			fs = Files.getFileStore(path);
			usableSpaceBytes = fs.getUsableSpace();
		} catch (IOException e) {
			egovLogger.error("IOException");
		}
		long usableSpaceKb = usableSpaceBytes / 1024;

		egovLogger.debug("usableSpaceBytes={}", usableSpaceBytes);
		egovLogger.debug("usableSpaceKb={}", usableSpaceKb);
		egovLogger.debug("usableSpaceMb={}", FileUtils.byteCountToDisplaySize(usableSpaceBytes));
	}

}
