package egovframework.com.sec.captcha.service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import java.io.IOException;
import java.io.OutputStream;

public interface EgovCaptchaService {

  String generateCaptcha();

  void writeCaptcha(OutputStream out) throws IOException;

  void matches(String captchaKey) throws EgovBizException;

}
