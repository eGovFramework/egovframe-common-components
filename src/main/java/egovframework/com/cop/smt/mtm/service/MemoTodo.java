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

	/**
	 * 할일제목 값읽기
	 * 
	 * <pre>
	 * 메모할일정보.할일제목
	 * comtnmemotodo.`TODO_SJ` varchar(255) NOT NULL COMMENT '할일제목',
	 * </pre>
	 * 
	 * @return
	 */
	public String getTodoNm() {
		return todoNm;
	}

	/**
	 * 할일제목 값설정
	 * 
	 * <pre>
	 * 메모할일정보.할일제목
	 * comtnmemotodo.`TODO_SJ` varchar(255) NOT NULL COMMENT '할일제목',
	 * </pre>
	 * 
	 * @param todoNm
	 */
	public void setTodoNm(String todoNm) {
		this.todoNm = todoNm;
	}

	/**
	 * 할일시작시간 값읽기
	 * 
	 * <pre>
	 * 메모할일정보.할일시작시간
	 * comtnmemotodo.`TODO_BEGIN_TIME` varchar(14) NOT NULL COMMENT '할일시작시간',
	 * </pre>
	 *  
	 * @return
	 */
	public String getTodoBeginTime() {
		return todoBeginTime;
	}

	/**
	 * 할일시작시간 값설정
	 * 
	 * <pre>
	 * 메모할일정보.할일시작시간
	 * comtnmemotodo.`TODO_BEGIN_TIME` varchar(14) NOT NULL COMMENT '할일시작시간',
	 * </pre>
	 * 
	 * @param todoBeginTime
	 */
	public void setTodoBeginTime(String todoBeginTime) {
		this.todoBeginTime = todoBeginTime;
	}

	/**
	 * 할일종료시간 값읽기
	 * 
	 * <pre>
	 * 메모할일정보.할일종료시간
	 * comtnmemotodo.`TODO_END_TIME` varchar(14) NOT NULL COMMENT '할일종료시간',
	 * </pre>
	 * 
	 * @return
	 */
	public String getTodoEndTime() {
		return todoEndTime;
	}

	/**
	 * 할일종료시간 값설정
	 * 
	 * <pre>
	 * 메모할일정보.할일종료시간
	 * comtnmemotodo.`TODO_END_TIME` varchar(14) NOT NULL COMMENT '할일종료시간',
	 * </pre>
	 * 
	 * @param todoEndTime
	 */
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

	/**
	 * 작성자ID 값읽기
	 * 
	 * <pre>
	 * 메모할일정보.작성자ID
	 * comtnmemotodo.`WRTER_ID` varchar(20) NOT NULL COMMENT '작성자ID',
	 * </pre>
	 * 
	 * @return
	 */
	public String getWrterId() {
		return wrterId;
	}

	/**
	 * 작성자ID 값설정
	 * 
	 * <pre>
	 * 메모할일정보.작성자ID
	 * comtnmemotodo.`WRTER_ID` varchar(20) NOT NULL COMMENT '작성자ID',
	 * </pre>
	 * 
	 * @param wrterId
	 */
	public void setWrterId(String wrterId) {
		this.wrterId = wrterId;
	}

	public String getWrterNm() {
		return wrterNm;
	}

	public void setWrterNm(String wrterNm) {
		this.wrterNm = wrterNm;
	}

	/**
	 * 할일내용 값읽기
	 * 
	 * <pre>
	 * 메모할일정보.할일내용
	 * comtnmemotodo.`TODO_CN` varchar(2500) NOT NULL COMMENT '할일내용',
	 * </pre>
	 * 
	 * @return
	 */
	public String getTodoCn() {
		return todoCn;
	}

	/**
	 * 할일내용 값설정
	 * 
	 * <pre>
	 * 메모할일정보.할일내용
	 * comtnmemotodo.`TODO_CN` varchar(2500) NOT NULL COMMENT '할일내용',
	 * </pre>
	 * 
	 * @param todoCn
	 */
	public void setTodoCn(String todoCn) {
		this.todoCn = todoCn;
	}

	/**
	 * 최초등록자ID 값읽기
	 * 
	 * <pre>
	 * 메모할일정보.최초등록자ID
	 * comtnmemotodo.`FRST_REGISTER_ID` varchar(20) NOT NULL COMMENT '최초등록자ID',
	 * </pre>
	 * 
	 * @return
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * 최초등록자ID 값설정
	 * 
	 * <pre>
	 * 메모할일정보.최초등록자ID
	 * comtnmemotodo.`FRST_REGISTER_ID` varchar(20) NOT NULL COMMENT '최초등록자ID',
	 * </pre>
	 * 
	 * @param frstRegisterId
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * 최초등록시점 값읽기
	 * 
	 * <pre>
	 * 메모할일정보.최초등록시점
	 * comtnmemotodo.`FRST_REGIST_PNTTM` datetime NOT NULL COMMENT '최초등록시점',
	 * </pre>
	 * 
	 * @return
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	/**
	 * 최초등록시점 값설정
	 * 
	 * <pre>
	 * 메모할일정보.최초등록시점
	 * comtnmemotodo.`FRST_REGIST_PNTTM` datetime NOT NULL COMMENT '최초등록시점',
	 * </pre>
	 * 
	 * @param frstRegisterPnttm
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * 최종수정자ID 값읽기
	 * 
	 * <pre>
	 * 메모할일정보.최종수정자ID
	 * comtnmemotodo.`LAST_UPDUSR_ID` varchar(20) DEFAULT NULL COMMENT '최종수정자ID',
	 * </pre>
	 * 
	 * @return
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * 최종수정자ID 값설정
	 * 
	 * <pre>
	 * 메모할일정보.최종수정자ID
	 * comtnmemotodo.`LAST_UPDUSR_ID` varchar(20) DEFAULT NULL COMMENT '최종수정자ID',
	 * </pre>
	 * 
	 * @param lastUpdusrId
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	/**
	 * 최종수정시점 값읽기
	 * 
	 * <pre>
	 * 메모할일정보.최종수정시점
	 * comtnmemotodo.`LAST_UPDT_PNTTM` datetime DEFAULT NULL COMMENT '최종수정시점',
	 * </pre>
	 * 
	 * @return
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	/**
	 * 최종수정시점 값설정
	 * 
	 * <pre>
	 * 메모할일정보.최종수정시점
	 * comtnmemotodo.`LAST_UPDT_PNTTM` datetime DEFAULT NULL COMMENT '최종수정시점',
	 * </pre>
	 * 
	 * @param lastUpdusrPnttm
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

}