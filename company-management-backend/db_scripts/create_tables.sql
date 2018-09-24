CREATE TABLE users (
  email      varchar2(50),
  password   varchar2(250) not null,
  firstname  varchar2(20)  not null,
  lastname   varchar2(20)  not null,
  role       varchar2(15)  not null,
  department varchar2(15),
  CONSTRAINT pk_users PRIMARY KEY (email)
);

CREATE TABLE COMPANIES (
  id         varchar2(10),
  name       varchar2(30) not null,
  admin_user varchar2(50),
  constraint pk_company primary key (id),
  constraint fk_company_adm foreign key (admin_user) references users (email)
);
̥

create table departments (
  id              varchar2(15),
  name            varchar2(20),
  COMP_DEPT_ADMIN varchar2(50),
  company         varchar(10),
  constraint pk_department primary key (id),
  constraint fk_dept_adm foreign key (COMP_DEPT_ADMIN) references users (email) on delete set null,
  constraint fk_dept_comp foreign key (company) references COMPANIES (id) on delete cascade
);

alter table users
  add constraint fk_user_dept foreign key (department) references departments (id) on delete cascade;

CREATE table documents (
  id varchar2(15),
  file_name varchar2(50) not null,
  mime_type varchar2(250) not null,
  company varchar2(10),
  department varchar2(15),
  employee varchar2(50),
  uploaded_date timestamp,
  modified_date timestamp,
  constraint pk_document primary key (id),
  constraint fk_doc_comp foreign key (company) references companies(id) on delete cascade,
  constraint fk_doc_dept foreign key (department) references departments(id) on delete cascade ,
  constraint fk_doc_user foreign key (employee) references users(email) on delete cascade
)