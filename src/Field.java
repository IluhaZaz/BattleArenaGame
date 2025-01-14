import heroes.Hero;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Field {
    private boolean ifLeftMove;

    private List<Hero> upSide;
    private List<Hero> downSide;

    public Field(){
        this.ifLeftMove = true;

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
            for (int j = 0; j < this.upSide.size(); ++j) {
                line = line.concat(this.upSide.get(j).sprite.get(i));
            }
            System.out.println(line);
        }
        for(Hero hero: this.upSide){
            System.out.print(hero.getHp() + "\t\t");
        }
        System.out.print("\n");

        for(int i = 0; i < this.downSide.getFirst().sprite.size(); ++i){
            String line = "";
            for (int j = 0; j < this.downSide.size(); ++j) {
                line = line.concat(this.downSide.get(j).sprite.get(i));
            }
            System.out.println(line);
        }
        for(Hero hero: this.downSide){
            System.out.print(hero.getHp() + "\t\t");
        }
        System.out.print("\n");
    }

    protected void makeMove(int attackPos, int defPos){
        if(this.ifLeftMove) {
            this.upSide.get((attackPos)).attack(this.downSide.get(defPos));
        }
        else {
            this.downSide.get((attackPos)).attack(this.upSide.get(defPos));
        }
    }

    protected String isGameEnded(){
        boolean leftAlive = false;
        boolean rightAlive = false;

        for(int i = 0; i < this.upSide.size(); ++i){
            if(this.upSide.get(i).getHp() > 0){
                leftAlive = true;
            }
            if(this.downSide.get(i).getHp() > 0){
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
            if(this.ifLeftMove){
                System.out.println("Now up player's turn");
            }
            else{
                System.out.println("Now down player's turn");
            }
            System.out.println("Choose hero to perform attack");
            int lPos = scanner.nextInt();
            System.out.println("Choose enemy to attack");
            int rPos = scanner.nextInt();
            this.makeMove(lPos, rPos);

            this.ifLeftMove = !this.ifLeftMove;
            res = this.isGameEnded();
            this.showField();
        }
        System.out.println(res);
    }
}
