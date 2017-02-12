package src.food.farmer.web.rest.dto;

public class WarehouseDTO {

    private String warehouselicenseno;

    private String warehousename;
    private String address;
    private int godowns;
    private int chambers;
    private int stacks;
    private double capacityinmt;
    private int capacityinbags;
    private int active;
    private String stacktype;
    private String warehousetype;

    public String getWarehouselicenseno() {
        return warehouselicenseno;
    }

    public void setWarehouselicenseno(String warehouselicenseno) {
        this.warehouselicenseno = warehouselicenseno;
    }

    public String getWarehousename() {
        return warehousename;
    }

    public void setWarehousename(String warehousename) {
        this.warehousename = warehousename;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGodowns() {
        return godowns;
    }

    public void setGodowns(int godowns) {
        this.godowns = godowns;
    }

    public int getChambers() {
        return chambers;
    }

    public void setChambers(int chambers) {
        this.chambers = chambers;
    }

    public int getStacks() {
        return stacks;
    }

    public void setStacks(int stacks) {
        this.stacks = stacks;
    }

    public double getCapacityinmt() {
        return capacityinmt;
    }

    public void setCapacityinmt(double capacityinmt) {
        this.capacityinmt = capacityinmt;
    }

    public int getCapacityinbags() {
        return capacityinbags;
    }

    public void setCapacityinbags(int capacityinbags) {
        this.capacityinbags = capacityinbags;
    }

    public String getStacktype() {
        return stacktype;
    }

    public void setStacktype(String stacktype) {
        this.stacktype = stacktype;
    }

    public String getWarehousetype() {
        return warehousetype;
    }

    public void setWarehousetype(String warehousetype) {
        this.warehousetype = warehousetype;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
