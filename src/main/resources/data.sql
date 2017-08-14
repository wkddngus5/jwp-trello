-- password='a'
INSERT INTO user (id, user_id, password, email) VALUES (1, 'a', '$2a$10$yPX4FZBDG0QpchOZHHFUTOqLMQtZiPEj9K7KFGJtJTnByrFM.kY6G', 'a@EMAIL.COM');
INSERT INTO user (id, user_id, password, email) VALUES (2, 'c', '$2a$10$GaJKDLoZSj6aO8xMHEp1FuFeAUgRB/CKS5WTslX2dah4K2rQJftJG', 'b@EMAIL.COM');

INSERT INTO role (role_id, role) VALUES (1, 'ADMIN');
INSERT INTO role (role_id, role) VALUES (2, 'USER');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (1, 2);

INSERT INTO user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO deck (deck_id, user_id, title) VALUES (1, 1, 'TEST DECK');

INSERT INTO card (card_id, deck_id, contents) VALUES (1, 1, 'TEST CARD');
INSERT INTO card (card_id, deck_id, contents) VALUES (2, 1, 'TEST CARD2');

INSERT INTO comment (comment_id, card_id, contents) VALUES (1, 1, 'TEST COMMENT');
INSERT INTO comment (comment_id, card_id, contents) VALUES (2, 1, 'TEST COMMENT2');
