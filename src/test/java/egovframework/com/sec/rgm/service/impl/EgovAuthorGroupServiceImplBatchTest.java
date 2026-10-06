package egovframework.com.sec.rgm.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import egovframework.com.sec.rgm.service.AuthorGroup;

/**
 * {@link EgovAuthorGroupServiceImpl#updateAuthorGroupList}·{@link EgovAuthorGroupServiceImpl#deleteAuthorGroupList}가
 * 항목마다 컨트롤러가 서비스를 여러 번 호출하던 구조를 서비스 진입점 하나로 옮겼는지 확인한다.
 *
 * <p>{@code context-transaction.xml}의 AOP는 이 클래스(*Impl)의 공개 메서드 진입마다 트랜잭션을
 * 건다. 이 테스트는 DAO 호출 순서만 검증한다 — 롤백 보장 자체는 그 AOP 설정이 주는 것이지
 * 이 테스트의 관측 대상이 아니다.</p>
 */
class EgovAuthorGroupServiceImplBatchTest {

	private static AuthorGroupDAO recordingDao(List<String> calls) {
		return new AuthorGroupDAO() {
			@Override
			public void insertAuthorGroup(AuthorGroup authorGroup) {
				calls.add("insert:" + authorGroup.getUniqId() + ":" + authorGroup.getAuthorCode() + ":" + authorGroup.getMberTyCode());
			}

			@Override
			public void updateAuthorGroup(AuthorGroup authorGroup) {
				calls.add("update:" + authorGroup.getUniqId() + ":" + authorGroup.getAuthorCode() + ":" + authorGroup.getMberTyCode());
			}

			@Override
			public void deleteAuthorGroup(AuthorGroup authorGroup) {
				calls.add("delete:" + authorGroup.getUniqId());
			}
		};
	}

	@Test
	void updateAuthorGroupList_insertsWhenRegYnIsN_otherwiseUpdates() throws Exception {
		List<String> calls = new ArrayList<>();
		EgovAuthorGroupServiceImpl service = new EgovAuthorGroupServiceImpl();
		ReflectionTestUtils.setField(service, "authorGroupDAO", recordingDao(calls));

		service.updateAuthorGroupList(new AuthorGroup(),
				new String[] { "user1", "user2" },
				new String[] { "AUTH_A", "AUTH_B" },
				new String[] { "M1", "M2" },
				new String[] { "N", "Y" });

		assertEquals(List.of("insert:user1:AUTH_A:M1", "update:user2:AUTH_B:M2"), calls,
				"regYn=N 은 insert, 그 외는 update — 컨트롤러 원래 분기와 동일해야 한다");
	}

	@Test
	void deleteAuthorGroupList_deletesEachUserInOrder() throws Exception {
		List<String> calls = new ArrayList<>();
		EgovAuthorGroupServiceImpl service = new EgovAuthorGroupServiceImpl();
		ReflectionTestUtils.setField(service, "authorGroupDAO", recordingDao(calls));

		service.deleteAuthorGroupList(new AuthorGroup(), new String[] { "user1", "user2", "user3" });

		assertEquals(List.of("delete:user1", "delete:user2", "delete:user3"), calls);
	}
}
