-- ===========================================
-- gitpumta 스키마 및 테이블 전체 DDL (외래키 주석 포함)
-- ===========================================

-- 1. 스키마(데이터베이스) 생성 및 선택
CREATE DATABASE IF NOT EXISTS gitpumta;

USE gitpumta;

-- 2. 기존 테이블 삭제 (존재할 경우)
DROP TABLE IF EXISTS group_member;
DROP TABLE IF EXISTS `group`;
DROP TABLE IF EXISTS planner;
DROP TABLE IF EXISTS todo;
DROP TABLE IF EXISTS commit;
DROP TABLE IF EXISTS timer;
DROP TABLE IF EXISTS user;

-- 3. 테이블 생성

-- User 테이블
CREATE TABLE user (
                      id CHAR(36) NOT NULL PRIMARY KEY,
                      account_id VARCHAR(50) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      nickname VARCHAR(50) NOT NULL,
                      git_id VARCHAR(100),
                      created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                      updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      deleted_at DATETIME
);

-- Group 테이블
CREATE TABLE `group` (
                         id CHAR(36) NOT NULL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         password VARCHAR(255),
                         capacity INT,
                         description TEXT,
                         created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                         updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         deleted_at DATETIME
);

-- GroupMember 테이블
CREATE TABLE group_member (
                              id CHAR(36) NOT NULL PRIMARY KEY,
                              group_id CHAR(36) NOT NULL,
                              user_id CHAR(36) NOT NULL,
                              status VARCHAR(20),
                              created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                              updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              deleted_at DATETIME
    -- FOREIGN KEY (group_id) REFERENCES `group`(id),
    -- FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Planner 테이블
CREATE TABLE planner (
                         id CHAR(36) NOT NULL PRIMARY KEY,
                         user_id CHAR(36) NOT NULL,
                         name VARCHAR(100) NOT NULL,
                         description TEXT,
                         repository_link VARCHAR(255),
                         duration INT,
                         created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                         updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         deleted_at DATETIME
    -- FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Todo 테이블
CREATE TABLE todo (
                      id CHAR(36) NOT NULL PRIMARY KEY,
                      planner_id CHAR(36),
                      user_id CHAR(36) NOT NULL,
                      title VARCHAR(200) NOT NULL,
                      description TEXT,
                      status VARCHAR(20),
                      due_date DATETIME,
                      created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                      updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      deleted_at DATETIME
    -- FOREIGN KEY (planner_id) REFERENCES planner(id),
    -- FOREIGN KEY (user_id) REFERENCES user(id)
);

-- Commit 테이블
CREATE TABLE commit (
                        id CHAR(36) NOT NULL PRIMARY KEY,
                        planner_id CHAR(36) NOT NULL,
                        hash VARCHAR(100) NOT NULL,
                        message TEXT,
                        committed_at DATETIME,
                        created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                        deleted_at DATETIME
    -- FOREIGN KEY (planner_id) REFERENCES planner(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Timer 테이블
CREATE TABLE timer (
                       id CHAR(36) NOT NULL PRIMARY KEY,
                       user_id CHAR(36) NOT NULL,
    -- planner_id CHAR(36),
                       updated_at DATETIME, -- 시작 or 일시정지 시간 기록용
                       total_duration INT,
    -- session_type VARCHAR(30),
                       status VARCHAR(20), -- 1은 동작죽, 0은 일시정지, -1은 완전 정지
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                       deleted_at DATETIME
    -- FOREIGN KEY (user_id) REFERENCES user(id),
    -- FOREIGN KEY (planner_id) REFERENCES planner(id)
);

-- ===========================================
-- End of DDL
-- ===========================================
