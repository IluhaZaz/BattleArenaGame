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
        if(this.cooldown != 0){
            return String.format("This knight's ability will be available in %d moves",
                    this.cooldown);
        }
        if(heroes[0].isDead){
            return "This hero's dead";
        }
        if(this.armour < 2){
            return "Knight has no armour to give";
        }
        this.armour -= 2;
        this.hp -= 4;
        ++heroes[0].armour;
        heroes[0].hp += 2;

        this.cooldown += 1;
        return "";
    }
}
