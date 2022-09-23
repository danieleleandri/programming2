package un2Cor;

/**
 This program implements these rules to generate random sentences.  A lot of sentences make no
 sense (but still follow the syntax).   Note that an optional item like
 [ <adjective ] has a chance of being used, depending on the value of some
 randomly generated number.


  Some rules that capture the syntax of this verse:

    <sentence> ::= <simple_sentence> [ <conjunction> <sentence> ]

<simple_sentence> ::= <noun_phrase> <verb_phrase>

<noun_phrase> ::= <proper_noun> |
<determiner> [ <adjective> ]. <common_noun> [ who <verb_phrase> ]

<verb_phrase> ::= <intransitive_verb> |
<transitive_verb> <noun_phrase> |
is <adjective> |
believes that <simple_sentence>

<conjunction> ::= and | or | but | because

<proper_noun> ::= Fred | Jane | Richard Nixon | Miss America

<common_noun> ::= man | woman | fish | elephant | unicorn

<determiner> ::= a | the | every | some

<adjective> ::= big | tiny | pretty | bald

<intransitive_verb> ::= runs | jumps | talks | sleeps

<transitive_verb> ::= loves | hates | sees | knows | looks for | finds

  The program generates and outputs one random sentence every three seconds until
  it is halted (for example, by typing Control-C in the terminal window where it is
  running).

 This code can be simplified in many areas had I more time.  Many methods and calls were created as a brain storm, many of which can
 be combined or simplified.  As it is, the code works and is readable.

 @author unknown
 @version %I%, %G%
 @since 1.1
*/

    public class SimpleRandomSentences {

        static final String[] conjunction = {" and ", " or ", " but ", " because "};

        static final String[] proper_nouns = {" Fred ", " Jane ", " Richard Nixon ", " Miss America "};

        static final String[] common_nouns = {" man ", " woman ", " fish ", " elephant ", " unicorn "};
        static final String[] determiner = {" a ", " the ", " every ", " some "};

        static final String[] adjective = {" big ", " tiny ", " pretty ", " bald "};

        static final String[] intransitive_verb = {" runs ", " jumps ", " talks ", " sleeps "};

        static final String[] transitive_verb = {" loves ", " hates ", " sees ", " knows ", " looks for ", " finds "};


        public static void main(String[] args) {

            while (true) {
                System.out.println(randomSentence().trim() + ".\n\n");
                try {
                    Thread.sleep(3000); // provides a pause between sentences for user readability
                } catch (InterruptedException e) {
                    System.out.println("Error. Main");
                }
            }
        }

    /**
     *
     * @return randomSentence recursion
     */
    static String randomSentence() {

            if (Math.random() >= 0.2) {
                return (simpleSentence());
            } else if (Math.random() < 0.2) {
                    return (simpleSentence() + randomConjunction() + randomSentence());
                }
            return randomSentence();
            }

    /**
     *
     * @return simpleSentence rule construction
     */
    static String simpleSentence() {

            return randomNounPhrase() + randomVerbPhrase();
        }

    /**
     *
     * @return returns construction of sentences based on rules, and random options.  There is a 25% chance for each version of the sentences.
     */
        static String randomNounPhrase() {

            if ((properCheck())) {
                return SimpleRandomSentences.proper_nouns[(int) (Math.random() * proper_nouns.length)];
            } else if (!(properCheck())) {
                    if (Math.random() > 0.75){
                        return randomDeterminer() + randomAdjective() + common_nouns[(int)(Math.random()* common_nouns.length)] + " who " + randomVerbPhrase();
                    } else if (Math.random() > 0.5) {
                        return randomDeterminer() + randomAdjective() + common_nouns[(int)(Math.random() * common_nouns.length)];
                    } else if (Math.random() > 0.25){
                        return randomDeterminer() + common_nouns[(int)(Math.random() * common_nouns.length)] + " who " + randomVerbPhrase();
                } else {
                        return randomDeterminer() + randomAdjective();
                }
            }
            return randomNounPhrase();
        }

    /**
     *
     * @return returns Verb Phrase
     */
        static String randomVerbPhrase() {
            int x = verbType();
            switch (x) {
                case 0:
                    int iv = (int) (Math.random() * intransitive_verb.length);
                    return SimpleRandomSentences.intransitive_verb[iv];
                case 1:
                    int tv = (int) (Math.random() * transitive_verb.length);
                    return SimpleRandomSentences.transitive_verb[tv] + randomNounPhrase();
                case 2:
                    int adj = (int) (Math.random() * adjective.length);
                    return " is " + SimpleRandomSentences.adjective[adj];
                case 3:
                    return " believes that " + simpleSentence();
                default:
                    return randomVerbPhrase();

            }
        }

    /**
     *
     * @return random generator to determine if using a proper or common noun.  This code could be simplified and removed
     */
    static boolean properCheck() {

            if (Math.random() <= 0.5) {
                return true; // proper noun
            } else if (Math.random() > 0.5) {
                return false; // common noun
            }
            return false;
        }

    /**
     *
     * @return returns the verb type to be used... this code could be simplified and possibly removed.
     */
    static int verbType() {
            if (Math.random() > 0.75) {
                return 0; // intransitive verb
            } else if (Math.random() > 0.5) {
                return 1; // transitive verb and noun phrase
            } else if (Math.random() > 0.25) {
                return 2; // is <adjective>
            } else {
                return 3; // believes that <simple_sentence>
            }
        }

    /**
     *
     * @return returns randomDeterminer based on array list
     */
    static String randomDeterminer() {
            int d = (int) (Math.random() * determiner.length);
            return SimpleRandomSentences.determiner[d];
        }

    /**
     *
     * @return returns random adjective to use in sentence... could be simplified and removed
     */
    static String randomAdjective(){
            int adj = (int)(Math.random()* adjective.length);
            return SimpleRandomSentences.adjective[adj];
        }

    /**
     *
     * @return returns random conjunction to be used in sentence... could be simplified and removed.
     */
    static String randomConjunction() {
            int c = (int) (Math.random() * SimpleRandomSentences.conjunction.length);
            return SimpleRandomSentences.conjunction[c];
        }
    }