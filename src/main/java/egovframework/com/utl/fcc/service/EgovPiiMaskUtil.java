package egovframework.com.utl.fcc.service;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * @Class Name  : EgovPiiMaskUtil.java
 * @Description : 개인정보 마스킹 처리 관련 유틸리티
 * 
 * 제공되는 마스킹 규칙은 일반적인 활용 사례를 기준으로 하며,  
 * 실제 서비스 적용 시에는 관련 법령, 기관 가이드라인 및 업무 요건에 따라  
 * 마스킹 범위를 검토·조정해야 할 수 있다.
 * 
 * @Modification Information
 *
 *     수정일         수정자                   수정내용
 *     -------          --------        ---------------------------
 *   2025.06.05     공통컴포넌트개발팀          최초 생성
 *
 * @author 공통컴포넌트 개발팀
 * @since 2025. 06. 05
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2025.06.05  공통컴포넌트개발팀  최초 생성
 *
 * </pre>
 */
public class EgovPiiMaskUtil {

    private EgovPiiMaskUtil() {
        throw new IllegalStateException("유틸리티 클래스입니다.");
    }

    /** 주민등록번호 패턴: 생년월일 6자리 - 성별포함 7자리 */
    private static final Pattern JUMIN_PATTERN =
        Pattern.compile("(\\d{6})[-](\\d{7})");

    /** 휴대폰 번호 패턴 (010/011/016/017/018/019 계열, 하이픈 있음) */
    private static final Pattern PHONE_HYPHEN_PATTERN =
        Pattern.compile("(0\\d{1,2})[-](\\d{3,4})[-](\\d{4})");

    /** 휴대폰 번호 패턴 (하이픈 없음, 10~11자리 숫자) */
    private static final Pattern PHONE_PLAIN_PATTERN =
        Pattern.compile("(?<![\\d])(0\\d{9,10})(?![\\d])");

    /** 이메일 패턴 */
    private static final Pattern EMAIL_PATTERN =
        Pattern.compile("([A-Za-z0-9._%+\\-]{2,})(@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,})");

    /** 신용카드 패턴: 4자리-4자리-4자리-4자리 (하이픈 또는 공백 구분) */
    private static final Pattern CARD_PATTERN =
        Pattern.compile("(\\d{4})[-\\s](\\d{4})[-\\s](\\d{4})[-\\s](\\d{4})");

    /** 사업자등록번호 패턴: 3자리-2자리-5자리 */
    private static final Pattern BIZ_REG_PATTERN =
        Pattern.compile("(\\d{3})[-](\\d{2})[-](\\d{5})");

    /** 사설 IP 패턴: 10.x.x.x / 172.16~31.x.x / 192.168.x.x */
    private static final Pattern PRIVATE_IP_PATTERN =
        Pattern.compile(
            "(?<![\\d.])" +
            "(" +
                "10\\.\\d{1,3}\\.\\d{1,3}" +
                "|172\\.(1[6-9]|2\\d|3[01])\\.\\d{1,3}" +
                "|192\\.168\\.\\d{1,3}" +
            ")" +
            "\\.(\\d{1,3})" +
            "(?![\\d.])"
        );

    /**
     * 주민등록번호를 마스킹한다.
     * <p>뒤 7자리를 {@code *}로 대체한다. 예) {@code 901010-1234567} → {@code 901010-1******}</p>
     *
     * @param juminNo 주민등록번호 문자열 (null 허용)
     * @return 마스킹된 주민등록번호. null 또는 빈 문자열이면 원본 반환
     */
    public static String maskJuminNo(String juminNo) {
        if (juminNo == null || juminNo.isEmpty()) {
            return juminNo;
        }
        Matcher m = JUMIN_PATTERN.matcher(juminNo);
        if (!m.find()) {
            return juminNo;
        }
        String front = m.group(1);
        String back = m.group(2);
        String masked = front + "-" + back.charAt(0) + "******";
        return juminNo.substring(0, m.start()) + masked + juminNo.substring(m.end());
    }

    /**
     * 전화번호(휴대폰/일반)를 마스킹한다.
     * <p>가운데 구간을 {@code ****}로 대체한다. 예) {@code 010-1234-5678} → {@code 010-****-5678}</p>
     *
     * @param phoneNo 전화번호 문자열 (null 허용)
     * @return 마스킹된 전화번호. null 또는 빈 문자열이면 원본 반환
     */
    public static String maskPhoneNo(String phoneNo) {
        if (phoneNo == null || phoneNo.isEmpty()) {
            return phoneNo;
        }
        Matcher m = PHONE_HYPHEN_PATTERN.matcher(phoneNo);
        if (m.find()) {
            String masked = m.group(1) + "-" + m.group(2).replaceAll("\\d", "*") + "-" + m.group(3);
            return phoneNo.substring(0, m.start()) + masked + phoneNo.substring(m.end());
        }
        Matcher mp = PHONE_PLAIN_PATTERN.matcher(phoneNo);
        if (mp.find()) {
            String masked = maskPlainPhoneDigits(mp.group(1));
            return phoneNo.substring(0, mp.start()) + masked + phoneNo.substring(mp.end());
        }
        return phoneNo;
    }

    /**
     * 하이픈 없는 10~11자리 휴대폰 번호 숫자열을 마스킹한다.
     * <p>단일 마스킹({@link #maskPhoneNo})과 일괄 마스킹({@code maskAllPhoneNo})이
     * 동일한 규칙을 사용하도록 공통 로직으로 분리한다. 11자리는 {@code 3-****-뒤4자리},
     * 10자리는 {@code 3-***-뒤4자리} 형태로 가린다.</p>
     */
    private static String maskPlainPhoneDigits(String num) {
        if (num.length() == 11) {
            return num.substring(0, 3) + "****" + num.substring(7);
        }
        return num.substring(0, 3) + "***" + num.substring(6);
    }

    /**
     * 이메일 주소를 마스킹한다.
     * <p>로컬 파트 앞 2자리를 제외한 나머지를 {@code *}로 대체한다. 예) {@code abcde@domain.com} → {@code ab***@domain.com}</p>
     *
     * @param email 이메일 주소 문자열 (null 허용)
     * @return 마스킹된 이메일 주소. null 또는 빈 문자열이면 원본 반환
     */
    public static String maskEmail(String email) {
        if (email == null || email.isEmpty()) {
            return email;
        }
        Matcher m = EMAIL_PATTERN.matcher(email);
        if (!m.find()) {
            return email;
        }
        String local = m.group(1);
        String domain = m.group(2);
        int visibleLen = Math.min(2, local.length());
        String masked = local.substring(0, visibleLen)
            + "*".repeat(local.length() - visibleLen)
            + domain;
        return email.substring(0, m.start()) + masked + email.substring(m.end());
    }

    /**
     * 신용카드 번호를 마스킹한다.
     * <p>가운데 8자리를 {@code *}로 대체한다. 예) {@code 1234-5678-9012-3456} → {@code 1234-****-****-3456}</p>
     *
     * @param cardNo 신용카드 번호 문자열 (null 허용)
     * @return 마스킹된 카드 번호. null 또는 빈 문자열이면 원본 반환
     */
    public static String maskCardNo(String cardNo) {
        if (cardNo == null || cardNo.isEmpty()) {
            return cardNo;
        }
        Matcher m = CARD_PATTERN.matcher(cardNo);
        if (!m.find()) {
            return cardNo;
        }
        char sep = m.group(0).charAt(4);
        String masked = m.group(1) + sep + "****" + sep + "****" + sep + m.group(4);
        return cardNo.substring(0, m.start()) + masked + cardNo.substring(m.end());
    }

    /**
     * 사업자등록번호를 마스킹한다.
     * <p>마지막 5자리를 {@code *}로 대체한다. 예) {@code 123-45-67890} → {@code 123-45-*****}</p>
     *
     * @param bizRegNo 사업자등록번호 문자열 (null 허용)
     * @return 마스킹된 사업자등록번호. null 또는 빈 문자열이면 원본 반환
     */
    public static String maskBizRegNo(String bizRegNo) {
        if (bizRegNo == null || bizRegNo.isEmpty()) {
            return bizRegNo;
        }
        Matcher m = BIZ_REG_PATTERN.matcher(bizRegNo);
        if (!m.find()) {
            return bizRegNo;
        }
        String masked = m.group(1) + "-" + m.group(2) + "-*****";
        return bizRegNo.substring(0, m.start()) + masked + bizRegNo.substring(m.end());
    }

    /**
     * 사설 IP 주소의 마지막 옥텟을 마스킹한다.
     * <p>10.x.x.x, 172.16~31.x.x, 192.168.x.x 대역에 한해 마지막 옥텟을 {@code ***}로 대체한다.</p>
     * <p>예) {@code 192.168.1.100} → {@code 192.168.1.***}</p>
     *
     * @param ipAddress IP 주소 문자열 (null 허용)
     * @return 마스킹된 IP 주소. null 또는 빈 문자열, 또는 공인 IP이면 원본 반환
     */
    public static String maskPrivateIp(String ipAddress) {
        if (ipAddress == null || ipAddress.isEmpty()) {
            return ipAddress;
        }
        Matcher m = PRIVATE_IP_PATTERN.matcher(ipAddress);
        if (!m.find()) {
            return ipAddress;
        }
        String masked = m.group(1) + ".***";
        return ipAddress.substring(0, m.start()) + masked + ipAddress.substring(m.end());
    }

    /**
     * 임의 텍스트에서 주민등록번호·전화번호·이메일·신용카드·사업자등록번호·사설IP를 탐지하여 일괄 마스킹한다.
     * <p>외부 전송 전 인바운드 필터 등 텍스트 전처리 용도로 사용한다.</p>
     * <p>오탐을 줄이기 위해 명확한 패턴만 처리하며, 불명확한 패턴은 건너뛴다.</p>
     *
     * @param text 마스킹 대상 텍스트 (null 허용)
     * @return 개인정보가 마스킹된 텍스트. null이면 null 반환
     */
    public static String maskAll(String text) {
        if (text == null) {
            return null;
        }
        String result = text;
        result = maskAllJuminNo(result);
        result = maskAllPhoneNo(result);
        result = maskAllEmail(result);
        result = maskAllCardNo(result);
        result = maskAllBizRegNo(result);
        result = maskAllPrivateIp(result);
        return result;
    }

    /* ============================================================
     *  내부 일괄 처리 헬퍼 — 텍스트 전체에서 반복 치환
     * ============================================================ */

    private static String maskAllJuminNo(String text) {
        Matcher m = JUMIN_PATTERN.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String replacement = m.group(1) + "-" + m.group(2).charAt(0) + "******";
            m.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private static String maskAllPhoneNo(String text) {
        // 1) 하이픈 포함 번호 치환
        Matcher m = PHONE_HYPHEN_PATTERN.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String replacement = m.group(1) + "-" + m.group(2).replaceAll("\\d", "*") + "-" + m.group(3);
            m.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        m.appendTail(sb);

        // 2) 하이픈 없는 번호 치환 — 단일 마스킹(maskPhoneNo)과 동일 규칙 적용.
        //    1)에서 가려진 결과는 하이픈/별표를 포함하므로 연속 10~11자리 패턴에 매칭되지 않는다.
        Matcher mp = PHONE_PLAIN_PATTERN.matcher(sb.toString());
        StringBuffer sb2 = new StringBuffer();
        while (mp.find()) {
            mp.appendReplacement(sb2, Matcher.quoteReplacement(maskPlainPhoneDigits(mp.group(1))));
        }
        mp.appendTail(sb2);
        return sb2.toString();
    }

    private static String maskAllEmail(String text) {
        Matcher m = EMAIL_PATTERN.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String local = m.group(1);
            String domain = m.group(2);
            int visibleLen = Math.min(2, local.length());
            String replacement = local.substring(0, visibleLen)
                + "*".repeat(local.length() - visibleLen)
                + domain;
            m.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private static String maskAllCardNo(String text) {
        Matcher m = CARD_PATTERN.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            char sep = m.group(0).charAt(4);
            String replacement = m.group(1) + sep + "****" + sep + "****" + sep + m.group(4);
            m.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private static String maskAllBizRegNo(String text) {
        Matcher m = BIZ_REG_PATTERN.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String replacement = m.group(1) + "-" + m.group(2) + "-*****";
            m.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private static String maskAllPrivateIp(String text) {
        Matcher m = PRIVATE_IP_PATTERN.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String replacement = m.group(1) + ".***";
            m.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
