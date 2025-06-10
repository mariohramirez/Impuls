CREATE TABLE `roles` (
    `id` int,
    `name` varchar(255) UNIQUE,
    `description` varchar(255),
    PRIMARY KEY (`id`)
);

CREATE TABLE `users` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `user_number` varchar(255),
    `email` varchar(255) UNIQUE NOT NULL,
    `password` varchar(255) NOT NULL,
    `is_verified` BOOLEAN  DEFAULT FALSE,
    `is_active` BOOLEAN  DEFAULT TRUE,
    `created_at` TIMESTAMP DEFAULT NOW(),
    `updated_at` TIMESTAMP DEFAULT NOW(),
    `role_id` int REFERENCES roles(id),
    PRIMARY KEY (`id`)
);

CREATE TABLE `gender` (
    `id` int,
    `name` varchar(100),
    PRIMARY KEY (`id`)
);

CREATE TABLE `document_type` (
    `id` int,
    `name` varchar(100),
    PRIMARY KEY (`id`)
);

CREATE TABLE `social_network` (
    `id` int,
    `name` varchar(100),
    `url` varchar(255),
    PRIMARY KEY (`id`)
);

CREATE TABLE `countries` (
    `id` int,
    `name` varchar(100),
    `code` varchar(20),
    PRIMARY KEY (`id`)
);

CREATE TABLE `states` (
    `id` int,
    `name` varchar(100),
    `country_id` int REFERENCES countries(id),
    PRIMARY KEY (`id`)
);

CREATE TABLE `cities` (
    `id` int,
    `name` varchar(100),
    `state_id` int REFERENCES states(id),
    PRIMARY KEY (`id`)
);

CREATE TABLE `address` (
    `id` int,
    `street` varchar(100),
    `city_id` int REFERENCES cities(id),
    `neighborhood` varchar(100),
    `zip_code` varchar(100),
    `is_primary` BOOLEAN,
    PRIMARY KEY (`id`)
);

CREATE TABLE `user_profile` (
    `user_id` bigint(20) REFERENCES users(id) ON DELETE CASCADE,
    `first_name` varchar(100),
    `last_name` varchar(100),
    `phone` varchar(20),
    `avatar_url` varchar(255),
    `gender_id` int REFERENCES gender(id),
    `document_type_id` int REFERENCES document_type(id),
    `social_network_id` int REFERENCES social_network(id),
    `address_id` int REFERENCES address(id),
    PRIMARY KEY (`user_id`)
);