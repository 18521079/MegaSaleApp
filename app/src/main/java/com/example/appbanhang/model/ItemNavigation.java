package com.example.appbanhang.model;

public class ItemNavigation {
    private String name, iconLink;

    public ItemNavigation(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public ItemNavigation(String name, String iconLink) {
        this.name = name;
        this.iconLink = iconLink;
    }
}
