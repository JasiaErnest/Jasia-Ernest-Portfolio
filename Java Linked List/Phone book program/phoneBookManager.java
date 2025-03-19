import java.util.NoSuchElementException;
import java.util.Scanner;

public class phoneBookManager<E> {
    private listNode<String> firstNode;
    protected String name;
    
    public phoneBookManager() {
        this("phoneBook");
    }
    
    public phoneBookManager(String listName) {
        name = listName;
        firstNode = null;
    }

    public boolean isEmpty() {
        return firstNode == null;
    }
    
    public void addEntry(String firstName, String lastName, String address, String phoneNum) {
        if (isEmpty()) {
            firstNode = new listNode<String>(null, firstName, lastName, address, phoneNum);
        } else {
            listNode<String> newNode = new listNode<String>(firstNode, firstName, lastName, address, phoneNum);
            firstNode = newNode;
        }
    }

    public void deleteEntry(String phoneNumber) throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException(name + " is empty");
        }

        listNode<String> current = firstNode;
        listNode<String> prev = null;

        // Checks if the firstNode has the phone number of the contact that we want to delete
        if (current != null && current.phoneNum.equals(phoneNumber)) {
            firstNode = current.nextNode; 
            return;
        }

        // Search for the node to be deleted and keep track of the previous node
        while (current != null && !current.phoneNum.equals(phoneNumber)) {
            prev = current;
            current = current.nextNode;
        }

        // Triggers if the phone number was not present in linked list
        if (current == null) {
            throw new NoSuchElementException("Entry with phone number " + phoneNumber + " not found");
        }

        // Unlinks the node from linked list to delete it
        prev.nextNode = current.nextNode;
    }

    public void findnPrintContact(int category, Scanner scan) {
        listNode<String> current = firstNode;
        if (isEmpty()) {
            throw new NoSuchElementException(name + " is empty");
        } else {
            switch (category) {
                case 1:
                    current = firstNode;
                    System.out.println("Please enter first name");
                    String firstName = scan.nextLine();
                    firstName.toLowerCase();
                    System.out.println("Please enter last name");
                    String lastName = scan.nextLine();
                    lastName.toLowerCase();
                    while (current != null) {
                        if(current.firstName.equals(firstName) && current.lastName.equals(lastName)) {
                            System.out.println();
                            System.out.println("First Name: " + current.firstName);
                            System.out.println("Last Name: " + current.lastName);
                            System.out.println("Address: " + current.address);
                            System.out.println("Phone Number: " + current.phoneNum);
                        }
                        current = current.nextNode;
                    }
                    break;
                case 2:
                    current = firstNode;
                    System.out.println("Please enter address");
                    String addressToFind = scan.nextLine();
                    addressToFind.toLowerCase();
                    while (current != null) {
                        if(current.address.equals(addressToFind)) {
                            System.out.println();
                            System.out.println("First Name: " + current.firstName);
                            System.out.println("Last Name: " + current.lastName);
                            System.out.println("Address: " + current.address);
                            System.out.println("Phone Number: " + current.phoneNum);
                        }
                        current = current.nextNode;
                    }
                    break;
                case 3:
                    current = firstNode;
                    System.out.println("Please enter a phone number");
                    String phoneNumber = scan.nextLine();
                    phoneNumber.toLowerCase();
                    while (current != null) {
                        if(current.phoneNum.equals(phoneNumber)) {
                            System.out.println();
                            System.out.println("First Name: " + current.firstName);
                            System.out.println("Last Name: " + current.lastName);
                            System.out.println("Address: " + current.address);
                            System.out.println("Phone Number: " + current.phoneNum);
                        }
                        current = current.nextNode;
                    }
                    break;
                default:
                    System.out.println("This option does not exist");
                    break;
            }
        }
    }

    public void modifyEntry(String pnToModify, int toChange, Scanner scan) {
        listNode<String> current = firstNode;
        boolean found = false;

        while (current != null) {
            if (current.phoneNum.equals(pnToModify)) {
                switch (toChange) {
                    case 1:
                        scan.nextLine();
                        System.out.println("Please enter first name");
                        String firstName = scan.nextLine().toLowerCase();
                        current.firstName = firstName;
                        break;
                    case 2:
                        scan.nextLine();
                        System.out.println("Please enter last name");
                        String lastName = scan.nextLine().toLowerCase();
                        current.lastName = lastName;
                        break;
                    case 3:
                        scan.nextLine();
                        System.out.println("Please enter address");
                        String address = scan.nextLine().toLowerCase();
                        current.address = address;
                        break;
                    case 4:
                        scan.nextLine();
                        System.out.println("Please enter a phone number");
                        String phoneNum = scan.nextLine().toLowerCase();
                        current.phoneNum = phoneNum;
                        break;
                    default:
                        System.out.println("This option does not exist");
                        break;
                }
                found = true;
                break;
            } else {
                current = current.nextNode;
            }
        }

        if (!found) {
            throw new NoSuchElementException("Entry with phone number " + pnToModify + " not found");
        }
    }

    public void printPhoneBook() {
        if (isEmpty()) {
            System.out.printf("Empty %s%n", name);
            return;
        }
        System.out.printf("The content of the %s is: ", name);
        listNode<String> current = firstNode;
    
        while (current != null) {
            System.out.println();
            System.out.println("First Name: " + current.firstName);
            System.out.println("Last Name: " + current.lastName);
            System.out.println("Address: " + current.address);
            System.out.println("Phone Number: " + current.phoneNum);
            current = current.nextNode;
        }
        System.out.println();
    }
}
