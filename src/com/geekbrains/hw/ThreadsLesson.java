package com.geekbrains.hw;

public class ThreadsLesson {
    private final Object monitor = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        ThreadsLesson threadsLesson = new ThreadsLesson();
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadsLesson.printLetterA();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                threadsLesson.printLetterB();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                threadsLesson.printLetterC();
            }
        }).start();
    }

    public void printLetterA() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A') {
                        monitor.wait();
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getCause());
            }
        }
    }

    public void printLetterB() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B') {
                        monitor.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getCause());
            }
        }
    }

    public void printLetterC() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C') {
                        monitor.wait();
                    }
                    System.out.print("C");
                    currentLetter = 'A';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getCause());
            }
        }
    }
}
