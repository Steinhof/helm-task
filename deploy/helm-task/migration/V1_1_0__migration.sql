create table users
(
    id        bigserial
        constraint users_pk
            primary key,
    username  varchar,
    firstname varchar,
    lastname  varchar,
    email     varchar,
    phone     varchar
);

alter table users
    owner to postgres;

create unique index users_id_uindex
    on users (id);

