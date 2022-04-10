create table Stock_and_option_details (
  ticker VARCHAR(255) PRIMARY KEY, 
  stock_name VARCHAR(255), 
  type INTEGER, 
  expected_return DECIMAL(0, 2), 
  annualized_sd DECIMAL(0, 2), 
  related_common_stock_ticker VARCHAR(255), 
  time_to_Maturity INTEGER,
  current_price Double, 
  strike_price Double,
  call_option_price Double, 
  put_option_price Double, 
);
