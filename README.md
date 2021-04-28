# Stock matériel

Requirements :

Un de nos clients aimerait que l’on s’occupe de la gestion de ses stocks matériels. En effet, on effectue un grand volume de livraison et il est prêt à stocker du matériel chez lui directement afin d’éviter d’avoir à payer des frais de livraison en permanence.

On aimerait donc réaliser une application qui puisse gérer les stocks matériels d’un client (entrée / sortie). Il faudrait donc que sur cette application un client puisse :

    Consulter son stock matériel
    Pouvoir ajouter un nouveau matériel
    Pouvoir “sortir” ou “rentrer” du stock pour un matériel donné.

Pour les besoins de l’exercice, on part du principe que le client est déjà connecté et que l’on sait qui c’est, pas besoin de gérer toute la partie authentification.

Réalisation :

1. Application du Clean Architecture pour découper le code. C'est pas nécessaire pour une petite app mais si elle évolue et devient grande, cette archi est efficace. Le code contient 2 modules : app (dépend de Android plateforme), core (n'y dépend pas)

2. Pour faciliter le test : Le modèle/entité Materiel a uniquement 2 champs : nom et code (code == id). Pour l'image de chaque matériel, je l'ai fixé par une image dure mais je peux faire des autres solutions plus tard : url depuis serveur, utilisation de l'appareil photo, sélection un fichier dans sdcard/

3. Dans le module app, le code est bien respecté le MVVM supporté par Jetpack de Google (Database Room, Coroutine, ViewModel avec LiveData, DataBinding, Navigation, Single Activity, Binding Adapters, l'injection de dépendances par Dagger-Hilt). Le schéma de l'app : View -> ViewModel -> Repository -> DataSource -> Room local database

4. Un matériel est ajouté par le clic sur le bouton "Ajouter un matériel", ce bouton n'est visible que si le nom et le code sont bien remplis. Sur la liste des matériels, le clic sur l'icône "info" de chaque matériel ou sur sa zone ouvre la fenêtre de ses détails. L'icône "poubelle" pour retirer un matériel.

5. L'app a 2 groupes de tests : AllUnitTests (sans portable, ni émulateur Android), AllInstrumentedTests (avec un portable Android connecté à Android Studio)


