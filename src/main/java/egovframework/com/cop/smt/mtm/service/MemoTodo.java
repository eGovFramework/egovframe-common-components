package egovframework.com.cop.smt.mtm.service;

import java.io.Serializable;

/**
 * 개요
 * - 메모할일에 대한 model 클래스를 정의한다.
 * 
 * 상세내용
 * - 할일ID, 할일제목, 할일시작시간, 할일종료시간, 작성자ID, 할일내용 항목을 관리한다.
 * @author 장철호
 * @version 1.0
 * @created 19-7-2010 오전 10:12:47
 *   <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.7.19	장철호          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class MemoTodo implements Serializable {

	/** 할일ID */
	private String todoId;
	/** 할일제목 */
	private String todoNm;
	/** 할일시작시간 */
	private String todoBeginTime;
	/** 할일종료시간 */
	private String todoEndTime;
	/** 할일일자 */
	private String todoDe;
	/** 할일시작시 */
	private String todoBeginHour;
	/** 할일시작분 */
	private String todoBeginMin;
	/** 할일종료시 */
	private String todoEndHour;
	/** 할일종료분 */
	private String todoEndMin;
	/** 작성자ID */
	private String wrterId;
	/** 작성자명 */
	private String wrterNm;
	/** 할일내용 */
	private String todoCn;
	/** 최초등록자ID */
	private String frstRegisterId = "";
	/** 최초등록시점 */
	private String frstRegisterPnttm = "";
	/** 최종수정자ID */
	private String lastUpdusrId = "";
	/** 최종수정시점 */
	private String lastUpdusrPnttm = "";

	public String getTodoId() {
		return todoId;
	}

	public void setTodoId(String todoId) {
		this.todoId = todoId;
	}

	public String getTodoNm() {
		return todoNm;
	}

	public void setTodoNm(String todoNm) {
		this.todoNm = todoNm;
	}

	public String getTodoBeginTime() {
		return todoBeginTime;
	}

	public void setTodoBeginTime(String todoBeginTime) {
		this.todoBeginTime = todoBeginTime;
	}

	public String getTodoEndTime() {
		return todoEndTime;
	}

	public void setTodoEndTime(String todoEndTime) {
		this.todoEndTime = todoEndTime;
	}

	public String getTodoDe() {
		return todoDe;
	}

	public void setTodoDe(String todoDe) {
		this.todoDe = todoDe;
	}

	public String getTodoBeginHour() {
		return todoBeginHour;
	}

	public void setTodoBeginHour(String todoBeginHour) {
		this.todoBeginHour = todoBeginHour;
	}

	public String getTodoBeginMin() {
		return todoBeginMin;
	}

	public void setTodoBeginMin(String todoBeginMin) {
		this.todoBeginMin = todoBeginMin;
	}

	public String getTodoEndHour() {
		return todoEndHour;
	}

	public void setTodoEndHour(String todoEndHour) {
		this.todoEndHour = todoEndHour;
	}

	public String getTodoEndMin() {
		return todoEndMin;
	}

	public void setTodoEndMin(String todoEndMin) {
		this.todoEndMin = todoEndMin;
	}

	public String getWrterId() {
		return wrterId;
	}

	public void setWrterId(String wrterId) {
		this.wrterId = wrterId;
	}

	public String getWrterNm() {
		return wrterNm;
	}

	public void setWrterNm(String wrterNm) {
		this.wrterNm = wrterNm;
	}

	public String getTodoCn() {
		return todoCn;
	}

	public void setTodoCn(String todoCn) {
		this.todoCn = todoCn;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

}