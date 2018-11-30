# state 
## scénario
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
- 