public class Main {
    public static void main(String[] args) {
        ExamChecking assistant1 = new ExamChecking(6);
        ExamChecking assistant2 = new ExamChecking(4);

        assistant1.setName("Zeinep");
        assistant2.setName("Diana");

        assistant2.setPriority(Thread.MAX_PRIORITY);

        assistant1.start();
        assistant2.start();
    }
}