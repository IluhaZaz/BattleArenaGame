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
    public String attack(Hero enemy){
        if(this.hp <= 0){
            return "This wizard's dead";
        }
        if(enemy.hp <= 0){
            return "This enemy's already dead";
        }
        enemy.hp -= (5 - enemy.magic_armour);
        return "";
    }

    @Override
    public String ability(Hero... heroes) {
        if(this.hp <= 0){
            return "This wizard's dead";
        }
        heroes[0].hp += 4;
        return "";
    }
}
