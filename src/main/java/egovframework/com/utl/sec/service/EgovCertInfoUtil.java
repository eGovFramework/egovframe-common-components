package egovframework.com.utl.sec.service;

import javax.servlet.http.HttpServletRequest;

import com.dsjdf.jdf.Config;
import com.dsjdf.jdf.Configuration;
import com.dsjdf.jdf.ConfigurationException;
import com.gpki.gpkiapi.cert.X509Certificate;
import com.gpki.gpkiapi.exception.GpkiApiException;
import com.gpki.gpkiapi.storage.Disk;
import com.gpki.gpkiapi.util.Base64;
import com.gpki.servlet.GPKIHttpServletRequest;

/**
 * GPKISecureWeb 인증서 로그인 서비스 유틸
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.08.06
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.06  한성곤          최초 생성
 *
 * </pre>
 */
public class EgovCertInfoUtil {
    /**
     * 서버인증서에 대한 Base64 정보를 얻는다.
     *
     * @return
     * @throws ConfigurationException
     * @throws GpkiApiException
     */
    public static String getBase64ServerCert() throws ConfigurationException, GpkiApiException {
	/*
	 * Configuration를 사용하기 위해서는 다음과 같은 시스템 변수 지정이 필요함
	 *
	 * -Dcom.dsjdf.config.file="/product/jeus/egovProps/gpkisecureweb/conf/dsjdf.properties"
	 */
	Config dsjdf = new Configuration();

	String certPath = dsjdf.get("GPKISecureWeb.CertFilePathName");

	X509Certificate x509Cert = null;
	byte[] cert = null;
	String base64cert = null;

	x509Cert = Disk.readCert(certPath);
	cert = x509Cert.getCert();
	Base64 base64 = new Base64();
	base64cert = base64.encode(cert);

	return base64cert;
    }

    /**
     * 인증서에 대한 정보를 제공한다.
     *
     * @param request
     * @return
     * @throws Exception
     */
    public static CertInfoVO getCertInfo(HttpServletRequest request) throws Exception {
	CertInfoVO certInfo = new CertInfoVO();

	GPKIHttpServletRequest gpkirequest = null;

	//System.out.println(request.getParameter("encryptedData"));

	gpkirequest = new GPKIHttpServletRequest(request);

	X509Certificate cert = gpkirequest.getSignerCert();

	certInfo.setSubjectDn(cert.getSubjectDN());
	certInfo.setIssuerDn(cert.getIssuerDN());

	return certInfo;
    }
}
