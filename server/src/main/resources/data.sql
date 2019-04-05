INSERT INTO users (username, password, role) VALUES ('raynermdz', '123456', 'ROLE_ADMIN');

INSERT INTO profile (first_name, last_name, profile_picture, bio, date_of_birth, country)
VALUES ('Rayner Enmanuel', 'Mendez Garcia', 'https://media.licdn.com/dms/image/C4D03AQGwEC3WgfxvEg/profile-displayphoto-shrink_200_200/0?e=1547683200&v=beta&t=4HWgAmzWf9Z52ao0c5Y36aHp89_l8TQdL39Cphc0ZBM', 'Aspiring Software Engineer. Quickly learn and master new technologies; successful working as part of a team and working independently.', DATE '1995-04-06', 'USA');

INSERT INTO posts (title, body, date, featured_picture) VALUES ('This is a post', 'Lorem ipsum', DATE '2015-12-17', 'https://www.lamborghini.com/sites/it-en/files/DAM/lamborghini/model/huracan/Evo/restyle/3_RP---Huracan-Evo-88.jpg');

INSERT INTO comments (name, body, date, post_id) VALUES ('This is a comment', 'Lorem ipsum', DATE '2015-12-17', 1);
INSERT INTO comments (name, body, date, post_id) VALUES ('This is a comment', 'Lorem ipsum', DATE '2015-12-17', 1);
INSERT INTO comments (name, body, date, post_id) VALUES ('This is a comment', 'Lorem ipsum', DATE '2015-12-17', 1);
INSERT INTO comments (name, body, date, post_id) VALUES ('This is a comment', 'Lorem ipsum', DATE '2015-12-17', 1);