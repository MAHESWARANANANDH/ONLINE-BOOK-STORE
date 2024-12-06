package onlinebookstore;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
public class OnlineBookstoreApp extends JFrame {
    private List<String> books;
    private ArrayList<String> cart;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JList<String> bookList;
    private DefaultListModel<String> listModel;
    private JTextArea cartArea;
    private JTextField searchField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField paymentCardNumberField;
    private JTextField paymentExpiryDateField;
    private JTextField paymentCVVField;
    public OnlineBookstoreApp() {
        books = new ArrayList<>();
        cart = new ArrayList<>();
        books.add("The Great Gatsby - $10");
        books.add("Moby Dick - $12");
        books.add("Clean Code - $25"); // Robert C. Martin
        books.add("Introduction to Algorithms - $50");  
        books.add("Effective Java - $35"); 
        books.add("The Pragmatic Programmer - $30"); 
        books.add("Design Patterns: Elements of Object-Oriented Software - $45"); 
        books.add("Python Crash Course - $40"); 
        books.add("Head First Java - $35");
        books.add("JavaScript: The Good Parts - $28"); // Douglas Crockford
        books.add("You Don't Know JS - $22"); // Kyle Simpson
        books.add("Artificial Intelligence: A Modern Approach - $55"); 
        books.add("To Kill a Mockingbird - $15");
        books.add("1984 - $18");
        books.add("Pride and Prejudice - $14");
        books.add("The White Tiger - $15");
        books.add("Chetan Bhagat - Five Point Someone - $10"); 
        books.add("The God of Small Things - $18"); 
        books.add("Midnight's Children - $22"); 
        books.add("The Namesake - $17"); 
        books.add("A Fine Balance - $19"); 
        books.add("Shantaram - $20"); 
        books.add("The Inheritance of Loss - $14"); 
        books.add("The Elephant Vanishes - $16"); 
        books.add("Train to Pakistan - $18"); 
        books.add("The Discovery of India - $13"); 
        books.add("The Guide - $12"); 
        books.add("Gitanjali - $14"); 
        books.add("A Passage to India - $15"); // E.M. Forster
        books.add("The Great Indian Novel - $17"); // Shashi Tharoor
        books.add("The Immortals of Meluha - $16"); // Amish Tripathi
        books.add("The Secret of the Nagas - $18"); // Amish Tripathi
        books.add("Sacred Games - $19"); // Vikram Chandra
        books.add("The Ballad of Puran Singh - $11"); // Dalip Kaur Tiwana
        setTitle("Online Bookstore");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(createLoginPage(), "Login");
        cardPanel.add(createBookstorePage(), "Bookstore");
        cardPanel.add(createCartPage(), "Cart");
        cardPanel.add(createCheckoutPage(), "Checkout");
        add(cardPanel);
    }

    // 1. Login Page
    private JPanel createLoginPage() {
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        loginPanel.setBackground(new Color(255, 255, 255));
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 122, 204));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(e -> login());

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(labelFont);
        usernameLabel.setForeground(new Color(0, 122, 204));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(new Color(0, 122, 204));
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginPanel.add(loginButton, gbc);
        return loginPanel;
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals("Maheswaran") && password.equals("123@Mahes")) {
            cardLayout.show(cardPanel, "Bookstore");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
        }
    }

    // 2. Bookstore Page (Search & Display Books)
    private JPanel createBookstorePage() {
        JPanel bookstorePanel = new JPanel();
        bookstorePanel.setLayout(new BorderLayout());
        bookstorePanel.setBackground(new Color(240, 248, 255)); // Light background

        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(0, 122, 204));
        searchButton.setForeground(Color.WHITE);

        listModel = new DefaultListModel<>();
        for (String book : books) {
            listModel.addElement(book);
        }
        bookList = new JList<>(listModel);
        bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(bookList);
        searchButton.addActionListener(e -> searchBooks());
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(new JLabel("Search for a book: "), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);
        bookstorePanel.add(searchPanel, BorderLayout.NORTH);
        bookstorePanel.add(listScrollPane, BorderLayout.CENTER);
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setBackground(new Color(0, 122, 204));
        addToCartButton.setForeground(Color.WHITE);
        addToCartButton.addActionListener(e -> addToCart());
        bookstorePanel.add(addToCartButton, BorderLayout.SOUTH);
        return bookstorePanel;
    } 
    private void searchBooks() {
        String query = searchField.getText().toLowerCase();
        listModel.clear();
        for (String book : books) {
            if (book.toLowerCase().contains(query)) {
            
    listModel.addElement(book);
            }
        }
    }

    // Add Book to Cart
    private void addToCart() {
        String selectedBook = bookList.getSelectedValue();
        if (selectedBook != null) {
            cart.add(selectedBook);
            JOptionPane.showMessageDialog(this, "Added to cart!");
            showPaymentDetailsDialog();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a book to add to the cart.");
        }
    }

    // Show Payment Details Dialog
    private void showPaymentDetailsDialog() {
        JPanel paymentPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        paymentPanel.setBackground(new Color(255, 255, 255));

        Font labelFont = new Font("Arial", Font.BOLD, 14);

        paymentCardNumberField = new JTextField(20);
        paymentExpiryDateField = new JTextField(10);
        paymentCVVField = new JTextField(4);

        paymentPanel.add(new JLabel("Card Number:"));
        paymentPanel.add(paymentCardNumberField);
        paymentPanel.add(new JLabel("Expiry Date (MM/YY):"));
        paymentPanel.add(paymentExpiryDateField);
        paymentPanel.add(new JLabel("CVV:"));
        paymentPanel.add(paymentCVVField);
        int option = JOptionPane.showConfirmDialog(this, paymentPanel, "Enter Payment Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String cardNumber = paymentCardNumberField.getText();
            String expiryDate = paymentExpiryDateField.getText();
            String cvv = paymentCVVField.getText();

            if (cardNumber.isEmpty() || expiryDate.isEmpty() || cvv.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter all payment details.");
            } else { 
                JOptionPane.showMessageDialog(this, "Payment Successful. Proceeding to checkout.");
                cardLayout.show(cardPanel, "Cart"); // Show cart after payment
            }
        }
    }




    // 3. Cart Page
    private JPanel createCartPage() {
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.setBackground(new Color(240, 248, 255));

        cartArea = new JTextArea(10, 30);
        cartArea.setEditable(false);
        JScrollPane cartScrollPane = new JScrollPane(cartArea);

        JButton checkoutButton = new JButton("Proceed to Checkout");
        checkoutButton.setBackground(new Color(0, 122, 204));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.addActionListener(e -> cardLayout.show(cardPanel, "Checkout"));

        cartPanel.add(new JLabel("Your Cart:"), BorderLayout.NORTH);
        cartPanel.add(cartScrollPane, BorderLayout.CENTER);
        cartPanel.add(checkoutButton, BorderLayout.SOUTH);
        return cartPanel;
    }

    // Update Cart Display
    private void updateCartDisplay() {
        cartArea.setText("");
        for (String item : cart) {
            cartArea.append(item + "\n");
        }
    }
    // 4. Checkout Page (Payment Process)
    private JPanel createCheckoutPage() {
        JPanel checkoutPanel = new JPanel(new BorderLayout());
        checkoutPanel.setBackground(new Color(240, 248, 255));
        JTextArea orderSummary = new JTextArea(10, 30);
        orderSummary.setEditable(false);
        JScrollPane orderScrollPane = new JScrollPane(orderSummary);
        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBackground(new Color(0, 122, 204));
        placeOrderButton.setForeground(Color.WHITE);
        placeOrderButton.addActionListener(e -> placeOrder(orderSummary));
        checkoutPanel.add(new JLabel("Checkout - Order Summary:"), BorderLayout.NORTH);
        checkoutPanel.add(orderScrollPane, BorderLayout.CENTER);
        checkoutPanel.add(placeOrderButton, BorderLayout.SOUTH);

        return checkoutPanel;
    } 



    private void placeOrder(JTextArea orderSummary) {
        StringBuilder receipt = new StringBuilder("Order Summary:\n\n");
        double total = 0;
        for (String item : cart) {
            receipt.append(item).append("\n");
            total += extractPrice(item);
        }
        receipt.append("\nTotal: $").append(total);
        orderSummary.setText(receipt.toString());

        int option = JOptionPane.showConfirmDialog(this, "Proceed with payment?", "Payment", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Payment Successful. Thank you for your purchase!");
            cart.clear();
            updateCartDisplay();
            cardLayout.show(cardPanel, "Login");
        }
    }

    // Extract the price from a book string (e.g., "The Great Gatsby - $10")
    private double extractPrice(String bookDetails) {
        String[] parts = bookDetails.split(" - \\$");
        return Double.parseDouble(parts[1]);
    }
}