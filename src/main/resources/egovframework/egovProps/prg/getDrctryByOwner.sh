# Shell Name : getDrctryByOwner.sh
# Description : 해당되는 사용자 계정이 소유주인 디렉토리를 찾는다.
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
find $1 -name $2 -user $3