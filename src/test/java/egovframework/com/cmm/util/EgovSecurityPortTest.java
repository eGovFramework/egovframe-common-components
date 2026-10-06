package egovframework.com.cmm.util;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;

import javax.imageio.ImageIO;

import org.egovframe.rte.fdl.crypto.EgovEnvCryptoService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.*;
import egovframework.com.cmm.web.EgovFileMngController;
import egovframework.com.cop.cmy.service.*;
import egovframework.com.cop.cmy.web.EgovCommuManageController;
import egovframework.com.cop.bbs.service.*;
import egovframework.com.cop.cmt.service.*;
import egovframework.com.cop.cmt.web.EgovArticleCommentController;
import egovframework.com.uss.ion.mtg.service.*;
import egovframework.com.uss.ion.mtg.web.EgovMtgPlaceManageController;
import egovframework.com.cop.stf.web.EgovBBSSatisfactionController;
import egovframework.com.uss.ion.ntm.service.*;
import egovframework.com.uss.ion.ntm.web.EgovNoteManageController;
import egovframework.com.uss.olp.mgt.service.*;
import egovframework.com.uss.olp.mgt.web.EgovMeetingManageController;
import egovframework.com.sym.ccm.cca.web.EgovCcmCmmnCodeManageController;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.com.utl.sys.nsm.service.NtwrkSvcMntrngChecker;

@org.junit.jupiter.api.parallel.ResourceLock("EgovUserDetailsHelper")
class EgovSecurityPortTest {
    private LoginVO user;
    private boolean admin;
    private EgovUserDetailsService previous;
    private Object previousCrypto;

    @BeforeEach
    void login() {
        previous = new EgovUserDetailsHelper().getEgovUserDetailsService();
        previousCrypto = ReflectionTestUtils.getField(EgovFileMngController.class, "cryptoService");
        user = new LoginVO();
        user.setId("alice");
        user.setUniqId("USER_A");
        user.setOrgnztId("DEPT_A");
        new EgovUserDetailsHelper().setEgovUserDetailsService(stub(EgovUserDetailsService.class, (m,a) -> {
            if (m.equals("getAuthenticatedUser")) return user;
            if (m.equals("isAuthenticated")) return user != null;
            if (m.equals("getAuthorities")) return admin ? List.of("ROLE_ADMIN") : List.of("ROLE_USER");
            return null;
        }));
    }

    @AfterEach
    void restore() {
        new EgovUserDetailsHelper().setEgovUserDetailsService(previous);
        ReflectionTestUtils.setField(EgovFileMngController.class, "cryptoService", previousCrypto);
    }

    @Test
    void ownerUsesTheStoredIdentityDomain() {
        assertDoesNotThrow(() -> EgovAuthorizationHelper.assertAdminOrOwner("USER_A"));
        assertDoesNotThrow(() -> EgovAuthorizationHelper.assertAdminOrOwnerById("alice"));
        assertThrows(IllegalStateException.class, () -> EgovAuthorizationHelper.assertAdminOrOwner("alice"));
        assertThrows(IllegalStateException.class, () -> EgovAuthorizationHelper.assertAdminOrOwner("USER_B"));
        assertThrows(IllegalStateException.class, () -> EgovAuthorizationHelper.assertAdminOrOwner(null));
        assertThrows(IllegalStateException.class, () -> EgovAuthorizationHelper.assertAdminOrOwnerById(" "));
        admin = true;
        assertDoesNotThrow(() -> EgovAuthorizationHelper.assertAdminOrOwner("USER_B"));
        // 소유자 정보가 없는 데이터도 관리자는 통과한다(isAdminOrOwner 와 같은 기준)
        assertDoesNotThrow(() -> EgovAuthorizationHelper.assertAdminOrOwner(null));
        assertDoesNotThrow(() -> EgovAuthorizationHelper.assertAdminOrOwnerById(" "));
        user = null;
        assertThrows(IllegalStateException.class, () -> EgovAuthorizationHelper.assertAdminOrOwner("USER_A"));
    }

    @Test
    void ownerOnlyCheckGivesAdministratorsNoException() {
        assertDoesNotThrow(() -> EgovAuthorizationHelper.assertOwner("USER_A"));
        assertThrows(IllegalStateException.class, () -> EgovAuthorizationHelper.assertOwner("alice"));
        assertThrows(IllegalStateException.class, () -> EgovAuthorizationHelper.assertOwner(" "));
        admin = true;
        assertThrows(IllegalStateException.class, () -> EgovAuthorizationHelper.assertOwner("USER_B"));
        user = null;
        assertThrows(IllegalStateException.class, () -> EgovAuthorizationHelper.assertOwner("USER_A"));
    }

    @Test
    void ownerOnlyCheckByLoginIdGivesAdministratorsNoException() {
        assertDoesNotThrow(() -> EgovAuthorizationHelper.assertOwnerById("alice"));
        assertThrows(IllegalStateException.class, () -> EgovAuthorizationHelper.assertOwnerById("USER_A"));
        assertThrows(IllegalStateException.class, () -> EgovAuthorizationHelper.assertOwnerById(" "));
        admin = true;
        assertThrows(IllegalStateException.class, () -> EgovAuthorizationHelper.assertOwnerById("bob"));
        user = null;
        assertThrows(IllegalStateException.class, () -> EgovAuthorizationHelper.assertOwnerById("alice"));
    }

    @Test
    void communitySelfSignupCannotChooseManagerOrAnotherUser() {
        EgovCommuManageController controller = new EgovCommuManageController();
        AtomicBoolean saved = new AtomicBoolean();
        ReflectionTestUtils.setField(controller, "egovCommuManageService", stub(EgovCommuManageService.class, (m,a) -> {
            if (m.equals("checkCommuUserDetail")) return "";
            if (m.equals("insertCommuUserRqst")) {
                CommunityUser value = (CommunityUser) a[0];
                assertEquals("N", value.getMngrAt());
                assertEquals("USER_A", value.getEmplyrId());
                saved.set(true);
            }
            return null;
        }));
        messages(controller);
        CommunityUser request = new CommunityUser();
        request.setMngrAt("Y");
        request.setEmplyrId("USER_B");
        controller.insertCmmntyUserBySelf(request, new ModelMap(), new org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap());
        assertTrue(saved.get());
    }

    @Test
    void replyChecksRecipientBeforeReadingOriginalNote() throws Exception {
        EgovNoteManageController controller = new EgovNoteManageController();
        AtomicBoolean allowed = new AtomicBoolean();
        AtomicBoolean read = new AtomicBoolean();
        ReflectionTestUtils.setField(controller, "cmmUseService", stub(EgovCmmUseService.class, (m,a) -> List.of()));
        ReflectionTestUtils.setField(controller, "egovNoteManageService", stub(EgovNoteManageService.class, (m,a) -> {
            if (m.equals("selectNoteRecptnCheck")) {
                NoteManageVO lookup = (NoteManageVO) a[0];
                assertEquals("NOTE_B", lookup.getNoteId());
                assertEquals("USER_A", lookup.getRcverId());
                return allowed.get() ? 1 : 0;
            }
            if (m.equals("selectNoteManage")) { read.set(true); return Map.of("noteSj", "subject"); }
            return null;
        }));
        NoteManageVO request = new NoteManageVO();
        request.setNoteId("NOTE_B");
        request.setRcverId("USER_B");
        assertThrows(IllegalStateException.class, () -> controller.EgovNoteRecptnRegistForm(request, Map.of("cmd","reply"), new ModelMap()));
        assertFalse(read.get());
        allowed.set(true);
        controller.EgovNoteRecptnRegistForm(request, Map.of("cmd","reply"), new ModelMap());
        assertTrue(read.get());
    }

    @Test
    void meetingDeletionByAnotherAdministratorIsAllowed() throws Exception {
        EgovMeetingManageController controller = new EgovMeetingManageController();
        EgovMap stored = new EgovMap(); stored.put("frstRegisterId", "USER_B");
        AtomicBoolean deleted = new AtomicBoolean();
        ReflectionTestUtils.setField(controller, "egovMeetingManageService", stub(EgovMeetingManageService.class, (m,a) -> {
            if (m.equals("selectMeetingManageDetail")) return List.of(stored);
            if (m.equals("deleteMeetingManage")) deleted.set(true);
            return null;
        }));
        // 관리자 전용 경로는 관리자끼리 신뢰하므로 등록자가 아니어도 삭제된다
        controller.egovMeetingManageDetail(new egovframework.com.cmm.ComDefaultVO(), new MeetingManageVO(), Map.of("cmd","del"), new ModelMap());
        assertTrue(deleted.get());
    }

    @Test
    void anonymousSatisfactionVerifiesThePasswordOfTheUpdatedRecord() {
        EgovBBSSatisfactionController controller = new EgovBBSSatisfactionController();
        AtomicBoolean saved = new AtomicBoolean();
        ReflectionTestUtils.setField(controller, "bbsSatisfactionService", stub(EgovBBSSatisfactionService.class, (m,a) -> {
            if (m.equals("getSatisfactionPassword")) {
                assertEquals("42", ((Satisfaction)a[0]).getStsfdgNo());
                return EgovFileScrty.encryptPassword("correct", "42");
            }
            if (m.equals("updateSatisfaction")) saved.set(true);
            return null;
        }));
        messages(controller);
        SatisfactionVO search = new SatisfactionVO(); search.setStsfdgNo("other"); search.setConfirmPassword("wrong");
        Satisfaction value = new Satisfaction(); value.setStsfdgNo("42"); value.setStsfdgPassword("new-password");
        controller.updateAnonymousSatisfaction(search, value, new BeanPropertyBindingResult(value,"satisfaction"), new ModelMap());
        assertFalse(saved.get());
        search.setConfirmPassword("correct");
        controller.updateAnonymousSatisfaction(search, value, new BeanPropertyBindingResult(value,"satisfaction"), new ModelMap());
        assertTrue(saved.get());
    }

    @Test
    void passwordSearchLimiterStopsTheServiceAfterFiveAttemptsDespiteForwardedHeaders() throws Exception {
        var controller = new egovframework.com.uat.uia.web.EgovLoginController();
        var calls = new java.util.concurrent.atomic.AtomicInteger();
        ReflectionTestUtils.setField(controller,"loginService",stub(egovframework.com.uat.uia.service.EgovLoginService.class,(m,a)-> {
            assertEquals("searchPassword",m);
            calls.incrementAndGet();
            return false;
        }));
        messages(controller);
        var value = new egovframework.com.cmm.SearchPasswordRequestVO();
        String client = java.util.UUID.randomUUID().toString();
        for(int i=0;i<6;i++) {
            MockHttpServletRequest request = new MockHttpServletRequest();
            request.setRemoteAddr(client);
            request.addHeader("X-Forwarded-For","192.0.2."+i);
            controller.searchPassword(value,new BeanPropertyBindingResult(value,"searchPasswordRequestVO"),new ModelMap(),request);
        }
        assertEquals(5,calls.get());
    }

    @Test
    void imagesRequireAllowedExtensionAndDecodableContent() throws Exception {
        ByteArrayOutputStream png = new ByteArrayOutputStream();
        ImageIO.write(new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB), "png", png);
        assertTrue(EgovFileMngUtil.isAllowedImageExtension("photo.PNG"));
        assertFalse(EgovFileMngUtil.isAllowedImageExtension("photo.svg"));
        assertTrue(EgovFileMngUtil.isValidImageBytes(png.toByteArray()));
        MockMultipartFile fake = new MockMultipartFile("file", "photo.png", "image/png", "<html>not an image</html>".getBytes());
        assertFalse(EgovFileMngUtil.isValidImageFile(fake));
        // Rejection must happen before ID allocation or writing any uploaded file.
        assertThrows(IllegalArgumentException.class, () -> new EgovFileMngUtil().parseFileInf(Map.of("file",fake),"T",0,"","",true));
    }

    @Test
    void newlyProtectedAdminRouteIsInterceptedBySpringAop() {
        ProxyFactory factory = new ProxyFactory(new EgovCcmCmmnCodeManageController());
        factory.setProxyTargetClass(true);
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("@annotation(egovframework.com.cmm.annotation.RequireAdmin)");
        factory.addAdvisor(new DefaultPointcutAdvisor(pointcut, (org.aopalliance.intercept.MethodInterceptor) invocation -> {
            new EgovAdminAuthorizationAspect().assertAdmin();
            return "allowed";
        }));
        EgovCcmCmmnCodeManageController controller = (EgovCcmCmmnCodeManageController) factory.getProxy();
        // unchecked 거부라 throws 선언이 없는 메서드에서도 UndeclaredThrowableException 으로 감싸이지 않는다
        assertThrows(egovframework.com.cmm.exception.EgovAccessDeniedException.class, () -> controller.updateCmmnCodeView(null,new ModelMap()));
        admin=true;
        assertDoesNotThrow(() -> assertEquals("allowed",controller.updateCmmnCodeView(null,new ModelMap())));
        user=null;
        assertThrows(egovframework.com.cmm.exception.EgovLoginRequiredException.class, () -> controller.updateCmmnCodeView(null,new ModelMap()));
    }

    @Test
    void adminDenialIsNotWrappedWhenHandlerDeclaresNoThrows() {
        ProxyFactory factory = new ProxyFactory(new egovframework.com.uss.ion.fbk.web.EgovFacebookController());
        factory.setProxyTargetClass(true);
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("@annotation(egovframework.com.cmm.annotation.RequireAdmin)");
        factory.addAdvisor(new DefaultPointcutAdvisor(pointcut, (org.aopalliance.intercept.MethodInterceptor) invocation -> {
            new EgovAdminAuthorizationAspect().assertAdmin();
            return invocation.proceed();
        }));
        var controller = (egovframework.com.uss.ion.fbk.web.EgovFacebookController) factory.getProxy();
        // showAlbums 는 throws 선언이 없다 — checked 예외였다면 UndeclaredThrowableException 이 된다
        assertThrows(egovframework.com.cmm.exception.EgovAccessDeniedException.class,
            () -> controller.showAlbums(new org.springframework.ui.ExtendedModelMap()));
    }

    @Test
    void monitoringRejectsLoopbackBeforeOpeningSocket() {
        var result = NtwrkSvcMntrngChecker.check("127.0.0.1", 1);
        assertFalse(result.isNrmltAt());
        assertInstanceOf(IllegalArgumentException.class, result.getCause());
        assertThrows(IllegalArgumentException.class, () -> egovframework.com.cmm.EgovWebUtil.validatePublicFtpHost("127.0.0.1"));
    }

    @Test
    void departmentOrderChangesUseStoredDepartmentInsteadOfSubmittedDepartment() {
        var controller = new egovframework.com.cop.smt.djm.web.EgovDeptJobController();
        var stored = new egovframework.com.cop.smt.djm.service.DeptJobBxVO();
        stored.setDeptId("DEPT_B");
        AtomicBoolean updated = new AtomicBoolean();
        ReflectionTestUtils.setField(controller, "deptJobService", stub(egovframework.com.cop.smt.djm.service.EgovDeptJobService.class, (m,a) -> {
            if (m.equals("selectDeptJobBx")) return stored;
            if (m.equals("updateDeptJobBxOrdr")) { updated.set(true); return true; }
            return null;
        }));
        var request = new egovframework.com.cop.smt.djm.service.DeptJobBxVO();
        request.setDeptId("DEPT_A");
        assertThrows(IllegalStateException.class, () -> controller.updateDeptJobBxOrdr(request, new ModelMap()));
        assertFalse(updated.get());
        stored.setDeptId("DEPT_A");
        controller.updateDeptJobBxOrdr(request, new ModelMap());
        assertTrue(updated.get());
    }

    @Test
    void leaderScheduleDeletionRequiresStoredOwnerEvenForAdministrator() {
        var controller = new egovframework.com.cop.smt.lsm.web.EgovLeaderSchdulController();
        var stored = new egovframework.com.cop.smt.lsm.service.LeaderSchdulVO();
        stored.setFrstRegisterId("USER_B");
        AtomicBoolean deleted = new AtomicBoolean();
        ReflectionTestUtils.setField(controller, "leaderSchdulService", stub(egovframework.com.cop.smt.lsm.service.EgovLeaderSchdulService.class, (m,a) -> {
            if (m.equals("selectLeaderSchdul")) return stored;
            if (m.equals("deleteLeaderSchdul")) deleted.set(true);
            return null;
        }));
        var request = new egovframework.com.cop.smt.lsm.service.LeaderSchdulVO();
        request.setFrstRegisterId("USER_A");
        assertThrows(IllegalStateException.class, () -> controller.deleteLeaderSchdul(request, new ModelMap()));
        assertFalse(deleted.get());
        admin = true;
        assertThrows(IllegalStateException.class, () -> controller.deleteLeaderSchdul(request, new ModelMap()));
        assertFalse(deleted.get());
        admin = false;
        stored.setFrstRegisterId("USER_A");
        controller.deleteLeaderSchdul(request, new ModelMap());
        assertTrue(deleted.get());
    }

    @Test
    void memoInstructionsCheckSessionUserRelationshipBeforeWriting() {
        var controller = new egovframework.com.cop.smt.mrm.web.EgovMemoReprtController();
        AtomicBoolean allowed = new AtomicBoolean(), saved = new AtomicBoolean();
        ReflectionTestUtils.setField(controller, "memoReprtService", stub(egovframework.com.cop.smt.mrm.service.EgovMemoReprtService.class, (m,a) -> {
            if (m.equals("selectMemoReprt")) {
                var query = (egovframework.com.cop.smt.mrm.service.MemoReprtVO) a[0];
                assertEquals("USER_A", query.getSearchId());
                if (!allowed.get()) return null;
                // 지시사항은 보고받는 사람(REPORTR_ID)만 작성한다
                query.setReportrId("USER_A");
                return query;
            }
            if (m.equals("updateMemoReprtDrctMatter")) saved.set(true);
            return null;
        }));
        var request = new egovframework.com.cop.smt.mrm.service.MemoReprtVO();
        request.setSearchId("USER_B");
        assertThrows(IllegalStateException.class, () -> controller.updateMemoReprtDrctMatter(request,new ModelMap()));
        assertFalse(saved.get());
        allowed.set(true);
        controller.updateMemoReprtDrctMatter(request,new ModelMap());
        assertTrue(saved.get());
    }

    @Test
    void recipientCheckMappersParseAndBindNoteAndSessionUserInEveryDialect() throws Exception {
        for (String db : List.of("mysql", "maria", "oracle", "tibero", "altibase", "cubrid", "postgres", "goldilocks")) {
            var config = new org.apache.ibatis.session.Configuration();
            config.getTypeAliasRegistry().registerAlias("egovMap", EgovMap.class);
            config.getTypeAliasRegistry().registerAlias("comDefaultVO", egovframework.com.cmm.ComDefaultVO.class);
            String resource = "egovframework/mapper/com/uss/ion/ntm/EgovNoteManage_SQL_" + db + ".xml";
            try (var input = getClass().getClassLoader().getResourceAsStream(resource)) {
                assertNotNull(input, resource);
                new org.apache.ibatis.builder.xml.XMLMapperBuilder(input,config,resource,config.getSqlFragments()).parse();
            }
            var bound = config.getMappedStatement("NoteManage.selectNoteRecptnCheck").getBoundSql(new NoteManageVO());
            assertEquals(List.of("noteId", "rcverId"), bound.getParameterMappings().stream().map(p -> p.getProperty()).toList(), db);
        }
    }

    @Test
    void commentModifyViewComparesStoredUniqId() {
        EgovArticleCommentController controller = new EgovArticleCommentController();
        CommentVO stored = new CommentVO();
        stored.setWrterId("USER_B");
        ReflectionTestUtils.setField(controller, "egovArticleCommentService", stub(EgovArticleCommentService.class, (m,a) -> {
            if (m.equals("selectArticleCommentList")) return Map.of("resultList", List.of(), "resultCnt", "0");
            if (m.equals("selectArticleCommentDetail")) return stored;
            return null;
        }));
        ReflectionTestUtils.setField(controller, "propertyService", stub(org.egovframe.rte.fdl.property.EgovPropertyService.class, (m,a) -> 10));
        CommentVO request = new CommentVO();
        assertThrows(IllegalStateException.class, () -> controller.updateArticleCommentView(request, new ModelMap()));
        // 등록은 uniqId 를 WRTER_ID 에 저장하므로 로그인 id(alice)가 아니라 uniqId 로 통과해야 한다.
        stored.setWrterId("USER_A");
        assertDoesNotThrow(() -> controller.updateArticleCommentView(request, new ModelMap()));
    }

    @Test
    void meetingRoomReservationUpdateChecksStoredReserver() throws Exception {
        EgovMtgPlaceManageController controller = new EgovMtgPlaceManageController();
        MtgPlaceManageVO stored = new MtgPlaceManageVO();
        stored.setResveManId("USER_B");
        AtomicBoolean updated = new AtomicBoolean();
        ReflectionTestUtils.setField(controller, "egovMtgPlaceManageService", stub(EgovMtgPlaceManageService.class, (m,a) -> {
            if (m.equals("selectMtgPlaceResveDetail")) return stored;
            if (m.equals("updtMtgPlaceResve")) updated.set(true);
            return null;
        }));
        messages(controller);
        MtgPlaceResveVO request = new MtgPlaceResveVO();
        request.setMtgPlaceId("PLACE_1");
        request.setResveId("RESVE_1");
        var binding = new BeanPropertyBindingResult(request, "mtgPlaceResveVO");
        assertThrows(IllegalStateException.class, () -> controller.updtMtgPlaceResveManage(new MtgPlaceManageVO(), request, binding,
                "Y", new org.springframework.web.bind.support.SimpleSessionStatus(), new ModelMap()));
        assertFalse(updated.get());
        stored.setResveManId("USER_A");
        controller.updtMtgPlaceResveManage(new MtgPlaceManageVO(), request, binding,
                "Y", new org.springframework.web.bind.support.SimpleSessionStatus(), new ModelMap());
        assertTrue(updated.get());
    }

    private static void messages(Object target) {
        ReflectionTestUtils.setField(target, "egovMessageSource", new EgovMessageSource() {
            @Override public String getMessage(String code) { return code; }
        });
    }

    @SuppressWarnings("unchecked")
    private static <T> T stub(Class<T> type, BiFunction<String,Object[],Object> handler) {
        return (T) Proxy.newProxyInstance(type.getClassLoader(),new Class<?>[]{type},(proxy,method,args) -> {
            if (method.getName().equals("toString")) return type.getSimpleName()+"Stub";
            return handler.apply(method.getName(),args);
        });
    }
}
