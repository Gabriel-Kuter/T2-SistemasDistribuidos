package model;

/*
 * Define o funcionamento dos blocos da malha rodoviaria
 */
public abstract class Block {

    protected boolean isExitBlock = false;
    protected boolean isEntryCar;
    protected Block nextBlock;
    protected int directionFlow;
    protected int rowIndex;
    protected int colIndex;

    public Block(int directionFlow, int x, int y) {
        this.directionFlow = directionFlow;
        this.rowIndex = x;
        this.colIndex = y;
        this.nextBlock = null;
    }

    public int getDirectionFlow() {
        return directionFlow;
    }

    public void setDirectionFlow(int direction) {
        this.directionFlow = direction;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int xPos) {
        this.rowIndex = xPos;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int yPos) {
        this.colIndex = yPos;
    }

    public boolean isCross() {
        if (this.directionFlow > 4) {
            return true;
        }
        return false;
    }

    public boolean isExit() {
        return isExitBlock;
    }

    public void setIsExit(boolean isExitBlock) {
        this.isExitBlock = isExitBlock;
    }

    public boolean getEntryCar() {
        return isEntryCar;
    }

    public void setEntryCar(boolean isEntryCar) {
        this.isEntryCar = isEntryCar;
    }

    public Block getNextBlock() {
        return this.nextBlock;
    }

    public void setNextBlock(Block[][] road) {

        switch (this.directionFlow) {
            case 1:
                if (this.rowIndex - 1 >= 0) {
                    this.nextBlock = road[this.rowIndex - 1][this.colIndex];
                }
                break;
            case 2:
                if (this.colIndex + 1 < road[0].length) {
                    this.nextBlock = road[this.rowIndex][this.colIndex + 1];
                }
                break;
            case 3:
                if (this.rowIndex + 1 < road.length) {
                    this.nextBlock = road[this.rowIndex + 1][this.colIndex];
                }
                break;
            case 4:
                if (this.colIndex - 1 >= 0) {
                    this.nextBlock = road[this.rowIndex][this.colIndex - 1];
                }
        }
    }
}