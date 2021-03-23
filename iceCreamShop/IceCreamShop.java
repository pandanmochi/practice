package practice.iceCreamShop;

import java.util.Random;

public class IceCreamShop {
    private IceCreamSales[] sales;

    public IceCreamShop(Menu menu)
    {
        sales = new IceCreamSales[menu.flavours.length];
        for(int i = 0; i < sales.length; i++)
        {
            sales[i] = new IceCreamSales(menu.getIceCreamAtIndex(i));
        }
    }

    public int order()
    {
        Random r = new Random();
        int randomOrder = r.nextInt(this.sales.length);
        return randomOrder;
    }

    public void sell(int index)
    {
        if(!(index < 0 || index >= this.sales.length))
        {
            sales[index].sell();
        }
    }

    public void salesOfDay(int sales)
    {
        for(int i = 0; i < sales; i++)
        {
            sell(order());
        }
    }

    public void print()
    {
        for(int i = 0; i < this.sales.length; i++)
        {
            System.out.printf("%-20s : ", sales[i].getIceCream().getName());
            for(int j = 0; j < sales[i].getQuantitySold(); j++)
            {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public IceCream bestSellingIceCream()
    {
        if(this.sales.length > 0)
        {
            int maxIndex = 0;
            for(int i = 0; i < this.sales.length; i++)
            {
                if(sales[i].getQuantitySold() > sales[maxIndex].getQuantitySold())
                {
                    maxIndex = i;
                }
            }
            return sales[maxIndex].getIceCream();
        }
        else
        {
            return null;
        }
    }

    public double totalRevenue()
    {
        double totalRevenue = 0;
        for(int i = 0; i < this.sales.length; i++)
        {
           totalRevenue += sales[i].sales();
        }
        return totalRevenue;
    }
}
