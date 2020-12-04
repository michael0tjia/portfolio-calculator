create table Stock
  (
     ticker         varchar(50) primary key,
     name           varchar(255),
     price          decimal(20, 6),
     Volatility     decimal(20, 6),
     ExpectedReturn decimal(20, 6)
  );

create table StockOption
  (
     ticker         varchar(50) primary key,
     Underlying     varchar(50),
     ExpirationDate date,
     Strike         decimal(20, 6),
     Right          varchar(10),
     Multiplier     decimal(20, 6)
  );

insert into Stock
values     ('NVDA',
            'NVDA',
            110.0,
            0.35,
            0.45);

insert into Stock
values     ('AGNC',
            'AGNC Investment Corp',
            110.0,
            0.23,
            0.15);

insert into Stock
values     ('ATAX',
            'America First Multifamily Investors LP',
            255.0,
            0.78,
            0.65);

insert into Stock
values     ('STX',
            'Seagate Technology PLC',
            142.0,
            0.19,
            0.23);

insert into Stock
values     ('CG',
            'The Carlyle Group LP',
            53.5,
            0.95,
            0.45); 
