package egovframework.com.cmm.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * @Class Name : EgovWildcardReloadableResourceBundleMessageSource
 * @Description : 다국어 properties 파일을 팩키지 구조의 폴더로 읽어드리는 MessageSource
 * @Modification Information
 * @
 * @  수정일         수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2016.06.10    장동한          최초 생성
 *
 *  @author 2016 표준프레임워크 유지보수 장동한
 *  @since 2016.06.10
 *  @version 1.0
 *  @see
 *
 */

public class EgovWildcardReloadableResourceBundleMessageSource extends
        org.springframework.context.support.ReloadableResourceBundleMessageSource {
    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    public void setEgovBasenames(String... basenames) {
        if (basenames != null) {
            List<String> baseNames = new ArrayList<String>();
            for (int i = 0; i < basenames.length; i++) {
            	
                String basename = StringUtils.trimToEmpty(basenames[i]);
                if(basename.indexOf("classpath:/") > -1 ){
                	baseNames.add(basename);
                }else if(StringUtils.isNotBlank(basename)) {
                    try {
                    	
                        Resource[] resources = resourcePatternResolver.getResources(basename);

                        for (int j = 0; j < resources.length; j++) {
                            Resource resource = resources[j];
                            String uri = resource.getURI().toString();
                            String baseName = null;
                            
                            if(uri.indexOf(".properties") == -1){continue;}
                            
                            if (resource instanceof FileSystemResource) {
                                baseName = "classpath:" + StringUtils.substringBetween(uri, "/classes/", ".properties");
                                baseName = baseName.substring(0,baseName.indexOf("_"));
                                baseName = baseName.replaceAll("classpath:", "classpath:/");
                                if(baseNames.indexOf(baseName) > -1){continue;};
                                
                            } else if (resource instanceof ClassPathResource) {
                                baseName = StringUtils.substringBefore(uri, ".properties");
                                baseName = baseName.substring(0,baseName.indexOf("_"));
                                baseName = baseName.replaceAll("classpath:", "classpath:/");
                            } else if (resource instanceof UrlResource) {
                                baseName = "classpath:" + StringUtils.substringBetween(uri, ".jar!/", ".properties");
                                baseName = baseName.substring(0,baseName.indexOf("_"));
                                baseName = baseName.replaceAll("classpath:", "classpath:/");
                            }
                            if (baseName != null) {
                                String fullName = processBasename(baseName);
                                baseNames.add(fullName);
                            }
                        }
                    } catch (IOException e) {
                        logger.debug("No message source files found for basename " + basename + ".");
                    }
                }
     
               
            }
            
            logger.debug("EgovWildcardReloadableResourceBundleMessageSource>>basenames>["+baseNames+"}");
            setBasenames(baseNames.toArray(new String[baseNames.size()]));
        }
    }

    String processBasename(String baseName) {
        String prefix = StringUtils.substringBeforeLast(baseName, "/");
        String name = StringUtils.substringAfterLast(baseName, "/");
        do {
            name = StringUtils.substringBeforeLast(name, "_");
        } while (name.contains("_"));
        return prefix + "/" + name;
    }
}