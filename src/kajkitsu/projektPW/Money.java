package kajkitsu.projektPW;

public class Money {
    private long money;
    boolean isMoneyUpdating = false;

    public Money(int money) {
        this.money = money;
    }

    public boolean ChangeMoney(long change) {
        if(money+change>=0) {
            money+=change;
            return true;
        } else return false;

    }

    public static String getStringFromLong(long money) {
        if (money >= 10000000000000000L) return money / 1000000000000000L + "B";
        if (money >= 10000000000000L) return money / 1000000000000L + "b";
        if (money >= 10000000000L) return money / 1000000000L + "M";
        if (money >= 10000000) return money / 1000000 + "m";
        if (money >= 10000) return money / 1000 + "k";
        return money + "";
    }

    public long GetMoney() {
        return money;
    }

    public boolean isMoneyUpdating() {
        return isMoneyUpdating;
    }

    public synchronized void setMoneyUpdating(boolean moneyUpdating) {
        isMoneyUpdating = moneyUpdating;
    }
}
