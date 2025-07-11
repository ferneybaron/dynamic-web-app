ALTER TABLE user ADD `role` VARCHAR(50) NOT NULL;

UPDATE fbank.`user`
SET `role`='ADMIN'
WHERE id=1;