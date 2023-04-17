package egovframework.com.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import egovframework.com.utl.sim.service.EgovFileCmprs;

public class TestDecompress {

	public static void main(String[] args) {
		
		// 프로젝트 ROOT
		//String strDirPath = "C:\\eGovFrameDev-4.0.0-64bit\\workspace\\test.simple_homepage";
	    String strDirPath = "";
		strDirPath = System.getProperty("user.dir");
	    System.out.println("Working Directory = " + strDirPath);

	    //Path relativePath = Paths.get("");
	    //strDirPath = relativePath.toAbsolutePath().toString();
	    //System.out.println("Working Directory = " + strDirPath);

	    String source = strDirPath + File.separator + "target" + File.separator + "sample.zip";
	    String target = strDirPath + File.separator + "target" + File.separator + "result";
	    String moved = target + File.separator + "sample.zip.bak";
	    System.out.println("source = " + source);
	    System.out.println("target = " + target);
	    boolean result = false;
	    try {
	    	result = EgovFileCmprs.decmprsFile(source, target);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    System.out.println("result = " + result);

	    // source => target 파일 이동
	    // sample.zip => sample.zip.bak
	    try {
	        Path filePath = Paths.get(source);
	        Path filePathToMove = Paths.get(moved);
	        Files.move(filePath, filePathToMove);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}

}
