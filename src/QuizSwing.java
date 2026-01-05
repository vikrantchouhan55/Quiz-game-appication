import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizSwing extends JFrame implements ActionListener {

    JLabel questionLabel, timerLabel;
    JButton[] options = new JButton[4];

    String[][] questions = {
            {"Which language is platform independent?",
                    "C", "Java", "C++", "Python", "Java"},

            {"Which keyword is used to inherit a class?",
                    "this", "super", "extends", "implements", "extends"},

            {"Which method is entry point of Java?",
                    "start()", "run()", "main()", "init()", "main()"},

            {"Which package has Scanner class?",
                    "java.io", "java.lang", "java.util", "java.sql", "java.util"},

            {"Which is not OOP concept?",
                    "Inheritance", "Encapsulation", "Polymorphism", "Compilation", "Compilation"},

            {"Which loop runs at least once?",
                    "for", "while", "do-while", "foreach", "do-while"},

            {"Which keyword creates object?",
                    "class", "new", "void", "static", "new"},

            {"Which exception is unchecked?",
                    "IOException", "SQLException", "NullPointerException", "FileNotFoundException", "NullPointerException"},

            {"Which collection avoids duplicates?",
                    "List", "ArrayList", "Set", "Vector", "Set"},

            {"Which operator accesses members?",
                    "*", "->", ".", ":", "."}
    };

    int index = 0;
    int score = 0;
    int timeLeft = 15;
    Timer timer;

    QuizSwing() {
        setTitle("Java Quiz Application");
        setSize(600, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        timerLabel = new JLabel("Time Left: 15", JLabel.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        timerLabel.setForeground(Color.RED);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(questionLabel, BorderLayout.CENTER);
        topPanel.add(timerLabel, BorderLayout.EAST);

        JPanel optionPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        optionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (int i = 0; i < 4; i++) {
            options[i] = new JButton();
            options[i].addActionListener(this);
            optionPanel.add(options[i]);
        }

        add(topPanel, BorderLayout.NORTH);
        add(optionPanel, BorderLayout.CENTER);

        loadQuestion();
        setVisible(true);
    }

    void loadQuestion() {
        if (index == questions.length) {
            showResult();
            return;
        }

        questionLabel.setText("Q" + (index + 1) + ": " + questions[index][0]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(questions[index][i + 1]);
            options[i].setEnabled(true);
        }

        timeLeft = 15;
        timerLabel.setText("Time Left: 15");

        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft);
            if (timeLeft == 0) {
                timer.stop();
                index++;
                loadQuestion();
            }
        });
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        timer.stop();

        JButton clicked = (JButton) e.getSource();

        if (clicked.getText().equals(questions[index][5])) {
            score++;
        }

        index++;
        loadQuestion();
    }

    void showResult() {
        JOptionPane.showMessageDialog(this,
                "Quiz Completed!\nYour Score: " + score + "/10");
        System.exit(0);
    }

    public static void main(String[] args) {
        new QuizSwing();
    }
}