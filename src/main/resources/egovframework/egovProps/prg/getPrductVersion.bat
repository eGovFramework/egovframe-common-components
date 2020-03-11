@rem Shell Name : getPrductVersion.bat                                         	
@rem Description : 시스템에 설치된 서버(WAS,WEB,MAIL서버)의 버전을 조회하는 Shell 	
@rem Modification Information                                                				
@rem                                                                    
@rem 수정일                      수정자                   수정내용
@rem -------      --------     ---------------------------
@rem 2009.02.11    박지욱                   최초 생성
@rem
@rem author 공통 서비스 개발팀 박지욱
@rem since 2009. 02. 11
@rem version 1.0
@rem see
@rem
@rem Copyright (C) 2009 by MOPAS  All right reserved.

IF "%1"=="WEBLOGIC" goto step1
IF "%1"=="JEUS" goto step2
IF "%1"=="JBOSS" goto step3
goto error

:step1
set CMMD1=java weblogic.Admin -username weblogic -password weblogic VERSION
set CMMD2=
goto done


:step2
set CMMD1=jeusadmin -version
set CMMD2=
goto done


:step3
set CMMD1=twiddle.bat -s localhost:1099 -u admin -p admin get "jboss.system:type=Server"
set CMMD2=find "Version="
goto done

:error
@echo Usage: getPrductVersion 서버이름
@echo        example) getPrductVersion WEBLOGIC

:done
%CMMD1%|%CMMD2%