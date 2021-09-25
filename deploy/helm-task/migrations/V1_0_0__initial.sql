create table role
(
    name        varchar not null
        constraint role_pk
            primary key,
    permissions character varying[]
);

alter table role
    owner to postgres;

create table users
(
    id       bigint  not null
        constraint user_pk
            primary key,
    login    varchar not null,
    password varchar not null,
    name     varchar not null,
    role     varchar not null
        constraint users_role_name_fk
            references role
            on update cascade
);

alter table users
    owner to postgres;

create unique index user_id_uindex
    on users (id);

create unique index users_login_uindex
    on users (login);

create unique index role_name_uindex
    on role (name);

create type permissions as enum ('USER_UPDATE', 'USER_READ', 'USER_DELETE');

alter type permissions owner to postgres;