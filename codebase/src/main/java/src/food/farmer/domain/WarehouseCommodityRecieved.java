package src.food.farmer.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * A Warehouse Entry
 */
@Table(name = "warehousecommodityrecieved")
public class WarehouseCommodityRecieved implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private String warehouselicenseno;
    private UUID lotid;

    private String warehousereciept;
    private Date liftedon;
    private String enteredby;
    private UUID commoditycode;
    private double liftedweight;
    private int liftedbags;
    private double liftedgunnyweight;
    private double liftednetweight;
    private UUID depositor;
    private String vehicleno;
    private String drivername;
    private UUID sourcemandi;
    private String sourcewarehouse;
    private String sourcemill;
    private String inwarehouse;
    private String inmill;
    private String status;
    private Date ondate;
    private String bookno;
    private String srno;

    public UUID getLotid() {
        return lotid;
    }

    public void setLotid(UUID lotid) {
        this.lotid = lotid;
    }

    public String getWarehousereciept() {
        return warehousereciept;
    }

    public void setWarehousereciept(String warehousereciept) {
        this.warehousereciept = warehousereciept;
    }

    public Date getLiftedon() {
        return liftedon;
    }

    public void setLiftedon(Date liftedon) {
        this.liftedon = liftedon;
    }

    public String getEnteredby() {
        return enteredby;
    }

    public void setEnteredby(String enteredby) {
        this.enteredby = enteredby;
    }

    public UUID getCommoditycode() {
        return commoditycode;
    }

    public void setCommoditycode(UUID commoditycode) {
        this.commoditycode = commoditycode;
    }

    public double getLiftedweight() {
        return liftedweight;
    }

    public void setLiftedweight(double liftedweight) {
        this.liftedweight = liftedweight;
    }

    public int getLiftedbags() {
        return liftedbags;
    }

    public void setLiftedbags(int liftedbags) {
        this.liftedbags = liftedbags;
    }

    public double getLiftedgunnyweight() {
        return liftedgunnyweight;
    }

    public void setLiftedgunnyweight(double liftedgunnyweight) {
        this.liftedgunnyweight = liftedgunnyweight;
    }

    public double getLiftednetweight() {
        return liftednetweight;
    }

    public void setLiftednetweight(double liftednetweight) {
        this.liftednetweight = liftednetweight;
    }

    public UUID getDepositor() {
        return depositor;
    }

    public void setDepositor(UUID depositor) {
        this.depositor = depositor;
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public UUID getSourcemandi() {
        return sourcemandi;
    }

    public void setSourcemandi(UUID sourcemandi) {
        this.sourcemandi = sourcemandi;
    }

    public String getSourcewarehouse() {
        return sourcewarehouse;
    }

    public void setSourcewarehouse(String sourcewarehouse) {
        this.sourcewarehouse = sourcewarehouse;
    }

    public String getSourcemill() {
        return sourcemill;
    }

    public void setSourcemill(String sourcemill) {
        this.sourcemill = sourcemill;
    }

    public String getInmill() {
        return inmill;
    }

    public void setInmill(String inmill) {
        this.inmill = inmill;
    }

    public String getInwarehouse() {
        return inwarehouse;
    }

    public void setInwarehouse(String inwarehouse) {
        this.inwarehouse = inwarehouse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOndate() {
        return ondate;
    }

    public void setOndate(Date ondate) {
        this.ondate = ondate;
    }

    public String getWarehouselicenseno() {
        return warehouselicenseno;
    }

    public void setWarehouselicenseno(String warehouselicenseno) {
        this.warehouselicenseno = warehouselicenseno;
    }

    public String getBookno() {
        return bookno;
    }

    public void setBookno(String bookno) {
        this.bookno = bookno;
    }

    public String getSrno() {
        return srno;
    }

    public void setSrno(String srno) {
        this.srno = srno;
    }

}
