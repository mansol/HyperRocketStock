package com.example.oem.hyperrocketstock;

import java.util.List;

/**
 * Created by oem on 3/13/17.
 */

    public class Stock implements Cloneable{

        public Stock(String symbol, String name, double price, double prev, double pcvold, double pchange, boolean flag) {

            super();

            this.symbol = symbol;
            this.name = name;
            this.price = price;
            this.prev = prev;
            this.pcvold = pcvold;
            this.pchange = pchange;
            this.flag = flag; // 1 for Positive/Winner Stock; 0 for Negative/Loser Stock
        }

        public Stock() {
            super();
        }

        private String symbol;
        private String name;
        private double price;
        private double prev;
        private double pcvold;
        private double pchange;
        private boolean flag;

        public String getSymbol() {
            return symbol;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public double getPrev() {return prev; }

        public double getPcvold() {return pcvold; }
        public double getPchange() {return pchange; }

        public boolean getFlag(){return flag;}

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setPcvold(double pcvold){ this.pcvold = pcvold;}
        public void setPchange(double pchange) {this.pchange = pchange;}

        public void setFlag(boolean flag){this.flag = flag;}


        @Override
        public Object clone(){
            return new Stock(symbol, name, price, prev, pcvold, pchange, flag);
        }
        
        @Override
        public String toString(){
            return name + "(" + symbol + "): $" + price + " Prev$=" + prev +
                    " %change=" +pchange + " %AvgVol=" + pcvold;
        }
}
