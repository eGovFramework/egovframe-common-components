package egovframework.com.uss.ion.msi.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import lombok.Getter;
import lombok.Setter;
import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 개요
 * - 메인화면이미지에 대한 Vo 클래스를 정의한다.
 *
 * 상세내용
 * - 메인화면이미지의 일련번호, 이미지명, 링크URL, 이미지설명, 반영여부 및 목록 항목을 관리한다.
 *
 * @author 이문준
 * @since 2010.08.03
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.03  이문준          최초 생성
 *   2026.05.26  기여자          Lombok @Getter/@Setter 적용으로 보일러플레이트 코드 제거
 *
 *      </pre>
 */
@Getter
@Setter
public class MainImageVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;

	/** 이미지 ID */
	private String imageId;

	/** 이미지명 */
	@EgovNullCheck
	@Size(max = 10)
	private String imageNm;

	/** 메인 이미지 */
	@NotBlank(message = "{uss.ion.msi.mainImageRegist.ImageReq}")
	private String image;

	/** 메인 이미지 파일 */
	private String imageFile;

	/** 이미지 설명 */
	private String imageDc;

	/** 반영여부 */
	private String reflctAt;

	/** 사용자 ID */
	private String userId;

	/** 등록일자 */
	private String regDate;

	/** 파일첨부여부 */
	private boolean isAtchFile;

	/** 메인화면이미지 목록 */
	private List<MainImageVO> mainImageList;

	/** 삭제대상 목록 */
	private String[] delYn;

}
