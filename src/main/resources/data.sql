INSERT INTO quiz (title, max_score, created, updated) VALUES ('Quiz1', 1, '2001-01-01 01:01:01', '2011-01-01 01:01:01');
INSERT INTO quiz (title, max_score, created, updated) VALUES ('Quiz2', 2, '2002-01-01 01:01:01', '2012-01-01 01:01:01');
INSERT INTO quiz (title, max_score, created, updated) VALUES ('Quiz3', 3, '2003-01-01 01:01:01', '2013-01-01 01:01:01');
INSERT INTO quiz (title, max_score, created, updated) VALUES ('Quiz4', 4, '2004-01-01 01:01:01', '2014-01-01 01:01:01');



INSERT INTO question (score, content, quiz_id) VALUES (0, 'Quiz1-Q1 - No valid answer', 1);
INSERT INTO question (score, content, quiz_id) VALUES (1, 'Quiz1-Q2- One valid answer', 1);

INSERT INTO question (score, content, quiz_id) VALUES (1, 'Quiz2-Q1 - One valid answer', 2);
INSERT INTO question (score, content, quiz_id) VALUES (1, 'Quiz2-Q2 - One valid answer', 2);

INSERT INTO question (score, content, quiz_id) VALUES (1, 'Quiz3-Q1 - One valid answer', 3);
INSERT INTO question (score, content, quiz_id) VALUES (2, 'Quiz3-Q2 - Two valid answers', 3);

INSERT INTO question (score, content, quiz_id) VALUES (2, 'Quiz4-Q1 - Two valid answers', 4);
INSERT INTO question (score, content, quiz_id) VALUES (2, 'Quiz4-Q2 - Two valid answers', 4);


INSERT INTO answer (correct, content, question_id) VALUES (0, 'Q1-A1 - not valid', 1);
INSERT INTO answer (correct, content, question_id) VALUES (0, 'Q1-A2 - not valid', 1);
INSERT INTO answer (correct, content, question_id) VALUES (0, 'Q2-A1 - not valid', 2);
INSERT INTO answer (correct, content, question_id) VALUES (1, 'Q2-A2 - valid', 2);

INSERT INTO answer (correct, content, question_id) VALUES (0, 'Q3-A1 - not valid', 3);
INSERT INTO answer (correct, content, question_id) VALUES (1, 'Q3-A2 - valid', 3);
INSERT INTO answer (correct, content, question_id) VALUES (0, 'Q4-A1 - not valid', 4);
INSERT INTO answer (correct, content, question_id) VALUES (1, 'Q4-A2 - valid', 4);

INSERT INTO answer (correct, content, question_id) VALUES (0, 'Q5-A1 - not valid', 5);
INSERT INTO answer (correct, content, question_id) VALUES (1, 'Q5-A2 - valid', 5);
INSERT INTO answer (correct, content, question_id) VALUES (1, 'Q6-A1 - valid', 6);
INSERT INTO answer (correct, content, question_id) VALUES (1, 'Q6-A2 - valid', 6);

INSERT INTO answer (correct, content, question_id) VALUES (1, 'Q7-A1 - valid', 7);
INSERT INTO answer (correct, content, question_id) VALUES (1, 'Q7-A2 - valid', 7);
INSERT INTO answer (correct, content, question_id) VALUES (1, 'Q8-A1 - valid', 8);
INSERT INTO answer (correct, content, question_id) VALUES (1, 'Q8-A2 - valid', 8);

INSERT INTO role (id, name) VALUES (1,'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'USER');

INSERT INTO user (email, name, password, role_id) VALUES ('test1@test.pl', 'User1', '$2a$04$gb9XG47BMLY9w/jVGnSmuOThZW7Gx3hbn.V0qETeU3kWXX8ppXYt2',1);
INSERT INTO user (email, name, password, role_id) VALUES ('test2@test.pl', 'User2', '$2a$04$6SDR9Hm53NTCXTWqPhJWiu.XyIJeCLKZwHdzbZuFICLbNP2BymSQa',2);

INSERT INTO take_quiz (score, start, finish, quiz_id, user_id) VALUES (1, '2001-01-01 01:01:01', '2011-01-01 01:01:01', 1, 1);
INSERT INTO take_quiz (score, start, finish, quiz_id, user_id) VALUES (1, '2001-01-01 01:01:01', '2011-01-01 01:01:01', 2, 1);
INSERT INTO take_quiz (score, start, finish, quiz_id, user_id) VALUES (2, '2001-01-01 01:01:01', '2011-01-01 01:01:01', 3, 2);
INSERT INTO take_quiz (score, start, finish, quiz_id, user_id) VALUES (0, '2001-01-01 01:01:01', '2011-01-01 01:01:01', 1, 2);
INSERT INTO take_quiz (score, start, finish, quiz_id, user_id) VALUES (4, '2001-01-01 01:01:01', '2011-01-01 01:01:01', 4, 2);

INSERT INTO take_quiz_answers (take_quiz_id, question_id, answer_id) VALUES (1, 1, 1);
INSERT INTO take_quiz_answers (take_quiz_id, question_id, answer_id) VALUES (1, 2, 3);
INSERT INTO take_quiz_answers (take_quiz_id, question_id, answer_id) VALUES (1, 3, 5);
INSERT INTO take_quiz_answers (take_quiz_id, question_id, answer_id) VALUES (1, 4, 7);