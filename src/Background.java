import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Background class represents all the non-movable things in the world
 * it contains and draw the grass and water tiles
 * This is actually a "helper" class to make the World be more clear and concise
 * Furthermore, to make it easier to modify background
 * */

public class Background {
    // GRASS_LINE_NUM is the number of grass tile line
    private static final int GRASS_LINE_NUM = 2;
    // Tile_PER_LINE_NUM is the number of tile per line
    private static final int Tile_PER_LINE_NUM = (int) (App.SCREEN_WIDTH/Tile.TILE_SIZE) + 1;
    // Grass_Y_Position is an array that contains the Y coordinator of grass horizontal line
    private static final float[] Grass_Y_Position = new float[]{384, 672};
    // Water_Y_Position_Start is the starting Y coordinator of water horizontal line (include)
    private static final float Water_Y_Position_Start = 336;
    // Water_Y_Position_End is the ending Y coordinator of water horizontal line (exclude)
    private static final float Water_Y_Position_End = 48;
    // Water_Line_Num is the number of horizontal water line
    private static final int Water_Line_Num = (int) (Water_Y_Position_Start - Water_Y_Position_End)/((int) Tile.TILE_SIZE);
    // Grass_Offset is the starting point of x coordinator of grass tile
    private static final float Grass_Offset = 0;
    // WaterOffset is the starting point of x coordinator of water tile
    private static final float Water_Offset = 0;


    // the obstacle line Y coordinator array
    public static final float[] OBSTACLE_Y_ARRAY = new float[]{432, 480, 528, 576, 624};
    // the obstacle lines Offset array
    public static final float[] OBSTACLE_OFFSET_ARRAY = new float[]{48, 0, 64, 128, 250};
    // the obstacle separation array
    public static final float[] OBSTACLE_SEPARATION_ARRAY = new float[]{6.5f*Tile.TILE_SIZE,
            5*Tile.TILE_SIZE, 12*Tile.TILE_SIZE, 5*Tile.TILE_SIZE, 6.5f*Tile.TILE_SIZE};
    // The number of obstacle line in total
    public static final int OBSTACLE_LINE_TOTAL_NUMBER = 5;
    // The Moving direction of bus line
    public static final char[] LEFT_AND_RIGHT_ARRAY = new char[]{Obstacle.LEFT, Obstacle.RIGHT};

    /*Define Sprites*/

    // grasses is a 2-D array of grass tiles at different y coordinator location
    private Tile[][] grasses = new Tile[GRASS_LINE_NUM][Tile_PER_LINE_NUM];
    // waters is a 2-D array of water tiles at different y coordinator location
    private Tile[][] waters  = new Tile[Water_Line_Num][Tile_PER_LINE_NUM];
    // obstacles is a 2-D array of obstacle lines at given y coordinator location
    private Obstacle[][] obstacles = new Obstacle[OBSTACLE_LINE_TOTAL_NUMBER][];





    // The constructor of Background
    // It initialize the Image grass and water
    /**
     * @param grassImgSrc -> the Image source of Image grass
     * @param waterImgSrc -> the Image source of Image water
     * */
    public Background(String grassImgSrc, String waterImgSrc, String obstacleImgSrc) throws SlickException{

        // initialize grasses tiles
        for (int i = 0; i<GRASS_LINE_NUM; ++i){
            for (int j = 0; j < Tile_PER_LINE_NUM; j++){
                grasses[i][j] = new Tile(grassImgSrc, Grass_Offset+Tile.TILE_SIZE/2+Tile.TILE_SIZE*j,
                        Grass_Y_Position[i]);
            }
        }

        // initialize waters tiles
        for (int i = 0; i<Water_Line_Num; ++i){
            for (int j = 0; j < Tile_PER_LINE_NUM; j++){
                waters[i][j] = new Tile(waterImgSrc, Water_Offset+Tile.TILE_SIZE/2+Tile.TILE_SIZE*j,
                        Water_Y_Position_Start-i*Tile.TILE_SIZE);
            }
        }

        Image obstacle = new Image(obstacleImgSrc);

        // initialize obstacles array
        for (int i = 0; i<OBSTACLE_LINE_TOTAL_NUMBER;i++){
            int numOfObstacles =
                    (int) (
                            (App.SCREEN_WIDTH - (OBSTACLE_OFFSET_ARRAY[i]- obstacle.getWidth()/2)+ OBSTACLE_SEPARATION_ARRAY[i]) /
                    OBSTACLE_SEPARATION_ARRAY[i]
                    );
            obstacles[i] = new Obstacle[numOfObstacles];
            for (int j=0; j<numOfObstacles; j++){
                obstacles[i][j] = new Obstacle(obstacleImgSrc, OBSTACLE_OFFSET_ARRAY[i]+j*OBSTACLE_SEPARATION_ARRAY[i],
                        OBSTACLE_Y_ARRAY[i]);
            }
        }
    }

    /**
     * This update function is used to update the position of each line of obstacles
     * @param delta -> it is the milliseconds since last frame passes
     * */
    public void update(int delta){
        // draw the obstacle lines

        for (int i=0; i < OBSTACLE_LINE_TOTAL_NUMBER; i++){
            // obstacle.update() would draw the line of obstacle on a given horizontal line
            for(Obstacle obstacle : obstacles[i]){
                obstacle.update(delta, LEFT_AND_RIGHT_ARRAY[i%2]);
            }
        }
    }

    /**
     * The function to draw all the background tiles horizontally on the given y coordinator
     * */
    public void render(){
        // draw the grasses tiles
        for (Tile[] grassLine : grasses){
            for (Tile grass: grassLine){
                grass.render();
            }
        }

        // draw the waters tiles
        for (Tile[] waterLine : waters){
            for (Tile water: waterLine){
                water.render();
            }
        }

        // draw the obstacle lines
        for (int i=0; i < OBSTACLE_LINE_TOTAL_NUMBER; i++){
            // obstacles.render() would draw the line of obstacle on a given horizontal line
            for(Obstacle obstacle : obstacles[i]){
                obstacle.render();
            }
        }
    }

    // return the array of water tile
    public Tile[][] getWaters(){
        return waters;
    }

    // return the array of obstacle
    public Obstacle[][] getObstacles(){
        return obstacles;
    }

}
