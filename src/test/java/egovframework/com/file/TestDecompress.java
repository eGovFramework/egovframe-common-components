package egovframework.com.file;

import java.io.File;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;

import egovframework.com.utl.sim.service.EgovFileCmprs;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestDecompress {

	public static void main(String[] args) {
		
		// 프로젝트 ROOT
		//String strDirPath = "C:\\eGovFrameDev-4.0.0-64bit\\workspace\\test.simple_homepage";
	    String strDirPath = "";
		strDirPath = System.getProperty("user.dir");
	    log.debug("Working Directory = {}", strDirPath);

	    //Path relativePath = Paths.get("");
	    //strDirPath = relativePath.toAbsolutePath().toString();
	    //log.debug("Working Directory = " + strDirPath);

	    String source = strDirPath + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "egovframework" + File.separator + "file" + File.separator + "sample.zip";
	    String target = strDirPath + File.separator + "target" + File.separator + "result";
	    String moved = target + File.separator + "sample.zip.bak";
	    log.debug("source = {}", source);
	    log.debug("target = {}", target);
	    log.debug("moved = {}", moved);
	    boolean result = false;
	    try {
	    	result = EgovFileCmprs.decmprsFile(source, target);
		} catch (Exception e) {
			throw new BaseRuntimeException(e);
		}
	    
	    log.debug("result = {}", result);

	    // source => target 파일 이동
	    // sample.zip => sample.zip.bak
	    /*
	    try {
	        Path filePath = Paths.get(source);
	        Path filePathToMove = Paths.get(moved);
	        Files.move(filePath, filePathToMove);
	    } catch (IOException e) {
	        throw new BaseRuntimeException(e);
	    }
	    */
		
	}

}
