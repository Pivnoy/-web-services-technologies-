package command.ifc;

import java.io.IOException;
import java.util.Scanner;

public interface Command {
    void execute(Scanner scanner) throws IOException;
}
