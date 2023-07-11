# Test Simplon Côte d'Ivoire - Application de gestion des participants

Cette application web a été développée dans le cadre de la mission de Simplon Côte d'Ivoire pour faciliter la gestion des participants à leurs activités. L'application permet d'enregistrer les participants et de consulter la liste des participants enregistrés.

## Technologies utilisées

- Back end : Java avec Springboot
- Base de données : MySQL
- Front end : Thymeleaf, HTML5, jQuery

## Fonctionnalités

L'application propose les fonctionnalités suivantes :

- Enregistrement d'un participant avec les informations suivantes : nom, prénom, numéro de téléphone, adresse email.
- Consultation de la liste des participants enregistrés.

## Configuration et déploiement

Pour configurer et déployer l'application localement, suivez les étapes suivantes :

1. Téléchargez le code source de l'application.
2. Assurez-vous d'avoir les prérequis suivants :
   - Java JDK 17 installé
   - MySQL installé et configuré avec les paramètres de connexion appropriés
3. Configurez la base de données en modifiant les paramètres de connexion dans le fichier de configuration `application.yaml`. vous devez modifier les paramètres suivants :
   - `spring.datasource.url`
   - `spring.datasource.username`
   - `spring.datasource.password`
   en créant une base de données  dans votre serveur MySQL et mettre le nom à la place de your-database-name, 
   Créer également un utilisateur et mettre son nom à la place de your-username et son mot de passe à la place de your-password et lui donner tous les privilèges sur la base de données créée.
- syntaxe de  creation d'une base de données dans MySQL:
 - `CREATE DATABASE your-database-name;`
- syntaxe de création d'un utilisateur dans MySQL:
 -  `CREATE USER 'your-username'@'localhost' IDENTIFIED BY 'your-password';`
- syntaxe d'attribution de tous les privilèges à un utilisateur sur une base de données dans MySQL:
   - `GRANT  PRIVILEGES ON your-database-name.* TO 'your-username'@'localhost';`


4. Exécutez l'application en exécutant la classe `SimplonappApplication` située dans le package `com.tonde.simplonapp`.
5. Accédez à l'application dans votre navigateur à l'adresse `http://localhost:8080`.


## Docker

Le fichier Dockerfile permet de créer une image Docker de l'application. Pour créer l'image, exécutez la commande suivante dans le répertoire racine de l'application :

```bash 
docker build -t simplonapp .
docker network create springmysql-net

docker pull mysql:latest

docker container run --name mysqldb --network springmysql-net -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=simplon -d mysql:8


#run les deux apps sur le même réseau
docker container run --name simplonapp --network springmysql-net -p 8080:8080 simplonapp

``` 
