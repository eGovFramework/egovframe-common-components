package egovframework.com.uat.uia.web;

import static org.junit.Assert.assertEquals;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.OrderWith;
import org.junit.runner.manipulation.Alphanumeric;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;

/**
 * 로그인 화면 셀레늄 단위 테스트
 * 
 * @author 2025년 컨트리뷰션팀 이백행
 * @since 2025.07.26
 * @version 4.3.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2025.07.26  이백행          2025년 컨트리뷰션 최초 생성
 *
 *      </pre>
 */
@OrderWith(Alphanumeric.class)
@Slf4j
public class EgovLoginControllerSelenium {

	private WebDriver driver;

	private JavascriptExecutor js;

	@BeforeClass
	public static void setUpBeforeClass() {
		if (log.isDebugEnabled()) {
			log.debug("setUpBeforeClass");
		}
	}

	@AfterClass
	public static void tearDownAfterClass() {
		if (log.isDebugEnabled()) {
			log.debug("tearDownAfterClass");
		}
	}

	@Before
	public void setUp() {
		if (log.isDebugEnabled()) {
			log.debug("setUp");
		}

		// 크롬 드라이버 다운로드
		// https://storage.googleapis.com/chrome-for-testing-public/137.0.7151.69/win64/chromedriver-win64.zip
		// https://googlechromelabs.github.io/chrome-for-testing/
		// https://storage.googleapis.com/chrome-for-testing-public/137.0.7151.69/win64/chrome-win64.zip
//		System.setProperty("webdriver.chrome.driver",
//				"C:\\eGovFrameDev-4.3.0-64bit\\webdriver.chrome.driver\\chromedriver-win64\\chromedriver.exe");

		// Automated driver management and other helper features for Selenium WebDriver
		// in Java
		// Java에서 Selenium WebDriver를 위한 자동화된 드라이버 관리 및 기타 도우미 기능
//		WebDriverManager.chromedriver().setup(); // 자동 다운로드 + 경로 설정

//		driver = new ChromeDriver();
//		js = (JavascriptExecutor) driver;
	}

	@After
	public void tearDown() {
		if (log.isDebugEnabled()) {
			log.debug("tearDown");
		}

		driver.quit();
	}

	@Test
	public void test1Chrome() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;

		test(driver);
	}

	@Test
	public void test2Edge() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		js = (JavascriptExecutor) driver;

		test(driver);
	}

	private void test(WebDriver driver) {
		// 메인페이지 이동
		if (log.isDebugEnabled()) {
			log.debug("메인페이지 이동");
		}
		driver.get("http://localhost:8080/egovframework-all-in-one");
		sleep();

//		// 새로고침
//		if (log.isDebugEnabled()) {
//			log.debug("새로고침");
//		}
//		js.executeScript("location.reload()");
//		sleep();

		// 타이틀 확인
		if (log.isDebugEnabled()) {
			log.debug("타이틀 확인");
		}
		String title = driver.getTitle();
		if (log.isDebugEnabled()) {
			log.debug("title={}", title);
		}
		assertEquals("에러가 발생했습니다!", "eGovFrame 공통 컴포넌트", title);

		driver.switchTo().frame("_content");
//		sleep();

		// 아이디 입력
		if (log.isDebugEnabled()) {
			log.debug("아이디 입력");
		}
		driver.findElement(By.id("id")).sendKeys("USER");
		sleep();

		// 비밀번호 입력
		if (log.isDebugEnabled()) {
			log.debug("비밀번호 입력");
		}
		driver.findElement(By.id("password")).sendKeys("rhdxhd12");
		sleep();

		// 로그인 버튼 클릭
		if (log.isDebugEnabled()) {
			log.debug("로그인 버튼 클릭");
		}
//		driver.findElement(By.cssSelector(".btn_login:nth-child(1)")).click();
		js.executeScript("actionLogin()");
		sleep();

		// 로그아웃 버튼 확인
		String aText = driver.findElement(By.tagName("a")).getText();
		if (log.isDebugEnabled()) {
			log.debug("로그아웃 버튼 확인");
			log.debug("aText={}", aText);
		}
		assertEquals("에러가 발생했습니다!", "로그아웃", aText);
//		sleep();
	}

	private void sleep() {
		try {
//			Thread.sleep(1_000); // 1초
//			Thread.sleep(2_000); // 2초
			Thread.sleep(3_000); // 3초
		} catch (InterruptedException e) {
			throw new BaseRuntimeException(e);
		}
	}

}
