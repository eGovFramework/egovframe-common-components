# Shell Name : getDiskExst.bat
# Description : 디스크 존재여부 확인(윈도우에서는 지원 안함)
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
lsdev -Cc disk | wc -l