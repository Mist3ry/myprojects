import java.util.*;
public class game {
    public static void main(String args[]){
        System.out.println("Let`s play a game called \"Odds and Evens\"");
        greeding();
    }

    //The project represtnt simple game vs computer. First of all you decide what you will play at that game even or not even. 
    //Computer takes the opposite. Then you guessing numbers and the computer in turn also generates a random number up to 6. 
    //Numbers are added together and it is checked whether this number is even or not. 
    //If you guessed right it and the amount came out of the one you asked earlier, you won, else you lose.
    public static void greeding() {
        Scanner action = new Scanner(System.in);
        System.out.print("What is your name? ");
        String name = action.next();
        System.out.print("Hi " + name + ", Which do you choose (O)dds or (E)vens ");
        String number = action.next();
        if(number.equalsIgnoreCase("o")) System.out.println(name + " has picked odds! The computer will be evens.");
        else System.out.println(name + " has picked evens! The computer will be odds.");
        System.out.println("-------------------------------------------------");

        System.out.print("How many \"fingers\" do you put out? ");
        Scanner finger = new Scanner(System.in);
        int fingers = finger.nextInt();
        Random rand = new Random();
        int computer = rand.nextInt(6);
        System.out.println("The computer plays number \"fingers\". " + computer);
        System.out.println("-------------------------------------------------");
        int sum = fingers + computer;
        System.out.println(fingers + " + " + computer + " = " + sum);
        if(sum%2==0) {
            System.out.println(sum + " is ...evens.");
            if(number.equalsIgnoreCase("o")) {
                System.out.println("That means computer wins! :)");
            }
            else {  System.out.println("That means" + name + " wins! :)"); }
        }
        else if(sum%2==1){
            System.out.println(sum + " is ...odds.");
            if((number.equalsIgnoreCase("o"))) {
                System.out.println("That means " + name + " wins! :)");
            }
            else { System.out.println("That means computer wins! :)");}
        }
        System.out.println("-------------------------------------------------");

    }

}
