package egovframework.com.utl.sim;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import egovframework.com.utl.sim.service.EgovFileCmprs;
import lombok.extern.slf4j.Slf4j;

/**
 * 파일(디렉토리)을 압축해제하는 기능 테스트
 * 
 * @author 이백행
 * @since 2023-09-15
 */
@Slf4j
public class TestEgovFileCmprs {

    /**
     * 파일(디렉토리)을 압축해제하는 기능 테스트
     */
    @Test
    public void test() {
        // 행정표준코드 관리시스템

        // 기관코드 전체자료
        String specSource = "https://www.code.go.kr/etc/codeFullDown.do?codeseId=00001";
        String pathnameDestination = "target/test/code.go.kr/기관코드 전체자료.zip";

        // 법정동코드 전체자료
//        String specSource = "https://www.code.go.kr/etc/codeFullDown.do?codeseId=00002";
//        String pathnameDestination = "target/test/code.go.kr/법정동코드 전체자료.zip";

        // 도로명코드 전체자료
//        String specSource = "https://www.code.go.kr/etc/codeFullDown.do?codeseId=00321";
//        String pathnameDestination = "target/test/code.go.kr/도로명코드 전체자료.zip";
        // ???θ??ڵ?_??ü.txt (파일 이름, 디렉터리 이름 또는 볼륨 레이블 구문이 잘못되었습니다)
        // 압축이 잘못된 것 같음

        codeFullDown(specSource, pathnameDestination);
        decmprsFile(pathnameDestination);
    }

    private void codeFullDown(String specSource, String pathnameDestination) {
        URL source = null;
        try {
            source = new URL(specSource);
        } catch (MalformedURLException e) {
//            e.printStackTrace();
            log.error("MalformedURLException URL");
            fail("MalformedURLException URL");
        }

        File destination = new File(pathnameDestination);

        int connectionTimeoutMillis = 10_000;

        int readTimeoutMillis = connectionTimeoutMillis;

        try {
            FileUtils.copyURLToFile(source, destination, connectionTimeoutMillis, readTimeoutMillis);
        } catch (IOException e) {
//            e.printStackTrace();
            log.error("IOException copyURLToFile");
            fail("IOException copyURLToFile");
        }
    }

    private void decmprsFile(String source) {
        String target = source.substring(0, source.lastIndexOf("."));
        boolean result = false;
        try {
            result = EgovFileCmprs.decmprsFile(source, target);
        } catch (ArchiveException e) {
//            e.printStackTrace();
            log.error("ArchiveException decmprsFile");
            fail("ArchiveException decmprsFile");
        } catch (IOException e) {
//            e.printStackTrace();
            log.error("IOException decmprsFile");
            fail("IOException decmprsFile");
        }
        log.debug("result={}", result);
    }

}
