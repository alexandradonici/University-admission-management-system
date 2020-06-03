package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AuditService
{
    private static AuditService instance = null;

    public AuditService(){}

    public static AuditService getInstance()
    {
        if(instance == null)
            instance = new AuditService();

        return instance;
    }

    public Path getPath()
    {
        return Paths.get("C:\\Users\\asus\\IdeaProjects\\AdmissionWebApp\\audit.csv");
    }

    public String getTimestamp ()
    {
        Date data = new Date();
        long t = data.getTime();
        Timestamp timestamp = new Timestamp(t);
        return timestamp.toString();
    }

    public void writeActiune(String actiune, String timestamp, String nume_thread)
    {
        try
        {
            FileWriter writer = new FileWriter(String.valueOf(getPath()), true);
            List<String> row =  Arrays.asList(actiune, timestamp, nume_thread);
            writer.append(String.join(",", row));
            writer.append("\n");
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}