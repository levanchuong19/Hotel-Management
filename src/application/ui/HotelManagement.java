
package application.ui;
import application.utilities.DataInput;
import bussiness_layer.service.HotelService;
import data_layer.FileManage;
public class HotelManagement {

    public static void main(String[] args) {
        try {
            HotelService tmp = new HotelService();
            FileManage data = new FileManage();
            data.loadDataFromFile(tmp, "Hotel.txt");
            int choice = 0;
            boolean stop = true;
            do {
                Menu.menuHotelManage();
                choice = DataInput.inputChoice(1, 7);
                switch (choice) {
                    case 1:
                        tmp.addHotel();
                        break;
                    case 2:
                        tmp.checkExitsHotel();
                        break;
                    case 3:
                        tmp.updateHotelInformation();
                        break;
                    case 4:
                        tmp.deleteHotel();
                        break;
                    case 5:
                        tmp.searchHotel();
                        break;
                    case 6:
                        tmp.displayAllHotel();
                        break;
                    case 7:
                        data.saveDataToFile(tmp, "Hotel.txt");
                        System.out.println("----------BYE----------");
                        stop = false;
                        break;
                }
            } while (!(choice < 1 || choice > 6));
        } catch (Exception e) {
            System.out.println("Program Error");;
        }
    }
}
