package bussiness_Layer.entity;

import java.io.Serializable;

public class HotelInformation implements Comparable<HotelInformation> ,Serializable {
    private String hotel_id;
    private String hotel_Name;
    private int hotel_Room_Available;
    private String hotel_Address;
    private String hotel_Phone;
    private int hotel_Rating;

    public HotelInformation() {
    }

    public HotelInformation(String hotel_id, String hotel_Name, int hotel_Room_Available, String hotel_Address, String hotel_Phone, int hotel_Rating) {
        this.hotel_id = hotel_id;
        this.hotel_Name = hotel_Name;
        this.hotel_Room_Available = hotel_Room_Available;
        this.hotel_Address = hotel_Address;
        this.hotel_Phone = hotel_Phone;
        this.hotel_Rating = hotel_Rating;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_Name() {
        return hotel_Name;
    }

    public void setHotel_Name(String hotel_Name) {
        this.hotel_Name = hotel_Name;
    }

    public int getHotel_Room_Available() {
        return hotel_Room_Available;
    }

    public void setHotel_Room_Available(int hotel_Room_Available) {
        this.hotel_Room_Available = hotel_Room_Available;
    }
    
    public String getHotel_Address() {
        return hotel_Address;
    }

    public void setHotel_Address(String hotel_Address) {
        this.hotel_Address = hotel_Address;
    }

    public String getHotel_Phone() {
        return hotel_Phone;
    }

    public void setHotel_Phone(String hotel_Phone) {
        this.hotel_Phone = hotel_Phone;
    }

    public int getHotel_Rating() {
        return hotel_Rating;
    }

    public void setHotel_Rating(int hotel_Rating) {
        this.hotel_Rating = hotel_Rating;
    }

    @Override
    public String toString() {
        return String.format("|%9s|%17s|%5d|%20s|%11s|%4d star|\n", hotel_id, hotel_Name,hotel_Room_Available,
                hotel_Address, hotel_Phone, hotel_Rating );
    }

    @Override
    public int compareTo(HotelInformation o) {
        if(this.getHotel_id().compareTo(o.getHotel_id()) > 0){
            return 1;
        } else if(this.getHotel_id().compareTo(o.getHotel_id()) < 0) {
            return -1;
        }else{
            return 0;
        }
    }
    
    
    
}
