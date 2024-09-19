alter table tokens
    add column entity_state varchar(10) not null check (
        entity_state in ('ACTIVE', 'INACTIVE', 'SUSPENDED', 'DELETED')
    );