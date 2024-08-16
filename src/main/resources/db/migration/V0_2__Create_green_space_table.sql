alter table "user"
    add primary key ("id");
create table if not exists "green-space"
(
    id           varchar primary key default uuid_generate_v4(),
    user_id      varchar references "user" ("id"),
    area         double precision,
    description  text,
    ticket_price double precision,
    longitude    double precision,
    latitude     double precision,
    name         varchar,
    address      varchar
);
