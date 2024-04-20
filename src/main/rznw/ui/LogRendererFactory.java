package rznw.ui;

public class LogRendererFactory
{
    private static MainGameFrame frame;
    private static LogRenderer instance;

    public static void init(MainGameFrame frame)
    {
        LogRendererFactory.frame = frame;
    }

    public static LogRenderer instance()
    {
        if (LogRendererFactory.instance == null)
        {
            LogRendererFactory.instance = new LogRenderer(LogRendererFactory.frame);
        }

        return LogRendererFactory.instance;
    }
}
