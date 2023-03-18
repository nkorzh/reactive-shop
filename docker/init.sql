create table if not exists good
(
    id    bigserial primary key,
    name  text  not null,
    price float not null default 1.0
);

create table if not exists currency
(
    id   bigserial primary key,
    name text
);

create table if not exists "user"
(
    id          bigserial primary key,
    login       text unique not null,
    currency_id bigint      not null,
    foreign key (currency_id) references currency (id)
);

insert into good (name, price)
values ('Pete''s mom', 0.5),
       ('Cheddar 1 kg', 5.50);

insert into currency (name)
values ('usd'),
       ('eur'),
       ('rub');

insert into "user" (login, currency_id)
values ('nkorzh', 1),
       ('tony', 3);
π
