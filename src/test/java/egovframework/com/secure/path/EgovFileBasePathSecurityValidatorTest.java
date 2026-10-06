package egovframework.com.secure.path;

import lombok.extern.slf4j.Slf4j;

import egovframework.com.cmm.aop.EgovFileBasePathSecurityValidator;

@Slf4j
public class EgovFileBasePathSecurityValidatorTest {

    public static void main(String[] args) {

        log.debug("=== EgovFileBasePathSecurityValidator 테스트 ===\n");

        // 테스트 케이스들
        /*
        String[] testPaths = {
            "/var/www/uploads/files",                    
            "/var/www/uploads/files/subdir",             
            "/var/www/uploads",                         
            "/var/www",                                  
            "/var",
            "/",
        };*/
        String[] testPaths = {
            "/opt/egov/sync/files",
            "/opt/egov/sync/files/subdir",
            "/opt/egov/sync/files/subdir/images",
            "/opt/egov/sync",
            "/opt/egov",
            "/opt",
            "/",
        };
        

        for (String path : testPaths) {
        	log.debug("Testing: {} -> ", path);
            EgovFileBasePathSecurityValidator.validate(path);
            log.debug("---");
        }
    }
}