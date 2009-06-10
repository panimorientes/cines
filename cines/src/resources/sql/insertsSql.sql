
INSERT INTO USUARIO (login,pass,nombre,ape1,ape2,e_mail,lenguaje,pais,version) VALUES ('tomate','tomate','Tomate','Smith','Frito','tomate@tomate.com','es','Es',0);
INSERT INTO USUARIO (login,pass,nombre,ape1,ape2,e_mail,lenguaje,pais,version) VALUES ('fresa','fresa','Fresa Maria','Perez','Lopez','fresa@fresa.com','en','En',0);
INSERT INTO USUARIO (login,pass,nombre,ape1,ape2,e_mail,lenguaje,pais,version) VALUES ('rosa','rosa','Rosa','Miceli','Greco','rosa@rosa.com','it','It',0);

INSERT INTO TARJETA (cod_c_c,Tlogin) VALUES ('654789543','rosa');
INSERT INTO TARJETA (cod_c_c,Tlogin) VALUES ('809836215','tomate');

INSERT INTO PELICULA (titulo,director,clasificacion,descripcion) VALUES ('Terminator Salvation','McG','Action','After Skynet has destroyed much of humanity');
INSERT INTO PELICULA (titulo,director,clasificacion,descripcion) VALUES ('Obsessed','Steve Shill','Drama','A successful asset manager, who has just received');
INSERT INTO PELICULA (titulo,director,clasificacion,descripcion) VALUES ('Angels and Demons','Ron Howard','Drama','Harvard symbologist Robert Langdon works to');

-- creo que deberiamos incrementar el espacio dedicado a descripcion --
INSERT INTO DVD (titulo,director,clasificacion,descripcion,precio,disponibilidad) VALUES ('Night at the museum','Shawn Levy','Comedy','When the lights go off the battle is on',20,1);
INSERT INTO DVD (titulo,director,clasificacion,descripcion,precio,disponibilidad) VALUES ('Drag me to hell','Sam Raimi','Horror','Christine Brown has a good job, a great',25,1);

INSERT INTO MERCHANDISING (descripcion,referencia,tallas,precio,disponibilidad) VALUES ('Star Wars T-Shirt','0000001','M,XL',15,1);

-- falta tabla Producto --

INSERT INTO DIRPOSTAL (cp,ciudad,direccion,numero,Ulogin) VALUES (15006,'Palermo','via collegio del giusino',41,'rosa');
INSERT INTO DIRPOSTAL (cp,ciudad,direccion,numero,Ulogin) VALUES (15006,'Madrid','calle fuencarral',3,'tomate');
INSERT INTO DIRPOSTAL (cp,ciudad,direccion,numero,Ulogin) VALUES (15006,'London','first avenue',234,'fresa');
INSERT INTO DIRPOSTAL (cp,ciudad,direccion,numero,Ulogin) VALUES (15007,'A Corunha','calle puerto',125,'admin');

INSERT INTO CINE (nombre,num_salas,c_cp) VALUES ('Port Cinemas',30,15007);
INSERT INTO CINE (nombre,num_salas,c_cp) VALUES ('The Rosales',17,15007);


INSERT INTO SALA (id_sala,filas,asientos,idCine) VALUES (0000001,20,400,1);
INSERT INTO SALA (id_sala,filas,asientos,idCine) VALUES (0000002,15,300,1);
INSERT INTO SALA (id_sala,filas,asientos,idCine) VALUES (0000003,15,300,1);
INSERT INTO SALA (id_sala,filas,asientos,idCine) VALUES (0000004,20,400,2);
INSERT INTO SALA (id_sala,filas,asientos,idCine) VALUES (0000005,15,300,2);

INSERT INTO SESION (fecha,hora,precio,numerada,idPelicula,Sid_sala,idCine) VALUES ('2009-08-15','15:30:00',6.50,1,1,0000001,1);
INSERT INTO SESION (fecha,hora,precio,numerada,idPelicula,Sid_sala,idCine) VALUES ('2009-08-15','19:00:00',6.50,0,1,0000001,1);
INSERT INTO SESION (fecha,hora,precio,numerada,idPelicula,Sid_sala,idCine) VALUES ('2009-08-20','15:30:00',6.50,1,2,0000002,1);
INSERT INTO SESION (fecha,hora,precio,numerada,idPelicula,Sid_sala,idCine) VALUES ('2009-08-20','19:00:00',6.50,0,2,0000004,2);

-- faltan tablas Pedido,Lpedido,Ticket --


INSERT INTO BUTACA (num_fila,num_asiento,ocupado,Bid_sala,Bid_producto,Bnum_linea,idCine) VALUES (20,13,0,0000001,1,1,1);
INSERT INTO BUTACA (num_fila,num_asiento,ocupado,Bid_sala,Bid_producto,Bnum_linea,idCine) VALUES (20,12,0,0000002,1,1,1);
INSERT INTO BUTACA (num_fila,num_asiento,ocupado,Bid_sala,Bid_producto,Bnum_linea,idCine) VALUES (20,17,0,0000004,1,1,2);
INSERT INTO BUTACA (num_fila,num_asiento,ocupado,Bid_sala,Bid_producto,Bnum_linea,idCine) VALUES (20,13,1,0000002,1,1,1);








