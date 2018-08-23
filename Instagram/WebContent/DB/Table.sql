CREATE TABLE follo(
  user_id VARCHAR2(100),
  following VARCHAR2(100)
);

CREATE TABLE newsfeed(
  newsfeed_id NUMBER,
  user_id VARCHAR2(100),
  feed_date Date,
  contents VARCHAR2(4000)
);

CREATE TABLE reply(
  reply_id NUMBER,
  user_id VARCHAR2(100),
  newsfeed_id NUMBER,
  reply_date DATE,
  contents VARCHAR2(2000)
);