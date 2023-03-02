package egovframework.com.sec.rnc.mip.mva.sp.comm.exception;

import org.apache.logging.log4j.util.Base64Util;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import egovframework.com.sec.rnc.mip.mva.sp.comm.enums.MipErrorEnum;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.M900VO;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.MipApiDataVO;
import egovframework.com.sec.rnc.mip.mva.sp.config.ConfigBean;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.exception
 * @FileName    : SpExceptionHandler.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 7.
 * @Description : SP Exception Controller Advice
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 7.    Min Gi Ju        최초생성
 */
@RestControllerAdvice
public class SpExceptionHandler {

	/**
	 * SP Excpetion 핸들러
	 * 
	 * @MethodName : handleSpException
	 * @param e SP Exception
	 * @return
	 */
	@ExceptionHandler(SpException.class)
	public MipApiDataVO handleSpException(SpException e) {
		MipApiDataVO mipApiData = new MipApiDataVO();

		M900VO m900 = new M900VO();

		m900.setType(ConfigBean.TYPE);
		m900.setVersion(ConfigBean.VERSION);
		m900.setCmd(ConfigBean.M900);
		m900.setErrcode(e.getErrcode());
		m900.setErrmsg(e.getErrmsg());
		m900.setTrxcode(e.getTrxcode());

		mipApiData.setResult(false);
		mipApiData.setData(Base64Util.encode(ConfigBean.gson.toJson(m900)));

		return mipApiData;
	}

	/**
	 * Excpetion 핸들러
	 * 
	 * @MethodName : handleException
	 * @param e Excpetion
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public MipApiDataVO handleException(Exception e) {
		MipApiDataVO mipApiData = new MipApiDataVO();

		M900VO m900 = new M900VO();

		m900.setType(ConfigBean.TYPE);
		m900.setVersion(ConfigBean.VERSION);
		m900.setCmd(ConfigBean.M900);
		m900.setErrcode(MipErrorEnum.UNKNOWN_ERROR.getCode());
		m900.setErrmsg(MipErrorEnum.UNKNOWN_ERROR.getMsg());

		mipApiData.setResult(false);
		mipApiData.setData(Base64Util.encode(ConfigBean.gson.toJson(m900)));

		return mipApiData;
	}

}
