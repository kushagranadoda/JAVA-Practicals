public class ParkingLot {
    private int twoWheelers;
    private int fourWheelers;
    private final int twoCap;
    private final int fourCap;
    private static long revenue = 0;

    public ParkingLot(int twoCap, int fourCap) {
        this.twoCap = twoCap;
        this.fourCap = fourCap;
        this.twoWheelers = 0;
        this.fourWheelers = 0;
    }

    public boolean park(String type) {
        if (type.equals("two")) {
            if (twoWheelers < twoCap) {
                twoWheelers++;
                revenue += 20;
                return true;
            } else {
                System.out.println("Full");
                return false;
            }
        } else if (type.equals("four")) {
            if (fourWheelers < fourCap) {
                fourWheelers++;
                revenue += 40;
                return true;
            } else {
                System.out.println("Full");
                return false;
            }
        } else {
            System.out.println("Unknown vehicle type: " + type);
            return false;
        }
    }

    public void leave(String type) {
        if (type.equals("two")) {
            if (twoWheelers > 0) {
                twoWheelers--;
            }
        } else if (type.equals("four")) {
            if (fourWheelers > 0) {
                fourWheelers--;
            }
        } else {
            System.out.println("Unknown vehicle type: " + type);
        }
    }

    public int getTwoWheelers() {
        return twoWheelers;
    }

    public int getFourWheelers() {
        return fourWheelers;
    }

    public static long getRevenue() {
        return revenue;
    }

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(3, 2);

        System.out.println("Park two: " + lot.park("two"));  
        System.out.println("Park two: " + lot.park("two"));   
        System.out.println("Park four: " + lot.park("four")); 
        System.out.println("Park four: " + lot.park("four"));  
        System.out.println("Park four: " + lot.park("four")); 
        System.out.println("Park two: " + lot.park("two"));   
        System.out.println("Park two: " + lot.park("two"));   

        System.out.println("Leave two: ");
        lot.leave("two");                                      
        System.out.println("Leave four: ");
        lot.leave("four");                                   

        System.out.println("Park four: " + lot.park("four")); 
        System.out.println("Leave two: ");
        lot.leave("two");                                    

        lot.leave("four");
        lot.leave("four");
        lot.leave("four"); 

        System.out.println();
        System.out.println("Final two-wheeler occupancy: " + lot.getTwoWheelers() + "/" + lot.twoCap);
        System.out.println("Final four-wheeler occupancy: " + lot.getFourWheelers() + "/" + lot.fourCap);
        System.out.println("Total revenue: " + ParkingLot.getRevenue());
    }
}