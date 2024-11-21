public class Main {
    public static void main(String[] args) {
        ExamChecking checker1 = new ExamChecking(6);
        ExamChecking checker2 = new ExamChecking(4);

        checker1.setName("Zeinep");
        checker2.setName("Diana");

        checker2.setPriority(Thread.MAX_PRIORITY);

        checker1.start();
        checker2.start();
    }
}
