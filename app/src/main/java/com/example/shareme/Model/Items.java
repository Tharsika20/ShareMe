package com.example.shareme.Model;

public class Items {
    private String image;
    private String name;
    private String description;
    private float price;
    private String address;

    // private String id;

    public Items() {

    }

    public Items (String image, String name, String description, float price, String address) {
        //this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.address = address;
    }

    /* public String getId(){
          return id;
      }

      public void setId(String id){
          this.id = id;
      } */
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
