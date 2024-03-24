CREATE TABLE public."member" (
                                 id varchar(50) COLLATE "C" NOT NULL,
                                 "name" varchar(100) COLLATE "C" NOT NULL,
                                 password varchar(255) NOT NULL,
                                 email varchar(100) COLLATE "C" NOT NULL,
                                 validated boolean DEFAULT false NOT NULL,
                                 "create" timestamp with time zone DEFAULT now() NOT NULL,
                                 creator varchar(50) NOT NULL,
                                 "modify" timestamp with time zone NULL,
                                 modifier varchar(50) COLLATE "C" NULL,
                                 CONSTRAINT member_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public."member" IS '회원';

-- Column comments

COMMENT ON COLUMN public."member".id IS '회원 아이디';
COMMENT ON COLUMN public."member"."name" IS '회원 이름';
COMMENT ON COLUMN public."member".password IS '비밀번호';
COMMENT ON COLUMN public."member".email IS '이메일 주소';
COMMENT ON COLUMN public."member".validated IS '인증 여부';
COMMENT ON COLUMN public."member"."create" IS '생성시각';
COMMENT ON COLUMN public."member".creator IS '생성자';
COMMENT ON COLUMN public."member"."modify" IS '최종수정시각';
COMMENT ON COLUMN public."member".modifier IS '최종수정자';

ALTER TABLE public."member" ADD "role" varchar(255) NULL;
COMMENT ON COLUMN public."member"."role" IS '사용자 역할';


-----------------------------------------------------------


CREATE TABLE public.career (
                               id bigserial NOT NULL,
                               userid varchar(50) NOT NULL,
                               projectname varchar(100) NOT NULL,
                               company varchar(50) NOT NULL,
                               startdate date NOT NULL,
                               enddate date NOT NULL,
                               "role" varchar(50) NOT NULL,
                               area varchar(50) NOT NULL,
                               tech text NOT NULL,
                               "desc" text NULL,
                               "create" timestamp with time zone DEFAULT now() NOT NULL,
                               creator varchar(50) NOT NULL,
                               "modify" timestamp with time zone NULL,
                               modifier varchar(50) COLLATE "C" NULL,
                               CONSTRAINT career_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.career IS '이력';

-- Column comments

COMMENT ON COLUMN public.career.id IS '고유번호';
COMMENT ON COLUMN public.career.userid IS '회원아이디';
COMMENT ON COLUMN public.career.projectname IS '프로젝트명';
COMMENT ON COLUMN public.career.company IS '회사명';
COMMENT ON COLUMN public.career.startdate IS '시작일';
COMMENT ON COLUMN public.career.enddate IS '종료일';
COMMENT ON COLUMN public.career."role" IS '수행 역할';
COMMENT ON COLUMN public.career.area IS '영역';
COMMENT ON COLUMN public.career.tech IS '기술';
COMMENT ON COLUMN public.career."desc" IS '비고';
COMMENT ON COLUMN public.career."create" IS '생성시각';
COMMENT ON COLUMN public.career.creator IS '생성자';
COMMENT ON COLUMN public.career."modify" IS '최종수정시각';
COMMENT ON COLUMN public.career.modifier IS '최종수정자';
