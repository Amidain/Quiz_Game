import javax.imageio.IIOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args)throws IIOException {
        List<Quiz_Round> rounds = readFileWithQuestion(chooseCategory());
        int score = 0;

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int rand = random.nextInt(rounds.size());
            Quiz_Round quiz_round = rounds.get(rand);
            System.out.println("Question-" + i +": "+ quiz_round.getQuestion());
            System.out.println("Choose answer: " + quiz_round.shuffleAnswers());
            System.out.println("Your answer is: ...");
            Scanner scanner = new Scanner(System.in);
            String userResponse = scanner.nextLine();
            if (quiz_round.checkIfAnswerIsCorrect(userResponse)) {
                System.out.println(true);
                score++;
            } else {
                System.out.println("Correct answer was: " + quiz_round.getCorrectAnswer());
            }
        }

        if (score >= 7) {
            System.out.println("Congratulations! Your final score is: " + score);
        } else if (score >= 4 && score < 7) {
            System.out.println("Not bad! Your final score is: " + score);
        } else if (score >= 0 && score < 4) {
            System.out.println("Buck up! Your final score is: " + score);
        }
    }

    private static File chooseCategory(){
        File folder = new File("C:\\Users\\48515\\OneDrive\\Pulpit\\JAVA_Practise\\Projects\\Quiz_Game\\src\\Questions");
        File[] categories = folder.listFiles();

        System.out.println("Choose one of the following categories: ");
        for (int i = 0; i < categories.length; i++) {
            String categoryName = categories[i].getName();
            categoryName = categoryName.replace(".txt", "").replace("__"," ").replace("_"," ");
            System.out.println((i +1) + ")" + " " + categoryName);
        }
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();

        int chosenCategoryNum = Integer.parseInt(response) - 1;
        File file = categories[chosenCategoryNum];
        System.out.println("Chosen category is: " + file.getName().replace(".txt", "").replace("__"," ").replace("_"," "));
        return file;
    }

    private static List<Quiz_Round> readFileWithQuestion(File file) throws IIOException{
        List<Quiz_Round> rounds = new ArrayList<>();

            try{
                FileReader fileReader = new FileReader(file);
                Scanner scanner = new Scanner(fileReader);

                while (scanner.hasNext()){
                    String question = scanner.nextLine();
                    String tempNum = scanner.nextLine();
                    int numberOfAnswers = Integer.parseInt(tempNum);

                    String[] possibleAnswers = new String[numberOfAnswers];
                    for (int i = 0; i < possibleAnswers.length; i++) {
                        possibleAnswers[i] = scanner.nextLine();
                    }

                    Quiz_Round quiz_round = new Quiz_Round(question, possibleAnswers);
                    rounds.add(quiz_round);
                }
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
            return rounds;
    }
}
