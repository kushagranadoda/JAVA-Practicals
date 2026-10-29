class MyResource implements AutoCloseable{

    public MyResource(){
        System.out.println("Resource opened");
    }

    public void use(){
        System.out.println("Using resource...");
        throw new RuntimeException("Error while using resource");
    }

    @Override
    public void close(){
        System.out.println("Resource closed");
    }
}

public class FileResource{
    public static void main(String[] args){

        try (MyResource resource = new MyResource()){
            resource.use();
        } catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
    }
}

