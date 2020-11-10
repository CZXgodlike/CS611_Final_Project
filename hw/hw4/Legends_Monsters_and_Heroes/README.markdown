## **Legends: Monsters and Heros**

This assignment is to implement a role playing game in Java using Object Oriented principles 
and design. This is a magical game full of spells, heroes and monsters.

The heroes and monsters live in a world represented by a square grid of fixed dimensions. This 
world has three types of places to be; Common space (either a safe zone or where heroes come 
across monsters and fight), Inaccessible (places the heroes can't go), and Markets (where items 
are bought and sold). The heroes and monsters do not get along and therefore fight each other. 
Heroes can use weapons, armors, potions, and spells against the monsters. Every time the heroes 
win, they gain some experience and some money. When they accumulate enough experience 
they level up which means that their skills become stronger. The goal of the game is for the 
heroes to gain experience and level up indefinitely.

#### **I.Items:**
**weapon:** A weapon has a name, a price and a minimum hero level required to be used by a hero. It has a specific 
        amount of damage that it can inflict, and it may require one or two hands to be 
        used.
        
**armor:** An armor, which when worn by a hero, reduces the incoming damage from 
       enemy's attacks. An armor has a price, a name and a minimum hero level as a 
       requirement to be used.
       
**potion:**  A potion can be used by a hero in order to increase one of their statistics by some 
            amount. Potions are single-use items which means that once they are used they 
            cannot be reused. Potions as well have a price, a name and a minimum hero level 
            as a requirement to be used.
            
**spell:** A spell represents a magic attack and can be executed by a hero. A spell has a 
           name, a price and a minimum hero level required to be used by a hero. A spell has 
           a damage range and an amount of magic energy (called mana ) that it requires in 
           order to get casted. After casting a spell, this specified amount of mana is 
           deducted from the hero. The level of damage a spell causes depends on the 
           dexterity skill level of the hero.
           
o ice spell: apart from the damage it causes it also reduces the damage of 
           the enemy.
           
o fire spell: apart from the damage it causes it also reduces the defense of 
            the enemy.
            
o lightning spell: apart from the damage it causes it also reduces the 
                 dodge chance of the enemy.
                 
#### II.Heroes:
The heroes have a name, a level and some health power (called HP ). When their hp becomes equal to zero the 
hero faints. They also have some mana and some skills that affect their fighting 
efficiency. Those skills are strength, 
            dexterity and agility. 
            
A hero's strength is added to the amount of damage they deal when 
            using a weapon. A hero's dexterity increases the amount of damage they deal when 
            casting a spell. A hero's agility increases their chance to dodge an incoming attack.

A hero has some money and some exp. When a hero accumulates enough experience points 
it levels up. During each of those times the levels of the skills of the hero get increased by 
some number.

There are three types of heroes; warriors, sorcerers and paladins. 

o Warriors are favored on strength and agility. 

o Sorcerers are favored on the dexterity and the agility. 

o Paladins are favored on strength and dexterity.

Favored means that their starting stats on those sectors will be increased and that every 
time they level up those statistics will be furtherly boosted.

#### III.Monsters:

Monsters have a name, some HP, and a level. Their stats include a base damage that they 
can inflict, a defense stat, which is deducted from the damage of an incoming attack and a 
dodge chance for evading incoming attacks. There are three kinds of monsters: dragons, 
exoskeletons and spirits. 

o Dragons have a higher damage 

o Exoskeletons have increased defense 

o Spirits have a higher dodge chance

#### IV.Map:

The map of the game is represented by a grid of specific dimensions. The 
grid contains tiles which may be inaccessible, marketplaces, or common tiles/cells. As 
the name suggests, heroes cannot access a non-accessible tile. The heroes can buy items 
from the market if they have enough money to do so. Moreover, they can sell the items in their inventory for half the price at which they were bought.


#### V.Fight:
When players enter a normal cell, player will have a chance of 30% encountering
with monsters. The number of monsters is the same as that of heroes and their level
is the same as the highest level of heroes.

During the fight, hero can take regular attack or spell attack every round.
Hero will regain 0.1 * current mana after each round. However, they cannot 
change weapons or use potions during fighting. Once a fight is over, that is, 
hero faints or monster is dead, the winner will go to help his partner.

After the fight, every survived hero will get 2 exp and 100 * level money as reward.

#### VI.Manual:

Manual is only available when hero is moving on the map or is in the market.
It can be opened by entering `m`. You can open inventory, quit game, show hero stats
and open operating instructions through it.

#### VI.Controls:
At the beginning, player can select no more that 3 unique heroes. Enter the names of them and 
`w` - move up

`s` - move down

`a` - move left

`d` - move right

`space` - regular attack

`m` - open manual

`c` - close manual

`i` - open inventory

`s` - show stats

`h` - open operating instructions

`q` - quit game

