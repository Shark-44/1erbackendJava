# 1er backend en Java

Créant une bdd sous MySQL j'ai opté pour 3 entités Studient, School et Langage

Le but c'est de pouvoir étudier la nuance avec node et démontrer que je peux construire en java
Ayant déjà regardé Symfony, la similitude me permet une meilleur approche.
C'est aussi un petit aide mémoire afin de refaire
Prise de connaissance de Eclipse et Maven

1) L'architecture aidé https://start.spring.io/ et testé avec Postman

src/main/java/com/datajava/controller
     |			|	    |-->LangageController.java
     |			|	    |-->SchoolController.java
     |			|	    |-->StudentController.java
     |              |
     |			|-/model
     |			|       |-->Langage.java
     |			|       |-->School.java
     |   		     |       |-->Student.java
     |			|
     |			|-/repository
     |			|       |-->LangageRepository.java
     |			|       |-->SchoolRepository.java
     |			|       |-->StudentRepository.java
     |			|
     |			|-/service
     |			|       |-->LangageService.java
     |			|       |-->SchoolService.java
     |			|       |-->StudentService.java
     |			|
     |			|-DatajavaApplications.java
     |-/resources/application.propreties(acces bdd)

Sous datajava le fichier pom.xml (pour la gesttion des dépendances )

2) Liaison Many-to-Many entre student et langage

1 etudiant apprend 1 ou plusieurs langues et
1 langue peut etre appris par 0 ou plusieurs étudiants 

Liaison Many-to-Many entre school et langage

1 langue peut être enseignée par plusieurs écoles
et 1 école peut enseigner une ou plusieurs langues

3) Suite à une mauvaise compréhension de la réponse d'une requete GET, je découvre DTO(Data Transfer Objects). Un test pour comprendre.

4) Liaison one-to-many entre student et school

5) Etude et mise en place d'upload images dans le backend comme multer dans node.js
 Apres la mise en place de mon front correction effectuée pour l'ajout d'image sans relancer le serveur. voir webconfig.java et gestion du cache.

 6) Mise en place de spring security pour un mode usr/root
 suivant ce tuto :https://www.invivoo.com/securiser-application-spring-boot-spring-security/

7) Suite a un developpement du frontend sous react-ts. J'ai eu des erreurs de boucles infinies avec les liaisons. L'ajout de @JsonBackReference m'a permis de corriger le probleme. Les liaisons m'ont bloqué aussi sur les posts avec une erreur 415. Cela a été reglé en redirigeant sur des fichiers DTO.

8) Pour effectuer un DELETE, j'ai du repenser la méthode en raison des liaisons.
  Un Get by ID me renvoyait pour un étudiant. {"idStudent":1,"name":"Dupont","firstname":"Martin","birthday":949359600000,"photo":"Martin_Dupont.webp","langages":[{"idLangage":2,"nameLangage":"Java"},{"idLangage":1,"nameLangage":"Php"}]} j'ai donc en laison un objet de langage et dans le cas ou l'etudiant n'a pas de relation avec langage j'obtenais une reponse null.
  Donc un DELETE impossible.
  
  Sachant qu'en SQL je dois supprimer les liaisons avant d'effacer une ligne dans une table et pour ressoudre se problème je suis passé par cette logique

          a) Un get ALL et chercher dedans si l'ID exsite pour eviter le null
          b) Verifier la présence des liaisons entre les tables langage et school 
          c) Supprimer les liaisons 
          e) Enfin DELETE 

9) Configuartion pour jwt
un dossier securité : AppAuthProvider.java /JwtAuthentificationFilter.jawa et JwtUtil.java

Commande pour ajouter un user curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d '{
    "username": "USERNAME",
    "password": "PASSWORD"
}'
10) Gerer un token avec JWT et utilisation d'un cookie.

11) Reprise du CRUD pour student. le dernier point était de faire un update sous conditions :
1- Etudiant inscrit sans école, sans langage
2- Associer une ecole 
3- Associer des langages en fonction de l'école.

12) Creation d'une table notation avec deux colonnes note et dateNote. Le but est de creer un ensemble de jeu test pour lancer des commandes telles que rechercher la meilleure note, la meilleure moyenne, etc... Je n'ai pas d'idée precise mais un exercice qui me trotte dans la tête. Car j'ai vu en commande SQL certaines fonctions dont je pourrais appliquer dans node.js, mais dans ce genre de backend je m'interroge. Et j'ai envie de mettre tout sur un serveur qui sera une prochaine étape. 

13) Aujourd'hui je cherche a mettre mon backend sous docker et le faire fonctionner avec ma bdd qui est sous docker. l'ajout d'un script "wait-for-it.sh" m'assure le démarrage du container mysql avant le api backend.
Prochaine étape faire de même avec le front et de pouvoir consulter mon projet depuis une url.