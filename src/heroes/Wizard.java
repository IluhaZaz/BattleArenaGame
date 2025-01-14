package heroes;

import utils.txtFileHandler;

public class Wizard extends Hero{
    public Wizard() {
        this.hp = 5;
        this.armour = 0;
        this.magic_armour = 3;

        String spritePath = "src\\sprites\\wizard.txt";
        this.sprite = txtFileHandler.readFile(spritePath);
    }

    @Override
    public boolean attack(Hero enemy){
        enemy.hp -= (5 - enemy.magic_armour);
        return enemy.hp <= 0;
    }
}
