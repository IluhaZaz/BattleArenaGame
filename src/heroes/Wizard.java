package heroes;

import utils.txtFileHandler;

public class Wizard extends Hero{
    public Wizard() {
        this.hp = 5;
        this.armour = 0;
        this.magic_armour = 3;

        this.loadSprite();
    }

    @Override
    protected void loadSprite() {
        String spritePath = "src\\sprites\\wizard.txt";
        this.sprite = txtFileHandler.readFile(spritePath);
    }

    @Override
    public String attack(Hero enemy){
        if(this.isDead){
            return "This wizard's dead";
        }
        if(enemy.isDead){
            return "This enemy's already dead";
        }
        enemy.hp -= (5 - enemy.magic_armour);
        return "";
    }

    @Override
    public String ability(Hero... heroes) {
        if(this.isDead){
            return "This wizard's dead";
        }
        if(this.cooldown != 0){
            return String.format("This wizard's ability will be available in %d moves",
                    this.cooldown);
        }
        heroes[0].reanimate(4);

        this.cooldown += 3;
        return "";
    }
}
