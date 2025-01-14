import heroes.Hero;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private boolean ifLeftMove;

    private List<Hero> leftSide;
    private List<Hero> rightSide;

    public Field(){
        this.ifLeftMove = true;

        this.leftSide = new ArrayList<Hero>();
        this.rightSide = new ArrayList<Hero>();
    }

    public void addHero(Hero hero, boolean isLeft){
        if(isLeft){
            this.leftSide.add(hero);
        }
        else{
            this.rightSide.add(hero);
        }
    }

    public void showField(){
        for(int i = 0; i < this.leftSide.size(); ++i){
            String line = "";
            for(int j = 0; j < this.leftSide.get(i).sprite.size(); ++j){
                line = this.leftSide.get(i).sprite.get(j) + this.rightSide.get(i).sprite.get(j);
                System.out.println(line);
            }
            System.out.printf("%d\t%d\n", this.leftSide.get(i).getHp(),
                    this.rightSide.get(i).getHp());
        }
    }

    protected void makeMove(int lPos, int rPos){
        if(this.ifLeftMove) {
            this.leftSide.get((lPos)).attack(this.rightSide.get(rPos));
        }
        else {
            this.rightSide.get((rPos)).attack(this.leftSide.get(lPos));
        }
    }

    public void startGame(){
        
    }
}
