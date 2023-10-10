Pour tester ce proto je propose des comptes de test :

- user@test.diginamic : utilisé pour voir l'implémentation des absences validées sur le calendrier individuel
- manager@test.diginamic 
- admin@test.diginamic : les trois autres sont utilisés pour voir la gestion des routes en fonction des droits
- adminmanager@test.diginamic

Tous se connectent avec le mdp : diginamic

Concernant les features, si dans le bandeau de navigation un click ne répond pas, c'est que la feature n'est pas implémentée.

Afin de tester au mieux, je propose d'éxécuter le script sql "CREATE light_rh" à la racine du repo back afin de générer la base "light_rh" ainsi qu'un petit jeu de données
