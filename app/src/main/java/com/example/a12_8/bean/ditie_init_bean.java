package com.example.a12_8.bean;

import java.util.List;

public class ditie_init_bean {
    /**
     * id : 1
     * name : 16 号线
     * first : 西苑
     * end : 北安河
     * startTime : 05:25
     * endTime : 23:00
     * cityId : 1
     * stationsNumber : null
     * km : 20
     * runStationsName : 马连洼
     * metroStepList : [{"searchValue":null,"createBy":null,"createTime":"2018-07-23 02:28:36","updateBy":null,"updateTime":"2021-04-05 15:46:38","remark":null,"params":{},"id":1,"name":"西苑","seq":0,"lineId":1,"firstCh":"X"}]
     * remainingTime : 4
     */

    private int id;
    private String name;
    private String first;
    private String end;
    private String startTime;
    private String endTime;
    private int cityId;
    private Object stationsNumber;
    private int km;
    private String runStationsName;
    private int remainingTime;
    private List<MetroStepListBean> metroStepList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public Object getStationsNumber() {
        return stationsNumber;
    }

    public void setStationsNumber(Object stationsNumber) {
        this.stationsNumber = stationsNumber;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getRunStationsName() {
        return runStationsName;
    }

    public void setRunStationsName(String runStationsName) {
        this.runStationsName = runStationsName;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public List<MetroStepListBean> getMetroStepList() {
        return metroStepList;
    }

    public void setMetroStepList(List<MetroStepListBean> metroStepList) {
        this.metroStepList = metroStepList;
    }

    public static class MetroStepListBean {
        /**
         * searchValue : null
         * createBy : null
         * createTime : 2018-07-23 02:28:36
         * updateBy : null
         * updateTime : 2021-04-05 15:46:38
         * remark : null
         * params : {}
         * id : 1
         * name : 西苑
         * seq : 0
         * lineId : 1
         * firstCh : X
         */

        private Object searchValue;
        private Object createBy;
        private String createTime;
        private Object updateBy;
        private String updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String name;
        private int seq;
        private int lineId;
        private String firstCh;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public int getLineId() {
            return lineId;
        }

        public void setLineId(int lineId) {
            this.lineId = lineId;
        }

        public String getFirstCh() {
            return firstCh;
        }

        public void setFirstCh(String firstCh) {
            this.firstCh = firstCh;
        }

        public static class ParamsBean {
        }
    }
}
