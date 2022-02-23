INSERT INTO role (id, name) VALUES (1,'SUPER_ADMIN');
INSERT INTO role (id, name) VALUES (2,'ADMIN');
INSERT INTO role (id, name) VALUES (3, 'USER');

INSERT INTO user (email, name, password, role_id) VALUES ('test0@test.pl', 'User0', '$2a$04$gb9XG47BMLY9w/jVGnSmuOThZW7Gx3hbn.V0qETeU3kWXX8ppXYt2',1);
INSERT INTO user (email, name, password, role_id) VALUES ('test1@test.pl', 'User1', '$2a$04$gb9XG47BMLY9w/jVGnSmuOThZW7Gx3hbn.V0qETeU3kWXX8ppXYt2',2);
INSERT INTO user (email, name, password, role_id) VALUES ('test2@test.pl', 'User2', '$2a$04$6SDR9Hm53NTCXTWqPhJWiu.XyIJeCLKZwHdzbZuFICLbNP2BymSQa',3);