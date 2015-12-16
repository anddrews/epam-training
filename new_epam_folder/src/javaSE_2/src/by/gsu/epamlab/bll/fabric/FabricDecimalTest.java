package javaSE_2.src.by.gsu.epamlab.bll.fabric;


import javaSE_2.src.by.gsu.epamlab.bll.readers.ReaderXML;
import javaSE_2.src.by.gsu.epamlab.model.*;
import javaSE_2.src.by.gsu.epamlab.model.tests.TestDecimal;

import java.sql.Date;

public class FabricDecimalTest implements IFabricTest
{
    private String fileName;
    private final static int factor=10;



    public FabricDecimalTest(String fileName)
    {
        this.fileName = fileName;
    }

    public  AbstractTest getTestFromFile(String login, String name, String date, String mark)
    {
        return new TestDecimal(login,name,date,mark);
    }

    public   AbstractTest getTestFromDB(String login, String name, Date date, int mark)
    {
        return new TestDecimal(login,name,date,mark);
    }
    public  IFileReader getReader()
    {
        return new ReaderXML(this);
    }

    public String getFileName()
    {
        return this.fileName;
    }

    @Override
    public int getFactor()
    {
        return factor;
    }
}