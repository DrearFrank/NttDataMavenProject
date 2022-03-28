# INTRODUCCION
- El fin de este proyecto es realizar una simulacion 
bancaria. con el manje de cuentas clientes y movimientos que se realicen.

##Se Procede a manejar Los siguentes endpoints:
> /cuentas

> /clientes

> /movimientos

##Configuracion de la aplicacion

#### 1. ejecutar el comando
mvn clean install -DskipTests=true 
- para generar el archivo .jar
### CONFIGURACION DOCKER.

#### en un terminal en la carpeta Raiz del proyecto ejecutar:
> docker-compose up
#### En un nuevo terminal ejecutar
> docker ps
- buscamos la imagen con el nombre "postgres"
y copiamos el Id de la imagen y reemplazar en el siguiente comando la pabla ID_IMAGEN.
>docker exec -it ID_IMAGEN psql -U postgres nttdata

-con el comando " \dt " se verifica la creacion de las tablas.

>En el mismo terminal ejecutamos los insert del archivo.
- Base de datos.sql.

#### 2.- Importar el archivo json a Postman 
 - la ubicacion del archivo esta en la carpeta raiz con el nombre.
>Lista servicios.json

#### 3.- verifcar desde postman con los metodos tipo get que la informacion ingresada se encuentre disponible
