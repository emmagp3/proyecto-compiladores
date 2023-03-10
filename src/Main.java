import automata.Automata;
import automata.AutomataDefinition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

  static boolean hasErrors = false;
  public static void main(String[] args) throws IOException {
    /* if (args.length > 1) {
      System.out.println("Correct use");
      System.exit(64);
    }
    else if (args.length == 1) {
      runFile(args[0]);
    }
    else {
      runPrompt();
    } */
    String word = "01010";
    System.out.println(AutomataDefinition.automata.isValid(word));
  }

  private static void runFile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes));
    if (hasErrors) {
      System.exit(65);
    }
  }

  private static void runPrompt() throws IOException {
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);

    for(;;) {
      System.out.print("> ");
      String line = reader.readLine();
      if (line == null) {
        break;
      }
      run(line);
      hasErrors = false;
    }
  }

  private static void run(String source) {
    Scanner scanner = new Scanner(source);
    List<Token> tokens = scanner.scanTokens();

    for(Token token : tokens) {
      System.out.println(token);
    }
  }

  public static void error(int numberLine, String message) {
    report(numberLine, "", message);
  }

  private static void report(int numberLine, String where, String message) {
    System.err.println(
        "[line " + numberLine + "] Error" + where + ": " + message
    );
    hasErrors = true;
  }
}
