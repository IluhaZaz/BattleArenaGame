package heroes;

import utils.txtFileHandler;

public class Knight extends Hero{
    public Knight() {
        this.hp = 10;
        this.armour = 2;
        this.magic_armour = 0;

        this.loadSprite();
    }

    @Override
    protected void loadSprite() {
        String spritePath = "src\\sprites\\knight.txt";
        this.sprite = txtFileHandler.readFile(spritePath);
    }

    @Override
    public String attack(Hero enemy){
        if(this.isDead){
            return "This knight's dead";
        }
        if(enemy.isDead){
            return "This enemy's already dead";
        }
        enemy.hp -= (3 - enemy.armour);
        return "";
    }

    @Override
    public String ability(Hero... heroes) {
        if(this.isDead){
            return "This knight's dead";
        }
        if(heroes[0].isDead){
            return "This hero's dead";
        }
        this.armour -= 2;
        ++heroes[0].armour;
        return "";
    }
}
