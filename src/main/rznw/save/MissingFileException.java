package rznw.save;

import java.io.File;

public class MissingFileException extends LoadException
{
    private File file;

    public MissingFileException(File file)
    {
        this.file = file;
    }
}
