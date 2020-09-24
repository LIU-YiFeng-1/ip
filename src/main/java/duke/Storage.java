//package duke;
//
//import duke.messages.Messages;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//
//import static duke.Duke.RECORD_PATH;
//
//public class Storage {
//    //importing pastRecord before reading user commands
//    public static void saveData() {
//        Messages.printDoneLoadingMessage();
//        File pastRecord = new File(RECORD_PATH);
//        try {
//            Scanner scanner = new Scanner(pastRecord);
//            while (scanner.hasNextLine()) {
//                int listSize1 = listSize;
//                String content = scanner.nextLine();
//                int pastTaskStatus = Integer.parseInt(content.substring(INDEX_PAST_RECORD_STATUS, INDEX_PAST_RECORD_STATUS + 1));
//                if (content.startsWith("T")) { //pastRecord has a todo task and add the todo to the task list
//                    String todoDescription = "todo " + content.substring(INDEX_PAST_RECORD_STATUS + 4, content.length());
//                    int listSize2 = listSize1;
//                    if (todoDescription.equals("todo") || todoDescription.equals("todo ")) {
//                        System.out.println("\u2639 " + "OOPS!!! The description of a todo cannot be empty.");
//                        Messages.printLine();
//                    } else {
//                        todoDescription = todoDescription.replace("todo ", "");
//                        list.add(todoDescription);
//                        tasks[listSize2] = new ToDo(todoDescription);
//                        listSize2 = printAddTaskMessage(listSize2, list, tasks);
//                    }
//                    listSize1 = listSize2;
//                    if (pastTaskStatus == 1) { //if pastTaskStatus is 1, mark it done
//                        tasks[listSize1 - 1].makeAsDone();
//                    }
//                } else if (content.startsWith("E")) { //pastRecord has a event task and add the event to the task list
//                    String eventDescription = "event " + content.substring(INDEX_PAST_RECORD_STATUS + 4, content.length() - 1);
//                    eventDescription = eventDescription.replace("(", "/");
//                    String input1 = eventDescription;
//                    int listSize2 = listSize1;
//                    try {
//                        input1 = input1.replace("event ", "");
//                        int positionIndex = input1.indexOf("/");
//                        String date = input1.substring(positionIndex + 1, input1.length());
//                        input1 = input1.substring(0, positionIndex);
//                        tasks[listSize2] = new Event(input1, date);
//                        list.add(tasks[listSize2].getDescription());
//                        listSize2 = printAddTaskMessage(listSize2, list, tasks);
//                    } catch (StringIndexOutOfBoundsException s) {
//                        System.out.println("\u2639 " + "OOPS!!! The description of an event cannot be empty.");
//                        Messages.printLine();
//                    }
//                    listSize1 = listSize2;
//                    if (pastTaskStatus == 1) { //if pastTaskStatus is 1, mark it done
//                        tasks[listSize1 - 1].makeAsDone();
//                    }
//                } else if (content.startsWith("D")) { //pastRecord has a deadline task and add the deadline to the task list
//                    String deadlineDescription = "event " + content.substring(INDEX_PAST_RECORD_STATUS + 4, content.length() - 1);
//                    deadlineDescription = deadlineDescription.replace("(", "/");
//                    String input1 = deadlineDescription;
//                    int listSize2 = listSize1;
//                    try {
//                        input1 = input1.replace("event ", "");
//                        int positionIndex = input1.indexOf("/");
//                        String date = input1.substring(positionIndex + 1, input1.length());
//                        input1 = input1.substring(0, positionIndex);
//                        tasks[listSize2] = new Event(input1, date);
//                        list.add(tasks[listSize2].getDescription());
//                        listSize2 = printAddTaskMessage(listSize2, list, tasks);
//                    } catch (StringIndexOutOfBoundsException s) {
//                        System.out.println("\u2639 " + "OOPS!!! The description of an event cannot be empty.");
//                        Messages.printLine();
//                    }
//                    listSize1 = listSize2;
//                    if (pastTaskStatus == 1) { //if pastTaskStatus is 1, mark it done
//                        tasks[listSize1 - 1].makeAsDone();
//                    }
//                }
//                content = "";
//                listSize = listSize1;
//            }
//            Messages.printDoneLoadingMessage();
//        } catch (FileNotFoundException | FileNotFoundException e) {
//            System.out.println("There is no past record! You are a 1st time user");
//            Messages.printLine();
//        }
//    }
//
////
////    when switching
////    off the
////    programme,
////    export the
////    tasks as
////    output text
////    and print
////    bye message
////    File file = new File(RECORD_PATH);
////    FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
////    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
////            if(list.size()==0)
////
////    {
////        bufferedWriter.write("");
////    } else
////
////    {
////        for (int i = 0; i < list.size(); i++) {
////            if (tasks[i].getStatusIcon() == "\u2713") {
////                bufferedWriter.write(tasks[i].getType() + " | " + "1" + " | " + list.get(i) + "\n");
////            } else {
////                bufferedWriter.write(tasks[i].getType() + " | " + "0" + " | " + list.get(i) + "\n");
////            }
////        }
////    }
////            bufferedWriter.close();
////            Messages.printBye();
////}
//}
