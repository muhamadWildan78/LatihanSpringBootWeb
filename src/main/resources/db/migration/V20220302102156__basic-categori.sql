create table category
(
    category_id          integer primary key,
    department_id        integer,
    name character varying(100),
    description   character varying(100)
);