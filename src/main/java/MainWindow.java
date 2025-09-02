import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import core.TronaldDump;

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
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        
        // Load images safely here
        userImage = new Image(getClass().getResourceAsStream("/user.png"));
        tronaldDumpImage = new Image(getClass().getResourceAsStream("/tronalddump.png"));
    }
   

    /** Injects the Duke instance */
    public void setTronaldDump(TronaldDump d) {
        tronaldDump = d;
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
        
        userInput.clear();
        
        // Auto-scroll to bottom
        scrollPane.setVvalue(1.0);
    }
}