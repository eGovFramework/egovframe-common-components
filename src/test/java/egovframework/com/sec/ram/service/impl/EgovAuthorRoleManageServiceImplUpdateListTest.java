package egovframework.com.sec.ram.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import egovframework.com.sec.ram.service.AuthorRoleManage;

/**
 * {@link EgovAuthorRoleManageServiceImpl#updateAuthorRoleList}가 형제(등록·삭제)
 * 메서드를 그대로 위임하면서도, 컨트롤러가 항목마다 서비스를 두 번씩 호출하던 구조를
 * 서비스 진입점 하나로 옮겼는지 확인한다.
 *
 * <p>{@code context-transaction.xml}의 AOP는 {@code *Impl.*(..)}(이 클래스의 공개 메서드)
 * 마다 트랜잭션을 건다. 이 테스트는 DAO 호출 순서만 검증하고 실제 롤백은 보지 않는다 —
 * 롤백 보장은 위 AOP 설정 자체가 주는 것이지 이 테스트의 관측 대상이 아니다. 대신 "항목별로
 * 서비스가 여러 번 불리던 구조"가 "서비스 진입 한 번으로 항목 N개를 처리하는 구조"로
 * 바뀌었는지, 그리고 배정여부(regYn)에 따른 분기(Y=삭제 후 재등록, 그 외=삭제만)가
 * 그대로 보존됐는지를 DAO 호출 순서로 증명한다.</p>
 */
class EgovAuthorRoleManageServiceImplUpdateListTest {

	@Test
	void updateAuthorRoleList_deletesThenReinsertsOnlyWhenAssigned() throws Exception {
		List<String> calls = new ArrayList<>();
		AuthorRoleManageDAO recordingDao = new AuthorRoleManageDAO() {
			@Override
			public void insertAuthorRole(AuthorRoleManage authorRoleManage) {
				calls.add("insert:" + authorRoleManage.getRoleCode());
			}

			@Override
			public void deleteAuthorRole(AuthorRoleManage authorRoleManage) {
				calls.add("delete:" + authorRoleManage.getRoleCode());
			}
		};

		EgovAuthorRoleManageServiceImpl service = new EgovAuthorRoleManageServiceImpl();
		ReflectionTestUtils.setField(service, "authorRoleManageDAO", recordingDao);

		AuthorRoleManage authorRoleManage = new AuthorRoleManage();
		authorRoleManage.setAuthorCode("AUTH001");

		service.updateAuthorRoleList(authorRoleManage, new String[] { "ROLE_A", "ROLE_B" }, new String[] { "Y", "N" });

		assertEquals(List.of("delete:ROLE_A", "insert:ROLE_A", "delete:ROLE_B"), calls,
				"ROLE_A(Y)는 삭제 후 재등록, ROLE_B(N 그 외)는 삭제만 — 컨트롤러 원래 분기와 동일해야 한다");
	}
}
