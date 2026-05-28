package egovframework.com.uss.ion.ntm.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.ion.ntm.service.NoteManageVO;

/**
 * 쪽지 관리(보내기)에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("noteManageMapper")
public interface NoteManageMapper {

	Map<?, ?> selectNoteManage(NoteManageVO noteManage);

	void insertNoteManage(NoteManageVO noteManage);

	void insertNoteTrnsmit(NoteManageVO noteManage);

	void insertNoteRecptn(NoteManageVO noteManage);

	List<EgovMap> EovNoteEmpListPopup(ComDefaultVO searchVO);

	int EovNoteEmpListPopupCnt(ComDefaultVO searchVO);
}
