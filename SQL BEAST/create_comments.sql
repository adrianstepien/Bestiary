create table comments(
ID int primary key auto_increment,
User_Login char(40),
conetnt text(255), 
date_of_adding datetime,
foreign key (User_Login) references user(login)
);