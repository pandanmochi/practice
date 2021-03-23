package practice.iceCreamShop;


public class Test {
    public static void main(String[] args) {
        IceCream i1 = IceCream.createIceCreamFlavour("Belgian Chocolate", 2.5f);
        IceCream i2 = IceCream.createIceCreamFlavour("Mochi Mango", 3.0f);
        IceCream i3 = IceCream.createIceCreamFlavour("Dragon Fruit", 3.0f);
        IceCream i4 = IceCream.createIceCreamFlavour("Rice", 2.5f);
        IceCream i5 = IceCream.createIceCreamFlavour("Trout Frozen Yogurt", 4.5f);
        i1.print();
        i2.print();
        i3.print();
        i4.print();
        i5.print();

        IceCreamSales s5 = new IceCreamSales(i5);
        s5.print();
        s5.sell();
        s5.sell();
        s5.sell();
        s5.print();

        Menu m = new Menu();

        m.addFlavour(i1);
        m.addFlavour(i2);
        m.addFlavour(i3);
        m.addFlavour(i4);
        m.addFlavour(i5);

        m.print();

        m.removeFlavour(i1);
        m.removeFlavour(i2);
        m.removeFlavour(i3);
        m.removeFlavour(i4);
        m.print();

        m.addFlavour(i1);
        m.addFlavour(i2);
        m.addFlavour(i3);
        m.addFlavour(i4);

        IceCreamShop shop = new IceCreamShop(m);

        shop.salesOfDay(400);
        shop.print();

        System.out.println("The best selling Ice Cream of the day is " + shop.bestSellingIceCream().getName());
        System.out.println("Total revenue of the day: " + shop.totalRevenue() + " Euros");




    }
}
