package android.com.rxjava;

public enum Type {

    Base(1, "最基本的使用")/*Rxjava的最基本使用方法*/,
    SwitchThread(2, "线程的切换");

    private int index;
    private String desc;

    private Type(int i, String s) {
        this.index = 1;
        this.desc = s;
    }

    public int getIndex() {
        return index;
    }

    public String getDesc() {
        return desc;
    }
}
