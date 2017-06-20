package cn.tokiz.mddemo;

/**
 * Created by lounnig on 2017/6/11.
 */

public class Item {
    private int resid;
    private String name;

    public Item(int resid, String name) {
        this.resid = resid;
        this.name = name;
    }

    public Item() {
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
