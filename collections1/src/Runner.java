import by.gsu.epamlab.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Runner
{

    final static String FIRST_DAY="first day is ";
    final static String LAST_DAY="last day is ";
    final static String SEPARATOR="--------------";
    final static String NOT_FOUND="not found";
    final static String TOTAL_COST=" total cost= ";



    public static void main(String[] args)
    {
        final String FILE_NAME="src/in.csv";
        final Purchase BREAD_1550=new Purchase("bread",1550,1);
        final Purchase BREAD_1700=new Purchase("bread",1700,1);

        Map<Purchase,WeekDay> firstDayPurchase=new HashMap<>();
        Map<Purchase,WeekDay> lastDayPurchase=new HashMap<>();
        Map<WeekDay,PurchaseList> weekDayKey=new TreeMap<>();


        Scanner scanner=null;
        try
        {
            scanner=new Scanner(new FileReader(FILE_NAME));
            while (scanner.hasNext())
            {
                Purchase purchase= PurchaseFactory.getPurchase(scanner);
                WeekDay weekDay= WeekDay.valueOf(scanner.nextLine());
                //create map with key- day of week
                if(!weekDayKey.containsKey(weekDay))
                {
                    weekDayKey.put(weekDay,new PurchaseList(purchase));
                }
                else
                {
                    weekDayKey.get(weekDay).addPurchase(purchase);
                }
                //create map with value - fist day purchase
                if(!firstDayPurchase.containsKey(purchase))
                {
                    firstDayPurchase.put(purchase,weekDay);
                }
                else
                {
                    if(firstDayPurchase.get(purchase).ordinal()>weekDay.ordinal())
                    {
                        firstDayPurchase.put(purchase,weekDay);
                    }
                }
                //create map with value - last day purchase
                if(!lastDayPurchase.containsKey(purchase))
                {
                    lastDayPurchase.put(purchase,weekDay);
                }
                else
                {
                    if(lastDayPurchase.get(purchase).ordinal()<weekDay.ordinal())
                    {
                        lastDayPurchase.put(purchase,weekDay);
                    }
                }
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(scanner!=null)
            {
                scanner.close();
            }
        }
        printMap(weekDayKey);
        System.out.println(SEPARATOR);

        printMap(firstDayPurchase);
        System.out.println(SEPARATOR);

        printMap(lastDayPurchase);
        System.out.println(SEPARATOR);

        System.out.println(BREAD_1550);
        System.out.println(FIRST_DAY
                +(firstDayPurchase.get(BREAD_1550)==null?NOT_FOUND:firstDayPurchase.get(BREAD_1550))
            +LAST_DAY
            + (lastDayPurchase.get(BREAD_1550)==null?NOT_FOUND:lastDayPurchase.get(BREAD_1550)));
        System.out.println(SEPARATOR);
        System.out.println(BREAD_1700);
        System.out.println(FIRST_DAY+
                (firstDayPurchase.get(BREAD_1700)==null?NOT_FOUND:firstDayPurchase.get(BREAD_1700)));

        PurchaseChecker checkMeat =new PurchaseChecker()
        {
            @Override
            public boolean check(Map.Entry<Purchase, WeekDay> mapEntry)
            {
                return mapEntry.getKey().getCommodity().equals("meat");
            }
        };
        PurchaseChecker checkFriday=new PurchaseChecker()
        {
            @Override
            public boolean check(Map.Entry<Purchase, WeekDay> mapEntry)
            {
                return mapEntry.getValue().equals(WeekDay.FRIDAY);
            }
        };

        removeFromMap(firstDayPurchase, checkMeat);


        System.out.println(SEPARATOR);
        printMap(firstDayPurchase);


        removeFromMap(lastDayPurchase,checkFriday);
        System.out.println(SEPARATOR);
        printMap(lastDayPurchase);
        System.out.println(SEPARATOR);

        for (Map.Entry<WeekDay,PurchaseList> entry:weekDayKey.entrySet())
        {
            System.out.println(entry.getKey()+ TOTAL_COST+entry.getValue().getTotalcost());
        }


    }

    private static void removeFromMap(Map<Purchase, WeekDay> purchases, PurchaseChecker check)
    {
        Iterator<Map.Entry<Purchase,WeekDay>> iterator=purchases.entrySet().iterator();
        while (iterator.hasNext())
        {
            if(check.check(iterator.next()))
            {
                iterator.remove();
            }
        }
    }

    private static void printMap(Map<?,?> purchases)
    {
        for (Map.Entry<?,?> temp:purchases.entrySet())
        {
            System.out.println(temp.getKey()+" = "+temp.getValue());
        }
    }

}
