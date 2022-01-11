package chapter03;

public class Program3_8 {
    /**
     * 程序清单3-8 P87
     * 该程序展示如何存储二维数组
     *
     * @param args
     */
    public static void main(String[] args) {
        final double STARTRATE = 10;
        final int NRATES = 6;
        final int NYEARS = 10;

        // 将利率设置到10%~15%
        double[] interestRate = new double[NRATES];
        for (int j = 0; j < interestRate.length; j++) {
            interestRate[j] = (STARTRATE + j) / 100.0;
        }

        // 设置初始余额为1000
        double[][] balances = new double[NYEARS][NRATES];
        for (int j = 0; j < interestRate.length; j++) {
            balances[0][j] = 1000;
        }

        // 计算未来年份的间隔时间
        for (int i = 1; i < balances.length; i++) {
            for (int j = 0; j < balances[i].length; j++) {
                // 从前一行得到去年的余额
                double oldBalance = balances[i - 1][j];

                // 计算利息
                double interest = oldBalance * interestRate[j];

                // 计算今年的余额
                balances[i][j] = oldBalance + interest;
            }
        }

        // 打印一行利息
        for (int j = 0; j < interestRate.length; j++)
            System.out.printf("%9.0f%%", 100 * interestRate[j]);

        System.out.println();

        // 打印余额表
        for (double[] row : balances) {
            // 打印表行
            for (double b : row) {
                System.out.printf("%10.2f", b);
            }
        }

    }
}
