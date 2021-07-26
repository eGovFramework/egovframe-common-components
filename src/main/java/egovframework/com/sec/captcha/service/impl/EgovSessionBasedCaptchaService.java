package egovframework.com.sec.captcha.service.impl;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import reactor.util.StringUtils;

public class EgovSessionBasedCaptchaService extends EgovAbstractCaptchaService {

  public static final String EGOV_CAPTCHA_SESSION_KEY = "__EGOV_CAPTCHA__";


  @Override
  public String generateCaptcha() {
    final String captcha = super.generateCaptcha();

    final RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
    attrs.setAttribute(EGOV_CAPTCHA_SESSION_KEY, captcha, SCOPE_SESSION);

    return captcha;
  }

  @Override
  public void matches(String captcha) throws EgovBizException {
    if (StringUtils.isEmpty(captcha)) {
      final Exception exception = processException("comCopSecCaptcha.notAllowedEmptyCaptcha");
      throw new EgovBizException(exception.getMessage(), exception);
    }

    final RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
    final Object saved = attrs.getAttribute(EGOV_CAPTCHA_SESSION_KEY, SCOPE_SESSION);

    if (!captcha.equals(saved)) {
      final Exception exception = processException("comCopSecCaptcha.notMatchedCaptcha");
      throw new EgovBizException(exception.getMessage(), exception);
    }
  }

}
