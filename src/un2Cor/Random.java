package un2Cor;


public class Random {
    
    static final private String[] Noun = {"Aseel", "Mohammed", "Banf",
                                                "Saleh"};
    static final private String[] commonNoun = {"Boy", "Girl", "Dog",
                                                 "Tiger"};
    static final private String[] conjuction = {"and", "or", "so", "also"};
    static final private String[] determiner = {"a", "the", "every", "some"};
    static final private String[] adjective = {"big", "nice", "kind", "small"};
    static final private String[] Verb = {"walk", "jump", "look for",
                                                        "sees"};
    static final private String[] helpingVerb = {"like", "think", "know"};
    
    public static void main(String[] args) {
        while (true) {
			randomSentence();
			System.out.println(".\n\n");
			try {
				Thread.sleep(3000);
			}
			catch (InterruptedException e) {
			}
		}
    }

     static void randomSentence(){
 
      randomSimpleSentence();
      
      if (Math.random() > 0.75){
          int c = (int)(Math.random()*conjuction.length);
          System.out.print(conjuction[c] + " ");
          randomSentence();
      }
      
    }
     
     static void randomSimpleSentence() {
		
                randomNounPhrase();
                System.out.print(" ");
                randomVerbPhrase();
	}

	static void randomNounPhrase() {
		
		int n = (int)(Math.random()*Noun.length);
		System.out.print(Noun[n] + " ");
		

		if (Math.random() > 0.75) { 
			int d = (int)(Math.random()*determiner.length);
			System.out.print(determiner[d] + " ");
                        if(Math.random() > 0.80){
                            int a = (int)(Math.random()*adjective.length);
                            System.out.print(adjective[a] + " ");
                        }
                        
                        int c = (int)(Math.random()*commonNoun.length);
                        System.out.println(commonNoun[c] + " ");
                        if (Math.random() > 0.9){
                            System.out.print("who" + " ");
                            randomVerbPhrase();
                        }
		}
        } 
        
        static void randomVerbPhrase(){
                   
                    int q = (int)(Math.random()*Verb.length);
                    System.out.print(Verb[q] + " ");
                    
                    if (Math.random() > 0.5){
                        int t = (int)(Math.random()*helpingVerb.length);
                        System.out.print(helpingVerb[t] + " ");
                        randomNounPhrase();
                    } else {
                        int a = (int)(Math.random()*adjective.length);
                        System.out.print("is " + adjective[a] + " ");
                    }
                    
                    if (Math.random() > 0.65){
                        System.out.print("think that ");
                        randomSimpleSentence();
                    }
                    
                } 
}
 
