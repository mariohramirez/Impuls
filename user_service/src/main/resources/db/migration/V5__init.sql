ALTER TABLE address
ADD CONSTRAINT fk_address_user_profile
FOREIGN KEY (user_profile_id) REFERENCES user_profile(user_id);

ALTER TABLE social_network
ADD CONSTRAINT fk_social_network_user_profile
FOREIGN KEY (user_profile_id) REFERENCES user_profile(user_id);