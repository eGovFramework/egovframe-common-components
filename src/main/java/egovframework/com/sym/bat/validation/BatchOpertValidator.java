package egovframework.com.sym.bat.validation;

import java.io.File;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.sym.bat.service.BatchOpert;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * BatchOpert클래스에대한 validator 클래스.
 * common validator가 처리하지 못하는 부분 검사.
 *
 * @author 김진만
 * @version 1.0
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.08.20   김진만     최초 생성
 * </pre>
 */
@Component("batchOpertValidator")
public class BatchOpertValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return BatchOpert.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object obj, Errors errors) {
		// 배치프로그램으로 지정된 값이 파일로 존재하는지 검사한다.
		BatchOpert batchOpert = (BatchOpert) obj;
		//KISA 보안약점 조치 (2018-10-29, 윤창원)
		File file = new File(EgovWebUtil.filePathBlackList(batchOpert.getBatchProgrm()));
		try {
			if (!file.exists()) {
				errors.rejectValue("batchProgrm", "errors.batchProgrm", new Object[] { batchOpert.getBatchProgrm() }, "배치프로그램 {0}이  존재하지 않습니다.");
				return;
			}
			if (!file.isFile()) {
				errors.rejectValue("batchProgrm", "errors.batchProgrm", new Object[] { batchOpert.getBatchProgrm() }, "배치프로그램 {0}이 파일이 아닙니다.");
				return;
			}
		} catch (SecurityException se) {
			errors.rejectValue("batchProgrm", "errors.batchProgrm", new Object[] { batchOpert.getBatchProgrm() }, " 배치프로그램 {0}에 접근할 수 없습니다. 파일접근권한을 확인하세요.");
		}

	}

}
