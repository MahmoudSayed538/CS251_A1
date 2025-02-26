// saif omar
//ID: 20230183
import java.util.Scanner;


public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        while(true) {

            System.out.println("Welcom to car rental ");
            System.out.println("Please login");
            System.out.println("Enter your choice");
            System.out.println("1)Admin");
            System.out.println("2)User");
            System.out.println("3)Exit");
            int choice;

            choice = input.nextInt();

            if (choice == 3)
            {
                System.out.println("See you soon/!!");
                break;
            }
            switch (choice) {
                case 1:
                    System.out.println("Please enter the user name:");
                    String username = input.next();
                    System.out.println("Please enter the password:");
                    String password = input.next();
                    if (username.equals("admin") && password.equals("1234")) {
                        int i;
                        System.out.println("Welcome Admin");
                        System.out.println("enter your choice: ");
                        System.out.println("1)Add item: ");
                        System.out.println("2)Remove item: ");
                        System.out.println("3)update price: ");
                        Scanner in = new Scanner(System.in);
                        i = in.nextInt();
                        car c0 = new car();
                        switch (i) {
                            case 1:

                                c0.addd();

                                break;

                            case 2:
                                c0.remove();
                                break;

                            case 3:
                                c0.updateprice();

                        }


                    } else {
                        System.out.println("invalid username or password");
                    }
                    break;
                case 2:
                    System.out.println("please choose which car you want to rent: ");
                    car c1 = new car();
                    c1.settype();
                    c1.setcolor();
                    c1.setmodel();
                    c1.setPrice();
                    c1.setrentalhours();
                    break;
            }
        }
    }
}