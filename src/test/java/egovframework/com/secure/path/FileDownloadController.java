package egovframework.com.secure.path;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.cmm.service.EgovFileMngUtil;

@RestController
public class FileDownloadController {

    @GetMapping("/downFile.do")
    public void downFile(
            HttpServletResponse response,
            @RequestParam("streFileNm") String streFileNm,
            @RequestParam("orignFileNm") String orignFileNm) {
    	
    	EgovFileMngUtil fileMngUtil = new EgovFileMngUtil();
    	System.out.println("===> start fileDown.do");
    	
        try {
            fileMngUtil.downFile(response, streFileNm, orignFileNm);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}