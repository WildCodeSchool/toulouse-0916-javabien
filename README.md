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


##Images

Accueil
![alt text](http://www.hostingpics.net/viewer.php?id=28195101accueil.jpg "Acceuil")

Un Niveau
![alt text](http://www.hostingpics.net/viewer.php?id=44118902niveau.jpg "Niveau")

Différents exemples d'exercices :
![alt text](http://www.hostingpics.net/viewer.php?id=88487403exotype.jpg "Qcm")

![alt text](http://www.hostingpics.net/viewer.php?id=50044104exotypeavecresultat.jpg "Résultat d'exercice")

![alt text](http://www.hostingpics.net/viewer.php?id=22738605finquizz.jpg "Fin de quizz")


##License
Libre de droit. Cette application est un projet d'application, à ce titre elle peut être améliorée. Aussi, si vous le désirez, vous pouvez la modifier, la transformer, ou l'adapter dans une autre langue ; n'hésitez pas à nous faire des pull request :)


##Dev Team
Développement mobile par Héléna Van Looveren, Aurélien Tuffery, Clément Mezerette de la Wild Code School.

##La Wild Code School
La _Wild Code School_ révèle les talents tech et invente le modèle pédagogique de demain. Nous voulons offrir une expérience d’apprentissage exceptionnelle à tous en développant des outils éducatifs numériques pour notre réseau d’écoles. Aujourd’hui, nous formons des développeurs à portée des entreprises en quête de talents. Demain nous proposerons un nouveau modèle applicable à d’autres compétences et métiers.