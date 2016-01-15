package IO;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
    
    private static final String fileName = "f:/t/Object.bin";
    
    public void serializeUtil(Object p) {
        try {
            
            // create an output stream for serializing the employee object
            FileOutputStream outputFileStream = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    outputFileStream);
            // writeObject method of ObjectOutputStream will write/serialize the
            // employeeobject to
            // the path provided by FileOutputStream
            outputStream.writeObject(p);
            outputStream.close();
            outputFileStream.close();
            System.err.println("successfully serialized ");
            
        } catch (IOException e) {
            // Print any exception
            e.printStackTrace();
        }
    }
    
    public Object deserializeUtil() {
        ObjectInput input = null;
        Object party = null;
        try {
            // use buffering
            InputStream file = new FileInputStream(fileName);
            InputStream buffer = new BufferedInputStream(file);
            input = new ObjectInputStream(buffer);
            // deserialize the List
            return input.readObject();
        } catch (Exception o) {
            o.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return party;
    }
}
