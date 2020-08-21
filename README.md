SPECIFICATION

Short description: This software is presented to the user by a JFrame which has only 1 dropdown option. This dropdown has 2 items both of which represent a game.
By choosing one of the items, the approppriate game is automatically selected based on dropdown listeners. Dynamic panel has the responsibility to switch
between games according to the user's wishes/actions which means that 1 panel can display 2 games. 
The JFrame's title is always set to display the current game name. Not to forget, the JFrame itself is centered on the user's screen.
The code is designed in such a way that if a new game needs to be designed, only 1 class needs to be created (that game class) and the current classes need no update.

Game 1: 'Day of the week game'
* a) description: user must select 3 things in any order: month, day and year
* b) goal of the game: user should set such a date that is matched with 'Friday'
* c) other details: before clicking the 'Check' button the following things are checked for validity:
    -- month must be chosen from the dropdown (January - December)
    -- day must be written down (1 - 31); invalid numbers or characters will not be possible to add in any way
    -- year must be written down (numbers only); invalid characters will not be possible to add in any way
    -- if all previous data is correctly set, the system checks if the set date matches with 'Friday' and an according colored message is displayed to the user

Game 2: 'Dice game'
* a) description: user rolls the number of dices he previously selected
* b) goal of the game: user should get the same number on all of the dices
* c) other details: the game starts only after the user selected 2 or more dices to be thrown
    -- displayed dices number is chosen randomly
    -- after each throwing of the dices, the software checks whether the each dice holds the same number
    -- finally, a message is displayed to the user along with dice numbers and a number of attempts to get the same number on each dice (number of throws)
