package view;

import config.ConfigReadAndWrite;
import model.Contact;
import service.ContactSerVice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class View {
    public void backToMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press any key to return menu!");
        String choose = scanner.nextLine();
        switch (choose) {
            default:
                new View();
        }
    }

    public View() {
        ContactSerVice contactSerVice = new ContactSerVice();
        List<Contact> contactList = contactSerVice.findAll();
        List<Contact> blankList = new ArrayList<>();
        String PATH = "src/data/contactData.txt";
        Scanner scanner = new Scanner(System.in);
        String phoneRegex = "^0[0-9]{8,9}$";
        String emailRegex = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";

        System.out.println("----------------Chương trình quản lý danh bạ----------------------");
        System.out.println("\n1.Xem danh sách");
        System.out.println("2.Thêm mới");
        System.out.println("3.Cập nhật");
        System.out.println("4.Xóa");
        System.out.println("5.Tìm kiếm ");
        System.out.println("6.Đọc từ file");
        System.out.println("7.Ghi vào file");
        System.out.println("8.Thoát");
        System.out.print("Chọn chức năng:    ");

        String choose = scanner.nextLine();
        switch (choose) {
            case "1":
                if (contactList.equals(blankList)) {
                    System.err.println("        No contact found! ");
                } else {
                    System.out.println(contactList);
                }
                backToMenu();
                break;
            case "2":
                System.out.println("Enter phone number (9-10 digits and starts with 0): ");
                String phoneNumber;
                while (true) {
                    phoneNumber = scanner.nextLine();
                    if (Pattern.matches(phoneRegex, phoneNumber)) {
                        break;
                    } else {
                        System.err.println("Invalid phone number: ");
                    }
                }
                System.out.println("Entergroup contact: ");
                String group = scanner.nextLine();
                System.out.println("Enter name: ");
                String name = scanner.nextLine();
                System.out.println("Enter gender : ");
                String gender = scanner.nextLine();
                System.out.println("Enter address: ");
                String address = scanner.nextLine();
                System.out.println("Enter date of birth: ");
                String birth = scanner.nextLine();
                System.out.println("Email must follow abc@def.xyz: ");
                String email;
                while (true) {
                    email = scanner.nextLine();
                    if (Pattern.matches(emailRegex, email)) {
                        break;
                    } else {
                        System.err.println("Invalid email: ");
                    }
                }
                Contact contact = new Contact(phoneNumber, group, name, address, birth, email);
                contactList.add(contact);
                backToMenu();
                break;
            case "3":
                System.out.println("Enter phone number to change:  ");
                String editPhonenumber = scanner.nextLine();
                if (contactSerVice.findByPhonenumber(editPhonenumber) == null) {
                    System.err.println("Cannot find this number:  ");
                    backToMenu();
                } else {
                    System.out.println("Change group: ");
                    String group1 = scanner.nextLine();
                    System.out.println("Change name: ");
                    String name1 = scanner.nextLine();
                    System.out.println("Change gender: ");
                    String gender1 = scanner.nextLine();
                    System.out.println("Change address: ");
                    String address1 = scanner.nextLine();
                    System.out.println("Change date: ");
                    String birth1 = scanner.nextLine();
                    System.out.println("email must follow abc@def.xyz:");
                    String email1;
                    while (true) {
                        email1 = scanner.nextLine();
                        if (Pattern.matches(emailRegex, email1)) {
                            break;
                        } else {
                            System.err.println("Email not valid. try again: ");
                        }
                    }
                    for (int i = 0; i < contactSerVice.findAll().size(); i++) {
                        if (contactList.get(i).getPhonenumber().equals(editPhonenumber)) {
                            contactList.get(i).setName(name1);
                            contactList.get(i).setAddress(address1);
                            contactList.get(i).setBirth(birth1);
                            contactList.get(i).setEmail(email1);
                            contactList.get(i).setGroup(group1);
                        }
                    }
                }
                backToMenu();
                break;

            case "4":
                System.out.println("Enter phone number to delete: ");
                String deleteNumber = scanner.nextLine();
                if (contactSerVice.findByPhonenumber(deleteNumber) == null) {
                    System.err.println("Cannot find phone number");
                    backToMenu();
                } else {
                    System.out.println("Enter Y to delete: ");
                    String choose2 = scanner.nextLine();
                    switch (choose2) {
                        case "Y":
                            for (int i = 0; i < contactList.size(); i++) {
                                if (contactList.get(i).getPhonenumber().equals(deleteNumber)) {
                                    contactList.remove(contactList.get(i));
                                }
                            }
                            System.out.println("Delete!");
                            backToMenu();
                            break;
                        default:
                            backToMenu();
                            break;
                    }

                }
                backToMenu();
                break;
            case "5":
                System.out.println("1 for phone number, 2 for name: ");
                String choose1 = scanner.nextLine();
                switch (choose1) {
                    case "1":
                        System.out.println("Enter phone number: ");
                        String searchPhonenumber = scanner.nextLine();
                        if (contactSerVice.findByPhonenumber(searchPhonenumber) == null) {
                            System.err.println("Info not found");
                            backToMenu();
                        } else {
                            System.out.println(contactSerVice.findByPhonenumber(searchPhonenumber));
                        }
                        backToMenu();
                        break;
                    case "2":
                        System.out.println("Enter name");
                        String searchName = scanner.nextLine();
                        if (contactSerVice.findByName(searchName) == null) {
                            System.err.println("Does not find info");
                            backToMenu();
                        } else {
                            System.out.println(contactSerVice.findByName(searchName));
                        }
                        backToMenu();
                        break;
                    default:
                        new View();
                        break;
                }
                backToMenu();
                break;
            case "6":
                System.out.println("Press Y to continue");
                String choose2 = scanner.nextLine();
                switch (choose2) {
                    case "Y":
                        contactList = new ConfigReadAndWrite().readFromFile(PATH);
                        backToMenu();
                        break;
                    default:
                        backToMenu();
                        break;
                }
            case "7":
                System.out.println("Press Y to continue");
                String choose3 = scanner.nextLine();
                switch (choose3) {
                    case "Y":
                        new ConfigReadAndWrite().writeToFile(PATH, contactList);
                        backToMenu();
                        break;
                    default:
                        backToMenu();
                        break;
                }
            case "8":
                new View();
                break;
            default:
                System.err.println("Try again!");
                new View();
        }
    }

    public static void main(String[] args) {
        new View();
    }
}
