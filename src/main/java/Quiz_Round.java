import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz_Round {
    private String question;
    private String[] possibleAnswers;
    private String correctAnswer;

    public Quiz_Round(String question, String[] possibleAnswers) {
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = possibleAnswers[0];
    }

    public String getQuestion() {
        return question;
    }

    public String[] getPossibleAnswers() {
        return possibleAnswers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> shuffleAnswers (){
        List<String> answers = new ArrayList<>();

        for (int i = 0; i < possibleAnswers.length; i++) {
            answers.add(possibleAnswers[i]);
        }
        Collections.shuffle(answers);

        return answers;
    }

    public boolean checkIfAnswerIsCorrect (String userAnswer){
        return (userAnswer.toLowerCase()).equals(correctAnswer.toLowerCase());
    }
}
