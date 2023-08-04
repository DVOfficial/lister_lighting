package com.listerled.listerlighting;

public class Class_ListerCounter {


String Sno, Model_Name,COLOR,BRAND,OPENING_STOCK,IN_QTY,Out,CURRENT_STOCK,Location,ImageLink,Date;

        public Class_ListerCounter() {
        }

        public Class_ListerCounter(String model_Name, String COLOR, String BRAND, String CURRENT_STOCK, String imageLink, String date) {
                Model_Name = model_Name;
                this.COLOR = COLOR;
                this.BRAND = BRAND;
                this.CURRENT_STOCK = CURRENT_STOCK;
                ImageLink = imageLink;
                Date = date;
        }

        public Class_ListerCounter(String sno, String model_Name, String COLOR, String BRAND, String OPENING_STOCK, String IN_QTY, String out, String CURRENT_STOCK, String location, String imageLink, String date) {
                Sno = sno;
                Model_Name = model_Name;
                this.COLOR = COLOR;
                this.BRAND = BRAND;
                this.OPENING_STOCK = OPENING_STOCK;
                this.IN_QTY = IN_QTY;
                Out = out;
                this.CURRENT_STOCK = CURRENT_STOCK;
                Location = location;
                ImageLink = imageLink;
                Date = date;
        }

        public String getSno() {
                return Sno;
        }

        public void setSno(String sno) {
                Sno = sno;
        }

        public String getModel_Name() {
                return Model_Name;
        }

        public void setModel_Name(String model_Name) {
                Model_Name = model_Name;
        }

        public String getCOLOR() {
                return COLOR;
        }

        public void setCOLOR(String COLOR) {
                this.COLOR = COLOR;
        }

        public String getBRAND() {
                return BRAND;
        }

        public void setBRAND(String BRAND) {
                this.BRAND = BRAND;
        }

        public String getOPENING_STOCK() {
                return OPENING_STOCK;
        }

        public void setOPENING_STOCK(String OPENING_STOCK) {
                this.OPENING_STOCK = OPENING_STOCK;
        }

        public String getIN_QTY() {
                return IN_QTY;
        }

        public void setIN_QTY(String IN_QTY) {
                this.IN_QTY = IN_QTY;
        }

        public String getOut() {
                return Out;
        }

        public void setOut(String out) {
                Out = out;
        }

        public String getCURRENT_STOCK() {
                return CURRENT_STOCK;
        }

        public void setCURRENT_STOCK(String CURRENT_STOCK) {
                this.CURRENT_STOCK = CURRENT_STOCK;
        }

        public String getLocation() {
                return Location;
        }

        public void setLocation(String location) {
                Location = location;
        }

        public String getImageLink() {
                return ImageLink;
        }

        public void setImageLink(String imageLink) {
                ImageLink = imageLink;
        }

        public String getDate() {
                return Date;
        }

        public void setDate(String date) {
                Date = date;
        }
}
