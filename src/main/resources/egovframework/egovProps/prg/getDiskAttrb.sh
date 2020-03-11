# Shell Name : getDiskAttr.bat
# Description : 디스크 속성정보 확인
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
#### DISK CHECK ####
#for A in `lspv | awk '{print $1}' `
for A in `lsdev -Cc disk | awk '{print $1}'`
do
    # NAME
    echo $A
    # ATTRIBUTE
    echo `lscfg -l $A  | awk -F $A '{print $2}'`
    # TYPE
    echo "disk"
    # AUTHORITY

    # SIZE
    isEq="FALSE"
    for B in `lspv | awk '{print $1}' `
    do
        if [ $A = $B ]
        then
            isEq="TRUE"
        fi
    done

    if [ $isEq = "TRUE" ]
    then
        # TOTAL SIZE
        lspv $A | grep "TOTAL PPs" | awk -F"(" '{print $2}' | awk '{print $1}'
        # USED SIZE
        lspv $A | grep "USED PPs" | awk -F"(" '{print $2}' | awk '{print $1}'
        # FREE SIZE
        lspv $A | grep "FREE PPs" | awk -F"(" '{print $2}' | awk '{print $1}'
    else
        echo ""
        echo ""
        echo ""
    fi

done

#### USB CHECK ####
for A in `lsdev -Cc usb | awk '{print $1}'`
do
# NAME
echo $A
# ATTRIBUTE
echo `lscfg -l $A  | awk -F $A '{print $2}'`
# TYPE
echo "usb"
# AUTHORITY

# SIZE
    isEq="FALSE"
    for B in `lspv | awk '{print $1}' `
    do
        if [ $A = $B ]
        then
            isEq="TRUE"
        fi
    done

    if [ $isEq = "TRUE" ]
    then
        # TOTAL SIZE
        lspv $A | grep "TOTAL PPs" | awk -F"(" '{print $2}' | awk '{print $1}'
        # USED SIZE
        lspv $A | grep "USED PPs" | awk -F"(" '{print $2}' | awk '{print $1}'
        # FREE SIZE
        lspv $A | grep "FREE PPs" | awk -F"(" '{print $2}' | awk '{print $1}'
    else
        echo ""
        echo ""
        echo ""
    fi

done

#### CDROM CHECK ####
for A in `lsdev -Cc cdrom | awk '{print $1}'`
do
# NAME
echo $A
# ATTRIBUTE
echo `lscfg -l $A  | awk -F $A '{print $2}'`
# TYPE
echo "cdrom"
# AUTHORITY

# SIZE
    isEq="FALSE"
    for B in `lspv | awk '{print $1}' `
    do
        if [ $A = $B ]
        then
            isEq="TRUE"
        fi

            isEq="TRUE"
        fi
    done

    if [ $isEq = "TRUE" ]
    then
        # TOTAL SIZE
        lspv $A | grep "TOTAL PPs" | awk -F"(" '{print $2}' | awk '{print $1}'
        # USED SIZE
        lspv $A | grep "USED PPs" | awk -F"(" '{print $2}' | awk '{print $1}'
        # FREE SIZE
        lspv $A | grep "FREE PPs" | awk -F"(" '{print $2}' | awk '{print $1}'
    else
        echo ""
        echo ""
        echo ""
    fi

done