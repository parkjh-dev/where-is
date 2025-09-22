SET NAMES utf8mb4;

-- 데이터베이스 선택
USE where_is;

CREATE TABLE IF NOT EXISTS `user` (
  `id` integer PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) COMMENT '이름',
  `nickname` varchar(255) COMMENT '닉네임',
  `email` varchar(255) UNIQUE NOT NULL COMMENT '이메일',
  `phone` varchar(255) UNIQUE NOT NULL COMMENT '휴대전화',
  `profile_img` varchar(255) COMMENT '프로필 이미지',
  `birthday` date COMMENT '생일',
  `pw` varchar(255) NOT NULL COMMENT '패스워드',
  `role_id` integer NOT NULL COMMENT 'role fk',
  `is_locked` TINYINT COMMENT '계정 잠금',
  `login_fail_cnt` integer COMMENT '로그인 실패 횟수',
  `last_login_fail_dt` timestamp COMMENT '최근 로그인 실패 시간',
  `last_login_success_dt` timestamp COMMENT '최근 로그인 성공 시간',
  `add_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `mod_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);

CREATE TABLE `role` (
  `id` integer PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) COMMENT '역할명'
);

CREATE TABLE IF NOT EXISTS `trash_can_area` (
  `id` integer PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '명칭',
  `longitude` varchar(255) COMMENT '경도',
  `latitude` varchar(255) COMMENT '위도',
  `managing_agency` varchar(255) COMMENT '관리 주체',
  `agency_contact` varchar(255) COMMENT '관리 주체 연락처',
  `roadnm_addr` varchar(255) COMMENT '도로명 주소',
  `lotno_addr` varchar(255) COMMENT '지번 주소',
  `operating_hours` varchar(255) COMMENT '개방 시간',
  `facility_image` varchar(255) COMMENT '시설 이미지',
  `origin` CHAR(6) COMMENT '원본 데이터 출처',
  `add_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `mod_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);

CREATE TABLE IF NOT EXISTS `trash_can_area_requests` (
  `id` integer PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '명칭',
  `longitude` varchar(255) COMMENT '경도',
  `latitude` varchar(255) COMMENT '위도',
  `managing_agency` varchar(255) COMMENT '관리 주체',
  `agency_contact` varchar(255) COMMENT '관리 주체 연락처',
  `roadnm_addr` varchar(255) COMMENT '도로명 주소',
  `lotno_addr` varchar(255) COMMENT '지번 주소',
  `operating_hours` varchar(255) COMMENT '개방 시간',
  `facility_image` varchar(255) COMMENT '시설 이미지',
  `add_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `mod_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);

CREATE TABLE IF NOT EXISTS `trash_can_area_comments` (
  `id` integer PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `trash_can_area_id` integer COMMENT '외래키',
  `user_id` integer COMMENT 'user fk',
  `rating` integer COMMENT '별점',
  `comment` varchar(255) COMMENT '내용',
  `add_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `mod_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);

CREATE TABLE IF NOT EXISTS `trash_can_area_likes` (
  `id` integer PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `trash_can_area_id` integer COMMENT 'trash_can_area fk',
  `user_id` integer COMMENT 'user fk',
  `add_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일'
);

CREATE TABLE IF NOT EXISTS `trash_can_area_reports` (
  `id` integer PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `trash_can_area_id` integer COMMENT '외래키',
  `user_id` integer COMMENT 'user fk',
  `category` varchar(255) COMMENT '카테고리',
  `contents` varchar(255) COMMENT '제보 내용',
  `add_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `mod_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);

CREATE TABLE IF NOT EXISTS `smoking_area` (
  `id` integer PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '명칭',
  `longitude` varchar(255) COMMENT '경도',
  `latitude` varchar(255) COMMENT '위도',
  `managing_agency` varchar(255) COMMENT '관리 주체',
  `agency_contact` varchar(255) COMMENT '관리 주체 연락처',
  `roadnm_addr` varchar(255) COMMENT '도로명 주소',
  `lotno_addr` varchar(255) COMMENT '지번 주소',
  `operating_hours` varchar(255) COMMENT '개방 시간',
  `facility_image` varchar(255) COMMENT '시설 이미지',
  `origin` CHAR(6) COMMENT '원본 데이터 출처',
  `add_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `mod_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);

CREATE TABLE IF NOT EXISTS `smoking_area_requests` (
  `id` integer PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '명칭',
  `longitude` varchar(255) COMMENT '경도',
  `latitude` varchar(255) COMMENT '위도',
  `managing_agency` varchar(255) COMMENT '관리 주체',
  `agency_contact` varchar(255) COMMENT '관리 주체 연락처',
  `roadnm_addr` varchar(255) COMMENT '도로명 주소',
  `lotno_addr` varchar(255) COMMENT '지번 주소',
  `operating_hours` varchar(255) COMMENT '개방 시간',
  `facility_image` varchar(255) COMMENT '시설 이미지',
  `add_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `mod_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);

CREATE TABLE IF NOT EXISTS `smoking_area_comments` (
  `id` integer PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `smoking_area_id` integer COMMENT '외래키',
  `user_id` integer COMMENT 'user fk',
  `rating` integer COMMENT '별점',
  `comment` varchar(255) COMMENT '내용',
  `add_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `mod_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);

CREATE TABLE IF NOT EXISTS `smoking_area_likes` (
  `id` integer PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `smoking_area_id` integer COMMENT 'trash_can_area fk',
  `user_id` integer COMMENT 'user fk',
  `add_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일'
);

CREATE TABLE IF NOT EXISTS `smoking_area_reports` (
  `id` integer PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `smoking_area_id` integer COMMENT '외래키',
  `user_id` integer COMMENT 'user fk',
  `category` varchar(255) COMMENT '카테고리',
  `contents` varchar(255) COMMENT '제보 내용',
  `add_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `mod_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);

CREATE TABLE IF NOT EXISTS `toilets_area` (
  `id` integer PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '명칭',
  `longitude` varchar(255) COMMENT '경도',
  `latitude` varchar(255) COMMENT '위도',
  `managing_agency` varchar(255) COMMENT '관리 주체',
  `agency_contact` varchar(255) COMMENT '관리 주체 연락처',
  `roadnm_addr` varchar(255) COMMENT '도로명 주소',
  `lotno_addr` varchar(255) COMMENT '지번 주소',
  `operating_hours` varchar(255) COMMENT '개방 시간',
  `facility_image` varchar(255) COMMENT '시설 이미지',
  `origin` CHAR(6) COMMENT '원본 데이터 출처',
  `add_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `mod_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);

CREATE TABLE IF NOT EXISTS `toilets_area_requests` (
  `id` integer PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '명칭',
  `longitude` varchar(255) COMMENT '경도',
  `latitude` varchar(255) COMMENT '위도',
  `managing_agency` varchar(255) COMMENT '관리 주체',
  `agency_contact` varchar(255) COMMENT '관리 주체 연락처',
  `roadnm_addr` varchar(255) COMMENT '도로명 주소',
  `lotno_addr` varchar(255) COMMENT '지번 주소',
  `operating_hours` varchar(255) COMMENT '개방 시간',
  `facility_image` varchar(255) COMMENT '시설 이미지',
  `add_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `mod_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);

CREATE TABLE IF NOT EXISTS `toilets_area_comments` (
  `id` integer PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `toilets_area_id` integer COMMENT '외래키',
  `user_id` integer COMMENT 'user fk',
  `rating` integer COMMENT '별점',
  `comment` varchar(255) COMMENT '내용',
  `add_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `mod_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);

CREATE TABLE IF NOT EXISTS `toilets_area_likes` (
  `id` integer PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `toilets_area_id` integer COMMENT 'trash_can_area fk',
  `user_id` integer COMMENT 'user fk',
  `add_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일'
);

CREATE TABLE IF NOT EXISTS `toilets_area_reports` (
  `id` integer PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `toilets_area_id` integer COMMENT '외래키',
  `user_id` integer COMMENT 'user fk',
  `category` varchar(255) COMMENT '카테고리',
  `contents` varchar(255) COMMENT '제보 내용',
  `add_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `mod_dt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);

CREATE UNIQUE INDEX `trash_can_area_likes_index_0` ON `trash_can_area_likes` (`trash_can_area_id`, `user_id`);

CREATE UNIQUE INDEX `smorking_area_likes_index_1` ON `smorking_area_likes` (`smorking_area_id`, `user_id`);

CREATE UNIQUE INDEX `toilets_area_likes_index_2` ON `toilets_area_likes` (`toilets_area_id`, `user_id`);

ALTER TABLE `user` ADD FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

ALTER TABLE `trash_can_area_comments` ADD FOREIGN KEY (`trash_can_area_id`) REFERENCES `trash_can_area` (`id`);

ALTER TABLE `trash_can_area_comments` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `trash_can_area_likes` ADD FOREIGN KEY (`trash_can_area_id`) REFERENCES `trash_can_area` (`id`);

ALTER TABLE `trash_can_area_likes` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `trash_can_area_reports` ADD FOREIGN KEY (`trash_can_area_id`) REFERENCES `trash_can_area` (`id`);

ALTER TABLE `trash_can_area_reports` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `smoking_area_comments` ADD FOREIGN KEY (`smoking_area_id`) REFERENCES `smoking_area` (`id`);

ALTER TABLE `smoking_area_comments` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `smoking_area_likes` ADD FOREIGN KEY (`smoking_area_id`) REFERENCES `smoking_area` (`id`);

ALTER TABLE `smoking_area_likes` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `smoking_area_reports` ADD FOREIGN KEY (`smoking_area_id`) REFERENCES `smoking_area` (`id`);

ALTER TABLE `smoking_area_reports` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `toilets_area_comments` ADD FOREIGN KEY (`toilets_area_id`) REFERENCES `toilets_area` (`id`);

ALTER TABLE `toilets_area_comments` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `toilets_area_likes` ADD FOREIGN KEY (`toilets_area_id`) REFERENCES `toilets_area` (`id`);

ALTER TABLE `toilets_area_likes` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `toilets_area_reports` ADD FOREIGN KEY (`toilets_area_id`) REFERENCES `toilets_area` (`id`);

ALTER TABLE `toilets_area_reports` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
