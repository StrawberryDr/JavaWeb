package lesson2;

public class MyThread{
    public static void main(String[] args) {
        //创建线程方式1
        OverMyThread overMyThread = new OverMyThread();
        overMyThread.start();  //真实代码，启动了overMyThread线程，执行的是overMyThread中的代码
        overMyThread.run();  //实例方法的调用，并没有启动线程，所处线程依然是main线程
//
//        new OverMyThread().start();  //启动线程
//
//        //创建线程方式1
//        new Thread(new MyRunnable()).start();  //启动线程通过start方法
    }
}

class OverMyThread extends Thread {
    @Override
    public void run() {  //run是线程运行的时候执行的代码块
        //代码
        System.out.println(Thread.currentThread().getName());
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
