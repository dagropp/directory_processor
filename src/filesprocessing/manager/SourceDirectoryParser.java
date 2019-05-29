package filesprocessing.manager;

import filesprocessing.type2errors.DirectoryNotFound;
import filesprocessing.type2errors.NoReadPermission;

import java.io.File;
import java.util.Arrays;

/**
 * This class parses the source directory and lists the file in it, ignoring sub-folders.
 */
public class SourceDirectoryParser {
    /**
     * Creates File array from source folder files, ignoring sub-folders.
     *
     * @param sourceDirPath Path to source directory.
     * @return File array with all source folder files, ignoring sub-folders.
     * @throws DirectoryNotFound If directory path doesn't represent an actual folder.
     * @throws NoReadPermission  If can't read folder.
     */
    public static File[] execute(String sourceDirPath) throws DirectoryNotFound, NoReadPermission {
        File folder = new File(sourceDirPath); // Assign File object with directory path.
        // If directory path doesn't represent an actual folder, throw DirectoryNotFound Exception.
        if (!folder.isDirectory())
            throw new DirectoryNotFound(folder);
        // If can't read folder, throw NoReadPermission Exception.
        if (!folder.canRead())
            throw new NoReadPermission(folder);
        File[] files = folder.listFiles(); // Create File array from folder.
        if (files != null) // If successful, filter the files, so only actual files and not folders are represented.
            return Arrays.stream(files).filter(File::isFile).toArray(File[]::new);
        return null;
    }
}
