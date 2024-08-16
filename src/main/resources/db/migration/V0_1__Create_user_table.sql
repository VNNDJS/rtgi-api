do
$$
    begin
        if not exists(select from pg_type where typname = 'user_type') then
            create type "user_type" as enum ('COMMON', 'GREEN_REPRESENTATIVE');
        end if;
    end
$$;
create extension if not exists "uuid-ossp";
create table if not exists "user"
(
    id        varchar not null default uuid_generate_v4(),
    name      varchar,
    email     varchar,
    birthdate varchar,
    type      user_type,
    password  varchar
);
