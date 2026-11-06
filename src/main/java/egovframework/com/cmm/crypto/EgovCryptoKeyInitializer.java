package egovframework.com.cmm.crypto;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.egovframe.rte.fdl.crypto.impl.EgovARIACryptoServiceImpl;
import org.egovframe.rte.fdl.crypto.impl.EgovEnvCryptoServiceImpl;

/**
 * 암호화 키 초기화 도구
 *
 * <p>egov-crypto-config.properties 의 배포 기본 키를 새 키로 바꾼다. 키만 바꾸면 그 키로 암호화해 둔
 * globals.properties 의 DB 비밀번호(Globals.*.Password)를 풀 수 없어 DB 에 접속하지 못하므로, 다음을 한 번에 처리한다.</p>
 * <ol>
 *   <li>새 키 결정 - 대화형으로 묻거나(자동 생성/직접 입력), --generate·--key·환경변수 EGOV_CRYPTO_KEY</li>
 *   <li>새 키의 해시(algorithmKeyHash) 계산 - 사람이 입력하지 않는다</li>
 *   <li>globals.properties 의 암호문을 옛 키로 풀어 새 키로 다시 암호화</li>
 *   <li>원본 백업(프로젝트 루트 crypto-key-backup/) 후 해당 줄만 바꿔 쓰기, 다시 읽어 검증, 실패하면 원복</li>
 * </ol>
 *
 * <p>실행: 프로젝트 루트의 init-crypto-key.sh / init-crypto-key.bat (Maven 으로 이 클래스를 실행한다).
 * 스프링 컨텍스트와 DB 없이 동작한다. 출력 문구는 message-cryptokey_{ko,en}.properties(initCryptoKey.*)에서 꺼내며,
 * --lang 을 주지 않으면 OS 언어가 한국어일 때 ko, 그 밖에는 en 이다.</p>
 *
 * @author 개발팀
 * @since 2026.10.02
 * @version 1.0
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2026.10.02  개발팀         최초 생성
 *
 *      </pre>
 */
public final class EgovCryptoKeyInitializer {

	/** 기본 설정 파일 경로(프로젝트 루트 기준) */
	static final String DEFAULT_CONFIG_PATH = "src/main/resources/egovframework/egovProps/conf/egov-crypto-config.properties";

	/** classpath: 위치를 풀 때 기준 디렉터리(프로젝트 루트 기준) */
	static final String RESOURCES_DIR = "src/main/resources";

	/** 백업 디렉터리(프로젝트 루트 기준, .gitignore 대상) */
	static final String BACKUP_DIR = "crypto-key-backup";

	/** 키를 넘기는 환경변수 - 명령행 기록에 남지 않는다 */
	static final String KEY_ENV = "EGOV_CRYPTO_KEY";

	/** 재암호화 대상 키 */
	private static final Pattern PASSWORD_KEY = Pattern.compile("Globals\\.[^.\\s]+\\.Password");

	/** 직접 입력 재시도 횟수 */
	private static final int MAX_ATTEMPTS = 3;

	static final int EXIT_OK = 0;
	static final int EXIT_ERROR = 1;
	static final int EXIT_USAGE = 2;

	private EgovCryptoKeyInitializer() {
	}

	/**
	 * 명령행 진입점.
	 *
	 * @param args 옵션(--help 참조)
	 */
	public static void main(String[] args) {
		int code;
		try {
			Options options = Options.parse(args);
			if (options.help) {
				usage(System.out, options.messages());
				code = EXIT_OK;
			} else {
				code = run(options, Paths.get("").toAbsolutePath(), ConsoleIo.create(), System.getenv(KEY_ENV));
			}
		} catch (UsageException e) {
			EgovCryptoKeyMessages messages = EgovCryptoKeyMessages.of(null);
			System.err.println(messages.get("initCryptoKey.error", messages.get(e.code, e.args)));
			usage(System.err, messages);
			code = EXIT_USAGE;
		}
		System.exit(code);
	}

	/**
	 * 도구 본체.
	 *
	 * @param options 옵션
	 * @param root 프로젝트 루트
	 * @param io 입출력
	 * @param envKey 환경변수로 받은 키(없으면 null)
	 * @return 종료 코드
	 */
	static int run(Options options, Path root, Io io, String envKey) {
		EgovCryptoKeyMessages messages = options.messages();
		try {
			return new Session(options, root, io, envKey, messages).execute();
		} catch (ToolException e) {
			io.println(messages.get("initCryptoKey.error", e.describe(messages)));
			return EXIT_ERROR;
		} catch (IOException e) {
			io.println(messages.get("initCryptoKey.error", messages.get("initCryptoKey.error.io", e.getMessage())));
			return EXIT_ERROR;
		}
	}

	static void usage(PrintStream out, EgovCryptoKeyMessages messages) {
		out.println(messages.get("initCryptoKey.usage.1"));
		out.println(messages.get("initCryptoKey.usage.2"));
		out.println(messages.get("initCryptoKey.usage.3"));
		out.println(messages.get("initCryptoKey.usage.4", KEY_ENV));
		out.println(messages.get("initCryptoKey.usage.5", DEFAULT_CONFIG_PATH));
		for (int i = 6; i <= 11; i++) {
			out.println(messages.get("initCryptoKey.usage." + i));
		}
	}

	/** 한 번의 실행 */
	private static final class Session {

		private final Options options;
		private final Path root;
		private final Io io;
		private final String envKey;
		private final EgovCryptoKeyMessages m;

		Session(Options options, Path root, Io io, String envKey, EgovCryptoKeyMessages messages) {
			this.options = options;
			this.root = root;
			this.io = io;
			this.envKey = envKey;
			this.m = messages;
		}

		int execute() throws IOException, ToolException {
			Path configPath = root.resolve(options.configPath).normalize();
			if (!Files.isRegularFile(configPath)) {
				throw new ToolException("initCryptoKey.error.noConfig", configPath);
			}
			PropertiesDocument config = PropertiesDocument.load(configPath);
			String oldKey = config.require("algorithmKey");
			String oldHash = config.require("algorithmKeyHash");
			String algorithm = config.get("algorithm");
			boolean crypto = !"false".equalsIgnoreCase(trim(config.get("crypto")));
			int blockSize = parseBlockSize(config.get("cryptoBlockSize"));

			io.println(m.get("initCryptoKey.title"));
			io.println(m.get("initCryptoKey.target", root.relativize(configPath)));
			boolean defaultState = EgovCryptoKeyPolicy.isDefaultKey(oldKey) || EgovCryptoKeyPolicy.isDefaultKeyHash(oldHash);
			if (!defaultState && !options.force) {
				io.println(m.get("initCryptoKey.state.initialized"));
				io.println(m.get("initCryptoKey.state.initialized.hint"));
				return EXIT_OK;
			}
			io.println(m.get(defaultState ? "initCryptoKey.state.default" : "initCryptoKey.state.force"));
			if (!defaultState) {
				io.println(m.get("initCryptoKey.warn.force"));
			}
			if (!EgovCryptoKeyPolicy.matches(oldKey, oldHash, algorithm)) {
				throw new ToolException("initCryptoKey.error.mismatch");
			}

			// 재암호화 대상 확인(키를 묻기 전에 - 풀 수 없는 값이 있으면 미리 알린다)
			Path globalsPath = resolveLocation(trim(config.get("cryptoPropertyLocation")));
			PropertiesDocument globals = null;
			Map<String, String> plainValues = new LinkedHashMap<>();
			List<String> skipped = new ArrayList<>();
			if (crypto) {
				if (globalsPath == null || !Files.isRegularFile(globalsPath)) {
					throw new ToolException("initCryptoKey.error.noGlobals", config.get("cryptoPropertyLocation"));
				}
				globals = PropertiesDocument.load(globalsPath);
				KeyCipher oldCipher = new KeyCipher(oldKey, oldHash, algorithm, blockSize);
				for (String name : globals.names()) {
					String value = trim(globals.get(name));
					if (!PASSWORD_KEY.matcher(name).matches() || value.isEmpty()) {
						continue;
					}
					String plain = oldCipher.tryDecrypt(value);
					if (plain == null) {
						skipped.add(name);
					} else {
						plainValues.put(name, plain);
					}
				}
			}

			Choice choice = chooseKey();
			if (choice == null) {
				return EXIT_ERROR;
			}
			String newKey = choice.key;
			String newHash = EgovCryptoKeyPolicy.hash(newKey, algorithm);
			KeyCipher newCipher = new KeyCipher(newKey, newHash, algorithm, blockSize);
			Map<String, String> newValues = new LinkedHashMap<>();
			for (Map.Entry<String, String> entry : plainValues.entrySet()) {
				newValues.put(entry.getKey(), newCipher.encrypt(entry.getValue()));
			}

			io.println("");
			io.println(m.get("initCryptoKey.plan.title"));
			io.println(m.get("initCryptoKey.plan.config", root.relativize(configPath)));
			if (!crypto) {
				io.println(m.get("initCryptoKey.plan.plain"));
			} else {
				io.println(m.get("initCryptoKey.plan.globals", root.relativize(globalsPath), newValues.size()));
				for (String name : skipped) {
					io.println(m.get("initCryptoKey.plan.skipped", name));
				}
			}
			if (options.dryRun) {
				io.println(m.get("initCryptoKey.dryRun"));
				return EXIT_OK;
			}
			if (!options.yes && !confirm(m.get("initCryptoKey.confirm") + " ")) {
				io.println(m.get("initCryptoKey.cancelled"));
				return EXIT_ERROR;
			}

			Path backupDir = root.resolve(BACKUP_DIR)
					.resolve(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")));
			Files.createDirectories(backupDir);
			Path configBackup = backupDir.resolve(configPath.getFileName());
			Files.copy(configPath, configBackup, StandardCopyOption.REPLACE_EXISTING);
			Path globalsBackup = null;
			if (globals != null && !newValues.isEmpty()) {
				globalsBackup = backupDir.resolve(globalsPath.getFileName());
				Files.copy(globalsPath, globalsBackup, StandardCopyOption.REPLACE_EXISTING);
			}

			try {
				config.set("algorithmKey", newKey);
				config.set("algorithmKeyHash", newHash);
				config.save();
				if (globalsBackup != null) {
					for (Map.Entry<String, String> entry : newValues.entrySet()) {
						globals.set(entry.getKey(), entry.getValue());
					}
					globals.save();
				}
				verify(configPath, globalsBackup != null ? globalsPath : null, plainValues, blockSize);
			} catch (IOException | ToolException | RuntimeException e) {
				Files.copy(configBackup, configPath, StandardCopyOption.REPLACE_EXISTING);
				if (globalsBackup != null) {
					Files.copy(globalsBackup, globalsPath, StandardCopyOption.REPLACE_EXISTING);
				}
				String cause = (e instanceof ToolException) ? ((ToolException) e).describe(m) : e.getMessage();
				throw new ToolException("initCryptoKey.error.rollback", cause);
			}

			io.println("");
			io.println(m.get("initCryptoKey.done"));
			io.println(m.get("initCryptoKey.done.written", newValues.size()));
			io.println(m.get("initCryptoKey.done.backup", root.relativize(backupDir)));
			if (choice.generated && options.printKey) {
				io.println(m.get("initCryptoKey.done.key", newKey));
			}
			io.println(m.get("initCryptoKey.done.noCommit"));
			io.println(m.get("initCryptoKey.done.next"));
			return EXIT_OK;
		}

		/** 명령행·환경변수로 받은 키를 검증한다 */
		private Choice choose(String key, String source) throws ToolException {
			EgovCryptoKeyPolicy.Violation violation = EgovCryptoKeyPolicy.validateUserKey(key);
			if (violation != null) {
				throw new ToolException("initCryptoKey.error.badKey", source, m.get(violation));
			}
			return new Choice(key, false);
		}

		/** 새 키를 정한다. 직접 입력을 3번 실패하면 null */
		private Choice chooseKey() throws ToolException {
			if (options.key != null) {
				return choose(options.key, "--key");
			}
			if (envKey != null && !envKey.isEmpty()) {
				io.println(m.get("initCryptoKey.keyFromEnv", KEY_ENV));
				return choose(envKey, m.get("initCryptoKey.source.env", KEY_ENV));
			}
			if (options.generate) {
				return new Choice(EgovCryptoKeyPolicy.generateKey(), true);
			}
			io.println("");
			io.println(m.get("initCryptoKey.choose.title"));
			io.println(m.get("initCryptoKey.choose.1"));
			io.println(m.get("initCryptoKey.choose.2"));
			String answer = io.readLine(m.get("initCryptoKey.choose.prompt") + " ");
			if (answer == null) {
				throw nonInteractive();
			}
			answer = answer.trim();
			if (answer.isEmpty() || "1".equals(answer)) {
				return new Choice(EgovCryptoKeyPolicy.generateKey(), true);
			}
			if (!"2".equals(answer)) {
				throw new ToolException("initCryptoKey.error.choice", answer);
			}
			io.println(m.get("initCryptoKey.rules", EgovCryptoKeyPolicy.MIN_KEY_LENGTH,
					EgovCryptoKeyPolicy.minCharacterClasses()));
			for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
				String first = io.readLine(m.get("initCryptoKey.prompt.key") + " ");
				if (first == null) {
					throw nonInteractive();
				}
				EgovCryptoKeyPolicy.Violation violation = EgovCryptoKeyPolicy.validateUserKey(first);
				if (violation != null) {
					io.println("  " + m.get(violation));
					continue;
				}
				String second = io.readLine(m.get("initCryptoKey.prompt.confirm") + " ");
				if (second == null) {
					throw nonInteractive();
				}
				if (!first.equals(second)) {
					io.println(m.get("initCryptoKey.mismatchInput"));
					continue;
				}
				return new Choice(first, false);
			}
			io.println(m.get("initCryptoKey.tooManyAttempts", MAX_ATTEMPTS));
			return null;
		}

		private boolean confirm(String prompt) throws ToolException {
			String answer = io.readLine(prompt);
			if (answer == null) {
				throw nonInteractive();
			}
			answer = answer.trim().toLowerCase(Locale.ROOT);
			return "y".equals(answer) || "yes".equals(answer);
		}

		private ToolException nonInteractive() {
			return new ToolException("initCryptoKey.error.nonInteractive", KEY_ENV);
		}

		/** classpath:/x → src/main/resources/x, file:/x → /x */
		private Path resolveLocation(String location) {
			if (location.isEmpty()) {
				return null;
			}
			if (location.startsWith("classpath:")) {
				String path = location.substring("classpath:".length());
				while (path.startsWith("/")) {
					path = path.substring(1);
				}
				return root.resolve(RESOURCES_DIR).resolve(path).normalize();
			}
			if (location.startsWith("file:")) {
				return Paths.get(location.substring("file:".length())).toAbsolutePath().normalize();
			}
			return root.resolve(location).normalize();
		}

		/** 새로 쓴 파일을 다시 읽어 실행환경과 같은 경로로 검증한다 */
		private void verify(Path configPath, Path globalsPath, Map<String, String> plainValues, int blockSize)
				throws IOException, ToolException {
			PropertiesDocument config = PropertiesDocument.load(configPath);
			String key = config.require("algorithmKey");
			String hash = config.require("algorithmKeyHash");
			String algorithm = config.get("algorithm");
			if (!EgovCryptoKeyPolicy.matches(key, hash, algorithm) || EgovCryptoKeyPolicy.isDefaultKey(key)) {
				throw new ToolException("initCryptoKey.error.verifyKey");
			}
			if (globalsPath == null) {
				return;
			}
			EgovEnvCryptoServiceImpl env = new KeyCipher(key, hash, algorithm, blockSize).runtimeService();
			PropertiesDocument globals = PropertiesDocument.load(globalsPath);
			for (Map.Entry<String, String> entry : plainValues.entrySet()) {
				String decrypted = env.decrypt(trim(globals.get(entry.getKey())));
				if (!entry.getValue().equals(decrypted)) {
					throw new ToolException("initCryptoKey.error.verifyValue", entry.getKey());
				}
			}
		}
	}

	private static String trim(String value) {
		return value == null ? "" : value.trim();
	}

	private static int parseBlockSize(String value) {
		try {
			return Integer.parseInt(trim(value));
		} catch (NumberFormatException e) {
			return 1024;
		}
	}

	/** 정한 키 */
	private static final class Choice {
		final String key;
		final boolean generated;

		Choice(String key, boolean generated) {
			this.key = key;
			this.generated = generated;
		}
	}

	/** 키 하나로 실행환경과 같은 방식의 암·복호화를 한다 */
	static final class KeyCipher {

		private final String key;
		private final String hash;
		private final String algorithm;
		private final EgovARIACryptoServiceImpl aria;

		KeyCipher(String key, String hash, String algorithm, int blockSize) {
			this.key = key;
			this.hash = hash;
			this.algorithm = algorithm;
			this.aria = new EgovARIACryptoServiceImpl();
			this.aria.setPasswordEncoder(EgovCryptoKeyPolicy.encoder(algorithm, hash));
			this.aria.setBlockSize(blockSize);
		}

		/** 실행환경 EgovEnvCryptoServiceImpl.encrypt 와 같은 형식(ARIA → URL-safe Base64 패딩 없음 → URL 인코딩) */
		String encrypt(String plain) {
			byte[] encrypted = aria.encrypt(plain.getBytes(StandardCharsets.UTF_8), key);
			return URLEncoder.encode(Base64.getUrlEncoder().withoutPadding().encodeToString(encrypted), StandardCharsets.UTF_8);
		}

		/**
		 * 이 키로 암호화된 값이면 평문을, 아니면 null 을 돌려준다.
		 * 실행환경 decrypt 는 실패해도 입력을 그대로 돌려주므로(예외 없음) 판정용으로 직접 푼다.
		 */
		String tryDecrypt(String value) {
			try {
				String encoded = URLDecoder.decode(value, StandardCharsets.UTF_8).replace('-', '+').replace('_', '/');
				byte[] raw = Base64.getDecoder().decode(encoded);
				if (raw.length == 0 || raw.length % 16 != 0) {
					return null;
				}
				byte[] plain = aria.decrypt(raw, key);
				String text = StandardCharsets.UTF_8.newDecoder()
						.onMalformedInput(CodingErrorAction.REPORT)
						.onUnmappableCharacter(CodingErrorAction.REPORT)
						.decode(ByteBuffer.wrap(plain)).toString();
				for (int i = 0; i < text.length(); i++) {
					if (Character.isISOControl(text.charAt(i))) {
						return null;
					}
				}
				// 실행환경 복호화 경로로도 같은 값이 나와야 한다(표준 Base64·패딩으로 쓰인 예전 형식 포함)
				return text.equals(runtimeService().decrypt(value)) ? text : null;
			} catch (IllegalArgumentException | CharacterCodingException | ArrayIndexOutOfBoundsException e) {
				return null;
			}
		}

		/** 실행환경 복호화 경로(검증용) */
		EgovEnvCryptoServiceImpl runtimeService() {
			EgovEnvCryptoServiceImpl env = new EgovEnvCryptoServiceImpl();
			env.setPasswordEncoder(EgovCryptoKeyPolicy.encoder(algorithm, hash));
			env.setCryptoService(aria);
			env.setCrypto(true);
			env.setCryptoAlgorithm(algorithm);
			env.setCryptoAlgorithmKey(key);
			env.setCryptoAlgorithmKeyHash(hash);
			return env;
		}
	}

	/**
	 * .properties 파일을 줄 단위로 다룬다. 값을 바꿀 때 그 줄의 값 부분만 바꾸고 주석·순서·이스케이프·줄바꿈은 그대로 둔다.
	 * 파일은 ISO-8859-1 로 읽고 써서 바이트를 보존한다(.properties 표준 인코딩, 한글은 \\u 이스케이프).
	 */
	static final class PropertiesDocument {

		private final Path path;
		private final List<String> lines;
		private final List<String> separators;
		private final Properties values;

		private PropertiesDocument(Path path, List<String> lines, List<String> separators, Properties values) {
			this.path = path;
			this.lines = lines;
			this.separators = separators;
			this.values = values;
		}

		static PropertiesDocument load(Path path) throws IOException {
			String text = new String(Files.readAllBytes(path), StandardCharsets.ISO_8859_1);
			List<String> lines = new ArrayList<>();
			List<String> separators = new ArrayList<>();
			Matcher m = Pattern.compile("\r\n|\n|\r").matcher(text);
			int start = 0;
			while (m.find()) {
				lines.add(text.substring(start, m.start()));
				separators.add(m.group());
				start = m.end();
			}
			if (start < text.length()) {
				lines.add(text.substring(start));
				separators.add("");
			}
			Properties values = new Properties();
			try (java.io.StringReader reader = new java.io.StringReader(text)) {
				values.load(reader);
			}
			return new PropertiesDocument(path, lines, separators, values);
		}

		String get(String name) {
			return values.getProperty(name);
		}

		String require(String name) throws ToolException {
			String value = get(name);
			if (value == null || value.trim().isEmpty()) {
				throw new ToolException("initCryptoKey.error.missingItem", path.getFileName(), name);
			}
			return value.trim();
		}

		List<String> names() {
			List<String> names = new ArrayList<>();
			for (String line : lines) {
				String name = nameOf(line);
				if (name != null && !names.contains(name)) {
					names.add(name);
				}
			}
			return names;
		}

		/**
		 * 값을 바꾼다. 같은 키가 여러 번 있으면 실제로 쓰이는 마지막 줄을 바꾼다.
		 * 값은 이스케이프가 필요 없는 문자(출력 가능한 ASCII, 역슬래시 제외)여야 한다.
		 */
		void set(String name, String value) throws ToolException {
			for (int i = 0; i < value.length(); i++) {
				char c = value.charAt(i);
				if (c < 0x21 || c > 0x7E || c == '\\') {
					throw new ToolException("initCryptoKey.error.badChars", name);
				}
			}
			for (int i = lines.size() - 1; i >= 0; i--) {
				String line = lines.get(i);
				if (!name.equals(nameOf(line))) {
					continue;
				}
				if (line.endsWith("\\")) {
					throw new ToolException("initCryptoKey.error.multiline", name);
				}
				Matcher m = Pattern.compile("^(\\s*" + Pattern.quote(name) + "(?:\\s*[=:]\\s*|\\s+))").matcher(line);
				if (!m.find()) {
					throw new ToolException("initCryptoKey.error.lineFormat", name);
				}
				lines.set(i, m.group(1) + value);
				values.setProperty(name, value);
				return;
			}
			throw new ToolException("initCryptoKey.error.missingItem", path.getFileName(), name);
		}

		/** 같은 디렉터리의 임시 파일에 쓴 뒤 바꿔 끼운다 */
		void save() throws IOException {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < lines.size(); i++) {
				sb.append(lines.get(i)).append(separators.get(i));
			}
			Path temp = Files.createTempFile(path.getParent(), path.getFileName().toString(), ".tmp");
			try {
				Files.write(temp, sb.toString().getBytes(StandardCharsets.ISO_8859_1));
				try {
					Files.move(temp, path, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
				} catch (AtomicMoveNotSupportedException e) {
					Files.move(temp, path, StandardCopyOption.REPLACE_EXISTING);
				}
			} finally {
				Files.deleteIfExists(temp);
			}
		}

		/** 주석·빈 줄·이어지는 줄이 아니면 키 이름(이스케이프 없는 단순 키만) */
		private static String nameOf(String line) {
			String trimmed = line.trim();
			if (trimmed.isEmpty() || trimmed.startsWith("#") || trimmed.startsWith("!")) {
				return null;
			}
			Matcher m = Pattern.compile("^([^\\s=:\\\\]+)").matcher(trimmed);
			return m.find() ? m.group(1) : null;
		}
	}

	/** 명령행 옵션 */
	static final class Options {
		String configPath = DEFAULT_CONFIG_PATH;
		String key;
		boolean generate;
		boolean dryRun;
		boolean force;
		boolean printKey;
		boolean yes;
		boolean help;
		/** 출력 언어(ko/en) - 없으면 OS 언어 */
		Locale lang;

		EgovCryptoKeyMessages messages() {
			return EgovCryptoKeyMessages.of(lang);
		}

		static Options parse(String[] args) {
			Options o = new Options();
			for (int i = 0; i < args.length; i++) {
				String arg = args[i];
				switch (arg) {
					case "--generate": o.generate = true; break;
					case "--dry-run": o.dryRun = true; break;
					case "--force": o.force = true; break;
					case "--print-key": o.printKey = true; break;
					case "-y": case "--yes": o.yes = true; break;
					case "-h": case "--help": o.help = true; break;
					case "--key":
						o.key = value(args, ++i, arg);
						break;
					case "--config":
						o.configPath = value(args, ++i, arg);
						break;
					case "--lang":
						String lang = value(args, ++i, arg);
						if (!"ko".equals(lang) && !"en".equals(lang)) {
							throw new UsageException("initCryptoKey.error.lang", lang);
						}
						o.lang = Locale.forLanguageTag(lang);
						break;
					default:
						if (arg.isBlank()) {
							break;
						}
						throw new UsageException("initCryptoKey.error.unknownOption", arg);
				}
			}
			if (o.generate && o.key != null) {
				throw new UsageException("initCryptoKey.error.generateAndKey");
			}
			return o;
		}

		private static String value(String[] args, int index, String option) {
			if (index >= args.length || args[index].startsWith("--")) {
				throw new UsageException("initCryptoKey.error.optionValue", option);
			}
			return args[index];
		}
	}

	/** 사용자 입출력 - 테스트에서 바꿔 끼운다 */
	interface Io {
		void println(String line);

		/** 한 줄을 읽는다. 입력이 끝났으면(EOF) null */
		String readLine(String prompt);
	}

	/** 콘솔이 있으면 콘솔, 없으면(Git Bash mintty·IDE 등) 표준 입력으로 읽는다. 입력한 키는 화면에 그대로 보인다 */
	static final class ConsoleIo implements Io {

		private final Console console;
		private final BufferedReader stdin;
		private final PrintStream out;

		private ConsoleIo(Console console, BufferedReader stdin, PrintStream out) {
			this.console = console;
			this.stdin = stdin;
			this.out = out;
		}

		static ConsoleIo create() {
			return new ConsoleIo(System.console(),
					new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8)), System.out);
		}

		@Override
		public void println(String line) {
			out.println(line);
			out.flush();
		}

		@Override
		public String readLine(String prompt) {
			if (console != null) {
				return console.readLine("%s", prompt);
			}
			out.print(prompt);
			out.flush();
			try {
				return stdin.readLine();
			} catch (IOException e) {
				return null;
			}
		}
	}

	/** 도구가 사용자에게 알릴 실패 - 메시지 키와 인자를 가지고, 출력할 때 언어에 맞는 문장으로 바꾼다 */
	static final class ToolException extends Exception {
		private static final long serialVersionUID = 1L;

		final String code;
		final transient Object[] args;

		ToolException(String code, Object... args) {
			super(code);
			this.code = Objects.requireNonNull(code);
			this.args = args;
		}

		String describe(EgovCryptoKeyMessages messages) {
			return messages.get(code, args);
		}
	}

	/** 명령행 옵션 오류 - 메시지 키와 인자 */
	static final class UsageException extends IllegalArgumentException {
		private static final long serialVersionUID = 1L;

		final String code;
		final transient Object[] args;

		UsageException(String code, Object... args) {
			super(code);
			this.code = code;
			this.args = args;
		}
	}
}
