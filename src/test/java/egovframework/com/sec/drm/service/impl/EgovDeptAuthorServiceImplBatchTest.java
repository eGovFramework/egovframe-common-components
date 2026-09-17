package egovframework.com.sec.drm.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import egovframework.com.sec.drm.service.DeptAuthor;

/**
 * {@link EgovDeptAuthorServiceImpl#updateDeptAuthorList}·{@link EgovDeptAuthorServiceImpl#deleteDeptAuthorList}가
 * 항목마다 컨트롤러가 서비스를 여러 번 호출하던 구조를 서비스 진입점 하나로 옮겼는지 확인한다.
 *
 * <p>{@code context-transaction.xml}의 AOP는 이 클래스(*Impl)의 공개 메서드 진입마다 트랜잭션을
 * 건다. 이 테스트는 DAO 호출 순서만 검증한다 — 롤백 보장 자체는 그 AOP 설정이 주는 것이지
 * 이 테스트의 관측 대상이 아니다.</p>
 */
class EgovDeptAuthorServiceImplBatchTest {

	private static DeptAuthorDAO recordingDao(List<String> calls) {
		return new DeptAuthorDAO() {
			@Override
			public void insertDeptAuthor(DeptAuthor deptAuthor) {
				calls.add("insert:" + deptAuthor.getUniqId() + ":" + deptAuthor.getAuthorCode());
			}

			@Override
			public void updateDeptAuthor(DeptAuthor deptAuthor) {
				calls.add("update:" + deptAuthor.getUniqId() + ":" + deptAuthor.getAuthorCode());
			}

			@Override
			public void deleteDeptAuthor(DeptAuthor deptAuthor) {
				calls.add("delete:" + deptAuthor.getUniqId());
			}
		};
	}

	@Test
	void updateDeptAuthorList_insertsWhenRegYnIsN_otherwiseUpdates() throws Exception {
		List<String> calls = new ArrayList<>();
		EgovDeptAuthorServiceImpl service = new EgovDeptAuthorServiceImpl();
		ReflectionTestUtils.setField(service, "deptAuthorDAO", recordingDao(calls));

		service.updateDeptAuthorList(new DeptAuthor(),
				new String[] { "user1", "user2" },
				new String[] { "AUTH_A", "AUTH_B" },
				new String[] { "N", "Y" });

		assertEquals(List.of("insert:user1:AUTH_A", "update:user2:AUTH_B"), calls,
				"regYn=N 은 insert, 그 외는 update — 컨트롤러 원래 분기와 동일해야 한다");
	}

	@Test
	void deleteDeptAuthorList_deletesEachUserInOrder() throws Exception {
		List<String> calls = new ArrayList<>();
		EgovDeptAuthorServiceImpl service = new EgovDeptAuthorServiceImpl();
		ReflectionTestUtils.setField(service, "deptAuthorDAO", recordingDao(calls));

		service.deleteDeptAuthorList(new DeptAuthor(), new String[] { "user1", "user2", "user3" });

		assertEquals(List.of("delete:user1", "delete:user2", "delete:user3"), calls);
	}
}
