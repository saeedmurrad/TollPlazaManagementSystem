package com.tollPlaza.entities;

import java.io.Serializable;

public class TollPlaza implements Serializable {

    private String id;
    private String name;
    private int payingCarPassed;
    private int notPayingCar;
    private int amtCollected;

    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getPayingCarPassed() {
        return payingCarPassed;
    }
    public void setPayingCarPassed(int payingCarPassed){
        this.payingCarPassed=payingCarPassed;
    }
    public int getNotPayingCar() {
        return notPayingCar;
    }
    public void setNotPayingCar(int notPayingCar){
        this.notPayingCar=notPayingCar;
    }
    public int getAmtCollected(){
        return amtCollected;
    }
    public void setAmtCollected(int amtCollected) {
        this.amtCollected = amtCollected;
    }

    public TollPlaza(){
        super();
    }

    public TollPlaza(String id, String name, int payingCarPassed, int notPayingCar, int amtCollected){
        super();
        this.id=id;
        this.name=name;
        this.payingCarPassed=payingCarPassed;
        this.notPayingCar=notPayingCar;
        this.amtCollected=amtCollected;
    }


}
