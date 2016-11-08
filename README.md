#JAVABIEN

*Cette application a pour but de proposer une initiation ludique et gratuite au code par le biais du langage de programmation Java. Il s’agit d’une version Béta développée en un mois dont le contenu est destiné à être amélioré et enrichi de façon qualitative et quantitative. L’application a été conçue pour un public de curieux de tout âge, son ton et contenu sont volontairement décalés. Have fun !*


##Installation
Cloner/télécharger le projet puis ouvrir dans AndroidStudio.

##Process
Nous avons articulé le fonctionnement de notre application autour d'une base de données SQLite. Il existe quatre modèles d'exercices :

- Le "Vrai" : un exercice dans lequel chacunes des propositions est vraie (d'où sont nom) et qui permet de délivrer des infos réponses correspondants au choix.

- Le "QCM" : un exercice à choix multiples avec une seule bonne réponse.

- Le "Drag" : un exercice où l'on doit remettre les lignes dans l'ordre avec un sytème de drag and drop. un ordre correct est repertorié dans la base de données et la validation de l'exercice est fonction de cet ordre.

- Le "Quizz" : un enchainement de 5 questions rapides venant clôturer un chapître.


Chacun de ces exercices constitue une structure dans laquelle les données entrées dans le SQLite vont s'adapter: il suffit donc pour compléter ou modifier l'application de rajouter des entrées dans le SQLite en respectant les catégories d'exercices précédemment enoncées (vrai,qcm,drag,quiz).

Nous avons implémenté trois sons : un pour le juste, deux pour le faux. Nous travaillons actuellement pour ajouter un bouton de désactivation du son dans l'application.


##Backend

L'application est limitée à quatre niveaux de difficultés principalement en raison du découpage graphique du "J", représentant les différents paliers d'apprentissage. Des exercices peuvent être ajoutés (et bien sûr corrigés) sans limite. Pour ce faire, nous avons instauré une méthode d'organisation détaillée ci-après selon l'ordre des colonnes de la base de données :

- Catégorie : de 1 à 4 pour le niveau, quizz s'il s'agit d'une question du quizz (quizz valide sert à enregistré si un quizz a été validé).

- Quizz_Catégorie : de 1 à 4 pour le niveau, 0 s'il ne s'agit pas d'un exercice de type quizz.

- Id_exo : ordre d'apparition des exercices d'un niveau.

- Cours : contenu affiché en en-tête d'exercice pouvant servir de contenu pédagogique ou de consigne.

- Question : consigne ou directive de l'exercice (s'affiche en gras).

- Proposition, Proposition2, Proposition3, Proposition4 : correspondent aux choix affichés dans les boutons de gauche à droite ; proposition4 n'est utilisé que pour le "drag & drop".

- Info_réponse : correspond au message affiché en cas de bonne réponse.

- Info_réponse2, Info_réponse3 : correspondent aux réponses fausses entrées dans les propositions entrées (voir plus haut) de gauche à droite.

- Réponse : reponse juste attendue.

- Exo_type : qcm, vrai, drag.

- Exo_nom : nom de l'exercice affiché dans ListExoActivity.

- Avancement : détermine l'accessibilité de l'exercice ou la réussite d'un quizz (0 ou 1).


##Images

Accueil
![Accueil](http://imgur.com/oJOAPAI.jpg)

Un Niveau
![Un Niveau](http://imgur.com/H7KJ8mO.jpg)

Différents exemples d'exercices :
![Qcm](http://imgur.com/q7ZRjMo.jpg)

![Résultat d'exercice](http://imgur.com/d0lgLug.jpg)

![Fin de Quizz](http://imgur.com/SQ54PfC.jpg)


##License
Libre de droit. Cette application est un projet de formation, à ce titre elle peut être améliorée si vous le désirez : vous pouvez la modifier, l'adapter dans une autre langue, rajouter du contenu... N'hésitez pas à nous faire des pull request :)


##Dev Team
Développement mobile par Héléna Van Looveren, Aurélien Tuffery, Clément Mezerette de la Wild Code School.

##La Wild Code School
La _Wild Code School_ révèle les talents tech et invente le modèle pédagogique de demain. Nous voulons offrir une expérience d’apprentissage exceptionnelle à tous en développant des outils éducatifs numériques pour notre réseau d’écoles. Aujourd’hui, nous formons des développeurs à portée des entreprises en quête de talents. Demain nous proposerons un nouveau modèle applicable à d’autres compétences et métiers.