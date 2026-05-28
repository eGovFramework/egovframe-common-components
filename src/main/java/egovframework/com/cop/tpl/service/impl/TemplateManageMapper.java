package egovframework.com.cop.tpl.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cop.tpl.service.TemplateInf;
import egovframework.com.cop.tpl.service.TemplateInfVO;

/**
 * 템플릿 정보관리에 대한 Mapper 인터페이스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 */
@EgovMapper("templateManageMapper")
public interface TemplateManageMapper {

	/**
	 * 템플릿 정보를 삭제한다.
	 */
	void deleteTemplateInf(TemplateInf tmplatInf);

	/**
	 * 템플릿 정보를 등록한다.
	 */
	void insertTemplateInf(TemplateInf tmplatInf);

	/**
	 * 템플릿 정보를 수정한다.
	 */
	void updateTemplateInf(TemplateInf tmplatInf);

	/**
	 * 템플릿 화이트리스트 목록을 조회한다.
	 */
	List<TemplateInfVO> selectTemplateWhiteList();

	/**
	 * 템플릿 목록을 조회한다.
	 */
	List<TemplateInfVO> selectTemplateInfs(TemplateInfVO tmplatInfVO);

	/**
	 * 템플릿 목록 전체 건수를 조회한다.
	 */
	int selectTemplateInfsCnt(TemplateInfVO tmplatInfVO);

	/**
	 * 템플릿 상세정보를 조회한다.
	 */
	TemplateInfVO selectTemplateInf(TemplateInfVO tmplatInfVO);

	/**
	 * 템플릿 구분에 따른 목록을 조회한다.
	 */
	List<TemplateInfVO> selectTemplateInfsByCode(TemplateInfVO tmplatInfVO);

}
