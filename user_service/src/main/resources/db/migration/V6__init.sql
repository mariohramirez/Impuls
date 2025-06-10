DROP TABLE IF EXISTS address;

DROP TABLE IF EXISTS social_network;

CREATE TABLE `address` (
    `id` bigint,
    `street` varchar(100),
    `city_id` int REFERENCES cities(id),
    `neighborhood` varchar(100),
    `zip_code` varchar(100),
    `is_primary` BOOLEAN,
    `user_profile_id` bigint REFERENCES user_profile(user_id),
    PRIMARY KEY (`id`)
);

CREATE TABLE `social_network` (
    `id` bigint,
    `name` varchar(100),
    `url` varchar(255),
    `user_profile_id` bigint REFERENCES user_profile(user_id),
    PRIMARY KEY (`id`)
);