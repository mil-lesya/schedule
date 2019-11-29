create table personal_card
(
    id             bigserial not null
        constraint personal_card_pk
            primary key,
    surname        varchar   not null,
    name           varchar   not null,
    patronymic     varchar,
    parent_contact varchar,
    address        varchar   not null,
    phone_number   varchar,
    email          varchar
);

alter table personal_card
    owner to postgres;

create unique index personal_card_card_id_uindex
    on personal_card (id);

create table session
(
    semester_number integer   not null,
    id              bigserial not null
        constraint session_pk
            primary key,
    year            integer   not null
);

alter table session
    owner to postgres;

create unique index session_id_uindex
    on session (id);

create table subject
(
    id   bigserial not null
        constraint subject_pk
            primary key,
    name varchar   not null
);

alter table subject
    owner to postgres;

create table gradebook
(
    id               bigserial not null
        constraint gradebook_pk
            primary key,
    gradebook_number varchar   not null
);

alter table gradebook
    owner to postgres;

create unique index gradebook_id_uindex
    on gradebook (id);

create unique index gradebook_gradebook_number_uindex
    on gradebook (gradebook_number);

create table assessment
(
    id           bigserial not null
        constraint performance_monitoring_pk
            primary key,
    subject_id   bigint
        constraint assessment_subject_id_fk
            references subject,
    session_id   bigint
        constraint performance_monitoring_session_id_fk
            references session,
    mark         integer,
    gradebook_id bigint
        constraint assessment_gradebook_id_fk
            references gradebook
);

alter table assessment
    owner to postgres;

create unique index performance_monitoring_assessment_id_uindex
    on assessment (id);

create table pass
(
    id          bigserial not null
        constraint passes_pk
            primary key,
    pass_number varchar   not null
);

alter table pass
    owner to postgres;

create unique index passes_pass_number_uindex
    on pass (pass_number);

create table role
(
    id   bigserial not null
        constraint user_role_pk
            primary key,
    role varchar   not null
);

alter table role
    owner to postgres;

create table "user"
(
    id       bigserial not null
        constraint user_pk
            primary key,
    login    varchar   not null,
    password varchar   not null,
    role_id  bigint
        constraint user_user_role_id_fk
            references role
);

alter table "user"
    owner to postgres;

create table student
(
    id               bigserial not null
        constraint student_pk
            primary key,
    gradebook_id     bigint    not null
        constraint student_gradebook_id_fk
            references gradebook,
    group_id         bigint    not null,
    personal_card_id bigint    not null
        constraint student_personal_card_id_fk
            references personal_card,
    user_id          bigint
        constraint student_user_id_fk
            references "user"
);

alter table student
    owner to postgres;

create unique index student_personal_card_id_uindex
    on student (personal_card_id);

create unique index student_gradebook_number_uindex
    on student (gradebook_id);

create table lecturer
(
    pass_id       bigint                                              not null
        constraint lecturer_pass_id_fk
            references pass,
    surname       varchar                                             not null,
    name          varchar                                             not null,
    patronymic    varchar,
    phone_number  varchar,
    id            bigint default nextval('lecturer_id_seq'::regclass) not null
        constraint lecturer_pk
            primary key,
    department_id bigint                                              not null,
    user_id       bigserial
        constraint lecturer_user_id_fk
            references "user",
    email         varchar
);

alter table lecturer
    owner to postgres;

create unique index lecturer_id_uindex
    on lecturer (id);

create unique index lecturer_pass_number_uindex
    on lecturer (pass_id);

create table "group"
(
    id           bigint  not null
        constraint group_pk
            primary key,
    group_number integer not null,
    course       integer not null,
    headman_id   bigint
        constraint group_student_id_fk
            references student,
    curator_id   bigint
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

create unique index user_id_uindex
    on "user" (id);

create unique index user_login_uindex
    on "user" (login);

create unique index user_role_id_uindex
    on role (id);

create table corpus
(
    id     bigserial not null
        constraint case_pk
            primary key,
    number varchar   not null
);

alter table corpus
    owner to postgres;

create table auditory
(
    auditory_number varchar   not null,
    id              bigserial not null
        constraint auditory_pk
            primary key,
    case_id         bigint
        constraint auditory_case_id_fk
            references corpus
);

alter table auditory
    owner to postgres;

create unique index auditory_id_uindex
    on auditory (id);

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

create unique index department_id_uindex
    on department (id);

create unique index department_auditory_id_uindex
    on department (auditory_id);

create table schedule
(
    id           bigserial not null
        constraint schedule_pk
            primary key,
    subject_id   bigint    not null
        constraint schedule_subject_id_fk
            references subject,
    auditory_id  bigint
        constraint schedule_auditory_id_fk
            references auditory,
    lecturer_id  bigint
        constraint schedule_lecturer_id_fk
            references lecturer,
    class_number integer   not null,
    week         integer,
    group_id     bigint
);

alter table schedule
    owner to postgres;

create unique index schedule_schedule_id_uindex
    on schedule (id);

create table class
(
    id          bigserial not null
        constraint class_pk
            primary key,
    date        date      not null,
    schedule_id bigint    not null
        constraint class_schedule_id_fk
            references schedule
);

alter table class
    owner to postgres;

create unique index class_class_id_uindex
    on class (id);

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

create unique index case_number_uindex
    on corpus (number);

create function register_student(p_login character varying, p_password character varying, p_address character varying,
                                 p_email character varying, p_name character varying,
                                 p_parent_contact character varying, p_patronymic character varying,
                                 p_phone_number character varying, p_surname character varying, p_group_number integer,
                                 p_course integer) returns integer
    language plpgsql
as
$$
BEGIN
    INSERT INTO public.user (login, password, role_id) VALUES (p_login, p_password, 1);
    INSERT INTO personal_card (address, email, name, parent_contact, patronymic, phone_number, surname)
    VALUES (p_address, p_email, p_name, p_parent_contact, p_patronymic, p_phone_number, p_surname);
    INSERT INTO student (gradebook_id, group_id, personal_card_id, user_id)
    VALUES ((SELECT id from gradebook where gradebook_number = p_login),
            (SELECT id from "group" where (course = p_course AND group_number = p_group_number)),
            (currval(pg_get_serial_sequence('personal_card', 'id'))),
            (currval(pg_get_serial_sequence('user', 'id'))));
    RETURN (currval(pg_get_serial_sequence('student', 'id')));
END;
$$;

alter function register_student(varchar, varchar, varchar, varchar, varchar, varchar, varchar, varchar, varchar, integer, integer) owner to postgres;

create function take_group_schedule(p_group_id bigint) returns refcursor
    language plpgsql
as
$$
DECLARE
    schedules REFCURSOR;
BEGIN
    OPEN schedules FOR
        SELECT *
        FROM schedule
        WHERE schedule.group_id = p_group_id;
    RETURN schedules;
END;
$$;

alter function take_group_schedule(bigint) owner to postgres;

