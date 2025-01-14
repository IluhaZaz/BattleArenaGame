package heroes;

import utils.txtFileHandler;

public class Archer extends Hero{

    public Archer() {
        this.hp = 7;
        this.armour = 1;
        this.magic_armour = 1;

        String spritePath = "src\\sprites\\archer.txt";
        this.sprite = txtFileHandler.readFile(spritePath);
    }

    @Override
    public boolean attack(Hero enemy){
        enemy.hp -= (5 - enemy.armour);
        return enemy.hp <= 0;
    }
}
