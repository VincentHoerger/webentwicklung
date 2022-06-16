INSERT INTO members ( username, firstname, lastname, date_of_birth) VALUES ('max', 'Max', 'Mustermann','1976-02-01');
INSERT INTO members ( username, firstname, lastname, date_of_birth) VALUES ('erika', 'Erika', 'Mustermann','1964-08-12');
INSERT INTO members ( username, firstname, lastname, date_of_birth) VALUES ('markus', 'Markus', 'Mustermann','1982-06-17');
INSERT INTO members ( username, firstname, lastname, date_of_birth) VALUES ('leon', 'Leon', 'Mustermann','2003-03-15');

INSERT INTO holidays ( title, start_date, end_date) VALUES ('Herbstferien', '2022-11-02', '2022-11-04');
INSERT INTO holidays ( title, start_date, end_date) VALUES ('Weihnachtsferien', '2022-12-21', '2023-01-07');
INSERT INTO holidays ( title, start_date, end_date) VALUES ('Osterferien', '2023-04-11', '2023-04-15');
INSERT INTO holidays ( title, start_date, end_date) VALUES ('Pfingstferien', '2023-05-30', '2023-06-09');
INSERT INTO holidays ( title, start_date, end_date) VALUES ('Sommerferien', '2023-07-27', '2023-09-09');

INSERT INTO vacations ( title, destination, description, holiday_id) VALUES ('Kreuzfahrt', 'Karibik', 'Wunderbare Karibikrundfahrt',1);
INSERT INTO vacations ( title, destination, description, holiday_id) VALUES ('Aktivurlaub', 'Gardasee', 'Motorbootfahren und Tauchen am Gardasee',1);
INSERT INTO vacations ( title, destination, description, holiday_id) VALUES ('Erholungsurlaub', 'Madeira', 'Durch Madeiras Blumen wandern',2);
INSERT INTO vacations ( title, destination, description, holiday_id) VALUES ('Erlebnisreise', 'Orlando', 'In den Freizatparks neue Welten entdecken',2);
INSERT INTO vacations ( title, destination, description, holiday_id) VALUES ('Naturreise', 'Spitzbergen', 'Natur pur im "größten Labor der Welt"',3);
INSERT INTO vacations ( title, destination, description, holiday_id) VALUES ('Radreise', 'Bodensee', 'Mehrtagestour mit tollen Übernachtungen',4);
INSERT INTO vacations ( title, destination, description, holiday_id) VALUES ('Wanderurlaub', 'Alpen', 'Zwei Wochen Hügel rauf und runter',5);

INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (1,1,7);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (1,2,6);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (1,3,5);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (1,4,4);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (1,5,3);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (1,6,2);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (1,7,1);

INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (2,1,4);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (2,2,5);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (2,3,3);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (2,4,2);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (2,5,8);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (2,6,3);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (2,7,2);

INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (3,1,1);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (3,2,2);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (3,3,3);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (3,4,4);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (3,5,5);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (3,6,6);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (3,7,7);

INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (4,1,3);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (4,2,3);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (4,3,3);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (4,4,3);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (4,5,1);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (4,6,3);
INSERT INTO vacation_priorities (member_id, vacation_id, priority) VALUES (4,7,3);