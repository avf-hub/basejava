-- create table resume
create table resume
(
    uuid      char(36) not null
        constraint resume_pk
            primary key,
    full_name text not null
);

-- create table contact
create table contact
(
    id          serial
        constraint contact_pk
            primary key,
    resume_uuid char(36) not null
        constraint contact_resume_uuid_fk
            references resume
            on update restrict on delete cascade,
    type        text     not null,
    value       text     not null
);

create unique index contact_resume_uuid_type_uindex
    on contact (resume_uuid, type);

-- create table section
create table section
(
    id          serial
        constraint section_pk
            primary key,
    resume_uuid char(36) not null
        constraint section_resume_uuid_fk
            references resume
            on delete cascade,
    type        text     not null,
    content     text     not null
);

alter table section
    owner to postgres;

create unique index section_resume_uuid_type_uindex
    on section (resume_uuid, type);
