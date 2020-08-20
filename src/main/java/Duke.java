import javax.script.ScriptContext;
import java.util.Scanner;

public class Duke {
    public static void printLine(){
        System.out.println("-------------------------------------------");
    }
    public static void printBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void printGreeting(){
        String greeting ="Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.print(greeting);
        printLine();
        //printBye();
        //printLine();
    }
    public static void printEcho(String input){

    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printGreeting();
        System.out.println("Please enter your command:");
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while(true){
            if(input.equals("bye")){
                break;
            }
            System.out.println(input + "~~");
            input = in.nextLine();
        }
        printBye();
    }
}
