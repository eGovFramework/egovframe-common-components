package egovframework.com.cmm.crypto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

import org.egovframe.rte.fdl.crypto.config.EgovCryptoConfig;
import org.egovframe.rte.fdl.crypto.config.EgovCryptoConfigReader;
import org.egovframe.rte.fdl.crypto.impl.EgovEnvCryptoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.core.io.DefaultResourceLoader;

import egovframework.com.cmm.crypto.EgovCryptoKeyInitializer.Options;

/**
 * 암호화 키 초기화 도구 테스트
 *
 * <p>프로젝트 구조(src/main/resources/...)를 임시 디렉터리에 만들어 실행한다. 저장소의 실제 설정 파일은 개발자가 이미
 * 초기화했을 수 있으므로 쓰지 않고, 배포 상태와 같은 fixture 를 만든다.</p>
 */
class EgovCryptoKeyInitializerTest {

	/** 배포된 globals.properties 의 DB 비밀번호 암호문(평문 com01, 기본 키, 표준 Base64 패딩 형식) */
	private static final String PUBLISHED_CIPHER = "xz4fmrSdr1vGGl6UtwPLwA%3D%3D";

	private static final String CONFIG = "src/main/resources/egovframework/egovProps/conf/egov-crypto-config.properties";
	private static final String GLOBALS = "src/main/resources/egovframework/egovProps/globals.properties";

	@TempDir
	Path root;

	private String originalGlobals;

	@BeforeEach
	void setUp() throws IOException {
		writeConfig("true");
		originalGlobals = String.join("\n",
				"#-----------------------------------------------------------------------",
				"#  \\uc8fc\\uc758 : \\ud14c\\uc2a4\\ud2b8 \\uc124\\uc815",
				"Globals.DbType = mysql",
				"Globals.mysql.Url=jdbc:log4jdbc:mysql://127.0.0.1:3306/com",
				"Globals.mysql.UserName = com",
				"Globals.mysql.Password = " + PUBLISHED_CIPHER,
				"Globals.oracle.Password = " + PUBLISHED_CIPHER,
				"Globals.postgres.Password=" + PUBLISHED_CIPHER,
				"Globals.goldilocks.Password= " + PUBLISHED_CIPHER,
				"Globals.tibero.Password = plain-text-password",
				"Globals.cubrid.Password = ",
				"SHELL.batchShellFolder\t= ",
				"Globals.CryptoConfigPath = egovframework/egovProps/conf/egov-crypto-config.properties",
				"");
		write(GLOBALS, originalGlobals);
	}

	private void writeConfig(String crypto) throws IOException {
		write(CONFIG, String.join("\n",
				"# egov-crypto \\uc124\\uc815",
				"id = egovCryptoConfig",
				"initial = true",
				"crypto = " + crypto,
				"algorithm = SHA-256",
				"algorithmKey = egovframe",
				"algorithmKeyHash = gdyYs/IZqY86VcWhT8emCYfqY1ahw2vtLG+/FzNqtrQ=",
				"cryptoBlockSize = 1024",
				"cryptoPropertyLocation = classpath:/egovframework/egovProps/globals.properties",
				"plainDigest = false",
				""));
	}

	private void write(String relative, String content) throws IOException {
		Path path = root.resolve(relative);
		Files.createDirectories(path.getParent());
		Files.write(path, content.getBytes(StandardCharsets.ISO_8859_1));
	}

	private String read(String relative) throws IOException {
		return new String(Files.readAllBytes(root.resolve(relative)), StandardCharsets.ISO_8859_1);
	}

	private Properties props(String relative) throws IOException {
		Properties p = new Properties();
		try (java.io.Reader r = Files.newBufferedReader(root.resolve(relative), StandardCharsets.ISO_8859_1)) {
			p.load(r);
		}
		return p;
	}

	/** 출력 언어를 한국어로 고정해 실행한다(OS 언어와 무관하게 같은 결과) */
	private int run(ScriptedIo io, String env, String... args) {
		return runLang("ko", io, env, args);
	}

	private int runLang(String lang, ScriptedIo io, String env, String... args) {
		String[] all = new String[args.length + 2];
		all[0] = "--lang";
		all[1] = lang;
		System.arraycopy(args, 0, all, 2, args.length);
		return EgovCryptoKeyInitializer.run(Options.parse(all), root, io, env);
	}

	private boolean hasBackup() throws IOException {
		Path dir = root.resolve(EgovCryptoKeyInitializer.BACKUP_DIR);
		if (!Files.isDirectory(dir)) {
			return false;
		}
		try (Stream<Path> files = Files.walk(dir)) {
			return files.anyMatch(Files::isRegularFile);
		}
	}

	/** 바뀐 설정을 실행환경이 읽은 그대로 검증한다 - 가드 통과 + DB 비밀번호 복호화 */
	private void assertInitialized(String expectedKey) throws IOException {
		EgovCryptoConfig config = new EgovCryptoConfigReader("file:" + root.resolve(CONFIG), new DefaultResourceLoader())
				.readConfig();
		assertTrue(EgovCryptoKeyGuard.inspect(config).isEmpty(), EgovCryptoKeyGuard.inspect(config).toString());
		if (expectedKey != null) {
			assertEquals(expectedKey, config.getAlgorithmKey());
		}
		EgovEnvCryptoServiceImpl env = new EgovCryptoKeyInitializer.KeyCipher(config.getAlgorithmKey(),
				config.getAlgorithmKeyHash(), config.getAlgorithm(), config.getCryptoBlockSize()).runtimeService();
		Properties globals = props(GLOBALS);
		for (String name : Arrays.asList("Globals.mysql.Password", "Globals.oracle.Password",
				"Globals.postgres.Password", "Globals.goldilocks.Password")) {
			assertNotEquals(PUBLISHED_CIPHER, globals.getProperty(name), name + " 가 다시 암호화돼야 한다");
			assertEquals("com01", env.decrypt(globals.getProperty(name)), name);
		}
	}

	@Test
	void 자동_생성으로_키_해시_DB_비밀번호를_한_번에_바꾼다() throws IOException {
		ScriptedIo io = new ScriptedIo();
		assertEquals(0, run(io, null, "--generate", "-y"));

		assertInitialized(null);
		assertTrue(hasBackup());
		assertTrue(io.output().contains("DB 비밀번호 4건"), io.output());
		assertFalse(io.output().contains(props(CONFIG).getProperty("algorithmKey")), "--print-key 없이는 키를 출력하지 않는다");
	}

	@Test
	void 바꾼_줄_외에는_한_글자도_바꾸지_않는다() throws IOException {
		assertEquals(0, run(new ScriptedIo(), null, "--generate", "-y"));

		List<String> before = Arrays.asList(originalGlobals.split("\n", -1));
		List<String> after = Arrays.asList(read(GLOBALS).split("\n", -1));
		assertEquals(before.size(), after.size());
		List<Integer> changed = new ArrayList<>();
		for (int i = 0; i < before.size(); i++) {
			if (!before.get(i).equals(after.get(i))) {
				changed.add(i);
				String prefix = before.get(i).substring(0, before.get(i).indexOf(PUBLISHED_CIPHER));
				assertTrue(after.get(i).startsWith(prefix), "키 이름·구분자·공백은 그대로: " + after.get(i));
			}
		}
		assertEquals(Arrays.asList(5, 6, 7, 8), changed, "암호화된 비밀번호 4줄만 바뀐다");
		assertFalse(read(GLOBALS).contains("\r"), "줄바꿈(LF)을 보존한다");
		assertTrue(read(CONFIG).startsWith("# egov-crypto \\uc124\\uc815\n"), "\\u 이스케이프 주석을 보존한다");
	}

	@Test
	void 풀리지_않는_값과_빈_값은_그대로_두고_알린다() throws IOException {
		ScriptedIo io = new ScriptedIo();
		assertEquals(0, run(io, null, "--generate", "-y"));

		Properties globals = props(GLOBALS);
		assertEquals("plain-text-password", globals.getProperty("Globals.tibero.Password"));
		assertEquals("", globals.getProperty("Globals.cubrid.Password"));
		assertTrue(io.output().contains("[건너뜀] Globals.tibero.Password"), io.output());
	}

	@Test
	void 두_번째_실행은_아무것도_바꾸지_않는다() throws IOException {
		assertEquals(0, run(new ScriptedIo(), null, "--generate", "-y"));
		String config = read(CONFIG);
		String globals = read(GLOBALS);

		ScriptedIo io = new ScriptedIo();
		assertEquals(0, run(io, null, "--generate", "-y"));
		assertEquals(config, read(CONFIG));
		assertEquals(globals, read(GLOBALS));
		assertTrue(io.output().contains("이미 기본값이 아닌 키"), io.output());
	}

	@Test
	void force_로_운영_키를_교체하면_경고하고_다시_암호화한다() throws IOException {
		assertEquals(0, run(new ScriptedIo(), null, "--generate", "-y"));
		String firstKey = props(CONFIG).getProperty("algorithmKey");

		ScriptedIo io = new ScriptedIo();
		assertEquals(0, run(io, null, "--generate", "-y", "--force"));
		assertNotEquals(firstKey, props(CONFIG).getProperty("algorithmKey"));
		assertInitialized(null);
		assertTrue(io.output().contains("웹에디터 이미지 주소"), io.output());
	}

	@Test
	void 대화형_자동_생성() throws IOException {
		ScriptedIo io = new ScriptedIo("", "y");
		assertEquals(0, run(io, null));
		assertInitialized(null);
	}

	@Test
	void 대화형_직접_입력_규칙_위반과_불일치를_다시_묻는다() throws IOException {
		String key = "MySite-Crypto-2026";
		ScriptedIo io = new ScriptedIo("2",
				"Egov-Key-26", // 11자 - 12자 미만
				key, "MySite-Crypto-2025", // 확인 불일치
				key, key, "y");
		assertEquals(0, run(io, null));
		assertInitialized(key);
		assertTrue(io.output().contains("12자 이상"), io.output());
		assertTrue(io.output().contains("두 번 입력한 키가 다릅니다"), io.output());
	}

	@Test
	void 직접_입력은_12자부터_받는다() throws IOException {
		String key = "Egov-Key-26!"; // 12자, 대문자·소문자·숫자·기호
		ScriptedIo io = new ScriptedIo("2", key, key, "y");
		assertEquals(0, run(io, null));
		assertInitialized(key);
		assertTrue(io.output().contains("새 키: "), "입력은 화면에 보이는 일반 입력으로 받는다");
		assertFalse(io.output().contains("표시되지 않습니다"), io.output());
	}

	@Test
	void 직접_입력을_세_번_실패하면_아무것도_바꾸지_않고_끝낸다() throws IOException {
		ScriptedIo io = new ScriptedIo("2", "egovframe", "short", "lowercaseonlykeyabc");
		assertEquals(1, run(io, null));
		assertUnchanged();
	}

	@Test
	void 확인에_n_으로_답하면_바꾸지_않는다() throws IOException {
		assertEquals(1, run(new ScriptedIo("1", "n"), null));
		assertUnchanged();
	}

	@Test
	void 입력이_없는_환경에서는_기다리지_않고_실패한다() throws IOException {
		ScriptedIo io = new ScriptedIo();
		assertEquals(1, run(io, null));
		assertUnchanged();
		assertTrue(io.output().contains("--generate"), io.output());
	}

	@Test
	void 환경변수와_key_옵션도_같은_규칙으로_검증한다() throws IOException {
		assertEquals(1, run(new ScriptedIo(), "weak", "-y"));
		assertUnchanged();
		assertEquals(1, run(new ScriptedIo(), null, "--key", "egovframe", "-y"));
		assertUnchanged();

		assertEquals(0, run(new ScriptedIo(), "Env-Crypto-Key-2026", "-y"));
		assertInitialized("Env-Crypto-Key-2026");
	}

	@Test
	void dry_run_은_파일을_바꾸지_않는다() throws IOException {
		ScriptedIo io = new ScriptedIo();
		assertEquals(0, run(io, null, "--generate", "--dry-run"));
		assertUnchanged();
		assertTrue(io.output().contains("DB 비밀번호 4건"), io.output());
	}

	@Test
	void crypto_false_면_DB_비밀번호는_건드리지_않는다() throws IOException {
		writeConfig("false");
		assertEquals(0, run(new ScriptedIo(), null, "--generate", "-y"));
		assertEquals(originalGlobals, read(GLOBALS));
		assertFalse(EgovCryptoKeyPolicy.isDefaultKey(props(CONFIG).getProperty("algorithmKey")));
	}

	@Test
	void 현재_키와_해시가_맞지_않으면_시작하지_않는다() throws IOException {
		write(CONFIG, read(CONFIG).replace("algorithmKeyHash = gdyYs", "algorithmKeyHash = XXXXX"));
		String config = read(CONFIG);
		ScriptedIo io = new ScriptedIo();
		assertEquals(1, run(io, null, "--generate", "-y"));
		assertEquals(config, read(CONFIG));
		assertEquals(originalGlobals, read(GLOBALS));
		assertTrue(io.output().contains("맞지 않아"), io.output());
	}

	@Test
	void 설정_파일이_없으면_경로를_알려_준다() {
		ScriptedIo io = new ScriptedIo();
		assertEquals(1, run(io, null, "--generate", "-y", "--config", "no/such/file.properties"));
		assertTrue(io.output().contains("설정 파일이 없습니다"), io.output());
	}

	@Test
	void 옵션_오류() {
		assertThrowsIllegal("--unknown");
		assertThrowsIllegal("--key");
		assertThrowsIllegal("--generate", "--key", "Abcdefgh-12345678");
		assertThrowsIllegal("--lang", "fr");
	}

	@Test
	void 영어로도_출력한다() throws IOException {
		ScriptedIo io = new ScriptedIo("2", "short", "Egov-Key-26!", "Egov-Key-26!", "y");
		assertEquals(0, runLang("en", io, null));
		assertInitialized("Egov-Key-26!");
		String out = io.output();
		assertTrue(out.contains("[eGovFrame common components] Crypto key initialization"), out);
		assertTrue(out.contains("How do you want to set the key?"), out);
		assertTrue(out.contains("The key must be at least 12 characters long (currently 5)."), out);
		assertTrue(out.contains("re-encrypted 4 DB password(s)"), out);
		assertFalse(out.matches("(?s).*[\uac00-\ud7a3].*"), "영어 출력에 한글이 섞이지 않는다");
	}

	@Test
	void 오류도_선택한_언어로_출력한다() {
		ScriptedIo ko = new ScriptedIo();
		assertEquals(1, run(ko, null, "--generate", "-y", "--config", "no/such/file.properties"));
		assertTrue(ko.output().startsWith("[오류] 설정 파일이 없습니다"), ko.output());

		ScriptedIo en = new ScriptedIo();
		assertEquals(1, runLang("en", en, null, "--generate", "-y", "--config", "no/such/file.properties"));
		assertTrue(en.output().startsWith("[ERROR] The configuration file does not exist"), en.output());
	}

	private static void assertThrowsIllegal(String... args) {
		try {
			Options.parse(args);
		} catch (IllegalArgumentException expected) {
			return;
		}
		throw new AssertionError("IllegalArgumentException 이 나야 한다: " + Arrays.toString(args));
	}

	private void assertUnchanged() throws IOException {
		assertTrue(read(CONFIG).contains("algorithmKey = egovframe\n"));
		assertEquals(originalGlobals, read(GLOBALS));
		assertFalse(hasBackup());
	}

	/** 미리 정한 답을 차례로 돌려주는 입출력. 답이 떨어지면 입력 끝(EOF) */
	static final class ScriptedIo implements EgovCryptoKeyInitializer.Io {

		private final Deque<String> answers;
		private final StringBuilder out = new StringBuilder();

		ScriptedIo(String... answers) {
			this.answers = new ArrayDeque<>(Arrays.asList(answers));
		}

		@Override
		public void println(String line) {
			out.append(line).append('\n');
		}

		@Override
		public String readLine(String prompt) {
			out.append(prompt);
			return answers.poll();
		}

		String output() {
			return out.toString();
		}
	}
}
