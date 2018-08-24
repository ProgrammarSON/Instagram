INSERT INTO FOLLOW VALUES('sjw','real');
INSERT INTO FOLLOW VALUES('sjw','chelsea');
INSERT INTO FOLLOW VALUES('sjw','manchester');
INSERT INTO FOLLOW VALUES('sjw','ronaldo');
INSERT INTO FOLLOW VALUES('ronaldo','real');
INSERT INTO FOLLOW VALUES('ronaldo','manchester');
INSERT INTO FOLLOW VALUES('ronaldo','juventus');
INSERT INTO FOLLOW VALUES('real','ronaldo');
INSERT INTO FOLLOW VALUES('juventus','ronaldo');



INSERT INTO newsfeed
VALUES(newsfeed_seq.nextval,'sjw',sysdate,'my first newsfeed');

INSERT INTO newsfeed
VALUES(newsfeed_seq.nextval,'sjw',sysdate,'my second newsfeed');

INSERT INTO newsfeed
VALUES(newsfeed_seq.nextval,'ronaldo',sysdate,'ronaldo first feed');

INSERT INTO newsfeed
VALUES(newsfeed_seq.nextval,'manchester',sysdate,'manchester first feed');

INSERT INTO newsfeed
VALUES(newsfeed_seq.nextval,'real',sysdate,'real first feed');

INSERT INTO newsfeed
VALUES(newsfeed_seq.nextval,'real',sysdate,'real second feed');

INSERT INTO newsfeed
VALUES(newsfeed_seq.nextval,'juventus',sysdate,'juventus first feed');

