package egovframework.com.cop.ems.service.impl;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.ems.service.SndngMailVO;

/**
 * 발송메일을 상세 조회하기 위한 Mapper 인터페이스
 *
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.12
 * @version 1.0
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일         수정자       수정내용
 *  ----------    --------    ---------------------------
 *   2009.03.12    박지욱       최초 생성
 *
 * </pre>
 */
@EgovMapper
public interface SndngMailDetailMapper {

	/**
	 * 발송메일을 상세 조회한다.
	 * @param vo SndngMailVO
	 * @return SndngMailVO
	 */
	SndngMailVO selectSndngMail(SndngMailVO vo);

	/**
	 * 발송메일을 삭제한다.
	 * @param vo SndngMailVO
	 * @return int
	 */
	int deleteSndngMail(SndngMailVO vo);

}
