# Shell Name : getDiskInfo.sh                          	
# Description : 시스템에 존재하는 디스크 정보를 조회하는 Shell
# Modification Information                                                				
#                                                                    
# 수정일                      수정자                   수정내용
# -------      --------     ---------------------------
# 2009.02.23    박지욱                   최초 생성
#
# @author 공통 서비스 개발팀 박지욱
# @since 2009. 02. 13
# @version 1.0
# @see
#
# Copyright (C) 2009 by MOPAS  All right reserved.

#echo $1	-SEARCH NAME (NAME, FULL, USING, VALID)	

#NAME
if [ $1 == "NAME" ]
then
   lspv | awk '{print $1}'
fi

#FULL
if [ $1 == "FULL" ]
then
   lspv $2 | grep "TOTAL PPs" | awk -F"(" '{print $2}' | awk '{print $1}'
fi

#USING
if [ $1 == "USED" ]
then
   lspv $2 | grep "USED PPs" | awk -F"(" '{print $2}' | awk '{print $1}'
fi

#VALID
if [ $1 == "FREE" ]
then
   lspv $2 | grep "FREE PPs" | awk -F"(" '{print $2}' | awk '{print $1}'
fi