do
$$
    begin
        if not exists(select from pg_type where typname = 'event_type') then
            create type "event_type" as enum ('INDOOR', 'OUTDOOR');
        end if;
    end
$$;
create table if not exists "event"
(
    id                varchar primary key                  default uuid_generate_v4(),
    type              event_type,
    name              varchar,
    longitude         double precision,
    latitude          double precision,
    location_name     varchar,
    address           varchar,
    green_space_id    varchar references green_space ("id"),
    description       text,
    creation_datetime timestamp without time zone not null default now(),
    begin_datetime    timestamp without time zone,
    end_datetime      timestamp without time zone
);
