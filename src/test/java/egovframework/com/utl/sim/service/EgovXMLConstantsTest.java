package egovframework.com.utl.sim.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * EgovXMLConstants 상수 값 검증 테스트
 */
public class EgovXMLConstantsTest {

	@Test
	void testNullNsUri() {
		assertEquals("", EgovXMLConstants.NULL_NS_URI);
	}

	@Test
	void testDefaultNsPrefix() {
		assertEquals("", EgovXMLConstants.DEFAULT_NS_PREFIX);
	}

	@Test
	void testXmlNsUri() {
		assertEquals("http://www.w3.org/XML/1998/namespace", EgovXMLConstants.XML_NS_URI);
	}

	@Test
	void testXmlNsPrefix() {
		assertEquals("xml", EgovXMLConstants.XML_NS_PREFIX);
	}

	@Test
	void testXmlnsAttributeNsUri() {
		assertEquals("http://www.w3.org/2000/xmlns/", EgovXMLConstants.XMLNS_ATTRIBUTE_NS_URI);
	}

	@Test
	void testXmlnsAttribute() {
		assertEquals("xmlns", EgovXMLConstants.XMLNS_ATTRIBUTE);
	}

	@Test
	void testW3cXmlSchemaNsUri() {
		assertEquals("http://www.w3.org/2001/XMLSchema", EgovXMLConstants.W3C_XML_SCHEMA_NS_URI);
	}

	@Test
	void testW3cXmlSchemaInstanceNsUri() {
		assertEquals("http://www.w3.org/2001/XMLSchema-instance", EgovXMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
	}

	@Test
	void testW3cXpathDatatypeNsUri() {
		assertEquals("http://www.w3.org/2003/11/xpath-datatypes", EgovXMLConstants.W3C_XPATH_DATATYPE_NS_URI);
	}

	@Test
	void testXmlDtdNsUri() {
		assertEquals("http://www.w3.org/TR/REC-xml", EgovXMLConstants.XML_DTD_NS_URI);
	}

	@Test
	void testRelaxngNsUri() {
		assertEquals("http://relaxng.org/ns/structure/1.0", EgovXMLConstants.RELAXNG_NS_URI);
	}

	@Test
	void testFeatureSecureProcessing() {
		assertNotNull(EgovXMLConstants.FEATURE_SECURE_PROCESSING);
		assertEquals("http://jakarta.xml.XMLConstants/feature/secure-processing", EgovXMLConstants.FEATURE_SECURE_PROCESSING);
	}
}
