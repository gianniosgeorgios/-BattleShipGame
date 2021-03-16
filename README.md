# BattleShipGame

![4aef37d9-a816-4da2-8bed-0c9aa884a4d2](https://user-images.githubusercontent.com/50829499/111379192-82fbc600-86ab-11eb-8eeb-d5f25808062e.png)

## Abstract

The main purpose of this excerise was to develop a different version of a classic game: "BattleShip". 

## Tools

Coding on Java SE Development Kit 16 while the Interface was made using SceneBuilder and JavaFx

## How to Install 

1. Make sure that you have install JavaFx to your pc. If not, click [here](https://openjfx.io/openjfx-docs/#install-javafx).
2. Download the package `GameBattleship.jar` from this git-repo.
3. Open in Terminal and type: <br/>
`java --module-path "path_to\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml -jar GameBattleship.jar"`

## Game Guide

### Placing Ships
The aveilable ships are 5 and they are placed to a 10 x 10 grid.


| Type | Ships  | Positions  | Points of hit | Bonus if sunk|
| ------------- | ------------- | ------------- | ------------- | ------------- |
|1| Carrier    |5| 350| 1000|
|2| Battleship |4| 250| 500 |
|3| Cruiser    |3| 100| 250|
|4| Submarine  |3| 100|  0 |
|5| Destroyer  |2|  0 | 0  |

Create two `.txt` files one for player: `player_SCENARIO.txt`, and another one for enemy `enemy_SCENARIO.txt`

Each `.txt` file contains 5 lines. Each line is in the form:

type, x_cordinate, y_cordinate, is_vertical

Example: 

*"1,3,2,1" means that Carrier ship will be placed to (3,2) position horizontally.* <br/>
*"2,5,5,2" means that Cruiser ship will be placed to (5,5) position vertically.*

### Starting game

After placing all ships, cick on the game icon to start playing. The window that shows up is : 

![12](https://user-images.githubusercontent.com/50829499/111382249-6b264100-86af-11eb-8097-eb043d9658a9.png)

If the placement is not proper, an exception is throwded and user is notificated with message:


### Open Game

### More Details



