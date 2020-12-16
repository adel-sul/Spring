-- creating a new table
DROP TABLE IF EXISTS mentors;
CREATE TABLE mentors(
    id INT GENERATED ALWAYS AS IDENTITY,
    first_name varchar(40) not null,
    last_name varchar(40) not null,
    primary key (id)
);

-- creating audit table
DROP TABLE IF EXISTS audit;
CREATE TABLE audit(
    id INT GENERATED ALWAYS AS IDENTITY,
    mentor_id varchar(40) not null,
    last_name varchar(40) not null,
    changed_on timestamp(6) not null
);

INSERT INTO mentors(first_name, last_name) values ('Mike', 'Smith');
INSERT INTO mentors(first_name, last_name) values ('Tom', 'Hanks');
INSERT INTO mentors(first_name, last_name) values ('John', 'Snow');

-- creating a function
CREATE OR REPLACE FUNCTION log_last_name_changes()
    returns trigger
    language plpgsql
as
    $$
    begin
        IF NEW.last_name <> OLD.last_name THEN
            INSERT INTO audit(mentor_id, last_name, changed_on) VALUES
            (OLD.id, OLD.last_name, now());
        end if;

        RETURN new;
    end;
    $$;

-- creating BEFORE trigger
CREATE TRIGGER last_name_change
    BEFORE update
    ON mentors
    FOR EACH ROW
    EXECUTE PROCEDURE log_last_name_changes();

-- creating AFTER trigger
CREATE TRIGGER last_name_change2
    AFTER update
    ON mentors
    FOR EACH ROW
EXECUTE PROCEDURE log_last_name_changes();

UPDATE mentors
SET last_name = 'Smith'
WHERE last_name = 'Chan';

-- see all the triggers
SELECT * FROM pg_trigger;

-- deleting a trigger
DROP TRIGGER IF EXISTS last_name_change2 ON mentors;