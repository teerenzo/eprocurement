
create table tokens
(
    id          uuid primary key,
    token       varchar(255) not null,
    user_id     uuid      not null,
    created_at  timestamp    not null,
    expires_at  timestamp    not null,
    validated_at timestamp,
    version int8 not null,
    foreign key (user_id) references users (id)
);