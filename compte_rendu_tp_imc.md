# Compte-rendu des difficultés rencontrées - TP Calcul IMC

Au cours de la réalisation de ce TP sur Android Studio, les principales difficultés techniques rencontrées et résolues sont les suivantes :

1. **Configuration et lancement de l'émulateur :**
   - Au démarrage, l'absence d'appareil virtuel configuré a généré l'erreur *« No target device found »*. 
   - **Solution :** Utilisation du *Device Manager* pour créer et lancer un émulateur de type téléphone (ex. *Medium Phone* ou *Pixel*).

2. **Gestion des avertissements de l'interface (XML) :**
   - L'environnement de développement a affiché des alertes mineures (absence d'attributs `autofillHints` et recommandations sur le style des boutons).
   - **Solution :** Ces messages étant de simples avertissements de style et d'accessibilité, ils n'ont pas empêché la compilation et le bon fonctionnement de l'application.

3. **Validation et robustesse des saisies utilisateur (Kotlin) :**
   - La gestion des cas limites (champs vides, valeurs nulles, négatives ou alphabétiques) présentait un risque de plantage (*crash*).
   - **Solution :** Implémentation rigoureuse de contrôles à l'aide de méthodes sécurisées (`toDoubleOrNull`, structures conditionnelles `if`/`when`) et affichage de messages d'erreur contextuels par `Toast`.