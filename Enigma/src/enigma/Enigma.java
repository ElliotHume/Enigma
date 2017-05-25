package enigma;

import java.awt.Font;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class Enigma extends BasicGame {
    
        public String keyword = ">";
        public int mouseX;
        public int mouseY;
        public int keyHeight = 10;
        public String output;
        public static TrueTypeFont font;
        public static TrueTypeFont smallFont;
        public ArrayList<String> previous;
        public ArrayList<String> keywords = new ArrayList<String>();;
        public Puzzle puzzle;
        public boolean apophis = false;
        public boolean active = false;
        public int sequence = 1;
        public int skips = 0;
        public Animation nextPuzzle;
        public Image[] nextAnim;
        public boolean completed = false;
        public Music ambience;
        
        public int amount = 20;
        
	public Enigma(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
            Font awtFont = new Font("Times New Roman", Font.PLAIN, 22);
            font = new TrueTypeFont(awtFont, false);
            Font awtFont2 = new Font("Times New Roman", Font.ITALIC, 18);
            smallFont = new TrueTypeFont(awtFont2, false);
            previous = new ArrayList<String>();
            
            nextAnim = new Image[12];
            for (int i=1; i <= nextAnim.length-6 ; i++){
                String filePath = "puzzles/images/animations/anim"+String.valueOf(i)+".png";
                nextAnim[i-1] = new Image(filePath);
            }
            for (int i=1; i <= nextAnim.length-6 ; i++){
                String filePath = "puzzles/images/animations/anim"+String.valueOf(7-i)+".png";
                nextAnim[5+i] = new Image(filePath);
            }
            nextPuzzle = new Animation(nextAnim, 40);
            nextPuzzle.setLooping(false);
            nextPuzzle.stop();
            
            ambience = new Music("puzzles/images/ambience.wav");
            
            puzzle = new Puzzle(1);
           
	}

        @Override
        public void keyPressed(int key, char c){
            if (key != 14 && (Character.isAlphabetic(c) || Character.isDigit(c) || key == 39 || key == 52 || key == 57)){
                keyword += c;
            } else if (key == 14){
                if (keyword.length() > 1) {
                    keyword = keyword.substring(0, keyword.length()-1);
                }
            }
        }
    
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
            if (sequence >= amount+2){
                gc.sleep(8000);
                gc.exit();
            }
            Input input = gc.getInput();
            
            if (sequence == 19){
                if (!ambience.playing()){
                    ambience.loop(1f, 0.5f);
                }
            } else {
                ambience.stop();
                ambience.setVolume(0f);
            }
            
            if (input.isKeyPressed(Input.KEY_ENTER)){
                if (sequence == 13 && keyword == ">"){
                    keyword = null;
                }
                System.out.println(keyword);
                if (keyword != null){
                    keyword = keyword.toLowerCase();
                }
                previous.add(0,keyword+": "+action(keyword, gc.getGraphics()));
                keyword = ">";
            } else if (input.isKeyPressed(Input.KEY_ESCAPE)){
                gc.exit();
            }
//            } else if (input.isKeyPressed(Input.KEY_BACKSLASH)){
//                skips++;
//                sequence++;
//                puzzle = new Puzzle(sequence);
//                completed = true;
//            }
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
            g.setBackground(Color.black);
            g.setFont(font);
            
            //System.out.println(nextPuzzle.isStopped());

            if (completed){
                nextPuzzle.draw(0,0);
                nextPuzzle.start();
                if(nextPuzzle.getFrame() == 11){
                    //System.out.println(nextPuzzle.isStopped());
                    nextPuzzle.restart();
                    nextPuzzle.stop();
                    completed = false;
                }
                //
            }
            
            if (nextPuzzle.isStopped()){
                if (sequence >= amount+1){
                    g.setColor(Color.red);
                    g.drawString("You have solved the enigma", 180, 400);

                    g.setFont(smallFont);
                    g.setColor(Color.darkGray);
                    if (skips == 0){ 
                        g.drawString("look in the folder /Documents/Old Assignments/2015/Computing/Apophis", 40, 450);
                    } else {
                        g.drawString("You skipped "+String.valueOf(skips)+" times, solve all puzzles for a reward.", 100, 450);
                    }

                    sequence++;

                } else {
                    if (!active){
                        g.setColor(Color.red);
                    } else {
                        g.setColor(Color.green);
                    }

                    if (active){
                        puzzle.display(10, 10, g);
                    }

                    g.drawString(keyword, 10, keyHeight);
                    g.setColor(Color.gray);
                    if (previous.size() > 0){
                        for(int i=0; i<previous.size(); i++){
                            g.drawString(previous.get(i), 10, keyHeight+50+(i*24));
                        }
                    }

                    if (apophis){
                        g.setFont(smallFont);
                        for (int i=0; i< keywords.size(); i++){
                            g.drawString(keywords.get(i), 400, keyHeight+60+(i*24));
                        }
                        g.setFont(font);
                    }
                }   
            }
            
	}

	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Enigma("Enigma"));
			appgc.setDisplayMode(600, 900, false);
                        appgc.setShowFPS(false);
                        appgc.setVSync(true);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(Enigma.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
        
        public String action(String keyword, Graphics g){
            
            if (!active){
                if (keyword.equals(">start")){
                    active = true;
                    keyHeight += 250;
                    return "let the games begin, you may skip puzzles with the keyword 'skip'";
                } else if (keyword.equals(">enigma")){
                    return "What do you need to do to finish a race?";
                }
            } else {
                
                if (sequence == 13 && keyword == null){
                    sequence++;
                    puzzle = new Puzzle(sequence);
                    g.drawAnimation(nextPuzzle, 0, 0);
                    return "Correct";
                } else{
                    keyword = keyword.substring(1);
                    
                    if (keyword.equals("skip")){
                        skips++;
                        sequence++;
                        puzzle = new Puzzle(sequence);
                        completed = true;
                        return "Puzzle skipped";
                    }
                    
                    for (String key : puzzle.keys){
                        if (keyword.equals(key)){
                            sequence++;
                            puzzle = new Puzzle(sequence);
                            return "Correct";
                        }
                    }
                    
                }
            }
            
            return "null";
        }
}
