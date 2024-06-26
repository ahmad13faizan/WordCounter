# Word Counter GUI

This Java application provides a graphical user interface (GUI) for counting words in text input. It allows users to either enter text manually or select a file containing text to analyze.

## Features

- **Manual Text Input:** Users can directly enter text into the text area.
- **File Selection:** Users can select a text file from their system to load into the text area.
- **Word Count Analysis:** Counts total words, unique words, and displays word frequencies.
- **Stop Words Exclusion:** Common stop words (e.g., "the", "and", "or", "is", "are") are excluded from the analysis.

## Screenshots

![Main Window](screenshots/main_window.png)
*Main window of the application.*

![Word Count Result](screenshots/result.png)
*Word count result dialog.*

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Swing library (included with JDK)

### Installation

1. **Clone the repository:**
    ```bash
    git clone https://github.com/ahmad13faizan/WordCounter.git
    cd WordCounter
    ```

2. **Compile the source code:**
    ```bash
    javac WordCounterGUI.java
    ```

3. **Run the application:**
    ```bash
    java WordCounterGUI
    ```

## Usage

1. **Enter Text:**
   - Directly type or paste text into the text area.
2. **Select File:**
   - Click the "Browse" button to open a file chooser.
   - Select a text file to load its content into the text area.
3. **Count Words:**
   - Click the "Count Words" button to analyze the text.
   - A dialog box will display the results including total words, unique words, and word frequency.

## Code Overview

### WordCounterGUI.java

This is the main class that sets up the GUI and handles user interactions.

- **Components:**
  - `JTextArea textArea`: The area where users can input text.
  - `JButton browseButton`: Button to browse and select a text file.
  - `JButton countButton`: Button to start the word count analysis.
  - `JLabel resultLabel`: Label to display the results.

- **Methods:**
  - `countWords()`: Counts the total words, unique words, and their frequencies excluding stop words.
  - `browseFile()`: Opens a file chooser for selecting a text file and loads its content into the text area.

## Contributing

Contributions are welcome! Feel free to submit issues and pull requests.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
