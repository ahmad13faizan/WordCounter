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
        // Frame settings
        setDefaultLookAndFeelDecorated(true);
        setLocation(500, 200);
        setTitle("Word Counter Task");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLayout(new BorderLayout());

        // Set custom font
        Font customFont = new Font("Verdana", Font.PLAIN, 14);

        // Top Panel for Input
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBackground(new Color(50, 50, 50));

        JLabel inputLabel = new JLabel("Enter text or select a file:");
        inputLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputLabel.setForeground(new Color(155, 200, 150));

        textArea = new JTextArea(10, 50);
        textArea.setFont(customFont);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createLineBorder(new Color(51, 102, 255)));

        JScrollPane scrollPane = new JScrollPane(textArea);
        topPanel.add(inputLabel, BorderLayout.NORTH);
        topPanel.add(scrollPane, BorderLayout.CENTER);
        add(topPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(50, 50, 50));

        browseButton = new JButton("Browse");
        browseButton.setBackground(new Color(0, 153, 76));
        browseButton.setForeground(Color.WHITE);
        browseButton.setFont(new Font("Arial", Font.BOLD, 14));

        countButton = new JButton("Count Words");
        countButton.setBackground(new Color(0, 153, 76));
        countButton.setForeground(Color.WHITE);
        countButton.setFont(new Font("Arial", Font.BOLD, 14));

        buttonPanel.add(browseButton);
        buttonPanel.add(countButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Result Label
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        add(resultLabel, BorderLayout.NORTH);

        // Action Listeners
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

        StringBuilder result = new StringBuilder("<html><body style='text-align: center;'>Total words: " + wordCount + "<br>Unique words: " + wordFrequency.size() + "<br><br><b>Word Frequency:</b><br>");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("<br>");
        }
        result.append("</body></html>");

        resultLabel.setText(result.toString());
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
        UIDefaults uiDefaults = UIManager.getDefaults();
        uiDefaults.put("activeCaption", new javax.swing.plaf.ColorUIResource(Color.black));
        uiDefaults.put("activeCaptionText", new javax.swing.plaf.ColorUIResource(Color.white));
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WordCounterGUI().setVisible(true);
            }
        });
    }
}
