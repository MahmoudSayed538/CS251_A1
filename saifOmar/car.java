import java.util.*;

public class car {
    private static List <String> types = new ArrayList<>(Arrays.asList("Bmw", "Audi", "Ford", "Honda", "Nissan"));


    private static List <String> colors =new ArrayList<>(Arrays.asList("Red", "Blue", "Green", "Yellow", "Orange")) ;
    private static List<String> models = new ArrayList<>(Arrays.asList("2020", "2021", "2022", "2023", "2024"));

    private String model;
    private String color;
    private String type;
    private int price=0;
    private int renthours;
    private double totalprice;


    Scanner s = new Scanner(System.in);
    Scanner z = new Scanner(System.in);


    public void settype()
    {
        System.out.println("The avilable cars :"+types);
        System.out.println("Enter the car you want:");
        String in = s.next();
        if(types.contains(in))
        {
            type = in;
        }

           else System.out.println("Sorry we dont have this car type");

    }





public void setcolor()
    {
        System.out.println("The avilable colors : "+colors);
        System.out.print("Enter car color: ");
        String in = s.next();
        if(colors.contains(in))
        {
            color = in;
        }

        else System.out.println("Sorry we dont have this color");


    }

 public void setmodel()
    {
        System.out.println("The car models :"+models);
        System.out.print("Enter car model: ");
        String in = s.next();
        if(models.contains(in))
        {
            model = in;
        }

        else System.out.println("Sorry we dont have this model");


    }

    private static  Map<String,Map<String,Integer>> priceMap = new HashMap<>();

    static
    {
       Map<String,Integer> bmwprices = new HashMap<>();
        bmwprices.put("2020",400);
        bmwprices.put("2021",450);
        bmwprices.put("2022",500);
        bmwprices.put("2023",550);
        bmwprices.put("2024",600);

        Map<String,Integer> audiprices = new HashMap<>();
        audiprices.put("2020",600);
        audiprices.put("2021",650);
        audiprices.put("2022",700);
        audiprices.put("2023",750);
        audiprices.put("2024",800);

        Map<String,Integer> fordprices = new HashMap<>();
        fordprices.put("2020",550);
        fordprices.put("2021",650);
        fordprices.put("2022",750);
        fordprices.put("2023",800);
        fordprices.put("2024",900);

        Map<String,Integer> hondaprices = new HashMap<>();
        hondaprices.put("2020",200);
        hondaprices.put("2021",250);
        hondaprices.put("2022",300);
        hondaprices.put("2023",350);
        hondaprices.put("2024",400);

        Map<String,Integer> nissanprices = new HashMap<>();
        nissanprices.put("2020",200);
        nissanprices.put("2021",250);
        nissanprices.put("2022",300);
        nissanprices.put("2023",350);
        nissanprices.put("2024",400);

        priceMap.put("Bmw",bmwprices);
        priceMap.put("Audi",audiprices);
        priceMap.put("Ford",fordprices);
        priceMap.put("Honda",hondaprices);
        priceMap.put("Nissan",nissanprices);

    }

    public void setPrice()
    {
        if(priceMap.containsKey(type) && priceMap.get(type).containsKey(model))
        {
            price = priceMap.get(type).get(model);

        }

    }



    public void setrentalhours()
    {
        System.out.println("Discount!!!!");
        System.out.println("RENT more than 24 hours and you will get 10% sale on your total price");
        System.out.println("Enter rental hours:");
        renthours=s.nextInt();
        double discount =(price*renthours)-(price*renthours*0.1);
        if(renthours>24)
        {
            totalprice=discount;
        }
        else
        {
        totalprice=price*renthours;}
        System.out.print("Your carr: "+type );
        System.out.print(" color: "+color );
        System.out.print(" model: "+model );
        System.out.println(" price: "+price);
        System.out.println("You rent it for ("+renthours+") hours");

        System.out.println("Total price for rental: "+totalprice);

    }

    public void addd()
    {
        System.out.println("Enter what you want to add");
        System.out.println("1)Add car");
        System.out.println("2)Add color");
        System.out.println("3)Add model");
        int choice = s.nextInt();
        switch(choice)
        {
            case 1:
                System.out.println("Enter new type: ");
                String newtype = z.next();
                if(!types.contains(newtype))
                {
                    types.add(newtype);
                    priceMap.put(newtype,new HashMap<>());

                    System.out.println("Enter the number of new  models to add: ");
                    int newmodel = s.nextInt();
                    for (int i=0;i<newmodel;i++)
                    {
                        System.out.println("Enter new model year: ");
                        String newmodelyear = z.next();
                        System.out.println("Enter price for newmodel: "+newmodelyear+":");
                        int newprice = s.nextInt();
                        priceMap.get(newtype).put(newmodelyear,newprice);

                    }


                    System.out.println("new car added");
                }
                else
                    System.out.println("car already exists");
                break;

            case 2:
                System.out.println("Enter new color: ");
                String newcolor = z.next();
                if(!colors.contains(newcolor))
                {
                    colors.add(newcolor);
                    System.out.println("new color added");
                }
                else
                    System.out.println("color already exists");
                break;

            case 3:
                System.out.println("Enter new model: ");
                String newmodel = z.next();
                if(!models.contains(newmodel))
                {
                    models.add(newmodel);
                    System.out.println("new model added");
                }
                else
                    System.out.println("model already exists");
                break;
                default:
                    System.out.println("Invalid choice");

        }
    }

    public void remove()
    {
        System.out.println("Enter what you want to remove");
        System.out.println("1)Remove car");
        System.out.println("2)Remove color");
        System.out.println("3)Remove model");

        int choice = s.nextInt();

        switch(choice)
        {
            case 1:
                System.out.println("Enter type that you want to remove: ");
                String removetype = z.next();
                if(types.remove(removetype))
                {
                    priceMap.remove(removetype);
                    System.out.println("car removed");
                }
                else
                    System.out.println("car does not exist");
                break;

            case 2:
                System.out.println("Enter color that you want to remove: ");
                String removecolor = z.next();
                if(colors.remove(removecolor))
                {
                    colors.remove(removecolor);
                    System.out.println("color removed");
                }
                else
                    System.out.println("color does not exist");
                break;

            case 3:
                System.out.println("Enter model year that you want to remove: ");
                String removemodel = z.next();
                if(models.remove(removemodel))
                {
                    models.remove(removemodel);
                    for (Map<String, Integer> prices : priceMap.values())
                    {
                        prices.remove(removemodel); // remove price from  price map
                    }
                    System.out.println("car  model removed");
                }
                else
                    System.out.println("car model does not exist");
                break;
                default:
                    System.out.println("Invalid choice");


        }




    }

    public void updateprice()
    {
        System.out.println("Enter the car type that you want to update its price: ");
        String type = z.next();

        if(!priceMap.containsKey(type))
        {
            System.out.println("car type does not exist");
            return;
        }

        System.out.println("Enter the car model: ");
        String model = z.next();

        if(!models.contains(model))
        {
            System.out.println("car model does not exist");
            return;
        }

        System.out.println("Enter the new price: ");
        int newprice = z.nextInt();
        s.nextLine();
        z.nextLine();


        priceMap.get(type).put(model,newprice);

        System.out.println("car price updated");



    }



}