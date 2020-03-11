# Shell Name : getMountLc.sh                                       	
# Description : 파일(디렉토리)가 존재하는 파일시스템(마운트된 위치)을 조회하는 Shell
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

#echo $1	-FILE LOCATION
#echo $1	-/

df -k $1 | grep $2 | awk -F" " '{print $7}'