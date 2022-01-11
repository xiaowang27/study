package chapter06;

import java.util.Arrays;

public class MiGong {
    public static void main(String[] args) {
        MiGong mg = new MiGong();

        // 1. 创建迷宫，使用二维数组，0表示无障碍物，1表示有障碍物，2表示墙
        int[][] map = new int[8][7];
        int[][] map2 = new int[8][7];

        // 2. 设置墙
        for (int i = 0; i < map.length; i = i + (map.length - 1)) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 2;
            }
        }
        for (int i = 1; i < map.length - 1; i++) {
            map[i][0] = 2;
            map[i][map.length - 2] = 2;
        }
        map[4][1] = 1;
        map[4][2] = 1;
        map[5][5] = 1;
        map[5][4] = 1;

        // 3. 输出地图
        System.out.println("迷宫地图");
        mg.print2DArray(map);
        System.out.println("-------------------------------------------------------------");

        // 6.1 克隆地图
        mg.cloneMap(map, map2);


        // 4. 开始走迷宫
        boolean map1 = mg.findMap(map, 1, 1);
        if (map1) {
            System.out.print("可以走出,路线如下：");
        } else {
            System.out.println("不能走出");
        }

        // 5. 输出路线图
        mg.print2DArray(map);
        System.out.println("-------------------------------------------------------------");

        // 6. 使用优化的走法
        mg.findMap2(map2, 1, 1);
        System.out.println("优化后的路线图");
        mg.print2DArray(map2);

    }


    /**
     * 1. map代表迷宫
     * 2. i、j就是老鼠的位置，初始化为(1,1)
     * 3. map各数字的意思：0 表示可走，1 表示障碍物，2 表示墙，3 表示走过但走不通，4表示可以走
     * 4. 但map[6][5]=2，说明找到出口，表示结束
     *
     * @param map 表示迷宫
     * @param i   老鼠所处的横坐标
     * @param j   老鼠所在的竖坐标
     * @return 该位置是否能到达
     */
    public boolean findMap(int[][] map, int i, int j) {
        if (map[6][5] == 4) {
            return true;
        } else {
            if (map[i][j] == 0) {   // 当前位置可以走且没走过
                map[i][j] = 4;
                if (findMap(map, i, j + 1)) { // 判断右边是否可以走通
                    map[i][j] = 4;
                    return true;
                } else if (findMap(map, i + 1, j)) { // 判断下边是否可以走通
                    map[i][j] = 4;
                    return true;
                } else if (findMap(map, i, j - 1)) {   // 判断右边是否可以走通
                    map[i][j] = 4;
                    return true;
                } else if (findMap(map, i - 1, j)) {
                    map[i - 1][j] = 4;
                    return true;
                }
            } else {  // =1 表示障碍物；=2 表示墙；=3 表示走不通
                return false;
            }
        }
        return false;
    }

    /**
     * 输出二维数组
     *
     * @param map 目标数组
     */
    public void print2DArray(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.printf(map[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 优化，找最短路径
     *
     * @param map 表示迷宫
     * @param i   老鼠所处的横坐标
     * @param j   老鼠所在的竖坐标
     * @return 该位置是否能到达
     */
    public boolean findMap2(int[][] map, int i, int j) {
        if (map[6][5] == 4) {
            return true;
        } else {
            if (map[i][j] == 0) {   // 当前位置可以走且没走过
                map[i][j] = 4;
                if (findMap2(map, i + 1, j + 1)) {  // 判断右下是否可以走
                    map[i][j] = 4;
                    return true;
                } else if (findMap2(map, i + 1, j)) { // 判断下边是否可以走通
                    map[i][j] = 4;
                    return true;
                } else if (findMap2(map, i, j - 1)) {   // 判断左边边是否可以走通
                    map[i][j] = 4;
                    return true;
                } else if (findMap2(map, i, j + 1)) { // 判断右边是否可以走通
                    map[i][j] = 4;
                    return true;
                } else if (findMap2(map, i - 1, j)) {    // 判断上边是否可以走通
                    map[i - 1][j] = 4;
                    return true;
                }
            } else if (map[i][j] == 4) {
                return findMap2(map, i, j - 1);
            } else {  // =1 表示障碍物；=2 表示墙；=3 表示走不通
                return false;
            }
        }
        return false;
    }

    /**
     * 对二维数组进行科隆1
     *
     * @param map1 被克隆的数组
     * @param map2 克隆数组
     */
    public void cloneMap(int[][] map1, int[][] map2) {
        for (int i = 0; i < map1.length; i++) {
            for (int j = 0; j < map1[0].length; j++) {
                map2[i][j] = map1[i][j];
            }
        }
    }
}
