SELECT * FROM BOARD;
SELECT * FROM MEMBER;
SELECT * FROM GMEMBER;

delete from MEMBER where name = 'kim';
delete from GMEMBER where name = '홍길동';

/* Drop Triggers */
DROP TRIGGER TRI_Board_no;
DROP TRIGGER TRI_GMember_no;
DROP TRIGGER TRI_Member_no;


/* Drop Tables */
DROP TABLE Board CASCADE CONSTRAINTS;
DROP TABLE GMember CASCADE CONSTRAINTS;
DROP TABLE Member CASCADE CONSTRAINTS;

drop table board purge;
drop table member purge;
drop table gmember purge;



/* Drop Sequences */
DROP SEQUENCE SEQ_Board_no;
DROP SEQUENCE SEQ_GMember_no;
DROP SEQUENCE SEQ_Member_no;


/* Create Sequences */
CREATE SEQUENCE SEQ_Board_no INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_GMember_no INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_Member_no INCREMENT BY 1 START WITH 1;



/* Create Tables */
CREATE TABLE Board
(
   no number NOT NULL,
   title varchar2(100) NOT NULL,
   category varchar2(100) NOT NULL,
   writer varchar2(100),
   content varchar2(2000) NOT NULL,
   cnt number NOT NULL,
   regdate date DEFAULT SYSDATE NOT NULL,
   PRIMARY KEY (no)
);

INSERT INTO GMember VALUES (SEQ_Board_no.NEXTVAL,'name','email','password','gender','major', SYSDATE);

CREATE TABLE GMember
(
	no number(10) NOT NULL,
	name varchar2(10),
	email varchar2(100),
	password varchar2(100),
	gender varchar2(20),
	fileName varchar2(2000),
	fileRealName varchar2(2000),
	major varchar2(100),
	regdate date DEFAULT SYSDATE,
	PRIMARY KEY (no)
);

SELECT * FROM Member WHERE Email = 'mlolw2@naver.com';

CREATE TABLE Member
(
	no number(10) NOT NULL,
	name varchar2(10) NOT NULL,
	email varchar2(100) NOT NULL,
	password varchar2(100) NOT NULL,
	gender varchar2(20) NOT NULL,
	regdate date DEFAULT SYSDATE NOT NULL,
	PRIMARY KEY (no)	
);

SELECT * FROM GMember;
DROP TABLE GMember purge;



/* Create Triggers */

CREATE OR REPLACE TRIGGER TRI_Board_no BEFORE INSERT ON Board
FOR EACH ROW
BEGIN
	SELECT SEQ_Board_no.nextval
	INTO :new.no
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_GMember_no BEFORE INSERT ON GMember
FOR EACH ROW
BEGIN
	SELECT SEQ_GMember_no.nextval
	INTO :new.no
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_Member_no BEFORE INSERT ON Member
FOR EACH ROW
BEGIN
	SELECT SEQ_Member_no.nextval
	INTO :new.no
	FROM dual;
END;

/


DELETE FROM GMEMBER WHERE gender = '남'

======================================================


insert into board (no,title,category,email)  values (2, '안녕하세요', '없음', 'qqqq@qqqq');

CREATE TABLE Board
(
	no number,
	title varchar2(100),
	category varchar2(10),
	email varchar2(100)
);
			