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


create or replace PROCEDURE deleteComment_proc(  
  pcomment_id COMMENTS.COMMENT_ID%TYPE,
  pnewsfeed_id NEWSFEED.NEWSFEED_ID%TYPE,
  pcheck OUT NUMBER
  ) IS
  
  commentID NUMBER;

BEGIN
    pcheck := 1;
    
    DELETE FROM comments
    WHERE COMMENT_ID = pcomment_id;
    
    UPDATE NEWSFEED
    SET COMMENT_COUNT = COMMENT_COUNT - 1
    WHERE NEWSFEED_ID = pnewsfeed_id;
      
    commit;

EXCEPTION 
 WHEN OTHERS THEN
    rollback;
    pcheck := -1;
END deleteComment_proc;

create or replace PROCEDURE insertComment_proc(  
  puser_id IN NEWSFEED.USER_ID%TYPE,
  pnewsfeed_id IN NUMBER,
  pcontents IN COMMENTS.CONTENTS%TYPE,
  pcheck OUT NUMBER
  ) IS
  
  commentID NUMBER;

BEGIN
    pcheck := 1;
    commentID := comment_seq.nextval;
  
    INSERT INTO comments
    VALUES(commentID,puser_id,pnewsfeed_id,sysdate,pcontents);
  
    UPDATE NEWSFEED
    SET COMMENT_COUNT = COMMENT_COUNT + 1
    WHERE NEWSFEED_ID = pnewsfeed_id;
      
    commit;

EXCEPTION 
 WHEN OTHERS THEN
    rollback;
    pcheck := -1;
END insertComment_proc;

create or replace PROCEDURE insertLike_proc(  
  pnewsfeed_id IN NUMBER, 
  puser_id IN NEWSFEED.USER_ID%TYPE,
  pcheck OUT NUMBER
  ) IS
  
BEGIN
    pcheck := 1;
      
    INSERT INTO likes VALUES(pnewsfeed_id, puser_id);
  
    UPDATE NEWSFEED
    SET LIKE_COUNT = LIKE_COUNT + 1
    WHERE NEWSFEED_ID = pnewsfeed_id;
      
    commit;

EXCEPTION 
 WHEN OTHERS THEN
    rollback;
    pcheck := -1;
END insertLike_proc;

create or replace PROCEDURE deleteLike_proc(  
  pnewsfeed_id IN NUMBER, 
  puser_id IN NEWSFEED.USER_ID%TYPE,
  pcheck OUT NUMBER
  ) IS
  
BEGIN
    pcheck := 1;
      
    DELETE FROM likes 
    WHERE NEWSFEED_ID = pnewsfeed_id AND USER_ID = puser_id;
  
    UPDATE NEWSFEED
    SET LIKE_COUNT = LIKE_COUNT - 1
    WHERE NEWSFEED_ID = pnewsfeed_id;
      
    commit;

EXCEPTION 
 WHEN OTHERS THEN
    rollback;
    pcheck := -1;
END deleteLike_proc;

create or replace PROCEDURE newsfeed_proc(  
  puser_id IN NEWSFEED.USER_ID%TYPE,
  pcontents IN NEWSFEED.CONTENTS%TYPE,
  pimage_path IN NEWSFEED.IMAGE_PATH%TYPE,
  paddress IN NEWSFEED.ADDRESS%TYPE,
  plat IN NEWSFEED.LATITUDE%TYPE,
  plng IN NEWSFEED.LONGITUDE%TYPE,
  pnewsfeed_id OUT NUMBER
  ) IS
  
  feedID NUMBER;

BEGIN
  feedID := newsfeed_seq.nextval;
  
  INSERT INTO newsfeed
  VALUES(feedID, puser_id, sysdate, pcontents, pimage_path,0,0,paddress,plat,plng);
 
  pnewsfeed_id := feedID;  
  commit;

EXCEPTION 
 WHEN OTHERS THEN
    rollback;
    pnewsfeed_id := -1;
END newsfeed_proc;



create or replace PROCEDURE deletenewsfeed_proc(  
  pnewsfeed_id IN NUMBER, 
  pcheck OUT NUMBER
  ) IS
  
BEGIN
    pcheck := 1;
      
    DELETE FROM hashtag 
    WHERE NEWSFEED_ID = pnewsfeed_id;
  
    DELETE FROM likes
    WHERE NEWSFEED_ID = pnewsfeed_id;
    
    DELETE FROM reply r
    WHERE EXISTS(
        SELECT 1
        FROM comments c
        WHERE r.comment_id = c.comment_id AND c.newsfeed_id = pnewsfeed_id
     );
     
    DELETE FROM COMMENTS
    WHERE NEWSFEED_ID = pnewsfeed_id;
     
    DELETE FROM newsfeed
    WHERE NEWSFEED_ID = pnewsfeed_id;
    
    commit;

EXCEPTION 
 WHEN OTHERS THEN
    rollback;
    pcheck := -1;
END deletenewsfeed_proc;


create or replace PROCEDURE modify_userinfo_proc(  
  o_user_id IN MEMBER.USERNAME%TYPE,
  puser_id IN MEMBER.USER_ID%TYPE,
  
  pusername IN MEMBER.USERNAME%TYPE,
  p_password IN MEMBER.PASSWORD%TYPE,
  pcontents IN MYFEED.CONTENTS%TYPE,
  p_profile_img IN MYFEED.PROFILE_IMG%TYPE,
  pcheck OUT NUMBER
  ) IS
  
BEGIN
    pcheck := 1;
      
    if(p_password = 'no') then
      UPDATE member
      SET user_id = puser_id, 
          USERNAME = pusername
      WHERE user_id = o_user_id;
    else
      UPDATE member
      SET user_id = puser_id, 
          USERNAME = pusername,
          PASSWORD = p_password
      WHERE user_id = o_user_id;
    end if;
    
    UPDATE myfeed
    SET user_id = puser_id,
        contents = pcontents,
        profile_img =  p_profile_img
    WHERE user_id = o_user_id;
    
    if(o_user_id != puser_id) then
      UPDATE COMMENTS
      SET user_id = puser_id
      WHERE user_id = o_user_id;
    
      UPDATE follow
      SET user_id = puser_id,
          following = puser_id
      WHERE user_id = o_user_id OR following = o_user_id;
    
      UPDATE likes
      SET USER_ID = puser_id
      WHERE user_id = o_user_id;
    
      UPDATE NEWSFEED
      SET user_id = puser_id
      WHERE user_id = o_user_id;
    
      UPDATE reply
      SET user_id = puser_id
      WHERE user_id = o_user_id;   
    end if;
    commit;

EXCEPTION 
 WHEN OTHERS THEN
    rollback;
    pcheck := -1;
END modify_userinfo_proc;

