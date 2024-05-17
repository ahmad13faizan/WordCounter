import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class WordCounterGUI extends JFrame {
    private JTextArea textArea;
    private JButton browseButton;
    private JButton countButton;
    private JLabel resultLabel;

    public WordCounterGUI() {
        setLocation(500, 200);
        setTitle("Word Counter Task");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 350);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel inputLabel = new JLabel("Enter text or select a file:");
        textArea = new JTextArea(5, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        topPanel.add(inputLabel, BorderLayout.NORTH);
        topPanel.add(scrollPane, BorderLayout.CENTER);
        add(topPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        browseButton = new JButton("Browse");
        countButton = new JButton("Count Words");
        resultLabel = new JLabel();
        buttonPanel.add(browseButton);
        buttonPanel.add(countButton);
        add(buttonPanel, BorderLayout.SOUTH);

        countButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                countWords();
            }
        });

        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browseFile();
            }
        });
    }

    private void countWords() {
        String inputText = textArea.getText().trim();
        if (inputText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter text or select a file.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

       
        String[] words = inputText.split("\\s+|\\p{Punct}");
        Set<String> stopWords = new HashSet<>(Arrays.asList("the", "and", "or", "is", "are")); 
        int wordCount = 0;
        Map<String, Integer> wordFrequency = new HashMap<>();

        for (String word : words) {
            if (!stopWords.contains(word.toLowerCase())) {
                wordCount++;
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        resultLabel.setText("<html>Total words: " + wordCount + "<br>" +
                "Unique words: " + wordFrequency.size() + "<br>" +
                "Word frequency: " + wordFrequency.toString() + "</html>");
        JOptionPane.showMessageDialog(this, resultLabel, "Word Count Result", JOptionPane.INFORMATION_MESSAGE);
    }

    private void browseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder text = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    text.append(line).append("\n");
                }
                textArea.setText(text.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WordCounterGUI().setVisible(true);
            }
        });
    }
}
