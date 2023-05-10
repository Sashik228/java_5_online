
CREATE SCHEMA "Test" DEFAULT CHARACTER SET utf8 ;
CREATE TABLE Test.Student(
    id bigint  primary key,
    first_name varchar(45),
    last_name varchar(45),
    age int
);
CREATE TABLE Test.Group
(
    id bigint  primary key,
    label varchar(45)
);
CREATE TABLE Test.Student_group
(
    student_id bigint not null,
    group_id bigint not null,
    primary key (student_id, group_id),
    foreign key (student_id) references Student(id),
    foreign key (group_id) references Group(id)
);
