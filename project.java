import java.io.*;
import java.util.*;

public class LoginSystem {
    
    private static String fname, password, name, pass, date, month, year, email, number;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        LoginSystem ls = new LoginSystem();
        ls.homepage();
    }

    public void homepage() {
        while(true) {
            System.out.println("\n\t     ____________________________________________________________________________________________");
            System.out.println("\t    |                                                  \t\t\t\t\t        |");
            System.out.println("\n\t    |\t\t\t              CREATE, LOGIN & AUTHENTICATION                            |");
            System.out.println("\t    |                                                  \t\t\t\t\t        |");
            System.out.println("\n\t    |___________________________________________________________________________________________|");
            System.out.println("\n\t\t\t\t\t ______________________________________");
            System.out.println("\t\t\t\t\t|                                      |");
            System.out.println("\n\t\t\t\t\t|                HOMEPAGE              |");
            System.out.println("\n\t\t\t\t\t|______________________________________|");
            System.out.println("\n\t\t\t\t\t|                                      |");
            System.out.println("\n\t\t\t\t\t|Kindly select an option below:        |");
            System.out.println("\n\t\t\t\t\t|1. Create Account                     |");
            System.out.println("\n\t\t\t\t\t|2. Login                              |");
            System.out.println("\n\t\t\t\t\t|3. List of All Accounts               |");
            System.out.println("\n\t\t\t\t\t|4. Search Account By Name             |");
            System.out.println("\n\t\t\t\t\t|5. Exit                               |");
            System.out.println("\t\t\t\t\t|______________________________________|");

            System.out.print("\t\t\t\t\t-> ");
            char choice = sc.next().charAt(0);

            switch(choice) {
                case '1': createAccount(); break;
                case '2': login(); break;
                case '3': listAllAccounts(); break;
                case '4': searchAccount(); break;
                case '5': System.exit(0);
                default: 
                    System.out.println("\t\t\t\t\tInvalid option, please try again.");
            }
        }
    }

    public void createAccount() {
        System.out.println("\n\t\t\t\t ________________________________________");
        System.out.println("\t\t\t\t|                                        |");
        System.out.println("\n\t\t\t\t|               Create Account           |");
        System.out.println("\n\t\t\t\t|________________________________________|");

        System.out.print("\n\t\t\t\t\tEnter User_Name: ");
        fname = sc.next();
        
        System.out.println("\t\t\t\t\tEnter your Date of Birth-");
        System.out.print("\t\t\t\t\tDay: ");
        date = sc.next();
        System.out.print("\t\t\t\t\tMonth: ");
        month = sc.next();
        System.out.print("\t\t\t\t\tYear: ");
        year = sc.next();

        System.out.println("\n\t\t\t\t\tEnter your Contact Details: ");
        System.out.print("\t\t\t\t\tPhone Number: ");
        number = sc.next();
        
        System.out.print("\n\t\t\t\t\tEnter Email Address: ");
        email = sc.next();

        System.out.println("\n\t\t\t\t\tEnter password: ");
        password = sc.next();

        try {
            FileWriter fileWriter = new FileWriter("files", true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(fname + " " + password + " " + number + " " + email + " " + date + " " + month + " " + year + "\n");
            writer.close();
            System.out.println("\n\n\t\t\t\t\tAccount Created Successfully.");
        } catch (IOException e) {
            System.out.println("\t\t\t\t\tRegistration failed. Try again later.");
        }
    }

    public void login() {
        System.out.println("\n\t\t\t\t ______________________________________");
        System.out.println("\t\t\t\t\t|                                      |");
        System.out.println("\n\t\t\t\t\t|               LOGIN PAGE             |");
        System.out.println("\n\t\t\t\t\t|______________________________________|");

        System.out.print("\n\t\t\t\t\tEnter User_Name: ");
        name = sc.next();
        System.out.print("\t\t\t\t\tEnter Password: ");
        pass = sc.next();

        boolean flag = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("files"));
            String line;
            while((line = reader.readLine()) != null) {
                String[] accountDetails = line.split(" ");
                if(accountDetails[0].equals(name) && accountDetails[1].equals(pass)) {
                    flag = true;
                    break;
                }
            }
            reader.close();

            if(flag) {
                System.out.println("\n\t\t\t\t\tLogin Successful");
                menu();
            } else {
                System.out.println("\n\t\t\t\t\tNo account exists with the given credentials.");
            }
        } catch (IOException e) {
            System.out.println("\t\t\t\t\tError reading the file.");
        }
    }

    public void menu() {
        while(true) {
            System.out.println("\n\t\t\t\t __________________________________________________");
            System.out.println("\t\t\t\t|                                                  |");
            System.out.println("\n\t\t\t\t|                 ACCOUNT MANAGEMENT              |");
            System.out.println("\n\t\t\t\t|__________________________________________________|");
            System.out.println("\n\t\t\t\t\tKindly select an option below:");
            System.out.println("\n\t\t\t\t\t1. Account Details");
            System.out.println("\n\t\t\t\t\t2. Modify Account");
            System.out.println("\n\t\t\t\t\t3. Change Password");
            System.out.println("\n\t\t\t\t\t4. Delete Account");
            System.out.println("\n\t\t\t\t\t5. Logout");
            
            System.out.print("\n\t\t\t\t\t-> ");
            char option = sc.next().charAt(0);

            switch(option) {
                case '1': accountDetails(); break;
                case '2': modifyAccount(); break;
                case '3': changePassword(); break;
                case '4': deleteAccount(); break;
                case '5': homepage(); break;
                default: 
                    System.out.println("\t\t\t\t\tInvalid option, please try again.");
            }
        }
    }

    public void accountDetails() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("files"));
            String line;
            while((line = reader.readLine()) != null) {
                String[] accountDetails = line.split(" ");
                if(accountDetails[0].equals(name) && accountDetails[1].equals(pass)) {
                    System.out.println("\n\t\t\t\t\t1. User_Name = " + accountDetails[0]);
                    System.out.println("\t\t\t\t\t2. Email = " + accountDetails[3]);
                    System.out.println("\t\t\t\t\t3. Contact = " + accountDetails[2]);
                    System.out.println("\t\t\t\t\t4. Date of Birth = " + accountDetails[4] + "/" + accountDetails[5] + "/" + accountDetails[6]);
                    System.out.println("\t\t\t\t\t5. Account Password = " + accountDetails[1]);
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("\t\t\t\t\tError reading the file.");
        }
    }

    public void modifyAccount() {
        System.out.println("\n\t\t\t\t ___________________________________________");
        System.out.println("\t\t\t\t|                                           |");
        System.out.println("\n\t\t\t\t|              MODIFY ACCOUNT               |");
        System.out.println("\n\t\t\t\t|___________________________________________|");

        try {
            BufferedReader reader = new BufferedReader(new FileReader("files"));
            List<String> accounts = new ArrayList<>();
            String line;
            while((line = reader.readLine()) != null) {
                String[] accountDetails = line.split(" ");
                if(accountDetails[0].equals(name) && accountDetails[1].equals(pass)) {
                    System.out.print("\n\t\t\t\t\tEnter new User_Name: ");
                    fname = sc.next();
                    System.out.print("\n\t\t\t\t\tEnter new Date of Birth (Day Month Year): ");
                    date = sc.next();
                    month = sc.next();
                    year = sc.next();
                    System.out.print("\n\t\t\t\t\tEnter new Phone Number: ");
                    number = sc.next();
                    System.out.print("\n\t\t\t\t\tEnter new Email: ");
                    email = sc.next();

                    accounts.add(fname + " " + pass + " " + number + " " + email + " " + date + " " + month + " " + year);
                } else {
                    accounts.add(line);
                }
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter("files"));
            for(String account : accounts) {
                writer.write(account + "\n");
            }
            writer.close();
            System.out.println("\n\t\t\t\t\tAccount details updated successfully.");
        } catch (IOException e) {
            System.out.println("\t\t\t\t\tError reading or writing to the file.");
        }
    }

    public void changePassword() {
        System.out.println("\n\t\t\t\t ___________________________________________");
        System.out.println("\t\t\t\t|                                           |");
        System.out.println("\n\t\t\t\t|              CHANGE PASSWORD              |");
        System.out.println("\n\t\t\t\t|___________________________________________|");

        System.out.print("\n\t\t\t\t\tEnter your Old Password: ");
        String oldPass = sc.next();
        if(oldPass.equals(pass)) {
            System.out.print("\n\t\t\t\t\tEnter new password: ");
            String newPass = sc.next();
            System.out.print("\n\t\t\t\t\tConfirm new password: ");
            String confirmPass = sc.next();

            if(newPass.equals(confirmPass)) {
                pass = newPass;
                modifyAccount();
                System.out.println("\n\t\t\t\t\tPassword changed successfully.");
            } else {
                System.out.println("\n\t\t\t\t\tPasswords do not match.");
            }
        } else {
            System.out.println("\n\t\t\t\t\tIncorrect old password.");
        }
    }

    public void deleteAccount() {
        System.out.println("\n\t\t\t\t ___________________________________________");
        System.out.println("\t\t\t\t|                                           |");
        System.out.println("\n\t\t\t\t|              DELETE ACCOUNT               |");
        System.out.println("\n\t\t\t\t|___________________________________________|");

        System.out.print("\n\t\t\t\t\tAre you sure you want to delete your account? (Y/N): ");
        char confirm = sc.next().charAt(0);
        if(confirm == 'Y' || confirm == 'y') {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("files"));
                List<String> accounts = new ArrayList<>();
                String line;
                while((line = reader.readLine()) != null) {
                    String[] accountDetails = line.split(" ");
                    if(accountDetails[0].equals(name) && accountDetails[1].equals(pass)) {
                        continue;
                    }
                    accounts.add(line);
                }
                reader.close();

                BufferedWriter writer = new BufferedWriter(new FileWriter("files"));
                for(String account : accounts) {
                    writer.write(account + "\n");
                }
                writer.close();
                System.out.println("\n\t\t\t\t\tAccount deleted successfully.");
                homepage();
            } catch (IOException e) {
                System.out.println("\t\t\t\t\tError reading or writing to the file.");
            }
        } else {
            System.out.println("\n\t\t\t\t\tAccount deletion cancelled.");
        }
    }

    public void listAllAccounts() {
        System.out.println("\n\t\t\t\t ________________________________________");
        System.out.println("\t\t\t\t|                                        |");
        System.out.println("\n\t\t\t\t|            LIST OF ALL ACCOUNTS       |");
        System.out.println("\n\t\t\t\t|________________________________________|");

        try {
            BufferedReader reader = new BufferedReader(new FileReader("files"));
            String line;
            while((line = reader.readLine()) != null) {
                String[] accountDetails = line.split(" ");
                System.out.println("\n\t\t\t\t\tUser Name: " + accountDetails[0]);
                System.out.println("\t\t\t\t\tEmail: " + accountDetails[3]);
                System.out.println("\t\t\t\t\tPhone: " + accountDetails[2]);
                System.out.println("\t\t\t\t\tDate of Birth: " + accountDetails[4] + "/" + accountDetails[5] + "/" + accountDetails[6]);
                System.out.println("\t\t\t\t-------------------------------------------");
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("\t\t\t\t\tError reading the file.");
        }
    }

    public void searchAccount() {
        System.out.println("\n\t\t\t\t ________________________________________");
        System.out.println("\t\t\t\t|                                        |");
        System.out.println("\n\t\t\t\t|            SEARCH ACCOUNT BY NAME     |");
        System.out.println("\n\t\t\t\t|________________________________________|");

        System.out.print("\n\t\t\t\t\tEnter username to search: ");
        String searchName = sc.next();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("files"));
            String line;
            while((line = reader.readLine()) != null) {
                String[] accountDetails = line.split(" ");
                if(accountDetails[0].equals(searchName)) {
                    System.out.println("\n\t\t\t\t\tAccount Found!");
                    System.out.println("\n\t\t\t\t\tUser Name: " + accountDetails[0]);
                    System.out.println("\t\t\t\t\tEmail: " + accountDetails[3]);
                    System.out.println("\t\t\t\t\tPhone: " + accountDetails[2]);
                    System.out.println("\t\t\t\t\tDate of Birth: " + accountDetails[4] + "/" + accountDetails[5] + "/" + accountDetails[6]);
                    return;
                }
            }
            reader.close();
            System.out.println("\n\t\t\t\t\tNo account found with that username.");
        } catch (IOException e) {
            System.out.println("\t\t\t\t\tError reading the file.");
        }
    }
}
