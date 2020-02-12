Rxjava 最基本的原理 所有的操作符 都会形成一个新继承ObservableSource的类，订阅上一个类

**1、关于线程的切换**

***subscribeOn***

都会生成ObservableSubscribeOn

问：为什么只有第一次的subsribeon起效？

答：其实每一切换都起效，最初的ObservableOnSubscribe是在第一次切换线程中执行，后续的切换，其实切换是订阅时即（subscribe（））的线程，故网上有这样的说法。

***observeOn***

应该从上往下看，如果未切换线程，就在当前observeOn的指定的线程执行

**2、大体的Rxjava结构**

![img](https://github.com/cy-cyx/Rxjava/blob/master/img/IMG_20200213_023823.jpg)