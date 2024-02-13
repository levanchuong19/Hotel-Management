package bussiness_layer.service;

import application.ui.Menu;
import bussiness_Layer.entity.HotelInformation;
import application.utilities.DataInput;
import bussiness_layer.component.DataValidation;
import java.util.ArrayList;
import data_layer.FileManage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HotelService extends ArrayList<HotelInformation> implements IHotelService {

    private DataInput inputer;
    private FileManage fi;
    private DataValidation da;

    public HotelService() throws Exception {
        inputer = new DataInput();
        fi = new FileManage();
        da = new DataValidation();
    }

    @Override
    public void addHotel() {

        try {
            do {
                String Hotel_Id = da.inputID(this);
                String Hotel_Name = da.inputName();
                int Hotel_Room_Available = da.inputRoom();
                String Hotel_Address = da.inputAddress();
                String Hotel_phone = da.inputPhone();
                int Hotel_Rating = da.inputRating();
                HotelInformation hotel = new HotelInformation(Hotel_Id, Hotel_Name, Hotel_Room_Available, Hotel_Address, Hotel_phone, Hotel_Rating);
                this.add(hotel);
                System.out.println("Add Hotel Successfully !");
            } while (inputer.InputYN("Do You Want To Continue(Y/N): "));
        } catch (Exception e) {
            System.out.println("Add Hotel Error !");
        }
    }

    @Override
    public void checkExitsHotel() {
        try {
            do {
                String New_id = inputer.enterId();
                ArrayList<HotelInformation> tmp = new ArrayList<>();
                fi.loadDataFromFile(tmp, "Hotel.txt");
                HotelInformation hotel = da.searchDataByID(New_id, tmp);
                if (hotel != null) {
                    System.out.println("Exist Hotel");
                    showHotel(hotel);
                } else {
                    System.out.println("“No Hotel Found!");
                }
            } while (inputer.InputYN("Do You Want To Continue(Y/N): "));
        } catch (Exception e) {
            System.out.println("Hotel Not Found.");
        }
    }

    @Override
    public void updateHotelInformation() {
        try {
            do {
                String id = inputer.enterId();
                HotelInformation hotel = da.searchDataByID(id, this);

                if (hotel != null) {
                    String newHotelName = inputer.inputNameNoFormat();
                    int newHotelRoomAvailable = da.inputRoomNoFormat();
                    String newHotelAddress = inputer.inputAddressNoFormat();
                    String newHotelPhone = inputer.inputPhoneNoFormat();
                    int newHotelRating = da.inputRatingNoFormat();

                    String updatedName = newHotelName.isEmpty() ? hotel.getHotel_Name() : newHotelName;
                    int updatedRoomAvailable = newHotelRoomAvailable == 0 ? hotel.getHotel_Room_Available() : newHotelRoomAvailable;
                    String updatedAddress = newHotelAddress.isEmpty() ? hotel.getHotel_Address() : newHotelAddress;
                    String updatedPhone = newHotelPhone.isEmpty() ? hotel.getHotel_Phone() : newHotelPhone;
                    int updatedRating = newHotelRating == 0 ? hotel.getHotel_Rating() : newHotelRating;
                    if (updatedName.equals(hotel.getHotel_Name())
                            && updatedRoomAvailable == hotel.getHotel_Room_Available()
                            && updatedAddress.equals(hotel.getHotel_Address())
                            && updatedPhone.equals(hotel.getHotel_Phone())
                            && updatedRating == hotel.getHotel_Rating()) {
                        System.out.println("Update Fail! No new information provided.");
                        showHotel(hotel);
                    } else {
                        HotelInformation updateHotel = new HotelInformation(id, updatedName, updatedRoomAvailable, updatedAddress, updatedPhone, updatedRating);
                        this.set(this.indexOf(hotel), updateHotel);
                        System.out.println("Update Successfully !");
//                        fi.saveDataToFile(arr, "Hotel.txt");
                        System.out.println("Here is hotel after update: ");
                        showHotel(updateHotel);
                    }
                } else {
                    System.out.println("No Hotel Found !");
                }
            } while (inputer.InputYN("Do You Want To Continue(Y/N): "));
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("Error during hotel update: " + e.getMessage());
        }
    }

    boolean choice = true;

    @Override
    public void deleteHotel() {
        try {
            do {
                displayAllHotel();
                String newID = inputer.enterId();
                HotelInformation tmp = da.searchDataByID(newID, this);
                if (tmp != null) {
                    choice = inputer.InputYN("Do You Ready Want To Delete This Hotel(Y/N): ");
                    this.remove(tmp);
                    System.out.println("Delete Hotel Successfully !");
                    fi.saveDataToFile(this, "Hotel.txt");

                } else {
                    System.out.println("Delete Fail !");
                }
            } while (inputer.InputYN("Do You Want To Continue(Y/N): "));
        } catch (Exception e) {
            System.out.println("Delete Hotel Error !");
        }
    }

    @Override
    public void searchHotel() {
        int choice = 0;
        do {
            try {
                Menu.menuSearchHotel();
                choice = DataInput.inputChoice(1, 3);
                boolean check = false;
                switch (choice) {
                    case 1:
                        String id = inputer.enterId();
                        if (id.equals("H") || id.length() < 2) {
                            System.out.println("Invalid ID format. Please enter 'H' followed by one or two digits.");
                            break;
                        }
                        ArrayList<HotelInformation> correctID = new ArrayList<>();
                        for (HotelInformation x : this) {
                            if (x.getHotel_id().startsWith(id)) {
                                correctID.add(x);
                            }
                        }
                        if (!correctID.isEmpty()) {
                            da.sortHotelById(correctID);
                            System.out.println("---------------------------------------------------------------------------------------------------------------");
                            System.out.printf("|%9s|%17s|%25s|%30s|%11s|%10s|\n", "Hotel_ID", "Hotel_Name", "Hotel_Room_Available",
                                    "Hotel_Address", "Hotel_Phone", "Hotel_Rating");
                            for (HotelInformation hotel : correctID) {
                                showHotel(hotel);
                            }
                            check = true;
                            break;
                        }
                        if (!check) {
                            System.out.println("No Hotel Found !");
                            break;
                        }
                        break;
                    case 2:
                        String name = inputer.enterName();
                        ArrayList<HotelInformation> correctName = new ArrayList<>();
                        for (HotelInformation x : this) {
                            if (x.getHotel_Name().contains(name)) {
                                correctName.add(x);
                            }
                        }
                        if (!correctName.isEmpty()) {
                            da.sortHotel(correctName);
                            System.out.println("---------------------------------------------------------------------------------------------------------------");
                            System.out.printf("|%9s|%17s|%25s|%30s|%11s|%10s|\n", "Hotel_ID", "Hotel_Name", "Hotel_Room_Available",
                                    "Hotel_Address", "Hotel_Phone", "Hotel_Rating");
                            for (HotelInformation hotel : correctName) {
                                showHotel(hotel);
                            }
                            check = true;
                            break;
                        }
                        if (!check) {
                            System.out.println("No Hotel Found !");
                            break;
                        }
                        break;
                    default:
                        System.out.println("----------Bye----------");
                }
            } catch (Exception e) {
                System.out.println("Search Hotel Error");
            }
        } while (!(choice < 1 || choice > 2));
    }

    @Override
    public void displayAllHotel() {
        try {
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            System.out.printf("|%9s|%17s|%25s|%30s|%11s|%10s|\n", "Hotel_ID", "Hotel_Name", "Hotel_Room_Available",
                    "Hotel_Address", "Hotel_Phone", "Hotel_Rating");
            ArrayList<HotelInformation> tmp = new ArrayList<>();
            fi.loadDataFromFile(tmp, "Hotel.txt");
            da.sortHotel(tmp);
            if (!tmp.isEmpty()) {
                for (HotelInformation x : tmp) {
                    showHotel(x);
                }
            } else {
                System.out.println("List Empty.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Display HotelInformation Fail");;
        }
    }

    public void showHotel(HotelInformation hotel) {
        String address = hotel.getHotel_Address();
        Pattern pattern = Pattern.compile("(.{0,30})\\b");
        Matcher matcher = pattern.matcher(address);
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        try {
            if (matcher.find()) {
                System.out.printf("|%9s|%17s|%25s|%30s|%11s|%7s star|\n",
                        hotel.getHotel_id(), hotel.getHotel_Name(), hotel.getHotel_Room_Available(), matcher.group(1),
                        hotel.getHotel_Phone(), hotel.getHotel_Rating());
            }
            while (matcher.find()) {
                if (!matcher.group(1).equals("")) {
                    System.out.printf("|%9s|%17s|%25s|%30s|%11s|%12s|\n",
                            "", "", "",
                            matcher.group(1), "", "");
                }
            }
        } catch (Exception e) {
            System.out.println("Error displaying hotel information: ");

        }
    }
}
