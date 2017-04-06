import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
  private final static String DATA = "src/data.csv";
  private final static String REGEX = ";";
  private static TodoList myTodos;

  public static void main(String[] args) {
    myTodos = new TodoList();
    List<String> rawLines = readData();
    splitLines(rawLines);

    if (args.length == 1 && args[0].equals("-l")) {
      myTodos.list();
    }
    else if (args.length == 2 && args[0].equals("-a")) {
      myTodos.add(new Task("notdone", args[1]));
    }
    else if (args.length == 2 && args[0].equals("-r")) {
      myTodos.delete(Integer.valueOf(args[1]));
    }
    else if () {

    }
    else {
      System.out.print("Python Todo application\n =======================\n Command line arguments:\n -l   Lists all the tasks\n -a   Adds a new task\n -r   Removes an task\n -c   Completes an task");
    }

    writeData(myTodos.saveToFile());
  }

  public static void splitLines(List<String> toSplit) {
    for (String line : toSplit) {
      String[] buffer = line.split(REGEX);
      myTodos.add(new Task(buffer[0], buffer[1]));
    }
  }

  private static List<String> readData() {
    List<String> readLines = new ArrayList<>();
    Path path = Paths.get(DATA);
    try {
      readLines = Files.readAllLines(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return readLines;
  }

  private static void writeData(List<String> data) {
    Path path = Paths.get(DATA);
    try {
      Files.write(path, data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
