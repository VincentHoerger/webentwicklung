INSERT INTO members ( username, firstname, lastname, date_of_birth) VALUES ('max', 'Max', 'Mustermann','1976-02-01');
INSERT INTO members ( username, firstname, lastname, date_of_birth) VALUES ('erika', 'Erika', 'Mustermann','1964-08-12');
INSERT INTO members ( username, firstname, lastname, date_of_birth) VALUES ('markus', 'Markus', 'Mustermann','1982-06-17');
INSERT INTO members ( username, firstname, lastname, date_of_birth) VALUES ('leon', 'Leon', 'Mustermann','2003-03-15');

INSERT INTO vacations ( title, destination, description) VALUES ('Kreuzfahrt', 'Karibik', 'Wunderbare Karibikrundfahrt');
INSERT INTO vacations ( title, destination, description) VALUES ('Aktivurlaub', 'Gardasee', 'Motorbootfahren und Tauchen am Gardasee');
INSERT INTO vacations ( title, destination, description) VALUES ('Erholungsurlaub', 'Madeira', 'Durch Madeiras Blumen wandern');
INSERT INTO vacations ( title, destination, description) VALUES ('Erlebnisreise', 'Orlando', 'In den Freizatparks neue Welten entdecken');
INSERT INTO vacations ( title, destination, description) VALUES ('Naturreise', 'Spitzbergen', 'Natur pur im "größten Labor der Welt"');
INSERT INTO vacations ( title, destination, description) VALUES ('Radreise', 'Bodensee', 'Mehrtagestour mit tollen Übernachtungen');
INSERT INTO vacations ( title, destination, description) VALUES ('Wanderurlaub', 'Alpen', 'Zwei Wochen Hügel rauf und runter');

INSERT INTO holidays ( title, start_date, end_date) VALUES ('Herbstferien', '2022-11-02', '2022-11-04');
INSERT INTO holidays ( title, start_date, end_date) VALUES ('Weihnachtsferien', '2022-12-21', '2023-01-07');
INSERT INTO holidays ( title, start_date, end_date) VALUES ('Osterferien', '2023-04-11', '2023-04-15');
INSERT INTO holidays ( title, start_date, end_date) VALUES ('Pfingstferien', '2023-05-30', '2023-06-09');
INSERT INTO holidays ( title, start_date, end_date) VALUES ('Sommerferien', '2023-07-27', '2023-09-09');

INSERT INTO holidays_vacations (holiday_id, vacations_id) VALUES (1,1);
INSERT INTO holidays_vacations (holiday_id, vacations_id) VALUES (1,2);