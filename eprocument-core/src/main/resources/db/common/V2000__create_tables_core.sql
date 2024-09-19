
create table configuration_property
(
   id uuid primary key not null,
   version int8 not null,
   is_framework_configuration boolean not null,
   application varchar (50)   not null,
   name varchar (50)   not null,
   display_name varchar (50)   not null,
   value varchar (255) not null,
   type varchar (20) not null check
  	(type in('INT','STRING','BOOLEAN')
   ),
   entity_state varchar(10) not null check (
 	 entity_state in ('ACTIVE', 'INACTIVE', 'SUSPENDED', 'DELETED')
   ),
   unique (application, name),
   unique (application, display_name)
);


create table users
(
    id uuid primary key not null,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    description varchar(20),
    email varchar(40),
    password varchar(200),
    national_id varchar(40) unique not null,
    account_locked boolean,
    enabled boolean,
    version int8 not null,
    entity_state varchar(10) not null check (
        entity_state in ('ACTIVE', 'INACTIVE', 'SUSPENDED', 'DELETED')
        )
);


create table roles
(
    id uuid primary key not null,
    name varchar(20) not null,
    description varchar(20),
    version int8 not null,
    entity_state varchar(10) not null check (
        entity_state in ('ACTIVE', 'INACTIVE', 'SUSPENDED', 'DELETED')
        )
);


create table users_roles
(
    id uuid primary key not null,
    user_id uuid not null,
    role_id uuid not null,
    version int8 not null,
    entity_state varchar(10) not null check (
        entity_state in ('ACTIVE', 'INACTIVE', 'SUSPENDED', 'DELETED')
        ),
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (role_id) references roles(id) on delete cascade
);



 