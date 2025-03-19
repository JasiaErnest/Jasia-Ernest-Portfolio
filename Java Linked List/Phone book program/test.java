//Purpose: This assignment is to make a linked list from scratch in order to create a phonebook.

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        phoneBookManager pbManager = new phoneBookManager();
        boolean run = true;
        while (run) {
            System.out.println("What do you want to do?");
            System.out.println("1. Add new contact");
            System.out.println("2. Delete contact");
            System.out.println("3. Modify contact");
            System.out.println("4. Find and print contact");
            System.out.println("5. Print phone book");
            System.out.println("6. Quit");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    addNewContact(scan, pbManager);
                    break;
                case 2: 
                    deleteContact(scan, pbManager);
                    break;
                case 3:
                    modifyContact(scan, pbManager);
                    break;
                case 4:
                    findnPrintContact(scan, pbManager);
                    break;
                case 5:
                    printPhoneBook(pbManager);
                    break;
                case 6:
                    System.out.println("Thank you for using our program!");
                    run = false;
                    break;
                default:
                    break;
            }
        }   
    }

    public static void addNewContact(Scanner scan, phoneBookManager pbManager) {
        scan.nextLine();
        System.out.println("Please enter a first name");
        String firstName = scan.nextLine();
        System.out.println("Please enter a last name");
        String lastName = scan.nextLine();
        System.out.println("Please enter an address");
        String address = scan.nextLine();
        System.out.println("Please enter a phone number");
        String phoneNum = scan.nextLine();
        pbManager.addEntry(firstName.toLowerCase(), lastName.toLowerCase(), address.toLowerCase(), phoneNum.toLowerCase());
        System.out.println("Contact added");
    }

    public static void deleteContact(Scanner scan, phoneBookManager pbManager) {
        scan.nextLine();
        System.out.println("Please enter the phone number of the contact you want to delete");
        String toDelete = scan.nextLine();
        pbManager.deleteEntry(toDelete.toLowerCase());
    }

    public static void modifyContact(Scanner scan, phoneBookManager pbManager) {
        System.out.println("Please enter the phone number of the contact you want to modify");
        scan.nextLine();
        String toModify = scan.nextLine();
        System.out.println("Please enter the category you want to modify");
        System.out.println("1. First name");
        System.out.println("2. Last name");
        System.out.println("3. Address");
        System.out.println("4. Phone number");
        int toChange = scan.nextInt();
        pbManager.modifyEntry(toModify.toLowerCase(), toChange, scan);
    }

    public static void findnPrintContact(Scanner scan, phoneBookManager pbManager) {
        System.out.println("Please enter the category you know the information of");
        System.out.println("1. First and last name");
        System.out.println("2. Address");
        System.out.println("3. Phone number");
        int category = scan.nextInt();
        scan.nextLine();
        pbManager.findnPrintContact(category, scan);
    }

    public static void printPhoneBook(phoneBookManager pbManager) {
        pbManager.printPhoneBook();
    }

    public static void intro() {
        System.out.println("This is a phone book program.");
    }
}
