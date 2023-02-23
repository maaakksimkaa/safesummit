create table roles
(
    id          bigint not null
        primary key,
    description varchar(255),
    title       varchar(255)
);

create table users
(
    id                bigint       not null
        primary key,
    created_by        varchar(255),
    created_timestamp timestamp(6),
    deleted_by        varchar(255),
    deleted_timestamp timestamp(6),
    is_deleted        boolean,
    updated_by        varchar(255),
    updated_timestamp timestamp(6),
    address           varchar(255),
    birth_date        timestamp(6),
    email             varchar(255),
    first_name        varchar(255),
    last_name         varchar(255),
    login             varchar(255) not null,
    middle_name       varchar(255),
    passport_no       varchar(255),
    password          varchar(255) not null,
    phone             varchar(255),
    role_id           bigint
        constraint fk_user_role
            references roles
);
create table message_types
(
    id          bigint not null
        primary key,
    description varchar(255),
    title       varchar(255)
);
create table messages
(
    id                bigint not null
        primary key,
    created_by        varchar(255),
    created_timestamp timestamp(6),
    deleted_by        varchar(255),
    deleted_timestamp timestamp(6),
    is_deleted        boolean,
    updated_by        varchar(255),
    updated_timestamp timestamp(6),
    description       varchar(255),
    title             varchar(255),
    message_type_id   bigint
        constraint fk_message_messagetype
            references message_types
);
create table checkpoints
(
    id                bigint not null
        primary key,
    created_by        varchar(255),
    created_timestamp timestamp(6),
    deleted_by        varchar(255),
    deleted_timestamp timestamp(6),
    is_deleted        boolean,
    updated_by        varchar(255),
    updated_timestamp timestamp(6),
    description       varchar(255),
    latitude          double precision,
    longitude         double precision,
    title             varchar(255)
);
create table checkpoint_marks
(
    id                  bigint not null
        primary key,
    actual_marked_time  timestamp(6),
    scheduled_mark_time timestamp(6),
    checkpoint_id       bigint
        constraint fk_checkpoint
            references checkpoints
);
create table routes
(
    id                bigint not null
        primary key,
    created_by        varchar(255),
    created_timestamp timestamp(6),
    deleted_by        varchar(255),
    deleted_timestamp timestamp(6),
    is_deleted        boolean,
    updated_by        varchar(255),
    updated_timestamp timestamp(6),
    category          varchar(255),
    description       varchar(255),
    duration          integer,
    title             varchar(255)

);
create table tour_applications
(
    id                            bigint not null
        primary key,
    created_by                    varchar(255),
    created_timestamp             timestamp(6),
    deleted_by                    varchar(255),
    deleted_timestamp             timestamp(6),
    is_deleted                    boolean,
    updated_by                    varchar(255),
    updated_timestamp             timestamp(6),
    application_date              timestamp(6),
    application_registration_date timestamp(6),
    description                   varchar(255),
    incoming_post_number          varchar(255),
    outcoming_post_number         varchar(255),
    title                         varchar(255)
);
create table tour_equipment
(
    id                bigint not null
        primary key,
    created_by        varchar(255),
    created_timestamp timestamp(6),
    deleted_by        varchar(255),
    deleted_timestamp timestamp(6),
    is_deleted        boolean,
    updated_by        varchar(255),
    updated_timestamp timestamp(6),
    date_of_issue     timestamp(6)
);
create table item_types
(
    id                bigint not null
        primary key,
    created_by        varchar(255),
    created_timestamp timestamp(6),
    deleted_by        varchar(255),
    deleted_timestamp timestamp(6),
    is_deleted        boolean,
    updated_by        varchar(255),
    updated_timestamp timestamp(6),
    description       varchar(255),
    title             varchar(255)
);
create table items
(
    id                       bigint not null
        primary key,
    created_by               varchar(255),
    created_timestamp        timestamp(6),
    deleted_by               varchar(255),
    deleted_timestamp        timestamp(6),
    is_deleted               boolean,
    updated_by               varchar(255),
    updated_timestamp        timestamp(6),
    available                boolean,
    brand                    varchar(255),
    description              varchar(255),
    expiration_date          timestamp(6),
    inventory_number         bigint,
    is_verifiable            boolean,
    latest_verification_date timestamp(6),
    manufacture_date         timestamp(6),
    title                    varchar(255),
    verification_certificate varchar(255),
    verification_interval    timestamp(6),
    item_type_id             bigint
        constraint fk_item_itemtype
            references item_types,
    tour_equipment_id        bigint
        constraint fk_tour_equipment_items
            references tour_equipment
);
create table tours
(
    id                      bigint not null
        primary key,
    created_by              varchar(255),
    created_timestamp       timestamp(6),
    deleted_by              varchar(255),
    deleted_timestamp       timestamp(6),
    is_deleted              boolean,
    updated_by              varchar(255),
    updated_timestamp       timestamp(6),
    description             varchar(255),
    end_date                timestamp(6),
    start_date              timestamp(6),
    title                   varchar(255),
    primary_guide_user_id   bigint
        constraint fk_tour_primaryguide
            references users,
    route_id                bigint
        constraint fk_tour_route
            references routes,
    secondary_guide_user_id bigint
        constraint fk_tour_secondaryguide
            references users,
    tour_application_id     bigint
        constraint fk_tour_tourapplication
            references tour_applications,
    tour_equipment_id       bigint
        constraint fk_tour_tour_equipment
            references tour_equipment
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
    route_id     bigint not null
        constraint fk_routes_checkpoints
            references routes,
    checkpoit_id bigint not null
        constraint fk_checkpoints_routes
            references checkpoints,
    primary key (route_id, checkpoit_id)
);
create table tour_checkpoint_marks
(
    tour_id             bigint not null
        constraint fk_tours_checkpoint_marks
            references tours,
    checkpoint_marks_id bigint not null
        constraint uk_checkpoint_marks_tour
            unique references checkpoint_marks,
    primary key (tour_id, checkpoint_marks_id)
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