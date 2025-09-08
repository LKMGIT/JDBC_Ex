package Score_DB.Output;

public interface OutputInterface {
    public void printUsage();
    public void saveData() throws Exception;
    public void checkKeyAndInputData() throws Exception;
}
