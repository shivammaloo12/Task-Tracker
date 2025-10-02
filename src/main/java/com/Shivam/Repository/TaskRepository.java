package com.Shivam.Repository;

import com.Shivam.Model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

        private static final String FILE_PATH="tasks.json";


        public List<Task> loadTask() throws IOException {
            List<Task> tasks=new ArrayList<>();
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("tasks.json file not found. Starting with an empty task list.");
                return tasks;
            }

            try(BufferedReader reader= new BufferedReader(new FileReader(file))){
                StringBuilder jsonBuilder=new StringBuilder();
                String line;

                while((line=reader.readLine())!=null){
                    jsonBuilder.append(line);
                }
                String json=jsonBuilder.toString();

                if(!json.isEmpty()){
                    System.out.println("Empty");
                    return tasks;
                }

                json =json.substring(1,json.length()-1);

                String[] objects = json.split("\\},\\s*\\{");

                for (String obj:objects){
                    if (!obj.startsWith("{")) obj = "{" + obj;
                    if (!obj.endsWith("}")) obj = obj + "}";

                    Task task = parseTask(obj);
                    if (task != null) {
                        tasks.add(task);
                    }

                }
            }catch (IOException e){
                System.out.println("Error reading tasks: " + e.getMessage());
            }
            return tasks;

        }

        private Task parseTask(String obj){
            obj=obj.substring(1,obj.length()-1);

            Task task=new Task();

            String[] fields = obj.split(",\"");

            for (String field:fields){
                String[] keyValue = field.replace("\"", "").split(":", 2);
                if (keyValue.length < 2) continue;

                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                switch (key){
                    case "id":
                        task.setId(Integer.parseInt(value));
                        break;
                    case "description":
                        task.setDescription(value);
                        break;
                    case "status":
                        task.setStatus(value);
                        break;
                    case "createdAt":
                        task.setCreatedAt(value);
                        break;
                    case "updatedAt":
                        task.setUpdatedAt(value);
                        break;
                }
            }

            return task;
        }
}
