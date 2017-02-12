/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.food.farmer.web.rest.dto;

import java.util.UUID;

/**
 *
 * @author sumit.garg
 */
public class WarehouseStockInStackDTO {
    private String warehouselicenseno;
    private UUID stackid;

    public String getWarehouselicenseno() {
        return warehouselicenseno;
    }

    public void setWarehouselicenseno(String warehouselicenseno) {
        this.warehouselicenseno = warehouselicenseno;
    }

    public UUID getStackid() {
        return stackid;
    }

    public void setStackid(UUID stackid) {
        this.stackid = stackid;
    }
    
}
