DROP TABLE IF EXISTS workout;
DROP TABLE IF EXISTS exerciser_role;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS favorite_club;
DROP TABLE IF EXISTS club;
DROP TABLE IF EXISTS exerciser;
DROP TABLE IF EXISTS goal;

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
  id           BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  exerciser_id BIGINT      NOT NULL,
  type         VARCHAR(20) NOT NULL,
  calories     INT         NULL,
  distance     DOUBLE      NULL,
  duration     INT         NULL,
  workout_date TIMESTAMP   NOT NULL,
  created_at   TIMESTAMP   NOT NULL,
  updated_at   TIMESTAMP   NOT NULL,
  FOREIGN KEY workout_exerciser_id_fk (exerciser_id) REFERENCES exerciser (id)
    ON DELETE CASCADE
);

CREATE TABLE role (
  id   BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(16)
);
CREATE TABLE exerciser_role (
  exerciser_id BIGINT NOT NULL,
  role_id      BIGINT NOT NULL,
  PRIMARY KEY (exerciser_id, role_id),
  FOREIGN KEY exerciser_id_fk (exerciser_id) REFERENCES exerciser (id)
    ON DELETE CASCADE,
  FOREIGN KEY role_id_fk (role_id) REFERENCES role (id)
    ON DELETE CASCADE
);
INSERT INTO role (name) VALUES ('ROLE_EXERCISER');

CREATE TABLE club (
  id         BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  uuid       VARCHAR(36) NOT NULL UNIQUE,
  name       VARCHAR(36),
  latitude   DOUBLE      NOT NULL,
  longitude  DOUBLE      NOT NULL,
  created_at TIMESTAMP   NOT NULL DEFAULT NOW()
);

CREATE TABLE favorite_club (
  exerciser_id BIGINT NOT NULL,
  club_id      BIGINT NOT NULL,
  PRIMARY KEY (exerciser_id, club_id),
  FOREIGN KEY exerciser_id_fk (exerciser_id) REFERENCES exerciser (id)
    ON DELETE CASCADE,
  FOREIGN KEY club_id_fk (club_id) REFERENCES club (id)
    ON DELETE CASCADE
);

CREATE TABLE goal (
  id           BIGINT PRIMARY KEY AUTO_INCREMENT,
  uuid         varchar(36)                                                         not null,
  exerciser_id bigint                                                              not null,
  start_date   timestamp default '0000-00-00 00:00:00'                             not null,
  end_date     timestamp default '0000-00-00 00:00:00'                             not null,
  name         varchar(50)                                                         not null,
  target       float                                                               not null,
  goal_type    varchar(20)                                                         not null,
  progress     float                                                               not null,
  completed    boolean default false                                               not null,
  created_at   timestamp default '0000-00-00 00:00:00'                             not null,
  updated_at   timestamp default '0000-00-00 00:00:00'                             not null,
  foreign key (exerciser_id) references exerciser (id)
    on delete cascade
);