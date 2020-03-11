package egovframework.com.sec.pki.service;

/**
 * GPKI(Goverment Public Key Infrastructure)를 위한 서비스 인터페이스 클래스
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.04
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.6.4  한성곤          최초 생성
 *
 * </pre>
 */
public interface EgovGPKIService {
    /**
     * 현 서버의 ID를 얻는다.
     * 
     * @return
     * @throws Exception
     */
    public String getServerId() throws Exception;
    
    /**
     * 데이터 암호화 처리.
     * 
     * @param message
     * @param target String 서버ID로 cn=SVRXXXXXXXXXX와 같은 CN에서 뒤 XXXXXXXXXX 지정
     * @return
     * @throws Exception
     */
    public byte[] encrypt(byte[] message, String target) throws Exception;
    
    /**
     * 복호화 처리.
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public byte[] decrypt(byte[] data) throws Exception;
    
    /**
     * 전자서명 처리.
     * 
     * @param message
     * @return
     * @throws Exception
     */
    public byte[] sign(byte[] message) throws Exception;
    
    /**
     * 전자서명 검증.
     * 
     * @param signedData
     * @return
     * @throws Exception
     */
    public byte[] verifySign(byte[] signedData) throws Exception;
    
    /**
     * BASE64 encoding 처리.
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public String getBASE64String(byte[] data) throws Exception;
    
    /**
     * BASE64 decoding 처리.
     * 
     * @param base64
     * @return
     * @throws Exception
     */
    public byte[] getDataFromBASE64(String base64) throws Exception;
}
