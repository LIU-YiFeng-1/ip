import javax.script.ScriptContext;
import java.util.ArrayList;
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
        System.out.println("Available Commands:\n"
                +"1.list\n"
                +"2.bye\n"
                +"p.s all other inputs will be added to the list\n"
                +"Please enter your command:");
        printLine();
        String input;
        ArrayList<String> list = new ArrayList<String>();
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while(!input.equals("bye")){
            if(input.equals("list")){
                if(list.size()==0){
                    System.out.println("The list is empty");
                    printLine();
                }
                for(int i=0; i < list.size(); i++){
                    System.out.println(i+1 + ". " + list.get(i));
                }
                printLine();
            }else{
                list.add(input);
                System.out.println("added: " + input);
                printLine();
            }
            input = in.nextLine();
        }
        printBye();
    }
}
