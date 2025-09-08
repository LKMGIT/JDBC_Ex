package Score_DB.Output;

import java.io.IOException;

public class Out_main {
    public static void main(String[] args) throws IOException {
        OutputImpl iimpl = new OutputImpl();

        iimpl.printUsage();
        iimpl.checkKeyAndInputData();
        iimpl.saveData();

    }
}
