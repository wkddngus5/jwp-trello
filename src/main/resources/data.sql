-- password='a'
INSERT INTO user (email, password) VALUES ('woohyeon@hanmail.com', '$2a$10$yPX4FZBDG0QpchOZHHFUTOqLMQtZiPEj9K7KFGJtJTnByrFM.kY6G');
INSERT INTO user (email, password) VALUES ('a@EMAIL.COM', '$2a$10$GaJKDLoZSj6aO8xMHEp1FuFeAUgRB/CKS5WTslX2dah4K2rQJftJG');

INSERT INTO role (role_id, role) VALUES (1, 'ADMIN');
INSERT INTO role (role_id, role) VALUES (2, 'USER');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO board (board_id, title) VALUES (1, 'TEST BOARD');

INSERT INTO user_board (user_id, board_id) VALUES (1, 1);

INSERT INTO deck (deck_id, board_id, title) VALUES (1, 1, 'TEST DECK');

INSERT INTO card (card_id, deck_id, contents) VALUES (1, 1, 'TEST CARD');
INSERT INTO card (card_id, deck_id, contents) VALUES (2, 1, 'TEST CARD2');

INSERT INTO comment (comment_id, card_id, contents, writer_name, create_time) VALUES (1, 1, 'TEST COMMENT', 'a', '16 Aug 2017 at 23:59');
INSERT INTO comment (comment_id, card_id, contents, writer_name, create_time) VALUES (2, 1, 'TEST COMMENT2', 'a', '16 Aug 2017 at 23:59');
