# Java-Sing-App-_-Student-Registartion-Form
A GUI App where student can add their registration number with their name and their corresponding course in which they have enrolled along their  profile picture 
This project is a standalone desktop application developed in Java using the Swing library for managing student registration records and associated profile pictures.

Key Features & Functionality
Custom Graphical User Interface (GUI): Developed a single-panel interface (MainPanel) using Java Swing with absolute positioning for a fixed, responsive layout.

Dynamic Data Capture: Facilitates the input of Student Registration Number, Student Name, and Course selection (via a JComboBox).

Image Handling: Implements file browsing and displays a student profile picture (lblProfilePict), reading and scaling the image using the ImageIO and BufferedImage classes.

Custom List Rendering: Features a dynamically updated JList where each entry uses a custom ListCellRenderer to display student data (Regn No, Name, Course) alongside their scaled profile picture within a single list item (using a JLabel with HTML formatting and an ImageIcon).

Form Control Logic:

Uses DocumentListener on text fields to enable/disable the Register button, enforcing form completeness (Regn No, Name, and Image must be present).

Uses ListSelectionListener to enable the Cancel button only when an item in the list is selected, allowing for the removal of a registered student record.
