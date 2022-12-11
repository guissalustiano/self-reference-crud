create table product(
    id bigserial primary key,
    slug varchar(255) not null unique check (slug <> ''),
    code varchar(64) not null unique check (code <> ''),
    name varchar(255) not null unique check (name <> ''),
    description text,
    created_at timestamp with time zone not null default now(),
    updated_at timestamp with time zone not null default now()
);