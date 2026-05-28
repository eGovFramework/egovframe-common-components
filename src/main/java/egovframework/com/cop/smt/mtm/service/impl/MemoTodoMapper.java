package egovframework.com.cop.smt.mtm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.smt.mtm.service.MemoTodo;
import egovframework.com.cop.smt.mtm.service.MemoTodoVO;

/**
 * 메모할일을 위한 Mapper 인터페이스
 * @author 장철호
 * @since 2010.07.19
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.19  장철호          최초 생성
 *   2026.05.28  dasomel         XML 기반 DAO → @EgovMapper 인터페이스로 전환
 *
 * </pre>
 */
@EgovMapper("memoTodoMapper")
public interface MemoTodoMapper {

	List<MemoTodoVO> selectMemoTodoList(MemoTodoVO memoTodoVO);

	MemoTodoVO selectMemoTodo(MemoTodoVO memoTodoVO);

	int updateMemoTodo(MemoTodo memoTodo);

	int insertMemoTodo(MemoTodo memoTodo);

	int deleteMemoTodo(MemoTodo memoTodo);

	int selectMemoTodoListCnt(MemoTodoVO memoTodoVO);

	List<MemoTodoVO> selectMemoTodoListToday(MemoTodoVO memoTodoVO);

}
