Create Database nttdata;

CREATE TABLE public.cliente (
	id_cliente bigserial NOT NULL,
	direccion varchar(255) NULL,
	edad varchar(255) NULL,
	genero varchar(255) NULL,
	identificacion varchar(255) NULL,
	nombre varchar(255) NULL,
	telefono varchar(255) NULL,
	clave varchar(255) NULL,
	estado varchar(255) NULL,
	fe_creacion date NULL,
	CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente)
);
CREATE TABLE public.cuenta (
	id_cuenta bigserial NOT NULL,
	cliente_id int8 NULL,
	estado varchar(255) NULL,
	fe_creacion date NULL,
	numero varchar(255) NULL,
	saldo_inicial float4 NULL,
	tipo_cuenta varchar(255) NULL,
	CONSTRAINT cuenta_pkey PRIMARY KEY (id_cuenta),
	CONSTRAINT fk4p224uogyy5hmxvn8fwa2jlug FOREIGN KEY (cliente_id) REFERENCES cliente(id_cliente)
);

CREATE TABLE public.movimiento (
	id_movimiento bigserial NOT NULL,
	cuenta_id int8 NULL,
	fe_creacion date NULL,
	saldo float4 NOT NULL,
	tipo_movimiento varchar(255) NULL,
	valor float4 NOT NULL,
	CONSTRAINT movimiento_pkey PRIMARY KEY (id_movimiento),
	CONSTRAINT fk4ea11fe7p3xa1kwwmdgi9f2fi FOREIGN KEY (cuenta_id) REFERENCES cuenta(id_cuenta)
);

INSERT INTO public.cliente (direccion,edad,genero,identificacion,nombre,telefono,clave,estado,fe_creacion) VALUES
('Otavalo sn y principal ','22','M','1234','Jose Laaema','0988425083','154752','Act',NULL)
,('Amazonas y  NNUU ','23','F','123456','Marianela Montalvo ','0988425083','154752','Act',NULL)
,('Otavalo sn y principal ','25','M','12345','Juan Osorio ','0988425083','154752','Act',NULL)
;
INSERT INTO public.cuenta (cliente_id,estado,fe_creacion,numero,saldo_inicial,tipo_cuenta) VALUES
(3,'Activa',NULL,'12312',1000,'Ahorro')
,(3,'Activa',NULL,'12312',1000,'Corriente')
,(4,'Activa',NULL,'12312',1000,'Ahorro')
,(2,'Activa',NULL,'12312',1000,'Corriente')
,(4,'Activa',NULL,'12312',1000,'Ahorro')
,(4,'Activa',NULL,'12312',1000,'Corriente')
;

INSERT INTO public.movimiento (cuenta_id,fe_creacion,saldo,tipo_movimiento,valor) VALUES
(1,'2022-10-12',1111,'Credito',111)
,(1,'2022-10-12',1333,'Credito',111)
,(11,'2022-10-12',22,'credito',222)
,(11,'2022-10-12',133,'Debito',111)
;