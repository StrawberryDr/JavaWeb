package lesson2;

public class ThreadYield {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
        while (Thread.activeCount() > 1){  //获取活跃线程数，将当前运行进程让步（运行态--->就绪态）
            Thread.yield();  //进程让步
        }
        System.out.println(Thread.currentThread().getName());
    }
}
