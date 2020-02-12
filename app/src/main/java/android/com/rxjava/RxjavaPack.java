package android.com.rxjava;

import android.com.rxjava.rxjava.Base;
import android.com.rxjava.rxjava.BaseImpl;

public class RxjavaPack implements Base {

    private BaseImpl base;

    public RxjavaPack() {
        base = new BaseImpl();
    }
    @Override
    public void simpleUse() {
        base.simpleUse();
    }

    @Override
    public void switchThread() {
        base.switchThread();
    }
}
