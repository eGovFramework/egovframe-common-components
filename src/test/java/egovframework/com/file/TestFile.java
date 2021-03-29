package egovframework.com.file;

import java.util.Arrays;
import java.util.List;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.Globals;

public class TestFile {

	public static void main(String[] args) {
		
		System.out.println("Path = "+EgovProperties.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		
		String myPath = "/Users/myID/runBatch.sh";
		String propertyValue = EgovProperties.getProperty("SHELL." + Globals.OS_TYPE + ".batchShellFiles");
		if (propertyValue == null || propertyValue.length() == 0) {
			System.out.println("SHELL.Globals.OSTYPE.batchShellFiles OK");
			return;
		}
		System.out.println("===>>> "+propertyValue);
		List<String> cmdShell = Arrays.asList(propertyValue.split(","));
		
		System.out.println("===>>> cmdShell Length = " + cmdShell.size());
		for(String result : cmdShell) {
			System.out.println("===>>> "+result);
		}
		
		boolean result = false;
		for(String item : cmdShell) {
			result = myPath.contains(item);
			System.out.println("===> item = "+item +" , result = "+result);
			if (result) break;
		}
		
	}

}
