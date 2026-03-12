package egovframework.com.secure.path;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import egovframework.com.cmm.aop.EgovFileBasePathSecurityValidator;

public class EgovFileBasePathSecurityValidatorTest {

    public static void main(String[] args) {

        System.out.println("=== EgovFileBasePathSecurityValidator 테스트 ===\n");

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
            System.out.print("Testing: " + path + " -> ");
            EgovFileBasePathSecurityValidator.validate(path);
            System.out.println("---");
        }
    }
}