# Shell Name : getPrductVersion.sh                                           	
# Description : 시스템에 설치된 서버(WAS,WEB,MAIL서버)의 버전을 조회하는 Shell 	
# Modification Information                                                				
#                                                                    
# 수정일                      수정자                   수정내용
# -------      --------     ---------------------------
# 2009.02.11    박지욱                   최초 생성
#
# @author 공통 서비스 개발팀 박지욱
# @since 2009. 02. 11
# @version 1.0
# @see
#
# Copyright (C) 2009 by MOPAS  All right reserved.

#echo $1	-SERVER NAME	

#WEBLOGIC
if [ $1 == "WEBLOGIC" ]
then
   java weblogic.Admin -username weblogic -password weblogic VERSION
fi

#JEUS
if [ $1 == "JEUS" ]
then
   jeusadmin -version
fi

#JBOSS
if [ $1 == "JBOSS" ]
then
   /user/com/jboss/bin/twiddle.sh -s localhost:1099 -u admin -p admin get "jboss.system:type=Server" | grep "Version="
fi