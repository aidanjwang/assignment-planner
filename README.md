Assignment-Planning/Anti-Procrastination System (APS)

Overview
	A program that helps users plan when to complete assignments. Allows users to add and remove assignments, which are broken up into sequential tasks, and generates balanced to-do lists for each day to help the user complete assignments in small chunks.

Detailed Features
1.	Initializing: Program initializes by asking user to fill in settings, which consist of what subjects (assignment categories) the user has and how many hours of worktime user has for each day of the week.
2.	Adding assignments: User can add an assignment with a specified due date and subject. When it is added, the program asks user to break up the assignment into sequential tasks, and asks for estimated worktimes for each task.
3.	Viewing assignments/tasks: User can view tasks and assignments in subject form or list form.
a.	Subject form: assignments are listed by subject, and each assignment is broken up into sequential tasks. Each assignment lists due date, and each task lists estimated time.
b.	List form: The program always carries lists of tasks to be completed on each day until all tasks are completed. This data can be viewed by the user upon request.
i.	An assignment’s tasks will always be assigned to be completed before the assignment’s due date.
ii.	Each workday always contains tasks with a total worktime equal to or slightly above the user’s set worktime. If worktime needs to be more than the user’s set worktime to meet due dates, the program will allocate more tasks.
iii.	Each day between the present day and the due date for a given assignment will have as balanced distribution of that assignment’s tasks as possible.
iv.	For example, if it is Monday and the user has 10 hours of work due on Wednesday, and Monday is set to 4 hours of worktime and Tuesday is set to 6 hours, then the program will hold 4 hours’ worth of tasks on Monday and 6 hours’ worth of tasks on Tuesday. If one of the assignments is 4 hours total, 2 hours will be on Monday and 2 hours will be on Tuesday, assuming this is possible with the given due dates.
v.	When assignments are added or removed, the program automatically updates the distribution of tasks among days. When the program starts, it automatically updates the distribution of tasks (i.e. deletes dates that have passed since last time program was run).

Commands
User runs the system, which accepts a sequence of commands until the user exits the program. Between sessions of running the system, data is serialized. Commands follow this syntax (BNF grammar):

Initializing:
<initialize statement> ::= “subjects” <new subject>,+ “and” “daily” “hours” <time> “,” <time> “,” <time> “,” <time> “,” <time> “,” <time> “,” <time> “;”
<new subject> ::= <name>

Commands:
<program> ::= <statement>*
<statement> ::=
	<add statement>
	| <remove statement>
	| <view statement>
	| <exit statement>
<add statement> ::= “add” <subject> <name> “due” <date> “with” “tasks” <tasks> “;”
<tasks> ::= <task>,+
<task> ::= <literal> “(“ <time> “)”
<remove statement> ::= “remove” <subject> <name> “due” <date> “;”
<view statement> ::= “view” <view clause>
<view clause> :: =
	“today” “;”
	| “all” “;”
	| “subjects” “;”
<exit statement> ::= “exit” “;” | “quit” “;”
<subject> ::= <name>
<name> denotes a sequence of letters, digits, and underscores that does not start with a digit.
<literal> denotes a sequence of zero or more characters other than ends-of-lines, commas, or single quotes (apostrophes), surrounded by single quotes.
<date> denotes a date that follows the format MM/dd/yyyy.
<time> denotes a number of hours of time, a nonnegative decimal number.

Implemented:
1.	<add statement> allows user to add an assignment and its tasks.
2.	<remove statement> allows user to remove an assignment.
3.	“view” “today” “;” prints today’s to-do list
4.	“view” “all” “;” prints all to-do lists.
5.	“view” “subjects” “;” prints all assignments in subject form.
6.	<exit statement> saves changes and exits the program.

Future implementations:
1.	edit settings: allows user to edit settings (subjects or daily work times).
2.	edit assignment: allows user to edit an existing assignment.
3.	store: stores lists into a csv file in list format (Gantt chart) or subject format.
4.	load: loads lists from a csv file.

Internal Structure
Classes:
1.	APS class—contains subjects, linked list of Dates, and most commands.
2.	Subject object—represents a subject category for assignments and contains its Assignments.
3.	Assignment object—represents an assignment and contains its Subject and Tasks.
4.	Task object—represents a task and contains its Assignment and all relevant information for each task.
5.	Date object—a linked list node which represents a single date. Contains its Tasks.
6.	Main class— runs program, accepting commands until program exits.
7.	CommandInterpreter class—converts user commands into program commands and sometimes prompts user; uses recursive descent.
8.	Tokenizer—converts user input into tokens.
9.	APSException—RunTimeExceptions for use throughout the system.

Data Structures:
•	The APS class holds a LinkedHashSet of Subjects; each Subject holds a TreeSet of Assignments; each Assignment holds a LinkedHashSet of Tasks, which are iterated over to construct the list forms. Additionally, the APS class holds a TreeSet of all Assignments, which are iterated over to construct the list forms.
•	The APS class also holds an ArrayList of Date nodes, each representing a date. Each Date contains a LinkedHashSet of Tasks. Each Date also contains a running total worktime for that day.
