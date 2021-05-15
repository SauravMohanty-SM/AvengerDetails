package com.example.avengerheros;

public class Data {
    private String Name;
    private String ImageUrl;

    public Data() {}
    public Data(String Name, String ImageUrl) {
        this.Name = Name;
        this.ImageUrl = ImageUrl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
