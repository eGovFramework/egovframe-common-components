package egovframework.com.cop.smt.mtm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.smt.mtm.service.MemoTodo;
import egovframework.com.cop.smt.mtm.service.MemoTodoVO;

/**
 * <pre>
 * 개요
 * - 메모할일에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 메모할일에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 메모할일의 조회기능은 목록조회, 상세조회로 구분된다.
 * </pre>
 * 
 * @author 장철호
 * @version 1.0
 * @created 19-7-2010 오전 10:12:47
 * 
 *          <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.7.19	장철호          최초 생성
 *
 *          </pre>
 */
@Repository("MemoTodoDAO")
public class MemoTodoDAO extends EgovComAbstractDAO {

	/**
	 * 주어진 조건에 맞는 메모할일 목록을 불러온다.
	 * 
	 * @param MemoTodoVO - 메모할일 VO
	 * @return List<MemoTodoVO> - 메모할일 List
	 * 
	 * @param memoTodoVO
	 */
	public List<MemoTodoVO> selectMemoTodoList(MemoTodoVO memoTodoVO) {
		List<MemoTodoVO> resultList = selectList("MemoTodoDAO.selectMemoTodoList", memoTodoVO);
		for (int i = 0; i < resultList.size(); i++) {
			MemoTodoVO resultVO = resultList.get(i);
			resultVO.setTodoDe(resultVO.getTodoBeginTime().substring(0, 10));
			resultVO.setTodoBeginHour(resultVO.getTodoBeginTime().substring(10, 12));
			resultVO.setTodoBeginMin(resultVO.getTodoBeginTime().substring(12, 14));
			resultVO.setTodoEndHour(resultVO.getTodoEndTime().substring(10, 12));
			resultVO.setTodoEndMin(resultVO.getTodoEndTime().substring(12, 14));
			resultList.set(i, resultVO);
		}
		return resultList;
	}

	/**
	 * 주어진 조건에 맞는 메모할일을 불러온다.
	 * 
	 * @param MemoTodoVO - 메모할일 VO
	 * @return MemoTodoVO - 메모할일 VO
	 * 
	 * @param memoTodoVO
	 */
	public MemoTodoVO selectMemoTodo(MemoTodoVO memoTodoVO) {
		MemoTodoVO resultVO = (MemoTodoVO) selectOne("MemoTodoDAO.selectMemoTodo", memoTodoVO);
		resultVO.setTodoDe(resultVO.getTodoBeginTime().substring(0, 10));
		resultVO.setTodoBeginHour(resultVO.getTodoBeginTime().substring(10, 12));
		resultVO.setTodoBeginMin(resultVO.getTodoBeginTime().substring(12, 14));
		resultVO.setTodoEndHour(resultVO.getTodoEndTime().substring(10, 12));
		resultVO.setTodoEndMin(resultVO.getTodoEndTime().substring(12, 14));

		return resultVO;
	}

	/**
	 * 메모할일 정보를 수정한다.
	 * 
	 * @param MemoTodo - 메모할일 model
	 * 
	 * @param memoTodo
	 */
	public void updateMemoTodo(MemoTodo memoTodo) {
		update("MemoTodoDAO.updateMemoTodo", memoTodo);
	}

	/**
	 * 메모할일 정보를 등록한다.
	 * 
	 * @param MemoTodo - 메모할일 model
	 * 
	 * @param memoTodo
	 */
	public void insertMemoTodo(MemoTodo memoTodo) {
		insert("MemoTodoDAO.insertMemoTodo", memoTodo);
	}

	/**
	 * 메모할일 정보를 삭제한다.
	 * 
	 * @param MemoTodo - 메모할일 model
	 * 
	 * @param memoTodo
	 */
	public void deleteMemoTodo(MemoTodo memoTodo) {
		delete("MemoTodoDAO.deleteMemoTodo", memoTodo);
	}

	/**
	 * 메모할일 목록에 대한 전체 건수를 조회한다.
	 * 
	 * @param MemoTodoVO - 메모할일 VO
	 * @return int - 메모할일 목록 개수
	 * 
	 * @param memoTodoVO
	 */
	public int selectMemoTodoListCnt(MemoTodoVO memoTodoVO) {
		return (Integer) selectOne("MemoTodoDAO.selectMemoTodoListCnt", memoTodoVO);
	}

	/**
	 * 메모할일 목록 중 오늘의 할일을 조회한다.
	 * 
	 * @param MemoTodoVO - 메모할일 VO
	 * @return List - 메모할일 List
	 * 
	 * @param memoTodoVO
	 */
	public List<MemoTodoVO> selectMemoTodoListToday(MemoTodoVO memoTodoVO) {
		List<MemoTodoVO> resultList = selectList("MemoTodoDAO.selectMemoTodoListToday", memoTodoVO);
		for (int i = 0; i < resultList.size(); i++) {
			MemoTodoVO resultVO = resultList.get(i);
			resultVO.setTodoDe(resultVO.getTodoBeginTime().substring(0, 10));
			resultVO.setTodoBeginHour(resultVO.getTodoBeginTime().substring(10, 12));
			resultVO.setTodoBeginMin(resultVO.getTodoBeginTime().substring(12, 14));
			resultVO.setTodoEndHour(resultVO.getTodoEndTime().substring(10, 12));
			resultVO.setTodoEndMin(resultVO.getTodoEndTime().substring(12, 14));
			resultList.set(i, resultVO);
		}
		return resultList;
	}

}