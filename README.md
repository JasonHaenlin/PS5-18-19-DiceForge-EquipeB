# <span style="color:royalblue">PS5-18-19-DiceForge-Equipe</span>
## <span style="color:darkcyan">Dice Forge Game using Java.</span>

This is a school project from the 1st year in computer engineering at
Polytech Nice-Sophia from 2018. The current version implements the 
classic version of the platform game Dice Forge, without some Hero Feat 
cards which need to be activated. 3 AI are actually implemented :
- Raichu : the best one who forges and purchase the highest Hero feats
- Asta : who has for main objective to complete the Smith's Hammer Card
- Totoro : a random bot

------------------------------------------------------------------------

To run the program, run the following command in the root directory:
> chechout to the last version of the code
<br>`git checkout FINAL`

> clean and build the package
<br>`mvn clean package`

> exec the main class to run the game
<br>`mvn exec:java`

------------------------------------------------------------------------

## <span style="color:darkcyan">Simple rules to follow (dev team)</span>

### Before coding
- cmd : `git pull`
- Check the task on *github project*
- Move a task in *In progress* and assign it to you

### While coding
- You have to test everytime you have a new piece of code, especially if is a sensitive part
- Think about the comments in the code and in the tests, so that other members will understand more easily.

### After coding
- cmd : `mvn test`
- cmd : `git add *` 
- cmd : `git commit -m "#<task id> <write a message>"`
- cmd : `git push`
- If the task is done put it in *Done* and put also the *Feature* in *Delivered feature* if you are done with it

### Advance feature
- You are encouraged to check the CI result, especially the quality gate

### Special care
- Be careful with the naming policy in Java
- Don't write any Frenglish please (only English)
- Don't forget the **#\<task id\>** on each commit
- Ask the other team members if you don't understand or if you are not sure about something

### Note to future developpers
- Strategies are developped in "...bot/strategy"
- The strategies are called by a bot in "...bot/player"
- Configuration files are in "src/main/resources/configuration"
- These configuration files are called in the class "diceforge/Engine"


------------------------------------------------------------------------

## <span style="color:darkcyan">Authors</span>
* Maxime CASTELLANO
* Jason HAENLIN
* Ruben HOURI
* Vincent UNG
