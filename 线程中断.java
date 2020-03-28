package lesson3;

import org.omg.CORBA.PUBLIC_MEMBER;

public class InterruptThread {

    //中断一个线程，但是线程没有处理中断
    public static void test1(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                }
            }
        });
        thread.start();
        thread.interrupt();
    }


    public static void test2() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //线程运行状态时，需要自行判断线程的中断标志位，处理中断操作
//                for(int i = 0;i < 50;i++){
//                    System.out.println(i + "=" + Thread.currentThread().isInterrupted());
//                }
//                while (Thread.currentThread().isInterrupted()){
//                while (!Thread.currentThread().isInterrupted()){
                while (!Thread.interrupted()){
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });
        thread.start(); //thread线程中的中断标志位=false
        thread.interrupt();  //thread线程的中断标志位=true
    }


    public static void test3(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().isInterrupted());  //true
                    //线程处于调用wait()/join()/sleep()阻塞时
                    //如果把当前线程给中断，会直接抛出一个异常
                    //阻塞状态时通过捕获及处理异常来进行中断线程的逻辑处理
                    //抛出异常以后，线程中断标志位会进行重置
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().isInterrupted());  //false
                }
            }
        });
        thread.start(); //thread线程中的中断标志位=false
        thread.interrupt();  //thread线程的中断标志位=true
    }


    public static void test4(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
//                    System.out.println(Thread.interrupted());//返回中断标志位,并重置标志位
                    System.out.println(Thread.currentThread().isInterrupted()); //返回中断标志位，但不重置
                }
            }
        });
        thread.start();
        thread.interrupt();
    }


    private static volatile boolean IS_Interrupted;
    //使用自定义的中断标志位
    public static void test5(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //使用自定义的标志位能满足线程处于运行态的中断操作
//                while (!IS_Interrupted){
//                    System.out.println(Thread.currentThread().getName());
//                }
                //自定义的标志位满足不了线程处于阻塞状态时的中断操作
                try {
                    Thread.sleep(9999);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        IS_Interrupted = true;
    }

    public static void main(String[] args) throws Exception {
//        test2();
//        test3();
        test5();
    }
}
