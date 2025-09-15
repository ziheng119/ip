import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import core.TronaldDump;
import core.Ui;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private TronaldDump tronaldDump;

    private Image userImage;
    private Image tronaldDumpImage;

    @FXML
    public void initialize() {
        // Auto-scroll to bottom when dialog container height changes
        dialogContainer.heightProperty().addListener((observable) -> {
            scrollPane.setVvalue(1.0);
        });
        
        // Load images safely here
        userImage = new Image(getClass().getResourceAsStream("/user.png"));
        tronaldDumpImage = new Image(getClass().getResourceAsStream("/tronalddump.png"));
    }
   

    /** Injects the Duke instance */
    public void setTronaldDump(TronaldDump d) {
        tronaldDump = d;
        // Show welcome message after TronaldDump is set
        showWelcomeMessage();
    }

    /**
     * Shows the welcome message when the GUI starts.
     */
    private void showWelcomeMessage() {
        String welcomeMessage = Ui.getWelcomeMessage();
    
        dialogContainer.getChildren().addAll(
                DialogBox.getTronaldDumpDialog(welcomeMessage, tronaldDumpImage)
        );
    }

    /**
     * Shows the goodbye message before closing the application.
     */
    private void showGoodbyeMessage() {
        String goodbyeMessage = Ui.getGoodbyeMessage();
    
        dialogContainer.getChildren().addAll(
                DialogBox.getTronaldDumpDialog(goodbyeMessage, tronaldDumpImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return; // Don't process empty input
        }
        
        // Show user input
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        
        // Process command and get response
        String response = tronaldDump.getResponse(input);
        
        // Show TronaldDump response
        dialogContainer.getChildren().addAll(
                DialogBox.getTronaldDumpDialog(response, tronaldDumpImage)
        );
        
        // Check if this is an exit command
        if (isExitCommand(input)) {
            javafx.application.Platform.exit();
        }
        
        userInput.clear();

        
    }
    
    /**
     * Checks if the input is an exit command.
     * @param input The user input to check
     * @return true if the input is an exit command, false otherwise
     */
    private boolean isExitCommand(String input) {
        String trimmedInput = input.trim().toLowerCase();
        return trimmedInput.equals("bye");
    }
}