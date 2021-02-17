DROP DATABASE IF EXISTS conference;
CREATE DATABASE IF NOT EXISTS conference DEFAULT CHARACTER SET utf8;
USE conference;

DROP TABLE IF EXISTS report_event;
DROP TABLE IF EXISTS visitor_event;
DROP TABLE IF EXISTS speaker_report;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS report;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS role;

CREATE TABLE role (
    id INT PRIMARY KEY,
    title ENUM('MODERATOR', 'SPEAKER', 'VISITOR') NOT NULL
);

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    email VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL,
    role_id INT,
    CONSTRAINT fk_user_role_id FOREIGN KEY (role_id)
        REFERENCES role (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE report (
    id INT AUTO_INCREMENT PRIMARY KEY,
    topic VARCHAR(100) NOT NULL,
    speaker INT
);

CREATE TABLE location (
    id INT AUTO_INCREMENT PRIMARY KEY,
    zipcode VARCHAR(10),
    country VARCHAR(30) NOT NULL,
    region VARCHAR(30),
    city VARCHAR(30) NOT NULL,
    street VARCHAR(30),
    building VARCHAR(10) NOT NULL,
    suite VARCHAR(10),
    hash_code INT NOT NULL UNIQUE
);

CREATE TABLE event (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    date DATE NOT NULL,
    location_id INT,
    CONSTRAINT fk_event_Location_id FOREIGN KEY (location_id)
        REFERENCES location (id)
        ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE speaker_report (
   speaker_id INT NOT NULL,
   report_id INT NOT NULL,
   is_assigned BOOL DEFAULT FALSE,
   last_update DATETIME DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP(),
   PRIMARY KEY (report_id , speaker_id),
   CONSTRAINT fk_speaker_report_speaker_id FOREIGN KEY (speaker_id)
       REFERENCES user (id)
       ON UPDATE CASCADE ON DELETE CASCADE,
   CONSTRAINT fk_speaker_report_report_id FOREIGN KEY (report_id)
       REFERENCES report (id)
       ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE report_event (
    report_id INT NOT NULL,
    event_id INT NOT NULL,
    PRIMARY KEY (event_id , report_id),
    CONSTRAINT fk_report_event_report_id FOREIGN KEY (report_id)
        REFERENCES report (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_report_event_event_id FOREIGN KEY (event_id)
        REFERENCES event (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE visitor_event (
    visitor_id INT NOT NULL,
    event_id INT NOT NULL,
    is_visited BOOL DEFAULT FALSE,
    last_update DATETIME DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP(),
    PRIMARY KEY (event_id , visitor_id),
    CONSTRAINT fk_visitor_event_user_id FOREIGN KEY (visitor_id)
        REFERENCES user (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_visitor_event_event_id FOREIGN KEY (event_id)
        REFERENCES event (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);



INSERT INTO role (id, title) VALUES (1, 'MODERATOR');
INSERT INTO role (id, title) VALUES (2, 'SPEAKER');
INSERT INTO role (id, title) VALUES (3, 'VISITOR');
INSERT INTO user (id, name, email, password, role_id) VALUES (DEFAULT, 'Admin', 'taras@gmail.com', 'admin', 1);
INSERT INTO user (id, name, email, password, role_id) VALUES (DEFAULT, 'Speaker', 'speaker@gmail.com', 'speaker', 2);
INSERT INTO user (id, name, email, password, role_id) VALUES (DEFAULT, 'Visitor1', 'v1@gmail.com', 'v1', 3);
INSERT INTO report (id, topic) VALUES (DEFAULT, 'newReport');
INSERT INTO event (id, date, title) VALUES (DEFAULT, '2021-01-31', 'event');

##########################################
# TestQueries
INSERT INTO visitor_event (visitor_id, event_id) VALUES (3, 1);
UPDATE visitor_event SET is_visited = true WHERE visitor_id = 3 AND event_id = 1;

SELECT * FROM report WHERE speaker IS NULL;
UPDATE report SET speaker = ? WHERE report.id = ?;
INSERT INTO speaker_report (speaker_id, report_id) VALUES (?, ?);
SELECT * FROM user WHERE role_id = 2 AND id IN (SELECT speaker_id FROM speaker_report WHERE report_id = ?);

INSERT INTO user (id, name, email, password, role_id) VALUES (DEFAULT, 'Speaker2', 'speaker2@gmail.com', 'speaker2', 2);

SELECT r.id, r.topic, u.id, u.name, u.email, u.password, u.role_id FROM report r, user u WHERE r.speaker AND u.id = 2;

SELECT * FROM report r, user u WHERE r.speaker AND u.id = 22;

INSERT INTO event (title, date, location_id) VALUES (?, ?, ?);

INSERT INTO location VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?);

SELECT * FROM event WHERE id IN
    (SELECT event_id FROM report_event WHERE report_id IN
    (SELECT id FROM report WHERE speaker = ?));

SELECT * FROM event WHERE id IN
    (SELECT event_id FROM report_event re JOIN report r ON r.speaker = ?);

SELECT e.id id, e.title title, e.date date, e.location_id location_id
    FROM event e JOIN report_event re ON e.id = re.event_id
    JOIN report r ON r.speaker = ?
    GROUP BY id;

SELECT * FROM event WHERE date BETWEEN ? AND ?;

select * from event where date BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 100 YEAR);






