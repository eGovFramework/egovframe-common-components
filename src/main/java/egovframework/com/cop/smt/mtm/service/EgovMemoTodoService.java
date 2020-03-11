package egovframework.com.cop.smt.mtm.service;

import java.util.List;
import java.util.Map;

/**
 * 개요
 * - 메모할일에 대한 Service Interface를 정의한다.
 * 
 * 상세내용
 * - 메모할일에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 메모할일의 조회기능은 목록조회, 상세조회, 오늘의 할일조회로 구분된다.
 * @author 장철호
 * @version 1.0
 * @created 19-7-2010 오전 10:12:46
 *   <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.7.19	장철호          최초 생성
 *
 * </pre>
 */
public interface EgovMemoTodoService {

	/**
	 * 메모할일 목록을 조회한다.
	 * @param MemoTodoVO - 메모할일 VO
	 * @return  Map<String, Object> - 메모할일 List
	 * 
	 * @param memoTodoVO
	 */
	public Map<String, Object> selectMemoTodoList(MemoTodoVO memoTodoVO) throws Exception;

	/**
	 * 메모할일 정보를 조회한다.
	 * @param MemoTodoVO - 메모할일 VO
	 * @return  MemoTodoVO - 메모할일 VO
	 * 
	 * @param memoTodoVO
	 */
	public MemoTodoVO selectMemoTodo(MemoTodoVO memoTodoVO) throws Exception;

	/**
	 * 메모할일 정보를 수정한다.
	 * @param MemoTodo - 메모할일 model
	 * 
	 * @param memoTodo
	 */
	public void updateMemoTodo(MemoTodo memoTodo) throws Exception;

	/**
	 * 메모할일 정보를 등록한다.
	 * @param MemoTodo - 메모할일 model
	 * 
	 * @param memoTodo
	 */
	public void insertMemoTodo(MemoTodo memoTodo) throws Exception;

	/**
	 * 메모할일 정보를 삭제한다.
	 * @param MemoTodo - 메모할일 model
	 * 
	 * @param memoTodo
	 */
	public void deleteMemoTodo(MemoTodo memoTodo) throws Exception;

	/**
	 * 메모할일 목록 중 오늘의 할일을 조회한다.
	 * @param MemoTodoVO - 메모할일 VO
	 * @return  List<MemoTodoVO> - 메모할일 List
	 * 
	 * @param memoTodoVO
	 */
	public List<MemoTodoVO> selectMemoTodoListToday(MemoTodoVO memoTodoVO) throws Exception;

}