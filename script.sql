create table contact_types
(
    id          int auto_increment
        primary key,
    name        varchar(48)  not null,
    description varchar(256) null
)
    charset = utf8;

create table location_categories
(
    id          int auto_increment
        primary key,
    name        varchar(48)  not null,
    description varchar(256) null
)
    charset = utf8;

create table location_levels
(
    id   int auto_increment
        primary key,
    name varchar(24) not null
)
    charset = utf8;

create table location_panels
(
    id   int auto_increment
        primary key,
    name varchar(24) not null
)
    charset = utf8;

create table location_regions
(
    id          int auto_increment
        primary key,
    name        varchar(48)  not null,
    description varchar(256) null
)
    charset = utf8;

create table location_rooms
(
    id   int auto_increment
        primary key,
    name varchar(24) not null
)
    charset = utf8;

create table location_walls
(
    id   int auto_increment
        primary key,
    name varchar(24) not null
)
    charset = utf8;

create table states
(
    id                  int auto_increment
        primary key,
    states_Abbreviation varchar(2)  not null,
    states_Name         varchar(30) not null
)
    charset = utf8;

create table location_contacts
(
    id         int auto_increment
        primary key,
    first_name varchar(24)  not null,
    last_name  varchar(24)  not null,
    phone      varchar(15)  null,
    fax        varchar(15)  null,
    email      varchar(128) null,
    address    varchar(64)  null,
    city       varchar(24)  not null,
    state_id   int          not null,
    zipcode    varchar(11)  not null,
    type_id    int          not null,
    constraint contacts_state_fk
        foreign key (state_id) references states (id),
    constraint contacts_type_fk
        foreign key (type_id) references contact_types (id)
)
    charset = utf8;

create table locations
(
    id        int auto_increment
        primary key,
    name      varchar(48) not null,
    phone     varchar(15) null,
    fax       varchar(15) null,
    address   varchar(64) null,
    city      varchar(24) not null,
    state_id  int         not null,
    zipcode   varchar(11) not null,
    region_id int         null,
    constraint locations_region_fk
        foreign key (region_id) references location_regions (id),
    constraint locations_state_fk
        foreign key (state_id) references states (id)
)
    charset = utf8;

create table location_category
(
    location_id int not null,
    category_id int not null,
    primary key (location_id, category_id),
    constraint category_fk
        foreign key (category_id) references location_categories (id),
    constraint category_location_fk
        foreign key (location_id) references locations (id)
            on delete cascade
)
    charset = utf8;

create table location_contact
(
    location_id int not null,
    contact_id  int not null,
    primary key (location_id, contact_id),
    constraint contact_fk
        foreign key (contact_id) references location_contacts (id),
    constraint location_fk
        foreign key (location_id) references locations (id)
            on delete cascade
)
    charset = utf8;

create table location_level
(
    location_id int not null,
    level_id    int not null,
    primary key (location_id, level_id),
    constraint level_fk
        foreign key (level_id) references location_levels (id),
    constraint locations_fk
        foreign key (location_id) references locations (id)
            on delete cascade
)
    charset = utf8;

create table location_notes
(
    id          int auto_increment
        primary key,
    note        varchar(1024) null,
    location_id int           not null,
    constraint notes_location_fk
        foreign key (location_id) references locations (id)
            on delete cascade
)
    charset = utf8;

create table location_room
(
    location_id int not null,
    level_id    int not null,
    room_id     int not null,
    primary key (location_id, level_id, room_id),
    constraint location_level_fk
        foreign key (location_id, level_id) references location_level (location_id, level_id)
            on delete cascade,
    constraint room_fk
        foreign key (room_id) references location_rooms (id)
)
    charset = utf8;

create table location_wall
(
    location_id int not null,
    level_id    int not null,
    room_id     int not null,
    wall_id     int not null,
    primary key (location_id, level_id, room_id, wall_id),
    constraint location_wall_fk
        foreign key (location_id, level_id, room_id) references location_room (location_id, level_id, room_id)
            on delete cascade,
    constraint wall_fk
        foreign key (wall_id) references location_walls (id)
)
    charset = utf8;

create table location_panel
(
    location_id int           not null,
    level_id    int           not null,
    room_id     int           not null,
    wall_id     int           not null,
    panel_id    int           not null,
    height      float(5, 3)   not null,
    width       float(5, 3)   not null,
    description varchar(1024) null,
    primary key (location_id, level_id, room_id, wall_id, panel_id),
    constraint location_panel_fk
        foreign key (location_id, level_id, room_id, wall_id) references location_wall (location_id, level_id, room_id, wall_id)
            on delete cascade,
    constraint panel_fk
        foreign key (panel_id) references location_panels (id)
)
    charset = utf8;

create table user_types
(
    id          int auto_increment
        primary key,
    name        varchar(48)  not null,
    description varchar(256) null
)
    charset = utf8;

create table users
(
    id         int auto_increment
        primary key,
    username   varchar(24)   not null,
    password   varchar(128)  not null,
    first_name varchar(24)   not null,
    last_name  varchar(24)   not null,
    phone      varchar(15)   null,
    fax        varchar(15)   null,
    email      varchar(128)  null,
    address    varchar(64)   null,
    city       varchar(24)   not null,
    state_id   int           not null,
    zipcode    varchar(11)   not null,
    type_id    int default 1 null,
    constraint users_state_fk
        foreign key (state_id) references states (id),
    constraint users_type_fk
        foreign key (type_id) references user_types (id)
)
    charset = utf8;

create table user_locations
(
    admin_id    int not null,
    location_id int not null,
    constraint user_admin_location_fk
        foreign key (admin_id) references users (id)
            on delete cascade,
    constraint user_location_fk
        foreign key (location_id) references locations (id)
            on delete cascade
)
    charset = utf8;

create table user_users
(
    admin_id int not null,
    user_id  int not null,
    primary key (admin_id, user_id),
    constraint user_admin_fk
        foreign key (admin_id) references users (id)
            on delete cascade
)
    charset = utf8;


