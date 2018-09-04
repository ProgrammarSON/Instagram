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


create or replace PROCEDURE newsfeed_proc(  
  puser_id IN NEWSFEED.USER_ID%TYPE,
  pcontents IN NEWSFEED.CONTENTS%TYPE,
  pimage_path IN NEWSFEED.IMAGE_PATH%TYPE,
  pnewsfeed_id OUT NUMBER
  ) IS
  
  feedID NUMBER;

BEGIN
  feedID := newsfeed_seq.nextval;
  
  INSERT INTO newsfeed
  VALUES(feedID, puser_id, sysdate, pcontents, pimage_path);
 
  pnewsfeed_id := feedID;  
  commit;

EXCEPTION 
 WHEN OTHERS THEN
    rollback;
    pnewsfeed_id := -1;
END newsfeed_proc;