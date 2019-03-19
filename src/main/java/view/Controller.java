package view;

import com.google.common.base.Splitter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import ham.BinaryToHexDecBCD;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Controller {
    
    @FXML
    private Rectangle windowBorder;
    
    @FXML
    private JFXTextField inputTextField;
    
    @FXML
    private JFXButton calcBtn;
    
    @FXML
    private Label DECLABEL;
    
    @FXML
    private Label HEXLABEL;
    
    @FXML
    private Label BCD0LABEL;
    
    @FXML
    private Label BCD1LABEL;
    
    @FXML
    private Label BCD2LABEL;
    
    @FXML
    private Label BCD3LABEL;
    
    @FXML
    private Label DEC0LABEL;
    
    @FXML
    private Label DEC1LABEL;
    
    @FXML
    private Label DEC2LABEL;
    
    @FXML
    private Label DEC3LABEL;
    
    @FXML
    private JFXToggleButton parToggle;
    
    
    @FXML
    void checkInput () {
        if (inputTextField.getText().matches("[0-1]{12}")) {
            windowBorder.setStroke(Color.FORESTGREEN);
            calcBtn.setDisable(false);
        } else {
            windowBorder.setStroke(Color.RED);
            calcBtn.setDisable(true);
        }
    }
    
    @FXML
    void runBCD () {
        String input = inputTextField.getText();
        Integer decimal = BinaryToHexDecBCD.recursiveBin(input);
        String hex = BinaryToHexDecBCD.recursiveDecHex(decimal);
        String bcd = BinaryToHexDecBCD.toBCD(decimal);
        
        HEXLABEL.setText(hex);
        DECLABEL.setText(decimal.toString());
        List Blist = Splitter.fixedLength(4).splitToList(bcd);
        BCD0LABEL.setText(Blist.get(0).toString());
        BCD1LABEL.setText(Blist.get(1).toString());
        BCD2LABEL.setText(Blist.get(2).toString());
        BCD3LABEL.setText(Blist.get(3).toString());
        
        Integer dec0 = BinaryToHexDecBCD.recursiveBin(BCD0LABEL.getText());
        Integer dec1 = BinaryToHexDecBCD.recursiveBin(BCD1LABEL.getText());
        Integer dec2 = BinaryToHexDecBCD.recursiveBin(BCD2LABEL.getText());
        Integer dec3 = BinaryToHexDecBCD.recursiveBin(BCD3LABEL.getText());
        
        DEC0LABEL.setText(dec0.toString());
        DEC1LABEL.setText(dec1.toString());
        DEC2LABEL.setText(dec2.toString());
        DEC3LABEL.setText(dec3.toString());
        
    }
    
}
