package egovframework.com.utl.sim.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * EgovXMLDoc.getXMLToClass 에 적용한 XXE 방지 옵션의 동작을 검증한다.
 *
 * <p>getXMLToClass 는 SndngMailDocument.Factory.parse 호출 시
 * {@link XmlOptions#setDisallowDocTypeDeclaration(boolean)} 와
 * {@link XmlOptions#setLoadExternalDTD(boolean)} 를 적용한다. XXE 공격은
 * DOCTYPE 선언을 통한 외부 엔티티 참조에서 비롯되므로, DOCTYPE 선언 자체가
 * 거부되는지를 동일한 XMLBeans 파서로 확인한다.</p>
 */
class EgovXMLDocXxeTest {

	// 외부 접근 없이 DOCTYPE 차단 효과만 격리하기 위해 내부 엔티티만 사용한다.
	private static final String DOCTYPE_PAYLOAD =
		"<?xml version=\"1.0\"?>"
		+ "<!DOCTYPE foo [<!ENTITY bar \"baz\">]>"
		+ "<foo>&bar;</foo>";

	@Test
	@DisplayName("하드닝 옵션 적용 시 DOCTYPE 선언 XML은 XmlException으로 거부된다")
	void hardenedOptionsRejectDoctype() {
		XmlOptions xmlOptions = new XmlOptions();
		xmlOptions.setDisallowDocTypeDeclaration(true);
		xmlOptions.setLoadExternalDTD(false);

		assertThrows(XmlException.class, () -> XmlObject.Factory.parse(DOCTYPE_PAYLOAD, xmlOptions));
	}

	@Test
	@DisplayName("옵션 미적용 시 동일 DOCTYPE 입력이 파싱됨 - 차단 주체가 옵션임을 확인")
	void defaultOptionsAllowDoctype() throws Exception {
		XmlObject parsed = XmlObject.Factory.parse(DOCTYPE_PAYLOAD);
		assertNotNull(parsed);
	}
}
