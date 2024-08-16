do
$$
    begin
        if not exists(select from pg_type where typname = 'animal_gender') then
            create type "animal_gender" as enum ('M', 'F');
        end if;
    end
$$;
create table if not exists "animal"
(
    id             varchar primary key default uuid_generate_v4(),
    name           varchar,
    is_endemic     bool    not null,
    green_space_id varchar not null,
    gender         animal_gender,
    number         integer
);
