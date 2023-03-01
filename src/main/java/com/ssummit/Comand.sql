create table checkpoints
(
    id                bigint not null
        primary key,
    title             varchar(255),
    description       text,
    latitude          double precision,
    longitude         double precision,
    created_by        varchar(255),
    created_timestamp timestamp(6),
    deleted_by        varchar(255),
    deleted_timestamp timestamp(6),
    is_deleted        boolean,
    updated_by        varchar(255),
    updated_timestamp timestamp(6)
);
create table item_types
(
    id                bigint not null
        primary key,
    title             varchar(255),
    description       text,
    created_by        varchar(255),
    created_timestamp timestamp(6),
    deleted_by        varchar(255),
    deleted_timestamp timestamp(6),
    is_deleted        boolean,
    updated_by        varchar(255),
    updated_timestamp timestamp(6)
);
create table message_types
(
    id          bigint not null
        primary key,
    title       varchar(255),
    description text
);
create table messages
(
    id                bigint not null
        primary key,
    created_by        varchar(255),
    created_timestamp timestamp(6),
    title             varchar(255),
    message_type_id   bigint
        constraint fk_message_messagetype
            references message_types,
    description       text
);
create table tour_equipment
(
    id                bigint not null
        primary key,
    date_of_issue     timestamp(6),
    created_by        varchar(255),
    created_timestamp timestamp(6),
    deleted_by        varchar(255),
    deleted_timestamp timestamp(6),
    is_deleted        boolean,
    updated_by        varchar(255),
    updated_timestamp timestamp(6)
);
create table items
(
    id                       bigint not null
        primary key,
    title                    varchar(255),
    brand                    varchar(255),
    description              text,
    manufacture_date         date,
    available                boolean,
    expiration_date          date,
    inventory_number         bigint,
    is_verifiable            boolean,
    latest_verification_date date,
    verification_certificate varchar(255),
    verification_interval    interval month,
    item_type_id             bigint
        constraint fk_item_itemtype
            references item_types,
    tour_equipment_id        bigint
        constraint fk_tour_equipment_items
            references tour_equipment,
    created_by               varchar(255),
    created_timestamp        timestamp(6),
    deleted_by               varchar(255),
    deleted_timestamp        timestamp(6),
    is_deleted               boolean,
    updated_by               varchar(255),
    updated_timestamp        timestamp(6)
);
create table roles
(
    id          bigint not null
        primary key,
    title       varchar(255),
    description text
);
create table routes
(
    id                bigint not null
        primary key,
    category          varchar(255),
    duration          integer,
    title             varchar(255),
    description       text,
    created_by        varchar(255),
    created_timestamp timestamp(6),
    deleted_by        varchar(255),
    deleted_timestamp timestamp(6),
    is_deleted        boolean,
    updated_by        varchar(255),
    updated_timestamp timestamp(6)
);
create table required_item_types
(
    route_id    bigint not null
        constraint fk_routes_itemtypes
            references routes,
    itemtype_id bigint not null
        constraint fk_itemtypes_routes
            references item_types,
    primary key (route_id, itemtype_id)
);
create table route_checkpoints
(
    route_id      bigint not null
        constraint fk_routes_checkpoints
            references routes,
    checkpoint_id bigint not null
        constraint fk_checkpoints_routes
            references checkpoints,
    primary key (route_id, checkpoint_id)
);
create table tour_applications
(
    id                            bigint not null
        primary key,
    application_date              date,
    application_registration_date date,
    incoming_post_number          varchar(255),
    outcoming_post_number         varchar(255),
    title                         varchar(255),
    description                   text,
    created_by                    varchar(255),
    created_timestamp             timestamp(6),
    deleted_by                    varchar(255),
    deleted_timestamp             timestamp(6),
    is_deleted                    boolean,
    updated_by                    varchar(255),
    updated_timestamp             timestamp(6)
);
create table users
(
    id                    bigint       not null
        primary key,
    role_id               bigint
        constraint fk_user_role
            references roles,

    login                 varchar(255) not null unique,
    password              varchar(255) not null,
    email                 varchar(255) not null unique,
    first_name            varchar(255),
    middle_name           varchar(255),
    last_name             varchar(255),
    birth_date            date,
    passport_no           varchar(255),
    address               text,
    phone                 varchar(255),
    change_password_token varchar(255),
    created_by            varchar(255),
    created_timestamp     timestamp(6),
    deleted_by            varchar(255),
    deleted_timestamp     timestamp(6),
    is_deleted            boolean,
    updated_by            varchar(255),
    updated_timestamp     timestamp(6)
);
create table tours
(
    id                      bigint not null
        primary key,
    title                   varchar(255),
    description             text,
    start_date              timestamp(6),
    end_date                timestamp(6),
    primary_guide_user_id   bigint
        constraint fk_tour_primaryguide
            references users,
    secondary_guide_user_id bigint
        constraint fk_tour_secondaryguide
            references users,
    route_id                bigint
        constraint fk_tour_route
            references routes,
    tour_application_id     bigint
        constraint fk_tour_tourapplication
            references tour_applications,
    tour_equipment_id       bigint
        constraint fk_tour_tour_equipment
            references tour_equipment,
    created_by              varchar(255),
    created_timestamp       timestamp(6),
    deleted_by              varchar(255),
    deleted_timestamp       timestamp(6),
    is_deleted              boolean,
    updated_by              varchar(255),
    updated_timestamp       timestamp(6)
);
create table checkpoint_marks
(
    id                  bigint not null
        primary key,
    message_send        boolean,
    actual_marked_time  timestamp(6),
    scheduled_mark_time timestamp(6),
    tour_id        bigint
        constraint fk_checkpoint_mark_tour
            references tours,
    checkpoint_id       bigint
        constraint fk_checkpoint
            references checkpoints

);
create table tour_participants
(
    tour_id bigint not null
        constraint fk_tours_users
            references tours,
    user_id bigint not null
        constraint fk_users_tours
            references users,
    primary key (tour_id, user_id)
);
create table tour_checkpoint_marks
(
    tour_id             bigint not null
        constraint fk_tours_checkpoint_marks
            references tours,
    checkpoint_marks_id bigint not null
        constraint uk_checkpoint_marks_tour
            unique
        references checkpoint_marks,
    primary key (tour_id, checkpoint_marks_id)
);