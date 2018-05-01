DROP TABLE IF EXISTS exerciser;
CREATE TABLE exerciser (
  id        BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  uuid        VARCHAR(36) NOT NULL UNIQUE,
  email       VARCHAR(50) NOT NULL UNIQUE,
  password    VARCHAR(20) NOT NULL,
  first_name  VARCHAR(30) NOT NULL,
  last_name   VARCHAR(30) NOT NULL,
  gender      VARCHAR(1)  NOT NULL
);
CREATE INDEX exerciser_uuid_idx
  ON exerciser (uuid);