package application.utilities;

import java.util.Scanner;

public class DataInput {

    public static int inputInteger(String msg, int min, int max) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        int num = sc.nextInt();
        if (num >= min && num <= max) {
            return num;
        }
        return -1;
    }

    public static String inputString(String msg, String parten) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        String s = sc.nextLine();
        if (s.matches(parten)) {
            return s;
        }
        return "";
    }

    public static int inputChoice(int min, int max) {
        Scanner sc = new Scanner(System.in);
        boolean check = false;
        do {
            try {
                System.out.print("Enter The Option: ");
                int num = sc.nextInt();
                if (num >= min && num <= max) {
                    check = true;
                    return num;
                } else {
                    System.out.println("Option out of range. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Option Incorrect");
              check = false;
              sc.nextLine();
            }
        } while (!check);
        return -1;
    }

    public static String inputNameNoFormat() {
        String tmp = inputString("Enter The New Name (Press Enter to keep current): ", ".*");
        return tmp;
    }

    public static String inputAddressNoFormat() {
        String tmp = inputString("Enter The New Address (Press Enter to keep current): ", ".*");
        return tmp;
    }

    public static String inputPhoneNoFormat() {
        String tmp = inputString("Enter The New Phone (Press Enter to keep current): ", ".*");
        return tmp;
    }

    public static String enterId() {
        String enterID = inputString("Enter The ID Hotel: ", "^H[0-9]{1,2}$");
        return enterID;
    }

    public static String enterName() {
        String enterName = inputString("Enter The Name: ", "^[a-zA-Z\\s]+$");
        return enterName;
    }

    public static boolean InputYN(String msg) {

        String connt;
        connt = inputString(msg, "[yYnN]$");
        if (connt.equalsIgnoreCase("Y")) {
            return true;
        } else if (connt.equalsIgnoreCase("N")) {
            return false;
        } else {
            System.out.println("Please Enter Y or N ");
            return InputYN(msg);
        }

    }

}
