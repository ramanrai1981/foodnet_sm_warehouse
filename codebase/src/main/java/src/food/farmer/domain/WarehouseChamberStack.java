package src.food.farmer.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * A Warehouse Entry
 */
@Table(name = "warehousechamberstack")
public class WarehouseChamberStack implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private String warehouselicenseno;

    private UUID stackid;
    private UUID depositor;

    private UUID godownid;
    private UUID chamberid;

    private String godownname;
    private String chambername;
    private String stackname;
    private Date fromdate;
    private Date todate;
    private UUID commoditycode;
    private double capacityinbags;
    private String stacktype;

    public String getWarehouselicenseno() {
        return warehouselicenseno;
    }

    public void setWarehouselicenseno(String warehouselicenseno) {
        this.warehouselicenseno = warehouselicenseno;
    }

    public UUID getDepositor() {
        return depositor;
    }

    public void setDepositor(UUID depositor) {
        this.depositor = depositor;
    }

   

    public UUID getGodownid() {
        return godownid;
    }

    public void setGodownid(UUID godownid) {
        this.godownid = godownid;
    }

    public UUID getChamberid() {
        return chamberid;
    }

    public void setChamberid(UUID chamberid) {
        this.chamberid = chamberid;
    }

    public UUID getStackid() {
        return stackid;
    }

    public void setStackid(UUID stackid) {
        this.stackid = stackid;
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

    public String getStackname() {
        return stackname;
    }

    public void setStackname(String stackname) {
        this.stackname = stackname;
    }

    public Date getFromdate() {
        return fromdate;
    }

    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }

    public Date getTodate() {
        return todate;
    }

    public void setTodate(Date todate) {
        this.todate = todate;
    }

    public UUID getCommoditycode() {
        return commoditycode;
    }

    public void setCommoditycode(UUID commoditycode) {
        this.commoditycode = commoditycode;
    }

    public double getCapacityinbags() {
        return capacityinbags;
    }

    public void setCapacityinbags(double capacityinbags) {
        this.capacityinbags = capacityinbags;
    }

    public String getStacktype() {
        return stacktype;
    }

    public void setStacktype(String stacktype) {
        this.stacktype = stacktype;
    }

}
