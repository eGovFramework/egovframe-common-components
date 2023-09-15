package egovframework.com.file;

import java.io.IOException;

import org.apache.commons.compress.archivers.ArchiveException;

import egovframework.com.utl.sim.service.EgovFileCmprs;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestDecompress {

    /**
     * 파일(디렉토리)을 압축해제하는 기능 테스트
     * 
     * @param args
     */
    public static void main(String[] args) {

        String strDirPath = "src/test/resources/egovframework/data";
        log.debug("Working Directory = {}", strDirPath);

        String source = strDirPath + "/sample.zip";
        String target = "target/test/sample";
        decmprsFile(source, target);

        source = strDirPath + "/샘플.zip";
        target = "target/test/샘플";
        decmprsFile(source, target);

    }

    private static void decmprsFile(String source, String target) {
        log.debug("source = {}", source);
        log.debug("target = {}", target);
        boolean result = false;
        try {
            result = EgovFileCmprs.decmprsFile(source, target);
        } catch (ArchiveException e) {
//            e.printStackTrace();
            log.error("ArchiveException decmprsFile");
        } catch (IOException e) {
//            e.printStackTrace();
            log.error("IOException decmprsFile");
        }

        log.debug("result = {}", result);
    }

}
