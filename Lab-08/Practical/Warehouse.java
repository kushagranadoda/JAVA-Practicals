import java.util.*;

class OutOfStockException extends Exception{
    private int shortfall;

    public OutOfStockException(int shortfall){
        super("Out of stock");
        this.shortfall = shortfall;
    }

    public int getShortfall(){
        return shortfall;
    }
}

class InvalidQuantityException extends Exception{
    public InvalidQuantityException(){
        super("Quantity must be greater than 0");
    }
}

public class Warehouse{

    String[] items = {"Laptop", "Mouse", "Keyboard"};
    int[] stock = {10, 5, 8};

    public void issue(String item, int qty)
            throws OutOfStockException, InvalidQuantityException{

        if (qty <= 0){
            throw new InvalidQuantityException();
        }

        int index = -1;
        for (int i = 0; i < items.length; i++){
            if (items[i].equals(item)) {
                index = i;
                break;
            }
        }

        if (index == -1){
            throw new OutOfStockException(qty);
        }

        if (qty > stock[index]){
            int shortfall = qty - stock[index];
            throw new OutOfStockException(shortfall);
        }

        stock[index] -= qty;
        System.out.println("Issued " + qty + " " + item);
        System.out.println("Remaining stock: " + stock[index]);
    }

    public static void main(String[] args){

        Warehouse warehouse = new Warehouse();

        String[][] requests = {{"Laptop", "3"}, {"Mouse", "10"}, {"Keyboard", "2"}, {"Laptop", "0"}, {"Mouse", "2"}, {"Tablet", "1"}};

        for (String[] request : requests){
            String item = request[0];
            int qty = Integer.parseInt(request[1]);

            System.out.println("\nRequest: " + item + " - " + qty);

            try{
                warehouse.issue(item, qty);
            } catch (OutOfStockException e){
                System.out.println("Failed: Out of stock");
                System.out.println("Shortfall: " + e.getShortfall());
            } catch (InvalidQuantityException e){
                System.out.println("Failed: " + e.getMessage());
            }
        }
        System.out.println("\nAll requests processed.");
    }
}

