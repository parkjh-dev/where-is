SET NAMES utf8mb4;

-- 데이터베이스 선택
USE where_is;

INSERT INTO role (name)
VALUES  ("admin"),
        ("user");

INSERT INTO user (name, email, phone, pw, role_id)
VALUES ("admin", "pgh6444@naver.com", "010-0000-0000", "5806cbf2e9fdf8ae113829c5c23ff0020238bd17ff2d5a4dd92a070e8b329120", 1);

INSERT INTO toilets_area (name, longitude,latitude, managing_agency, agency_contact, roadnm_addr,lotno_addr, operating_hours, facility_image, origin)
VALUES ("수원월드컵경기장 축구공 함성", "127.034892", "37.288686", "(재)경기도수원월드컵경기장관리재단 시설운영팀", "031-0000-0000", "경기 수원시 팔달구 우만동 222", null, "00:00 ~ 23:59", "IMG_4510.JPG", "USER"),
	("수원월드컵경기장 축구공 감동", "127.034179", "37.287338", "(재)경기도수원월드컵경기장관리재단 시설운영팀", "031-0000-0000", "경기 수원시 팔달구 우만동 222", null, "00:00 ~ 23:59", "IMG_4516.JPG", "USER"),
	("수원월드컵경기장 축구공 하나", "127.036677", "37.284889", "(재)경기도수원월드컵경기장관리재단 시설운영팀", "031-0000-0000", "경기 수원시 팔달구 우만동 222", null, "00:00 ~ 23:59", "IMG_4513.JPG", "USER");

INSERT INTO smoking_area (name, longitude,latitude, managing_agency, agency_contact, roadnm_addr,lotno_addr, operating_hours, facility_image, origin)
VALUES ("수원 월드컵 경기장 북편", "127.036329", "37.287873", "(재)경기도수원월드컵경기장관리재단 시설운영팀", "031-0000-0000", "경기 수원시 팔달구 우만동 222", null, "00:00 ~ 23:59", "IMG_4511.JPG", "USER"),
    ("수원 월드컵 경기장 남편", "127.035692", "37.285768", "(재)경기도수원월드컵경기장관리재단 시설운영팀", "031-0000-0000", "경기 수원시 팔달구 우만동 222", null, "00:00 ~ 23:59", "IMG_4512.JPG", "USER");

INSERT INTO trash_can_area (name, longitude,latitude, managing_agency, agency_contact, roadnm_addr,lotno_addr, operating_hours, facility_image, origin)
VALUES ("수원 월드컵 경기장 중앙광장", "127.034632", "37.288212", "(재)경기도수원월드컵경기장관리재단 시설운영팀", "031-0000-0000", "경기 수원시 팔달구 우만동 222", null, "00:00 ~ 23:59", "IMG_4517.JPG", "USER");