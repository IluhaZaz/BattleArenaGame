package heroes;

import utils.txtFileHandler;

public class Archer extends Hero{

    public Archer() {
        this.hp = 7;
        this.armour = 1;
        this.magic_armour = 1;

        this.loadSprite();
    }

    @Override
    protected void loadSprite() {
        String spritePath = "src\\sprites\\archer.txt";
        this.sprite = txtFileHandler.readFile(spritePath);
    }

    @Override
    public String attack(Hero enemy){
        if(this.isDead){
            return "This archer's dead";
        }
        if(enemy.isDead){
            return "This enemy's already dead";
        }
        enemy.hp -= (5 - enemy.armour);
        return "";
    }

    @Override
    public String ability(Hero... heroes) {
        if(this.isDead){
            return "This archer's dead";
        }
        for(Hero enemy: heroes){
            enemy.hp -= (3 - enemy.armour);
        }
        return "";
    }
}
