package menu;

import database.Mongo;
import todo.Todo;
import utils.Prints;
import utils.Scan;

import java.util.ArrayList;

public class Menu {
    Mongo mongo;
    String input;
    public Menu() {
        mongo = new Mongo("mongodb://localhost:27017", "Todos", "Todo");
        start();
    }

    private void start() {
        System.out.println(Prints.welcomeText);
        input = Scan.getString(Prints.input);
        switchStart();
    }

    private void switchStart() {
        switch (input) {
            case "1" -> create();
            case "2" -> read();
            case "3" -> updateDone();
            case "4" -> updateText();
            case "5" -> listAll();
            case "6" -> delete();
            case "7" -> System.exit(0);
            default -> {
                System.out.println(Prints.unvalid);
                input = Scan.getString(Prints.input);
                switchStart();
            }
        }
    }


    private void create() {
        System.out.println(Prints.create);
        String text = Scan.getString(Prints.input);

        Todo todo = new Todo();
        todo.setText(text);
        todo.setDone(false);

        mongo.create(todo);

        System.out.println("Todo har skapats.");
        start();
    }
    private void read() {
        String objectID = Scan.getString(Prints.getID);

        try {
            Todo todo = mongo.read(objectID);
            if (todo != null) {
                System.out.println("Todo:\n" + todo.getText());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Todo hittades inte.");
        }


        start();
    }
    private void updateDone() {
        String objectID = Scan.getString(Prints.getID);

        mongo.update(objectID);

        System.out.println("Todo har uppdaterats.");
        start();
    }

    private void updateText() {
        String objectID = Scan.getString(Prints.getID);
        String updateText = Scan.getString(Prints.input);
        mongo.updateText(objectID, updateText);
        System.out.println("Todo har uppdaterats.");
        start();
    }
    private void listAll() {
        ArrayList<Todo> todos = mongo.getAll();

        if (todos.isEmpty()) {
            System.out.println("Det finns inga Todos.");
        } else {
            System.out.println("Todos:");
            for (Todo todo : todos) {
                System.out.println(todo.getId() + ": " + todo.getText());
            }
        }

        start();
    }
    private void delete() {
        String objectID = Scan.getString(Prints.getID);

        mongo.delete(objectID);

        System.out.println("Todo har raderats.");
        start();
    }
}

