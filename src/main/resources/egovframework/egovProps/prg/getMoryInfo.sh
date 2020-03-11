# Shell Name : getMoryInfo.sh                          	
# Description : 시스템에 존재하는 메모리 정보를 조회하는 Shell
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

#echo $1	-SEARCH NAME (FULL, USING, FREE)	

#FULL
if [ $1 == "FULL" ]
then
   vmstat | grep "mem=" | awk '{print $4}'
fi

#USING
if [ $1 == "USED" ]
then
   vmstat | tail -1 | awk '{print $3*4096/1024/1024}'
fi

#VALID
if [ $1 == "FREE" ]
then
   vmstat | tail -1 | awk '{print $4*4096/1024/1024}'
fi