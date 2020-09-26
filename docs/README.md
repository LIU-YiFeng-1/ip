# User Guide
_Duke_ is a program for users to store different types of tasks. _Duke_ is a simple client program and users can interact with _Duke_ by typing commands in the command prompt.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. After the importing is complete, locate the `src/main/java/duke/Duke.java` file, right-click it, and choose `run Duke.main()`. If the setup is correct, you should see something like the below:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   
   Hello! I'm Duke
   Importing data from previous record
   .
   .
   .
   .
   please wait :)
   -----------------------------------------------------------------------------
   Done loading past record onto your task list!
   Updating task status based on past record
   updating...
   .
   .
   .
   .
   Done updating! Please type "List" to check!
   -----------------------------------------------------------------------------
   Type [help] to view available commands!
   -----------------------------------------------------------------------------
   
 ## Running jar file on Command Prompt
 If you are running the jar file on Command Prompt, please use the following command:

 chcp 65001
 
 java -Dfile.encoding=UTF-8 -jar .\iP.jar
 
## Features for _Duke_
The following features are available for _Duke_:
1. Keep track of todo, event and deadline types of tasks.
2. List down all available tasks stored in the task list.
3. Find specific events based on input keyword.
4. Save all available tasks into `Duke_output.txt`.
5. Extract data from `Duke_output.txt` and update the task list automatically.

## Function List
The table below shows the available commands in _Duke_ and the corresponding function description.

Function Name | Function Description
------------ | -------------
bye | terminates the program and saves all available tasks into `~/data/duke.txt`
deadline | creates a deadline task with the given task description and task date
delete | deletes a specific task from the task list based on the given task index
done | mark a specific task from the task list as done, based on the given task index
event | creates an event task with the given task description and task date
find | prints out all tasks that contains the given keyword
help | prints out the available commands in _Duke_
list | prints out all available tasks currently stored in the task list
save | saves all available tasks into `Duke_output.txt`
todo | creates a todo task with the given task description
## Available Commands in _Duke_
### Notes on command format
`command` - this is the exact command that you need to type in the terminal

`<must have content>` - this is the parameter that you must supply to the program. E.g `todo <task description>`

`[may have content]` -this is the parameter that you may want to supply to the program. E.g `event <task description> /<task date> [task time]`
#### Bye
Terminates the program and saves all available tasks into `~/data/duke.txt`
##### Syntax
`bye`
#### deadline 
Creates a deadline task with the given task description and task date
##### Syntax
`deadline <task description> /<task date> [task time]`

`<task description>` - description of the task that has a deadline. E.g project submission

`<task date>` - date of the deadline that is due. E.g sunday

`[task time]` - exact time of the deadline that is due. E.g 2359
##### Example
`deadline project submission /3 Oct` - creates a deadline task called "project submission" that is due on 3 Oct

`deadline project submission /3 Oct 2359` - - creates a deadline task called "project submission" that is due on 3 Oct, 2359
#### delete 
Deletes a specific task from the task list based on the given task index
##### Syntax
`delete <index>`

`<index>` - the corresponding task index which you want to delete
##### Example
`delete 4` - deletes the task with index 4 from your list of tasks
#### done 
Mark a specific task from the task list as done, based on the given task index
##### Syntax
`done <index>`

`<index>` - the corresponding task index which you want to mark as done
##### Example
`done 3` - marks the task with index 3 from your list of tasks as done
#### event 
Creates an event task with the given task description and task date
##### Syntax
`event <task description> /<task date> [task time]`

`<task description>` - description of the task that is happening on certain date. E.g CNY celebration

`<task date>` - date of the event that is happening. E.g 13 Feb

`[task time]` - exact time of the event that is happening. E.g 2359
##### Example
`event CNY celebration /13 Feb` - creates an event task called "CNY celebration" that is happening on 13 Feb

`event CNY celebration /13 Feb 10pm` - creates an event task called "CNY celebration" that is happening on 13 Feb 10pm
#### help 
Prints out the available commands in _Duke_
##### Syntax
`help`
#### list 
Prints out all available tasks currently stored in the task list
##### Syntax
`list`
##### Example
`list`
#### save 
Saves all available tasks into `Duke_output.txt`
##### Syntax
`save`
##### Example
`save`
#### todo 
Creates a todo task with the given task description
##### Syntax
`todo <task description>`

`<task description>` - description of the task that needs to do

##### Example
`todo homework` - creates a todo task called "homework" 
