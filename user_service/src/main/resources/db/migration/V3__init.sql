ALTER TABLE document_type MODIFY COLUMN code VARCHAR(10);

ALTER TABLE countries ADD COLUMN phone_code VARCHAR(10);

ALTER TABLE states ADD COLUMN code VARCHAR(10);