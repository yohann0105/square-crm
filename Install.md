Voici un résumé de l'installation :

Il faut avoir le socle technique Scub foundation ( http://www.scub-foundation.org ) d'installé.

Il faut créer des bases postgres pour chaque application "core" (square-core, tarifiateur-noyau, ...), le nom de BDD / User / Password est indiqué dans chaque fichier conf/dev/filters/filters.properties de ces projets
Une fois les bases créées il faut compiler les modèles, pour cela, il suffit d'utiliser la tache du socle "Publier dans le référentiel local" sur le projet eclipse square-modules-model

Il faut ensuite démarrer JoNaS (CF documentation du socle technique) Puis déployer toutes les applications dans JoNaS, pour cela, il suffit d'utiliser la tache du socle "Déployer dans JoNaS" sur le projet eclipse square-modules-app

Une fois toutes les applications déployées correctement, les structures des tables ont dû être créées au démarrage, il reste donc à initialiser les bases via la tache "Initialiser la base de données" du socle technique sur les projets square-core et square-tarificateur-noyau
Pour finir, un navigateur à l'URL http://localhost:9000/square-client-1.0-SNAPSHOT/ devrait lancer l'application (login = user, mot de passe = user)

Nous travaillons à vous mettre à disposition des livrables dans la partie download.