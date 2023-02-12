create table board_tb(
    id int auto_increment primary key,
    title varchar(100) not null,
    content longtext not null,
    thumbnail longtext,
    user_id int not null,
    created_at timestamp not null    
);

create table user_tb(
    id int auto_increment primary key,
    username varchar unique not null,
    password varchar not null,
    email varchar not null,
    created_at timestamp not null    
);

create table reply_tb(
    id int auto_increment primary key,
    comment varchar(100) not null,
    board_id int not null,
    user_id int not null,
    created_at timestamp not null    
);


