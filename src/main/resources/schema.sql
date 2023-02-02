DROP TABLE IF EXISTS courses;

CREATE TABLE courses (
  courseid int NOT NULL AUTO_INCREMENT,
  course_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (courseid)
); 

DROP TABLE IF EXISTS students;

CREATE TABLE students (
  studentid int NOT NULL AUTO_INCREMENT,
  regnumber varchar(255) DEFAULT NULL,
  institution varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  course_id int DEFAULT NULL,
  PRIMARY KEY (studentid),
  KEY FK6jiqckumc6tm0h9otqbtqhgnr (course_id),
  CONSTRAINT FK6jiqckumc6tm0h9otqbtqhgnr FOREIGN KEY (course_id) REFERENCES courses (courseid)
);

DROP TABLE IF EXISTS institutions;

CREATE TABLE institutions (
  institutionid int NOT NULL AUTO_INCREMENT,
  institution_name varchar(255) DEFAULT NULL,
  location varchar(255) DEFAULT NULL,
  PRIMARY KEY (institutionid),
  UNIQUE KEY institution_name_UNIQUE (institution_name)
);

DROP TABLE IF EXISTS institutions_courses;

CREATE TABLE institutions_courses (
  institutionid int NOT NULL,
  courseid int NOT NULL,
  KEY FKq8b8mhwdphm9wss2rcedfcx6d (courseid),
  KEY FKq6k5fgijkcooo2bsjw4t893a2 (institutionid),
  CONSTRAINT FKq6k5fgijkcooo2bsjw4t893a2 FOREIGN KEY (institutionid) REFERENCES institutions (institutionid),
  CONSTRAINT FKq8b8mhwdphm9wss2rcedfcx6d FOREIGN KEY (courseid) REFERENCES courses (courseid)
);

