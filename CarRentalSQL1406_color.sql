DROP TABLE emergency;
DROP TABLE reservation;
DROP TABLE customer;
DROP TABLE car;

CREATE TABLE customer (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  name VARCHAR(45) NOT NULL,
  surname VARCHAR(45) NOT NULL,
  mail VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL DEFAULT 'password',
  login_key VARCHAR(45),
  last_update TIMESTAMP default current_timestamp,
  PRIMARY KEY (id),
  UNIQUE (mail)
);
CREATE TABLE car (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  brand VARCHAR(45) NOT NULL,
  model VARCHAR(45) NOT NULL,
  doors INTEGER NOT NULL,
  yearprod INTEGER NOT NULL,
  boot INTEGER NOT NULL,
  drive VARCHAR(45) NOT NULL,
  color VARCHAR(45) NOT NULL,
  fuelCap INTEGER NOT NULL,
  fuelType VARCHAR(45) NOT NULL,
  range INTEGER NOT NULL,
  gearbox VARCHAR(45) NOT NULL,
  gears INTEGER NOT NULL,
  dayCost FlOAT NOT NULL,
  imageUrl VARCHAR(255) DEFAULT 'https://www.grifor.com/wp-content/uploads/2016/03/4.jpg',
  photo BLOB(2147483647),
  last_update TIMESTAMP default current_timestamp,
  PRIMARY KEY (id)
);
CREATE TABLE reservation (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  car_id INTEGER NOT NULL,
  customer_id INTEGER NOT NULL,
  from_date date NOT NULL,
  to_date date NOT NULL,
  last_update TIMESTAMP default current_timestamp,
  PRIMARY KEY (id),
  FOREIGN KEY (car_id) REFERENCES car (id),
  FOREIGN KEY (customer_id) REFERENCES customer (id)
);
CREATE TABLE emergency (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  reservation_id INTEGER NOT NULL,
  lat DOUBLE NOT NULL,
  lon DOUBLE NOT NULL,
  message VARCHAR(255) NOT NULL,
  actual BOOLEAN NOT NULL DEFAULT true,
  reported TIMESTAMP default current_timestamp,
  last_update TIMESTAMP default current_timestamp,
  PRIMARY KEY (id),
  FOREIGN KEY (reservation_id) REFERENCES reservation (id)
);

insert into customer(name, surname, mail) values ('Tomek', 'Boosl', 'tombs@wp.pl');
insert into customer(name, surname, mail) values ('Tomek2', 'Boosl2', 'tombs2@wp.pl');
insert into car(brand, model, doors, yearprod, boot, drive, color, fuelcap, fueltype, range, gearbox, gears, daycost, imageUrl)
        values ('Volvo', 'V40', 5, 2015, 60, '2X4', 'silver', 50, 'd', 450, 'M', 5, 59.99, 'https://photos-1.carwow.co.uk/blog/1600/Bright-Silver.jpg');
insert into car(brand, model, doors, yearprod, boot, drive, color, fuelcap, fueltype, range, gearbox, gears, daycost, imageUrl)
        values ('BMW', 'X7', 4, 2018, 80, '4X4', 'white', 56, 'b', 590, 'A', 6, 65.90, 'https://www.bmw-dobrzanski.pl/www/media/mediapool/newcar/X/bmw-x1/BMW-X1_ModelCard.png');
insert into car(brand, model, doors, yearprod, boot, drive, color, fuelcap, fueltype, range, gearbox, gears, daycost, imageUrl)
        values ('Daevoo', 'Matiz', 5, 2010, 20, '2X4', 'blue', 30, 'b', 300, 'M', 5, 29.99, 'https://www.autocentrum.pl/ac-file/gallery-photo/589489c6592c7d49138bc1ef/daewoo-matiz-02.jpg');

select * from customer;
select * from car;