package egovframework.com.sec.captcha.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.trace.LeaveaTrace;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;


public class EgovSessionBasedCaptchaServiceTest {

//  @Test
//  public void shouldGetRandomNumberForDefinedLength() {
//    // GIVEN
//    final EgovSessionBasedCaptchaService service = new EgovSessionBasedCaptchaService();
//    final int definedLength = 6;
//
//    // WHEN
//    final String randomKey = service.generateCaptcha();
//    System.out.printf("randomKey: %s", randomKey);
//
//    // THEN
//    assertTrue(randomKey.matches("\\d{" + definedLength + "}"));
//  }

//  @Test(expected = IllegalArgumentException.class)
//  public void shouldOccurExceedLength10() {
//    // GIVEN
//    final EgovSessionBasedCaptchaService service = new EgovSessionBasedCaptchaService();
//
//    // WHEN
//    final int definedLength = 11;
//    // THEN
//    final String randomKey = service.createRandomKey(definedLength);
//  }

//  @Test
//  public void shouldGenerateJPEGImage() throws Exception {
//    // GIVEN
//    final ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
//    final EgovSessionBasedCaptchaService service = new EgovSessionBasedCaptchaService();
//
//
//    // WHEN
//    when(service.generateCaptcha()).thenReturn("123456");
//    service.writeCaptcha(out);
//
//    // https://nightohl.tistory.com/entry/JPEG-%ED%97%A4%EB%8D%94-%EA%B5%AC%EC%A1%B0
//    // JPEG 파일의 해더는 "FF D8"로 고정 된대요.
//
//    // THEN
//    assertEquals("FF", String.format("%02X", out.toByteArray()[0] & 0xff));
//    assertEquals("D8", String.format("%02X", out.toByteArray()[1] & 0xff));
//  }

//  @Test(expected = IllegalArgumentException.class)
//  public void shouldThrownGenerateWithNonRandomKey() throws IOException {
//    // GIVEN
//    // WHEN
//    new EgovSessionBasedCaptchaService().matches("");
//  }

//  @Test(expected = IllegalArgumentException.class)
//  public void shouldThrownGenerateWithEmptyRandomKey() throws IOException {
//    // GIVEN
//    // WHEN
//    new EgovSessionBasedCaptchaService().matches(null);
//  }

}
