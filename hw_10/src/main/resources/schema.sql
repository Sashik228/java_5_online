CREATE SCHEMA public DEFAULT CHARACTER SET utf8 ;

create table student
(
    id bigint auto_increment primary key,
    first_name varchar(45),
    last_name varchar(45),
    age int
);

create table `group`
(
    id bigint auto_increment primary key,
    first_name varchar(45)
);

create table student_group
(
    student_id bigint not null,
    group_id bigint not null,
    primary key (student_id, group_id),
    foreign key (student_id) references student(id),
    foreign key (group_id) references `group`(id)
);