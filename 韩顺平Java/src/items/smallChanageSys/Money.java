package items.smallChanageSys;

import javafx.scene.input.DataFormat;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Money {
    private int money;
    private String log = "";

    Date date = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Money(int money) {
        this.money = money;
        this.log = "\n初始金额" + money + "\t初始时间" + simpleDateFormat.format(date) + "\t余额" + this.money + "\n";
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log += log;
    }

    public void add(int money) {
        this.money += money;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String log = "\n收益金额" + money + "\t收益时间" + simpleDateFormat.format(date) + "\t余额" + this.money + "\n";
        setLog(log);
    }

    public void shop(int money) {
        if (this.money < money) {
            System.out.println("余额仅剩" + this.money + "请量力消费...");
        } else {
            this.money -= money;
            String log = "\n消费金额" + money + "\t消费时间" + simpleDateFormat.format(date) + "\t余额" + this.money + "\n";
            setLog(log);
        }
    }

    public String logPrint() {
        return this.log;
    }
}
