package egovframework.config;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.egovframe.rte.fdl.property.impl.EgovPropertyServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EgovConfigProperty {

    @Bean(name = "localPropertiesService", destroyMethod = "destroy")
    public EgovPropertyServiceImpl egovPropertyService() {
    	
        EgovPropertyServiceImpl egovPropertyService = new EgovPropertyServiceImpl();
        
        /*
        // properties 설정
        Map<String, String> properties = new HashMap<>();
        properties.put("pageUnit", "20");
        properties.put("pageSize", "10");
        
        egovPropertyService.setProperties(properties);
        */

        // extFileName 설정
        Set<Object> extFileNameSet = new HashSet<>();

        // Map 정의
        Map<String, String> extFileMap = new HashMap<>();
        extFileMap.put("encoding", "UTF-8");
        extFileMap.put("filename", "classpath*:/egovframework/egovProps/conf/config.properties");

        // Set에 Map 추가
        extFileNameSet.add(extFileMap);

        // 필요시 주석에 있는 단일 값 추가
        // extFileNameSet.add("classpath*:");

        egovPropertyService.setExtFileName(extFileNameSet);
        
        return egovPropertyService;
    }
}