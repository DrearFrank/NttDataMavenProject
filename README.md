# INTRODUCCION
- El fin de este proyecto es realizar una simulacion 
bancaria. con el manje de cuentas clientes y movimientos que se realicen.

## Se Procede a manejar Los siguentes endpoints:
> /cuentas

> /clientes

> /movimientos

## Configuracion de la aplicacion

#### 1. ejecutar el comando
mvn clean install -DskipTests=true 
- para generar el archivo .jar
### CONFIGURACION DOCKER.

#### en un terminal en la carpeta Raiz del proyecto ejecutar:
> docker-compose up
#### En un nuevo terminal ejecutar
> docker ps
- buscamos la imagen con el nombre "postgres"
- ![image](https://user-images.githubusercontent.com/29445798/160481925-99788ac2-9daa-4c2c-95ca-fc44b74142ba.png)

y copiamos el Id de la imagen y reemplazar en el siguiente comando la pablabra CONTAINER_ID.
> docker exec -it CONTAINER_ID psql -U postgres nttdata

- con el comando " \dt " se verifica la creacion de las tablas.

![image](https://user-images.githubusercontent.com/29445798/160482251-f9ad4ec8-268d-42a0-a7b9-74231081f458.png)

> En el mismo terminal ejecutamos los insert del archivo.
- Base de datos.sql.
![image](https://user-images.githubusercontent.com/29445798/160482383-d072b002-47c6-40b8-823c-1df9387c534b.png)

#### 2.- Importar el archivo json a Postman 
 - la ubicacion del archivo esta en la carpeta raiz con el nombre.
> Lista servicios.json

#### 3.- verifcar desde postman con los metodos tipo get que la informacion ingresada se encuentre disponible

![image](https://user-images.githubusercontent.com/29445798/160481426-1eebd0b4-9f72-4731-841e-c9f1ef482331.png)

