package filesprocessing;

import filesprocessing.manager.DirectoryProcessorFactory;

/**
 * This class runs the program by executing the manager package factory.
 */
public class DirectoryProcessor {
    public static void main(String[] args) {
        DirectoryProcessorFactory.execute(args); // Calls factory method that runs the program.
    }
}
