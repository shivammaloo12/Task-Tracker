📌 Task Tracker CLI

Task Tracker is a simple Command Line Interface (CLI) application to manage your tasks.
It lets you add, update, delete, and track tasks with statuses: todo, in-progress, and done.
All tasks are stored in a tasks.json file for persistence across sessions.

✨ Features

➕ Add tasks

✏️ Update task descriptions

❌ Delete tasks

🔄 Mark tasks as in-progress or done

📃 List tasks by status (todo, in-progress, done) or list all

💾 Persistent storage using JSON file

🛠️ Tech Stack

Java (Core Java, no external libraries)

JSON file storage (using Java File I/O)

📂 Project Structure
task-cli/
 ├── model/
 │    └── Task.java
 ├── repository/
 │    └── TaskRepository.java
 ├── service/
 │    └── TaskService.java
 ├── TaskCLI.java
 └── tasks.json   # auto-created on first run

🚀 Usage
1️⃣ Compile

From project root:

javac -d target/classes src/**/*.java

2️⃣ Run

Run with classpath & fully qualified class name:

java -cp target/classes com.Shivam.TaskCLI <command> [args...]

3️⃣ Examples
# Add tasks
java -cp target/classes com.Shivam.TaskCLI add "Buy groceries"
java -cp target/classes com.Shivam.TaskCLI add "Cook dinner"

# Update a task
java -cp target/classes com.Shivam.TaskCLI update 1 "Buy groceries and snacks"

# Mark as in-progress / done
java -cp target/classes com.Shivam.TaskCLI mark-in-progress 1
java -cp target/classes com.Shivam.TaskCLI mark-done 1

# Delete a task
java -cp target/classes com.Shivam.TaskCLI delete 2

# List all tasks
java -cp target/classes com.Shivam.TaskCLI list

# List by status
java -cp target/classes com.Shivam.TaskCLI list todo
java -cp target/classes com.Shivam.TaskCLI list in-progress
java -cp target/classes com.Shivam.TaskCLI list done

📖 Task Properties

Each task has:

id → Unique identifier

description → Task text

status → todo | in-progress | done

createdAt → Timestamp when created

updatedAt → Timestamp when last updated

📊 Sample tasks.json
[
  {
    "id": 1,
    "description": "Buy groceries",
    "status": "todo",
    "createdAt": "2025-10-04T23:55:12.123",
    "updatedAt": "2025-10-04T23:55:12.123"
  },
  {
    "id": 2,
    "description": "Cook dinner",
    "status": "done",
    "createdAt": "2025-10-04T23:56:01.456",
    "updatedAt": "2025-10-04T23:58:21.789"
  }
]

🧪 Testing in IntelliJ IDEA

Right-click TaskCLI.java → Run 'TaskCLI.main()'

Go to Run → Edit Configurations... → Program arguments

Example: add "Buy groceries"

Example: list

Run again to test different commands.

📦 Future Improvements

Export tasks to CSV/Excel

Search/filter tasks

Colorized CLI output

Create a JAR for easier execution (java -jar task-cli.jar list)

https://roadmap.sh/projects/task-tracker
