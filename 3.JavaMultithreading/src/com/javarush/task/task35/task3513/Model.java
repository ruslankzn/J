package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;
    int maxTile;

    private Stack<Integer> previousScores;
    private Stack<Tile[][]> previousStates;
    private boolean isSaveNeeded = true;


    public Model() {
        resetGameTiles();

        this.previousScores = new Stack<Integer>();
        this.previousStates = new Stack<Tile[][]>();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> result = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value == 0) result.add(gameTiles[i][j]);
            }
        }
        return result;
    }

    void addTile() {
        List<Tile> list = getEmptyTiles();
        if (list.size() != 0) {
            list.get((int) (Math.random() * list.size())).value = Math.random()< 0.9 ? 2 : 4;
        }
    }

    void resetGameTiles(){
        this.gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for(int i = 0; i<FIELD_WIDTH; i ++){
            for(int j = 0; j < FIELD_WIDTH; j++){
                this.gameTiles[i][j]= new Tile();
            }
        }
        addTile();
        addTile();
    }

    private boolean compressTiles(Tile[] tiles){
//        boolean isChanged = false;
//        Tile temp;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (tiles[j].getValue() == 0 && tiles[j + 1].getValue() != 0) {
//                    temp = tiles[j];
//                    tiles[j] = tiles[j + 1];
//                    tiles[j + 1] = temp;
//                    isChanged = true;
//                }
//            }
//        }
//        return isChanged;
        boolean x = false;
        for (int j = tiles.length - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                if (tiles[i].isEmpty()&& !tiles[i+1].isEmpty()) {
                    tiles[i].value = tiles[i + 1].value;
                    tiles[i + 1].value = 0;
                    //tiles[i+1] = new Tile();
                    x = true;
                }
            }
        }
        //mergeTiles(tiles);
        return x;
    }

    private boolean mergeTiles(Tile[] tiles){
//        boolean isChanged = false;
//        for (int j = 0; j < 3; j++) {
//            if (tiles[j].getValue() != 0 && tiles[j].getValue() == tiles[j + 1].getValue()) {
//                tiles[j].setValue(tiles[j].getValue() * 2);
//                tiles[j + 1].setValue(0);
//                if (tiles[j].getValue() > maxTile) maxTile = tiles[j].getValue();
//                score += tiles[j].getValue();
//                isChanged = true;
//            }
//        }
//
//        if (isChanged) {
//            Tile temp;
//            for (int j = 0; j < 3; j++) {
//                if (tiles[j].getValue() == 0 && tiles[j + 1].getValue() != 0) {
//                    temp = tiles[j];
//                    tiles[j] = tiles[j + 1];
//                    tiles[j + 1] = temp;
//                }
//            }
//        }
//
//        return isChanged;
        boolean x = false;
        for (int i = 1; i < tiles.length; i++) {
            if (tiles[i - 1].value == tiles[i].value && !tiles[i-1].isEmpty() && !tiles[i].isEmpty()) {
                tiles[i - 1].value *= 2;
                tiles[i].value = 0;
                //tiles[i] = new Tile();
                if (tiles[i - 1].value > maxTile) {
                    maxTile = tiles[i - 1].value;
                }
                score += tiles[i - 1].value;
                x = true;

                /*i+=1;
                for (; i <tiles.length-1 ; i++) {
                    tiles[i].value = tiles[i+1].value;
                }
                tiles[tiles.length-1].value = 0;*/
            }
        }
        //boolean y = compressTiles(tiles);
        return x;
    }

    public void left(){
        if (isSaveNeeded) saveState(this.gameTiles);
        boolean isChanged = false;
        for(int i = 0; i< FIELD_WIDTH; i++){
            if(mergeTiles(gameTiles[i]) | compressTiles(gameTiles[i])){
                isChanged = true;
            }
        }
        if(isChanged){
            addTile();
            isSaveNeeded = true;
        }
    }

    private void rotate(){
        int len = FIELD_WIDTH;
        for (int k = 0; k < len / 2; k++) // border -> center
        {
            for (int j = k; j < len - 1 - k; j++) // left -> right
            {
                Tile tmp = gameTiles[k][j];
                gameTiles[k][j] = gameTiles[j][len - 1 - k];
                gameTiles[j][len - 1 - k] = gameTiles[len - 1 - k][len - 1 - j];
                gameTiles[len - 1 - k][len - 1 - j] = gameTiles[len - 1 - j][k];
                gameTiles[len - 1 - j][k] = tmp;
            }
        }
    }

    public void up(){
        saveState(this.gameTiles);
        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }

    public void right(){
        saveState(this.gameTiles);
        rotate();
        rotate();
        left();
        rotate();
        rotate();
    }

    public void down(){
        saveState(this.gameTiles);
        rotate();rotate();rotate();
        left();
        rotate();
    }

    public boolean canMove(){
        if (!getEmptyTiles().isEmpty())
            return true;
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j - 1].value)
                    return true;
            }
        }
        for (int j = 0; j < gameTiles.length; j++) {
            for (int i = 1; i < gameTiles.length; i++) {
                if (gameTiles[i][j].value == gameTiles[i - 1][j].value)
                    return true;
            }
        }
        return false;
    }

    private void saveState(Tile[][] field){
        Tile[][] fieldToSave = new Tile[field.length][field[0].length];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                fieldToSave[i][j] = new Tile(field[i][j].getValue());
            }
        }
        previousStates.push(fieldToSave);
        int scoreToSave = score;
        previousScores.push(scoreToSave);
        isSaveNeeded = false;
    }

    public void  rollback(){
        if(!previousStates.isEmpty() && !previousScores.isEmpty()){
            this.score = previousScores.pop();
            this.gameTiles = previousStates.pop();
        }
    }

    public void randomMove(){
        switch (((int)(Math.random() * 100))%4){
            case 0:
                left();
                break;
            case 1:
                up();
                break;
            case 2:
                right();
                break;
            case 3:
                down();
                break;
        }
    }

    private boolean hasBoardChanged() {
        boolean result = false;
        int sumNow = 0;
        int sumPrevious = 0;
        Tile[][] tmp = previousStates.peek();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                sumNow += gameTiles[i][j].getValue();
                sumPrevious += tmp[i][j].getValue();
            }
        }
        return sumNow != sumPrevious;
    }

    private MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency moveEfficiency;
        move.move();
        if (hasBoardChanged()) moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        else moveEfficiency = new MoveEfficiency(-1, 0, move);
        rollback();

        return moveEfficiency;
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());
        queue.add(getMoveEfficiency(this::left));
        queue.add(getMoveEfficiency(this::right));
        queue.add(getMoveEfficiency(this::up));
        queue.add(getMoveEfficiency(this::down));
        Move move=queue.peek().getMove();
        move.move();
    }
}
