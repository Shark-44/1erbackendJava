# 1er backend en Java

Créant une bdd sous MySQL j'ai opté pour 3 entités Studient, School et Langage

Le but c'est de pouvoir étudier la nuance avec node et démontrer que je peux construire en java
Ayant déjà regardé Symfony, la similitude me permet une meilleur approche.
C'est aussi un petit aide mémoire afin de refaire
Prise de connaissance de Eclipse et Maven

1ere étape l'architecture aidé https://start.spring.io/ et testé avec Postman

src/main/java/com/datajava/controller
     |			|	|-->LangageController.java
     |			|	|-->SchoolController.java
     |			|	|-->StudentController.java
     |                       |
     |			|-/model
     |			|       |-->Langage.java
     |			|       |-->School.java
     |   		|       |-->Student.java
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

2ieme étape Liaison Many-to-Many entre student et langage

1 etudiant apprend 1 ou plusieurs langues et
1 langue peut etre appris par 0 ou plusieurs étudiants 

Liaison Many-to-Many entre school et langage

1 langue peut être enseignée par plusieurs écoles
et 1 école peut enseigner une ou plusieurs langues