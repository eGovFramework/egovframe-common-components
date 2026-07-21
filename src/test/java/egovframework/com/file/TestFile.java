package egovframework.com.file;

import java.util.Arrays;
import java.util.List;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.Globals;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestFile {

	public static void main(String[] args) {
		
		log.debug("Path = {}", EgovProperties.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		
		String myPath = "/Users/myID/runBatch.sh";
		String propertyValue = EgovProperties.getProperty("SHELL." + Globals.OS_TYPE + ".batchShellFiles");
		if (propertyValue == null || propertyValue.length() == 0) {
			log.debug("SHELL.Globals.OSTYPE.batchShellFiles OK");
			return;
		}
		log.debug("===>>> {}", propertyValue);
		List<String> cmdShell = Arrays.asList(propertyValue.split(","));
		
		log.debug("===>>> cmdShell Length = {}", cmdShell.size());
		for(String result : cmdShell) {
			log.debug("===>>> {}", result);
		}
		
		boolean result = false;
		for(String item : cmdShell) {
			result = myPath.contains(item);
			log.debug("===> item = {} , result = {}", item, result);
			if (result) break;
		}
		
	}

}
