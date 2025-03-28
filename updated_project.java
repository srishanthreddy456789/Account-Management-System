import java.util.*;
import java.io.*;
import java.util.regex.*;

public class LoginSystem {
    private String fname, password, name, pass, date, month, year, email, number, enc;

    private void waiting() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(200);
                System.out.print("..");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String encryption(String pw) {
        StringBuilder enc_pass = new StringBuilder();
        for (int i = 0; i < pw.length(); i++) {
            int encrypted = pw.charAt(i) + 12;
            enc_pass.append(encrypted);
        }
        return enc_pass.toString();
    }

    private String decrypt(String enc_pass) {
        StringBuilder decrypted_pass = new StringBuilder();
        int i = 0;
        while (i < enc_pass.length()) {
            int num = Integer.parseInt(enc_pass.substring(i, i + 2));
            num -= 12;
            decrypted_pass.append((char) num);
            i += 2;
        }
        return decrypted_pass.toString();
    }

    private boolean isLeap(int year) {
        boolean x = false;
        if (year % 4 == 0)
            x = true;
        if (year % 100 == 0)
            x = false;
        if (year % 400 == 0)
            x = true;
        return x;
    }

    private boolean validNum(String s) {
        Pattern pattern = Pattern.compile("(6|7|8|9)?[0-9]{9}");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    private boolean validMail(String email) {
        int At = -1, Dot = -1;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                At = i;
            } else if (email.charAt(i) == '.') {
                Dot = i;
                break;
            }
        }
        if (At == -1 || Dot == -1 || Dot - At <= 1 || Dot >= email.length() - 1 || email.length() <= 4)
            return false;
        return true;
    }

    private boolean valCode(String s) {
        if (s.charAt(0) == '+') s = s.substring(1);
        String[] countryCodes = {
            "93", "355", "213", "1-684", "376", "244", "1-264", "672", "1-268", "54", "374", "297", "61", "43", "994", 
            "1-242", "973", "880", "1-246", "375", "32", "501", "229", "1-441", "975", "591", "387", "267", "55", "246", 
            "673", "359", "226", "257", "855", "237", "1", "238", "1-345", "236", "235", "56", "86", "53", "61", "57", 
            "269", "243", "242", "682", "506", "225", "385", "53", "357", "420", "45", "253", "1-767", "1-809", "1-829", 
            "670", "593", "20", "503", "240", "291", "372", "251", "500", "298", "679", "358", "33", "594", "689", "262", 
            "241", "220", "995", "49", "233", "350", "44", "30", "299", "1-473", "590", "1-671", "502", "224", "245", 
            "592", "509", "672", "39", "504", "852", "36", "354", "91", "62", "98", "964", "377", "976", "1-664", "212", 
            "258", "95", "264", "674", "977", "31", "599", "687", "64", "505", "227", "234", "683", "672", "1-670", "47", 
            "968", "92", "680", "970", "507", "675", "595", "51", "63", "64", "48", "351", "1-787", "1-939", "974", "262", 
            "40", "7", "250", "290", "1-869", "1-758", "508", "1-784", "685", "378", "239", "966", "381", "221", "248", 
            "232", "65", "421", "386", "677", "252", "27", "500", "34", "94", "249", "597", "47", "268", "46", "41", "963", 
            "886", "992", "255", "66", "228", "690", "676", "1-868", "216", "90", "993", "1-649", "688", "256", "380", 
            "971", "44", "1", "246", "598", "998", "678", "418", "58", "84", "1-284", "1-340", "681", "212", "967", "243", 
            "260", "263"
        };
        for (String code : countryCodes) {
            if (code.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public void homepage() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        
        System.out.println("\n\t     ____________________________________________________________________________________________");
        System.out.println("\t    |                                                  \t\t\t\t\t        |");
        System.out.println("\n\t    |\t\t\t              CREATE, LOGIN & AUTHENTICATION                            |");
        System.out.println("\t    |                                                  \t\t\t\t\t        |");
        System.out.println("\n\t    |___________________________________________________________________________________________|");
        System.out.println("\n\n\n");
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
        System.out.println("\n\n");
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("\t\t\t\t\t-> ");
        char x = scanner.next().charAt(0);
        
        if (x == '1') {
            createAccount();
        } else if (x == '2') {
            login();
        } else if (x == '3') {
            listAccounts();
        } else if (x == '4') {
            searchAccount();
        } else if (x == '5') {
            System.exit(0);
        } else {
            System.out.println("\t\t\t\t\tInvalid Parameter, Try Again.");
            try {
                Thread.sleep(636);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            homepage();
        }
    }

    private void createAccount() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        
        System.out.println("\n\t\t\t\t ________________________________________");
        System.out.println("\t\t\t\t|                                        |");
        System.out.println("\n\t\t\t\t|               Create Account           |");
        System.out.println("\t\t\t\t|________________________________________|");
        System.out.println("\n");
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("\t\t\t\t\tEnter User Name: ");
        fname = scanner.next();
        
        System.out.println("\n\t\t\t\t\tEnter your Date of Birth-");
        int d, m, y;
        
        do {
            System.out.print("\t\t\t\t\tDay : ");
            d = scanner.nextInt();
            if (d < 1 || d > 31) {
                System.out.println("\t\t\t\t\tReminder: You are a Human! Enter a valid Day.");
            }
        } while (d < 1 || d > 31);
        date = Integer.toString(d);
        
        do {
            System.out.print("\t\t\t\t\tMonth : ");
            m = scanner.nextInt();
            if (m < 1 || m > 12) {
                System.out.println("\t\t\t\t\tReminder: You are a Human! Enter a valid Month.\n");
            } else if (d > 29 && m == 2) {
                System.out.println("\t\t\t\t\tReminder: Are you sure you born in Feb?\n\t\t\t\t\tRewrite your DOB\n");
                d = 0; // Force re-entry of day
            }
        } while (m < 1 || m > 12 || (d > 29 && m == 2));
        month = Integer.toString(m);
        
        do {
            System.out.print("\t\t\t\t\tYear :  ");
            y = scanner.nextInt();
            if (y < 1950 || y > 2024) {
                System.out.println("\t\t\t\t\tReminder: You are a Human! Enter a valid Year.\n\t\t\t\t\tLet's Begin Again!\n");
                d = 0; // Force re-entry of day
            } else if (!isLeap(y) && m == 2 && d > 28) {
                System.out.println("\t\t\t\t\tReminder: You are a Human! Be logical.\n\t\t\t\t\tLet's Begin Again!\n");
                d = 0; // Force re-entry of day
            }
        } while (y < 1950 || y > 2024 || (!isLeap(y) && m == 2 && d > 28));
        year = Integer.toString(y);
        
        System.out.println("\n\t\t\t\t\tEnter your Contact Details: ");
        String countryCode;
        do {
            System.out.print("\t\t\t\t\tEnter Country Code : ");
            countryCode = scanner.next();
            if (!valCode(countryCode)) {
                System.out.println("\t\t\t\t\tPlease Enter a Valid Code.\n");
            }
        } while (!valCode(countryCode));
        
        String digit;
        do {
            System.out.print("\t\t\t\t\tEnter Mobile Number : ");
            digit = scanner.next();
            if (digit.length() != 10) {
                System.out.println("\t\t\t\t\tEntered " + digit.length() + " Digits. Expecting 10, try again!\n");
            } else if (!validNum(digit)) {
                System.out.println("\t\t\t\t\tPlease enter a valid phone number!\n");
            }
        } while (digit.length() != 10 || !validNum(digit));
        number = countryCode + digit;
        
        do {
            System.out.println("\n\t\t\t\t\tEnter Email Address: ");
            System.out.print("\t\t\t\t\t");
            email = scanner.next();
            if (!validMail(email)) {
                System.out.println("\t\t\t\t\tPlease Re-enter a Valid Email Address\n");
            }
        } while (!validMail(email));
        
        System.out.println("\n\t\t\t\t\tEnter password: ");
        System.out.print("\t\t\t\t\t");
        Console console = System.console();
        if (console != null) {
            password = new String(console.readPassword());
        } else {
            password = scanner.next();
        }
        
        enc = encryption(password);
        
        try (PrintWriter file = new PrintWriter(new FileWriter("files.txt", true))) {
            file.println(fname + " " + enc + " " + number + " " + email + " " + date + " " + month + " " + year);
            System.out.println("\n\n\t\t\t\t\tAccount Created Successfully.");
            password = "";
            enc = "";
        } catch (IOException e) {
            System.out.println("\t\t\t\t\tRegistration failed\nTry again later");
        }
        
        System.out.print("\n\t\t\t\t\t");
        scanner.nextLine(); // Consume newline
        scanner.nextLine(); // Wait for enter
        homepage();
    }

    private void login() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        
        System.out.println("\n\t\t\t\t\t ______________________________________");
        System.out.println("\t\t\t\t\t|                                      |");
        System.out.println("\n\t\t\t\t\t|               LOGIN PAGE             |");
        System.out.println("\n\t\t\t\t\t|______________________________________|");
        System.out.println("\n");
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("\t\t\t\t\tEnter User_Name : ");
        name = scanner.next();
        
        System.out.print("\t\t\t\t\tEnter Password : ");
        Console console = System.console();
        if (console != null) {
            pass = new String(console.readPassword());
        } else {
            pass = scanner.next();
        }
        
        boolean flag = false;
        try (Scanner file = new Scanner(new File("files.txt"))) {
            while (file.hasNext()) {
                fname = file.next();
                password = file.next();
                number = file.next();
                email = file.next();
                date = file.next();
                month = file.next();
                year = file.next();
                
                String dec_password = decrypt(password);
                if (name.equals(fname) && pass.equals(dec_password)) {
                    flag = true;
                    break;
                }
            }
            
            System.out.println("\n\t\t\t\t\tVerifying User Data");
            System.out.print("\t\t\t\t\t");
            waiting();
            
            if (flag) {
                System.out.println("\n\n\t\t\t\t\tLogin Successful");
                try {
                    Thread.sleep(666);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                menu();
            } else {
                System.out.println("\n\n\t\t\t\t\tNo account exists with given credentials.");
                System.out.print("\n\t\t\t\t\t");
                scanner.nextLine(); // Wait for enter
                homepage();
            }
        } catch (FileNotFoundException e) {
            System.out.println("\t\t\t\t\tError! File not found");
            System.out.print("\n\t\t\t\t\t");
            scanner.nextLine(); // Wait for enter
            homepage();
        }
    }

    private void listAccounts() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        
        System.out.println("\n\t\t\t\t ______________________________________________");
        System.out.println("\t\t\t\t|                                              |");
        System.out.println("\n\t\t\t\t|             LIST OF ALL ACCOUNTS             |");
        System.out.println("\t\t\t\t|______________________________________________|");
        System.out.println("\n");
        
        int x = 1;
        try (Scanner file = new Scanner(new File("files.txt"))) {
            while (file.hasNext()) {
                fname = file.next();
                password = file.next();
                number = file.next();
                email = file.next();
                date = file.next();
                month = file.next();
                year = file.next();
                
                try {
                    Thread.sleep(66);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("\t\t\t\t\t\t ______________");
                System.out.println("\t\t\t\t\t\t|              |");
                System.out.println("\t\t\t\t\t\t|   ACCOUNT " + x++ + "  |");
                System.out.println("\t\t\t\t\t\t|______________|");
                System.out.println("\n\t\t\t\t\t 1. User_name = " + fname);
                System.out.println("\t\t\t\t\t 2. Contact = " + number);
                System.out.println("\t\t\t\t\t 3. Email = " + email);
                System.out.println("\t\t\t\t\t 4. Date of Birth = " + date + "/" + month + "/" + year);
                System.out.println("\t\t\t\t\t ------------------------------------");
                System.out.println("\n");
            }
            System.out.println("\t\t\t\t\t Reached at end of the File");
        } catch (FileNotFoundException e) {
            System.out.println("\t\t\t\t\t Error! File not found");
        }
        
        System.out.print("\t\t\t\t\t ");
        new Scanner(System.in).nextLine(); // Wait for enter
        homepage();
    }

    private void searchAccount() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        
        System.out.println("\n\t\t\t\t ___________________________________________");
        System.out.println("\t\t\t\t|                                           |");
        System.out.println("\n\t\t\t\t|                SEARCH ACCOUNT             |");
        System.out.println("\t\t\t\t|___________________________________________|");
        System.out.println("\n");
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("\t\t\t\t\tEnter User_Name: ");
        name = scanner.next();
        
        int found = 0;
        try (Scanner file = new Scanner(new File("files.txt"))) {
            while (file.hasNext()) {
                fname = file.next();
                password = file.next();
                number = file.next();
                email = file.next();
                date = file.next();
                month = file.next();
                year = file.next();
                
                if (name.equals(fname)) {
                    try {
                        Thread.sleep(66);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    System.out.println("\n\t\t\t\t\t1. User_Name = " + fname);
                    System.out.println("\t\t\t\t\t2. Email = " + email);
                    System.out.println("\t\t\t\t\t3. Contact = " + number);
                    System.out.println("\t\t\t\t\t4. Date of Birth = " + date + "/" + month + "/" + year);
                    System.out.println("\n");
                    System.out.println("\t\t\t\t\t------------------------------------");
                    System.out.println("\n");
                    found++;
                }
            }
            
            if (found == 0) {
                System.out.println("\n\t\t\t\t\tNo account exist with [" + name + "] username .\n\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("\t\t\t\t\tError! File not found");
        }
        
        System.out.print("\t\t\t\t\t");
        scanner.nextLine(); // Wait for enter
        homepage();
    }

    public void menu() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        
        System.out.println("\n\t\t\t\t __________________________________________________");
        System.out.println("\t\t\t\t|                                                  |");
        System.out.println("\n\t\t\t\t|                 ACCOUNT MANAGMENT                |");
        System.out.println("\t\t\t\t|__________________________________________________|");
        System.out.println("\n\n");
        System.out.println("\t\t\t\t\tKindly select an option below:\n\n");
        System.out.println("\t\t\t\t\t1. Account Details\n\n");
        System.out.println("\t\t\t\t\t2. Modify Account\n\n");
        System.out.println("\t\t\t\t\t3. Change Password\n\n");
        System.out.println("\t\t\t\t\t4. Delete Account\n\n");
        System.out.println("\t\t\t\t\t5. Logout");
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\t\t\t\t\t-> ");
        char option = scanner.next().charAt(0);
        
        switch (option) {
            case '1':
                details();
                break;
            case '2':
                modify();
                break;
            case '3':
                changePassword();
                break;
            case '4':
                deleteAccount();
                break;
            case '5':
                logout();
                break;
            default:
                System.out.println("\t\t\t\t\tInvalid Option\n\t\t\t\t\tTry Again.");
                try {
                    Thread.sleep(636);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                menu();
        }
    }

    private void details() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        
        System.out.println("\n\t\t\t\t ___________________________________________");
        System.out.println("\t\t\t\t|                                           |");
        System.out.println("\n\t\t\t\t|              ACCOUNT DETAILS              |");
        System.out.println("\t\t\t\t|___________________________________________|");
        System.out.println("\n");
        
        try (Scanner file = new Scanner(new File("files.txt"))) {
            while (file.hasNext()) {
                fname = file.next();
                password = file.next();
                number = file.next();
                email = file.next();
                date = file.next();
                month = file.next();
                year = file.next();
                
                String dec_password = decrypt(password);
                if (name.equals(fname) && dec_password.equals(pass)) {
                    try {
                        Thread.sleep(66);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    System.out.println("\n\t\t\t\t\t1. User_Name = " + fname + "\n\n");
                    try {
                        Thread.sleep(66);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    System.out.println("\t\t\t\t\t2. Email = " + email + "\n\n");
                    try {
                        Thread.sleep(66);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    System.out.println("\t\t\t\t\t3. Contact = " + number + "\n\n");
                    try {
                        Thread.sleep(66);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    System.out.println("\t\t\t\t\t4. Date of Birth = " + date + "/" + month + "/" + year + "\n\n");
                    try {
                        Thread.sleep(66);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    System.out.println("\t\t\t\t\t5. Account Password = " + dec_password + "\n\n");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\t\t\t\t\tError! File not found");
        }
        
        System.out.print("\t\t\t\t\t");
        new Scanner(System.in).nextLine(); // Wait for enter
        menu();
    }

    private void modify() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        
        System.out.println("\n\t\t\t\t ___________________________________________");
        System.out.println("\t\t\t\t|                                           |");
        System.out.println("\n\t\t\t\t|              MODIFY ACCOUNT               |");
        System.out.println("\t\t\t\t|___________________________________________|");
        System.out.println("\n");
        
        try (Scanner file = new Scanner(new File("files.txt"));
             PrintWriter file1 = new PrintWriter(new FileWriter("modify.txt"))) {
            
            while (file.hasNext()) {
                fname = file.next();
                password = file.next();
                number = file.next();
                email = file.next();
                date = file.next();
                month = file.next();
                year = file.next();
                
                String dec_password = decrypt(password);
                if (name.equals(fname) && pass.equals(dec_password)) {
                    Scanner scanner = new Scanner(System.in);
                    char ch;
                    
                    // Change username
                    do {
                        System.out.print("\t\t\t\t\tWanna change User Name?\n\t\t\t\t\t[Y|N]: ");
                        ch = scanner.next().charAt(0);
                        if (ch == 'Y' || ch == 'y') {
                            System.out.print("\t\t\t\t\tEnter New User Name: \n\t\t\t\t\t");
                            fname = scanner.next();
                            break;
                        } else if (ch == 'N' || ch == 'n') {
                            fname = name;
                            break;
                        } else {
                            System.out.println("\t\t\t\t\tChoose Properly!\n");
                        }
                    } while (true);
                    
                    // Change DOB
                    System.out.print("\t\t\t\t\tWanna change DoB?\n\t\t\t\t\t[Y|N]: ");
                    ch = scanner.next().charAt(0);
                    if (ch == 'Y' || ch == 'y') {
                        System.out.println("\n\t\t\t\t\tEnter your Date of Birth:");
                        int d, m, y;
                        
                        do {
                            System.out.print("\t\t\t\t\tDay : ");
                            d = scanner.nextInt();
                            if (d < 1 || d > 31) {
                                System.out.println("\t\t\t\t\tReminder: You are a Human! Enter a valid Day.");
                            }
                        } while (d < 1 || d > 31);
                        date = Integer.toString(d);
                        
                        do {
                            System.out.print("\t\t\t\t\tMonth : ");
                            m = scanner.nextInt();
                            if (m < 1 || m > 12) {
                                System.out.println("\t\t\t\t\tReminder: You are a Human! Enter a valid Month.\n");
                            } else if (d > 29 && m == 2) {
                                System.out.println("\t\t\t\t\tReminder: Are you sure you born in Feb?\n\t\t\t\t\tRewrite your DOB\n");
                                d = 0; // Force re-entry of day
                            }
                        } while (m < 1 || m > 12 || (d > 29 && m == 2));
                        month = Integer.toString(m);
                        
                        do {
                            System.out.print("\t\t\t\t\tYear :  ");
                            y = scanner.nextInt();
                            if (y < 1950 || y > 2024) {
                                System.out.println("\t\t\t\t\tReminder: You are a Human! Enter a valid Year.\n\t\t\t\t\tLet's Begin Again!\n");
                                d = 0; // Force re-entry of day
                            } else if (!isLeap(y) && m == 2 && d > 28) {
                                System.out.println("\t\t\t\t\tReminder: You are a Human! Be logical.\n\t\t\t\t\tLet's Begin Again!\n");
                                d = 0; // Force re-entry of day
                            }
                        } while (y < 1950 || y > 2024 || (!isLeap(y) && m == 2 && d > 28));
                        year = Integer.toString(y);
                    }
                    
                    // Change mobile number
                    System.out.print("\t\t\t\t\tWanna change Mobile Number?\n\t\t\t\t\t[Y|N]: ");
                    ch = scanner.next().charAt(0);
                    if (ch == 'Y' || ch == 'y') {
                        String countryCode;
                        do {
                            System.out.print("\t\t\t\t\tEnter Country Code : ");
                            countryCode = scanner.next();
                            if (!valCode(countryCode)) {
                                System.out.println("\t\t\t\t\tPlease Enter a Valid Code.\n");
                            }
                        } while (!valCode(countryCode));
                        
                        String digit;
                        do {
                            System.out.print("\t\t\t\t\tEnter Mobile Number : ");
                            digit = scanner.next();
                            if (digit.length() != 10) {
                                System.out.println("\t\t\t\t\tEntered " + digit.length() + " Digits. Expecting 10, try again!\n");
                            } else if (!validNum(digit)) {
                                System.out.println("\t\t\t\t\tPlease enter a valid phone number!\n");
                            }
                        } while (digit.length() != 10 || !validNum(digit));
                        number = countryCode + digit;
                    }
                    
                    // Change email
                    System.out.print("\t\t\t\t\t\nWanna change Email?\n\t\t\t\t\t[Y|N]: ");
                    ch = scanner.next().charAt(0);
                    if (ch == 'Y' || ch == 'y') {
                        do {
                            System.out.println("\n\t\t\t\t\tEnter Email Address: ");
                            System.out.print("\t\t\t\t\t");
                            email = scanner.next();
                            if (!validMail(email)) {
                                System.out.println("\t\t\t\t\tPlease Re-enter a Valid Email Address\n");
                            }
                        } while (!validMail(email));
                    }
                    
                    file1.println(fname + " " + password + " " + number + " " + email + " " + date + " " + month + " " + year);
                } else {
                    file1.println(fname + " " + password + " " + number + " " + email + " " + date + " " + month + " " + year);
                }
            }
            
            System.out.println("\n\t\t\t\t\tSaving new details, Please wait . . .");
            System.out.print("\t\t\t\t\t");
            waiting();
            
            File oldFile = new File("files.txt");
            File newFile = new File("modify.txt");
            
            if (oldFile.delete()) {
                if (!newFile.renameTo(oldFile)) {
                    System.out.println("\n\t\t\t\t\tError renaming file");
                }
            } else {
                System.out.println("\n\t\t\t\t\tError deleting old file");
            }
            
            System.out.println("\n\n\t\t\t\t\tAccount Details Saved Successfully.");
            System.out.println("\t\t\t\t\tChanges will be shown after Re-login.");
            System.out.print("\n\t\t\t\t\t");
            new Scanner(System.in).nextLine(); // Wait for enter
            logout();
        } catch (IOException e) {
            System.out.println("\t\t\t\t\tError modifying account");
            System.out.print("\n\t\t\t\t\t");
            new Scanner(System.in).nextLine(); // Wait for enter
            menu();
        }
    }

    private void changePassword() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        
        System.out.println("\n\t\t\t\t ___________________________________________");
        System.out.println("\t\t\t\t|                                           |");
        System.out.println("\n\t\t\t\t|              CHANGE PASSWORD              |");
        System.out.println("\t\t\t\t|___________________________________________|");
        System.out.println("\n");
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("\t\t\t\t\tEnter your Old Password: ");
        String oldpass;
        Console console = System.console();
        if (console != null) {
            oldpass = new String(console.readPassword());
        } else {
            oldpass = scanner.next();
        }
        
        if (oldpass.equals(pass)) {
            System.out.print("\n\t\t\t\t\tEnter New Password: ");
            String newpass;
            if (console != null) {
                newpass = new String(console.readPassword());
            } else {
                newpass = scanner.next();
            }
            
            if (newpass.equals(oldpass)) {
                System.out.println("\n\t\t\t\t\tOld and New Password can't be same.");
            } else {
                System.out.print("\n\t\t\t\t\tConfirm New Password: ");
                String confirmpass;
                if (console != null) {
                    confirmpass = new String(console.readPassword());
                } else {
                    confirmpass = scanner.next();
                }
                
                if (!confirmpass.equals(newpass)) {
                    System.out.println("\n\t\t\t\t\tError! Password mismatch.\n\t\t\t\t\tPlease try again.");
                    try {
                        Thread.sleep(636);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    changePassword();
                } else {
                    newpass = encryption(newpass);
                    
                    try (Scanner file = new Scanner(new File("files.txt"));
                         PrintWriter file1 = new PrintWriter(new FileWriter("modify.txt"))) {
                        
                        while (file.hasNext()) {
                            fname = file.next();
                            password = file.next();
                            number = file.next();
                            email = file.next();
                            date = file.next();
                            month = file.next();
                            year = file.next();
                            
                            if (name.equals(fname) && pass.equals(oldpass)) {
                                file1.println(fname + " " + newpass + " " + number + " " + email + " " + date + " " + month + " " + year);
                            } else {
                                file1.println(fname + " " + password + " " + number + " " + email + " " + date + " " + month + " " + year);
                            }
                        }
                        
                        System.out.println("\n\t\t\t\t\tChanging Password, Please wait . . .");
                        System.out.print("\t\t\t\t\t");
                        waiting();
                        System.out.println("\n\n\t\t\t\t\tPassword changed successfully.");
                        System.out.println("\n\t\t\t\t\tChanges will be shown after Re-login.");
                        
                        File oldFile = new File("files.txt");
                        File newFile = new File("modify.txt");
                        
                        if (oldFile.delete()) {
                            if (!newFile.renameTo(oldFile)) {
                                System.out.println("\n\t\t\t\t\tError renaming file");
                            }
                        } else {
                            System.out.println("\n\t\t\t\t\tError deleting old file");
                        }
                        
                        logout();
                    } catch (IOException e) {
                        System.out.println("\n\t\t\t\t\tError changing password");
                        System.out.print("\n\t\t\t\t\t");
                        scanner.nextLine(); // Wait for enter
                        menu();
                    }
                }
            }
        } else {
            System.out.println("\n\t\t\t\t\tError! Wrong Password");
        }
        
        System.out.print("\n\t\t\t\t\t");
        scanner.nextLine(); // Wait for enter
        menu();
    }

    private void deleteAccount() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        
        System.out.println("\n\t\t\t\t ___________________________________________");
        System.out.println("\t\t\t\t|                                           |");
        System.out.println("\n\t\t\t\t|              DELETE ACCOUNT               |");
        System.out.println("\t\t\t\t|___________________________________________|");
        System.out.println("\n");
        System.out.println("\t\t\t\t\tAre you sure want to delete account ?");
        System.out.println("\t\t\t\t\tThis action can't be undone!");
        System.out.print("\t\t\t\t\tY/N: ");
        
        Scanner scanner = new Scanner(System.in);
        char x = scanner.next().charAt(0);
        
        if (x == 'y' || x == 'Y') {
            try (Scanner file = new Scanner(new File("files.txt"));
                 PrintWriter file1 = new PrintWriter(new FileWriter("modify.txt"))) {
                
                while (file.hasNext()) {
                    fname = file.next();
                    password = file.next();
                    number = file.next();
                    email = file.next();
                    date = file.next();
                    month = file.next();
                    year = file.next();
                    
                    String dec_password = decrypt(password);
                    if (name.equals(fname) && pass.equals(dec_password)) {
                        // Skip writing this account (effectively deleting it)
                    } else {
                        file1.println(fname + " " + password + " " + number + " " + email + " " + date + " " + month + " " + year);
                    }
                }
                
                System.out.println("\n\t\t\t\t\tDeleting Account . . .");
                System.out.print("\t\t\t\t\t");
                waiting();
                System.out.println("\n\n\t\t\t\t\tAccount Deleted Successfully.");
                System.out.print("\n\t\t\t\t\tLogging you out . . .\n\t\t\t\t\t");
                waiting();
                
                File oldFile = new File("files.txt");
                File newFile = new File("modify.txt");
                
                if (oldFile.delete()) {
                    if (!newFile.renameTo(oldFile)) {
                        System.out.println("\n\t\t\t\t\tError renaming file");
                    }
                } else {
                    System.out.println("\n\t\t\t\t\tError deleting old file");
                }
                
                logout();
            } catch (IOException e) {
                System.out.println("\t\t\t\t\tError deleting account");
                System.out.print("\n\t\t\t\t\t");
                scanner.nextLine(); // Wait for enter
                menu();
            }
        } else if (x == 'N' || x == 'n') {
            System.out.println("\n\t\t\t\t\tTaking you back to Main Menu");
            System.out.print("\t\t\t\t\t");
            waiting();
            try {
                Thread.sleep(636);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            menu();
        } else {
            System.out.println("\t\t\t\t\tPlease select a valid option.");
            System.out.print("\t\t\t\t\t");
            scanner.nextLine(); // Wait for enter
            deleteAccount();
        }
    }

    private void logout() {
        fname = "";
        password = "";
        name = "";
        pass = "";
        date = "";
        month = "";
        year = "";
        email = "";
        number = "";
        enc = "";
        
        System.out.println("\n\t\t\t\t\tLogging Out . . .");
        System.out.print("\t\t\t\t\t");
        waiting();
        homepage();
    }

    public static void main(String[] args) {
        LoginSystem x = new LoginSystem();
        x.homepage();
    }
}
