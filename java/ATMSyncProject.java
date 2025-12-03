class ATM {
    synchronized public void chekBalance(String name) {
        System.out.println(name + " Checking");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

        System.out.println("Balance");

    }

    synchronized public void withdraw(String name, int amount) {

        System.out.println(name + " Withdrawing ");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

        System.out.println(amount);

    }
}

class Customer extends Thread {
    ATM atm;
    String name;
    int amount;

    public Customer(String n, ATM a, int amt) {
        name = n;
        atm = a;
        amount = amt;
    }

    void useATM() {
        atm.chekBalance(name);
        atm.withdraw(name, amount);
    }

    public void run() {
        useATM();
    }

}

public class ATMSyncProject {

    public static void main(String[] args) {

        ATM atm = new ATM();

        Customer c1 = new Customer("John", atm, 100);
        Customer c2 = new Customer("Smith", atm, 200);

        c1.start();
        c2.start();
    }

}
