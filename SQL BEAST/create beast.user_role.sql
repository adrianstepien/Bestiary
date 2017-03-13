create table beast.user_role(
ID int auto_increment,
username char(40),
role char(40),
foreign key(username) references user(login),
primary key(ID)
);