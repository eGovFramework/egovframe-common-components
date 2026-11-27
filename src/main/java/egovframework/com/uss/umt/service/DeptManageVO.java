package egovframework.com.uss.umt.service;

import egovframework.com.cmm.ComDefaultVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeptManageVO extends ComDefaultVO {

	private static final long serialVersionUID = 1L;
	private String orgnztId;
	private String orgnztNm;
	private String orgnztDc;

}
