create table instrument
  (
     ticker          varchar(50) primary key,
     instrument_type varchar(20)
  );

create table common_stock
  (
     ticker          varchar(50) primary key,
     price           decimal(20, 6),
     expected_return decimal(20, 6),
     volatility      decimal(20, 6)
  );

create table option
  (
     ticker            varchar(50) primary key,
     underlying_ticker varchar(50),
     right             varchar(10),
     time_to_maturity  decimal(20, 6),
     strike_price      decimal(20, 6)
  );
