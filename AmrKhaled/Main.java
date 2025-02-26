// amr khaled 20230271



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Base64;
import java.io.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static  ArrayList<BankUser> Accounts = new ArrayList<BankUser>();
    static  BankUser loginAccount;


    static void saveAccounts() {


        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("accounts.text"))) {
            out.writeObject(Accounts);

        } catch (Exception e) {

        }
    }
    static void loadAccounts() {


        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("accounts.text"))) {
            Accounts = (ArrayList<BankUser>) in.readObject();

        } catch (Exception e) {

        }
    }

    static     void register(){
        System.out.println("please enter user name : ");
        String username = new Scanner(System.in).next();
        for(BankUser user : Accounts){
            if(Objects.equals(user.name, username)){
                System.out.println("username is already in use try login  ");
                return;
            }

//            else {
//
//                System.out.println("please enter  password : ");
//                String password = new Scanner(System.in).next();
//
//
//                  BankUser newBanckAccount=new BankUser(username,password);
//                  Accounts.add(newBanckAccount);
//                  System.out.println("Account registered successfully");
//                return;
//
//            }
        }


        System.out.println("please enter  password : ");
        String password = new Scanner(System.in).next();


        BankUser newBanckAccount=new BankUser(username,password);
        Accounts.add(newBanckAccount);
        System.out.println("Account registered successfully");
        saveAccounts();

    }



    static void login() {
        System.out.println("please enter user name : ");
        String username = scanner.next();
        boolean userFound = false;



        if (username.equals(Admin.AdminName) ) {
            System.out.println("please enter password : ");
            String password = scanner.next();
            if (password.equals(Admin.AdminPass)) {
                while (true) {
                    System.out.println("\n Admin Dashboard ");
                    System.out.println("1. View All Users");
                    System.out.println("2. Lock an Account");
                    System.out.println("3. Unlock an Account");
                    System.out.println("4. Reset User Password");

                    System.out.println("5. Exit");
                    System.out.print(" Choose an option: ");

                    int choice = Main.getValidIntegerInput();
                    switch (choice) {
                        case 1:
                            Admin.showAllUsers();
                            break;
                        case 2:
                            System.out.print(" Enter username to lock an account: ");
                            String lockUser = scanner.next();
                            Admin.lockAccount(lockUser);
                            saveAccounts();
                            break;
                        case 3:
                            System.out.print(" Enter username to unlock an account: ");
                            String unlockUser = scanner.next();
                            Admin.unlockAccount(unlockUser);
                            saveAccounts();
                            break;
                        case 4:
                            System.out.print(" Enter username to reset password: ");
                            String resetUser = scanner.next();
                            System.out.print(" Enter new password: ");
                            String newPassword = scanner.next();
                            Admin.resetUserPassword(resetUser, newPassword);
                            saveAccounts();
                            break;

                        case 5:
                            System.out.println("Exit...");
                            return;
                        default:
                            System.out.println("Invalid choice! Try again.");
                    }

                }
            }
        }

        for (BankUser user : Accounts) {
            if (Objects.equals(user.name, username)) {
                if(user.isLock){
                    System.out.println("this user has locked by admin  ");
                    return;
                }
                userFound = true;
                System.out.println("please enter password : ");
                String password = scanner.next();

                if (Objects.equals(user.getPass(), password)) {
                    loginAccount = user;
                    System.out.println("login successfully");
                    System.out.println("\n --- your unique Id is  " + loginAccount.getID()+"---");

                    ShowMenu();
                    return;
                } else {
                    System.out.println("wrong password");
                    return;
                }
            }
        }

        if (!userFound) {
            System.out.println("There is no such user with username: " + username);
        }
    }


    static    void ShowMenu(){
        while (true) {
            System.out.println("\n--- Welcome, " + loginAccount.name + " ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. send money");
            System.out.println("5. show Transaction History ");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int option = getValidIntegerInput();


            switch (option) {
                case 1:
                    System.out.print("Enter deposit amount: $");
                    double amount = Main.getValidDoubleInput();

                    loginAccount.deposit(amount);
                    saveAccounts();
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: $");
                    double AMOUNT = Main.getValidDoubleInput();

                    loginAccount.withdraw(AMOUNT);
                    saveAccounts();
                    break;
                case 3:
                    loginAccount.ShowBalnce();
                    break;
                case 4:
                    loginAccount.transferMoney();
                    saveAccounts();
                    return;
                case 5:
                    loginAccount.ShowTransactionHistory();
                    return;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static int getValidIntegerInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }


    public static int getValidDoubleInput() {
        while (true) {
            try {
                return Main.scanner.nextInt(); // Try to read an integer
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                Main.scanner.nextLine(); // Clear invalid input
            }
        }
    }
    public static void  main(String[] args) {

        loadAccounts();



        while (true) {

            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = getValidIntegerInput();


            switch (option) {
                case 1:

                    register();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    System.out.println("exit!");
                    saveAccounts();
                    return;

                default:
                    System.out.println("Invalid option Please try again.");
            }
        }





    }
}


class BankUser implements Serializable{


    String name;

    String phone;

    private String password;

    private  double balance=0;

    String email;

    boolean isLock=false;


    private int uniqId;

    private static int Id=1;

    List<String> TransactionHistory = new ArrayList<>();




    BankUser(String name,  String password) {

        this.name = name;

        setPassword(password) ;
        this.balance = balance;
        uniqId = Id;
        Id++;

    }

    void lock(){
        isLock=true;
    }
    void unlock(){
        isLock=false;
    }





    int getID(){
        return uniqId;
    }



//    String getPass(){
//        return password;
//    }

    void setPassword(String password) {
        this.password = Base64.getEncoder().encodeToString(password.getBytes());
    }


    String getPass() {
        return new String(Base64.getDecoder().decode(password));
    }


    double getBalance(){
        return balance;
    }



    void transferMoney() {
        System.out.println("Enter recipient user ID: ");
        int recipientId = Main.getValidDoubleInput();

        BankUser recipient = null;
        for (BankUser user : Main.Accounts) {
            if (user.getID() == recipientId) {
                recipient = user;
                break;
            }
        }



        if (recipient == null) {
            System.out.println("User ID not found.");
            return;
        }

        System.out.println("Enter amount to transfer: ");
        double amount = Main.getValidDoubleInput();



        if (Main.loginAccount.getBalance() >= amount) {
            Main.loginAccount.balance-=amount;
            recipient.balance+=amount;
            System.out.println("Transfer successful! New balance: $" + Main.loginAccount.getBalance());

            TransactionHistory.add("send " + amount + " to " + recipient.name );

            recipient.TransactionHistory.add("recive " + amount + " from " + name );

            Main.ShowMenu();
        } else {
            System.out.println("Insufficient funds.");
        }
    }




    void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient Balance");
        }


        else {
            balance = balance - amount;
            System.out.println("Withdraw Successful");
            System.out.println("Balance: " + balance);
            TransactionHistory.add("withdraw " + amount );

        }
    }




    void deposit(double amount) {
        balance = balance + amount;
        System.out.println("Deposit Successful");
        System.out.println("Balance: " + balance);
        TransactionHistory.add("deposit " + amount );


    }





    void ShowTransactionHistory() {
        System.out.println("Transaction history: ");
        for (String s : TransactionHistory) {

            System.out.println(s);
        }
        System.out.println("\n");

        Main.ShowMenu();

    }









    void ShowBalnce(){
        System.out.println("Balance: " + balance);
    }
}












class Admin {
    static final String AdminName = "admin";
    static final String AdminPass = "admin";



    public static void showAllUsers() {
        System.out.println("\n Registered Users :");
        for (BankUser user : Main.Accounts) {
            System.out.println(" Username: " + user.name + "  Balance: $" + user.getBalance()+ " ID: "+user.getID());
        }
    }

    public static void lockAccount(String username) {
        for (BankUser user : Main.Accounts) {
            if (user.name.equals(username)) {
                user.lock();
                System.out.println( username + "'s account has been LOCKED.");
                return;
            }
        }
        System.out.println(" User not found.");
    }

    public static void unlockAccount(String username) {
        for (BankUser user : Main.Accounts) {
            if (user.name.equals(username)) {
                user.unlock();
                System.out.println( username + "'s account has been UNLOCKED.");
                return;
            }
        }
        System.out.println(" User not found.");
    }

    public static void resetUserPassword(String username, String newPassword) {
        for (BankUser user : Main.Accounts) {
            if (user.name.equals(username)) {
                user.setPassword(newPassword);
                System.out.println("Password for " + username + " has been RESET.");
                return;
            }
        }
        System.out.println(" User not found.");
    }


}
