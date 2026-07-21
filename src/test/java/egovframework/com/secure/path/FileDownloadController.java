package egovframework.com.secure.path;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.cmm.service.EgovFileMngUtil;

@RestController
@Slf4j
public class FileDownloadController {

    @GetMapping("/downFile.do")
    public void downFile(
            HttpServletResponse response,
            @RequestParam("streFileNm") String streFileNm,
            @RequestParam("orignFileNm") String orignFileNm) {
    	
    	EgovFileMngUtil fileMngUtil = new EgovFileMngUtil();
    	log.debug("===> start fileDown.do");
    	
        try {
            fileMngUtil.downFile(response, streFileNm, orignFileNm);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}