CREATE TABLE follow(
  user_id VARCHAR2(100),
  following VARCHAR2(100)
);

CREATE TABLE newsfeed(
  newsfeed_id NUMBER,
  user_id VARCHAR2(100),
  feed_date Date,
  contents VARCHAR2(4000),
  image_path VARCHAR2(200),
  comment_count NUMBER,
  like_count NUMBER,
  address VARCHAR2(100),
  latitude VARCHAR2(100),
  longitude VARCHAR2(100)
);

CREATE TABLE comments(
  comment_id NUMBER,
  user_id VARCHAR2(100),
  newsfeed_id NUMBER,
  comment_date DATE,
  contents VARCHAR2(2000)
);

CREATE TABLE likes(
  newsfeed_id NUMBER,
  user_id VARCHAR2(100)
);

CREATE TABLE reply(
  reply_id NUMBER,
  comment_id NUMBER,
  user_id VARCHAR2(100),
  contents VARCHAR2(2000)
);

CREATE TABLE member(
  email VARCHAR2(100),
  username VARCHAR2(100),
  user_id VARCHAR2(100),
  password VARCHAR2(100)
);

CREATE TABLE myfeed(
  user_id VARCHAR2(100),
  feed_num NUMBER,
  follower_num NUMBER,
  following_num NUMBER,
  contents VARCHAR2(2000),
  profile_img VARCHAR2(200)
);

CREATE TABLE HASHTAG(
  hashtag_contents VARCHAR2(2000),
  newsfeed_id NUMBER
);