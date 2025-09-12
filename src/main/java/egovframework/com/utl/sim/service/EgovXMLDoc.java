package egovframework.com.utl.sim.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FilenameUtils;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import egovframework.com.cmm.service.EgovProperties;
import noNamespace.SndngMailDocument;

/**
 * XML파일을 파싱하여 구조체 형태로 반환 또는 구조체 형태의 데이터를 XML파일로 저장하는 Business Interface class
 * 
 * @author 공통 서비스 개발팀 박지욱
 * @since 2009.02.03
 * @version 1.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.02.03  박지욱          최초 생성
 *   2022.11.11  김혜준          시큐어코딩 처리
 *   2024.10.29  이백행          불필요 형변환 제거 (SndngMailDocument.Factory.parse(xmlFile);)
 *   2025.09.11  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-CloseResource(부적절한 자원 해제)
 *   2025.09.11  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-AvoidReassigningParameters(넘겨받는 메소드 parameter 값을 직접 변경하는 코드 탐지)
 *
 *      </pre>
 */
public class EgovXMLDoc {

	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;

	static final String ACCESS_EXTERNAL_DTD = "http://javax.xml.XMLConstants/property/accessExternalDTD";
	static final String ACCESS_EXTERNAL_STYLESHEET = "http://javax.xml.XMLConstants/property/accessExternalStylesheet";
	static final String EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
	static final String EXTERNAL_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";

	/**
	 * XML파일을 파싱하여 메일발송 클래스(임의)에 내용을 담아 반환
	 * 
	 * @param file XML파일
	 * @return SndngMailDocument mailDoc 메일발송 클래스(XML스키마를 통해 생성된 자바클래스)
	 */
	public static SndngMailDocument getXMLToClass(String file) {
		SndngMailDocument mailDoc = null;

		String storePathString = EgovProperties.getProperty("Globals.fileStorePath");
		File xmlFile = new File(storePathString, FilenameUtils.getName(file));

		if (xmlFile.exists() && xmlFile.isFile()) {
			try {
				mailDoc = SndngMailDocument.Factory.parse(xmlFile);
			} catch (XmlException | IOException e) {
				throw new BaseRuntimeException(e);
			}
		}

		return mailDoc;
	}

	/**
	 * XML데이터를 XML파일로 저장
	 * 
	 * @param mailDoc 사용자 임의 클래스(XML스키마를 통해 생성된 자바클래스)
	 * @param file    저장될 파일
	 * @return boolean 저장여부 True / False
	 */
	public static boolean getClassToXML(SndngMailDocument mailDoc, String file) {
		boolean result = false;

		String storePathString = EgovProperties.getProperty("Globals.fileStorePath");

		String file2 = EgovFileTool.createNewFile(storePathString, FilenameUtils.getName(file));
		File xmlFile = new File(storePathString, FilenameUtils.getName(file2));

		try (FileOutputStream fos = new FileOutputStream(xmlFile);) {
			XmlOptions xmlOptions = new XmlOptions();
			xmlOptions.setSavePrettyPrint();
			xmlOptions.setSavePrettyPrintIndent(4);
			xmlOptions.setCharacterEncoding("UTF-8");
			String xmlStr = mailDoc.xmlText(xmlOptions);
			fos.write(xmlStr.getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

		result = true;

		return result;
	}

	/**
	 * XML 파일을 파싱하여 데이터를 조작할 수 있는 Document 객체를 반환
	 * 
	 * @param xml XML파일
	 * @return Document document 문서객체
	 */
	public static Document getXMLDocument(String xml) {
		Document xmlDoc = null;
		String storePathString = EgovProperties.getProperty("Globals.fileStorePath");

		File srcFile = new File(storePathString, FilenameUtils.getName(xml));

		if (srcFile.exists() && srcFile.isFile()) {
			try (FileInputStream fis = new FileInputStream(srcFile);) {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setNamespaceAware(true);
				factory.setFeature(EgovXMLConstants.FEATURE_SECURE_PROCESSING, true);
				factory.setFeature(EXTERNAL_GENERAL_ENTITIES, false);
				factory.setFeature(EXTERNAL_PARAMETER_ENTITIES, false);
				factory.setAttribute(ACCESS_EXTERNAL_DTD, "");
				factory.setAttribute(ACCESS_EXTERNAL_STYLESHEET, "");
				factory.setExpandEntityReferences(false);
				DocumentBuilder builder;
				builder = factory.newDocumentBuilder();
				xmlDoc = builder.parse(fis);
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			} catch (ParserConfigurationException e) {
				throw new BaseRuntimeException(e);
			} catch (SAXException e) {
				throw new BaseRuntimeException(e);
			}
		}

		return xmlDoc;
	}

	/**
	 * Document의 최상의 Element로 이동
	 * 
	 * @param document XML데이터
	 * @return Element root 루트
	 */
	public static Element getRootElement(Document document) throws Exception {
		return document.getDocumentElement();
	}

	/**
	 * 하위에 새로운 Elemenet를 생성
	 * 
	 * @param document XML데이터
	 * @param rt       추가될위치
	 * @param id       생성될 Element의 ID
	 * @return Element element 추가된 Element
	 */
	public static Element insertElement(Document document, Element rt, String id) throws Exception {
		Element child;
		Element root;

		if (rt == null) {
			root = getRootElement(document);
		} else {
			root = rt;
		}
		child = document.createElement(id);
		root.appendChild(child);

		return child;
	}

	/**
	 * 하위에 문자열을 가지는 새로운 Elemenet를 생성
	 * 
	 * @param document XML데이터
	 * @param rt       추가 위치
	 * @param id       생성될 Element의 ID
	 * @param text     Element 하위에 들어갈 문자열
	 * @return Element element 추가된 Element
	 */
	public static Element insertElement(Document document, Element rt, String id, String text) throws Exception {
		Element echild;
		Text tchild;
		Element root;

		if (rt == null) {
			root = getRootElement(document);
		} else {
			root = rt;
		}
		echild = document.createElement(id);
		root.appendChild(echild);
		tchild = document.createTextNode(text);
		echild.appendChild(tchild);

		return echild;
	}

	/**
	 * 하위에 문자열을 추가
	 * 
	 * @param document XML데이터
	 * @param rt       추가 위치
	 * @param text     Element 하위에 들어갈 문자열
	 * @return Element element 추가된 Element
	 */
	public static Text insertText(Document document, Element rt, String text) throws Exception {
		Text tchild;
		Element root;

		if (rt == null) {
			root = getRootElement(document);
		} else {
			root = rt;
		}
		tchild = document.createTextNode(text);
		root.appendChild(tchild);

		return tchild;
	}

	/**
	 * 마지막으로 입력되었거나 참조된 XML Node의 상위 Element를 리턴
	 * 
	 * @param current 현재노드
	 * @return Element parent 상위노드
	 */
	public static Element getParentNode(Element current) throws Exception {
		Node parent = current.getParentNode();
		return (Element) parent;
	}

	/**
	 * Document 객체를 XML파일로 저장
	 * 
	 * @param document 문서객체
	 * @param file     저장될 파일
	 * @return boolean 저장여부 True / False
	 */
	public static boolean getXMLFile(Document document, String file) throws Exception {
		boolean retVal = false;
		String storePathString = EgovProperties.getProperty("Globals.fileStorePath");

		File srcFile = new File(storePathString, FilenameUtils.getName(file));
		if (srcFile.exists() && srcFile.isFile()) {
			Source source = new DOMSource(document);
			Result result = new StreamResult(srcFile);
			TransformerFactory factory = TransformerFactory.newInstance();
			factory.setFeature(EgovXMLConstants.FEATURE_SECURE_PROCESSING, true);
			factory.setAttribute(ACCESS_EXTERNAL_DTD, "");
			factory.setAttribute(ACCESS_EXTERNAL_STYLESHEET, "");
			Transformer transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
		}

		return retVal;
	}

}
