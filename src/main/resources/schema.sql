-- Bank Management System Database Schema Initialization
-- Compatible with MySQL 8.x and H2 Database

CREATE TABLE IF NOT EXISTS signup (
    formno VARCHAR(30),
    name VARCHAR(50),
    fname VARCHAR(50),
    dob VARCHAR(30),
    gender VARCHAR(20),
    email VARCHAR(50),
    marital VARCHAR(20),
    address VARCHAR(100),
    city VARCHAR(50),
    pincode VARCHAR(20),
    state VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS signuptwo (
    formno VARCHAR(30),
    rel VARCHAR(30),
    cate VARCHAR(30),
    inc VARCHAR(30),
    edu VARCHAR(50),
    occ VARCHAR(50),
    pan VARCHAR(30),
    addhar VARCHAR(30),
    scitizen VARCHAR(10),
    eAccount VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS signupthree (
    formno VARCHAR(30),
    atype VARCHAR(50),
    cardno VARCHAR(30),
    pin VARCHAR(10),
    fac VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS login (
    formno VARCHAR(30),
    card_number VARCHAR(30),
    pin VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS bank (
    pin VARCHAR(10),
    date VARCHAR(50),
    type VARCHAR(20),
    amount VARCHAR(20)
);
