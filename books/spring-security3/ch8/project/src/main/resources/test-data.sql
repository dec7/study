insert into users(username, password, enabled) values ('admin','admin',true);
insert into authorities(username,authority) values ('admin','ROLE_USER');
insert into authorities(username,authority) values ('admin','ROLE_ADMIN');

insert into users(username, password, enabled) values ('admin2','admin2',true);
insert into authorities(username,authority) values ('admin2','ROLE_USER');
insert into authorities(username,authority) values ('admin2','ROLE_ADMIN');

insert into users(username, password, enabled) values ('guest','guest',true);
insert into authorities(username,authority) values ('guest','ROLE_USER');
commit;
