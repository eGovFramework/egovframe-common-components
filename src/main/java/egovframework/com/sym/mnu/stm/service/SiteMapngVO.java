package egovframework.com.sym.mnu.stm.service;

import lombok.Getter;
import lombok.Setter;

/**
 * 사이트맵 조회를 위한 VO 클래스르를 정의한다.
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이  용          최초 생성
 *
 * </pre>
 */
@Getter
@Setter
public class SiteMapngVO {

	/** 메뉴생성내역 */
	/** 보안설정대상ID */
	private String scrtyEstbstrgetId;

	/** 사이트맵 */
	/** 생성자ID **/
	private String creatPersonId;
	/** 맵생성ID */
	private String mapCreatId;
	/** 맵파일명 */
	private String bndeFileNm;
	/** 맵파일경로 */
	private String bndeFilePath;

}
