package egovframework.com.utl.sim.service;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

import org.apache.commons.io.FileUtils;
import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.junit.Test;

import egovframework.com.cmm.service.EgovProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * 파일 및 텍스트 문자열 암호화 처리하는 구현 클래스 테스트
 * 
 * @author 공통컴포넌트 2025년 컨트리뷰션팀 이백행
 * @since 2025.09.08
 * @version 4.3.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2025.09.08  이백행          2025년 컨트리뷰션 최초 생성
 *
 *      </pre>
 */
@Slf4j
public class EgovFileScrtyTest {

	private static final String STORE_FILE_PATH = EgovProperties.getProperty("Globals.fileStorePath");

	@Test
	public void encryptFile() {
		String pathname = "README.md";
		try {
			FileUtils.copyFile(new File(pathname), new File(STORE_FILE_PATH, pathname));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

		String target = pathname + ".encryptFile";
		try {
			boolean encryptFile = EgovFileScrty.encryptFile(pathname, target);
			boolean decryptFile = EgovFileScrty.decryptFile(target, pathname + ".decryptFile");
			if (log.isDebugEnabled()) {
				log.debug("encryptFile={}", encryptFile);
				log.debug("decryptFile={}", decryptFile);
			}
		} catch (BaseRuntimeException e) {
			throw new BaseRuntimeException(e);
		} catch (Exception e) {
			throw new BaseRuntimeException(e);
		}
	}

}
