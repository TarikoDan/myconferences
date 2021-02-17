DROP DATABASE IF EXISTS conference2;
CREATE DATABASE IF NOT EXISTS conference2 DEFAULT CHARACTER SET utf8;
USE conference2;

DROP TABLE IF EXISTS event_report;
DROP TABLE IF EXISTS event_visitor;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS report;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS role;

CREATE TABLE role (
  id INT PRIMARY KEY,
  title ENUM('MODERATOR', 'SPEAKER', 'VISITOR') NOT NULL
);

CREATE TABLE language (
  id INT AUTO_INCREMENT PRIMARY KEY,
  value VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE user (
  id INT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(30) NOT NULL UNIQUE,
  password VARCHAR(30) NOT NULL,
  role_id INT,
  CONSTRAINT fk_user_role_id FOREIGN KEY (role_id)
      REFERENCES role (id)
      ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE user_details (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  name VARCHAR(30) NOT NULL,
  lastname VARCHAR(30),
  language_id INT,
  CONSTRAINT fk_user_details_user_id FOREIGN KEY (user_id)
      REFERENCES user (id)
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_user_details_language_id FOREIGN KEY (language_id)
      REFERENCES language (id)
      ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE report (
    id INT AUTO_INCREMENT PRIMARY KEY,
    speaker INT,
    CONSTRAINT fk_report_user_id FOREIGN KEY (speaker)
        REFERENCES user (id)
        ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE report_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    report_id INT NOT NULL ,
    topic VARCHAR(100) NOT NULL,
    language_id INT,
    CONSTRAINT fk_report_details_report_id FOREIGN KEY (report_id)
        REFERENCES report (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_report_details_language_id FOREIGN KEY (language_id)
        REFERENCES language (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE location (
  id INT AUTO_INCREMENT PRIMARY KEY,
  zipcode INT
);

CREATE TABLE location_details (
  id INT AUTO_INCREMENT PRIMARY KEY,
  location_id INT NOT NULL,
  country VARCHAR(30),
  city VARCHAR(30) NOT NULL,
  language_id INT,
  CONSTRAINT fk_location_details_user_id FOREIGN KEY (location_id)
      REFERENCES location (id)
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_location_details_language_id FOREIGN KEY (language_id)
      REFERENCES language (id)
      ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE event (
   id INT AUTO_INCREMENT PRIMARY KEY,
   date DATE NOT NULL,
   location INT,
   CONSTRAINT fk_event_Location_id FOREIGN KEY (location)
       REFERENCES location (id)
       ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE event_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    topic VARCHAR(100) NOT NULL,
    language_id INT,
    CONSTRAINT fk_event_details_event_id FOREIGN KEY (event_id)
        REFERENCES event (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_event_details_language_id FOREIGN KEY (language_id)
        REFERENCES language (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE event_report (
  event_id INT NOT NULL,
  report_id INT NOT NULL,
  PRIMARY KEY (event_id , report_id),
  CONSTRAINT fk_event_report_event_id FOREIGN KEY (event_id)
      REFERENCES event (id)
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_event_report_report_id FOREIGN KEY (report_id)
      REFERENCES report (id)
      ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE event_visitor (
   event_id INT NOT NULL,
   visitor_id INT NOT NULL,
   visited BOOL DEFAULT FALSE,
   PRIMARY KEY (event_id , visitor_id),
   CONSTRAINT fk_event_visitor_event_id FOREIGN KEY (event_id)
       REFERENCES event (id)
       ON UPDATE CASCADE ON DELETE CASCADE,
   CONSTRAINT fk_event_visitor_visitor_id FOREIGN KEY (visitor_id)
       REFERENCES user (id)
       ON UPDATE CASCADE ON DELETE CASCADE
);



INSERT INTO role (id, title) VALUES (1, 'MODERATOR');
INSERT INTO role (id, title) VALUES (2, 'SPEAKER');
INSERT INTO role (id, title) VALUES (3, 'VISITOR');
INSERT INTO user (id, email, password, role_id) VALUES (DEFAULT, 'taras@gmail.com', 'admin', 1);
# INSERT INTO user (id, email, password, role_id) VALUES (DEFAULT, 'Speaker', 'speaker@gmail.com', 'speaker', 2);
# INSERT INTO user (id, email, password, role_id) VALUES (DEFAULT, 'Visitor1', 'v1@gmail.com', 'v1', 3);

INSERT INTO language (id, value) VALUES (DEFAULT, 'en');
INSERT INTO language (id, value) VALUES (DEFAULT, 'ua');

INSERT INTO user_details (user_id, name, lastname, language_id) VALUES (1,'Admin', 'b', 1);
INSERT INTO user_details (user_id, name, lastname, language_id) VALUES (1,'АДМІН', 'Бб', 2);



