create table example
(
    id          character varying(100) primary key,
    nama        character varying(100),
    last_update timestamp,
    last_date   date,
    salary      decimal,
    description text
);