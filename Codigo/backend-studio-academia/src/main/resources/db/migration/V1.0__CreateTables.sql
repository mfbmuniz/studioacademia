CREATE SEQUENCE dueDate_seq;
CREATE TABLE dueDate (
                       dueDate_id INT DEFAULT NEXTVAL ('dueDate_seq') PRIMARY KEY,
                       due_Date TIMESTAMP(0) NOT NULL,

                       created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
                       deleted_at TIMESTAMP(0) NULL DEFAULT NULL,

);

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

CREATE SEQUENCE plans_seq;
CREATE TABLE plans (
                    plans_id INT DEFAULT NEXTVAL ('plans_seq') PRIMARY KEY,
                    name VARCHAR(50) NOT NULL,
                    price NUMERIC NOT NULL,
                    description TEXT NOT NULL,
                    planCode VARCHAR(3) NOT NULL,
                    contracted_days INT NOT NULL,


                    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
                    deleted_at TIMESTAMP(0) NULL DEFAULT NULL


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
                       plans_id INT CHECK (plans_id > 0),
                       birth_date TIMESTAMP(0) NULL DEFAULT NULL,
                       phone1 VARCHAR(100) NOT NULL,
                       phone2 VARCHAR(100) NOT NULL,
                       dueDate_id INT not null CHECK (dueDate_id > 0),


                       created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
                       deleted_at TIMESTAMP(0) NULL DEFAULT NULL,

                       CONSTRAINT FK_address_id FOREIGN KEY (address_id) REFERENCES address(address_id),
                       CONSTRAINT FK_plans_id FOREIGN KEY (plans_id) REFERENCES plans(plans_id),
                       CONSTRAINT FK_dueDate_id FOREIGN KEY (dueDate_id) REFERENCES dueDate(dueDate_id)

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



CREATE SEQUENCE week_days_seq;
CREATE TABLE week_days (
                    week_days_id INT DEFAULT NEXTVAL ('week_days_seq') PRIMARY KEY,
                    user_id INT CHECK (user_id > 0),
                    sunday BOOLEAN,
                    monday BOOLEAN,
                    tuesday BOOLEAN,
                    wednesday BOOLEAN,
                    thursday BOOLEAN,
                    friday BOOLEAN,
                    saturday BOOLEAN,


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


CREATE SEQUENCE monthly_payment_seq;
CREATE TABLE monthly_payment (
                    monthly_payment_id INT DEFAULT NEXTVAL ('monthly_payment_seq') PRIMARY KEY,
                    paymentDate TIMESTAMP(0) NULL DEFAULT NULL,
                    payment_CHECK BOOLEAN  not null,
                    user_id INT CHECK (user_id > 0),
                    payment_voucher VARCHAR(200),  /*imagem de comprovante*/
                    optional_message VARCHAR(200),
                    dueDate_id INT CHECK (dueDate_id > 0),


                    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
                    deleted_at TIMESTAMP(0) NULL DEFAULT NULL,

                    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
                    CONSTRAINT FK_dueDate_id FOREIGN KEY (dueDate_id) REFERENCES dueDate(dueDate_id)

);
CREATE SEQUENCE exercises_seq;
CREATE TABLE exercises (
                    exercises_id INT DEFAULT NEXTVAL ('exercises_seq ') PRIMARY KEY,
                    exercise_name VARCHAR(100)  not null,
                    exercise_link VARCHAR(100)  not null,
                    description varchar(100) not null,


                    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
                    deleted_at TIMESTAMP(0) NULL DEFAULT NULL

);

CREATE SEQUENCE user_file_seq;
CREATE TABLE user_file (
                    user_file_id INT DEFAULT NEXTVAL ('user_file_seq') PRIMARY KEY,
                    user_id INT CHECK (user_id > 0) not null,
                    file_name VARCHAR(100) NOT NULL,

                    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
                    deleted_at TIMESTAMP(0) NULL DEFAULT NULL,

                    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)

);

CREATE SEQUENCE user_exercises_seq;
CREATE TABLE user_exercises (
                    user_exercises_id INT DEFAULT NEXTVAL ('user_exercises_seq') PRIMARY KEY,
                    user_file_id INT CHECK (user_file_id > 0) not null,
                    exercises_id INT CHECK (exercises_id > 0) not null,
                    series INT not null ,
                    repetitions INT not null,


                    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
                    deleted_at TIMESTAMP(0) NULL DEFAULT NULL,

                    CONSTRAINT FK_user_file_id FOREIGN KEY (user_file_id) REFERENCES user_file(user_file_id),
                    CONSTRAINT FK_exercises_id FOREIGN KEY (exercises_id) REFERENCES exercises(exercises_id)

);



CREATE SEQUENCE physical_assessment_seq;
CREATE TABLE physical_assessment (
                    physical_assessment_id INT DEFAULT NEXTVAL ('physical_assessment_seq') PRIMARY KEY,
                    user_id INT CHECK (user_id > 0) ,
                    height DECIMAL,
                    general_description DECIMAL,
                    ideal_weight DECIMAL,
                    actual_weight DECIMAL,
                    relaxed_right_arm DECIMAL,
                    relaxed_left_arm DECIMAL,
                    contracted_right_arm DECIMAL,
                    contracted_left_arm DECIMAL,
                    right_forearm DECIMAL,
                    left_forearm DECIMAL,
                    right_fist DECIMAL,
                    left_fist DECIMAL,
                    neck DECIMAL,
                    chest DECIMAL,
                    abdomen DECIMAL,
                    shoulder DECIMAL,
                    waist DECIMAL,
                    hip DECIMAL,
                    right_calf DECIMAL,
                    left_calf DECIMAL,
                    right_thigh DECIMAL,
                    left_thigh DECIMAL,
                    right_proximal_thigh DECIMAL,
                    left_proximal_thigh DECIMAL,
                    bone_fist_diameter DECIMAL,
                    femur DECIMAL,
                    skin_folds_biceps DECIMAL,
                    skin_folds_triceps DECIMAL,
                    skin_folds_abdominal DECIMAL,
                    skin_folds_suprailiac DECIMAL,
                    skin_folds_medium_armpit DECIMAL,
                    skin_folds_chest DECIMAL,
                    skin_folds_subscapularis DECIMAL,
                    skin_folds_thigh DECIMAL,
                    skin_medial_calf DECIMAL,
                    IMC DECIMAL,
                    fat_mass DECIMAL,
                    corporal_water DECIMAL,
                    residual_weight DECIMAL,
                    visceral_fat DECIMAL,
                    lean_mass DECIMAL,
                    muscular_weight DECIMAL,
                    metabolic_age DECIMAL,
                    bone_weight DECIMAL,
                    physical_assessment_date DECIMAL,

                    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
                    deleted_at TIMESTAMP(0) NULL DEFAULT NULL,

                    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)

);

CREATE SEQUENCE user_assessments_seq;
CREATE TABLE user_assessments (
                    user_assessments_id INT DEFAULT NEXTVAL ('user_assessments_seq') PRIMARY KEY,
                    user_id INT CHECK (user_id > 0) not null,
                    physical_assessment_id INT CHECK (physical_assessment_id > 0) not null,
                    general_description varchar(250),

                    created_at TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,
                    deleted_at TIMESTAMP(0) NULL DEFAULT NULL,

                    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
                    CONSTRAINT FK_physical_assessment_id FOREIGN KEY (physical_assessment_id) REFERENCES physical_assessment(physical_assessment_id)

);

