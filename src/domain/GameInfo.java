package domain;

import java.io.Serial;
import java.io.Serializable;

public class GameInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 5544981119935263973L;

    private int[][] data;
    private int x = 0;
    private int y = 0;
    private String path;
    private int step;

    public GameInfo(){

    }

    public GameInfo(int[][] data, int x, int y, String path, int step) {
        this.data = data;
        this.x = x;
        this.y = y;
        this.path = path;
        this.step = step;
    }




}
