package heroes;

import java.util.List;

public abstract class Hero {
    protected int hp;
    protected int armour;
    protected int magic_armour;

    public int cooldown = 0;
    public List<String> sprite;

    public int getHp() {
        return this.hp;
    }

    public abstract String attack(Hero enemy);

    public abstract String ability(Hero... heroes);
}
