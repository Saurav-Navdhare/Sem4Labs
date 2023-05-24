const createRelations = [`CREATE TABLE IF NOT EXISTS user_details(
    username varchar(40), 
	first_name varchar(30) NOT NULL, 
    last_name varchar(20) NOT NULL, 
    phone_no varchar(15) NOT NULL, 
    PRIMARY KEY(username)
);`
    ,
    `CREATE TABLE IF NOT EXISTS user_cred(
	user_id varchar(10), 
    username varchar(30) NOT NULL, 
    password varchar(50) NOT NULL,
    PRIMARY KEY(user_id),
    FOREIGN KEY(username) REFERENCES user_details(username)
);`
    ,
    `CREATE TABLE IF NOT EXISTS postal_codes(
	postal_code varchar(10), 
	city varchar(30) NOT NULL, 
    country varchar(30)  NOT NULL, 
    PRIMARY KEY(postal_code)
);`
    ,
    `CREATE TABLE IF NOT EXISTS user_addr(
	user_id varchar(10), 
    city varchar(30) NOT NULL, 
    address varchar(200) NOT NULL,
    postal_code varchar(10) NOT NULL,
    PRIMARY KEY(user_id), 
    FOREIGN KEY(postal_code) REFERENCES postal_codes(postal_code)
);`
    ,
    `CREATE TABLE IF NOT EXISTS payment_method(
	user_pay_id varchar(20),
    account_no varchar(20) NOT NULL, 
    pay_type varchar(15) NOT NULL, 
    PRIMARY KEY(user_pay_id)
);`
    ,
    `CREATE TABLE IF NOT EXISTS user_paymt_det(
	user_id varchar(10), 
    user_pay_id varchar(20) NOT NULL, 
    PRIMARY KEY(user_id), 
    FOREIGN KEY(user_pay_id) REFERENCES payment_method(user_pay_id)
);`
    ,
    `CREATE TABLE IF NOT EXISTS categories(
	category_id varchar(20), 
    category_name varchar(20) NOT NULL, 
    PRIMARY KEY(category_id)
);`
    ,
    `CREATE TABLE IF NOT EXISTS product_details(
	product_id varchar(20), 
    product_name varchar(50) NOT NULL, 
    category_id varchar(20) NOT NULL, 
    price decimal(10, 2) NOT NULL, 
    PRIMARY KEY(product_id), 
    FOREIGN KEY(category_id) REFERENCES categories(category_id)
);`
    ,
    `CREATE TABLE IF NOT EXISTS user_ordered_prod(
	user_id varchar(10),
	product_id  varchar(10) NOT NULL, 
	FOREIGN KEY(product_id) REFERENCES product_details(product_id),
	PRIMARY KEY(user_id)
);`
    ,
    `CREATE TABLE IF NOT EXISTS order_payment(
	total_order double NOT NULL,
	order_id varchar(10) NOT NULL,
	PRIMARY KEY(order_id)
);`
    ,
    `CREATE TABLE IF NOT EXISTS user_order_details(
	user_id varchar(10),
	total_order double NOT NULL, 
	order_id varchar(10) NOT NULL,
	PRIMARY KEY(user_id),
	FOREIGN KEY (order_id) references order_payment(order_id)
);`
    ,
    `CREATE TABLE IF NOT EXISTS order_details(
	order_item_id varchar(10),
	order_id varchar(10 ) NOT NULL,
	product_id  varchar(10) NOT NULL,
	quantity double NOT NULL,
	PRIMARY KEY(order_item_id),
	FOREIGN KEY(order_id) REFERENCES order_payment(order_id)
);`
    ,
    `CREATE TABLE IF NOT EXISTS prod_quant_sold(
	order_id varchar(10) ,
	product_id varchar(10) NOT NULL,
	quantity double NOT NULL,
	PRIMARY KEY(order_id)
);`
    ,
    `CREATE TABLE IF NOT EXISTS payment_status(
	payment_id varchar(20), 
	amount double NOT NULL,
	pay_status varchar(20) NOT NULL,
	PRIMARY KEY(payment_id)
);`
    ,
    `CREATE TABLE IF NOT EXISTS order_status(
	user_id varchar(10),
	order_id varchar(10) NOT NULL,
	payment_id varchar(20) NOT NULL,
	PRIMARY KEY(user_id),
    FOREIGN KEY(payment_id) REFERENCES payment_status(payment_id)
);`
]

module.exports = createRelations;