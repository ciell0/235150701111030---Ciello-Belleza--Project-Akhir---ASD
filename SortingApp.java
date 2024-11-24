import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SortingApp extends JFrame {
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton bubbleSortButton, insertionSortButton;

    public SortingApp() {
        setTitle("Sorting Application");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel inputLabel = new JLabel("Masukkan Angka (Pisahkan angka dengan koma):");
        inputField = new JTextField(20);

        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        bubbleSortButton = new JButton("Bubble Sort");
        insertionSortButton = new JButton("Insertion Sort");

        buttonPanel.add(bubbleSortButton);
        buttonPanel.add(insertionSortButton);

        // Output Area
        outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Button Listeners
        bubbleSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSorting("Bubble");
            }
        });

        insertionSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSorting("Insertion");
            }
        });
    }

    private void performSorting(String method) {
        String inputText = inputField.getText();
        if (inputText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan angka terlebih dahulu!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // mengubah input menjadi array
            String[] inputStrings = inputText.split(",");
            int[] numbers = Arrays.stream(inputStrings).mapToInt(Integer::parseInt).toArray();

            outputArea.setText("Sorting menggunakan " + method + " Sort:\n");
            if (method.equals("Bubble")) {
                bubbleSort(numbers);
            } else {
                insertionSort(numbers);
            }

            outputArea.append("\nHasil Akhir Sorting :\n" + Arrays.toString(numbers));
        
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Tolong masukkan angka yang dipisahkan dengan koma.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Bubble Sort 
    private void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                outputArea.append("Step " + (i * n + j + 1) + " --> " + Arrays.toString(arr) + "\n");
            }
        }
    }

    // Insertion Sort 
    private void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;

            outputArea.append("Step " + i + " --> " + Arrays.toString(arr) + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SortingApp app = new SortingApp();
            app.setVisible(true);
        });
    }
}
