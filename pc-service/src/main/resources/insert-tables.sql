insert into instrument values ('CS001', 'COMMON_STOCK');
insert into instrument values ('CS002', 'COMMON_STOCK');
insert into instrument values ('CS003', 'COMMON_STOCK');
insert into instrument values ('CS004', 'COMMON_STOCK');
insert into instrument values ('CS005', 'COMMON_STOCK');
insert into instrument values ('CO001', 'OPTION');
insert into instrument values ('CO002', 'OPTION');
insert into instrument values ('CO003', 'OPTION');
insert into instrument values ('CO004', 'OPTION');
insert into instrument values ('CO005', 'OPTION');
insert into instrument values ('PO001', 'OPTION');
insert into instrument values ('PO002', 'OPTION');
insert into instrument values ('PO003', 'OPTION');
insert into instrument values ('PO004', 'OPTION');
insert into instrument values ('PO005', 'OPTION');

insert into common_stock values ('CS001', 100, 0.1, 0.1);
insert into common_stock values ('CS002', 200, 0.2, 0.2);
insert into common_stock values ('CS003', 300, 0.3, 0.3);
insert into common_stock values ('CS004', 400, 0.4, 0.4);
insert into common_stock values ('CS005', 500, 0.5, 0.5);

insert into option values ('CO001', 'CS001', 'CALL', 11, 110);
insert into option values ('CO002', 'CS002', 'CALL', 12, 220);
insert into option values ('CO003', 'CS003', 'CALL', 13, 330);
insert into option values ('CO004', 'CS004', 'CALL', 14, 440);
insert into option values ('CO005', 'CS005', 'CALL', 15, 550);

insert into option values ('PO001', 'CS001', 'PUT', 11, 90);
insert into option values ('PO002', 'CS002', 'PUT', 12, 180);
insert into option values ('PO003', 'CS003', 'PUT', 13, 270);
insert into option values ('PO004', 'CS004', 'PUT', 14, 360);
insert into option values ('PO005', 'CS005', 'PUT', 15, 450);
