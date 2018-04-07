package com.example.yuekao.model.bean;

import java.util.List;

/**
 * Created by 王有道 on 2018/4/7.
 */

public class GoodsBean {


    /**
     * list : [{"bargainPrice":6666,"createtime":"2017-10-10T16:01:31","detailUrl":"https://item.m.jd.com/product/5089273.html?utm#_source=androidapp&utm#_medium=appshare&utm#_campaign=t#_335139774&utm#_term=QQfriends","images":"https://m.360buyimg.com/n0/jfs/t8284/363/1326459580/71585/6d3e8013/59b857f2N6ca75622.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t9346/182/1406837243/282106/68af5b54/59b8480aNe8af7f5c.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t8434/54/1359766007/56140/579509d9/59b85801Nfea207db.jpg!q70.jpg","num":1,"pid":46,"price":234,"pscid":39,"selected":0,"sellerid":2,"subhead":"【iPhone新品上市】新一代iPhone，让智能看起来更不一样","title":"Apple iPhone 8 Plus (A1864) 64GB 金色 移动联通电信4G手机"}]
     * sellerName : 商家2
     * sellerid : 2
     */

    private String sellerName;
    private String sellerid;
    private List<ListBean> list;
    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * bargainPrice : 6666
         * createtime : 2017-10-10T16:01:31
         * detailUrl : https://item.m.jd.com/product/5089273.html?utm#_source=androidapp&utm#_medium=appshare&utm#_campaign=t#_335139774&utm#_term=QQfriends
         * images : https://m.360buyimg.com/n0/jfs/t8284/363/1326459580/71585/6d3e8013/59b857f2N6ca75622.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t9346/182/1406837243/282106/68af5b54/59b8480aNe8af7f5c.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t8434/54/1359766007/56140/579509d9/59b85801Nfea207db.jpg!q70.jpg
         * num : 1
         * pid : 46
         * price : 234
         * pscid : 39
         * selected : 0
         * sellerid : 2
         * subhead : 【iPhone新品上市】新一代iPhone，让智能看起来更不一样
         * title : Apple iPhone 8 Plus (A1864) 64GB 金色 移动联通电信4G手机
         */
        private boolean checked;
        private double bargainPrice;
        private String createtime;
        private String detailUrl;
        private String images;
        private int num;
        private int pid;
        private double price;
        private int pscid;
        private int selected;
        private int sellerid;
        private String subhead;
        private String title;

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public double getBargainPrice() {
            return bargainPrice;
        }

        public void setBargainPrice(double bargainPrice) {
            this.bargainPrice = bargainPrice;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getPscid() {
            return pscid;
        }

        public void setPscid(int pscid) {
            this.pscid = pscid;
        }

        public int getSelected() {
            return selected;
        }

        public void setSelected(int selected) {
            this.selected = selected;
        }

        public int getSellerid() {
            return sellerid;
        }

        public void setSellerid(int sellerid) {
            this.sellerid = sellerid;
        }

        public String getSubhead() {
            return subhead;
        }

        public void setSubhead(String subhead) {
            this.subhead = subhead;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
