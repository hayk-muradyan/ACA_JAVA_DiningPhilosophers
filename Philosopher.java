public class Philosopher implements Runnable{
    private Fork leftFork;
    private Fork rightFork;
    private int foodAmount;

    private void eat() throws InterruptedException {
        foodAmount -= 100;
        System.out.println(Thread.currentThread().getName() + " is eating");
        Thread.sleep(1000);
    }
    private void takeLeftFork() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " takes left fork");
        Thread.sleep(100);
    }
    private void takeRightFork() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " takes right fork");
        Thread.sleep(100);
    }
    private void putLeftFork() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " puts left fork");
        Thread.sleep(100);
    }
    private void putRightFork() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " puts right fork");
        Thread.sleep(100);
    }
    @Override
    public void run() {
        try {
            while (foodAmount > 0) {

                synchronized (leftFork) {
                    takeLeftFork();
                    synchronized (rightFork) {
                        // eating
                        takeRightFork();
                        eat();
                        putRightFork();
                    }
                    putLeftFork();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

    public Philosopher(Fork leftFork, Fork rightFork){
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        foodAmount = 1000;
    }
}
