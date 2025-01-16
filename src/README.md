Before game starts you can choose heroes to add on Field like
```
Field f = new Field();
f.addHero(new Knight(), true);
```
where for `addHero` first argument is Hero and the second `isUpper` is it's side

So heroes are

1. Knight with 10 hp, 2 armour and no magic armour, 
deals 3 hp physical damage
2. Archer with 6 hp, 1 armour and 1 magic armour,
   deals 5 hp physical damage
3. Wizard with 5 hp, 1 armour and 3 magic armour,
   deals 5 hp magic damage

You can choose who is upper player and who is down with `f.downPlayerStarts();` 
or by just removing it

After game begins players use one of heroes by turn,
you can just attack (damage decreases by enemy's physical 
or magic armour due to attack nature) or use ability:

1. Knight removes his armour ang gives one point to ally,
also his hp decreases by 4 and ally's increases by 2
2. Archer shot all enemies with 3 points of damage.
Has 2 moves cooldown
3. Wizard heals ally with 4 hp, can make hero alive.
Has 3 moves cooldown

Hero dies only on the next opponent's move after it's hp
become negative.

Winner is that one who has at least one alive hero