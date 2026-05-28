package egovframework.com.cop.ems.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.ems.service.AtchmnFileVO;
import egovframework.com.cop.ems.service.SndngMailVO;

/**
 * 발송메일을 등록하기 위한 Mapper 인터페이스
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
public interface SndngMailRegistMapper {

	/**
	 * 발송할 메일을 등록한다.
	 * @param vo SndngMailVO
	 */
	void insertSndngMail(SndngMailVO vo);

	/**
	 * 발송할 메일에 있는 첨부파일 목록을 조회한다.
	 * @param vo SndngMailVO
	 * @return List
	 */
	List<AtchmnFileVO> selectAtchmnFileList(SndngMailVO vo);

	/**
	 * 발송결과를 수정한다.
	 * @param vo SndngMailVO
	 */
	void updateSndngMail(SndngMailVO vo);

}
