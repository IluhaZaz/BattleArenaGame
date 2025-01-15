package heroes;

import utils.txtFileHandler;

import java.util.List;

public abstract class Hero {
    protected int hp;
    protected int armour;
    protected int magic_armour;
    protected boolean isDead = false;

    public int cooldown = 0;
    public List<String> sprite;

    protected abstract void loadSprite();

    public int getHp() {
        return this.hp;
    }

    public boolean isDead() {
        return this.isDead;
    }

    public void reanimate(int hp){
        this.hp += hp;
        if(this.hp > 0){
            this.isDead = false;
            this.loadSprite();
        }
    }

    public void tryMakeDead(){
        if(this.hp <= 0){
            this.isDead = true;
            this.sprite = txtFileHandler.readFile("src\\sprites\\dead.txt");
        }
    }

    public abstract String attack(Hero enemy);

    public abstract String ability(Hero... heroes);
}
