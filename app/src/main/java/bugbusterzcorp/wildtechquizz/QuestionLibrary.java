package bugbusterzcorp.wildtechquizz;


public class QuestionLibrary {

    private String mQuestions [] = {
            "Quel est le nom héroïque du Joueur du Grenier",
            "Dans quelle ville 'Joueur du grenier' vit-il ?",
            "Comment s'appelle en vrai 'Joueur du grenier ?",
            "Quel est le surnom du Joueur du Grenier dans la vidéo des RPG ?"


    };

    private String mChoices [] [] = {
            {"Connar-Man", "Cannard-Man", "Angry-Man" },
            {"Paris", "Toulouse", "Fougères" },
            {"Sébastien Rassiat", "Frédéric Molas", "Yannick Cremer" },
            {"Enfant des jeux pourri", "Enfant à la chemise bizzare", "Enfant des jurrons" },
    };

    private String mCorrectAnswers [] = {"Cannard-Man","Fougères","Frédéric Molas","Enfant des jurrons"};



    public String getQuestion(int a) {
        String question = mQuestions [a];
        return question;
    }

    public int getQuestionNumber(){
        return mQuestions.length;
    };


    public String getChoice1(int a) {
        String choice0 = mChoices[a] [0];
        return choice0;
    }

    public String getChoice2(int a) {
        String choice1 = mChoices[a] [1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = mChoices[a] [2];
        return choice2;
    }

    public String getCorrectAnswers(int a) {
        String answer = mCorrectAnswers[a];
        return answer;

    }



}
