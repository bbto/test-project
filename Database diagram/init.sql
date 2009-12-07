grant all on sres.* to 'sres'@'localhost' identified by 'default';
use sres;
insert into users (email,password,firstname,lastname,role) values ('admin@sres.com',password('default'),'Admin','',0);
insert into users (email,password,firstname,lastname,role) values ('professor@sres.com',password('default'),'Professor','',1);
insert into users (email,password,firstname,lastname,role) values ('student@sres.com',password('default'),'Student','',2);
insert into users (email,password,firstname,lastname,role) values ('student1@sres.com',password('default'),'Student1','',2);
insert into users (email,password,firstname,lastname,role) values ('student2@sres.com',password('default'),'Student2','',2);
insert into users (email,password,firstname,lastname,role) values ('student3@sres.com',password('default'),'Student3','',2);
insert into users (email,password,firstname,lastname,role) values ('student4@sres.com',password('default'),'Student4','',2);
