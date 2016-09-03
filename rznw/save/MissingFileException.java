package rznw.save;

import java.io.File;

public class MissingFileException extends Exception
{
    private File file;

    public MissingFileException(File file)
    {
        this.file = file;
    }
}
