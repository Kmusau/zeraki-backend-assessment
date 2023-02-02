INSERT INTO courses (courseid, course_name) VALUES (1, 'Computer Science');
INSERT INTO courses (courseid, course_name) VALUES (9, 'Software Engineering');
INSERT INTO courses (courseid, course_name) VALUES (10, 'Networking');
INSERT INTO courses (courseid, course_name) VALUES (16, 'Economics');

INSERT INTO students (studentid, regnumber, institution, `name`, course_id) VALUES (1,'S13/09010/17','Egerton University','Kelvin Musau',9);
INSERT INTO students (studentid, regnumber, institution, `name`, course_id) VALUES (2,'S12/09011/17','JKUAT','Tom Musyimi Hunter',1);
INSERT INTO students (studentid, regnumber, institution, `name`, course_id) VALUES (8,'S13/08021/17','Egerton University','Omondi Timon',NULL);
INSERT INTO students (studentid, regnumber, institution, `name`, course_id) VALUES (13,'S13/08015/17','JKUAT','John Doe',10);

INSERT INTO institutions (institutionid, institution_name, `location`) VALUES (1,'Kenyatta University','Kahawa');
INSERT INTO institutions (institutionid, institution_name, `location`) VALUES (11,'Egerton University','Njoro');
INSERT INTO institutions (institutionid, institution_name, `location`) VALUES (14,'Kabarak University','Nakuru');
INSERT INTO institutions (institutionid, institution_name, `location`) VALUES (15,'JKUAT','Juja');

INSERT INTO institutions_courses (institutionid, courseid) VALUES (1,1);
INSERT INTO institutions_courses (institutionid, courseid) VALUES (1,10);
INSERT INTO institutions_courses (institutionid, courseid) VALUES (15,16);
INSERT INTO institutions_courses (institutionid, courseid) VALUES (15,1);

-- INSERT INTO hibernate_sequence (next_val) VALUES (19);