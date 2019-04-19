create table personal_card
(
  id             bigint  not null
    constraint personal_card_pk
      primary key,
  surname        varchar not null,
  name           varchar not null,
  patronymic     varchar,
  parent_contact varchar,
  address        varchar not null,
  phone_number   varchar,
  mail           varchar
);

alter table personal_card
  owner to postgres;

create unique index personal_card_card_id_uindex
  on personal_card (id);

create table session
(
  semester_number integer not null,
  id              bigint  not null
    constraint session_pk
      primary key,
  year            integer not null
);

alter table session
  owner to postgres;

create unique index session_id_uindex
  on session (id);

create table subject
(
  id   bigint  not null
    constraint subject_pk
      primary key,
  name varchar not null
);

alter table subject
  owner to postgres;

create table assessment
(
  id         bigint  not null
    constraint performance_monitoring_pk
      primary key,
  subject_id bigint  not null
    constraint assessment_subject_id_fk
      references subject,
  session_id bigint  not null
    constraint performance_monitoring_session_id_fk
      references session,
  mark       integer not null
);

alter table assessment
  owner to postgres;

create unique index performance_monitoring_assessment_id_uindex
  on assessment (id);

create unique index performance_monitoring_subject_id_uindex
  on assessment (subject_id);

create unique index assessment_session_id_uindex
  on assessment (session_id);

create unique index subject_subject_id_uindex
  on subject (id);

create table auditory
(
  auditory_number integer   not null,
  corps           integer   not null,
  id              bigserial not null
    constraint auditory_pk
      primary key
);

alter table auditory
  owner to postgres;

create unique index auditory_id_uindex
  on auditory (id);

create unique index audiyory_auditory_number_uindex
  on auditory (auditory_number);

create table department
(
  auditory_id bigint    not null
    constraint department_auditory_id_fk
      references auditory,
  id          bigserial not null
    constraint department_pk
      primary key
);

alter table department
  owner to postgres;

create table lecturer
(
  pass_number   bigint    not null,
  surname       varchar   not null,
  name          varchar   not null,
  patronymic    varchar,
  phone_number  varchar,
  mail          varchar,
  id            bigserial not null
    constraint lecturer_pk
      primary key,
  department_id bigint    not null
    constraint lecturer_department_id_fk
      references department,
  password      varchar
);

alter table lecturer
  owner to postgres;

create table schedule
(
  id           bigint  not null
    constraint schedule_pk
      primary key,
  subject_id   bigint  not null
    constraint schedule_subject_id_fk
      references subject,
  auditory_id  bigint  not null
    constraint schedule_auditory_id_fk
      references auditory,
  lecturer_id  bigint  not null
    constraint schedule_lecturer_id_fk
      references lecturer,
  class_number integer not null,
  periodicity  integer not null,
  week         varchar
);

alter table schedule
  owner to postgres;

create table class
(
  id          bigint not null
    constraint class_pk
      primary key,
  date        date   not null,
  schedule_id bigint not null
    constraint class_schedule_id_fk
      references schedule
);

alter table class
  owner to postgres;

create unique index class_class_id_uindex
  on class (id);

create unique index class_schedule_uindex
  on class (schedule_id);

create unique index schedule_auditory_uindex
  on schedule (auditory_id);

create unique index schedule_lecturer_uindex
  on schedule (lecturer_id);

create unique index schedule_schedule_id_uindex
  on schedule (id);

create unique index schedule_subject_uindex
  on schedule (subject_id);

create unique index lecturer_pass_number_uindex
  on lecturer (pass_id);

create unique index lecturer_id_uindex
  on lecturer (id);

create unique index lecturer_department_id_uindex
  on lecturer (department_id);

create unique index department_id_uindex
  on department (id);

create unique index department_auditory_id_uindex
  on department (auditory_id);

create table gradebookNumber
(
  id               bigserial not null
    constraint gradebook_pk
      primary key,
  gradebook_number integer   not null,
  assessment_id    bigint    not null
    constraint gradebook_assessment_id_fk
      references assessment
);

alter table gradebookNumber
  owner to postgres;

create table student
(
  id               bigserial not null
    constraint student_pk
      primary key,
  gradebook_id     bigint    not null
    constraint student_gradebook_id_fk
      references gradebookNumber,
  group_id         bigint    not null,
  personal_card_id bigint    not null
    constraint student_personal_card_id_fk
      references personal_card,
  password         varchar
);

alter table student
  owner to postgres;

create unique index student_group_id_uindex
  on student (group_id);

create unique index student_personal_card_id_uindex
  on student (personal_card_id);

create unique index student_gradebook_number_uindex
  on student (gradebook_id);

create table "group"
(
  id           bigint  not null
    constraint group_pk
      primary key,
  group_number integer not null,
  course       integer not null,
  headman_id   bigint  not null
    constraint group_student_id_fk
      references student,
  curator_id   bigint  not null
    constraint group_lecturer_id_fk
      references lecturer
);

alter table "group"
  owner to postgres;

alter table student
  add constraint student_group_id_fk
    foreign key (group_id) references "group";

create unique index group_group_id_uindex
  on "group" (id);

create unique index group_headman_id_uindex
  on "group" (headman_id);

create unique index group_curator_id_uindex
  on "group" (curator_id);

create table attendance
(
  id         bigserial not null
    constraint attendance_pk
      primary key,
  student_id bigint    not null
    constraint attendance_student_id_fk
      references student,
  class_id   bigint    not null
    constraint attendance_class_id_fk
      references class,
  presence   boolean   not null
);

alter table attendance
  owner to postgres;

create unique index attendance_attendance_id_uindex
  on attendance (id);

create unique index attendance_student_uindex
  on attendance (student_id);

create unique index gradebook_gradebook_number_uindex
  on gradebookNumber (gradebook_number);

create unique index gradebook_id_uindex
  on gradebookNumber (id);

create unique index gradebook_assessment_id_uindex
  on gradebookNumber (assessment_id);

create table group_schedule
(
  shedule_id bigint not null
    constraint group_schedule_schedule_id_fk
      references schedule,
  gpoup_id   bigint not null
    constraint group_schedule_group_id_fk
      references "group"
);

alter table group_schedule
  owner to postgres;

create unique index group_schedule_gpoup_id_uindex
  on group_schedule (gpoup_id);

create unique index group_schedule_shedule_id_uindex
  on group_schedule (shedule_id);

create table student_token
(
  id         bigint not null
    constraint student_token_pk
      primary key,
  student_id bigint not null
    constraint student_token_student_id_fk
      references student,
  token      varchar
);

alter table student_token
  owner to postgres;

create unique index student_token_student_id_uindex
  on student_token (student_id);

create table lecturer_token
(
  id          bigint not null
    constraint lecturer_token_pk
      primary key,
  lecturer_id bigint not null
    constraint lecturer_token_lecturer_id_fk
      references lecturer,
  token       varchar
);

alter table lecturer_token
  owner to postgres;

create unique index lecturer_token_lecturer_id_uindex
  on lecturer_token (lecturer_id);

