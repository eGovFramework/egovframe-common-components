package egovframework.com.uss.ion.wik.bmk.service;

import java.util.List;

/**
 * 위키북마크를 처리하는 Service Class 구현
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
public interface EgovWikiBookmarkService {

    /**
	 * 위키북마크 목록을 조회한다.
	 * @param wikiBookmark -조회할 정보가 담긴 객체
	 * @return List
	 * @throws Exception
	 */
	public List<?> selectWikiBookmarkList(WikiBookmark wikiBookmark) throws Exception;

    /**
     * 위키북마크를(을) 목록 전체 건수를(을) 조회한다.
     * @param wikiBookmark  -조회할 정보가 담긴 객체
     * @return int -조회한건수가담긴Integer
     * @throws Exception
     */
    public int selectWikiBookmarkListCnt(WikiBookmark wikiBookmark) throws Exception;

    /**
     * 위키북마크를(을) 중복을 조회한다.
     * @param wikiBookmark  -조회할 정보가 담긴 객체
     * @return int -조회한건수가담긴Integer
     * @throws Exception
     */
    public int selectWikiBookmarkDuplicationCnt(WikiBookmark wikiBookmark) throws Exception;

    /**
	 * 위키북마크를(을) 등록한다.
	 * @param wikiBookmark -위키북마크 정보 담김 객체
	 * @throws Exception
	 */
	void  insertWikiBookmark(WikiBookmark wikiBookmark) throws Exception;

     /**
	 * 위키북마크를(을) 삭제한다.
	 * @param wikiBookmark -위키북마크 정보 담김 객체
	 * @throws Exception
	 */
	void  deleteWikiBookmark(WikiBookmark wikiBookmark) throws Exception;

}
