import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizGame {
    JFrame jf = new JFrame("Quiz Game");

    // Panels for welcome screen and quiz screen
    JPanel welcomePanel = new JPanel();
    JPanel quizPanel = new JPanel();

    // Welcome panel components
    JLabel welcomeLabel = new JLabel("Welcome to the Quiz Game! Click Start to begin.");
    JButton startButton = new JButton("Start Quiz");

    // Quiz panel components
    JLabel qLabel = new JLabel();
    JRadioButton op1 = new JRadioButton();
    JRadioButton op2 = new JRadioButton();
    JRadioButton op3 = new JRadioButton();
    JRadioButton op4 = new JRadioButton();
    JButton sb = new JButton("Submit");
    ButtonGroup oGroup = new ButtonGroup();

    int score = 0;
    int currentQuestionIndex = 0;

    public QuizGame() {
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500, 500);
        //jf.setLocationRelativeTo(null); // Center on screen
        jf.setLayout(new CardLayout());

        setupWelcomePanel();
        setupQuizPanel();

        // Add panels to the frame with CardLayout
        jf.add(welcomePanel, "welcome");
        jf.add(quizPanel, "quiz");

        // Initially show welcome screen
        showCard("welcome");

        jf.setVisible(true);
    }

    private void setupWelcomePanel()
    {
        welcomePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 20, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        welcomePanel.add(welcomeLabel, gbc);

        gbc.gridy = 1;
        startButton.setPreferredSize(new Dimension(120, 30));
        welcomePanel.add(startButton, gbc);

        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            currentQuestionIndex = 0;
            score = 0;
            loadQuestion();
            showCard("quiz");
            }
        });
    }

    private void setupQuizPanel()
     {
        quizPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        oGroup.add(op1);
        oGroup.add(op2);
        oGroup.add(op3);
        oGroup.add(op4);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        quizPanel.add(qLabel, gbc);

        gbc.gridy = 1;
        quizPanel.add(op1, gbc);
        gbc.gridy = 2;
        quizPanel.add(op2, gbc);
        gbc.gridy = 3;
        quizPanel.add(op3, gbc);
        gbc.gridy = 4;
        quizPanel.add(op4, gbc);

        gbc.gridy = 5;
        quizPanel.add(sb, gbc);

        sb.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length)
                 {
                    loadQuestion();
                }
                else
                {
                    showScore();
                }
            }
        });
    }

    private void showCard(String cardName) {
        CardLayout cl = (CardLayout) jf.getContentPane().getLayout();
        cl.show(jf.getContentPane(), cardName);
    }

    String[][] questions =
    {
        {"Which data type is used to create a variable that should store text?", "string", "myString", "String", "Txt", "3"},
        {"Which method can be used to return a string in upper case letters?", "upperCase()", "tuc()", "toUpperCase()", "toupperCase()", "3"},
        {"Which statement is used to stop a loop?", "break", "return", "exit", "stop", "1"},
        {"What is the correct HTML for making a checkbox?", "<input type=\"checkbox\">","<input type=\"check\"> ", "<checkbox>", "<check>", "1"},
        {"What keyword is used to inherit a class in Java?", "implements", "inherit", "extends", "class", "3"}
    };

    void loadQuestion() 
    {
        String[] currentQuestion = questions[currentQuestionIndex];
        qLabel.setText(currentQuestion[0]);
        op1.setText(currentQuestion[1]);
        op2.setText(currentQuestion[2]);
        op3.setText(currentQuestion[3]);
        op4.setText(currentQuestion[4]);
        oGroup.clearSelection();
    }

    void checkAnswer()
    {
        String selectedOption = "";

        if (op1.isSelected()) 
        {
            selectedOption = "1";
        } 
        else if (op2.isSelected()) 
        {
            selectedOption = "2";
        }
        else if (op3.isSelected()) 
        {
            selectedOption = "3";
        } 
        else if (op4.isSelected())
         {
            selectedOption = "4";
        }

        if (selectedOption.equals(questions[currentQuestionIndex][5])) 
        {
            score++;
        }
    }

    void showScore() 
    {
        JOptionPane.showMessageDialog(jf, "Your score: " + score + "/" + questions.length);
        System.exit(0);
    }

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> 
        new QuizGame();
    }
}
