package egovframework.com.uss.ion.rmm.service;

/**
 * 개요
 * - 약도에 대한 Model을 정의한다.
 *
 * 상세내용
 * - 약도 정보를 관리한다.
 *
 * @author 옥찬우
 * @since 2014.08.27
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일			수정자		수정내용
 *  -----------		------		---------
 *   2014.08.27		옥찬우		최초 생성
 *
 * </pre>
 */

public class RoughMapVO extends RoughMapDefaultVO {

	private static final long serialVersionUID = -2344076278228282853L;

	/** roughMap ID */
    private String roughMapId;

	/** roughMap 제목 */
    private String roughMapSj;

	/** roughMap 상세주소 */
    private String roughMapAddress;

    /** 지도 위도 */
    private String la;

    /** 지도 경도 */
    private String lo;

    /** 마커 위도 */
    private String markerLa;

    /** 마커 경도 */
    private String markerLo;

    /** 인포윈도우(말풍선) */
    private String infoWindow;

    /** 지도 확대수준 */
    private String zoomLevel;

    /** 최초등록시점 */
    private String frstRegisterPnttm;

    /** 최초등록자ID */
    private String frstRegisterId;

    /** 최종수정시점 */
    private String lastUpdusrPnttm;

    /** 최종수정자ID */
    private String lastUpdusrId;

	/**
	 * roughMapId attribute를 리턴한다.
	 * @return the Integer
	 */
    public String getRoughMapId() {
		return roughMapId;
	}

    /**
     * roughMapId를 저장한다.
     * @param roughMapId
     */
    public void setRoughMapId(String roughMapId) {
		this.roughMapId = roughMapId;
	}

	/**
	 * roughMapSj attribute를 리턴한다.
	 * @return the String
	 */
	public String getRoughMapSj() {
		return roughMapSj;
	}

    /**
     * roughMapSj를 저장한다.
     * @param roughMapSj
     */
	public void setRoughMapSj(String roughMapSj) {
		this.roughMapSj = roughMapSj;
	}

	/**
	 * roughMapAddress attribute를 리턴한다.
	 * @return the String
	 */
	public String getRoughMapAddress() {
		return roughMapAddress;
	}

    /**
     * roughMapAddress를 저장한다.
     * @param roughMapAddress
     */
	public void setRoughMapAddress(String roughMapAddress) {
		this.roughMapAddress = roughMapAddress;
	}

	/**
	 * la attribute를 리턴한다.
	 * @return the String
	 */
	public String getLa() {
		return la;
	}

    /**
     * la를 저장한다.
     * @param la
     */
	public void setLa(String la) {
		this.la = la;
	}

	/**
	 * lo attribute를 리턴한다.
	 * @return the String
	 */
	public String getLo() {
		return lo;
	}

    /**
     * lo를 저장한다.
     * @param lo
     */
	public void setLo(String lo) {
		this.lo = lo;
	}

	/**
	 * markerLa attribute를 리턴한다.
	 * @return the String
	 */
	public String getMarkerLa() {
		return markerLa;
	}

    /**
     * markerLa를 저장한다.
     * @param markerLa
     */
	public void setMarkerLa(String markerLa) {
		this.markerLa = markerLa;
	}

	/**
	 * markerLo attribute를 리턴한다.
	 * @return the String
	 */
	public String getMarkerLo() {
		return markerLo;
	}

    /**
     * markerLo를 저장한다.
     * @param markerLo
     */
	public void setMarkerLo(String markerLo) {
		this.markerLo = markerLo;
	}

	/**
	 * @return the String
	 * infoWindow attribute를 리턴한다.
	 */
	public String getInfoWindow() {
		return infoWindow;
	}


    /**
     * infoWindow를 저장한다.
     * @param infoWindow
     */
	public void setInfoWindow(String infoWindow) {
		this.infoWindow = infoWindow;
	}

	/**
	 * mapLevel attribute를 리턴한다.
	 * @return the String
	 */
	public String getZoomLevel() {
		return zoomLevel;
	}

    /**
     * mapLevel를 저장한다.
     * @param mapLevel
     */
	public void setZoomLevel(String zoomLevel) {
		this.zoomLevel = zoomLevel;
	}

	/**
	 * frstRegisterPnttm attribute를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

    /**
     * frstRegisterPnttm를 저장한다.
     * @param frstRegisterPnttm
     */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * frstRegisterId attribute를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

    /**
     * frstRegisterId를 저장한다.
     * @param frstRegisterId
     */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * lastUpdusrPnttm attribute를 리턴한다.
	 * @return the String
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

    /**
     * lastUpdusrPnttm를 저장한다.
     * @param lastUpdusrPnttm
     */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	/**
	 * lastUpdusrId attribute를 리턴한다.
	 * @return the String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

    /**
     * lastUpdusrId를 저장한다.
     * @param lastUpdusrId
     */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

}