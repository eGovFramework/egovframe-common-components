package egovframework.com.uss.olh.hpc.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class HpcmVO extends HpcmDefaultVO{
	
	 private static final long serialVersionUID = 1L;
	    
	    /** 도움말 ID */
	    private String hpcmId;
	    
	    /** 도움말구분코드 */
	    private String hpcmSeCode;

	    /** 도움말구분코드명 */
	    private String hpcmSeCodeNm;
	    
	    /** 도움말정의 */
	    private String hpcmDf;
	    
	    /** 도움말 설명 */
	    private String hpcmDc;
	    
	    /** 최초등록시점 */
	    private String frstRegisterPnttm;

	    /** 최초등록자ID */
	    private String frstRegisterId;

	    /** 최종수정시점 */
	    private String lastUpdusrPnttm;

	    /** 최종수정자ID */
	    private String lastUpdusrId;

		/**
		 * hpcmId attribute 를 리턴한다.
		 * @return the String
		 */
		public String getHpcmId() {
			return hpcmId;
		}

		/**
		 * hpcmId attribute 값을 설정한다.
		 * @return hpcmId String
		 */
		public void setHpcmId(String hpcmId) {
			this.hpcmId = hpcmId;
		}

		/**
		 * hpcmSeCode attribute 를 리턴한다.
		 * @return the String
		 */
		public String getHpcmSeCode() {
			return hpcmSeCode;
		}

		/**
		 * hpcmSeCode attribute 값을 설정한다.
		 * @return hpcmSeCode String
		 */
		public void setHpcmSeCode(String hpcmSeCode) {
			this.hpcmSeCode = hpcmSeCode;
		}

		/**
		 * hpcmSeCodeNm attribute 를 리턴한다.
		 * @return the String
		 */
		public String getHpcmSeCodeNm() {
			return hpcmSeCodeNm;
		}

		/**
		 * hpcmSeCodeNm attribute 값을 설정한다.
		 * @return hpcmSeCodeNm String
		 */
		public void setHpcmSeCodeNm(String hpcmSeCodeNm) {
			this.hpcmSeCodeNm = hpcmSeCodeNm;
		}

		/**
		 * hpcmDf attribute 를 리턴한다.
		 * @return the String
		 */
		public String getHpcmDf() {
			return hpcmDf;
		}

		/**
		 * hpcmDf attribute 값을 설정한다.
		 * @return hpcmDf String
		 */
		public void setHpcmDf(String hpcmDf) {
			this.hpcmDf = hpcmDf;
		}

		/**
		 * hpcmDc attribute 를 리턴한다.
		 * @return the String
		 */
		public String getHpcmDc() {
			return hpcmDc;
		}

		/**
		 * hpcmDc attribute 값을 설정한다.
		 * @return hpcmDc String
		 */
		public void setHpcmDc(String hpcmDc) {
			this.hpcmDc = hpcmDc;
		}

		/**
		 * frstRegisterPnttm attribute 를 리턴한다.
		 * @return the String
		 */
		public String getFrstRegisterPnttm() {
			return frstRegisterPnttm;
		}

		/**
		 * frstRegisterPnttm attribute 값을 설정한다.
		 * @return frstRegisterPnttm String
		 */
		public void setFrstRegisterPnttm(String frstRegisterPnttm) {
			this.frstRegisterPnttm = frstRegisterPnttm;
		}

		/**
		 * frstRegisterId attribute 를 리턴한다.
		 * @return the String
		 */
		public String getFrstRegisterId() {
			return frstRegisterId;
		}

		/**
		 * frstRegisterId attribute 값을 설정한다.
		 * @return frstRegisterId String
		 */
		public void setFrstRegisterId(String frstRegisterId) {
			this.frstRegisterId = frstRegisterId;
		}

		/**
		 * lastUpdusrPnttm attribute 를 리턴한다.
		 * @return the String
		 */
		public String getLastUpdusrPnttm() {
			return lastUpdusrPnttm;
		}

		/**
		 * lastUpdusrPnttm attribute 값을 설정한다.
		 * @return lastUpdusrPnttm String
		 */
		public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
			this.lastUpdusrPnttm = lastUpdusrPnttm;
		}

		/**
		 * lastUpdusrId attribute 를 리턴한다.
		 * @return the String
		 */
		public String getLastUpdusrId() {
			return lastUpdusrId;
		}

		/**
		 * lastUpdusrId attribute 값을 설정한다.
		 * @return lastUpdusrId String
		 */
		public void setLastUpdusrId(String lastUpdusrId) {
			this.lastUpdusrId = lastUpdusrId;
		}
		
		/**
		 * toString 메소드를 대치한다.
		 */
		public String toString(){
			return ToStringBuilder.reflectionToString(this);
		}
}
