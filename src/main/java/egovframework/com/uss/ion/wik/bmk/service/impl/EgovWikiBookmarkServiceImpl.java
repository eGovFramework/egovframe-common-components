package egovframework.com.uss.ion.wik.bmk.service.impl;

import java.util.List;

import egovframework.com.uss.ion.wik.bmk.service.EgovWikiBookmarkService;
import egovframework.com.uss.ion.wik.bmk.service.WikiBookmark;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 위키북마크를 처리하는 ServiceImpl Class 구현
 * @author 공통콤포넌트 장동한
 * @since 2010.10.20
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.10.20  장동한          최초 생성
 *
 * </pre>
 */
@Service("egovWikiBookmarkService")
public class EgovWikiBookmarkServiceImpl extends EgovAbstractServiceImpl implements EgovWikiBookmarkService {

	/* 위키북마크 DAO */
    @Resource(name = "wikiBookmarkDao")
    private WikiBookmarkDao dao;

    /* WIKI_ID Generator Service */
    @Resource(name = "egovWikiBookmarkIdGnrService")
    private EgovIdGnrService idgenService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovWikiBookmarkServiceImpl.class);

    /**
	 * 위키북마크 목록을 조회한다.
	 * @param wikiBookmark -조회할 정보가 담긴 객체
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> selectWikiBookmarkList(WikiBookmark wikiBookmark) throws Exception{
		return dao.selectWikiBookmarkList(wikiBookmark);
	}

    /**
     * 위키북마크를(을) 목록 전체 건수를(을) 조회한다.
     * @param wikiBookmark  -조회할 정보가 담긴 객체
     * @return int -조회한건수가담긴Integer
     * @throws Exception
     */
    @Override
	public int selectWikiBookmarkListCnt(WikiBookmark wikiBookmark) throws Exception{
    	return dao.selectWikiBookmarkListCnt(wikiBookmark);
    }

    /**
     * 위키북마크를(을) 중복을 조회한다.
     * @param wikiBookmark  -조회할 정보가 담긴 객체
     * @return int -조회한건수가담긴Integer
     * @throws Exception
     */
    @Override
	public int selectWikiBookmarkDuplicationCnt(WikiBookmark wikiBookmark) throws Exception{
    	return dao.selectWikiBookmarkDuplicationCnt(wikiBookmark);
    }

    /**
	 * 위키북마크를(을) 등록한다.
	 * @param wikiBookmark -위키북마크 정보 담김 객체
	 * @throws Exception
	 */
    @Override
	public void insertWikiBookmark(WikiBookmark wikiBookmark) throws Exception{
    	//아이디 가져오기

    	String sUsid  = dao.selectWikiBookmarkEmpUniqId(wikiBookmark);

    	LOGGER.debug("EgovWikiBookmarkServiceImpl.java sUsid > {}", sUsid);

		//아이디 비교
    	if(sUsid != null){
    		//위키북마크 키 설정
        	wikiBookmark.setWikiBkmkId(idgenService.getNextStringId());
        	//아이디 설정
    		wikiBookmark.setUsid(sUsid);
    		wikiBookmark.setFrstRegisterId(sUsid);
    		wikiBookmark.setLastUpdusrId(sUsid);
    		dao.insertWikiBookmark(wikiBookmark);
    		LOGGER.debug("insertWikiBookmark > {}", sUsid);
    	};

	}

     /**
	 * 위키북마크를(을) 삭제한다.
	 * @param wikiBookmark -위키북마크 정보 담김 객체
	 * @throws Exception
	 */
	@Override
	public void deleteWikiBookmark(WikiBookmark wikiBookmark) throws Exception{
		dao.deleteWikiBookmark(wikiBookmark);
	}

}
