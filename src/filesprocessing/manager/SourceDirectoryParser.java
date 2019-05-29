package filesprocessing.manager;

import filesprocessing.type2errors.DirectoryNotFound;
import filesprocessing.type2errors.NoReadPermission;

import java.io.File;
import java.util.Arrays;

/**
 * This class parses the source directory and lists the file in it, ignoring sub-folders.
 */
public class SourceDirectoryParser {
    public static File[] execute(String sourceDirPath) throws DirectoryNotFound, NoReadPermission {
        File folder = new File(sourceDirPath);
        if (!folder.isDirectory()) throw new DirectoryNotFound(folder);
        if (!folder.canRead()) throw new NoReadPermission(folder);
        File[] files = folder.listFiles();
        if (files != null)
            return Arrays.stream(files).filter(File::isFile).toArray(File[]::new);
        return null;
    }
}
