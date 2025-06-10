ALTER TABLE address MODIFY COLUMN id bigint;
ALTER TABLE social_network MODIFY COLUMN id bigint;

ALTER TABLE address ADD COLUMN user_profile_id bigint REFERENCES user_profile(user_id);
ALTER TABLE social_network ADD COLUMN user_profile_id bigint REFERENCES user_profile(user_id);

ALTER TABLE user_profile DROP COLUMN address_id;
ALTER TABLE user_profile DROP COLUMN social_network_id;