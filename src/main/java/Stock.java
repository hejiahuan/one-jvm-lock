/**
 * 库存
 * @author HJH
 * @create 2020-07-04 0:05
 */
public class Stock {

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
}
