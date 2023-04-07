import java.util.Scanner;
import java.io.*;

class passgen {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String args[]) throws FileNotFoundException {
        try {
            System.out.println(ANSI_RED + " _______  _______  _______  _______  _______  _______  __    _ ");
            System.out.println("|       ||   _   ||       ||       ||       ||       ||  |  | |");
            System.out.println("|    _  ||  |_|  ||  _____||  _____||    ___||    ___||   |_| |");
            System.out.println("|   |_| ||       || |_____ | |_____ |   | __ |   |___ |       |");
            System.out.println("|    ___||       ||_____  ||_____  ||   ||  ||    ___||  _    |");
            System.out.println("|   |    |   _   | _____| | _____| ||   |_| ||   |___ | | |   |");
            System.out.println("|___|    |__| |__||_______||_______||_______||_______||_|  |__|" + ANSI_RESET);
            System.out.println("- By Pargat Singh");
            System.out.println("");
            System.out.println("###########################################################################");
            System.out.println("Hello Friend ! Welcome to Passgen, the password genreator for brute force attacks ");
            System.out.println(
                    "Simply enter the credentials that you think could be the password of the person followed by a semicoln (;) ");
            System.out.println("For example: flliper;2c;name;123;2019;");
            Scanner sc = new Scanner(System.in); // Scanner Class for taking input
            String in = sc.nextLine(); // user input
            int l = count(in); // Stores the value for the number of words entered
            String arr[] = new String[l];
            for (int i = 0; i < l; i++) // Loop to seprate the input words
            {
                arr[i] = in.substring(0, in.indexOf(';'));
                in = in.substring(in.indexOf(';') + 1);

            }
            System.out.println("There are " + poss(l) + " possible passwords");
            System.out.println("Do you want to save it? (Y/N)");
            char check = sc.next().charAt(0);
            System.out.println("Output saved to dict.txt file");
            PrintStream output = new PrintStream(new File("dict.txt"));
            // Redirect System.out to the file
            System.setOut(output);
            if (check == 'y' || check == 'Y') {
                crack(arr, l);
            }
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static int count(String s) // Counts the number of words
    {
        int a = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ';')
                a++;

        }
        return a;
    }

    static int poss(int a) // Finds the number of possible permutations
    {
        int p = 0;
        for (int i = 1; i <= a; i++) {
            p = p + (int) (Math.pow(a, i));
        }
        return p;
    }

    static void generate(String[] arr, int i, String s, int length) // recursive function
    {
        if (i == 0) {
            System.out.println(s);
            return;
        }
        for (int j = 0; j < length; j++) {
            String combine = s + arr[j]; // The combine string
            generate(arr, i - 1, combine, length);
        }
        return;
    }

    static void crack(String[] arr, int length) // generates all permutations
    {
        for (int i = 1; i <= length; i++) {
            generate(arr, i, "", length);
        }
    }
}