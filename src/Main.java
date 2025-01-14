import heroes.Knight;

class Main {
    public static void main(String[] args) {
        Field f = new Field();
        f.addHero(new Knight(), true);
        f.addHero(new Knight(), false);

        f.showField();
    }
}