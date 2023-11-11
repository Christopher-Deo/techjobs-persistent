--Part 1
SELECT column_name, data_type FROM information_schema.columns WHERE table_schema = 'techjobs' AND table_name = 'job';
--Part 2
SELECT * FROM techjobs.employer WHERE location = 'St. Louis';

--Part 3
DROP TABLE job;

--Part 4
SELECT DISTINCT s.name
FROM skill s
JOIN job_skills js ON s.id = js.skills_id
ORDER BY s.name ASC;