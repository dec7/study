package recipe.ch11;

import java.io.IOException;

import static recipe.ch11.Ch11R12Kt.houstonWeHaveAProblem;

public class Ch11R12Java {

    public static void doNothing() {
        //houstonWeHaveAProblem();
    }

    public static void useTryCatchBlock() {
        try {
            houstonWeHaveAProblem();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void useThrowsClause() throws IOException {
        houstonWeHaveAProblem();
    }
}
