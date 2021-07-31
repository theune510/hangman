package hangman;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

public class hangman extends JPanel implements ActionListener {
	private final static int width = 500;
	private final static int height = width;
	private int incorrectLetter = 0;
	Random r = new Random();
	private String words[] = {"RUSSIA", "BRAZIL", "VENEZUELA", "GERMANY", "UKRAINE", "VIETNAM", "THAILAND",
			"NIGERIA", "ETHIOPIA", "ANTARCTICA"}; //Our word bank
	private String correctWord = words[r.nextInt(words.length)];
	private JLabel jl;
	private StringBuilder hiddenWord;
	hangman(){
		JFrame frame = new JFrame("Hangman");
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(3);
		frame.setLocationRelativeTo(null);
		jl = (JLabel) add(new JLabel(hiddenWord(), JLabel.CENTER));
		jl.setFont(new Font("Arial", 0, 80));
		frame.add(jl, BorderLayout.NORTH);
		frame.add(new Drawing(), BorderLayout.CENTER);
		frame.add(new ButtonPanel(), BorderLayout.SOUTH);
		frame.setVisible(true);
	}
	
	//Our drawing class
	private class Drawing extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D gg = (Graphics2D) g;
			gg.setStroke(new java.awt.BasicStroke(5));
			gg.drawLine(width/2 - 40, 80, width/2 - 40, 50);
			gg.drawLine(width/2 - 40, 50, width/2 + 20, 50);
			gg.drawLine(width/2 + 20, 50, width/2 + 20, 225);
			gg.drawLine(width/2 - 60, 225, width/2 + 40, 225);
			if (incorrectLetter == 1)
				gg.fillOval(width/2 - 54, 80, 30, 30);
			else if (incorrectLetter == 2) {
				gg.fillOval(width/2 - 54, 80, 30, 30);
				gg.drawLine(width/2 - 40, 100, width/2 - 40, 160);
			}
			else if (incorrectLetter == 3) {
				gg.fillOval(width/2 - 54, 80, 30, 30);
				gg.drawLine(width/2 - 40, 100, width/2 - 40, 160);
				gg.drawLine(width/2 - 40, 120, width/2 - 60, 140);
			}
			else if (incorrectLetter == 4) {
				gg.fillOval(width/2 - 54, 80, 30, 30);
				gg.drawLine(width/2 - 40, 100, width/2 - 40, 160);
				gg.drawLine(width/2 - 40, 120, width/2 - 60, 140);
				gg.drawLine(width/2 - 40, 120, width/2 - 20, 140);
			}
			else if (incorrectLetter == 5) {
				gg.fillOval(width/2 - 54, 80, 30, 30);
				gg.drawLine(width/2 - 40, 100, width/2 - 40, 160);
				gg.drawLine(width/2 - 40, 120, width/2 - 60, 140);
				gg.drawLine(width/2 - 40, 120, width/2 - 20, 140);
				gg.drawLine(width/2 - 40, 160, width/2 - 60, 180);
			}
			else if (incorrectLetter == 6) {
				gg.fillOval(width/2 - 54, 80, 30, 30);
				gg.drawLine(width/2 - 40, 100, width/2 - 40, 160);
				gg.drawLine(width/2 - 40, 120, width/2 - 60, 140);
				gg.drawLine(width/2 - 40, 120, width/2 - 20, 140);
				gg.drawLine(width/2 - 40, 160, width/2 - 60, 180);
				gg.drawLine(width/2 - 40, 160, width/2 - 20, 180);
			}
			repaint();
		}
	}//end of subclass
	
	//Used to pick out letters
	private class ButtonPanel extends JPanel {
		ButtonPanel() {
		setLayout(new java.awt.GridLayout(3, 9, 4, 4));
		JButton[] buttons = new JButton[27];
		buttons[0] = new JButton("A");
		buttons[1] = new JButton("B");
		buttons[2] = new JButton("C");
		buttons[3] = new JButton("D");
		buttons[4] = new JButton("E");
		buttons[5] = new JButton("F");
		buttons[6] = new JButton("G");
		buttons[7] = new JButton("H");
		buttons[8] = new JButton("I");
		buttons[9] = new JButton("J");
		buttons[10] = new JButton("K");
		buttons[11] = new JButton("L");
		buttons[12] = new JButton("M");
		buttons[13] = new JButton("N");
		buttons[14] = new JButton("O");
		buttons[15] = new JButton("P");
		buttons[16] = new JButton("Q");
		buttons[17] = new JButton("R");
		buttons[18] = new JButton("S");
		buttons[19] = new JButton("T");
		buttons[20] = new JButton("U");
		buttons[21] = new JButton("V");
		buttons[22] = new JButton("W");
		buttons[23] = new JButton("X");
		buttons[24] = new JButton("Y");
		buttons[25] = new JButton("Z");
		buttons[26] = new JButton("Solve");
		buttons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('A');}
		});
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('B');}
		});
		buttons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('C');}
		});
		buttons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('D');}
		});
		buttons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('E');}
		});
		buttons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('F');}
		});
		buttons[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('G');}
		});
		buttons[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('H');}
		});
		buttons[8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('I');}
		});
		buttons[9].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('J');}
		});
		buttons[10].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('K');}
		});
		buttons[11].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('L');}
		});
		buttons[12].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('M');}
		});
		buttons[13].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('N');}
		});
		buttons[14].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('O');}
		});
		buttons[15].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('P');}
		});
		buttons[16].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('Q');}
		});
		buttons[17].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('R');}
		});
		buttons[18].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('S');}
		});
		buttons[19].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('T');}
		});
		buttons[20].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('U');}
		});
		buttons[21].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('V');}
		});
		buttons[22].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('W');}
		});
		buttons[23].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('X');}
		});
		buttons[24].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('Y');}
		});
		buttons[25].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {replaceLetter('Z');}
		});
		
		//If you manage to solve the puzzle, you win, else one limb is drawn in the stick figure
		buttons[26].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(null, "What's the word?");
				if (input.equalsIgnoreCase(correctWord)) {
					jl.setText(correctWord);
					playAgain();
				}
				else
					incorrectLetter++;
			}
		});
		for(int i = 0; i < buttons.length; i++)
			add(buttons[i]);
		}
	}//end of subclass
	
	//Used to guess if a letter is in the word. If not, one part of the figure is drawn
	//If the whole figure is drawn, you lose the game
	public void replaceLetter(char guess) {
		boolean matchFound = false;
		for (int i = 0; i < hiddenWord.length(); i++)
			if (correctWord.charAt(i) == guess) {
				if (hiddenWord.charAt(i) == '*') {
					hiddenWord.setCharAt(i, guess);
					jl.setText(hiddenWord.toString());
					matchFound = true;
					if (hiddenWord.toString().equals(correctWord))
						playAgain();
				}
				else {
					JOptionPane.showMessageDialog(null, "This letter has been selected.");
					matchFound = true;
					break;
				}
			}
		
		if (!matchFound) {
			incorrectLetter++;
			if (incorrectLetter == 6) {
				jl.setText(correctWord);
				playAgain();
			}
		}
	}
	
	//Takes a random word from the word bank and masks it so the player can guess
	public String hiddenWord() {
		hiddenWord = new StringBuilder(correctWord);
		for (int i = 0; i < correctWord.length(); i++)
			if (hiddenWord.charAt(i) != '*')
				hiddenWord.setCharAt(i, '*');
		return hiddenWord.toString();
	}
	
	//A prompt that asks if you want to play again or exit the game
	public void playAgain() {
		int option = 0;
		if (incorrectLetter == 6)
			option = JOptionPane.showConfirmDialog(null, "You lost!\nPlay again?");
		else
			option = JOptionPane.showConfirmDialog(null, "Correct! You win!\nPlay again?");
		if (option == JOptionPane.YES_OPTION) {
			correctWord = words[r.nextInt(words.length)];
			jl.setText(hiddenWord());
			incorrectLetter = 0;
		}
		else
			System.exit(0);
	}
	
	public static void main(String[] args){
		java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new hangman();
            }
        });
	} //end of driver code
}//end of entire class