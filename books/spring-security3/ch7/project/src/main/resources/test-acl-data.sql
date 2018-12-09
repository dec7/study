
-- class
insert into acl_class (class) values ('com.thebudding.book.security3.data.Category');

-- role SIDs
insert into acl_sid (principal, sid) values (false, 'ROLE_USER');
insert into acl_sid (principal, sid) values (false, 'ROLE_ADMIN');

-- object identity
insert into acl_object_identity (object_id_class,object_id_identity,parent_object,owner_sid,entries_inheriting)
select cl.id, 1, null, sid.id, false
from acl_class cl, acl_sid sid
where cl.class='com.thebudding.book.security3.data.Category' and sid.sid='ROLE_ADMIN';

-- ACE list
-- mask == R
insert into acl_entry (acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure)
select oi.id, 1, si.id, 1, true, true, true
from acl_object_identity oi, acl_sid si
where si.sid = 'ROLE_ADMIN';
