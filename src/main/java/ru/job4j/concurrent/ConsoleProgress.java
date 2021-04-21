package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        int count = 0;
        char[] process = {'\\', '|', '/', '-'};
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\r load: " + process[count++]);
            if (count == 4) {
                count = 0;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1);
        progress.interrupt();
    }
}
