CREATE SEQUENCE city_seq;
CREATE TABLE cities (
                        city_id INT DEFAULT NEXTVAL ('city_seq') PRIMARY KEY,
                        city VARCHAR(255) NOT NULL
);

CREATE SEQUENCE states_seq;
CREATE TABLE states (
                        state_id INT DEFAULT NEXTVAL ('states_seq') PRIMARY KEY,
                        state VARCHAR(255) NOT NULL,
                        uf VARCHAR(2) NOT NULL
);

CREATE SEQUENCE address_seq;
CREATE TABLE address(
                        address_id INT DEFAULT NEXTVAL ('address_seq') PRIMARY KEY,
                        street VARCHAR(255) NOT NULL,
                        number INT NOT NULL,
                        district VARCHAR(100) NOT NULL,
                        city_id INT NOT NULL,
                        state_id INT NOT NULL,

                        CONSTRAINT FK_city_id FOREIGN KEY (city_id) REFERENCES cities(city_id),
                        CONSTRAINT FK_state_id FOREIGN KEY (state_id) REFERENCES states(state_id)
);


CREATE SEQUENCE users_seq;
CREATE TABLE users (
                       user_id INT DEFAULT NEXTVAL ('users_seq') PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL unique,
                       password VARCHAR(255) NOT NULL,
                       sex VARCHAR(1) NOT NULL,
                       legal_document VARCHAR(11) NOT NULL,
                       address_id INT NOT NULL,
                       plans_id INT CHECK (user_id > 0) ,

                       created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
                       deleted_at TIMESTAMP(0) NULL DEFAULT NULL,

                       CONSTRAINT FK_address_id FOREIGN KEY (address_id) REFERENCES address(address_id)
                       CONSTRAINT FK_plans_id FOREIGN KEY (plans_id) REFERENCES plans(plans_id)
);

CREATE SEQUENCE country_code_seq;
CREATE TABLE country_code(
                             country_code_id INT DEFAULT NEXTVAL('country_code_seq') PRIMARY KEY,
                             country_code INT NOT NULL UNIQUE
);

CREATE SEQUENCE phones_seq;
CREATE TABLE phones(
                       phones_id INT DEFAULT NEXTVAL ('phones_seq') PRIMARY KEY,
                       phone VARCHAR(12) NOT NULL,
                       country_code_id INT NOT NULL,
                       user_id int not null,

                       CONSTRAINT FK_users_id FOREIGN KEY (user_id) REFERENCES users(user_id),
                       CONSTRAINT FK_country_code FOREIGN KEY (country_code_id) REFERENCES country_code(country_code_id)
);


CREATE SEQUENCE roles_seq;
CREATE TABLE roles (
                       roles_id INT DEFAULT NEXTVAL ('roles_seq') PRIMARY KEY,
                       name varchar(45) NOT NULL UNIQUE,

                       created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
                       deleted_at TIMESTAMP(0) NULL DEFAULT NULL
);


CREATE SEQUENCE user_roles_seq;
CREATE TABLE user_roles (
                    user_roles_id INT DEFAULT NEXTVAL ('user_roles_seq') PRIMARY KEY,
                    user_id INT CHECK (user_id > 0) NOT NULL,
                    role_id INT CHECK (role_id > 0) NOT NULL,

                    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
                    deleted_at TIMESTAMP(0) NULL DEFAULT NULL,

                    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
                    CONSTRAINT FK_role_id FOREIGN KEY (role_id) REFERENCES roles(roles_id)
);

CREATE SEQUENCE admin_message_service_seq;
CREATE TABLE admin_message_service (
                    admin_message_id INT DEFAULT NEXTVAL ('admin_message_service_seq') PRIMARY KEY,
                    user_id INT CHECK (user_id > 0),
                    message_content TEXT NOT NULL,

                    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
                    deleted_at TIMESTAMP(0) NULL DEFAULT NULL,

                    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)

);

CREATE SEQUENCE plans_seq;
CREATE TABLE plans (
                    plans_id INT DEFAULT NEXTVAL ('plans_seq') PRIMARY KEY,
                    name VARCHAR(50) NOT NULL,
                    price NUMERIC NOT NULL,
                    description TEXT NOT NULL,
                    planCode VARCHAR(3) NOT NULL,
                    contracted_days INT NOT NULL,
                    week_days_id INT CHECK (user_id > 0),

                    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
                    deleted_at TIMESTAMP(0) NULL DEFAULT NULL,

                    CONSTRAINT FK_week_days_id FOREIGN KEY (week_days_id) REFERENCES week_days(week_days_id)


);

CREATE SEQUENCE week_days_seq;
CREATE TABLE week_days (
                    week_days_id INT DEFAULT NEXTVAL ('week_days_seq') PRIMARY KEY,
                    user_id INT CHECK (user_id > 0),
                    daysOfWeek varchar (30),


                    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
                    deleted_at TIMESTAMP(0) NULL DEFAULT NULL,

                    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)

);

CREATE SEQUENCE calendar_seq;
CREATE TABLE calendar (
                    calendar_id INT DEFAULT NEXTVAL ('calendar_seq') PRIMARY KEY,
                    dateEvent TIMESTAMP(0) NULL DEFAULT NULL,
                    dateDescription TEXT NOT NULL,


                    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
                    deleted_at TIMESTAMP(0) NULL DEFAULT NULL

);

CREATE SEQUENCE calendar_seq;
CREATE TABLE calendar (
                    calendar_id INT DEFAULT NEXTVAL ('calendar_seq') PRIMARY KEY,
                    dateEvent TIMESTAMP(0) NULL DEFAULT NULL,
                    dateDescription TEXT NOT NULL,


                    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
                    deleted_at TIMESTAMP(0) NULL DEFAULT NULL

);

CREATE SEQUENCE monthly_payment_seq;
CREATE TABLE monthly_payment (
                    monthly_payment INT DEFAULT NEXTVAL ('monthly_payment') PRIMARY KEY,
                    due_Date TIMESTAMP(0) NULL DEFAULT NULL,
                    payment_CHECK BOOLEAN  not null,

                    dateDescription TEXT NOT NULL,


                    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
                    deleted_at TIMESTAMP(0) NULL DEFAULT NULL,

                    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)

);