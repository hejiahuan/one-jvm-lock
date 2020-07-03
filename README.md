1单jvm做了没加锁的情况。一个成功一个失败。

2如果给线程sleep,那么就会有问题了，库存都减成功的问题。

```
"D:\Program Files\Java\jdk1.8.0_151\bin\java.exe" "-javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2018.3.2\lib\idea_rt.jar=10514:D:\Program Files\JetBrains\IntelliJ IDEA 2018.3.2\bin" -Dfile.encoding=UTF-8 -classpath "D:\Program Files\Java\jdk1.8.0_151\jre\lib\charsets.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\deploy.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\access-bridge-64.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\cldrdata.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\dnsns.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\jaccess.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\jfxrt.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\localedata.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\nashorn.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\sunec.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\sunjce_provider.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\sunmscapi.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\sunpkcs11.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\ext\zipfs.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\javaws.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\jce.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\jfr.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\jfxswt.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\jsse.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\management-agent.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\plugin.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\resources.jar;D:\Program Files\Java\jdk1.8.0_151\jre\lib\rt.jar;E:\one-jvm-lock\target\classes" StockMain
线程1减少库存成功
线程2减少库存成功
```



```
 private static  int num=1;

    /**
     * 减少库存
     * @return
     */
    public boolean reduceStock(){
        if (num>0){
            try {
                Thread.sleep(1000);
                num--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return  true;
        }else {
            return  false;
        }

    }
```

1. 当线程1进来，num此时是1，那么1>0，这个时候线程睡一秒钟
2. 当线程1睡的时候，线程2，num也还是1，毕竟线程1还没有执行，这个时候1>0,线程2也睡1秒钟
3. 线程 1睡够了，num-- 
4. 线程2睡够了(此时线程2也在num>0的条件内，进来了) num--
5. 会造成多减库存的情况。

解决方案

1同步代码块(synchronzied)

2ReentrantLock 