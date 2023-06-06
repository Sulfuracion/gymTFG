-- Creación de tablas
CREATE TABLE Usuarios (
  ID INT PRIMARY KEY,
  NombreUsuario VARCHAR(50),
  Contraseña VARCHAR(50),
  Rol VARCHAR(20)
);

CREATE TABLE Rutinas (
  ID INT PRIMARY KEY,
  ID_usuario INT,
  Fecha DATE,
  Descripcion VARCHAR(200),
  FOREIGN KEY (ID_usuario) REFERENCES Usuarios(ID)
);

CREATE TABLE Ejercicios (
  ID INT PRIMARY KEY,
  Nombre VARCHAR(100),
  GrupoMuscular VARCHAR(50)
);

CREATE TABLE PersonalRecords (
  ID INT PRIMARY KEY,
  ID_usuario INT,
  TipoPR VARCHAR(50),
  PesoMaximo FLOAT,
  FechaPR DATE,
  FOREIGN KEY (ID_usuario) REFERENCES Usuarios(ID)
);

CREATE TABLE RegistroSeries (
  ID INT PRIMARY KEY,
  ID_usuario INT,
  ID_ejercicio INT,
  PesoUtilizado FLOAT,
  NumRepeticiones INT,
  RIR INT,
  FOREIGN KEY (ID_usuario) REFERENCES Usuarios(ID),
  FOREIGN KEY (ID_ejercicio) REFERENCES Ejercicios(ID)
);
--Roles
-- Creación de la tabla Dietas (opcional para los entrenadores nutricionistas)
CREATE TABLE Dietas (
  ID INT PRIMARY KEY,
  ID_usuario INT,
  DescripcionDieta VARCHAR(200),
  FOREIGN KEY (ID_usuario) REFERENCES Usuarios (ID)
);

-- Creación de la tabla Series
CREATE TABLE Series (
  ID INT PRIMARY KEY,
  ID_usuario INT,
  ID_rutina INT,
  PesoUtilizado FLOAT,
  NumRepeticiones INT,
  FOREIGN KEY (ID_usuario) REFERENCES Usuarios (ID),
  FOREIGN KEY (ID_rutina) REFERENCES Rutinas (ID)
);

-- Creación de la tabla PersonalRecords
CREATE TABLE PersonalRecords (
  ID INT PRIMARY KEY,
  ID_usuario INT,
  TipoPR VARCHAR(50),
  PesoMaximo FLOAT,
  FOREIGN KEY (ID_usuario) REFERENCES Usuarios (ID)
);

-- Inserciones de ejemplo
INSERT INTO Usuarios (ID, NombreUsuario, Contraseña, Rol) VALUES
  (1, 'usuario1', 'password1', 'Usuario'),
  (2, 'entrenador1', 'password2', 'Entrenador'),
  (3, 'staff1', 'password3', 'Staff');

INSERT INTO Rutinas (ID, ID_usuario, Fecha, Descripcion) VALUES
  (1, 1, '2023-05-01', 'Rutina de fuerza'),
  (2, 1, '2023-05-15', 'Rutina de hipertrofia');

INSERT INTO Ejercicios (ID, Nombre, GrupoMuscular) VALUES
  (1, 'Sentadilla', 'Piernas'),
  (2, 'Press de banca', 'Pecho'),
  (3, 'Peso muerto', 'Espalda');

INSERT INTO PersonalRecords (ID, ID_usuario, TipoPR, PesoMaximo, FechaPR) VALUES
  (1, 1, 'Sentadilla', 100, '2023-05-01'),
  (2, 1, 'Press de banca', 80, '2023-05-01');

INSERT INTO RegistroSeries (ID, ID_usuario, ID_ejercicio, PesoUtilizado, NumRepeticiones, RIR) VALUES
  (1, 1, 1, 80, 8, 2),
  (2, 1, 2, 60, 10, 1);

-- Actualización de ejercicios
UPDATE Ejercicios SET GrupoMuscular = 'Piernas y Glúteos' WHERE ID = 1;

-- Eliminación de una rutina
DELETE FROM Rutinas WHERE ID = 2;

-- Consultas
-- Consulta con INNER JOIN
SELECT Rutinas.Fecha, Usuarios.NombreUsuario, Rutinas.Descripcion
FROM Rutinas
INNER JOIN Usuarios ON Rutinas.ID_usuario = Usuarios.ID;

-- Subconsulta
SELECT *
FROM PersonalRecords
WHERE ID_usuario IN (SELECT ID FROM Usuarios WHERE Rol = 'Usuario');

-- Consulta con JOIN múltiple y subconsulta
SELECT Usuarios.NombreUsuario, Ejercicios.Nombre, RegistroSeries.PesoUtilizado
FROM RegistroSeries
JOIN Usuarios ON RegistroSeries.ID_usuario = Usuarios.ID
JOIN Ejercicios ON RegistroSeries.ID_ejercicio = Ejercicios.ID
WHERE Usuarios.Rol = 'Usuario'
  AND Ejercicios.GrupoMuscular = 'Piernas'
  AND RegistroSeries.PesoUtilizado > (
    SELECT AVG(PesoUtilizado) FROM RegistroSeries WHERE ID_ejercicio = 1
  );

-- Consulta con subconsulta correlacionada
SELECT Usuarios.NombreUsuario, PersonalRecords.TipoPR, PersonalRecords.PesoMaximo
FROM PersonalRecords
JOIN Usuarios ON PersonalRecords.ID_usuario = Usuarios.ID
WHERE PersonalRecords.PesoMaximo > (
  SELECT MAX(PesoMaximo)
  FROM PersonalRecords AS PR
  WHERE PR.ID_usuario = Usuarios.ID
);

-- Consulta con agregación
SELECT Usuarios.NombreUsuario, COUNT(*) AS TotalRutinas
FROM Usuarios
LEFT JOIN Rutinas ON Usuarios.ID = Rutinas.ID_usuario
GROUP BY Usuarios.NombreUsuario;
