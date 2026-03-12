package egovframework.com.cmm.util;

import org.egovframe.rte.fdl.crypto.EgovEnvCryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import egovframework.com.cmm.service.EgovProperties;
import jakarta.annotation.Resource;


/**
 * 데이터베이스 패스워드 해결을 위한 유틸리티 클래스
 * 암호화된 패스워드를 복호화하거나 평문 패스워드를 반환
 * 
 * @author 유지보수
 * @since 2025.06.01
 */
@Component
public class EgovPasswordResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovPasswordResolver.class);
    
    @Resource(name = "egovEnvCryptoService")
    private EgovEnvCryptoService cryptoService;
    
    /**
     * 암호화 서비스 설정
     */
    public void setCryptoService(EgovEnvCryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }
    
    /**
     * EgovEnvCryptoService를 통해 crypto 설정값을 확인
     * @return true: 암호화 사용, false: 평문 사용
     */
    private boolean isCryptoEnabled() {
        try {
            if (cryptoService == null) {
                LOGGER.warn("EgovEnvCryptoService가 null입니다. 기본값으로 평문 사용");
                return false;
            }
            
            try {
                java.lang.reflect.Method method = cryptoService.getClass().getMethod("isCrypto");
                boolean cryptoEnabled = (Boolean) method.invoke(cryptoService);
                return cryptoEnabled;
            } catch (NoSuchMethodException e) {
                LOGGER.debug("isCryptoEnabled() 메서드가 없습니다. 다른 방법으로 확인 시도");
                
                // 방법 1: getCryptoConfig() 메서드 시도
                try {
                    java.lang.reflect.Method getConfigMethod = cryptoService.getClass().getMethod("getCryptoConfig");
                    Object cryptoConfig = getConfigMethod.invoke(cryptoService);
                    
                    if (cryptoConfig != null) {
                        java.lang.reflect.Method isCryptoMethod = cryptoConfig.getClass().getMethod("isCrypto");
                        boolean cryptoEnabled = (Boolean) isCryptoMethod.invoke(cryptoConfig);
                        LOGGER.debug("EgovEnvCryptoService.getCryptoConfig().isCrypto(): {}", cryptoEnabled);
                        return cryptoEnabled;
                    }
                 // 2026.02.28 KISA 취약점 조치
                } catch (ReflectiveOperationException | SecurityException e2) {
                    LOGGER.debug("getCryptoConfig() 방법도 실패: {}", e2.getMessage());
                }
                
                // 방법 2: 기본값으로 true 반환 (암호화 사용)
                LOGGER.debug("crypto 설정을 확인할 수 없어 기본값으로 암호화 사용");
                return true;
            }
         // 2026.02.28 KISA 취약점 조치
        } catch (ReflectiveOperationException | SecurityException | ClassCastException e) {
            LOGGER.error("EgovEnvCryptoService를 통한 crypto 설정 확인 오류: {}", e.getMessage());
            return true; // 오류 시 암호화 사용 (기본값)
        }
    }
    
    /**
     * 패스워드 해결 (암호화된 패스워드 복호화 또는 평문 반환)
     * EgovEnvCryptoService를 통해 crypto 설정값을 확인하여 평문/암호화 구분
     * 
     * @param propertyKey 프로퍼티 키 (예: Globals.mysql.Password)
     * @return 해결된 패스워드
     * @throws IllegalStateException 패스워드 복호화 또는 조회 실패 시
     */
    public String resolvePassword(String propertyKey) {
       
            // 1. globals.properties에서 패스워드 값 읽기
            String passwordValue = EgovProperties.getProperty(propertyKey);
            if (passwordValue == null || passwordValue.trim().isEmpty()) {
                LOGGER.warn("패스워드 값을 찾을 수 없음: {}", propertyKey);
                return "";
            }
            
            // 2. EgovEnvCryptoService를 통해 crypto 설정값 확인
            boolean cryptoEnabled = isCryptoEnabled();
            
            if (cryptoEnabled) {
                // 3-1. 암호화 사용인 경우: 복호화 수행
                try {
                    String decryptedValue = cryptoService.decrypt(passwordValue);
                    return decryptedValue;
                 // 2026.02.28 KISA 취약점 조치
                } catch (RuntimeException e) { //decrypt 특성에 맞춤
                    LOGGER.error("패스워드 복호화 실패: {} - {}", propertyKey, e.getMessage(),e);
                    throw new IllegalStateException("패스워드 복호화 오류: " + propertyKey,e);
                    // 원본 반환대신 즉시 실패
                    
                }
            } else {
                // 3-2. 평문 사용인 경우: 값 그대로 반환
                return passwordValue;
            }
            //보안점검
        
    }

}
