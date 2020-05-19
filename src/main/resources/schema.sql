create table if not exists taco_order
(
    id           bigserial   not null
        constraint taco_order_pk
            primary key,
    name         varchar(50) not null,
    cccvv        varchar(3)  not null,
    street       varchar(50) not null,
    city         varchar(50) not null,
    state        varchar(2)  not null,
    zip          varchar(10) not null,
    ccnumber     varchar(16) not null,
    ccexpiration varchar(5),
    placedat     timestamp   not null
);

create table if not exists taco
(
    id        bigserial   not null
        constraint taco_pk
            primary key,
    name      varchar(50) not null,
    createdat timestamp   not null
);

create table if not exists ingredient
(
    id   varchar(4)  not null
        constraint ingredient_pk
            primary key,
    name varchar(30) not null,
    type varchar(10) not null
);

create table if not exists taco_order_taco_mapper
(
    order_id bigint not null
        constraint taco_order_taco_mapper_order_id_fk
            references taco_order
            on update cascade on delete restrict,
    taco_id  bigint not null
        constraint order_tacos_taco_id_fk
            references taco
            on update cascade on delete restrict,
    constraint order_tacos_pk
        primary key (order_id, taco_id)
);

create table if not exists taco_ingredients_mapper
(
    taco_id       bigint     not null
        constraint tacos_ingredients_taco_id_taco_id_fk
            references taco
            on update cascade on delete cascade,
    ingredient_id varchar(4) not null
        constraint tacos_ingredients_ingredient_id_fk
            references ingredient
            on update cascade on delete restrict,
    constraint taco_ingredients_pk
        primary key (taco_id, ingredient_id)
);

