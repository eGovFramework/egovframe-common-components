-- 🔍 1. 모든 테이블 목록 조회
SELECT /* select */
       A.TABLE_SCHEMA /* 테이블 스키마 */
     , A.TABLE_NAME /* 테이블 이름 */
     , A.TABLE_ROWS /* 테이블 행 */
     , A.CREATE_TIME /* 생성 시간 */
     , A.UPDATE_TIME /* 업데이트 시간 */
     , A.TABLE_COMMENT /* 테이블 주석 */
     , A.TEMPORARY /* 일시적인 */
--      , A.*
  FROM INFORMATION_SCHEMA.TABLES A /* 테이블 */
 WHERE 1 = 1
--    AND A.TABLE_CATALOG = 'def' /* 테이블 카탈로그 */
   AND A.TABLE_SCHEMA = 'com' /* 테이블 스키마 */
--    AND A.TABLE_SCHEMA = 'information_schema' /* 테이블 스키마 */
--    AND A.TABLE_SCHEMA = 'performance_schema' /* 테이블 스키마 */
;

-- 🔍 2. 특정 테이블의 컬럼 목록 조회
SELECT /* select */
       A.TABLE_SCHEMA /* 테이블 스키마 */
     , A.TABLE_NAME /* 테이블 이름 */
     , A.COLUMN_NAME /* 컬럼 이름 */
     , A.ORDINAL_POSITION /* 순서 위치 */
     , A.COLUMN_DEFAULT /* 컬럼 기본값 */
     , A.IS_NULLABLE /* 테이블 주석 */
     , A.DATA_TYPE /* 데이터 유형 */
     , A.NUMERIC_PRECISION /* 수치적 정밀도 */
     , A.NUMERIC_SCALE /* 숫자 눈금 */
     , A.COLUMN_TYPE /* 컬럼 유형 */
     , A.COLUMN_KEY /* 컬럼 키 */
     , A.PRIVILEGES /* 권한 */
     , A.COLUMN_COMMENT /* 컬럼 주석 */
--      , A.*
  FROM INFORMATION_SCHEMA.COLUMNS A /* 컬럼 */
 WHERE 1 = 1
--    AND A.TABLE_CATALOG = 'def' /* 테이블 카탈로그 */
   AND A.TABLE_SCHEMA = 'com' /* 테이블 스키마 */
--    AND A.TABLE_SCHEMA = 'information_schema' /* 테이블 스키마 */
--    AND A.TABLE_SCHEMA = 'performance_schema' /* 테이블 스키마 */
;

-- 🔍 3. 기본키 컬럼 확인
SELECT /* select */
       A.CONSTRAINT_NAME /* 제약 조건 이름 */
     , A.TABLE_SCHEMA /* 테이블 스키마 */
     , A.TABLE_NAME /* 테이블 이름 */
     , A.COLUMN_NAME /* 컬럼 이름 */
     , A.ORDINAL_POSITION /* 순서 위치 */
--      , A.*
  FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE A /* 키 컬럼 사용 */
 WHERE 1 = 1
--    AND A.TABLE_CATALOG = 'def' /* 테이블 카탈로그 */
   AND A.TABLE_SCHEMA = 'com' /* 테이블 스키마 */
--    AND A.TABLE_SCHEMA = 'information_schema' /* 테이블 스키마 */
--    AND A.TABLE_SCHEMA = 'performance_schema' /* 테이블 스키마 */
;

-- 

SELECT /* select */
       A.TABLE_CATALOG
     , COUNT(*) AS CNT
  FROM INFORMATION_SCHEMA.TABLES A
GROUP BY A.TABLE_CATALOG
;

SELECT /* select */
       A.TABLE_SCHEMA
     , COUNT(*) AS CNT
  FROM INFORMATION_SCHEMA.TABLES A
GROUP BY A.TABLE_SCHEMA
;

/*

information_schmea 정보스키마 script 추가

information_schema는 MySQL, PostgreSQL, SQL Server, Oracle 등 다양한 관계형 데이터베이스(RDBMS)에서 사용되는 시스템 정보 저장용 가상 데이터베이스입니다. 데이터베이스 안에 있는 테이블, 열, 인덱스, 권한 등 **메타데이터(metadata)**를 조회할 수 있도록 표준 SQL 인터페이스를 제공합니다.

✅ 주요 용도
- 데이터베이스의 구조 파악
- 테이블, 컬럼, 인덱스 정보 확인
- 접근 권한 및 사용자 정보 확인
- 자동화 스크립트 작성 시 유용

✅ 대표적인 테이블
| 테이블명                | 설명                          |
| ------------------- | --------------------------- |
| `TABLES`            | 현재 데이터베이스의 모든 테이블 정보        |
| `COLUMNS`           | 테이블별 컬럼 정보 (자료형, NULL 허용 등) |
| `SCHEMATA`          | 모든 스키마(DB) 목록               |
| `KEY_COLUMN_USAGE`  | 기본키 및 외래키 정보                |
| `TABLE_CONSTRAINTS` | 제약 조건 정보 (PK, FK, UNIQUE 등) |
| `ROUTINES`          | 저장 프로시저, 함수 정보              |
| `VIEWS`             | 뷰(View) 정보                  |

✅ 예제
🔍 1. 모든 테이블 목록 조회
```sql
SELECT TABLE_NAME
FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'my_database';
```

🔍 2. 특정 테이블의 컬럼 목록 조회
```sql
SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE, COLUMN_DEFAULT
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = 'my_database' AND TABLE_NAME = 'my_table';

```

🔍 3. 기본키 컬럼 확인
```sql
SELECT COLUMN_NAME
FROM information_schema.KEY_COLUMN_USAGE
WHERE TABLE_SCHEMA = 'my_database'
  AND TABLE_NAME = 'my_table'
  AND CONSTRAINT_NAME = 'PRIMARY';

```

✅ 지원 DBMS
- MySQL
- PostgreSQL
- MariaDB
- SQL Server (다만 일부 테이블은 sys 스키마 사용)
- Oracle (유사 기능은 ALL_TABLES, USER_TAB_COLUMNS 등)

✅ 참고
- information_schema는 읽기 전용입니다.
- 성능상 이유로 너무 자주 사용하거나 복잡한 조건으로 조회할 경우, 응답이 느릴 수 있습니다.

https://chatgpt.com/share/6888027a-d7b4-8003-abd1-249c2fd8e7e8

*/
