//sql запрос создания таблиц
CREATE TABLE mark (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name TEXT NOT NULL,
    value TEXT NOT NULL
);

CREATE TABLE study_group (
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name TEXT NOT NULL
);

CREATE TABLE student (
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  surname TEXT NOT NULL,
  name TEXT NOT NULL,
  second_name TEXT NOT NULL,
  study_group_id INT NOT NULL,
  FOREIGN KEY (study_group_id) REFERENCES study_group(id)
);

CREATE TABLE subject (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name TEXT NOT NULL,
    short_name TEXT NOT NULL
);

CREATE TABLE exam_type (
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  type TEXT NOT NULL
);

CREATE TABLE study_plan (
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  subject_id INT NOT NULL,
  exam_type_id INT NOT NULL,
  FOREIGN KEY (subject_id) REFERENCES subject(id),
  FOREIGN KEY (exam_type_id) REFERENCES exam_type(id)
);

CREATE TABLE journal (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    student_id INT NOT NULL,
    study_plan_id INT NOT NULL,
    in_time BIT NOT NULL,
    count INT NOT NULL,
    mark_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (study_plan_id) REFERENCES study_plan(id),
    FOREIGN KEY (mark_id) REFERENCES mark(id)
)