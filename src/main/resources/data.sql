INSERT INTO role (id, name) VALUES (1,'SUPER_ADMIN');
INSERT INTO role (id, name) VALUES (2,'ADMIN');
INSERT INTO role (id, name) VALUES (3, 'USER');

INSERT INTO users (email, name, password, role_id) VALUES ('sadmin@test.pl', 'User0', '$2a$04$gb9XG47BMLY9w/jVGnSmuOThZW7Gx3hbn.V0qETeU3kWXX8ppXYt2',1);
INSERT INTO users (email, name, password, role_id) VALUES ('admin@test.pl', 'User1', '$2a$04$gb9XG47BMLY9w/jVGnSmuOThZW7Gx3hbn.V0qETeU3kWXX8ppXYt2',2);
INSERT INTO users (email, name, password, role_id) VALUES ('user@test.pl', 'User2', '$2a$04$6SDR9Hm53NTCXTWqPhJWiu.XyIJeCLKZwHdzbZuFICLbNP2BymSQa',3);

-- Sample logins with different roles, passwords hashed:
-- sadmin@test.pl / Password1
-- admin@test.pl / Password1
-- user@test.pl / Password2
