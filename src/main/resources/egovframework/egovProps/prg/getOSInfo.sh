# Shell Name : getOSInfo.sh                                           	
# Description : 시스템 OS의 이름, 버전, 제조사를 조회하는 Shell
# Modification Information                                                				
#                                                                    
# 수정일                      수정자                   수정내용
# -------      --------     ---------------------------
# 2009.02.13    박지욱                   최초 생성
#
# @author 공통 서비스 개발팀 박지욱
# @since 2009. 02. 13
# @version 1.0
# @see
#
# Copyright (C) 2009 by MOPAS  All right reserved.

#echo $1	-SEARCH NAME (NAME, VERSION, PRDUCTOR)	

#NAME
if [ $1 == "NAME" ]
then
   uname -s
fi

#VERSION
if [ $1 == "VERSION" ]
then
   oslevel
fi

#PRDUCTOR
if [ $1 == "PRDUCTOR" ]
then
   prtconf | grep "시스템 모델:" | awk '{print $3}'
fi

#PROCESSOR
if [ $1 == "PROCESSOR" ]
then
   uname -a | awk '{print substr($5,3,6)}'
fi