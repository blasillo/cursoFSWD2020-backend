insert into usuarios (id_usuario, clave, correo, nombre_usuario, rol) values (default, '$2a$10$eGoJElZIDxGwR6bu.eiFE.R9.izhdCckH34oQiQHh.aWQFOdfULEi','e1@demo.org' ,'Estudiante1', 'ESTUDIANTE');
insert into usuarios (id_usuario, clave, correo, nombre_usuario, rol) values (default, '$2a$10$FuFsBe4JdwTNC16Nox/yPesAm2REgAmmmgpQoVrJozQF8DZcYvuZe','e2@demo.org', 'Estudiante2', 'ESTUDIANTE');

insert into usuarios (id_usuario, clave, correo, nombre_usuario, rol) values (default, '$2a$10$q389tPG2IBuCDII3wV65D.mINlBUSwQoU5Xcmqu1oC5b7.4/CiGxK', 'p1@demo.org', 'Profesor1', 'PROFESOR');
insert into usuarios (id_usuario, clave, correo, nombre_usuario, rol) values (default, '$2a$10$WrHq20S1gDkwQ29utQBgb.rw6aOf5z1gLTN710JJXNJ8q.F.q6xIC', 'adm1@demo.org', 'Admin', 'ADMINISTRADOR');


insert into cursos (id_curso, nombre_curso, id_profesor) values (default, 'Programación Java', 3);
insert into cursos (id_curso, nombre_curso, id_profesor) values (default, 'Spring Boot: IoC y DI', 3);
insert into cursos (id_curso, nombre_curso, id_profesor) values (default, 'Angular para principiantes', 3);
insert into cursos (id_curso, nombre_curso, id_profesor) values (default, 'Docker para programadores Java', 3);

-- insert into cursos_estudiantes (id, id_curso, id_estudiante) values (default, ?, ?);
-- insert into cursos_estudiantes (id, id_curso, id_estudiante) values (default, ?, ?);