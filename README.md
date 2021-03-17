# BattleShipGame

## Abstract

The main purpose of this excerise was to develop a different version of a classic game: "BattleShip". 

## Tools

Coding on Java SE Development Kit 16 while the Interface was made using SceneBuilder and JavaFx

## How to Install 

1. Make sure that you have JavaFx installed on your pc. If not, click [here](https://openjfx.io/openjfx-docs/#install-javafx).
2. Download the package `GameBattleship.jar` from this git-repo.
3. Open in Terminal and type: <br/>
`java --module-path "path_to\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml -jar GameBattleship.jar"`

## Game Guide

### Placing Ships
The available ships are 5 and they are placed on a 10 x 10 grid.


| Type | Ships  | Positions  | Points of hit | Bonus if sunk|
| ------------- | ------------- | ------------- | ------------- | ------------- |
|1| Carrier    |5| 350| 1000|
|2| Battleship |4| 250| 500 |
|3| Cruiser    |3| 100| 250|
|4| Submarine  |3| 100|  0 |
|5| Destroyer  |2|  0 | 0  |

Create two `.txt` files one for the player: `player_SCENARIO.txt`, and another one for the enemy `enemy_SCENARIO.txt`

Each `.txt` file contains 5 lines. Each line is in the form:   type, x_cordinate, y_cordinate, is_vertical

Example: 

*"1,3,2,1" means that Carrier ship will be placed on (3,2) position horizontally.* <br/>
*"2,5,5,2" means that Cruiser ship will be placed on (5,5) position vertically.*

**Restrinction in placement**

1. Ships can be placed only inside a grid 
2. In any cell one ship maximimum can be placed
3. All ships are of different types
4. Betwenn two ships at least should cells must intervene

If the placement is not proper, an exception is thrown and the user is notified with a message

### Starting Game

After placing all ships, cick on the game icon to start playing. The window that shows up is : 

![12](https://user-images.githubusercontent.com/50829499/111382249-6b264100-86af-11eb-8097-eb043d9658a9.png)

Player is first notified whether he plays first or not. Each movement can be done inserting taget coordinates using the form `x,y`. If not, 
an exception is thrown and a message shows up. Of course in case a player shoots a ship, he plays again. 

### Menu 

#### Application 

* **Start** : Start the game from the beggining. Initialize all shots  <br/>
* **Load** : Load a proper scenario, by typing SCENARIO's name  <br/>
* **Exit** : Close application  <br/>

#### Details 

* **Enemy Ships** : Get information about enemy ships: Safe, In Danger, Sunken <br/>
* **Player Shots** : Get information about 5 last shots of player ( coordinates, hit/miss, in case of hit the type of ship) <br/>
* **Enemy Shots** : The same for enemy <br/>

### Ending Game

The game ends when a player runs out of shots, or when all ships are sunk. Then, all ships are revealed with pink colour.

![α](https://user-images.githubusercontent.com/50829499/111387523-82b4f800-86b6-11eb-950b-2e3db596704a.png)

### About - Contact 

For bugs please contact me: gianniosgeorgios45@gmail.com 

Enjoy Playing !

George Giannios



