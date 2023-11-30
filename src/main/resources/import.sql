INSERT INTO todo(id, title, completed, ordering, url)
VALUES (1, 'Introdução ao Quarkus - Imperativo', false, 0, null);
INSERT INTO todo(id, title, completed, ordering, url)
VALUES (2, 'Aprender sobre Hibernate com Panache', false, 1, null);
INSERT INTO todo(id, title, completed, ordering, url)
VALUES (3, 'Visitar o site do Quarkus', false, 2, 'https://quarkus.io');
INSERT INTO todo(id, title, completed, ordering, url)
VALUES (4, 'Apresentar no EsquentaTDC', false, 3, 'https://www.youtube.com/watch?v=8Hx9HNe70zs');
ALTER SEQUENCE todo_seq RESTART WITH 5;