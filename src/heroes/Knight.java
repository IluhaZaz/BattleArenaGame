package heroes;

import utils.txtFileHandler;

public class Knight extends Hero{
    public Knight() {
        this.hp = 10;
        this.armour = 2;
        this.magic_armour = 0;

        String spritePath = "src\\sprites\\knight.txt";
        this.sprite = txtFileHandler.readFile(spritePath);
    }

    @Override
    public boolean attack(Hero enemy){
        enemy.hp -= (3 - enemy.armour);
        return enemy.hp <= 0;
    }

}
