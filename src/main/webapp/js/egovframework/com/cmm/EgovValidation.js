/**
 * eGovFrame Common Validation JavaScript Library
 * Spring Modules Validation을 대체하는 클라이언트사이드 검증 라이브러리
 */

const EgovValidation = {
    
    // 에러 메시지 설정
    messages: {
        required: '{0}은(는) 필수입력 항목입니다.',
        maxlength: '{0}은(는) {1}자를 초과할 수 없습니다.',
        minlength: '{0}은(는) {1}자 이상이어야 합니다.',
        integer: '{0}은(는) 숫자여야 합니다.',
        email: '{0}은(는) 올바른 이메일 형식이 아닙니다.',
        password1: '패스워드는 8~20자 이내여야 합니다.',
        password2: '패스워드에 특수문자를 사용할 수 없습니다.',
        password3: '연속된 문자나 순차적인 문자 4개 이상 사용할 수 없습니다.',
        password4: '반복문자나 숫자 연속 4개 이상 사용할 수 없습니다.',
        pwdCheckComb3: '영문자, 숫자, 특수문자의 최소 3가지 조합이어야 합니다.',
        pwdCheckComb4: '영대문자, 영소문자, 숫자, 특수문자의 최소 4가지 조합이어야 합니다.',
        pwdCheckSeries: '연속된 3개 이상의 문자나 숫자를 사용할 수 없습니다.',
        pwdCheckRepeat: '반복된 3개 이상의 문자나 숫자를 사용할 수 없습니다.',
        english: '{0}은(는) 영문자만 입력 가능합니다.',
        alphanumeric: '{0}은(는) 영문자, 숫자, 언더스코어(_)만 입력 가능합니다.',
        number: '{0}은(는) 숫자여야 합니다.',
        min: '{0}은(는) {1} 이상이어야 합니다.',
        positive: '{0}은(는) 양수여야 합니다.',
        dateRange: '행사시작일은 행사종료일보다 이전이어야 합니다.',
        telNo: '{0}은(는) 올바른 연락처 형식이 아닙니다. (숫자 및 하이픈만 입력)',
        urlFormat: '{0}의 형식이 URL 형식과 맞지 않습니다.',
        dateRangeEvent: '행사시작일이 행사종료일보다 늦습니다. 행사기간을 확인해 주세요.',
        dateRangeRcept: '행사접수시작일이 행사접수종료일보다 늦습니다. 행사접수기간을 확인해 주세요.',
        dateRangeRceptEnd: '행사접수는 행사시작일 이전에 접수종료되어야 합니다. 행사기간/행사접수기간을 확인해 주세요.',
        partcptCtZero: '참가비용이 유료인 경우 0원 이상 입력하셔야 합니다.',
        psncpaZero: '정원은 0명 이상 입력하셔야 합니다.',
        bhNtfcIntrvlRequired: '사전알림간격 지정이 필요합니다.',
        mainImageImageReq: '이미지는 필수 입력값입니다.',
        mtgPlaceSelectStartTime: '개방 오픈 시간을 선택하세요.',
        mtgPlaceSelectCloseTime: '개방 종료 시간을 선택하세요.',
        mtgPlaceCheckOpenTime: '개방오픈시간이 개방종료시간보다 늦거나 같습니다. 개방시간을 확인하세요.',
        mtgPlaceResveDplactCeck: '회의실 예약 중복확인을 한 후 회의실 예약을 해주세요.',
        mtgPlaceResveReserve: '이미 회의실이 예약되어 있습니다.',
        mtgPlaceResveTimeStartBeforeEnd: '예약시작시간은 예약종료시간보다 이전이어야 하며 같을 수 없습니다.',
        mtgPlaceResveTimeWithinOpen: '예약시간은 회의실 개방시간 내여야 합니다.'
    },
    
    // 기본 validation 규칙들
    rules: {
        // 필수입력 검사
        required: function(value) {
            return value !== null && value !== undefined && 
                   (typeof value === 'string' ? value.trim().length > 0 : true);
        },
        
        // 최대 길이 검사
        maxlength: function(value, max) {
            if (!value) return true;
            return value.length <= parseInt(max);
        },
        
        // 최소 길이 검사
        minlength: function(value, min) {
            if (!value) return true;
            return value.length >= parseInt(min);
        },
        
        // 숫자 검사
        integer: function(value) {
            if (!value) return true;
            return /^\d+$/.test(value);
        },
        
        // 이메일 검사
        email: function(value) {
            if (!value) return true;
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            return emailRegex.test(value);
        },
        
        // 영문자만 허용
        english: function(value) {
            if (!value) return true;
            return /^[a-zA-Z]+$/.test(value);
        },
        
        // 영문자, 숫자, 언더스코어만 허용 (테이블명 등)
        alphanumeric: function(value) {
            if (!value) return true;
            return /^[a-zA-Z0-9_]*$/.test(value);
        },
        
        // 패스워드 길이 검사 (8~20자)
        password1: function(value) {
            if (!value) return true;
            return value.length >= 8 && value.length <= 20;
        },
        
        // 패스워드 특수문자 제한
        password2: function(value) {
            if (!value) return true;
            // 특수문자 체크 (ASCII 33-47, 58-64, 91-96, 123-126)
            for (let i = 0; i < value.length; i++) {
                const ch = value.charCodeAt(i);
                if ((ch >= 33 && ch <= 47) || (ch >= 58 && ch <= 64) || 
                    (ch >= 91 && ch <= 96) || (ch >= 123 && ch <= 126)) {
                    return false;
                }
            }
            return true;
        },
        
        // 연속된 문자/순차적 문자 4개 이상 금지
        password3: function(value) {
            if (!value) return true;
            let cnt = 0, cnt2 = 1, cnt3 = 1;
            let temp = "";
            
            for (let i = 0; i < value.length; i++) {
                const ch = value.charAt(i);
                if (i > 0) {
                    const prevCh = value.charAt(i-1);
                    if (ch.charCodeAt(0) - prevCh.charCodeAt(0) === 1) {
                        cnt2++;
                        if (cnt2 >= 4) return false;
                    } else {
                        cnt2 = 1;
                    }
                    
                    if (ch.charCodeAt(0) - prevCh.charCodeAt(0) === -1) {
                        cnt3++;
                        if (cnt3 >= 4) return false;
                    } else {
                        cnt3 = 1;
                    }
                }
            }
            return true;
        },
        
        // 반복문자/숫자 연속 4개 이상 금지
        password4: function(value) {
            if (!value) return true;
            for (let i = 0; i < value.length - 3; i++) {
                if (value.charAt(i) === value.charAt(i+1) && 
                    value.charAt(i) === value.charAt(i+2) && 
                    value.charAt(i) === value.charAt(i+3)) {
                    return false;
                }
            }
            return true;
        },
        
        // 3가지 조합 (영문자, 숫자, 특수문자)
        pwdCheckComb3: function(value) {
            if (!value) return true;
            let hasAlpha = /[a-zA-Z]/.test(value);
            let hasNum = /\d/.test(value);
            let hasSpecial = /[~!@#$%^&*?]/.test(value);
            
            let count = 0;
            if (hasAlpha) count++;
            if (hasNum) count++;
            if (hasSpecial) count++;
            
            return count >= 3;
        },
        
        // 4가지 조합 (영대문자, 영소문자, 숫자, 특수문자)
        pwdCheckComb4: function(value) {
            if (!value) return true;
            let hasUpper = /[A-Z]/.test(value);
            let hasLower = /[a-z]/.test(value);
            let hasNum = /\d/.test(value);
            let hasSpecial = /[~!@#$%^&*?]/.test(value);
            
            let count = 0;
            if (hasUpper) count++;
            if (hasLower) count++;
            if (hasNum) count++;
            if (hasSpecial) count++;
            
            return count >= 4;
        },
        
        // 연속된 3개 이상의 문자나 숫자 금지
        pwdCheckSeries: function(value) {
            if (!value) return true;
            for (let i = 0; i < value.length - 2; i++) {
                const ch1 = value.charCodeAt(i);
                const ch2 = value.charCodeAt(i+1);
                const ch3 = value.charCodeAt(i+2);
                
                if ((ch2 - ch1 === 1 && ch3 - ch2 === 1) || 
                    (ch1 - ch2 === 1 && ch2 - ch3 === 1)) {
                    return false;
                }
            }
            return true;
        },
        
        // 반복된 3개 이상의 문자나 숫자 금지
        pwdCheckRepeat: function(value) {
            if (!value) return true;
            for (let i = 0; i < value.length - 2; i++) {
                if (value.charAt(i) === value.charAt(i+1) && 
                    value.charAt(i) === value.charAt(i+2)) {
                    return false;
                }
            }
            return true;
        },
        
        // 숫자 검사 (정수 및 소수점 포함)
        number: function(value) {
            if (!value) return true;
            const numValue = parseFloat(value);
            return !isNaN(numValue) && isFinite(numValue);
        },
        
        // 최소값 검사
        min: function(value, min) {
            if (!value) return true;
            const numValue = parseFloat(value);
            if (isNaN(numValue) || !isFinite(numValue)) return false;
            return numValue >= parseFloat(min);
        },
        
        // 양수 검사 (0보다 큰 값)
        positive: function(value) {
            if (!value) return true;
            const numValue = parseFloat(value);
            if (isNaN(numValue) || !isFinite(numValue)) return false;
            return numValue > 0;
        },
        // 연락처 검사 (숫자, 하이픈만 허용, 9~11자리 숫자)
        telNo: function(value) {
            if (!value) return true;
            const s = value.replace(/-/g, '');
            return /^\d{9,11}$/.test(s);
        },
        // URL 형식 검사
        urlFormat: function(value) {
            if (!value || value.trim() === '') return true;
            const urlRegex = /^(https?:\/\/)?[\w.-]+\.[a-zA-Z]{2,}(\/.*)?$/;
            return urlRegex.test(value.trim());
        }
    },
    
    // 폼 검증 함수
    validateForm: function(formElement, validationRules) {
        const errors = [];
        let firstErrorField = null;
        
        for (const fieldName in validationRules) {
            const field = formElement.elements[fieldName];
            if (!field) continue;
            
            const fieldRules = validationRules[fieldName];
            const fieldValue = this.getFieldValue(field);
            const fieldLabel = fieldRules.label || fieldName;
            
            // 각 규칙별로 검증
            for (const ruleName in fieldRules.rules) {
                if (ruleName === 'label') continue;
                
                const ruleValue = fieldRules.rules[ruleName];
                const ruleFunction = this.rules[ruleName];
                
                if (ruleFunction) {
                    let isValid = false;
                    
                    if (ruleValue === true) {
                        // 매개변수가 없는 규칙
                        isValid = ruleFunction(fieldValue);
                    } else {
                        // 매개변수가 있는 규칙
                        isValid = ruleFunction(fieldValue, ruleValue);
                    }
                    
                    if (!isValid) {
                        const errorMsg = this.formatMessage(this.messages[ruleName], fieldLabel, ruleValue);
                        errors.push(errorMsg);
                        
                        if (!firstErrorField) {
                            firstErrorField = field;
                        }
                        break; // 첫 번째 에러만 표시
                    }
                }
            }
        }
        
        if (errors.length > 0) {
            alert(errors.join('\n'));
            if (firstErrorField) {
                firstErrorField.focus();
            }
            return false;
        }
        
        return true;
    },
    
    // 필드 값 가져오기
    getFieldValue: function(field) {
        if (!field) return '';
        
        if (field.type === 'checkbox' || field.type === 'radio') {
            if (field.length) {
                // 배열인 경우
                for (let i = 0; i < field.length; i++) {
                    if (field[i].checked) {
                        return field[i].value;
                    }
                }
                return '';
            } else {
                return field.checked ? field.value : '';
            }
        } else if (field.type === 'select-one') {
            return field.selectedIndex >= 0 ? field.options[field.selectedIndex].value : '';
        } else if (field.type === 'select-multiple') {
            const values = [];
            for (let i = 0; i < field.options.length; i++) {
                if (field.options[i].selected) {
                    values.push(field.options[i].value);
                }
            }
            return values.join(',');
        } else {
            return field.value || '';
        }
    },
    
    // 메시지 포맷팅
    formatMessage: function(template, ...args) {
        return template.replace(/{(\d+)}/g, function(match, index) {
            return args[index] !== undefined ? args[index] : match;
        });
    },
    
    // 문자열 trim 유틸리티
    trim: function(str) {
        return str ? str.replace(/^\s+|\s+$/g, '') : '';
    }
};

// 전역 함수로 노출 (기존 코드 호환성을 위해)
window.EgovValidation = EgovValidation;

// 기존 Spring Modules validation 함수들을 대체하는 래퍼 함수들
function validateArticleVO(form) {
    const rules = {
        nttSj: {
            label: '제목',
            rules: {
                required: true,
                maxlength: 1200
            }
        },
        nttCn: {
            label: '내용',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

function validateBoardMasterVO(form) {
    const rules = {
        bbsNm: {
            label: '게시판명',
            rules: {
                required: true,
                maxlength: 120
            }
        },
        bbsIntrcn: {
            label: '게시판소개',
            rules: {
                required: true,
                maxlength: 2000
            }
        },
        bbsTyCode: {
            label: '게시판유형',
            rules: {
                required: true
            }
        },
        replyPosblAt: {
            label: '답글가능여부',
            rules: {
                required: true
            }
        },
        fileAtchPosblAt: {
            label: '파일첨부가능여부',
            rules: {
                required: true
            }
        },
        useAt: {
            label: '사용여부',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

function validateFormComment(form) {
    const rules = {
        comment: {
            label: '댓글',
            rules: {
                required: true,
                maxlength: 500
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// RSS 관리 validation
function validateRssManage(form) {
    const rules = {
        trgetSvcNm: {
            label: '대상서비스명',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        trgetSvcTable: {
            label: '대상서비스TABLE',
            rules: {
                required: true,
                maxlength: 255,
                alphanumeric: true
            }
        },
        trgetSvcListCo: {
            label: '대상서비스목록개수',
            rules: {
                required: true,
                maxlength: 5,
                integer: true
            }
        },
        hderTitle: {
            label: '헤더TITLE',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        hderLink: {
            label: '헤더LINK',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        hderDescription: {
            label: '헤더DESCRIPTION',
            rules: {
                required: true,
                maxlength: 4000
            }
        },
        bdtTitle: {
            label: '본문TITLE',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        bdtLink: {
            label: '본문LINK',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        bdtDescription: {
            label: '본문DESCRIPTION',
            rules: {
                required: true,
                maxlength: 4000
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 배너 validation
function validateBanner(form) {
    const rules = {
        bannerNm: {
            label: '배너명',
            rules: {
                required: true,
                maxlength: 30
            }
        },
        linkUrl: {
            label: '링크URL',
            rules: {
                required: true
            }
        },
        bannerImage: {
            label: '배너이미지',
            rules: {
                required: true
            }
        },
        sortOrdr: {
            label: '정렬순서',
            rules: {
                required: true,
                maxlength: 100,
                integer: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 주소록 validation
function validateAdbk(form) {
    const rules = {
        adbkNm: {
            label: '주소록명',
            rules: {
                required: true,
                maxlength: 50
            }
        },
        othbcScope: {
            label: '공개범위',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 템플릿 정보 validation
function validateTemplateInf(form) {
    const rules = {
        tmplatNm: {
            label: '템플릿명',
            rules: {
                required: true,
                maxlength: 50
            }
        },
        tmplatCours: {
            label: '템플릿경로',
            rules: {
                required: true,
                maxlength: 200
            }
        },
		tmplatSeCode: {
		            label: '템플릿구분코드',
		            rules: {
		                required: true
		            }
		        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 만족도조사 validation
function validateSatisfaction(form) {
    const rules = {
        qestnrTmplatId: {
            label: '설문템플릿',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 커뮤니티 마스터 validation
function validateCommuMasterVO(form) {
    const rules = {
        cmmntyNm: {
            label: '커뮤니티명',
            rules: {
                required: true,
                maxlength: 100
            }
        },
        cmmntyIntrcn: {
            label: '커뮤니티소개',
            rules: {
                required: true,
                maxlength: 2000
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 일정관리 validation
function validateIndvdlSchdulManageVO(form) {
    const rules = {
        schdulNm: {
            label: '일정명',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        schdulCn: {
            label: '일정내용',
            rules: {
                required: true,
                maxlength: 2500
            }
        },
		schdulSe: {
            label: '일정구분',
            rules: {
                required: true
            }
        },
		schdulIpcrCode: {
            label: '일정중요도코드',
            rules: {
                required: true
            }
        },
		schdulBgndeHH: {
            label: '일정시작일자(시간)',
            rules: {
                required: true
            }
        },
		schdulBgndeMM: {
            label: '일정시작일자(분)',
            rules: {
                required: true
            }
        },
		schdulEnddeHH: {
            label: '일정종료일자(시간)',
            rules: {
                required: true
            }
        },
		schdulEnddeMM: {
            label: '일정종료일자(분)',
            rules: {
                required: true
            }
        },
		schdulBgndeYYYMMDD: {
            label: '일정시작일자(Year/Month/Day)',
            rules: {
                required: true
            }
        },
		schdulEnddeYYYMMDD: {
            label: '일정종료일자(Year/Month/Day)',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// SMS 정보 validation
function validateSms(form) {
    const rules = {
        trnsmitTelno: {
            label: '발신전화번호',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        trnsmitCn: {
            label: '전송내용',
            rules: {
                required: true,
                maxlength: 2000
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 이메일 전송 validation
function validateSndngMailVO(form) {
    const rules = {
        dsptchPerson: {
            label: '발신자',
            rules: {
                required: true,
                maxlength: 100
            }
        },
        recptnPerson: {
            label: '수신자',
            rules: {
                required: true,
                maxlength: 500
            }
        },
        sj: {
            label: '제목',
            rules: {
                required: true,
                maxlength: 200
            }
        },
        emailCn: {
            label: '내용',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 명함 관리 validation
function validateNameCard(form) {
	const rules = {
	        adres: {
	            label: '주소',
	            rules: {
	                maxlength: 100
	            }
	        },
	        areaNo: {
	            label: '지역번호',
	            rules: {
					maxlength: 4,
					integer: true
	            }
	        },
			endMbtlNum: {
	            label: '끝휴대폰번호',
	            rules: {
					maxlength: 4,
					integer: true
	            }
	        },
			endTelNo: {
	            label: '끝전화번호',
	            rules: {
					maxlength: 4,
					integer: true
	            }
	        },
			middleMbtlNum: {
	            label: '중간휴대폰번호',
	            rules: {
					maxlength: 4,
					integer: true
	            }
	        },
			middleTelNo: {
	            label: '중간전화번호',
	            rules: {
					maxlength: 4,
					integer: true
	            }
	        },
			ncrdNm: {
	            label: '이름',
	            rules: {
					required: true,
					maxlength: 50
	            }
	        },
			ofcpsNm: {
	            label: '직위명',
	            rules: {
					maxlength: 60
	            }
	        },
			remark: {
	            label: '비고',
	            rules: {
					maxlength: 2500
	            }
	        },
			detailAdres: {
	            label: '상세주소',
	            rules: {
					maxlength: 100
	            }
	        }
	    };
	    return EgovValidation.validateForm(form, rules);
}

// 시스템 모니터링 validation
function validateFileSysMntrngVO(form) {
    const rules = {
        logicNm: {
            label: '논리명',
            rules: {
                required: true,
                maxlength: 50
            }
        },
        sysNm: {
            label: '시스템명',
            rules: {
                required: true,
                maxlength: 50
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 네트워크 서비스 모니터링 validation
function validateNtwrkSvcMntrngVO(form) {
    const rules = {
        logicNm: {
            label: '논리명',
            rules: {
                required: true,
                maxlength: 50
            }
        },
        sysNm: {
            label: '시스템명',
            rules: {
                required: true,
                maxlength: 50
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 로그인 정책 validation
function validateLoginPolicy(form) {
    const rules = {
		ipInfo: {
		     label: 'IP정보',
		     rules: {
		         required: true,
				 maxlength: 23
		     }
		},
		lmttAt: {
            label: '제한여부',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 게시판 사용정보 validation
function validateBoardUseInf(form) {
    const rules = {
        bbsNm: {
            label: '게시판명',
            rules: {
                required: true,
                maxlength: 50
            }
        },
        trgetId: {
            label: '대상ID',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 댓글 validation
function validateArticleCommentVO(form) {
    const rules = {
        comment: {
            label: '댓글',
            rules: {
                required: true,
                maxlength: 1000
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
} 

// FAQ validation
function validateFaqVO(form) {
    const rules = {
        qestnSj: {
            label: '질문제목',
            rules: {
                required: true,
                maxlength: 70
            }
        },
        qestnCn: {
            label: '질문내용',
            rules: {
                required: true
            }
        },
        answerCn: {
            label: '답변내용',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
} 

// 메모할일관리 validation
function validateMemoTodoVO(form) {
    const rules = {
        todoNm: {
            label: '할일명',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        todoDe: {
            label: '할일일자',
            rules: {
                required: true
            }
        },
        todoBeginHour: {
            label: '할일시작시간',
            rules: {
                required: true
            }
        },
        todoBeginMin: {
            label: '할일시작분',
            rules: {
                required: true
            }
        },
        todoEndHour: {
            label: '할일종료시간',
            rules: {
                required: true
            }
        },
        todoEndMin: {
            label: '할일종료분',
            rules: {
                required: true
            }
        },
        wrterNm: {
            label: '작성자명',
            rules: {
                required: true
            }
        },
        todoCn: {
            label: '할일내용',
            rules: {
                required: true,
                maxlength: 2500
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// AuthorManage validation
function validateAuthorManage(form) {
	const rules = {
	        authorCode: {
	            label: '권한코드',
	            rules: {
	                required: true,
	                maxlength: 30
	            }
	        },
	        authorNm: {
	            label: '권한 명',
	            rules: {
	                required: true,
					maxlength: 60
	            }
	        },
	        authorDc: {
	            label: '권한코드설명',
	            rules: {
					maxlength: 200
	            }
	        }
	    };
	    return EgovValidation.validateForm(form, rules);
}

// Group validation
function validateGroupManage(form) {
	const rules = {
	        groupNm: {
	            label: '그룹명',
	            rules: {
	                required: true,
	                maxlength: 60
	            }
	        },
	        groupDc: {
	            label: '그룹설명',
	            rules: {
					maxlength: 100
	            }
	        }
	    };
	    return EgovValidation.validateForm(form, rules);
}

// Role validation
function validateRoleManage(form) {
	const rules = {
	        roleNm: {
	            label: '롤명',
	            rules: {
	                required: true,
	                maxlength: 60
	            }
	        },
	        rolePtn: {
	            label: '롤패턴',
	            rules: {
	                required: true,
					maxlength: 300
	            }
	        },
	        roleSort: {
	            label: '롤Sort',
	            rules: {
	                required: true,
					maxlength: 10,
					integer: true
	            }
	        }
	    };
	    return EgovValidation.validateForm(form, rules);
}

// ReprtStats validation
function validateReprtStats(form) {
	const rules = {
		        reprtNm: {
		            label: '보고서명',
		            rules: {
		                required: true,
		                maxlength: 10
		            }
		        }
		    };
		    return EgovValidation.validateForm(form, rules);
}

// Blog validation
function validateBlogMasterVO(form) {
	const rules = {
		        blogNm: {
		            label: '블로그 명',
		            rules: {
		                required: true,
		                maxlength: 120
		            }
		        },
		        blogIntrcn: {
		            label: '블로그 소개',
		            rules: {
		                required: true,
						maxlength: 2000
		            }
		        },
		        useAt: {
		            label: '사용여부',
		            rules: {
		                required: true
		            }
		        }
		    };
		    return EgovValidation.validateForm(form, rules);
}

// Batch Operation Validation (시스템관리 - 배치작업관리 메뉴 - 배치작업 등록/수정)
function validateBatchOpert(form) {
	const rules = {
		batchOpertNm: {
			label: '배치작업명',
			rules: {
				required: true,
				maxlength: 60
			}
		},
		batchProgrm: {
			label: '배치프로그램',
			rules: {
				required: true,
				maxlength: 255
			}
		},
		paramtr: {
			label: '파라미터',
			rules: {
				maxlength: 250
			}
		}
	};
	return EgovValidation.validateForm(form, rules);
}

// Batch Schedule Validation (시스템관리 - 스케줄처리 메뉴 - 배치스케줄 등록/수정)
function validateBatchSchdul(form) {
	const rules = {
		batchOpertId: {
			label: '배치작업ID',
			rules: {
				required: true
			}
		},
		executCycle: {
			label: '실행주기',
			rules: {
				required: true
			}
		},
		executSchdulHour: {
			label: '실행스케줄시간',
			rules: {
				required: true
			}
		},
		executSchdulMnt: {
			label: '실행스케줄분',
			rules: {
				required: true
			}
		},
		executSchdulSecnd: {
			label: '실행스케줄초',
			rules: {
				required: true
			}
		}
	};
	return EgovValidation.validateForm(form, rules);
}

// DeptSchdulManageVO validation
function validateDeptSchdulManageVO(form) {
	const rules = {
		        schdulSe: {
		            label: '일정구분',
		            rules: {
		                required: true
		            }
		        },
		        schdulNm: {
		            label: '일정명',
		            rules: {
		                required: true,
						maxlength: 255
		            }
		        },
		        schdulCn: {
		            label: '일정내용',
		            rules: {
		                required: true,
						maxlength: 2500
		            }
		        },
				schdulBgndeHH: {
					label: '일정시작일자(시간)',
					rules: {
						required: true
					}
				},
				schdulBgndeMM: {
					label: '일정시작일자(분)',
					rules: {
						required: true
					}
				},
				schdulEnddeHH: {
					label: '일정종료일자(시간)',
					rules: {
						required: true
					}
				},
				schdulEnddeMM: {
					label: '일정종료일자(분)',
					rules: {
						required: true
					}
				},
				schdulBgndeYYYMMDD: {
					label: '일정시작일자(Year/Month/Day)',
					rules: {
						required: true
					}
				},
				schdulEnddeYYYMMDD: {
					label: '일정종료일자(Year/Month/Day)',
					rules: {
						required: true
					}
				},
				schdulDeptName: {
					label: '담당부서',
					rules: {
						required: true
					}
				},
				schdulChargerName: {
					label: '담당자명',
					rules: {
						required: true
					}
				}
		    };
		    return EgovValidation.validateForm(form, rules);
}

//DiaryManageVO Validation
function validateDiaryManageVO(form) {
	const rules = {
		        schdulId: {
		            label: '일정ID',
		            rules: {
		                required: true
		            }
		        },
		        diaryProcsPte: {
		            label: '진척률',
		            rules: {
		                required: true
		            }
		        },
		        diaryNm: {
		            label: '일정명',
		            rules: {
		                required: true,
						maxlength: 255
		            }
		        },
				drctMatter: {
					label: '지지사항',
					rules: {
						required: true,
						maxlength: 2500
					}
				},
				partclrMatter: {
					label: '특이사항',
					rules: {
						required: true,
						maxlength: 2500
					}
				}
		    };
		    return EgovValidation.validateForm(form, rules);
}

// LeaderSchdulVO Validation
function validateLeaderSchdulVO(form) {
	const rules = {
		        schdulSe: {
		            label: '일정구분',
		            rules: {
		                required: true
		            }
		        },
		        schdulNm: {
		            label: '일정명',
		            rules: {
		                required: true,
						maxlength: 255
		            }
		        },
		        schdulCn: {
		            label: '일정내용',
		            rules: {
		                required: true,
						maxlength: 2500
		            }
		        },
				leaderName: {
		            label: '간부명',
		            rules: {
		                required: true
		            }
		        },
				schdulChargerName: {
		            label: '일정담당자명',
		            rules: {
		                required: true
		            }
		        },
				schdulBgndeHH: {
					label: '일정시작일자(시간)',
					rules: {
						required: true
					}
				},
				schdulBgndeMM: {
					label: '일정시작일자(분)',
					rules: {
						required: true
					}
				},
				schdulEnddeHH: {
					label: '일정종료일자(시간)',
					rules: {
						required: true
					}
				},
				schdulEnddeMM: {
					label: '일정종료일자(분)',
					rules: {
						required: true
					}
				},
				schdulBgndeYYYMMDD: {
					label: '일정시작일자(Year/Month/Day)',
					rules: {
						required: true
					}
				},
				schdulEnddeYYYMMDD: {
					label: '일정종료일자(Year/Month/Day)',
					rules: {
						required: true
					}
				}
		    };
		    return EgovValidation.validateForm(form, rules);
}

// DeptJobBxVO Validation
function validateDeptJobBxVO(form) {
	const rules = {
			        deptJobBxNm: {
			            label: '부서업무함명',
			            rules: {
			                required: true,
							maxlength: 255
			            }
			        },
			        deptNm: {
			            label: '부서명',
			            rules: {
			                required: true
			            }
			        },
					indictOrdr: {
						label: '표시순서',
						rules: {
							required: true
						}
					}
			    };
			    return EgovValidation.validateForm(form, rules);
}

// DeptJobVO Validation
function validateDeptJobVO(form) {
	const rules = {
			        deptJobBxNm: {
			            label: '부서업무함명',
			            rules: {
			                required: true
			            }
			        },
			        deptNm: {
			            label: '부서명',
			            rules: {
			                required: true
			            }
			        },
					deptJobNm: {
						label: '부서업무명',
						rules: {
							required: true,
							maxlength: 255
						}
					},
					deptJobCn: {
						label: '부서업무내용',
						rules: {
							required: true,
							maxlength: 2500
						}
					},
					chargerNm: {
						label: '업무담당자명',
						rules: {
							required: true
						}
					},
					priort: {
						label: '우선순위',
						rules: {
							required: true
						}
					}
			    };
			    return EgovValidation.validateForm(form, rules);	
}

// WikMnthngReprtVO Validation
function validateWikMnthngReprtVO(form) {
	const rules = {
			        reprtSe: {
			            label: '보고서구분',
			            rules: {
			                required: true
			            }
			        },
			        reprtSj: {
			            label: '보고서제목',
			            rules: {
			                required: true,
							maxlength: 255
			            }
			        },
					reprtDe: {
						label: '보고일자',
						rules: {
							required: true
						}
					},
					wrterNm: {
						label: '작성자명',
						rules: {
							required: true
						}
					},
					reportrNm: {
						label: '보고자명',
						rules: {
							required: true
						}
					},
					reprtBgnDe: {
						label: '보고시작일자',
						rules: {
							required: true
						}
					},
					reprtEndDe: {
						label: '보고종료일자',
						rules: {
							required: true
						}
					},
					reprtThswikCn: {
						label: '금주보고내용',
						rules: {
							required: true
						}
					},
					reprtLesseeCn: {
						label: '차주보고내용',
						rules: {
							required: true
						}
					},
					partclrMatter: {
						label: '특이사항',
						rules: {
							maxlength: 2500
						}
					}
			    };
			    return EgovValidation.validateForm(form, rules);
}

// Batch Operation Validation (시스템관리 - 배치작업관리 메뉴 - 배치작업 등록/수정)
function validateBatchOpert(form) {
	const rules = {
		batchOpertNm: {
			label: '배치작업명',
			rules: {
				required: true,
				maxlength: 60
			}
		},
		batchProgrm: {
			label: '배치프로그램',
			rules: {
				required: true,
				maxlength: 255
			}
		},
		paramtr: {
			label: '파라미터',
			rules: {
				maxlength: 250
			}
		}
	};
	return EgovValidation.validateForm(form, rules);
}

// Batch Schedule Validation (시스템관리 - 스케줄처리 메뉴 - 배치스케줄 등록/수정)
function validateBatchSchdul(form) {
	const rules = {
		batchOpertId: {
			label: '배치작업ID',
			rules: {
				required: true
			}
		},
		executCycle: {
			label: '실행주기',
			rules: {
				required: true
			}
		},
		executSchdulHour: {
			label: '실행스케줄시간',
			rules: {
				required: true
			}
		},
		executSchdulMnt: {
			label: '실행스케줄분',
			rules: {
				required: true
			}
		},
		executSchdulSecnd: {
			label: '실행스케줄초',
			rules: {
				required: true
			}
		}
	};
	return EgovValidation.validateForm(form, rules);
}

// Backup Validation (시스템관리 - 백업관리 메뉴 - 백업작업 등록/수정)
function validateBackupOpert(form) {
	const rules = {
		backupOpertNm: {
			label: '백업작업명',
			rules: {
				required: true,
				maxlength: 60
			}
		},
		backupOrginlDrctry: {
			label: '백업원본디렉토리',
			rules: {
				required: true,
				maxlength: 255
			}
		},
		backupStreDrctry: {
			label: '백업저장디렉토리',
			rules: {
				required: true,
				maxlength: 255
			}
		},
		cmprsSe: {
			label: '압축구분',
			rules: {
				required: true
			}
		},
		executCycle: {
			label: '실행주기',
			rules: {
				required: true
			}
		},
		executSchdulHour: {
			label: '실행스케줄시간',
			rules: {
				required: true
			}
		},
		executSchdulMnt: {
			label: '실행스케줄분',
			rules: {
				required: true,
			}
		},
		executSchdulSecnd: {
			label: '실행스케줄초',
			rules: {
				required: true
			}
		}
	};
	return EgovValidation.validateForm(form, rules);
}

// 네트워크관리 Validation (시스템관리 - 네트워크관리 - 백업작업 등록/수정)
function validateNtwrk(form) {
	const rules = {
		ntwrkIp: {
			label: '네트워크IP',
			rules: {
				required: true,
				maxlength: 23
			}
		},
		gtwy: {
			label: '게이트웨이',
			rules: {
				required: true,
				maxlength: 23
			}
		},
		subnet: {
			label: 'SUBNET',
			rules: {
				required: true,
				maxlength: 23
			}
		},
		domnServer: {
			label: '도메인이름서버',
			rules: {
				required: true,
				maxlength: 23
			}
		},
		manageIem: {
			label: '관리항목',
			rules: {
				required: true
			}
		},
		userNm: {
			label: '사용자명',
			rules: {
				required: true,
				maxlength: 30
			}
		},
		useAt: {
			label: '사용여부',
			rules: {
				required: true
			}
		},
		regstYmd: {
			label: '등록일자',
			rules: {
				required: true
			}
		}
	};
	return EgovValidation.validateForm(form, rules);
}

// 서버정보관리(서버H/W 관리) Validation (시스템관리 - 서버정보관리 - 서버H/W 등록/수정)
function validateServerEqpmn(form) {
	const rules = {
		serverEqpmnNm: {
			label: '서버장비명',
			rules: {
				required: true,
				maxlength: 30
			}
		},
		serverEqpmnIp: {
			label: '서버장비IP',
			rules: {
				required: true,
				maxlength: 23
			}
		},
		serverEqpmnMngrNm: {
			label: '서버장비관리자명',
			rules: {
				required: true,
				maxlength: 30
			}
		},
		mngrEmailAddr: {
			label: '관리자이메일주소',
			rules: {
				required: true,
				maxlength: 50,
				email: true
			}
		},
		opersysmInfo: {
			label: 'OS정보',
			rules: {
				required: true,
				maxlength: 1000
			}
		},
		cpuInfo: {
			label: 'CPU정보',
			rules: {
				required: true,
				maxlength: 1000
			}
		},
		moryInfo: {
			label: '메모리정보',
			rules: {
				required: true,
				maxlength: 1000
			}
		},
		hdDisk: {
			label: '하드디스크',
			rules: {
				required: true,
				maxlength: 1000
			}
		},
		regstYmd: {
			label: '등록일자',
			rules: {
				required: true
			}
		}
	};
	return EgovValidation.validateForm(form, rules);
}

// 서버S/W관리 Validation (시스템관리 - 서버S/W관리 - 서버S/W 등록/수정)
function validateServerEqpmn(form) {
	const rules = {
		serverNm: {
			label: '서버S/W명',
			rules: {
				required: true,
				maxlength: 30
			}
		},
		regstYmd: {
			label: '등록일자',
			rules: {
				required: true
			}
		}
	};
	return EgovValidation.validateForm(form, rules);
}

// 장애신청관리 Validation (시스템관리 - 장애신청관리 - 장애신청 등록/수정)
function validateTroblReqst(form) {
	const rules = {
		troblNm: {
			label: '장애명',
			rules: {
				required: true,
				maxlength: 30
			}
		},
		troblKnd: {
			label: '장애종류',
			rules: {
				required: true
			}
		},
		troblDc: {
			label: '장애설명',
			rules: {
				required: true
			}
		},
		troblOccrrncTime: {
			label: '장애발생시간',
			rules: {
				required: true,
				minlength: 14
			}
		},
		troblRqesterNm: {
			label: '장애등록자',
			rules: {
				required: true,
				maxlength: 30
			}
		}
	};
	return EgovValidation.validateForm(form, rules);
}

// 장애처리결과관리 Validation (시스템관리 - 장애처리결과관리 - 장애처리 등록)
function validateTroblProcess(form) {
	const rules = {
		troblProcessResult: {
			label: '장애 처리 결과',
			rules: {
				required: true
			}
		},
		troblProcessTime: {
			label: '장애 처리 시간',
			rules: {
				required: true,
				minlength: 14
			}
		},
		troblOpetrNm: {
			label: '장애 처리자 명',
			rules: {
				required: true,
				maxlength: 30
			}
		}
	};
	return EgovValidation.validateForm(form, rules);
}

// 연계기관관리 - 연계기관 validation
function validateCntcInstt(form) {
    const rules = {
        insttNm: {
            label: '기관명',
            rules: {
                required: true,
                maxlength: 60
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 연계기관관리 - 연계시스템 validation
function validateCntcSystem(form) {
    const rules = {
        insttId: {
            label: '기관ID',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        sysNm: {
            label: '시스템명',
            rules: {
                required: true,
                maxlength: 60
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 연계기관관리 - 연계서비스 validation
function validateCntcService(form) {
    const rules = {
        insttId: {
            label: '기관ID',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        sysId: {
            label: '시스템ID',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        svcNm: {
            label: '서비스명',
            rules: {
                required: true,
                maxlength: 60
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 기업회원관리 validation
function validateEntrprsManageVO(form) {
    const rules = {
        entrprsmberId: {
            label: '기업회원아이디',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        cmpnyNm: {
            label: '회사명',
            rules: {
                required: true,
                maxlength: 50
            }
        },
        entrprsMberPassword: {
            label: '비밀번호',
            rules: {
                required: true,
                password1: true,
                pwdCheckSeries: true,
                pwdCheckRepeat: true,
                pwdCheckComb3: true
            }
        },
        entrprsMberPasswordHint: {
            label: '비밀번호힌트',
            rules: {
                required: true
            }
        },
        entrprsMberPasswordCnsr: {
            label: '비밀번호정답',
            rules: {
                required: true,
                maxlength: 100
            }
        },
        bizrno: {
            label: '사업자등록번호',
            rules: {
                required: true
            }
        },
        areaNo: {
            label: '회사지역번호',
            rules: {
                required: true,
                maxlength: 4,
                integer: true
            }
        },
        entrprsMiddleTelno: {
            label: '회사중간전화번호',
            rules: {
                required: true,
                maxlength: 4,
                integer: true
            }
        },
        entrprsEndTelno: {
            label: '회사마지막전화번호',
            rules: {
                required: true,
                maxlength: 4,
                integer: true
            }
        },
        zip: {
            label: '우편번호',
            rules: {
                required: true
            }
        },
        adres: {
            label: '주소',
            rules: {
                required: true,
                maxlength: 100
            }
        },
        applcntNm: {
            label: '신청자이름',
            rules: {
                required: true
            }
        },
        applcntEmailAdres: {
            label: '신청자이메일주소',
            rules: {
                required: true,
                email: true,
                maxlength: 50
            }
        },
        entrprsMberSttus: {
            label: '사용자상태코드',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 연계메시지관리 - 연계메시지 validation
function validateCntcMessage(form) {
    const rules = {
        cntcMessageNm: {
            label: '연계메시지명',
            rules: {
                required: true,
                maxlength: 60
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 연계메시지관리 - 연계메시지항목 validation
function validateCntcMessageItem(form) {
    const rules = {
        cntcMessageId: {
            label: '연계메시지ID',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        itemNm: {
            label: '항목명',
            rules: {
                required: true,
                maxlength: 100
            }
        },
        itemType: {
            label: '항목타입',
            rules: {
                maxlength: 20
            }
        },
        itemLt: {
            label: '항목길이',
            rules: {
                integer: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 기업회원관리 비밀번호변경 validation
function validatePasswordManageVO(form) {
    const rules = {
        password: {
            label: '비밀번호',
            rules: {
                required: true,
                password1: true,
                pwdCheckSeries: true,
                pwdCheckRepeat: true,
                pwdCheckComb3: true
            }
        },
        oldPassword: {
            label: '이전비밀번호',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 마이페이지관리
function validateIndvdlPgeVO(form){
	const rules = {
		cntntsNm: {
			label: '컨텐츠명',
			rules: {
				required: true,
				maxlength: 100
			}
		},
		cntcUrl: {
			label: '컨텐츠URL',
			rules: {
				required: true,
				maxlength: 255
			}
		},
		cntntsLinkUrl: {
			label: '미리보기URL',
			rules: {
				required: true,
				maxlength: 1000
			}
		},
		cntntsDc: {
			label: '컨텐츠설명',
			rules: {
				required: true,
				maxlength: 250
					}
				},
		cntntsUseAt: {
			label: '사용여부',
			rules: {
				required: true,
			}
		}
	};
	return EgovValidation.validateForm(form, rules);
}

// 약관관리
function validateStplatManageVO(form){
	const rules = {
			useStplatNm: {
				label: '약관명',
				rules: {
					required: true,
					maxlength: 100
				}
			},
			useStplatCn: {
				label: '약관내용',
				rules: {
					required: true
				}
			},
			infoProvdAgreCn: {
				label: '정보제공동의내용',
				rules: {
					required: true
				}
			}
		};
		return EgovValidation.validateForm(form, rules);
}

// 저작권보호장책확인
function validateCpyrhtPrtcPolicyVO(form){
	const rules = {
			cpyrhtPrtcPoliCn: {
				label: '저작권보호정책내용',
				rules: {
					required: true,
					maxlength: 2500
				}
			}
		};
		return EgovValidation.validateForm(form, rules);
}

// 개인정보보호정책확인
function validateIndvdlInfoPolicy(form){
	const rules = {
			indvdlInfoNm: {
				label: '개인정보보호정책명',
				rules: {
					required: true,
					maxlength: 255
				}
			},
			indvdlInfoYn: {
				label: '동의여부',
				rules: {
					required: true
				}
			},
			indvdlInfoDc: {
				label: '개인정보보호정책내용',
				rules: {
					required: true,
					maxlength: 2500
				}
			}
		};
		return EgovValidation.validateForm(form, rules);
}

//도움말
function validateHpcmVO(form){
	const rules = {
			hpcmSeCode: {
				label: '도움말구분',
				rules: {
					required: true
				}
			},
			hpcmDf: {
				label: '도움말정의',
				rules: {
					required: true,
					maxlength: 1000
				}
			},
			hpcmDc: {
				label: '도움말설명',
				rules: {
					required: true,
					maxlength: 2500
				}
			}
		};
		return EgovValidation.validateForm(form, rules);
}

// 용어사전
function validateWordDicaryVO(form){
	const rules = {
		wordNm: {
			label: '용어명',
			rules: {
				required: true,
				maxlength: 250
			}
		},
		engNm: {
			label: '영문명',
			rules: {
				required: true,
				maxlength: 60,
				english: true
			}
		},
		wordDc: {
			label: '용어설명',
			rules: {
				required: true,
				maxlength: 2500
			}
		},
		synonm: {
			label: '동의어',
			rules: {
				maxlength: 100
			}
		}
	};
	return EgovValidation.validateForm(form, rules);
}

// QnA관리 mode=question or answer
function validateQnaVO(form){
	
	const mode = form.mode ? form.mode.value.trim() : 'question'; //질문자 입력시
	let rules = {};
	if(mode === 'question'){
		Object.assign(rules,{
					wrterNm: {
						label: '작성자명',
						rules: {
							required: true,
							maxlength: 20
						}
					},
					areaNo: {
						label: '지역번호',
						rules: {
							required: true,
							maxlength: 4,
							integer: true
						}
					},
					middleTelno: {
						label: '중간전화번호',
						rules: {
							required: true,
							maxlength: 4,
							integer: true
						}
					},
					endTelno: {
						label: '끝전화번호',
						rules: {
							required: true,
							maxlength: 4,
							integer: true
						}
					},
					emailAdres: {
						label: '이메일주소',
						rules: {
							email: true,
							maxlength: 50
						}
					},
					qestnSj: {
						label: '질문제목',
						rules: {
							required: true,
							maxlength: 250
						}
					},
					qestnCn: {
						label: '질문내용',
						rules: {
							required: true,
							maxlength: 2500
						}
					}
			});
	}
	else if (mode === 'answer'){
		Object.assign(rules,{
			qnaProcessSttusCode: {
				label: '답변상태코드',
				rules: {
							required: true
						}
				},
			qestnCn: {
				label: '답변내용',
				rules: {
							required: true,
							maxlength: 2500
						}
				}
				});
	}
	return EgovValidation.validateForm(form, rules);
}

//행정전문용어사전관리
function validateAdministrationWordVO(form){
	const rules = {
		administWordNm: {
			label: '행정용어명',
			rules: {
				required: true,
				maxlength: 255
			}
		},
		administWordEngNm: {
			label: '행정용어영문명',
			rules: {
				required: true,
				maxlength: 255,
				english: true
			}
		},
		administWordAbrv: {
			label: '행정용어약어명',
			rules: {
				maxlength: 255,
				required: true
			}
		},
		themaRelm: {
			label: '주제영역',
			rules: {
				required: true
			}
		},
		wordDomn: {
			label: '용어구분',
			rules: {
				required: true
			}			
		},
		administWordDf: {
			label: '행정용어정의',
			rules: {
				maxlength: 2500
			}
		},
		administWordDc: {
			label: '행정용어설명',
			rules: {
				maxlength: 2500
			}
		}	
	};
	return EgovValidation.validateForm(form, rules);
}

//온라인메뉴얼

function validateOnlineManualVO(form){
	const rules = {
		onlineMnlNm: {
			label: '온라인메뉴얼명',
			rules: {
				required: true,
				maxlength: 255
			}
		},
		onlineMnlSeCode: {
			label: '구분',
			rules: {
				required: true
			}
		},
		onlineMnlDf: {
			label: '온라인메뉴얼정의',
			rules: {
				required: true
			}
		},
		onlineMnlDc: {
			label: '온라인메뉴얼설명',
			rules: {
				required: true
			}
		}
	};
	return EgovValidation.validateForm(form, rules);
}

//설문템플릿관리 등록


function validateQustnrTmplatManageVO(form) {
    const rules = {
        qestnrTmplatTy: {
            label: '템플릿유형',
            rules: {
                required: true,
                maxlength: 100
            }
        },
        qestnrTmplatCn: {
            label: '템플릿설명',
            rules: {
                required: true,
                maxlength: 1000
            }
        },
        qestnrTmplatCours: {
            label: '템플릿 파일(경로)',
            rules: {
                required: true,
                maxlength: 100
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}



function validateCnsltManageVO(form) {
    const rules = {
        wrterNm: {
            label: '작성자명',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        writngPassword: {
            label: '작성 비밀번호',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        areaNo: {
            label: '전화번호(지역)',
            rules: {
                required: true,
                maxlength: 4,
                integer: true
            }
        },
        middleTelno: {
            label: '전화번호(국번)',
            rules: {
                required: true,
                maxlength: 4,
                integer: true
            }
        },
        endTelno: {
            label: '전화번호(지번)',
            rules: {
                required: true,
                maxlength: 4,
                integer: true
            }
        },
        firstMoblphonNo: {
            label: '휴대폰번호(앞번)',
            rules: {
                maxlength: 4,
                integer: true
            }
        },
        middleMbtlnum: {
            label: '휴대폰번호(국번)',
            rules: {
                maxlength: 4,
                integer: true
            }
        },
        endMbtlnum: {
            label: '휴대폰번호(지번)',
            rules: {
                maxlength: 4,
                integer: true
            }
        },
        emailAdres: {
            label: '이메일',
            rules: {
                email: true,
                maxlength: 50
            }
        },
        cnsltSj: {
            label: '상담제목',
            rules: {
                required: true,
                maxlength: 250
            }
        },
        cnsltCn: {
            label: '상담내용',
            rules: {
                required: true,
                maxlength: 2500
            }
        },
        managtCn: {
            label: '답변내용',
            rules: {
                required: true,
                maxlength: 2500
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 시스템연계관리 validation
function validateSystemCntc(form) {
    const rules = {
        cntcNm: {
            label: '연계명',
            rules: {
                required: true,
                maxlength: 60
            }
        },
        cntcType: {
            label: '연계유형',
            rules: {
                required: true,
                maxlength: 60
            }
        },
        provdInsttId: {
            label: '제공기관',
            rules: {
                required: true
            }
        },
        provdSysId: {
            label: '제공시스템',
            rules: {
                required: true
            }
        },
        provdSvcId: {
            label: '제공서비스',
            rules: {
                required: true
            }
        },
        requstInsttId: {
            label: '요청기관',
            rules: {
                required: true
            }
        },
        requstSysId: {
            label: '요청시스템',
            rules: {
                required: true
            }
        },
        validBeginDe: {
            label: '유효시작일자',
            rules: {
                required: true
            }
        },
        useAt: {
            label: '사용여부',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 지식맵(조직) validation
function validateMapTeam(form) {
    const rules = {
        orgnztId: {
            label: '조직ID',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        orgnztNm: {
            label: '조직명',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        knoUrl: {
            label: '지식URL',
            rules: {
                required: true,
                maxlength: 100
            }
        },
        clYmd: {
            label: '분류일자',
            rules: {
                required: true,
                maxlength: 10
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 지식맵(유형) validation
function validateMapMaterial(form) {
    const rules = {
        orgnztId: {
            label: '조직ID',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        knoTypeCd: {
            label: '지식유형코드',
            rules: {
                required: true,
                maxlength: 3
            }
        },
        knoTypeNm: {
            label: '지식유형명',
            rules: {
                required: true,
                maxlength: 100
            }
        },
        knoUrl: {
            label: '지식URL',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        clYmd: {
            label: '분류일자',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 지식정보제공 validation
function validateRequestOffer(form) {
    const rules = {
        orgnztId: {
            label: '조직명',
            rules: {
                required: true
            }
        },
        knoTypeCd: {
            label: '지식유형',
            rules: {
                required: true
            }
        },
        knoNm: {
            label: '지식명',
            rules: {
                required: true,
                maxlength: 60
            }
        },
        knoCn: {
            label: '지식내용',
            rules: {
                required: true,
                maxlength: 2500
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 지식전문가관리 validation
function validateKnoSpecialist(form) {
    const rules = {
        orgnztId: {
            label: '조직명',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        knoTypeCd: {
            label: '지식유형',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        speId: {
            label: '전문가',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        appTypeCd: {
            label: '등급',
            rules: {
                required: true,
                maxlength: 1
            }
        },
        speExpCn: {
            label: '전문가설명',
            rules: {
                required: true,
                maxlength: 100
            }
        },
        speConfmDe: {
            label: '승인일자',
            rules: {
                required: true,
                maxlength: 10
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 개인지식관리 validation
function validateKnoPersonal(form) {
    const rules = {
        orgnztId: {
            label: '조직명',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        knoTypeCd: {
            label: '지식유형',
            rules: {
                required: true,
                maxlength: 3
            }
        },
        knoNm: {
            label: '지식명',
            rules: {
                required: true,
                maxlength: 60
            }
        },
        knoCn: {
            label: '지식내용',
            rules: {
                required: true,
                maxlength: 2500
            }
        },
        colYmd: {
            label: '수집일자',
            rules: {
                required: true,
                maxlength: 10
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}
//  회의관리 validation
function validateMeetingManageVO(form) {
    const rules = {
        mtgNm: {
            label: '회의제목',
            rules: {
                required: true,
                maxlength: 250
            }
        },
        mtgMtrCn: {
            label: '회의안건 내용',
            rules: {
                required: true,
                maxlength: 1000
            }
        },
        mtgSn: {
            label: '회의순서',
            rules: {
                required: true,
                maxlength: 10,
                integer: true
            }
        },
        mtgCo: {
            label: '회의회차',
            rules: {
                required: true,
                maxlength: 5,
                integer: true
            }
        },
        mtgDe: {
            label: '회의일자',
            rules: {
                required: true
            }
        },
        mtgPlace: {
            label: '회의장소',
            rules: {
                required: true,
                maxlength: 250
            }
        },
        mtgBeginHH: {
            label: '회의시작시간(시간)',
            rules: {
                required: true
            }
        },
        mtgBeginMM: {
            label: '회의시작시간(분)',
            rules: {
                required: true
            }
        },
        mtgEndHH: {
            label: '회의종료시간(시간)',
            rules: {
                required: true
            }
        },
        mtgEndMM: {
            label: '회의종료시간(분)',
            rules: {
                required: true
            }
        },
        readngBeginDe: {
            label: '열람 개시일',
            rules: {
                required: true
            }
        },
        mtgResultCn: {
            label: '회의결과 내용',
            rules: {
                maxlength: 1000
            }
        },
        etcMatter: {
            label: '기타사항',
            rules: {
                maxlength: 1000
            }
        },
        nonatdrnCo: {
            label: '불참석자수',
            rules: {
                integer: true
            }
        },
        atdrnCo: {
            label: '참석자수',
            rules: {
                integer: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}




// 공휴일관리(달력) Validation
function validateRestde(form) {
    const rules = {
        restdeDe: {
            label: '휴일일자',
            rules: {
                required: true,
                maxlength: 8
            }
        },
        restdeNm: {
            label: '휴일명',
            rules: {
                required: true,
                maxlength: 50
            }
        },
        restdeDc: {
            label: '휴일설명',
            rules: {
                required: true,
                maxlength: 200
            }
        },
        restdeSeCode: {
            label: '휴일구분',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 송수신모니터링 Validation
function validateTrsmrcvMntrng(form) {
    const rules = {
        cntcId: {
            label: '연계ID',
            rules: {
                required: true,
                maxlength: 8
            }
        },
        testClassNm: {
            label: '테스트클래스명',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        mngrNm: {
            label: '관리자명',
            rules: {
                required: true,
                maxlength: 60
            }
        },
        mngrEmailAddr: {
            label: '관리자이메일주소',
            rules: {
                required: true,
                maxlength: 50,
                email: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// DB서비스모니터링 Validation
function validateDbMntrng(form) {
    const rules = {
        dataSourcNm: {
            label: '데이타소스명',
            rules: {
                required: true,
                maxlength: 60
            }
        },
        serverNm: {
            label: '서버명',
            rules: {
                required: true,
                maxlength: 60
            }
        },
        dbmsKind: {
            label: 'DBMS종류',
            rules: {
                required: true
            }
        },
        ceckSql: {
            label: '체크SQL',
            rules: {
                required: true,
                maxlength: 250
            }
        },
        mngrNm: {
            label: '관리자명',
            rules: {
                required: true,
                maxlength: 60
            }
        },
        mngrEmailAddr: {
            label: '관리자이메일주소',
            rules: {
                required: true,
                maxlength: 50,
                email: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// HTTP서비스모니터링 Validation
function validateHttpMon(form) {
    const rules = {
        webKind: {
            label: '웹서비스종류',
            rules: {
                required: true,
                maxlength: 30
            }
        },
        siteUrl: {
            label: '시스템URL',
            rules: {
                required: true,
                maxlength: 100
            }
        },
        mngrNm: {
            label: '관리자명',
            rules: {
                required: true,
                maxlength: 60
            }
        },
        mngrEmailAddr: {
            label: '관리자이메일',
            rules: {
                required: true,
                maxlength: 60,
                email: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 온라인 POLL 관리 validation
function validateOnlinePollManage(form) {
    const rules = {
        pollNm: {
            label: 'POLL명',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        pollBeginDe: {
            label: 'POLL 시작일자',
            rules: {
                required: true
            }
        },
        pollEndDe: {
            label: 'POLL 종료일자',
            rules: {
                required: true
            }
        },
        pollKindCode: {
            label: 'POLL구분',
            rules: {
                required: true
            }
        },
        pollDsuseYn: {
            label: 'POLL페기유무',
            rules: {
                required: true
            }
        },
        pollAutoDsuseYn: {
            label: 'POLL자동페기유무',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 프로세스모니터링 Validation
function validateProcessMonVO(form) {
    const rules = {
        processNm: {
            label: '프로세스명',
            rules: {
                required: true,
                maxlength: 30
            }
        },
        mngrNm: {
            label: '관리자명',
            rules: {
                required: true,
                maxlength: 60
            }
        },
        mngrEmailAddr: {
            label: '관리자이메일주소',
            rules: {
                required: true,
                maxlength: 60,
                email: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 네트워크서비스모니터링 Validation
function validateNtwrkSvcMntrngVO(form) {
    const rules = {
        sysIp1: {
            label: '시스템IP1',
            rules: {
                required: true,
                maxlength: 3
            }
        },
        sysIp2: {
            label: '시스템IP2',
            rules: {
                required: true,
                maxlength: 3
            }
        },
        sysIp3: {
            label: '시스템IP3',
            rules: {
                required: true,
                maxlength: 3
            }
        },
        sysIp4: {
            label: '시스템IP4',
            rules: {
                required: true,
                maxlength: 3
            }
        },
        sysPort: {
            label: '시스템포트',
            rules: {
                required: true,
                maxlength: 5
            }
        },
        sysNm: {
            label: '시스템명',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        mngrNm: {
            label: '관리자명',
            rules: {
                required: true,
                maxlength: 60
            }
        },
        mngrEmailAddr: {
            label: '관리자이메일주소',
            rules: {
                required: true,
                maxlength: 50,
                email: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 파일시스템 모니터링 validation
function validateFileSysMntrngVO(form) {
    const rules = {
        fileSysNm: {
            label: '파일시스템명',
            rules: {
                required: true,
                maxlength: 60
            }
        },
        fileSysManageNm: {
            label: '파일시스템관리명',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        fileSysMg: {
            label: '파일시스템크기',
            rules: {
                required: true
            }
        },
        fileSysThrhld: {
            label: '파일시스템임계치',
            rules: {
                required: true
            }
        },
        mngrNm: {
            label: '관리자명',
            rules: {
                required: true,
                maxlength: 60
            }
        },
        mngrEmailAddr: {
            label: '관리자이메일주소',
            rules: {
                required: true,
                maxlength: 50,
                email: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 프록시서비스 Validation
function validateProxySvc(form) {
    const rules = {
        proxyNm: {
            label: '프록시명',
            rules: {
                required: true,
                maxlength: 30
            }
        },
        proxyIp: {
            label: '프록시IP',
            rules: {
                required: true,
                maxlength: 23
            }
        },
        proxyPort: {
            label: '프록시Port',
            rules: {
                required: true,
                maxlength: 10,
                integer: true
            }
        },
        trgetSvcNm: {
            label: '대상서비스명',
            rules: {
                required: true,
                maxlength: 30
            }
        },
        svcIp: {
            label: '서비스IP',
            rules: {
                required: true,
                maxlength: 23
            }
        },
        svcPort: {
            label: '서비스Port',
            rules: {
                required: true,
                maxlength: 10,
                integer: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 파일동기화(동기화대상 서버) Validation
function validateSynchrnServer(form) {
    const rules = {
        serverNm: {
            label: '서버명',
            rules: {
                required: true,
                maxlength: 30
            }
        },
        serverIp: {
            label: '서버IP',
            rules: {
                required: true,
                maxlength: 23
            }
        },
        serverPort: {
            label: '서버Port',
            rules: {
                required: true,
                maxlength: 10,
                integer: true
            }
        },
        ftpId: {
            label: 'FTP ID',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        ftpPassword: {
            label: 'FTP 비밀번호',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        synchrnLc: {
            label: '동기화 위치',
            rules: {
                required: true,
                maxlength: 255
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 설문항목 Validation
function validateQustnrItemManageVO(form) {
    const rules = {
        iemSn: {
            label: '항목순번',
            rules: {
                required: true,
                maxlength: 5,
                integer: true
            }
        },
        iemCn: {
            label: '항목내용',
            rules: {
                required: true,
                maxlength: 1000
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 설문문항 Validation
function validateQustnrQestnManageVO(form) {
    const rules = {
        qestnSn: {
            label: '질문순번',
            rules: {
                required: true,
                maxlength: 10,
                integer: true
            }
        },
        qestnTyCode: {
            label: '질문유형',
            rules: {
                required: true
            }
        },
        qestnCn: {
            label: '질문내용',
            rules: {
                required: true,
                maxlength: 2500
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}
// 응답자정보 Validation
function validateQustnrRespondManageVO(form) {
    const rules = {
        sexdstnCode: {
            label: '성별',
            rules: {
                required: true
            }
        },
        occpTyCode: {
            label: '직업',
            rules: {
                required: true
            }
        },
        respondNm: {
            label: '응답자명',
            rules: {
                required: true,
                maxlength: 50
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}


// annvrsryManage validation
function validateAnnvrsryManage(form) {
    const rules = {
        usid: {
            label: '사용자 ID',
            rules: {
                required: true
            }
        },
        annvrsrySe: {
            label: '기념일구분',
            rules: {
                required: true
            }
        },
        annvrsryDe: {
            label: '기념일자',
            rules: {
                required: true,
                maxlength: 10
            }
        },
        cldrSe: {
            label: '양력/음력구분',
            rules: {
                required: true
            }
        },
        annvrsryNm: {
            label: '기념일명',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        memo: {
            label: '메모',
            rules: {
                required: true,
                maxlength: 2500
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// bndtCeckManage validation
function validateBndtCeckManage(form) {
    const rules = {
        bndtCeckSe: {
            label: '당직체크구분',
            rules: {
                required: true,
                maxlength: 10
            }
        },
        bndtCeckCd: {
            label: '당직체크코드',
            rules: {
                required: true,
                maxlength: 10
            }
        },
        bndtCeckCdNm: {
            label: '당직체크코드명',
            rules: {
                required: true,
                maxlength: 100
            }
        },
        useAt: {
            label: '사용여부',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// bndtDiary validation
function validateBndtDiary(form) {
    const rules = {
        bndtId: {
            label: '당직ID',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        bndtDe: {
            label: '당직일자',
            rules: {
                required: true,
                maxlength: 8
            }
        },
        bndtCeckSe: {
            label: '당직체크구분',
            rules: {
                required: true,
                maxlength: 10
            }
        },
        bndtCeckCd: {
            label: '당직체크코드',
            rules: {
                required: true,
                maxlength: 10
            }
        },
        chckSttus: {
            label: '점검상태',
            rules: {
                maxlength: 2000
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// bndtManage validation
function validateBndtManage(form) {
    const rules = {
        bndtId: {
            label: '당직ID',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        bndtDe: {
            label: '당직일자',
            rules: {
                required: true
            }
        },
        remark: {
            label: '비고',
            rules: {
                maxlength: 2500
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// ctsnnManage validation
function validateCtsnnManage(form) {
    const rules = {
        usNm: {
            label: '신청자',
            rules: {
                required: true
            }
        },
        ctsnnNm: {
            label: '경조명',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        ctsnnCd: {
            label: '경조코드',
            rules: {
                required: true
            }
        },
        occrrDe: {
            label: '발생일자',
            rules: {
                required: true,
                maxlength: 10
            }
        },
        trgterNm: {
            label: '대상자명',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        relate: {
            label: '관계',
            rules: {
                required: true
            }
        },
        brth: {
            label: '생년월일',
            rules: {
                required: true,
                maxlength: 10
            }
        },
        sanctnDtNm: {
            label: '승인권자',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// eventCmpgnVO validation
function validateEventCmpgnVO(form) {
    const rules = {
        eventTyCode: {
            label: '행사유형',
            rules: {
                required: true
            }
        },
        eventCn: {
            label: '행사내용',
            rules: {
                required: true,
                maxlength: 1000
            }
        },
        eventSvcBeginDe: {
            label: '행사시작일',
            rules: {
                required: true
            }
        },
        eventSvcEndDe: {
            label: '행사종료일',
            rules: {
                required: true
            }
        },
        svcUseNmprCo: {
            label: '참가인원 수',
            rules: {
                maxlength: 10,
                integer: true
            }
        },
        prparetgCn: {
            label: '준비물 내용',
            rules: {
                maxlength: 1000
            }
        },
        eventConfmAt: {
            label: '승인여부',
            rules: {
                required: true
            }
        },
        eventConfmDe: {
            label: '승인일',
            rules: {
                required: true
            }
        }
    };
    if (!EgovValidation.validateForm(form, rules)) {
        return false;
    }
    // 행사시작일이 행사종료일보다 이전인지 검증 (시작일 <= 종료일)
    const beginEl = form.elements['eventSvcBeginDe'];
    const endEl = form.elements['eventSvcEndDe'];
    const begin = (beginEl && beginEl.value) ? beginEl.value.trim() : '';
    const end = (endEl && endEl.value) ? endEl.value.trim() : '';
    if (begin && end && begin > end) {
        alert(EgovValidation.messages.dateRange);
        if (beginEl) {
            beginEl.focus();
        }
        return false;
    }
    return true;
}

// TnextrlHrVO validation (외부인사정보 - 연락처/이메일 EgovValidation.js 방식)
function validateTnextrlHrVO(form) {
    const rules = {
        eventId: {
            label: '행사/이벤트/캠페인',
            rules: {
                required: true
            }
        },
        sexdstnCode: {
            label: '성별',
            rules: {
                required: true
            }
        },
        extrlHrNm: {
            label: '외부인사명',
            rules: {
                required: true,
                maxlength: 70
            }
        },
        brth: {
            label: '생년월일',
            rules: {
                required: true
            }
        },
        occpTyCode: {
            label: '직업유형',
            rules: {
                required: true
            }
        },
        areaNo: {
            label: '연락처(지역번호)',
            rules: {
                required: true,
                maxlength: 4,
                integer: true
            }
        },
        middleTelno: {
            label: '연락처(중간번호)',
            rules: {
                required: true,
                maxlength: 4,
                integer: true
            }
        },
        endTelno: {
            label: '연락처(끝번호)',
            rules: {
                required: true,
                maxlength: 4,
                integer: true
            }
        },
        emailAdres: {
            label: '이메일주소',
            rules: {
                required: true,
                maxlength: 70,
                email: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// eventAtdrn validation
function validateEventAtdrn(form) {
    const rules = {
        eventId: {
            label: '행사ID',
            rules: {
                required: true
            }
        },
        sanctnerId: {
            label: '결재자ID',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// eventManage validation (EgovEventReqstRegist / EgovEventReqstUpdt 동일 방식)
function validateEventManage(form) {
    const rules = {
        eventSe: {
            label: '행사구분',
            rules: {
                required: true
            }
        },
        eventNm: {
            label: '행사명',
            rules: {
                required: true,
                maxlength: 60
            }
        },
        eventPurps: {
            label: '행사목적',
            rules: {
                required: true,
                maxlength: 200
            }
        },
        eventBeginDe: {
            label: '행사시작일자',
            rules: {
                required: true
            }
        },
        eventEndDe: {
            label: '행사종료일자',
            rules: {
                required: true
            }
        },
        eventAuspcInsttNm: {
            label: '행사주최기관명',
            rules: {
                required: true,
                maxlength: 60
            }
        },
        eventMngtInsttNm: {
            label: '행사주관기관명',
            rules: {
                required: true,
                maxlength: 60
            }
        },
        eventPlace: {
            label: '행사장소',
            rules: {
                required: true,
                maxlength: 200
            }
        },
        eventCn: {
            label: '행사내용',
            rules: {
                required: true,
                maxlength: 1000
            }
        },
        ctOccrrncAt: {
            label: '비용발생여부',
            rules: {
                required: true
            }
        },
        partcptCt: {
            label: '참가비용',
            rules: {
                required: true,
                number: true,
                min: 0
            }
        },
        psncpa: {
            label: '정원',
            rules: {
                required: true,
                number: true,
                min: 1
            }
        },
        rceptBeginDe: {
            label: '접수시작일자',
            rules: {
                required: true
            }
        },
        rceptEndDe: {
            label: '접수종료일자',
            rules: {
                required: true
            }
        },
        refrnUrl: {
            label: '참조URL',
            rules: {
                urlFormat: true
            }
        }
    };
    if (!EgovValidation.validateForm(form, rules)) {
        return false;
    }
    const getVal = function(name) {
        const el = form.elements[name];
        return (el && el.value) ? el.value.trim() : '';
    };
    const eventBeginDe = getVal('eventBeginDe');
    const eventEndDe = getVal('eventEndDe');
    const rceptBeginDe = getVal('rceptBeginDe');
    const rceptEndDe = getVal('rceptEndDe');
    if (eventBeginDe && eventEndDe && eventBeginDe > eventEndDe) {
        alert(EgovValidation.messages.dateRangeEvent);
        if (form.elements['eventBeginDe']) form.elements['eventBeginDe'].focus();
        return false;
    }
    if (rceptBeginDe && rceptEndDe && rceptBeginDe > rceptEndDe) {
        alert(EgovValidation.messages.dateRangeRcept);
        if (form.elements['rceptBeginDe']) form.elements['rceptBeginDe'].focus();
        return false;
    }
    if (rceptEndDe && eventBeginDe && rceptEndDe > eventBeginDe) {
        alert(EgovValidation.messages.dateRangeRceptEnd);
        if (form.elements['rceptEndDe']) form.elements['rceptEndDe'].focus();
        return false;
    }
    const ctOccrrncAtEl = form.elements['ctOccrrncAt'];
    const isFee = ctOccrrncAtEl && Array.isArray(ctOccrrncAtEl) && Array.from(ctOccrrncAtEl).some(function(r) { return r.checked && r.value === '2'; });
    if (isFee) {
        const partcptCtVal = parseFloat(getVal('partcptCt'));
        if (!isNaN(partcptCtVal) && partcptCtVal <= 0) {
            alert(EgovValidation.messages.partcptCtZero);
            if (form.elements['partcptCt']) form.elements['partcptCt'].focus();
            return false;
        }
    }
    const psncpaVal = parseFloat(getVal('psncpa'));
    if (!isNaN(psncpaVal) && psncpaVal <= 0) {
        alert(EgovValidation.messages.psncpaZero);
        if (form.elements['psncpa']) form.elements['psncpa'].focus();
        return false;
    }
    return true;
}

// intnetSvcGuidance validation
function validateIntnetSvcGuidance(form) {
    const rules = {
        intnetSvcNm: {
            label: '인터넷서비스 명',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        intnetSvcDc: {
            label: '인터넷서비스 설명',
            rules: {
                required: true,
                maxlength: 200
            }
        },
        reflctAt: {
            label: '반영여부',
            rules: {
                required: true
            }
        },
        frstRegisterPnttm: {
            label: '등록일시',
            rules: {
                required: true,
                maxlength: 10
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// loginScrinImage validation
function validateLoginScrinImage(form) {
    const rules = {
        imageNm: {
            label: '이미지 명',
            rules: {
                required: true,
                maxlength: 10
            }
        },
        loginImage: {
            label: '이미지',
            rules: {
                required: true,
                maxlength: 30
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// mainImage validation (EgovMainImage.xml: mainImageVO - imageNm required/maxlength 10, image required)
function validateMainImage(form) {
    var rules = {
        imageNm: {
            label: '이미지명',
            rules: {
                required: true,
                maxlength: 10
            }
        },
		image: {
            label: '이미지',
            rules: {
                required: true
            }
        },
};
    return EgovValidation.validateForm(form, rules);
}

// mtgPlaceManage validation (개방시작/종료 시간 필수 + 개방시작 < 개방종료)
function validateMtgPlaceManage(form) {
    if (!form) return false;
    var opnBeginTm = form.opnBeginTm ? (form.opnBeginTm.value || '').trim() : '';
    var opnEndTm = form.opnEndTm ? (form.opnEndTm.value || '').trim() : '';
    if (opnBeginTm === '') {
        alert(EgovValidation.messages.mtgPlaceSelectStartTime || '개방 오픈 시간을 선택하세요.');
        if (form.opnBeginTm && form.opnBeginTm.focus) form.opnBeginTm.focus();
        return false;
    }
    if (opnEndTm === '') {
        alert(EgovValidation.messages.mtgPlaceSelectCloseTime || '개방 종료 시간을 선택하세요.');
        if (form.opnEndTm && form.opnEndTm.focus) form.opnEndTm.focus();
        return false;
    }
    var beginHour = parseInt(opnBeginTm.substring(0, 2), 10);
    var endHour = parseInt(opnEndTm.substring(0, 2), 10);
    if (beginHour >= endHour) {
        alert(EgovValidation.messages.mtgPlaceCheckOpenTime || '개방오픈시간이 개방종료시간보다 늦거나 같습니다. 개방시간을 확인하세요.');
        if (form.opnBeginTm && form.opnBeginTm.focus) form.opnBeginTm.focus();
        return false;
    }
    var rules = {
        mtgPlaceNm: {
            label: '회의실명',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        opnBeginTm: {
            label: '개방시작시간',
            rules: {
                required: true
            }
        },
        opnEndTm: {
            label: '개방종료시간',
            rules: {
                required: true
            }
        },
        aceptncPosblNmpr: {
            label: '수용가능인원',
            rules: {
                required: true
            }
        },
        lcSe: {
            label: '위치선택',
            rules: {
                required: true
            }
        },
        lcDetail: {
            label: '위치상세',
            rules: {
                required: true,
                maxlength: 200
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// mtgPlaceResve validation (dplactCeck 중복확인 후 예약 가능, 시작<종료, 개방시간 내)
function validateMtgPlaceResve(form) {
    function toMinutesResve(s) {
        if (!s) return 0;
        s = String(s).trim();
        if (s.indexOf(':') !== -1) {
            var p = s.split(':');
            return parseInt(p[0], 10) * 60 + (parseInt(p[1], 10) || 0);
        }
        if (s.length >= 4) {
            return parseInt(s.substring(0, 2), 10) * 60 + parseInt(s.substring(2, 4), 10);
        }
        if (s.length === 3) {
            return parseInt(s.substring(0, 1), 10) * 60 + parseInt(s.substring(1, 3), 10);
        }
        return 0;
    }
    if (!form) return false;
    var dplactCeckEl = form.dplactCeck || form.elements['dplactCeck'];
    if (dplactCeckEl) {
        var val = (dplactCeckEl.value || '').trim();
        if (val === '') {
            alert(EgovValidation.messages.mtgPlaceResveDplactCeck || '회의실 예약 중복확인을 한 후 회의실 예약을 해주세요.');
            return false;
        }
        if (val === 'N') {
            alert(EgovValidation.messages.mtgPlaceResveReserve || '이미 회의실이 예약되어 있습니다.');
            return false;
        }
    }
    var beginTm = (form.resveBeginTm && form.resveBeginTm.value) ? form.resveBeginTm.value : '';
    var endTm = (form.resveEndTm && form.resveEndTm.value) ? form.resveEndTm.value : '';
    if (beginTm && endTm) {
        var beginMin = toMinutesResve(beginTm);
        var endMin = toMinutesResve(endTm);
        if (beginMin >= endMin) {
            alert(EgovValidation.messages.mtgPlaceResveTimeStartBeforeEnd || '예약시작시간은 예약종료시간보다 이전이어야 하며 같을 수 없습니다.');
            return false;
        }
        var opnBegin = form.opnBeginTm || form.elements['opnBeginTm'];
        var opnEnd = form.opnEndTm || form.elements['opnEndTm'];
        if (opnBegin && opnEnd && opnBegin.value && opnEnd.value) {
            var opnBeginMin = toMinutesResve(opnBegin.value);
            var opnEndMin = toMinutesResve(opnEnd.value);
            if (beginMin < opnBeginMin || endMin > opnEndMin) {
                alert(EgovValidation.messages.mtgPlaceResveTimeWithinOpen || '예약시간은 회의실 개방시간 내여야 합니다.');
                return false;
            }
        }
    }
    var rules = {
        mtgSj: {
            label: '제목',
            rules: {
                required: true,
                maxlength: 100
            }
        },
        resveDe: {
            label: '예약일자',
            rules: {
                required: true,
                maxlength: 10
            }
        },
        resveBeginTm: {
            label: '예약시작시간',
            rules: {
                required: true
            }
        },
        resveEndTm: {
            label: '예약종료시간',
            rules: {
                required: true
            }
        },
        mtgCn: {
            label: '회의내용',
            rules: {
                maxlength: 200
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// 회의실 예약 폼: 개방시간(opnBeginTm~opnEndTm)에 맞게 예약시작/종료 시간 셀렉트 옵션만 활성화
function applyMtgPlaceResveOpenTimeFilter(form) {
    function toMin(s) {
        if (!s) return 0;
        s = String(s).trim();
        if (s.indexOf(':') !== -1) {
            var p = s.split(':');
            return parseInt(p[0], 10) * 60 + (parseInt(p[1], 10) || 0);
        }
        if (s.length >= 4) {
            return parseInt(s.substring(0, 2), 10) * 60 + parseInt(s.substring(2, 4), 10);
        }
        if (s.length === 3) {
            return parseInt(s.substring(0, 1), 10) * 60 + parseInt(s.substring(1, 3), 10);
        }
        return 0;
    }
    if (!form) form = document.getElementById('mtgPlaceResveVO') || document.forms['mtgPlaceResveVO'];
    if (!form) return;
    var opnBeginEl = form.opnBeginTm || form.elements['opnBeginTm'];
    var opnEndEl = form.opnEndTm || form.elements['opnEndTm'];
    if (!opnBeginEl || !opnEndEl) return;
    var opnBeginVal = (opnBeginEl.value || '').trim();
    var opnEndVal = (opnEndEl.value || '').trim();
    if (opnBeginVal === '' || opnEndVal === '') return;
    var opnBeginMin = toMin(opnBeginVal);
    var opnEndMin = toMin(opnEndVal);
    var selBegin = form.resveBeginTm || form.elements['resveBeginTm'];
    var selEnd = form.resveEndTm || form.elements['resveEndTm'];
    if (selBegin && selBegin.options) {
        var i, opt, m, firstEnabled = null;
        for (i = 0; i < selBegin.options.length; i++) {
            opt = selBegin.options[i];
            if (opt.value === '') { opt.disabled = true; continue; }
            m = toMin(opt.value);
            opt.disabled = (m < opnBeginMin || m >= opnEndMin);
            if (!opt.disabled && firstEnabled === null) firstEnabled = opt;
        }
        if (selBegin.selectedIndex >= 0 && selBegin.options[selBegin.selectedIndex].disabled && firstEnabled) {
            firstEnabled.selected = true;
        }
    }
    if (selEnd && selEnd.options) {
        var j, opt2, m2, firstEnabled2 = null;
        for (j = 0; j < selEnd.options.length; j++) {
            opt2 = selEnd.options[j];
            if (opt2.value === '') { opt2.disabled = true; continue; }
            m2 = toMin(opt2.value);
            opt2.disabled = (m2 <= opnBeginMin || m2 > opnEndMin);
            if (!opt2.disabled && firstEnabled2 === null) firstEnabled2 = opt2;
        }
        if (selEnd.selectedIndex >= 0 && selEnd.options[selEnd.selectedIndex].disabled && firstEnabled2) {
            firstEnabled2.selected = true;
        }
    }
}

// notification validation
function validateNotification(form) {
    // 사전알림간격: 최소 1개 선택 (체크박스 그룹)
    var bhNtfcIntrvl = form.bhNtfcIntrvl;
    if (bhNtfcIntrvl) {
        var arr = bhNtfcIntrvl.length != null ? bhNtfcIntrvl : [bhNtfcIntrvl];
        var checked = false;
        for (var i = 0; i < arr.length; i++) {
            if (arr[i].checked) { checked = true; break; }
        }
        if (!checked) {
            alert(EgovValidation.messages.bhNtfcIntrvlRequired || '사전알림간격 지정이 필요합니다.');
            return false;
        }
    } else {
        alert(EgovValidation.messages.bhNtfcIntrvlRequired || '사전알림간격 지정이 필요합니다.');
        return false;
    }
    var rules = {
        ntfcSj: {
            label: '제목',
            rules: {
                required: true,
                maxlength: 30
            }
        },
        ntfcCn: {
            label: '내용',
            rules: {
                required: true,
                maxlength: 50
            }
        },
        ntfcDate: {
            label: '알림일자',
            rules: {
                required: true
            }
        },
        ntfcHH: {
            label: '시',
            rules: {
                required: true
            }
        },
        ntfcMM: {
            label: '분',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// noteManage validation
function validateNoteManage(form) {
    const rules = {
        noteSj: {
            label: '쪽지관리',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        recptnEmpList: {
            label: '수신자 목록',
            rules: {
                required: true
            }
        },
        noteCn: {
            label: '쪽지 내용',
            rules: {
                required: true,
                maxlength: 4000
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// newsVO validation
function validateNewsVO(form) {
    const rules = {
        newsSj: {
            label: '뉴스제목',
            rules: {
                required: true,
                maxlength: 100
            }
        },
        newsCn: {
            label: '뉴스내용',
            rules: {
                required: true,
                maxlength: 1000
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// userAbsnceVO validation
function validateUserAbsnceVO(form) {
    const rules = {
        userId: {
            label: '사용자ID',
            rules: {
                required: true,
                maxlength: 20
            }
        },
        userNm: {
            label: '사용자명',
            rules: {
                required: true,
                maxlength: 60
            }
        },
        userAbsnceAt: {
            label: '부재여부',
            rules: {
                required: true
            }
        },
        frstRegisterPnttm: {
            label: '등록일시',
            rules: {
                required: true,
                maxlength: 10
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// popupManageVO validation
function validatePopupManageVO(form) {
    const rules = {
        popupTitleNm: {
            label: '팝업창명',
            rules: {
                required: true,
                maxlength: 1024
            }
        },
        fileUrl: {
            label: '팝업창 URL',
            rules: {
                required: true,
                maxlength: 1024
            }
        },
        popupWlc: {
            label: '팝업창 세로위치',
            rules: {
                required: true,
                maxlength: 10,
                integer: true
            }
        },
        popupHlc: {
            label: '팝업창 가로위치',
            rules: {
                required: true,
                maxlength: 10,
                integer: true
            }
        },
        popupHSize: {
            label: '팝업창 세로',
            rules: {
                required: true,
                maxlength: 10,
                integer: true
            }
        },
        popupWSize: {
            label: '팝업창 가로',
            rules: {
                required: true,
                maxlength: 10,
                integer: true
            }
        },
        stopVewAt: {
            label: '그만보기여부',
            rules: {
                required: true,
                maxlength: 1
            }
        },
        ntceAt: {
            label: '게시여부',
            rules: {
                required: true,
                maxlength: 1
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// recomendSiteVO validation
function validateRecomendSiteVO(form) {
    const rules = {
        recomendSiteNm: {
            label: '추천사이트명',
            rules: {
                required: true,
                maxlength: 100
            }
        },
        recomendSiteUrl: {
            label: '추천사이트URL',
            rules: {
                required: true,
                maxlength: 250
            }
        },
        recomendSiteDc: {
            label: '추천사이트설명',
            rules: {
                required: true,
                maxlength: 1000
            }
        },
        recomendResnCn: {
            label: '추천사유',
            rules: {
                required: true,
                maxlength: 1000
            }
        },
        confmDe: {
            label: '승인일자',
            rules: {
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// roughMapVO validation
function validateRoughMapVO(form) {
    const rules = {
        roughMapSj: {
            label: '약도제목',
            rules: {
                required: true
            }
        },
        roughMapAddress: {
            label: '약도주소',
            rules: {
                required: true
            }
        },
        infoWindow: {
            label: '약도표시',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// recentSrchwrd validation
function validateRecentSrchwrd(form) {
    const rules = {
        srchwrdManageNm: {
            label: '최근검색어관리명',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        srchwrdManageUrl: {
            label: '최근검색어관리 URL',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        srchwrdManageUseYn: {
            label: '사용자 검색 여부',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// rwardManage validation
function validateRwardManage(form) {
    const rules = {
        rwardManNm: {
            label: '포상자',
            rules: {
                required: true
            }
        },
        rwardCd: {
            label: '포상코드',
            rules: {
                required: true
            }
        },
        rwardNm: {
            label: '포상명',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        rwardDe: {
            label: '포상일자',
            rules: {
                required: true,
                maxlength: 10
            }
        },
        pblenCn: {
            label: '공적사항',
            rules: {
                required: true,
                maxlength: 1000
            }
        },
        sanctnDtNm: {
            label: '승인권자',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// siteVO validation
function validateSiteVO(form) {
    const rules = {
        siteNm: {
            label: '사이트명',
            rules: {
                required: true,
                maxlength: 100
            }
        },
        siteUrl: {
            label: '사이트URL',
            rules: {
                required: true,
                maxlength: 100
            }
        },
        siteDc: {
            label: '사이트설명',
            rules: {
                required: true,
                maxlength: 1000
            }
        },
        siteThemaClCode: {
            label: '사이트분류',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// twitterInfo validation
function validateTwitterInfo(form) {
    const rules = {
        twitterText: {
            label: '트위터  내용',
            rules: {
                required: true,
                maxlength: 140
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// unityLink validation
function validateUnityLink(form) {
    const rules = {
        unityLinkNm: {
            label: '통합링크명',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        unityLinkSeCode: {
            label: '통합링크그룹',
            rules: {
                required: true
            }
        },
        unityLinkUrl: {
            label: '통합링크URL',
            rules: {
                required: true,
                maxlength: 255
            }
        },
        unityLinkDc: {
            label: '통합링크설명',
            rules: {
                required: true,
                maxlength: 2500
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// vcatnManage validation
function validateVcatnManage(form) {
    const rules = {
        vcatnSe: {
            label: '휴가구분',
            rules: {
                required: true
            }
        },
        bgnde: {
            label: '시작일자',
            rules: {
                required: true,
                maxlength: 100
            }
        },
        endde: {
            label: '종료일자',
            rules: {
                required: true,
                maxlength: 100
            }
        },
        vcatnResn: {
            label: '휴가사유',
            rules: {
                required: true,
                maxlength: 200
            }
        },
        sanctnDtNm: {
            label: '승인권자',
            rules: {
                required: true
            }
        }
    };
    return EgovValidation.validateForm(form, rules);
}

// indvdlYrycManage validation
function validateIndvdlYrycManage(form, diffValueMessage) {
    const rules = {
        occrncYrycCo: {
            label: '발생연차개수',
            rules: {
                required: true,
                maxlength: 10,
                number: true,
                positive: true,
                min: 1
            }
        },
        useYrycCo: {
            label: '사용연차개수',
            rules: {
                required: true,
                maxlength: 10,
                number: true,
                min: 0
            }
        }
    };
    
    // 기본 필드 검증 수행
    if (!EgovValidation.validateForm(form, rules)) {
        return false;
    }
    
    // 잔여연차 검증: 발생연차가 사용연차보다 크거나 같아야 함
    const occrncYrycCo = parseFloat(form.occrncYrycCo.value);
    const useYrycCo = parseFloat(form.useYrycCo.value);
    
    if (!isNaN(occrncYrycCo) && !isNaN(useYrycCo)) {
        if (occrncYrycCo < useYrycCo) {
            const message = diffValueMessage || '잔여연차는 음수가 될 수 없습니다.';
            alert(message);
            form.useYrycCo.focus();
            return false;
        }
    }
    
    return true;
}


