/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.food.farmer.web.rest.dto;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author sumit.garg
 */
public class WarehouseLotStackDTO {

    private UUID id;
    private UUID lotid;
    private UUID stackid;
    private int bags;
    private String stackname;
    private String godownname;
    private String chambername;
    private Date ondate;
    private String byuser;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getLotid() {
        return lotid;
    }

    public void setLotid(UUID lotid) {
        this.lotid = lotid;
    }

    public UUID getStackid() {
        return stackid;
    }

    public void setStackid(UUID stackid) {
        this.stackid = stackid;
    }

    public int getBags() {
        return bags;
    }

    public void setBags(int bags) {
        this.bags = bags;
    }

    public String getStackname() {
        return stackname;
    }

    public void setStackname(String stackname) {
        this.stackname = stackname;
    }

    public String getGodownname() {
        return godownname;
    }

    public void setGodownname(String godownname) {
        this.godownname = godownname;
    }

    public String getChambername() {
        return chambername;
    }

    public void setChambername(String chambername) {
        this.chambername = chambername;
    }

    public Date getOndate() {
        return ondate;
    }

    public void setOndate(Date ondate) {
        this.ondate = ondate;
    }

    public String getByuser() {
        return byuser;
    }

    public void setByuser(String byuser) {
        this.byuser = byuser;
    }

}
