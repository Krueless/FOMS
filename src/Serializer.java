import java.util.ArrayList;
import java.io.*; 

public class Serializer<T extends Serializable> implements Serializable{
    private String filepath;

    public Serializer(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<T> deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filepath));
        Object obj = in.readObject();
        in.close();
        if (obj instanceof ArrayList)
            return (ArrayList<T>) obj;
        return null;
    }

    public void serialize(ArrayList<T> TList) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filepath))) {
            out.writeObject(TList);
        } catch (IOException e) {
            System.out.println("Error: I/O operation fails");
            e.printStackTrace();
        }
    }
}
