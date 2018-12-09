# state 

## Exemple de l'implémentation avec le manager

```java
@Override
protected void setup() {
    manager.addState(new Template() { // first state
        @Override
        public void onInitialization(Board boardView) {
            // ask the forge to build the dices with the following priorities
            // if the forge strategy can't craft a Gold face, it will try the next one
            forge.setdiceTypePriority(Resources.GOLD, Resources.MOON_STONE, Resources.SUN_STONE);
        }

        @Override
        public boolean onCondition(Board boardView) {
            // we will do the doAction method as long as we order it
            return gameRound < 3;
        }

        @Override
        public void doAction(Board boardView) {
            // try to forge by removing the gold faces and by puting the highest faces
            // the true attribut means that we want to craft as long as we have gold
            forge.compute(new SingleResource(), new HighestForge(), true);
            // if the forge didn't proceed well, we can try to exploit a card 
            // we are ordering to buy the highest one the player can afford to buy
            exploit.compute(new HighestExploit());
        }

        @Override
        public void doElse(Board boardView) {
            // if the condition (onCondition) is false
            // we proceed to the nest template, the one juste below
            manager.nextTemplate();
        }
    }).addState(new Template() { // second state
        @Override
        public void onInitialization(Board boardView) {
            // set a new priority
            forge.setdiceTypePriority(Resources.MOON_STONE, Resources.SUN_STONE);
        }

        @Override
        public boolean onCondition(Board boardView) {
            // new condition
            return gameRound < 5;
        }

        @Override
        public void doAction(Board boardView) {
            forge.compute(new SingleResource(), new HighestForge(), true);
            exploit.compute(new HighestExploit());
        }

        @Override
        public void doElse(Board boardView) {
            exploit.compute(new HighestExploit());
            forge.compute(new SingleResource(), new HighestForge(), true);
        }
    }).build();
}

@Override
protected void play() {
    // run the new sequence and the manager will handle everything
    manager.ExecSequence();
}
```

## scénarios

### scénario 1: 

- un dé fragment soleil et un dé mix en or et fragment lune
    - possibilité d'avoir une face point de victoire
- dés que possible (généralement 2 ou 3ème tour) il faut acheter un coffre
- au début on va récupérer des faces or puis on commence à faire notre dé soleil en priorité
- des que les des sont bon on commence à faire des exploits 
    - on peut faire un exploit prématurément si nos ressources soleil et lune sont élevés.
- on va récupérer un maximum d'exploit.
    - néanmoins, si notre or est au max (ou presque) on peut se permettre d'améliorer le dé pour une face avec plus de points
- on pourra aussi ajouter le fait de jouer à nouveau pour faire 2 exploits plus petit qui vont au final rapporter plus.


### scénario 2:

- un dé avec des ressources et un avec de l'or
- faire le coffre et récupérer le marteau dés que possible pour commencer à le rentabiliser
- améliorer les dés
    - or & soleil + lune
- acheter des exploits
- reprendre un marteau quand nécessaire et si le nombre de tour est suffisant pour le finir 

### ...

## Observations

- une action repose toujours sur une ou plusieurs conditions
- le set d'action peut varier selon l'état de la partie
- l'état d'une partie change selon certaine.s condition.s
- il faut construire une action et un state avec une liste de condition


## Conditions possible

- selon le nombre de tour actuel/restant
- selon la quantité de ressources
    - condition qui influt une action simple en actions multiples (ex. rejouer pour faire 2 exploits)
    - prioritisé une action si les ressources sont élevés
- selon une carte (ex. marteau)
    - si on posséde une carte marteau, il faut changer la façon de jouer si ce n'est pas déjà le cas
- selon la qualité du/des dé.s
    - continuer à crafter des faces si le dé n'est pas bon (vérification sur un ou deux dés)

## Actions possible

- forger une face
- jouer une carte

## States possible

- début de partie
    - améliorer le dé
    - prendre les cartes indispensables
- milieu de partie
    - favoriser les cartes
- fin de partie
    - vouloir utiliser un maximum de ressources pour ne rien gaspiller

On peut avoir des états alternatifs

- l'état du marteau. une fois le marteau en main, on doit adapter la façon de jouer


### Scénario 3

- 1° étape : Construire les dés (on n'achète pas d'exploit)
	Calcul nombre de ressource (si on a assez de pierre lune pour acheter marteau)
	Calcul potentiel de chaque dé
	Calcul nombre de round restant

	Si on peut finir le marteau avant la fin de la partie, onn passe à l'étape 2
	Sinon on forge des golds (règles classiques)

- 2° étape : Acheter le marteau
	Si pas de marteau dispo ou nombre de round resgtant trop faible
	Passe aux strat suivante (faire comme Raichu)

	Sinon, on achète le marteau

- 3° étape : Remplir le marteau
	Condition : un marteau est dispo (sinon on passe à l'étape suivante)

	Calcul nombre de ressource, nombre de round restant et potentiel des dés
	
	Règle de remplissage du marteau pas encore définie

	Si beaucoup de pierre de pierre lune et peu de pierre soleil
	On fait le Cancer et et tout les golds gagné vont au marteau

	Si beaucoup de pierre soleil et peu de pierre lune
	On fait le Sphinx

	"A-t-on la capacité de finir le marteau rapidement" oui alors on forge des ressource
	Sinon "Aura-t-on le temps de finir le marteau avant la fin de la partie" oui alors exploit classique (sans effet)
	Sinon on forge des golds

- 4° étape : Exploits classiques
	Si possibilité de faire action à exploit direct, reste au moin 1 manche et peu de ressource
	Jouer Cancer, ou Sphynx

	Sinon, si on peut faire un exploit sans effet alors faire le plus chère
		Sinon forge des ressources sur le dés 

