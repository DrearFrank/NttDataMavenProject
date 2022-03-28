INSERT INTO cliente (direccion,edad,genero,identificacion,nombre,telefono,clave,estado,fe_creacion) VALUES
('Otavalo sn y principal ','22','M','1234','Jose Laaema','0988425083','154752','Act',NULL)
,('Amazonas y  NNUU ','23','F','123456','Marianela Montalvo ','0988425083','154752','Act',NULL)
,('Otavalo sn y principal ','25','M','12345','Juan Osorio ','0988425083','154752','Act',NULL)
;
INSERT INTO cuenta (cliente_id,estado,fe_creacion,numero,saldo_inicial,tipo_cuenta) VALUES
(3,'Activa',NULL,'12312',1000,'Ahorro')
,(3,'Activa',NULL,'12312',1000,'Corriente')
,(4,'Activa',NULL,'12312',1000,'Ahorro')
,(2,'Activa',NULL,'12312',1000,'Corriente')
,(4,'Activa',NULL,'12312',1000,'Ahorro')
,(4,'Activa',NULL,'12312',1000,'Corriente')
;

INSERT INTO movimiento (cuenta_id,fe_creacion,saldo,tipo_movimiento,valor) VALUES
(1,'2022-10-12',1111,'Credito',111)
,(1,'2022-10-12',1333,'Credito',111)
,(11,'2022-10-12',22,'credito',222)
,(11,'2022-10-12',133,'Debito',111)
;
