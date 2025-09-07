package egovframework.com.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Collection;

import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;

import egovframework.com.utl.sim.service.EgovFileCmprs;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestDecompress {

	public static void main(String[] args) {
		// 프로젝트 ROOT
		// String strDirPath =
		// "C:\\eGovFrameDev-4.0.0-64bit\\workspace\\test.simple_homepage";
		String strDirPath = "";
		strDirPath = System.getProperty("user.dir");
		if (log.isDebugEnabled()) {
			log.debug("Working Directory = {}", strDirPath);
		}

		// Path relativePath = Paths.get("");
		// strDirPath = relativePath.toAbsolutePath().toString();
//		if (log.isDebugEnabled()) {
//			log.debug("Working Directory = {}", strDirPath);
//		}

		String source = strDirPath + File.separator + "target" + File.separator + "sample.zip";
		String target = "test/result/result_" + LocalDateTime.now().toString().replaceAll(":", "-");
//		String moved = target + File.separator + "sample.zip.bak";
		if (log.isDebugEnabled()) {
			log.debug("source = {}", source);
			log.debug("target = {}", target);
		}

		zipCommonArchivalLogic(strDirPath, source);

		boolean result = EgovFileCmprs.decmprsFile(source, target);

		if (log.isDebugEnabled()) {
			log.debug("result = {}", result);
		}

//		// source => target 파일 이동
//		// sample.zip => sample.zip.bak
//		try {
//			Path filePath = Paths.get(source);
//			Path filePathToMove = Paths.get(moved);
//			Files.move(filePath, filePathToMove);
//		} catch (IOException e) {
//			throw new BaseRuntimeException(e);
//		}
	}

	private static void zipCommonArchivalLogic(String strDirPath, String source) {
		Collection<File> filesToArchive = FileUtils
				.listFiles(new File(strDirPath, "/src/test/resources/egovframework/data"), null, false);
		if (log.isDebugEnabled()) {
			log.debug("filesToArchive={}", filesToArchive);
		}
		for (File f : filesToArchive) {
			if (log.isDebugEnabled()) {
				log.debug("f={}", f);
				log.debug("getName={}", f.getName());
			}
		}

		try (ArchiveOutputStream<ZipArchiveEntry> o = new ZipArchiveOutputStream(
				new BufferedOutputStream(new FileOutputStream(source)))) {
			for (File f : filesToArchive) {
				// maybe skip directories for formats like AR that don't store directories
				ZipArchiveEntry entry = o.createArchiveEntry(f, f.getName());
				// potentially add more flags to entry
				o.putArchiveEntry(entry);
				if (f.isFile()) {
					try (InputStream i = Files.newInputStream(f.toPath())) {
						IOUtils.copy(i, o);
					}
				}
				o.closeArchiveEntry();
			}
			o.finish();
		} catch (FileNotFoundException e) {
			throw new BaseRuntimeException(e);
		} catch (IOException e) {
			throw new BaseRuntimeException(e);
		}
	}

}
