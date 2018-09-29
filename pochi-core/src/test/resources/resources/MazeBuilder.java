import java.util.Random;

/**
 * 棒倒し法により迷路を作成する．
 *
 * @author Haruaki Tamada
 */
public class MazeBuilder{
    void run(){
        String[][] table = new String[21][41];

        init(table);
        buildMaze(table);

        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                System.out.print(table[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 迷路を作成する．
     * 1. 一番左にある列の柱は4方向に柱を倒し，壁を作る．
     * 2. その他の柱は上下右方向にのみ柱を倒し，壁を作る．
     *    * 左方向には倒さない．
     */
    void buildMaze(String[][] table){
        Random random = new Random();
        // 1列目: x = 2
        for(Integer j = 2; j < table[0].length - 1; j += 2){
            Integer direction = random.nextInt(4);

            makeWall(table, direction, 2, j);
        }
        for(Integer i = 4; i < table.length - 1; i += 2){
            for(Integer j = 2; j < table[i].length - 1; j += 2){
                Integer direction = random.nextInt(3);
                makeWall(table, direction, i, j);
            }
        }
    }

    void makeWall(String[][] table, Integer direction, Integer x, Integer y){
        switch(direction){
        case 0:
            table[x    ][y - 1] = "X";
            break;
        case 1:
            table[x + 1][y    ] = "X";
            break;
        case 2:
            table[x    ][y + 1] = "X";
            break;
        case 3:
            table[x - 1][y    ] = "X";
            break;
        }
    }

    /**
     * 迷路を初期化するメソッド．
     * - 端っこを壁にする．
     * - １マスを空けて柱を立てる．
     * - スタート地点を設定する．
     * - ゴール地点を設定する．
     */
    void init(String[][] table){
        for(Integer i = 0; i < table.length; i++){
            for(Integer j = 0; j < table[i].length; j++){
                if(i == 0 || j == 0){
                    table[i][j] = "X";
                }
                else if(i == table.length - 1 || j == table[0].length - 1){
                    table[i][j] = "X";
                }
                else if(i % 2 == 0 && j % 2 == 0){
                    table[i][j] = "X";
                }
                else{
                    table[i][j] = " ";
                }
            }
        }
        // start
        table[0][1] = " ";
        // goal
        table[table.length - 1][table[0].length - 2] = " ";
    }

    public static void main(String[] args){
        MazeBuilder builder = new MazeBuilder();
        builder.run();
    }
}
