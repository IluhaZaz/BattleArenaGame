import heroes.Archer;
import heroes.Hero;
import utils.safeNextInt;

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

    public void downPlayerStarts(){
        this.isUpMove = false;
    }

    public void addHero(Hero hero, boolean isUpper){
        if(isUpper){
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
            if(this.upSide.size() <= attackPos || attackPos < 0){
                return "Your hero invalid position";
            }
            if(this.downSide.size() <= defPos || defPos < 0){
                return "Enemy invalid position";
            }
            return this.upSide.get(attackPos).attack(this.downSide.get(defPos));
        }
        else {
            if(this.downSide.size() <= attackPos || attackPos < 0){
                return "Your hero invalid position";
            }
            if(this.upSide.size() <= defPos || defPos < 0){
                return "Enemy invalid position";
            }
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

    public void refreshHeroes(){
        List<Hero> toRefresh;
        if(this.isUpMove) {
            toRefresh = this.upSide;
        }
        else {
            toRefresh = this.downSide;
        }
        for (Hero h : toRefresh) {
            h.tryMakeDead();
            h.waitCooldown();
        }
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
                resMove = "RandomValue";
                int lPos;

                System.out.println("Choose hero to perform attack or use ability");
                lPos = safeNextInt.nextInt(scanner);
                if(lPos == -1){
                    continue;
                }

                System.out.println("You want to attack[0] or use ability[1]?");
                int ans = safeNextInt.nextInt(scanner);
                if(ans == -1){
                    continue;
                }

                if(ans != 0 && ans != 1){
                    System.out.println("Invalid command");
                    continue;
                }
                if(ans == 0) {
                    int rPos;
                    System.out.println("Choose enemy to attack");
                    rPos = safeNextInt.nextInt(scanner);
                    if(rPos == -1){
                        continue;
                    }

                    resMove = this.attack(lPos, rPos);
                }
                else {
                    if(this.isUpMove){
                        if(this.upSide.size() <= lPos || lPos < 0){
                            System.out.println("Your hero invalid position");
                            continue;
                        }
                        if(Archer.class == this.upSide.get(lPos).getClass()){
                            resMove = this.useAbility(lPos, -1);
                        }
                        else {
                            int rPos;
                            System.out.println("Choose hero to use ability on");
                            rPos = safeNextInt.nextInt(scanner);
                            if(rPos == -1){
                                continue;
                            };

                            if(this.downSide.size() <= rPos || rPos < 0){
                                System.out.println("Invalid position");
                                continue;
                            }

                            resMove = this.useAbility(lPos, rPos);
                        }
                    }
                    else{
                        if(this.downSide.size() <= lPos || lPos < 0){
                            System.out.println("Your hero invalid position");
                            continue;
                        }
                        if(Archer.class == this.downSide.get(lPos).getClass()){
                            resMove = this.useAbility(lPos, -1);
                        }
                        else {
                            int rPos;
                            System.out.println("Choose hero to use ability on");
                            rPos = safeNextInt.nextInt(scanner);
                            if(rPos == -1){
                                continue;
                            };

                            if(this.upSide.size() <= rPos || rPos < 0){
                                System.out.println("Invalid position");
                                continue;
                            }

                            resMove = this.useAbility(lPos, rPos);
                        }
                    }
                }
                System.out.println((resMove));
            }while (!resMove.isEmpty());

            this.refreshHeroes();

            this.isUpMove = !this.isUpMove;
            res = this.isGameEnded();
            this.showField();
        }
        System.out.println(res);
    }
}
