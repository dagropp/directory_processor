package filesprocessing;

import filesprocessing.manager.DirectoryProcessorFactory;

public class DirectoryProcessor {
    public static void main(String[] args) {
        DirectoryProcessorFactory.execute(args);
    }
}
