
    drop table board if exists;

    drop table card if exists;

    drop table comment if exists;

    drop table deck if exists;

    drop table role if exists;

    drop table user if exists;

    drop table user_board if exists;

    create table board (
        board_id bigint generated by default as identity,
        title varchar(255) not null,
        primary key (board_id)
    );
 
    create table card (
        card_id bigint generated by default as identity,
        contents varchar(255) not null,
        deck_id bigint,
        primary key (card_id)
    );

    create table comment (
        comment_id bigint generated by default as identity,
        card_id bigint,
        contents varchar(255) not null,
        create_time varchar(255),
        writer_name varchar(255),
        primary key (comment_id)
    );
 
    create table deck (
        deck_id bigint generated by default as identity,
        board_id bigint,
        title varchar(255) not null,
        primary key (deck_id)
    );
 
    create table role (
        role_id bigint generated by default as identity,
        role varchar(255),
        primary key (role_id)
    );
 
    create table user (
        id bigint generated by default as identity,
        email varchar(20) not null,
        password varchar(100),
        primary key (id)
    );
 
    create table user_board (
        user_id bigint not null,
        board_id bigint not null,
        primary key (user_id, board_id)
    );
 
    create table user_role (
        user_id bigint not null,
        role_id bigint not null,
        primary key (user_id, role_id)
    );
 
    alter table board 
        add constraint UK_mph7qe4yv41dlsoap3i3nojto unique (title);
 
    alter table user 
        add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);
 
    alter table card 
        add constraint FK6k0or7dj9m5qhnshnk9fpg8r1 
        foreign key (deck_id) 
        references deck;
 
    alter table comment 
        add constraint FKqgv5aujiclf0iihwxf4gmkf18 
        foreign key (card_id) 
        references card;
 
    alter table deck 
        add constraint FKqun63f1i6f7n6by4appt81i36 
        foreign key (board_id) 
        references board;
 
    alter table user_board 
        add constraint FKtmjpuif5sbn7e6e9bl5vo0bd6 
        foreign key (board_id) 
        references board;
 
    alter table user_board 
        add constraint FKhfgevw9wg1mt43a9qlhpcuxyw 
        foreign key (user_id) 
        references user;
 
    alter table user_role 
        add constraint FKa68196081fvovjhkek5m97n3y 
        foreign key (role_id) 
        references role;
 
    alter table user_role 
        add constraint FK859n2jvi8ivhui0rl0esws6o 
        foreign key (user_id) 
        references user;
