/**
 * @author HJH
 * @create 2020-07-04 0:07
 */
public class StockMain {

    static class StockTread implements Runnable{

        public void run() {
            //调用stock减少库存
            boolean s=new Stock().reduceStock();
            if(s){
                System.out.println(Thread.currentThread().getName()+"减少库存成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"减少库存失败");
            }
        }
    }

    public static void main(String[] args) {

        new Thread(new StockTread(),"线程1").start();
        new Thread(new StockTread(),"线程2").start();
    }
}
