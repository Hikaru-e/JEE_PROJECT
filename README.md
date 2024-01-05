# Gestion des Stagiaires - Projet JEE avec EJB, JSF, Bootstrap, et Wildfly



## <center> Description du Projet </center>
Ce projet vise à créer une application de gestion des stagiaires, permettant l'utilisation par divers utilisateurs avec des droits d'accès spécifiques en fonction de leur rôle. Les acteurs principaux sont l'Administrateur DRH et le Chef de la DRH. Chacun doit s'authentifier avec un login et un mot de passe pour accéder à ses fonctionnalités. Les mots de passe peuvent être enregistrés pour faciliter l'authentification.

Les stagiaires s'inscrivent en ligne en fournissant des informations personnelles telles que prénom, nom, CIN, établissement, filière, adresse, e-mail, téléphone, et date de naissance. Ces données sont stockées en base de données. Après l'inscription, le cadre peut ajouter un stage au stagiaire, avec des détails tels que le sujet du stage, le nom de l'encadrant, la date de début et de fin, la division, et le type de stage. Les informations des stages effectués sont également enregistrées.

Le Chef de la DRH peut ajouter, modifier ou supprimer des stagiaires, des encadrants, et des stages. L'Administrateur DRH peut rechercher et gérer les stagiaires, afficher des statistiques sur les départements, générer des attestations de stage, et administrer les comptes utilisateurs.

- Environnement Technique :
1) Serveur: **Wildfly 24**
1) JDK: **Java 17**
1) Frameworks et Technologies: **EJB, JPA ,JSF, Bootstrap, JS**
- Fonctionnalités Principales :
    - **Chef DRH :**

        - Authentification.
        - Gestion des comptes utilisateurs.
        - Gestion des stagiaires.
        - Gestion des stages/encadrants.
        - Liste des stagiaires.
        - Liste des stages/encadrants.
        - Recherche des stagiaires.
        - Marquer l'absence.
        - Imprimer les attestations.
        - Afficher les statistiques.
    - **Administrateur DRH :**

        - Authentification.
        - Gestion des stagiaires.
        - Liste des stagiaires.
        - Liste des stages.
        - Recherche des stagiaires.
        - Marquer l'absence.
        - Imprimer les attestations.
        - Afficher les statistiques.

## <center> Réalisation </center>
        
Le projet utilise les technologies EJB et JSF pour la logique côté serveur et l'interface utilisateur. Bootstrap et JS sont utilisés pour améliorer l'expérience utilisateur. L'application offre une gestion complète des stagiaires, des stages, des encadrants, et des comptes utilisateurs, tout en assurant la sécurité et la précision des droits d'accès pour chaque utilisateur.

## <center> Photos de l'application </center>

![accueil](https://github.com/Hikaru-e/Java-Project/assets/77628961/779f3274-01e0-4c2b-b5b0-d9cbad789915)

![login](https://github.com/Hikaru-e/Java-Project/assets/77628961/595bfa54-ded8-4137-87bb-465dc61f7a32)

![statistiques](https://github.com/Hikaru-e/Java-Project/assets/77628961/074788a2-4173-4865-9cfd-18acb797246d)

![statistiques_2](https://github.com/Hikaru-e/Java-Project/assets/77628961/8f098f17-ee57-4848-920b-eb7aa4511555)

![liste_stagiaires](https://github.com/Hikaru-e/Java-Project/assets/77628961/7acf98c6-ebdf-462b-855d-14a1efcfad50)

![ajouter_stagiaire](https://github.com/Hikaru-e/Java-Project/assets/77628961/62f4c92a-d83f-454d-96cc-5b6002e927de)

![certificate stage](https://github.com/Hikaru-e/Java-Project/assets/77628961/8e757f53-c0ff-442d-befe-13c4d106aec7)


<div align="center">
  <img src="https://github.com/Hikaru-e/Java-Project/assets/77628961/381d2a7e-ab81-4f85-8278-85ef1f172724" width="48%" alt="menu_chef">
  <img src="https://github.com/Hikaru-e/Java-Project/assets/77628961/ed044d94-83f2-45a9-a841-57964778cbaf" width="48%" alt="menu_admin">
</div>






## <center> Réalisé par </center>
<center> 

EL AKHIRI OUSSAMA **&** LAMCHANNEQ ZAKARIA
</center>
