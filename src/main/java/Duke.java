import javax.script.ScriptContext;
import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printWelcomeMessage();
        decodingUserCommands();
    }

    public static void printLine(){
        System.out.println("----------------------------------"
                +"-------------------------------------------");
    }
    public static void printBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void printGreeting(){
        String greeting ="Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.print(greeting);
        printLine();
    }

    /**
     * Reads user input and adds it to the task list accordingly
     * Based on the input, new task of different types (e.g todo, event, deadline)
     * will be created accordingly
     */
    private static void decodingUserCommands() {
        String input;
        int count = 0;
        ArrayList<String> list = new ArrayList<String>();
        Task tasks[] = new Task[100];
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while(!input.equals("bye")){
            if(input.equals("list")){
                if(list.size()==0){
                    System.out.println("The list is empty");
                }else {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i+1
                                + ".["
                                + tasks[i].getType()
                                + "]"
                                +"[" + tasks[i].getStatusIcon() + "] "
                                + list.get(i));
                    }
                    printLine();
                }
            }else if(input.contains("done")){
                if(list.size()==0) {
                    System.out.println("The list is empty");
                }
                else {
                    String inputNumber;
                    int taskNumber;
                    inputNumber = input.replaceAll("[^0-9]", "");//replace all non-number with space
                    taskNumber = Integer.parseInt(inputNumber);
                    if(taskNumber > list.size()){
                        System.out.println("The task number is out of bound! Please type \"list\"");
                        printLine();
                    }else if(tasks[taskNumber-1].isDone==true){
                        System.out.println("Chill man, this task is completed!");
                        printLine();
                    }else {
                        tasks[taskNumber - 1].makeAsDone();
                        System.out.println("Nice! I've marked this task as done:\n"
                                + "  ["
                                + tasks[taskNumber - 1].getType()
                                +  "]"
                                + "["
                                + tasks[taskNumber - 1].getStatusIcon()
                                + "] "
                                + tasks[taskNumber - 1].getDescription());
                        printLine();
                    }
                }
            } else if(input.contains("todo")){
                input = input.replace("todo ", "");
                list.add(input);
                tasks[count] = new ToDo(input);
                count = printTaskAdded(count, list, tasks);
            }else if(input.contains("event")){
                input = input.replace("event ", "");
                int positionIndex = input.indexOf("/");
                String date = input.substring(positionIndex+1, input.length());
                input = input.substring(0, positionIndex);
                tasks[count] = new Event(input, date);
                list.add(tasks[count].getDescription());
                count = printTaskAdded(count, list, tasks);
            }else if(input.contains("deadline")) {
                input = input.replace("deadline ", "");
                int positionIndex = input.indexOf("/");
                String date = input.substring(positionIndex + 1, input.length());
                input = input.substring(0, positionIndex);
                tasks[count] = new Deadline(input, date);
                list.add(tasks[count].getDescription());
                count = printTaskAdded(count, list, tasks);
            } else{
                System.out.println("Please input valid task description......");
                printLine();
            }
            input = in.nextLine();
        }
        printBye();
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printGreeting();
        System.out.println("Available Commands:\n"
                +"1. list\n"
                +"2. done (e.g done 1)\n"
                +"3. todo (e.g todo homework)\n"
                +"4. event (e.g event meeting /monday 2pm)\n"
                +"5. deadline (e.g deadline project /monday 2359)\n"
                +"6. bye\n"
                +"p.s all other inputs will be added to the list\n"
                +"Please enter your command:");
        printLine();
    }

    private static int printTaskAdded(int count, ArrayList<String> list, Task[] tasks) {
        System.out.println("Got it. I've added this task: " );
        System.out.println("  ["
                + tasks[count].getType()
                + "]"
                + "[" + tasks[count].getStatusIcon()
                + "] "
                + list.get(count));
        System.out.println("Now you have " + list.size() +" tasks in the list.");
        printLine();
        count++;
        return count;
    }
}
