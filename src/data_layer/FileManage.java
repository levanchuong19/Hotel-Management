package data_layer;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import bussiness_Layer.entity.HotelInformation;
import java.io.IOException;

public class FileManage implements IHotelDao<Object>{
   @Override
   public void saveDataToFile(List<HotelInformation> list, String fName) {
        try {
            File f = new File(fName);
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream o = new ObjectOutputStream(fos);
            for (HotelInformation x : list) {
                if(x != null){
                o.writeObject(x);
                }
            }
            fos.close();
            o.close();
            System.out.println("Save Data Successfully.");
        } catch (Exception e) {
            System.out.println("Save Data Error");
        }
   }
    
   @Override
    public void loadDataFromFile(List<HotelInformation> list, String FName) {
        try {
            File f = new File(FName);
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream oi = new ObjectInputStream(fis);
            while(true) {
                try {
                    HotelInformation tmp = (HotelInformation) oi.readObject();
                    list.add(tmp);
                } catch (EOFException e) {
                    break;
                }
            }
            fis.close();
            oi.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from the file: " + e.getMessage());
        } 
    }
}
