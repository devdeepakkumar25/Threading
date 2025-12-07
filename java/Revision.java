// class ATM {

//     synchronized public void checkBalance(String name) {
//         System.out.println(name + " Checking Balance");
//         try {
//             Thread.sleep(1000);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//         System.out.println("Balance");
//     }

//     synchronized public void withdraw(String name, int amount) {

//         System.out.println(name + " Withdrawing ");
//         try {
//             Thread.sleep(1000);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         System.out.println(amount);
//     }
// }

// class Customer extends Thread {
//     ATM atm;
//     String name;
//     int amount;

//     public Customer(String n, ATM a, int amt) {
//         name = n;
//         atm = a;
//         amount = amt;
//     }

//     void useATM() {
//         atm.checkBalance(name);
//         atm.withdraw(name, amount);
//     }

//     public void run() {
//         useATM();
//     }
// }

// public class Main {
//     public static void main(String[] args) {

//         ATM atm = new ATM();

//         Customer c1 = new Customer("John", atm, 100);
//         Customer c2 = new Customer("Smith", atm, 200);

//         c1.start();
//         c2.start();

//     }
// }

// class MyData {
// synchronized public void display(String str) {
// for (int i = 0; i < str.length(); i++) {
// // synchronized (this) {

// // }
// System.out.print(str.charAt(i));
// try {
// Thread.sleep(100);
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
// }
// }

// class MyThread1 extends Thread {
// MyData d;

// public MyThread1(MyData d) {
// this.d = d;
// }

// public void run() {
// d.display(" Hello World");
// }
// }

// class MyThread2 extends Thread {
// MyData d;

// public MyThread2(MyData d) {
// this.d = d;
// }

// public void run() {

// d.display(" Welcome all");
// }
// }

// public class Main {
// public static void main(String[] args) {

// MyData d = new MyData();
// MyThread1 th1 = new MyThread1(d);

// MyThread2 th2 = new MyThread2(d);

// th1.start();

// th2.start();

// }
// }

// class MyThread extends Thread {
// public MyThread(String name) {
// super(name);
// setPriority(Thread.MAX_PRIORITY);
// }

// public void run() {
// int count = 1;
// while (true) {
// System.out.println(" Run :" + count++);
// try {
// Thread.sleep(10);
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// }

// }
// }

// public class Main {
// public static void main(String[] args) {

// MyThread th = new MyThread("Thread1");
// System.out.println("ID" + th.getId());
// System.out.println(Thread.currentThread().getId());
// System.out.println(Thread.currentThread().getName());
// System.out.println(th.getName());
// System.out.println("State: " + th.getState());
// System.out.println("ISAlive " + th.isAlive());
// th.start();
// int i = 0;
// th.interrupt();

// while (true) {
// System.out.println(i + "Main");
// i++;
// try {
// Thread.sleep(100);
// } catch (InterruptedException e) {
// e.printStackTrace();
// }

// }

// }
// }

// class MyThread extends Thread {
// public void run() {
// int i = 1;
// while (true) {
// System.out.println(i + " Hello");
// i++;
// }
// }
// }

// class MyRunnable implements Runnable {
// public void run() {
// int i = 1;
// while (true) {
// System.out.println(i + " Hello");
// i++;
// }
// }
// }

// public class Main {
// public static void main(String[] args) {
// // MyThread th = new MyThread();
// // th.start();
// MyRunnable tr = new MyRunnable();
// Thread th = new Thread(tr);
// th.start();

// int i = 0;

// while (true) {
// System.out.println(i + " World");
// i++;

// }

// }
// }

public class Revision {

}
