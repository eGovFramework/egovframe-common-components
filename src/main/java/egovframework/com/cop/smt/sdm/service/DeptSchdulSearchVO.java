package egovframework.com.cop.smt.sdm.service;

import egovframework.com.cmm.ComDefaultVO;
import lombok.Getter;
import lombok.Setter;

/**
 * 일정 목록 조회(일지 등록 시 일정 선택 팝업)에서 등록자로 범위를 좁히기 위한 검색 조건.
 *
 * @author 공통서비스 개발팀
 * @since 2026.09.29
 * @version 1.0
 */
@SuppressWarnings("serial")
public class DeptSchdulSearchVO extends ComDefaultVO {

	/** 등록자 고유ID (지정하면 해당 등록자가 등록한 일정만 조회한다) */
	@Getter @Setter
	private String frstRegisterId = "";
}
