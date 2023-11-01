public class L8E2 {

    public static void main(String[] args) {
        int[] Scores = {69, 83, 20, 4, 92, 50, 15, 37, 90, 49};

        for (int value:Scores){
            System.out.println(value);
        }

        Double Average = (double) (Scores[0] + Scores[1] + Scores[2] + Scores[3] + Scores[4] + Scores[5] + Scores[6] + Scores[7]
                + Scores[8] + Scores[9]) / 10;
        System.out.println( Average );

    }
}
