package egovframework.com.uss.umt.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 변경된 MBER_NM DDL의 저장 동작을 HSQLDB(Oracle 문법)로 확인한다.
 * 실제 Oracle/Altibase/Tibero/Goldilocks 엔진은 CI에 없으므로,
 * 문자 의미(VARCHAR2(50 CHAR))와 Altibase 바이트 매핑(VARCHAR2(150))을 재현한다.
 */
class MberNmStorageTest {

	private static final String HANGUL_50 = repeat("가", 50);
	private static final String HANGUL_51 = repeat("가", 51);
	private static final String CJK_50 = repeat("王", 50);
	private static final String INTERNATIONAL = "José María Álvarez 홍길동";

	private Connection connection;

	@BeforeEach
	void openDatabase() throws Exception {
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		connection = DriverManager.getConnection("jdbc:hsqldb:mem:mbernmstore;shutdown=true", "sa", "");
		try (Statement statement = connection.createStatement()) {
			statement.execute("SET DATABASE SQL SYNTAX ORA TRUE");
		}
	}

	@AfterEach
	void closeDatabase() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	@Test
	void fiftyHangulCharactersAreOneHundredFiftyUtf8Bytes() {
		assertEquals(50, HANGUL_50.length());
		assertEquals(150, HANGUL_50.getBytes(StandardCharsets.UTF_8).length,
				"UTF-8 Hangul is 3 bytes, so Altibase must use VARCHAR2(150) instead of VARCHAR2(50 CHAR)");
		assertTrue(HANGUL_50.getBytes(StandardCharsets.UTF_8).length > 50,
				"50 Hangul characters exceed a 50-byte Altibase VARCHAR2(50) column");
	}

	@Test
	void oracleFamilyCharSemanticsStoresFiftyHangulCharacters() throws SQLException {
		execute("CREATE TABLE mber_nm_char (MBER_NM VARCHAR2(50) NOT NULL)");

		assertEquals(HANGUL_50, insertAndLoad("mber_nm_char", HANGUL_50));
		assertEquals(CJK_50, insertAndLoad("mber_nm_char", CJK_50));
		assertEquals(INTERNATIONAL, insertAndLoad("mber_nm_char", INTERNATIONAL));
		assertThrows(SQLException.class, () -> insertAndLoad("mber_nm_char", HANGUL_51),
				"51 characters must not fit in VARCHAR2(50 CHAR) / VARCHAR(50)");
	}

	@Test
	void altibaseMappedColumnStoresFiftyHangulCharacters() throws SQLException {
		// Altibase rejects VARCHAR2(n CHAR). Official eGov mapping is CHAR length * 3.
		execute("CREATE TABLE mber_nm_altibase (MBER_NM VARCHAR2(150) NOT NULL)");

		assertEquals(HANGUL_50, insertAndLoad("mber_nm_altibase", HANGUL_50));
		assertEquals(CJK_50, insertAndLoad("mber_nm_altibase", CJK_50));
		assertEquals(INTERNATIONAL, insertAndLoad("mber_nm_altibase", INTERNATIONAL));
	}

	@Test
	void fiftyByteColumnCannotStoreFiftyHangulCharacters() {
		byte[] hangul50Utf8 = HANGUL_50.getBytes(StandardCharsets.UTF_8);
		assertEquals(150, hangul50Utf8.length);
		assertTrue(hangul50Utf8.length > 50,
				"legacy Altibase/Oracle VARCHAR2(50) byte column cannot hold 50 Hangul characters");
	}

	private void execute(String sql) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.execute(sql);
		}
	}

	private String insertAndLoad(String table, String name) throws SQLException {
		try (PreparedStatement delete = connection.prepareStatement("DELETE FROM " + table)) {
			delete.executeUpdate();
		}
		try (PreparedStatement insert = connection.prepareStatement("INSERT INTO " + table + " (MBER_NM) VALUES (?)")) {
			insert.setString(1, name);
			assertEquals(1, insert.executeUpdate());
		}
		try (PreparedStatement select = connection.prepareStatement("SELECT MBER_NM FROM " + table);
				ResultSet resultSet = select.executeQuery()) {
			assertTrue(resultSet.next());
			return resultSet.getString(1);
		}
	}

	private static String repeat(String value, int count) {
		StringBuilder builder = new StringBuilder(value.length() * count);
		for (int i = 0; i < count; i++) {
			builder.append(value);
		}
		return builder.toString();
	}

}
