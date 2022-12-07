create table product(
    id bigserial primary key,
    code varchar(255) not null unique check (code <> ''),
    name varchar(255) not null,
    description varchar(1024),
    created_at timestamp with time zone not null default now(),
    updated_at timestamp with time zone not null default now()
);