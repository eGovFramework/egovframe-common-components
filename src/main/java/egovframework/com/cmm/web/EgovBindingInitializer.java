package egovframework.com.cmm.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;

/**
 * 파일 조회, 삭제, 다운로드 처리를 위한 컨트롤러 클래스
 * 
 * @author 표준프레임워크팀 이삼섭
 * @since 2022.12.22
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자        수정내용
 *  ----------     --------    ---------------------------
 *  2022.12.22     신용호        atchFileId 파라미터 추가 보완
 *  2024.07.05     신용호        reprtId/noteId/noteTrnsmitId/noteRecptnId 파라미터 추가 보완
 *
 *      </pre>
 */

public class EgovBindingInitializer implements WebBindingInitializer {


	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
		
		binder.registerCustomEditor(String.class, "atchFileId", new EgovAtchFileIdPropertyEditor());
		
		binder.registerCustomEditor(String.class, "reprtId", new EgovCipherIdPropertyEditor()); // 메모보고/주간/월간 보고
		binder.registerCustomEditor(String.class, "noteId", new EgovCipherIdPropertyEditor()); // 쪽지관리
		binder.registerCustomEditor(String.class, "noteTrnsmitId", new EgovCipherIdPropertyEditor()); // 쪽지관리
		binder.registerCustomEditor(String.class, "noteRecptnId", new EgovCipherIdPropertyEditor()); // 쪽지관리
	}

}
