package lesson2;

public class Advantage {
    //private static final int COUNT = 10_0000_0000;

    private static final int NUM = 2;

    private static void increment(){
        int count = 10_0000_0000;
        for(int i = 0; i < count;i++){
            count++;
        }
//        for(int i = 0; i < COUNT;i++){
//            i++;
//        }
    }

    //串行：依次执行
    private static void serial(){
        long start = System.nanoTime(); //纳秒
        //long start = System.currentTimeMillis(); //返回一个从1970-01-01开始到当前时间的毫秒数
        for(int i = 0;i < NUM;i++){
            increment();
        }
        long end = System.nanoTime();
        System.out.printf("串行的执行时间是：%s毫秒\n",(end-start)/1000/1000);
    }

    //并行/并发：（有时候Java语义里面，并发这个词既表达并发，也表达并行）
    private static void parallel(){
        long start = System.nanoTime();
        for(int i = 0;i < NUM;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    increment();
                }
            }).start();
        }
        //等待new thread所有线程执行完毕，否则将会一直等待
        while (Thread.activeCount() > 1){//活跃线程数>1，等待
            Thread.yield();  //将当前线程又运行状态转为就绪状态
        }
        long end = System.nanoTime();
        System.out.printf("并行的执行时间是：%s毫秒\n",(end-start)/1000/1000);
    }

    public static void main(String[] args) {
        serial();
        parallel();
    }
}
