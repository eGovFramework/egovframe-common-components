package egovframework.com.sec.captcha;

import egovframework.com.sec.captcha.service.EgovCaptchaService;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/captcha")
public class EgovCaptchaController {

  @Autowired
  private EgovCaptchaService captchaService;


  @GetMapping("/image.do")
  public void getCaptcha(HttpServletResponse response) throws IOException {
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    captchaService.generateCaptcha();

    final ServletOutputStream out = response.getOutputStream();
    captchaService.writeCaptcha(out);
  }

  @GetMapping("/confirm.do")
  @ResponseBody
  public String getCaptcha(@RequestParam String key) {
    try {
      captchaService.matches(key);
    } catch (EgovBizException e) {
      return Boolean.FALSE.toString();
    }

    return Boolean.TRUE.toString();
  }

}
