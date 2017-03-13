package com.adrian.domain.objects;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

public class Beast {
    private int beastId;
    @Size(min = 2, max = 50, message = "{Size.beast.name.validation}")
    private String name;
    @Min(value=0, message="{Min.beast.strAndHp.validation}")
    @Max(value=999, message="{Max.beast.strAndHp.validation}")
    @NotNull(message= "{NotNull.beast.strAndHp.validation}")
    private int strength;
    @Min(value=0, message="{Min.beast.strAndHp.validation}")
    @Max(value=999, message="{Max.beast.strAndHp.validation}")
    @NotNull(message= "{NotNull.beast.strAndHp.validation}")
    private int hp;
    @Size(min = 2, max = 50, message = "{Size.beast.category.validation}")
    private String category;
    @Size(min = 1, max = 250, message = "{Size.beast.description.validation}")
    private String description;
    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Beast(){}

    public void setBeastId(int beastId) {
        this.beastId = beastId;
    }

    public int getBeastId() {
        return beastId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}