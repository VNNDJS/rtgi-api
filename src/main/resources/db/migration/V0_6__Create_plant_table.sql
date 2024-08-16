create table if not exists "plant"
(
    id             varchar primary key default uuid_generate_v4(),
    name           varchar,
    is_endemic     bool    not null,
    green_space_id varchar not null,
    number         integer
);
