DROP TABLE IF EXISTS workout;
DROP TABLE IF EXISTS exerciser_role;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS exerciser;

CREATE TABLE exerciser (
  id              BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  uuid            VARCHAR(36) NOT NULL UNIQUE,
  email           VARCHAR(50) NOT NULL UNIQUE,
  password        VARCHAR(80) NOT NULL,
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
  calories     INT NULL,
  distance     DOUBLE NULL,
  duration     INT NULL,
  workout_date TIMESTAMP NOT NULL,
  created_at   TIMESTAMP NOT NULL,
  updated_at   TIMESTAMP NOT NULL,
  FOREIGN KEY workout_exerciser_id_fk (exerciser_id) REFERENCES exerciser (id)
    ON DELETE CASCADE
);

CREATE TABLE role (
  id   BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  type VARCHAR(16)
);
CREATE TABLE exerciser_role (
  exerciser_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY (exerciser_id, role_id),
  FOREIGN KEY exerciser_id_fk (exerciser_id) REFERENCES exerciser (id)
    ON DELETE CASCADE,
  FOREIGN KEY role_id_fk (role_id) REFERENCES role (id)
    ON DELETE CASCADE
);

INSERT INTO role (name) VALUES ('ROLE_EXERCISER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');
SET @admin_role_id = LAST_INSERT_ID();

INSERT INTO exerciser (uuid, email, password, first_name, last_name, gender, created_at, updated_at, last_login_time)
VALUES (uuid(), 'admin@fitness.com', '$2a$10$nIvzMXNpB7KgBub/IZ3kyu2.fVf..w5E9Ud0PI0cg5.zRNGhSGnji', 'Fitness', 'Admin', 'M', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
SET @exerciser_id = LAST_INSERT_ID();

INSERT INTO exerciser_role VALUES (@exerciser_id, @admin_role_id);