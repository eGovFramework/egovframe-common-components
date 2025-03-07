package egovframework.com.uss.ion.wik.bmk.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.ion.wik.bmk.service.WikiBookmark;

/**
 * 위키북마크를 처리하는 Dao Class 구현
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
@Repository("wikiBookmarkDao")
public class WikiBookmarkDao extends EgovComAbstractDAO {
    /**
	 * 위키북마크 목록을 조회한다.
	 * @param wikiBookmark -조회할 정보가 담긴 객체
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectWikiBookmarkList(WikiBookmark wikiBookmark) throws Exception{
		return selectList("WikiBookmark.selectWikiBookmarkList", wikiBookmark);
	}

    /**
     * 위키북마크를(을) 중복을 조회한다.
     * @param wikiBookmark  -조회할 정보가 담긴 객체
     * @return int -조회한건수가담긴Integer
     * @throws Exception
     */
    public int selectWikiBookmarkDuplicationCnt(WikiBookmark wikiBookmark) throws Exception{
    	return (Integer)selectOne("WikiBookmark.selectWikiBookmarkDuplicationCnt", wikiBookmark);
    }

    /**
     * 위키북마크를(을) 목록 전체 건수를(을) 조회한다.
     * @param wikiBookmark  -조회할 정보가 담긴 객체
     * @return int -조회한건수가담긴Integer
     * @throws Exception
     */
    public int selectWikiBookmarkListCnt(WikiBookmark wikiBookmark) throws Exception{
    	return (Integer)selectOne("WikiBookmark.selectWikiBookmarkListCnt", wikiBookmark);
    }

    /**
     * 사용자 아이디를  조회한다.
     * @param wikiBookmark  -조회할 정보가 담긴 객체
     * @return int -조회한건수가담긴Integer
     * @throws Exception
     */
    public String selectWikiBookmarkEmpUniqId(WikiBookmark wikiBookmark) throws Exception{
    	return (String)selectOne("WikiBookmark.selectWikiBookmarkEmpUniqId", wikiBookmark);
    }


    /**
	 * 위키북마크를(을) 등록한다.
	 * @param wikiBookmark -위키북마크 정보 담김 객체
	 * @throws Exception
	 */
	void insertWikiBookmark(WikiBookmark wikiBookmark) throws Exception{
		insert("WikiBookmark.insertWikiBookmark",wikiBookmark);
	}

     /**
	 * 위키북마크를(을) 수정한다.
	 * @param wikiBookmark -위키북마크 정보 담김 객체
	 * @throws Exception
	 */
	void deleteWikiBookmark(WikiBookmark wikiBookmark) throws Exception{
		delete("WikiBookmark.deleteWikiBookmark", wikiBookmark);
	}
}
