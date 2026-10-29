class FileResource implements AutoCloseable{

    public FileResource(){
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

public class AutoCloseableDemo{
    public static void main(String[] args){

        try (FileResource resource = new FileResource()){
            resource.use();
        } catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
    }
}

