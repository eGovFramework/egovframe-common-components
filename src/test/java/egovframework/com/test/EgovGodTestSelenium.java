package egovframework.com.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 셀레늄은 웹 애플리케이션 자동화 및 테스트를 위한 포터블 프레임워크이다. 셀레늄은 테스트 스크립트 언어를 학습할 필요 없이 기능 테스트를
 * 만들기 위한 플레이백 도구를 제공한다.
 * 
 * @author 이백행
 * @since 2023-08-25
 */
@NoArgsConstructor
@Slf4j
public class EgovGodTestSelenium {
    /**
     * WebDriver
     */
    private WebDriver driver;

    /**
     * vars
     */
    private Map<String, Object> vars;

    /**
     * JavascriptExecutor
     */
    private JavascriptExecutor js;

    /**
     * setUp
     */
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<>();
    }

    /**
     * tearDown
     */
    @After
    public void tearDown() {
        driver.quit();
    }

    /**
     * 30. 로그인정책관리 단위 테스트
     * 
     * 로그인정책 관리 단위 테스트
     * 
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        vars.put("id", "USER");// 아이디

        vars.put("searchKeyword", "일반회원"); // 사용자명검색
//        vars.put("searchKeyword", "테스트1"); // 사용자명검색
//        vars.put("searchKeyword", "NIA"); // 사용자명검색
//        vars.put("searchKeyword", "웹마스터"); // 사용자명검색

        // URL
        driver.get("http://localhost:8080/egovframework-all-in-one/");

        final String title = driver.getTitle();
        log.debug("title={}", title);
        assertEquals("에러가 발생했습니다!", "eGovFrame 공통 컴포넌트", title);

        driver.switchTo().frame("_content");

        // 아이디
//        driver.findElement(By.id("id")).sendKeys("USER");
        driver.findElement(By.id("id")).sendKeys((String) vars.get("id"));
        Thread.sleep(1000);

        // 비밀번호
        driver.findElement(By.id("password")).sendKeys("rhdxhd12");
        Thread.sleep(1000);

        // 로그인 버튼
        driver.findElement(By.cssSelector(".btn_login:nth-child(1)")).click();
//        js.executeScript("actionLogin()");
        Thread.sleep(1000);

        // 30. 로그인정책관리
        driver.switchTo().defaultContent();
        driver.switchTo().frame("_left");
        driver.findElement(By.linkText("30. 로그인정책관리")).click();
        Thread.sleep(1000);

        driver.switchTo().defaultContent();
        driver.switchTo().frame("_content");

//        js.executeScript("alert('로그인정책 관리')");
//        Thread.sleep(1000);

        // 사용자 명 :
        driver.findElement(By.name("searchKeyword")).sendKeys((String) vars.get("searchKeyword"));
        Thread.sleep(1000);

        // 조회 버튼
        js.executeScript("fncSelectLoginPolicyList('1')");
        Thread.sleep(1000);

        final WebElement message = driver
                .findElement(By.cssSelector("body > div > table > tbody > tr > td:nth-child(2)"));
        final String value = message.getText();
        log.debug("value={}", value);
        assertEquals("에러가 발생했습니다!", vars.get("searchKeyword"), value);

        Thread.sleep(10_000);
    }
}
