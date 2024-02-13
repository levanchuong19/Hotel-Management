
package data_layer;

import bussiness_Layer.entity.HotelInformation;
import java.util.List;

public interface IHotelDao<Object> {
     void saveDataToFile(List<HotelInformation> list, String fName);
     void loadDataFromFile(List<HotelInformation> list, String FName);
            
}
