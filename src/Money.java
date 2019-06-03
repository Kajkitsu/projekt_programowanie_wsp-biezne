public class Money {
    private int money;
    boolean isMoneyUpdating = false;

    public Money(int money) {
        this.money = money;
    }

    public boolean ChangeMoney(int change){
        if(money+change>=0) {
            money+=change;
            return true;
        }
        else return false;

    }

    public int GetMoney(){
        return money;
    }

    public boolean isMoneyUpdating() {
        return isMoneyUpdating;
    }

    public synchronized void setMoneyUpdating(boolean moneyUpdating) {
        isMoneyUpdating = moneyUpdating;
    }
}
