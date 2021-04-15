insert into address
values(1, 'Lublin', '20-239', 'Kwiatowa 7');

insert into address
values(2, 'Lublin', '20-239', 'Różana 7');

insert into address
values(3, 'Warszawa', '20-239', 'Różana 7');

insert into department
values (1, 'name', 'address');

insert into employee
values (hibernate_sequence.nextval, 'Jola', 'Kowalska', 300000, 1, 1);

insert into employee
values (hibernate_sequence.nextval, 'Anna', 'Nowak', 450000, 2, null);

insert into employee
values (hibernate_sequence.nextval, 'Maria', 'Styczen', 320000, 3, null);

insert into employee
values (hibernate_sequence.nextval, 'Jan', 'Nowak', 350000, null, 1);