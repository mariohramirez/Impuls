
CREATE TABLE `countries` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100),
    `code` varchar(20),
    `phone_code` varchar(10),
    PRIMARY KEY (`id`)
);

CREATE TABLE `states` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(100),
    `code` varchar(10),
    `country_id` int REFERENCES countries(id),
    PRIMARY KEY (`id`)
);

CREATE TABLE `cities` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(100),
    `state_id` bigint REFERENCES states(id),
    PRIMARY KEY (`id`)
);

CREATE TABLE `legal_form` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100),
    `code` varchar(10),
    `description` varchar(255),
    PRIMARY KEY (`id`)
);

CREATE TABLE `entrepreneurship` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `entrepreneurship_number` varchar(255),
    `owner_id` bigint,
    `name` varchar(255),
    `email` varchar(255) UNIQUE NOT NULL,
    `phone` varchar(20),
    `nit` varchar(20),
    `logo_url` varchar(255),
    `company_size` varchar(50),
    `company_name` varchar(255),
    `website_url` varchar(255),
    `short_description` varchar(255),
    `banner_url` varchar(255),
    `description` TEXT,
    `is_verified` BOOLEAN  DEFAULT FALSE,
    `is_active` BOOLEAN  DEFAULT TRUE,
    `created_at` TIMESTAMP DEFAULT NOW(),
    `updated_at` TIMESTAMP DEFAULT NOW(),
    `legal_form_id` int REFERENCES legal_form(id),
    `register_date` TIMESTAMP,
    `start_date` TIMESTAMP,
    `incorporation_date` TIMESTAMP,
    `formalized` BOOLEAN,
    PRIMARY KEY (`id`)
);

CREATE TABLE `address` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `street` varchar(100),
    `city_id` int REFERENCES cities(id),
    `neighborhood` varchar(100),
    `zip_code` varchar(100),
    `is_primary` BOOLEAN,
    `entrepreneurship_id` bigint,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`entrepreneurship_id`) REFERENCES `entrepreneurship`(`id`)
);

CREATE TABLE `social_network` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(100),
    `url` varchar(255),
    `entrepreneurship_id` bigint,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`entrepreneurship_id`) REFERENCES `entrepreneurship`(`id`)
);

CREATE TABLE `services` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(255),
    `description` varchar(255),
    PRIMARY KEY (`id`)
);

CREATE TABLE `entrepreneurship_services` (
    `entrepreneurship_id` bigint NOT NULL,
    `service_id` int NOT NULL,
    PRIMARY KEY (`entrepreneurship_id`, `service_id`),
    FOREIGN KEY (`entrepreneurship_id`) REFERENCES `entrepreneurship`(`id`),
    FOREIGN KEY (`service_id`) REFERENCES `services`(`id`)
);

CREATE TABLE `categories` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) UNIQUE NOT NULL,
    `description` TEXT,
    `icon` VARCHAR(50),
    PRIMARY KEY (`id`)
);

CREATE TABLE `event_categories` (
    `entrepreneurship_id` bigint NOT NULL,
    `category_id` int NOT NULL,
    PRIMARY KEY (`entrepreneurship_id`, `category_id`),
    FOREIGN KEY (`entrepreneurship_id`) REFERENCES `entrepreneurship`(`id`),
    FOREIGN KEY (`category_id`) REFERENCES `categories`(`id`)
);

