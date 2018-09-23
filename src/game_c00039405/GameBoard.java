package game_c00039405;

import java.util.ArrayList;
import java.util.Arrays;

public class GameBoard {

    private int[] location;
    private ArrayList<int[]> gameBoard;
    private ArrayList<Object> objectArray;//if this object type does not work i will have to replace it with a different type

    public GameBoard(int[] location, ArrayList<int[]> gameBoard, ArrayList<Object> objectArray) {
        this.location = location;
        this.gameBoard = gameBoard;
        this.objectArray = objectArray;
    }

    public int[] getLocation() {
        return location;
    }

    public void setGameBoard(int x, int y, Object object) {
        setLocation(x, y);
        if (gameBoard.indexOf(location) == -1) {//new location
            gameBoard.add(location);
            objectArray.add(object);
        } else {//not new location
            objectArray.set(gameBoard.indexOf(location), object);

        }
    }

    public Object clearGameBoardOfObject(int indexNumber, Nothing nothing) {
        return objectArray.set(indexNumber, nothing);
    }

    public Object getGameBoardObjects(int indexNumber) {
        return objectArray.get(indexNumber);
    }

    public void setLocation(int x, int y) {
        location[0] = x;
        location[1] = y;
    }

    public int getIndexForObjectsArray() {
        return gameBoard.indexOf(location);
    }

    public boolean hasIndexForObjectsArray(int[] xAndY) {
        return gameBoard.contains(xAndY);
    }

    @Override
    public String toString() {
        int ind = getIndexForObjectsArray();
        return ("There is " + getGameBoardObjects(ind).toString() + " at this location, " + Arrays.toString(getLocation()));
    }
}
