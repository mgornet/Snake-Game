# Two greedy snakes

This project was realized during my second year at ISAE-SUPAERO in 2018.
The description of the project is in french below.

## Contexte général : Blockade et Snake

Le jeu Blockade est l'un des jeux d'arcade les plus anciens (consultez Wikipedia pour les jeux d'arcade en général et Blockade en particulier). Mais vous connaissez sûrement davantage le jeu Snake dérivé de Blockade et largement décliné sur téléphone portable.

La version classique du jeu Blockade consiste à faire grandir un ou plusieurs serpents dans une arène (grille de jeu), de sorte que les serpents deviennent des obstacles.

Quand le jeu démarre, le serpent est de taille minimale et se déplace en ligne droite. Le joueur peut le faire tourner à droite ou à gauche, mais ne peut pas l'arrêter : s'il ne tourne pas, le serpent continue à progresser vers l'avant. Le serpent augmente périodiquement de taille, qu'il tourne ou qu'il continue en ligne droite. Si le serpent sort de l'arène ou entre en collision avec un autre serpent ou même avec son propre corps, le jeu s'arrête.

## Jeu de base pour le projet

### Principes généraux

Les règles sont ici celles de Blockade :

Il y a deux serpents.
Il n'y a pas de pomme, la taille de chaque serpent augmente automatiquement.
Il n'y a pas de score, le vainqueur est le serpent survivant.

L'arène de votre projet est une grille carrée composée de N x N cases et occupée par deux serpents, dont on peut différencier la tête et la queue. Le mouvement des serpents est calculé de manière périodique. Initialement, un serpent part du centre du quart supérieur-gauche de l'écran, et l'autre serpent part de la position opposée (centre du quart inférieur-droit de l'écran). À chaque cycle de calcul, chaque serpent grossit.

À chaque cycle de calcul, la tête d'un serpent occupe une nouvelle case, soit dans la même direction, soit à droite, soit à gauche. La direction initiale et le calcul de chaque nouvelle position de la tête dépendent du type de mouvement du serpent : les spécificités de ces types de mouvement seront donnés dans la section suivante.

Si la case de la nouvelle position de la tête d'un serpent est occupée (limite de l'aire de jeu ou corps de serpent), alors ce serpent s'assomme et perd la partie.

Le jeu se termine dès que l'un des deux serpents est assommé. Si les deux serpents sont assommés pendant le même cycle de calcul, il y a match nul, sinon l'autre serpent gagne la partie.

Pour coordonner ces calculs, on utilise traditionnellement un moteur de jeu qui dispose d'une horloge. À chaque cycle de calcul, le moteur de jeu demande à chaque serpent de calculer sa nouvelle position, puis détermine si chacun des serpents est assommé, et éventuellement arrête le jeu et annonce le résultat (match nul ou vainqueur).

### Types de mouvements à réaliser

Chaque serpent se déplace selon l'un des modes suivants pendant toute la durée de la partie : . grâce aux indications d'un joueur, avec les touches de direction du clavier (flèches) ; . ou bien de manière autonome, en suivant un mouvement aléatoire ou en suivant un mouvement intelligent. Ces modes de déplacement sont détaillés dans les sections suivantes.
Mouvement contrôlé par le joueur

Tant que le joueur ne tape sur aucune touche, le serpent va tout droit. Dès que le joueur tape une touche de direction, son serpent adopte cette direction ; les flèches peuvent alors soit correspondre aux points cardinaux, soit indiquer une nouvelle direction relative à la direction courante (à gauche ou à droite).

#### Mouvement aléatoire

Le serpent choisit aléatoirement sa direction, même si la case correspondante est occupée.

#### Mouvement intelligent

Le serpent peut savoir si les cases de devant, de droite ou de gauche sont occupées. Il choisit aléatoirement d'aller dans l'une des directions libres. Si le serpent ne peut pas choisir de direction libre, il s'assome.

#### Mouvement très intelligent

Le serpent peut mémoriser les différentes positions occupées par le serpent adverse à chaque cycle de calcul, et en tenir compte dans le calcul de sa prochaine direction. Si le serpent ne peut pas choisir de direction libre, il s'assomme.

### Jeu en réseau

De plus, deux joueurs pourront jouer un réseau : dans ce cas, chacun des joueurs lancera son propre jeu sur son ordinateur, se connectera à l'autre joueur et contrôlera son propre serpent. Il verra sur son écran les deux serpents : le sien et celui de son adversaire.

Les deux programmes des joueurs communiqueront en mode client/serveur. Chaque programme doit pouvoir être lancé soit en mode client, soit en mode serveur. Quand deux joueurs veulent faire une partie, ils doivent décider oralement quel joueur lancera son programme en mode serveur, et quel joueur lancera son programme en mode client. Le serveur doit être lancé avant le client.

Le protocole de communication s'appuiera sur le protocole standard TCP et le serveur utilisera le port 6789. Le protocole permettra à chaque joueur d'envoyer la position de son serpent et de recevoir la position de l'autre serpent à chaque cycle de calcul. Il faudra dessiner le serpent de l'adversaire en fonction des messages reçus.
