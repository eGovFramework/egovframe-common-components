# Shell Name : getNetWorkInfo.sh                                        
# Description : 네트워크 정보를 찾는다.
# Modification Information                                                              
#                                                                    
# 수정일                      수정자                   수정내용
# -------      --------     ---------------------------
# 2009.02.23    이용                   최초 생성
#
# @author 공통 서비스 개발팀 이용
# @since 2009. 02. 13
# @version 1.0
# @see
#
# Copyright (C) 2009 by MOPAS  All right reserved.

#getNetWorkInfo.sh
#네트워크 정보를 찾는다.
#netstat -v ent0 | grep "하드웨어 주소"   -MAC
#prtconf | grep "IP 주소"                 -IP
#prtconf | grep "서브넷 마스크"           -SM
#prtconf | grep "게이트웨이"              -GW
#echo $1
#echo $2
#MAC
if [ $1 == "MAC" ]
then
   netstat -v $2 | grep "하드웨어 주소" | awk '{print $3}'
fi

#IP
if [ $1 == "IP" ]
then
   prtconf | grep "IP 주소" | awk '{print $3}'
fi

#SM
if [ $1 == "SM" ]
then
   prtconf | grep "서브넷 마스크" | awk '{print $3}'
fi

#GW
if [ $1 == "GW" ]
then
   prtconf | grep "게이트웨이" | awk '{print $2}'
fi

#도메인명
if [ $1 == "DN" ]
then
   hostname
fi

#DNS
if [ $1 == "DNS" ]
then
   host $2 | awk '{print $2}'
fi

if [ $1 == "SCAN" ]
then
   netstat -na | egrep 'tcp|udp'
fi