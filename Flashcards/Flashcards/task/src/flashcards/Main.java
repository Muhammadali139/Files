package flashcards;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static String term;
    static String definition;
    static String answer;
    static Scanner in;
    static List<Cards> cards=new ArrayList<>();



    public static void main(String[] args) {
//        List<Cards> cards = createCards();
//        askDefinitions1(cards);
        String status="";
        while (!status.equals(Action.EXIT)){
            status = getStatus();
            if (status.equals(Action.ADD)){
                addOneCard(cards);
            }
            else if (status.equals(Action.REMOVE)){
                remove(cards);
            }
            else if (status.equals(Action.IMPORT)){
                try {
                    importCards();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (status.equals(Action.EXPORT)){
                exportCards();
            }
            else if (status.equals(Action.ASK)){
                askCards();
            }
            else if (status.equals(Action.EXIT)){
                System.out.println("Bye bye!");
            }
            System.out.println();
        }

    }

    private static void addOneCard(List<Cards> cards) {
        System.out.println("The card:");
        term=in.nextLine();
        boolean has=false;
        for (Cards cards1 : cards ) {
            if (cards1.getTerm().equals(term)){
                has=true;
            }
        }
        if (has){
            System.out.println(String.format("The card \"{0}\" already exists.",term));
        }
        else {
            return;
        }
        System.out.println("The definition of the card:");
        definition=in.nextLine();

        has=false;

        for (Cards cards1 : cards ) {
            if (cards1.getDefinition().equals(definition)){
                has=true;
            }
        }
        if (has){
            System.out.println(String.format("The definition \"{0}\" already exists.",definition));
        }

        System.out.println(String.format("The pair (\"{0}\":\"{1}\") has benn added."));
    }

    private static void askCards() {
        
    }

    private static void exportCards() {

    }

    private static void importCards() throws IOException {
        System.out.println("File name:");
        String fileName=in.nextLine();
        File file=new File(Main.class.getResource(fileName).getPath());
        if (!file.exists()){
            System.out.println("File not found.");
        }
        else {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                int n=0;
                while ((line = reader.readLine()) != null){
                    term = line;
                    definition = reader.readLine();
                    cards.add(new Cards(term,definition));
                    n++;
                }
            System.out.println(n+" cards have been loaded.");
        }
    }

    private static void remove(List<Cards> cards) {
        System.out.println("The card:");
        term=in.nextLine();
        boolean has=false;
        for (Cards cards1 : cards) {
            if (cards1.getTerm().equals(term)){
                cards.remove(cards1);
                has=true;
            }
        }
        if (!has){
            System.out.printf("Can't remove \"%s\": there is no such card.%n", term);
        }else {
            System.out.println("The card has been removed.");
        }
    }

    private static String getStatus() {
        System.out.println("Input the action (add, remove, import, export, export, ask, exit):");
        return in.nextLine();
    }

    private static void askDefinitions1(List<Cards> cards) {
        for(int i = 0; i < cards.size(); ++i) {
            Cards currentCard = cards.get(i);
            System.out.printf("Print the definition of \"%s\":\n", currentCard.getTerm());
            answer = in.nextLine();
            if (check(currentCard.getTerm(), currentCard.getDefinition(), answer)) {
                System.out.println("Correct answer");
            } else {
                boolean has = false;

                for (Cards cards1: cards) {
                    if (cards1.getDefinition().equals(answer)) {
                        System.out.println("Wrong answer. The correct one is \"" +currentCard.getDefinition()  + "\", you've just written the definition of \"" + cards1.getTerm() + "\".");
                        has = true;
                    }
                }
                if (!has) {
                    System.out.println("Wrong answer. The correct one is \""+currentCard.getDefinition()+"\"");
                }
            }
        }
    }

    private static List<Cards> createCards() {
        List<Cards> cards = new ArrayList();
        System.out.println("Input the number of cards:");
        int size = Integer.parseInt(in.nextLine());

        for(int i = 0; i < size; ++i) {
            addCard(cards);
        }

        return cards;
    }

    private static void addCard(List<Cards> cards) {
        System.out.println("The card:");

        getTermForCreation(cards);

        System.out.println("The definition of the card:");

        getDefinitionForCreation(cards);

        System.out.println("The pair (\""+term+"\":\""+definition+"\") has been added.");

        cards.add(new Cards(term, definition));
    }

    private static void getTermForCreation(List<Cards> cards) {
        boolean has;
        while(true) {
            has = false;
            term = in.nextLine();
            for (Cards cards1 : cards) {
                if (cards1.getTerm().equals(term)){
                    System.out.println("The card \"" + term + "\" already exists. Try again:");
                    has = true;
                }
            }
            if (!has){
                break ;
            }
        }
    }

    private static void getDefinitionForCreation(List<Cards> cards) {
        boolean has;
        while (true){
            definition = in.nextLine();
            has = false;

            for (Cards cards1 :cards) {
                if (cards1.getDefinition().equals(definition)){
                    System.out.println("The definition \"" + definition + "\" already exists. Try again:");
                    has = true;
                }
            }
            if (!has){
                break;
                }
            }
    }

    private static boolean check(String term, String definition, String answer) {
        if (definition == null || answer == null || term == null) {
            System.out.println("Definition, answer, term mustn't be null");
        }

        return definition.equals(answer);
    }

    private static void printCard(String term, String definition) {
        System.out.println("Card:");
        System.out.println(term);
        System.out.println("Definition:");
        System.out.println(definition);
    }

    static {
        in = new Scanner(System.in);
    }

    private static class Cards {
        private String term;
        private String definition;

        public Cards() {
        }

        public Cards(String term, String definition) {
            this.term = term;
            this.definition = definition;
        }

        public String getTerm() {
            return this.term;
        }

        public void setTerm(String term) {
            this.term = term;
        }

        public String getDefinition() {
            return this.definition;
        }

        public void setDefinition(String definition) {
            this.definition = definition;
        }
    }

}
interface Action{
    String ADD="add";
    String REMOVE="remove";
    String IMPORT="import";
    String EXPORT="export";
    String ASK="ask";
    String EXIT="exit";
}
