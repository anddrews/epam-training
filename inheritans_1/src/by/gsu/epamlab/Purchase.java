package by.gsu.epamlab;

import java.util.Scanner;


public class Purchase
{
    private String commodity;
    private int price;
    private int number;

    public Purchase(String commodity, int price, int number)
    {
        this.commodity = commodity;
        this.price = price;
        this.number = number;
    }

    public Purchase()
    {
        this("",0,0);
    }

    public Purchase(Scanner scanner)
    {
        this.commodity = scanner.next().trim();
        this.price = scanner.nextInt();
        this.number = scanner.nextInt();
    }

    public String getCommodity()
    {
        return commodity;
    }

    public void setCommodity(String commodity)
    {
        this.commodity = commodity;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public int getCost()
    {
        return price*number;
    }

    @Override
    public String toString()
    {
        return fieldToString()+ ";"+getCost();
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Purchase)){return false;}

        Purchase purchase =(Purchase)o;

        if(this.commodity.equals(purchase.getCommodity()) && this.price==purchase.getPrice())
        {
            return true;
        }

        return false;
    }

    protected String fieldToString()
    {
        return commodity+";"+number+";"+price;
    }
}
