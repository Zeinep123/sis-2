class ExamChecking extends Thread {
    static int totalSheets = 500;
    int sheetsToCheck;

    public ExamChecking(int sheetsToCheck) {
        this.sheetsToCheck = sheetsToCheck;
    }

    @Override
    public void run() {
        for (int i = 0; i < sheetsToCheck; i++) {
            synchronized (ExamChecking.class) {
                if (totalSheets <= 0) {
                    System.out.println("There is no any exam sheet left! All papers were already checked!!!");
                    return;
                }

                totalSheets -= 50;
                System.out.println(Thread.currentThread().getName() + " finished checking, at: " +
                        java.time.LocalDateTime.now() + ", exam sheet count is now " + totalSheets);
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted!");
            }
        }
    }
}


