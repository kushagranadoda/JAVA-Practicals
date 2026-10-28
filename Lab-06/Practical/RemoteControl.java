interface Switchable{

    void on();

    void off();
    
    default void toggle(){
        System.out.println("Toggling device...");
    }
}

class Fan implements Switchable{

    public void on(){
        System.out.println("Fan is ON");
    }

    public void off() {
        System.out.println("Fan is OFF");
    }
}

class Light implements Switchable{

    public void on(){
        System.out.println("Light is ON");
    }

    public void off(){
        System.out.println("Light is OFF");
    }
}

@FunctionalInterface
interface SwitchDecision{
    boolean maySwitchOn(Switchable device, int hour);
}


public class RemoteControl{
    public static void main(String[] args){

        Switchable[] devices = {new Fan(),new Light()};

        System.out.println("---- Toggling Devices ----");

        for (Switchable device : devices){
            device.toggle();
        }

        SwitchDecision decision1 = new SwitchDecision(){

            public boolean maySwitchOn(Switchable device, int hour){
                return hour >= 6 && hour <= 22;
            }
        };

        SwitchDecision decision2 = (device, hour) -> hour >= 6 && hour <= 22;

        System.out.println("\n---- Anonymous Class ----");

        System.out.println("Can switch Fan at 10 AM: " + decision1.maySwitchOn(devices[0], 10));

        System.out.println("Can switch Light at 2 AM: " + decision1.maySwitchOn(devices[1], 2));

        System.out.println("\n---- Lambda Expression ----");

        System.out.println("Can switch Fan at 8 PM: " + decision2.maySwitchOn(devices[0], 20));

        System.out.println("Can switch Light at 11 PM: " + decision2.maySwitchOn(devices[1], 23));
    }
} 
