
DROP TABLE PingTable;
CREATE TABLE PingTable (foo CHAR(1));


CREATE TABLE USUARIO ( 
login VARCHAR(16) NOT NULL, 
pass VARCHAR(40) CHARACTER SET utf8 NOT NULL, 
nombre VARCHAR(20) NOT NULL, 
ape1 VARCHAR(20) NOT NULL, 
ape2 VARCHAR(20) NOT NULL, 
e_mail VARCHAR(50) NOT NULL, 
lenguaje VARCHAR(2) NOT NULL, 
pais VARCHAR(2) NOT NULL, 
version BIGINT DEFAULT 0, 
PRIMARY KEY (login));

/*TARJETA*/

CREATE TABLE TARJETA ( 
cod_c_c DOUBLE PRECISION NOT NULL, 
Tlogin VARCHAR(10) NOT NULL, 
PRIMARY KEY (cod_c_c), 
FOREIGN KEY (Tlogin) REFERENCES USUARIO (login) ON UPDATE CASCADE);

/*PELICULA*/

CREATE TABLE PELICULA (
idPelicula BIGINT NOT NULL AUTO_INCREMENT,
titulo VARCHAR(25) NOT NULL,
director VARCHAR(25) NOT NULL,
clasificacion VARCHAR(25) NOT NULL,
descripcion VARCHAR(50),
PRIMARY KEY (idPelicula));

/*DVD*/

CREATE TABLE DVD (
idDvd BIGINT NOT NULL AUTO_INCREMENT,
titulo VARCHAR(25) NOT NULL,
director VARCHAR(25) NOT NULL,
clasificacion VARCHAR(25) NOT NULL,
descripcion VARCHAR(50),
precio DECIMAL(4,2)NOT NULL,
disponibilidad TINYINT,
PRIMARY KEY (idDvd));

/*MERCHANDISING*/
CREATE TABLE MERCHANDISING (
idMerchandising BIGINT NOT NULL AUTO_INCREMENT,
descripcion VARCHAR(25) NOT NULL,
referencia DOUBLE PRECISION NOT NULL,
tallas VARCHAR(10),
precio DECIMAL(4,2) NOT NULL,
disponibilidad TINYINT,
PRIMARY KEY (idMerchandising));


/*PRODUCTO*/

CREATE TABLE PRODUCTO (
idProducto BIGINT NOT NULL AUTO_INCREMENT,
tipo integer NOT NULL,
item BIGINT NOT NULL,
num_linea BIGINT ,
PRIMARY KEY (idProducto));


/*DIRPOSTAL*/

CREATE TABLE DIRPOSTAL (
cp DOUBLE PRECISION NOT NULL,
ciudad VARCHAR(20) NOT NULL,
direccion VARCHAR(50) NOT NULL,
numero DOUBLE PRECISION NOT NULL,
Ulogin VARCHAR(16) NOT NULL, 
PRIMARY KEY (cp,ciudad,direccion,numero),
FOREIGN KEY (Ulogin) REFERENCES USUARIO (login) ON UPDATE CASCADE);

/*CINE*/

CREATE TABLE CINE (
idCine BIGINT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(20) NOT NULL,
num_salas DOUBLE PRECISION NOT NULL,
C_cp DOUBLE PRECISION NOT NULL,
PRIMARY KEY (idCine),
FOREIGN KEY (C_cp) REFERENCES DIRPOSTAL (cp) ON UPDATE CASCADE);

/*SALA*/

CREATE TABLE SALA (
id_sala DOUBLE PRECISION NOT NULL,
filas DOUBLE PRECISION NOT NULL,
asientos DOUBLE PRECISION NOT NULL,
idCine BIGINT NOT NULL,
PRIMARY KEY (id_sala,idCine),
FOREIGN KEY (idCine) REFERENCES CINE (idCine) ON UPDATE CASCADE);


/*SESION*/

CREATE TABLE SESION (
fecha DATE NOT NULL,
hora TIME NOT NULL,
precio DECIMAL(4,2) NOT NULL,
numerada TINYINT,
idPelicula VARCHAR(25),
Sid_sala DOUBLE PRECISION NOT NULL,
idCine BIGINT NOT NULL,
idSesion BIGINT NOT NULL AUTO_INCREMENT,
PRIMARY KEY (idSesion),
FOREIGN KEY (idPelicula) REFERENCES PELICULA (idPelicula) ON UPDATE CASCADE);


/*PEDIDO*/

CREATE TABLE PEDIDO (
id_pedido DOUBLE PRECISION NOT NULL AUTO_INCREMENT,
fecha DATE NOT NULL,
Ulogin VARCHAR(30) NOT NULL, 
PRIMARY KEY (id_pedido),
FOREIGN KEY (Ulogin) REFERENCES USUARIO (login) ON UPDATE CASCADE);

/*BUTACA*/

CREATE TABLE BUTACA (
num_fila DOUBLE PRECISION NOT NULL,
num_asiento DOUBLE PRECISION NOT NULL,
ocupado TINYINT,
Bid_sala DOUBLE PRECISION,
Bid_producto DOUBLE PRECISION,
Bnum_linea DOUBLE PRECISION,
idCine BIGINT NOT NULL,
PRIMARY KEY (num_fila,num_asiento,Bid_sala,idCine),
FOREIGN KEY (Bid_sala,idCine) REFERENCES SALA (id_sala,idCine) ON UPDATE CASCADE);


/*LPEDIDO*/

CREATE TABLE LPEDIDO (
id_producto BIGINT NOT NULL,
num_linea BIGINT NOT NULL AUTO_INCREMENT,
num_unidades BIGINT NOT NULL,
id_pedido BIGINT NOT NULL,
tipo 	INT NOT NULL,
PRIMARY KEY (id_pedido,num_linea),
FOREIGN KEY (id_pedido) REFERENCES PEDIDO (id_pedido) ON UPDATE CASCADE);



/*TICKET

Esta tabla es comodin, repetimos algunos datos que podriamos obtener de otras tablas pero
obtenemos ciertas ventajas, nos sirve como una especificacion de producto y tambien para 
controlar el estado de la sala para una sesion. 

Las tuplas de esta tabla son creadas en el momento de crear una sesion no a la hora de realizar
una venta. Esto es debido a que funcionan como muestreo del estado de la sala para una sesion

*/

CREATE TABLE TICKET(
idTicket BIGINT NOT NULL AUTO_INCREMENT,
sesion BIGINT NOT NULL,
fila INT NOT NULL,
asiento INT NOT NULL,
estado INT NOT NULL,
precio DECIMAL(4,2),
PRIMARY KEY (idTicket),
FOREIGN KEY (sesion) REFERENCES SESION(idSESION) ON DELETE CASCADE
);

