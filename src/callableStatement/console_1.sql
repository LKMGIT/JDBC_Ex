--  샘플 테이블
create table CODE1
(
    CID   int,
    cName varchar(50)
);

desc CODE1;

insert into CODE1(CID, cName)
select ifnull(max(CID), 0) + 1 as cld2, 'TEST' as cName2
from CODE1;

select *
from CODE1;

truncate CODE1; -- 데이터 날리기


drop procedure if exists input;
delimiter $$
create procedure input(in cData varchar(255), in cTname varchar(255), out resultMsg varchar(255))
begin
    SET @strsql = concat('insert into ', cTname, '(cid,cName)',
                         'select coalesce(max(cid),0)+1,? from ', cTname);
    -- 바인딩 변수 설정
    SET @cData = cData;
    SET resultMsg = 'INSERT SUCCESS!';

    prepare stmt from @strsql;
    execute stmt using @cData;

    deallocate prepare stmt;
    commit;
end $$

call input('이름', 'CODE1', @result);
select @result;


delete
from CODE1
where cName = 'test';


CREATE TABLE TB_MEMBER
(
    m_seq        INT AUTO_INCREMENT PRIMARY KEY, -- 자동 증가 시퀀스
    m_userid     VARCHAR(20) NOT NULL,
    m_pwd        VARCHAR(20) NOT NULL,
    m_email      VARCHAR(50) NULL,
    m_hp         VARCHAR(20) NULL,
    m_registdate DATETIME DEFAULT NOW(),         -- 기본값: 현재 날짜와 시간
    m_point      INT      DEFAULT 0
);


DROP PROCEDURE IF EXISTS SP_MEMBER_INSERT;
delimiter $$
CREATE PROCEDURE SP_MEMBER_INSERT(
    IN V_USERID VARCHAR(20),
    IN V_PWD VARCHAR(20),
    IN V_EMAIL VARCHAR(50),
    IN V_HP VARCHAR(20),
    OUT RTN_CODE INT
)
begin
    declare v_count int;

    select count(m_seq) into v_count from TB_MEMBER where m_userid = V_USERID;

    if v_count > 0 then
        set RTN_CODE = 100;
    else
        insert into TB_MEMBER(m_userid, m_pwd, m_email, m_hp)
        values (V_USERID, V_PWD, V_EMAIL, V_HP);
        set RTN_CODE = 200;
    end if;
    commit;
end $$

call SP_MEMBER_INSERT('apple', 'apple1234', 'apple@apple.com', '010-0000-0000', @RTN_CODE);
select @RTN_CODE;
call SP_MEMBER_INSERT('apple', 'apple1234', 'apple@apple.com', '010-0000-0000', @RTN_CODE);
select @RTN_CODE;
select * from TB_MEMBER;

DROP PROCEDURE IF EXISTS SP_MEMBER_LIST;
delimiter $$
create procedure SP_MEMBER_LIST()
begin
    select * from tb_member order by m_seq;
end $$
delimiter ;



-- 아이디로 출력
DROP PROCEDURE IF EXISTS SP_MEMBER;
delimiter $$
create procedure SP_MEMBER(in V_USERID varchar(255))
begin
    select * from tb_member where m_userid = V_USERID;
end $$
delimiter ;

-- 아이디로 삭제
DROP PROCEDURE IF EXISTS SP_DELETE;
delimiter $$
create procedure SP_DELETE(in V_USERID varchar (255), in V_USERPW varchar(255) )
begin
    delete from tb_member where m_userid = V_USERID and m_pwd = V_USERPW;

end $$
delimiter ;


-- 아이디로 확인하고 값 받아서 부분에 맞는 시작
DROP PROCEDURE IF EXISTS SP_UPDATE;
delimiter $$
create procedure SP_UPDATE(in NUM int,in V_DATA varchar(255), in V_USERID varchar(255))
begin
    CASE NUM
        when 1 then UPDATE tb_member SET m_pwd = V_DATA WHERE m_userid = V_USERID;
        when 2 then UPDATE tb_member SET m_email = V_DATA WHERE m_userid = V_USERID;
        when 3 then UPDATE tb_member SET m_hp = V_DATA WHERE m_userid = V_USERID;
    end CASE;
end $$
delimiter ;


