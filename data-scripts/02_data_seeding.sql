USE club_management;

INSERT INTO users
    (username,firstname,password,role,bio)
VALUES
    ('dazzle','Dazzle the Coder','63E3E31E0E986A9CC716014A9790627B','Organizer',NULL),
    ('huskar','Huskar','63E3E31E0E986A9CC716014A9790627B','Organizer',NULL),
    ('kunkan','Kunkan','63E3E31E0E986A9CC716014A9790627B','Member',NULL);

INSERT INTO activities
    (title,description,date,created_on,created_by,updated_on,updated_by,status)
VALUES
    ('Draft event','This is draft event',1687355772633,1687355772633, 1, NULL, NULL,1),
    ('Published event','This is published event',1687355772633,1687355772633, 1, NULL, NULL,2),
    ('Canceled event','This is canceled event',1687355772633,1687355772633, 1, NULL, NULL,3);
