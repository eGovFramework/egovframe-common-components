package egovframework.com.cop.ems.service;

import java.io.Serializable;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

/**
 * 발송메일에 첨부파일용으로 사용되는 VO 클래스
 * @author 공통서비스 개발팀 이기하
 * @since 2011.12.06
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *    수정일      	수정자          수정내용
 *  ----------     --------    ---------------------------
 *  2011.12.06		이기하          최초 생성
 *  2013.05.23		이기하          thread-safe 하게 변경
 *
 *  </pre>
 */

public class EgovMultiPartEmail implements Serializable {

	private static final long serialVersionUID = -4322006921324597283L;
	private String id;
	private String password;
	private int port;
	private String host;
	private String emailAddress;
	private String senderName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	@Deprecated
	public String send() throws EmailException {
		MultiPartEmail email = new MultiPartEmail();

		email.setCharset("UTF-8");
		email.setHostName(this.host);
		email.setSmtpPort(this.port);
		email.setStartTLSEnabled(true);
		email.setAuthenticator(new DefaultAuthenticator(this.id, this.password));
		email.setSocketConnectionTimeout(60000);
		email.setSocketTimeout(60000);
		email.setFrom(this.emailAddress, this.senderName);

		return email.send();
	}

	public String send(String addTo, String subject, String msg) throws EmailException {
		return send(addTo, subject, msg, null);
	}

	public String send(String addTo, String subject, String msg, EmailAttachment attachment) throws EmailException {
		MultiPartEmail email = new MultiPartEmail();

		email.setCharset("UTF-8");
		email.setHostName(this.host);
		email.setSmtpPort(this.port);
		email.setStartTLSEnabled(true);
		email.setAuthenticator(new DefaultAuthenticator(this.id, this.password));
		email.setSocketConnectionTimeout(60000);
		email.setSocketTimeout(60000);
		email.setFrom(this.emailAddress, this.senderName);
		email.addTo(addTo);
		email.setSubject(subject);
		email.setMsg(msg);

		if (attachment != null) {
			email.attach(attachment);
		}

		return email.send();

	}

}
