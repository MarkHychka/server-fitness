DROP TABLE IF EXISTS workout;
DROP TABLE IF EXISTS exerciser;

CREATE TABLE exerciser (
  id              BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  uuid            VARCHAR(36) NOT NULL UNIQUE,
  email           VARCHAR(50) NOT NULL UNIQUE,
  password        VARCHAR(20) NOT NULL,
  first_name      VARCHAR(30) NOT NULL,
  last_name       VARCHAR(30) NOT NULL,
  gender          VARCHAR(1)  NOT NULL,
  created_at      TIMESTAMP,
  updated_at      TIMESTAMP,
  last_login_time TIMESTAMP
);
CREATE INDEX exerciser_uuid_idx
  ON exerciser (uuid);
CREATE TABLE workout (
  id           BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  exerciser_id BIGINT NOT NULL,
  type         VARCHAR(20) NOT NULL,
  calories     INT,
  distance     DOUBLE,
  duration     INT,
  created_at   TIMESTAMP,
  updated_at   TIMESTAMP,
  FOREIGN KEY workout_exerciser_id_fk (exerciser_id) REFERENCES exerciser (id)
    ON DELETE CASCADE
);