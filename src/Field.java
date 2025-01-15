import heroes.Archer;
import heroes.Hero;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Field {
    private boolean isUpMove;

    private List<Hero> upSide;
    private List<Hero> downSide;

    public Field(){
        this.isUpMove = true;

        this.upSide = new ArrayList<Hero>();
        this.downSide = new ArrayList<Hero>();
    }

    public void addHero(Hero hero, boolean isLeft){
        if(isLeft){
            this.upSide.add(hero);
        }
        else{
            this.downSide.add(hero);
        }
    }

    public void showField(){
        for(int i = 0; i < this.upSide.getFirst().sprite.size(); ++i) {
            String line = "";
            for (Hero hero : this.upSide) {
                line = line.concat(hero.sprite.get(i));
            }
            System.out.println(line);
        }
        for(Hero hero: this.upSide){
            System.out.print(hero.getHp() + "\t\t\t\t ");
        }
        System.out.print("\n");

        for(int i = 0; i < this.downSide.getFirst().sprite.size(); ++i){
            String line = "";
            for (Hero hero : this.downSide) {
                line = line.concat(hero.sprite.get(i));
            }
            System.out.println(line);
        }
        for(Hero hero: this.downSide){
            System.out.print(hero.getHp() + "\t\t\t\t ");
        }
        System.out.print("\n");
    }

    protected String attack(int attackPos, int defPos){
        if(this.isUpMove) {
            return this.upSide.get(attackPos).attack(this.downSide.get(defPos));
        }
        else {
            return this.downSide.get(attackPos).attack(this.upSide.get(defPos));
        }
    }

    protected String useAbility(int usePos, int otherPos){
        if(this.isUpMove) {
            if (otherPos == -1) {
                Hero[] enemies = new Hero[this.downSide.size()];
                for(int i = 0; i < this.downSide.size(); ++i){
                    enemies[i] = this.downSide.get(i);
                }
                return this.upSide.get(usePos).ability(enemies);
            }
            return this.upSide.get(usePos).ability(this.upSide.get(otherPos));
        }
        else {
            if (otherPos == -1) {
                Hero[] enemies = new Hero[this.upSide.size()];
                for(int i = 0; i < this.upSide.size(); ++i){
                    enemies[i] = this.upSide.get(i);
                }
                return this.downSide.get(usePos).ability(enemies);
            }
            return this.downSide.get(usePos).ability(this.downSide.get(otherPos));
        }
    }

    protected String isGameEnded(){
        boolean leftAlive = false;
        boolean rightAlive = false;

        for(int i = 0; i < this.upSide.size(); ++i){
            if(!this.upSide.get(i).isDead()){
                leftAlive = true;
            }
            if(!this.downSide.get(i).isDead()){
                rightAlive = true;
            }
        }
        if(leftAlive && rightAlive){
            return "GameNotEnded";
        }
        if(leftAlive){
            return "Up player won";
        }
        return "Down player won";
    }

    public void startGame(){
        Scanner scanner = new Scanner(System.in);
        String res = "GameNotEnded";

        this.showField();
        while(res.equals("GameNotEnded")){
            if(this.isUpMove){
                System.out.println("Now up player's turn");
            }
            else {
                System.out.println("Now down player's turn");
            }
            String resMove;
            do {
                int lPos;
                System.out.println("Choose hero to perform attack or use ability");
                lPos = scanner.nextInt();

                System.out.println("You want to attack[0] or use ability[1]?");
                int ans = scanner.nextInt();
                if(ans == 0) {
                    int rPos;
                    System.out.println("Choose enemy to attack");
                    rPos = scanner.nextInt();
                    resMove = this.attack(lPos, rPos);
                }
                else {
                    if(this.isUpMove){
                        if(Archer.class == this.upSide.get(lPos).getClass()){
                            resMove = this.useAbility(lPos, -1);
                        }
                        else {
                            int rPos;
                            System.out.println("Choose hero to use ability on");
                            rPos = scanner.nextInt();
                            resMove = this.useAbility(lPos, rPos);
                        }
                    }
                    else{
                        if(Archer.class == this.downSide.get(lPos).getClass()){
                            resMove = this.useAbility(lPos, -1);
                        }
                        else {
                            int rPos;
                            System.out.println("Choose hero to use ability on");
                            rPos = scanner.nextInt();
                            resMove = this.useAbility(lPos, rPos);
                        }
                    }
                }
                System.out.println((resMove));
            }while (!resMove.isEmpty());

            if(this.isUpMove) {
                for (Hero h : this.upSide) {
                    h.tryMakeDead();
                }
            }
            else {
                for (Hero h : this.downSide) {
                    h.tryMakeDead();
                }
            }

            this.isUpMove = !this.isUpMove;
            res = this.isGameEnded();
            this.showField();
        }
        System.out.println(res);
    }
}
