package com.example.networking;

public class Mountain extends RecyclerViewItem {

    private String name;
    private String type;
    private String company;
    private String category;
    private String location;
    private String wiki;
    private String img;
    private int size;
    private int cost;

    public Mountain(String ID, String name, String type, String company, String location, String category, int size, int cost, String wiki, String img) {
        super(ID);
        this.name = name;
        this.type = type;
        this.company = company;
        this.location = location;
        this.category = category;
        this.size = size;
        this.cost = cost;
        this.wiki = wiki;
        this.img = img;
    }

    // Getters
    public String getName() { return name; }
    public String getType() { return type; }
    public String getCompany() { return company; }
    public String getCategory() { return category; }
    public String getLocation() { return location; }
    public String getWiki() { return wiki; }
    public String getImg() { return img; }
    public int getSize() { return size; }
    public int getCost() { return cost; }
}
