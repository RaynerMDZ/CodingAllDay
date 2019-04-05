INSERT INTO users (username, password, role) VALUES ('raynermdz', '123456', 'ROLE_ADMIN');

INSERT INTO profile (first_name, last_name, profile_picture, bio, date_of_birth, country)
VALUES ('Rayner Enmanuel', 'Mendez Garcia', 'https://pbs.twimg.com/profile_images/1070485532977717248/dFg78mJ1_400x400.jpg', 'Aspiring Software Engineer. Quickly learn and master new technologies; successful working as part of a team and working independently.', DATE '1995-04-06', 'USA');

INSERT INTO posts (title, body, date, featured_picture) VALUES ('This is a post', 'Lorem ipsum', DATE '2015-12-17', 'https://www.lamborghini.com/sites/it-en/files/DAM/lamborghini/model/huracan/Evo/restyle/3_RP---Huracan-Evo-88.jpg');

INSERT INTO comments (name, body, date, post_id) VALUES ('This is a comment', 'Lorem ipsum', DATE '2015-12-17', 1);
INSERT INTO comments (name, body, date, post_id) VALUES ('This is a comment', 'Lorem ipsum', DATE '2015-12-17', 1);
INSERT INTO comments (name, body, date, post_id) VALUES ('This is a comment', 'Lorem ipsum', DATE '2015-12-17', 1);
INSERT INTO comments (name, body, date, post_id) VALUES ('This is a comment', 'Lorem ipsum', DATE '2015-12-17', 1);