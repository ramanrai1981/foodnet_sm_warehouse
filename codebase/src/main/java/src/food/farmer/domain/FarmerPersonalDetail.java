package src.food.farmer.domain;

import com.datastax.driver.mapping.annotations.*;

import java.io.Serializable;

/**
 * A Farmer's Personal Detail.
 */
@Table(name = "personaldetail")
public class FarmerPersonalDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey
    private String id;

    private String farmername;
    private String fathername;
    private String mothername;
    private String gender;
    private String category;
    private String aadhaarno;
    private String voterid;
    private String mobileno;
    private String vehicleno;
    private String bankname;
    private String branchname;
    private String accountno;
    private String ifsccode;
    private String farmingland;
    private String kullrakhba;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFarmername() {
        return farmername;
    }

    public void setFarmername(String farmername) {
        this.farmername = farmername;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAadhaarno() {
        return aadhaarno;
    }

    public void setAadhaarno(String aadhaarno) {
        this.aadhaarno = aadhaarno;
    }

    public String getVoterid() {
        return voterid;
    }

    public void setVoterid(String voterid) {
        this.voterid = voterid;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getIfsccode() {
        return ifsccode;
    }

    public void setIfsccode(String ifsccode) {
        this.ifsccode = ifsccode;
    }

    public String getFarmingland() {
        return farmingland;
    }

    public void setFarmingland(String farmingland) {
        this.farmingland = farmingland;
    }

    public String getKullrakhba() {
        return kullrakhba;
    }

    public void setKullrakhba(String kullrakhba) {
        this.kullrakhba = kullrakhba;
    }

   

    
  
   
}
