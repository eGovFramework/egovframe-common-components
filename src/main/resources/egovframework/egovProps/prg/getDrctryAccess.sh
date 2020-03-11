# Shell Name : getDrctryAccess.sh
# Description : 해당되는 디렉토리의 접근권한을 찾는다.
# Modification Information                                                              
#                                                                    
# 수정일                      수정자                   수정내용
# -------      --------     ---------------------------
# 2009.02.23    조재영                   최초 생성
#
# @author 공통 서비스 개발팀 조재영
# @since 2009. 02. 13
# @version 1.0
# @see
#
# Copyright (C) 2009 by MOPAS  All right reserved.
ls -alF $1 | grep $2 | awk -F" " '{print $1}'