package egovframework.com.cmm.util;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * 일반 사용자에게 열린 삭제·수정 경로에 작성자 또는 같은 부서 검사가 빠지지 않았는지 소스를 읽어 확인하는 가드 테스트.
 *
 * <p>호출이 있는지만 본다. 올바른 소유자 컬럼과 비교하는지는 실제 실행으로 확인한다.
 * 관리자 전용(@RequireAdmin) 경로는 관리자끼리 신뢰해 소유권 검사를 두지 않고, 담당자가 있는 경로만 {@link #ADMIN_DELEGATED} 로 확인한다.
 * 일반 경로의 예외는 {@link #EXCLUDED} 에 사유와 함께 둔다.</p>
 */
class EgovOwnershipGuardTest {

	private static final Path SRC = Paths.get("src/main/java/egovframework/com");

	private static final Pattern METHOD = Pattern.compile(
			"((?:[ \\t]*@[^\\n]*\\n)+)[ \\t]*public\\s+[\\w<>\\[\\], ?]+\\s+(\\w+)\\s*\\(");
	private static final Pattern MAPPING = Pattern.compile(
			"@(?:Request|Post|Get)Mapping\\s*\\(\\s*(?:value\\s*=\\s*)?\\{?\\s*\"([^\"]+)\"");
	private static final Pattern DELETE_NAME = Pattern.compile("delete|remove|Delete|Remove|Del\\b|del\\b|Dlt|dlt");
	private static final Pattern DELETE_CMD = Pattern.compile("\"del\"|\"delete\"|equals\\(\"del");
	private static final Pattern OWNER_CHECK = Pattern.compile(
			"EgovAuthorizationHelper\\.(assertOwner|assertOwnerById|assertSameDept)\\s*\\(|EgovXssChecker\\.checkerUserXss\\s*\\(|checkCommentOwner\\s*\\(");
	/** 조회수를 올리는 조회 — 소유권 검사보다 먼저 부르면 거부된 요청이 DB 를 바꾼다 */
	private static final Pattern COUNTING_READ = Pattern.compile(
			"egovArticleService\\.selectArticleDetail\\s*\\(|egovFaqService\\.selectFaqDetail\\s*\\(");

	/** 작성자·같은 부서 검사를 걸지 않은 삭제 경로 — 클래스#메서드 → 사유 */
	private static final Map<String, String> EXCLUDED = Map.ofEntries(
		entry("EgovAddressBookController#deleteUser", "오탐 — DB 삭제 없음"),
		entry("EgovBBSSatisfactionController#deleteAnonymousSatisfaction", "익명 등록 — 판단 필요"),
		entry("EgovBBSSatisfactionController#deleteSatisfaction", "자체 작성자 검사(인라인)"),
		entry("EgovCommuManageController#deleteCmmntyUserBySelf", "단건 조회 없음"),
		entry("EgovCommuManageController#deleteCommuUser", "단건 조회 없음"),
		entry("EgovCommuManageController#deleteCommuUserAdmin", "단건 조회 없음"),
		entry("EgovFileMngController#deleteFileInf", "세션 결합 식별자"),
		entry("EgovIndvdlSchdulManageController#egovIndvdlSchdulManageDelete", "작성자 또는 관리자 — 관리자도 개인일정을 관리"),
		entry("EgovRssTagManageController#EgovRssTagManageList", "목록 안 삭제 분기 — assertAdmin 뒤 관리자끼리 신뢰, 대상 존재만 확인")
	);

	/** 일반 경로의 첨부 수정 화면 — 화면·저장 메서드 모두 작성자 전용 검사가 있어야 한다 */
	private static final Set<String> GUARDED_UPDATES = Set.of(
		"EgovArticleController#updateArticleView",
		"EgovArticleController#updateBoardArticle",
		"EgovArticleController#updateGuestArticle",
		"EgovWikMnthngReprtController#modifyWikMnthngReprt",
		"EgovWikMnthngReprtController#updateWikMnthngReprt",
		"EgovMemoReprtController#modifyMemoReprt",
		"EgovMemoReprtController#updateMemoReprt",
		"EgovDiaryManageController#diaryManageModify",
		"EgovDiaryManageController#diaryManageModifyActor");

	/**
	 * 관리자 전용(@RequireAdmin) 경로는 관리자끼리 신뢰해 소유권 검사를 두지 않는다.
	 * 다만 결재자·신청자·예약자·변경요청자·기념일 대상자·지정 전문가처럼 기능상 담당자가 있는 경로와 부서 공유 자원·개인 바로가기·개인 지식, 설문 기능은 검사를 유지한다.
	 */
	private static final Set<String> ADMIN_DELEGATED = Set.of(
		"EgovDeptJobController#modifyDeptJobBx",
		"EgovDeptJobController#updateDeptJobBx",
		"EgovDeptJobController#updateDeptJobBxOrdr",
		"EgovDeptJobController#insertDeptJobBx",
		"EgovDeptJobController#deleteDeptJobBx",
		"EgovDeptJobController#modifyDeptJob",
		"EgovDeptJobController#updateDeptJob",
		"EgovDeptJobController#insertDeptJob",
		"EgovDeptJobController#deleteDeptJob",
		"EgovDeptSchdulManageController#egovDeptSchdulManageDelete",
		"EgovDeptSchdulManageController#deptSchdulManageModify",
		"EgovDeptSchdulManageController#deptSchdulManageModifyActor",
		"EgovDeptSchdulManageController#deptSchdulManageRegistActor",
		"EgovBkmkMenuManageController#deleteMenuManageList",
		"EgovProgrmManageController#updateProgrmChangeRequst",
		"EgovProgrmManageController#deleteProgrmChangeRequst",
		"EgovProgrmManageController#deleteProgrmChangRequstProcess",
		"EgovCtsnnManageController#selectCtsnnManage",
		"EgovCtsnnManageController#updtCtsnnManage",
		"EgovCtsnnManageController#deleteCtsnnManage",
		"EgovCtsnnManageController#selectCtsnnConfm",
		"EgovCtsnnManageController#updateCtsnnManageConfm",
		"EgovEventManageController#deleteEventAtdrn",
		"EgovMtgPlaceManageController#selectMtgPlaceResveManageDetail",
		"EgovMtgPlaceManageController#updtMtgPlaceResveManage",
		"EgovMtgPlaceManageController#deleteMtgPlaceResveManage",
		"EgovRwardManageController#selectRwardManage",
		"EgovRwardManageController#updtRwardManage",
		"EgovRwardManageController#deleteRwardManage",
		"EgovRwardManageController#selectRwardConfm",
		"EgovRwardManageController#updtRwardManageConfm",
		"EgovVcatnManageController#selectVcatnManage",
		"EgovVcatnManageController#updtVcatnManage",
		"EgovVcatnManageController#deleteVcatnManage",
		"EgovVcatnManageController#selectVcatnConfm",
		"EgovVcatnManageController#updtVcatnManageConfm",
		// 개인 지식은 등록한 본인, 지식정보 요청·답변은 요청자·답변한 지정 전문가만 수정·삭제한다
		"EgovKnoPersonalController#updateKnoPersonalView",
		"EgovKnoPersonalController#updateKnoPersonal",
		"EgovKnoPersonalController#deleteKnoPersonal",
		"EgovRequestOfferController#EgovRequestOfferDetail",
		"EgovRequestOfferController#EgovRequestOfferModify",
		"EgovRequestOfferController#EgovRequestOfferModifyActor",
		// 쪽지 상세는 수신자·발신자 본인만 본다
		"EgovNoteRecptnController#EgovNoteRecptnDetail",
		"EgovNoteTrnsmitController#EgovNoteTrnsmitDetail",
		// 기념일은 대상자 본인만 수정·삭제한다
		"EgovAnnvrsryManageController#selectAnnvrsryManage",
		"EgovAnnvrsryManageController#updateAnnvrsryManage",
		"EgovAnnvrsryManageController#deleteAnnvrsryManage",
		// 설문 기능(설문관리·항목·문항·템플릿·응답자·응답결과)은 모듈끼리 얽혀 있어 관리자 경로여도 작성자(응답결과는 응답자) 검사를 유지한다
		// 문항·항목은 상위 설문 등록자 기준이며, 남의 설문에 문항·항목을 붙이는 등록도 막는다
		"EgovQustnrItemManageController#egovQustnrItemManageDetail",
		"EgovQustnrItemManageController#egovQustnrItemManageListPopup",
		"EgovQustnrItemManageController#qustnrItemManageRegist",
		"EgovQustnrItemManageController#qustnrItemManageModify",
		"EgovQustnrItemManageController#qustnrItemManageModifyView",
		"EgovQustnrManageController#egovQustnrManageDetail",
		"EgovQustnrManageController#egovQustnrManageListPopup",
		"EgovQustnrManageController#qustnrManageModify",
		"EgovQustnrQestnManageController#egovQustnrQestnManageDetail",
		"EgovQustnrQestnManageController#qustnrQestnManageModifySave",
		"EgovQustnrQestnManageController#qustnrQestnManageModifyView",
		"EgovQustnrQestnManageController#qustnrQestnManageRegistSubmit",
		"EgovQustnrRespondInfoController#egovQustnrRespondInfoDetail",
		"EgovQustnrRespondInfoController#qustnrRespondInfoModify",
		"EgovQustnrRespondManageController#egovQustnrRespondManageDetail",
		"EgovQustnrRespondManageController#qustnrRespondManageModify",
		"EgovQustnrTmplatManageController#egovQustnrTmplatManageDetail",
		"EgovQustnrTmplatManageController#qustnrTmplatManageModify",
		"EgovQustnrTmplatManageController#qustnrTmplatManageModifyActor");

	@Test
	void deleteAndAttachmentUpdateRoutesKeepOwnerOnlyCheck() throws IOException {
		Map<String, Boolean> deletes = new HashMap<>();
		Map<String, Boolean> updates = new HashMap<>();
		Map<String, Boolean> delegated = new HashMap<>();
		List<String> problems = new ArrayList<>();
		try (Stream<Path> files = Files.walk(SRC)) {
			for (Path file : (Iterable<Path>) files.filter(p -> p.toString().endsWith("Controller.java"))::iterator) {
				scan(file, deletes, updates, delegated, problems);
			}
		}

		deletes.forEach((key, guarded) -> {
			if (!guarded && !EXCLUDED.containsKey(key)) {
				problems.add("작성자 전용 검사 없음: " + key);
			}
			if (guarded && EXCLUDED.containsKey(key)) {
				problems.add("검사가 생겼으니 EXCLUDED 에서 제거: " + key);
			}
		});
		EXCLUDED.keySet().stream().filter(key -> !deletes.containsKey(key))
				.forEach(key -> problems.add("EXCLUDED 에 있으나 삭제 경로를 찾지 못함: " + key));
		GUARDED_UPDATES.forEach(key -> {
			if (!Boolean.TRUE.equals(updates.get(key))) {
				problems.add("수정 경로 작성자 전용 검사 없음: " + key);
			}
		});

		ADMIN_DELEGATED.forEach(key -> {
			if (!Boolean.TRUE.equals(delegated.get(key))) {
				problems.add("관리자 경로의 담당자 검사 없음: " + key);
			}
		});

		assertTrue(deletes.size() > 30, "삭제 경로 탐지가 동작하지 않는다: " + deletes.size());
		assertTrue(problems.isEmpty(), String.join("\n", problems));
	}

	private static void scan(Path file, Map<String, Boolean> deletes, Map<String, Boolean> updates,
			Map<String, Boolean> delegated, List<String> problems) throws IOException {
		String src = Files.readString(file, StandardCharsets.UTF_8);
		String cls = file.getFileName().toString().replace(".java", "");
		Matcher m = METHOD.matcher(src);
		while (m.find()) {
			Matcher mapping = MAPPING.matcher(m.group(1));
			if (!mapping.find()) {
				continue;
			}
			String name = m.group(2);
			String body = body(src, m.end());
			String key = cls + "#" + name;
			Matcher owner = OWNER_CHECK.matcher(body);
			boolean guarded = owner.find();
			if (m.group(1).contains("@RequireAdmin")) {
				if (ADMIN_DELEGATED.contains(key)) {
					delegated.merge(key, guarded, Boolean::logicalAnd);
				}
				continue;
			}
			Matcher counting = COUNTING_READ.matcher(body);
			if (guarded && counting.find() && counting.start() < owner.start()) {
				problems.add("소유권 검사 전에 조회수를 올림: " + key);
			}
			// 조회수를 올리는 게시글 조회는 비밀글 검사를 가진 게시글 상세만 쓴다. 다른 컨트롤러(스크랩 등)는 NoCount + 검사
			if (!"EgovArticleController".equals(cls) && body.contains("egovArticleService.selectArticleDetail(")) {
				problems.add("게시글 상세 밖에서 조회수 증가형 조회를 씀: " + key);
			}
			if (DELETE_NAME.matcher(name + " " + mapping.group(1)).find() || DELETE_CMD.matcher(body).find()) {
				deletes.merge(key, guarded, Boolean::logicalAnd);
			}
			if (GUARDED_UPDATES.contains(key)) {
				updates.merge(key, guarded, Boolean::logicalAnd);
			}
		}
	}

	private static String body(String src, int from) {
		int start = src.indexOf('{', from);
		int depth = 0;
		for (int i = start; i < src.length(); i++) {
			char c = src.charAt(i);
			if (c == '{') {
				depth++;
			} else if (c == '}' && --depth == 0) {
				return src.substring(start, i);
			}
		}
		return src.substring(start);
	}
}
