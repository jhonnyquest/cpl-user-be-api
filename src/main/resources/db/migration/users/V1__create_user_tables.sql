-- Update this file with database structure and basic data (metadada, catalogs, etc)
-- Here you'll find an example
-- IMPORTANT: FileName must start with "V" + versionNumber + "__" and have .sql extension

CREATE TABLE cpl_users.users (
  id varchar(36) NOT NULL PRIMARY KEY,
  document_type varchar(3) DEFAULT NULL,
  document_number varchar(20) DEFAULT NULL,
  name varchar(45) DEFAULT NULL,
  last_name varchar(45) DEFAULT NULL,
  country varchar(4) DEFAULT NULL,
  city varchar(4) DEFAULT NULL,
  imei varchar(25) DEFAULT NULL,
  type varchar(3) DEFAULT NULL,
  status varchar(3) DEFAULT NULL,
  phone varchar(20) DEFAULT NULL,
  email varchar(36) DEFAULT NULL,
  password varchar(8) DEFAULT NULL,
  create_date TIMESTAMP DEFAULT NOW(),
  update_date TIMESTAMP DEFAULT NOW()
);


CREATE INDEX users_name ON cpl_users.users(name);
CREATE INDEX users_phone ON cpl_users.users(phone);

CREATE TABLE cpl_users.session (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(25) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL,
  `status` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




