package com.form.model.data;

/**
 * Price Tag - has the Price in integer and String format (for HTML)
 *
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
public class PriceTag {
    /** Price - Integer Format */
    private int price;
    /** Price - String Format [HTML]*/
    private String priceStr;

    /** Constructor */
    public PriceTag() {

    }

    /** Getters and Setters */
    public String getPriceStr() {
        return priceStr;
    }

    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
