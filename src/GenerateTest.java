public class GenerateTest {
    public static void main(String[] args) {
        int rows = (int)(Math.random() * 1000) + 1;
        int cols = (int)(Math.random() * 40) + 2;

        for(int i = 0; i < rows; i++){
            int middle = (int) (Math.random() * (cols - 4)) + 2;
            int lower = (int) (Math.random() * middle);
            int higher = (int) (Math.random() * cols - middle) + middle;
            System.out.println(lower + "," + higher);
        }
    }
}
