INSERT INTO follo VALUES('1','4');

INSERT INTO newsfeed
VALUES(newsfeed_seq.nextval, '4',sysdate,'num4 newsfeed');
commit;

SELECT n.contents, n.user_id, n.NEWSFEED_ID, n.FEED_DATE
FROM NEWSFEED n JOIN (SELECT following FROM follo
                      WHERE user_id = '100') P
ON n.user_id = p.following;

INSERT INTO reply
VALUES(reply_seq.nextval,'3','23',sysdate,'youtoo');
commit;

SELECT user_id,contents FROM reply
WHERE NEWSFEED_ID = 23
ORDER BY reply_date;