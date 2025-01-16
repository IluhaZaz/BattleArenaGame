import heroes.Archer;
import heroes.Knight;
import heroes.Wizard;

class Main {
    public static void main(String[] args) {
        Field f = new Field();
        f.addHero(new Knight(), true);
        f.addHero(new Archer(), true);
        f.addHero(new Wizard(), true);
        f.addHero(new Wizard(), false);
        f.addHero(new Wizard(), false);
        f.addHero(new Archer(), false);

        f.startGame();
    }
}