package testJavaSE.src;

import testJavaSE.src.by.gsu.epamlab.bll.ConnectionDb;
import testJavaSE.src.by.gsu.epamlab.bll.ReaderFromFileAndSaveToDB;
import testJavaSE.src.by.gsu.epamlab.bll.SQLQuerys;
import testJavaSE.src.by.gsu.epamlab.model.AbstractTest;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Runner2
{
    public static String RESOURCE_NAME="ConnectionDb";
    public static String TEST_VERSION ="TASK2";
    public static String FILE_NAME="src/students.xml";

    public static void main(String[] args) throws FileNotFoundException
    {
        try
        {
            Connection connection= ConnectionDb.getConnection();

            SQLQuerys sqlQuerys=new SQLQuerys(connection);

            ReaderFromFileAndSaveToDB reader=new ReaderFromFileAndSaveToDB(sqlQuerys,FILE_NAME,TEST_VERSION);


            reader.readAndSave();


            Map<String,Double> avgTest=sqlQuerys.getAvgTest();

            for (Map.Entry<String,Double> tmp:avgTest.entrySet())
            {
                System.out.println(tmp.getKey()+ " " + tmp.getValue());
            }

            List<AbstractTest> listMonth=sqlQuerys.getResultTestAtCurrentMonth(TEST_VERSION);

            for(AbstractTest tmp:listMonth)
            {
                System.out.println(tmp);
            }

            int index=listMonth.size()-1;
            Date latestDate=listMonth.get(index).getDate();
            System.out.println("\nTests at "+latestDate+":\n");
            while (listMonth.get(index).getDate().equals(latestDate))
            {
                System.out.println(listMonth.get(index));
                index--;
            }


        }
        catch (  SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }


    }
}
