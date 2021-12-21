package com.example.a12_8.bean;

public class park_jil_bean {
    /**
     * id : 1
     * lotId : 4
     * entryTime : 2021-04-11 17:24:37
     * outTime : 2021-04-11 18:24:45
     * plateNumber : 辽 B12345
     * monetary : 5
     * parkName : 天津市邦仓储威物流停车场
     * parkNo : 1
     * address : 天津市邦仓储威物流
     */

    private int id;
    private int lotId;
    private String entryTime;
    private String outTime;
    private String plateNumber;
    private String monetary;
    private String parkName;
    private String parkNo;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getMonetary() {
        return monetary;
    }

    public void setMonetary(String monetary) {
        this.monetary = monetary;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getParkNo() {
        return parkNo;
    }

    public void setParkNo(String parkNo) {
        this.parkNo = parkNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
