create table category(
    id bigserial primary key,
    slug varchar(255) not null unique,
    code varchar(255) not null unique,
    name varchar(255) not null unique,
    description text,
    created_at timestamp with time zone not null default now(),
    updated_at timestamp with time zone not null default now()
);

create table category_clousure(
    parent_id bigint not null references category(id),
    child_id bigint not null references category(id),
    depth integer not null check (depth >= 0),
    primary key (parent_id, child_id),
    created_at timestamp with time zone not null default now(),
    updated_at timestamp with time zone not null default now()
);

create table product(
    id bigserial primary key,
    slug varchar(255) not null unique check (slug <> ''),
    code varchar(64) not null unique check (code <> ''),
    name varchar(255) not null unique check (name <> ''),
    description text,
    created_at timestamp with time zone not null default now(),
    updated_at timestamp with time zone not null default now()
);