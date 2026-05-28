package egovframework.com.uss.ion.ntr.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.uss.ion.ntr.service.NoteRecptn;

/**
 * 받은쪽지함관리에 대한 Mapper 인터페이스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 * </pre>
 */
@EgovMapper("noteRecptnMapper")
public interface NoteRecptnMapper {

	List<EgovMap> selectNoteRecptn(NoteRecptn noteRecptn);

	int selectNoteRecptnCnt(NoteRecptn noteRecptn);

	void updateNoteRecptnRelationOpenYn(NoteRecptn noteRecptn);

	Map<?, ?> selectNoteRecptnDetail(NoteRecptn noteRecptn);

	void deleteNoteRecptnRelation(NoteRecptn noteRecptn);

	void deleteNoteRecptn(NoteRecptn noteRecptn);

	void deleteNoteTrnsmit(NoteRecptn noteRecptn);

	void deleteNoteManage(NoteRecptn noteRecptn);

	int selectNoteTrnsmitRelationCnt(NoteRecptn noteRecptn);

	int selectNoteRecptnRelationCnt(NoteRecptn noteRecptn);
}
