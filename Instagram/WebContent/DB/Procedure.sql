create or replace PROCEDURE following_proc(  
  puser_id IN myfeed.user_id%TYPE,
  pfollow_id IN myfeed.user_id%TYPE
  ) IS

BEGIN
  UPDATE myfeed
  SET FOLLOWING_NUM = FOLLOWING_NUM + 1
  WHERE user_id = puser_id;
  
  UPDATE myfeed
  SET FOLLOWER_NUM = FOLLOWER_NUM + 1
  WHERE user_id = pfollow_id;
  
  commit;
END following_proc;

create or replace PROCEDURE unfollow_proc(  
  puser_id IN myfeed.user_id%TYPE,
  pfollow_id IN myfeed.user_id%TYPE
  ) IS

BEGIN
  UPDATE myfeed
  SET FOLLOWING_NUM = FOLLOWING_NUM - 1
  WHERE user_id = puser_id;
  
  UPDATE myfeed
  SET FOLLOWER_NUM = FOLLOWER_NUM - 1
  WHERE user_id = pfollow_id;
  commit;
END unfollow_proc;