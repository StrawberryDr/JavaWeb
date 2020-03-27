package lesson2;

public class ThreadJoin {
    public static void main1(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        //先将thread这个线程执行完毕后，再往下执行
        //当前线程：代码行执行的时候所在的线程
        //将当前线程进行阻塞（运行态--->阻塞态）等待（满足一定条件）
        //一定条件：①有传入时间；②线程引用对象执行完毕（任意一个完成就满足）
        //thread线程（不做任何处理，继续运行）
        //thread.join();
        thread.join(2000);
        System.out.println(Thread.currentThread().getName());
    }

    public static void without() throws InterruptedException {
        // 打印顺序：main--->thread-0
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        t.start();
        t.join();//等待t线程执行完毕
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        without();
        withoutSleep();
        withSleep();
    }

    public static void withoutSleep() throws InterruptedException{
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        thread.start();
        thread.join(2000);
        System.out.println(Thread.currentThread().getName());
    }

    public static void withSleep() throws InterruptedException{
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        //当前线程main线程等待2s就往下执行
        thread.join(2000);
        System.out.println(Thread.currentThread().getName());
    }


}
