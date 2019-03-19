package view;

import com.google.common.base.Splitter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.sun.org.apache.xpath.internal.operations.String;
import ham.BinaryToHexDecBCD;
import ham.Hamming;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;
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
    private JFXTextField changeInput;
    
    @FXML
    private JFXButton table2Btn;
    
    @FXML
    private TableView<String[]> tableView1;
    
    @FXML
    private TableView<String[]> tableView2;
    
    
    private Boolean paridad = true;
    
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
    
        HammingTable1();
    }
    
    @FXML
    void checkToggle () {
        if (parToggle.isSelected()) {
            parToggle.setText("PAR");
            Hamming.setParityType("even");
        } else {
            parToggle.setText("IMPAR");
            Hamming.setParityType("odd");
        }
    }
    
    void HammingTable1 () {
        
        String[][] inputTable1 = Hamming.getHammingTable(inputTextField.getText());
        
        TableColumn<String[], String> p1Column = new TableColumn();
        p1Column.setText("p1");
        TableColumn<String[], String> p2Column = new TableColumn();
        p2Column.setText("p2");
        TableColumn<String[], String> d1Column = new TableColumn();
        d1Column.setText("d1");
        TableColumn<String[], String> p3Column = new TableColumn();
        p3Column.setText("p3");
        TableColumn<String[], String> d2Column = new TableColumn();
        d2Column.setText("d2");
        TableColumn<String[], String> d3Column = new TableColumn();
        d3Column.setText("d3");
        TableColumn<String[], String> d4Column = new TableColumn();
        d4Column.setText("d4");
        TableColumn<String[], String> p4Column = new TableColumn();
        p4Column.setText("p4");
        TableColumn<String[], String> d5Column = new TableColumn();
        d5Column.setText("d5");
        TableColumn<String[], String> d6Column = new TableColumn();
        d6Column.setText("d6");
        TableColumn<String[], String> d7Column = new TableColumn();
        d7Column.setText("d7");
        TableColumn<String[], String> d8Column = new TableColumn();
        d8Column.setText("d8");
        TableColumn<String[], String> d9Column = new TableColumn();
        d9Column.setText("d9");
        TableColumn<String[], String> d10Column = new TableColumn();
        d10Column.setText("d10");
        TableColumn<String[], String> d11Column = new TableColumn();
        d11Column.setText("d11");
        TableColumn<String[], String> p5Column = new TableColumn();
        p5Column.setText("p5");
        TableColumn<String[], String> d12Column = new TableColumn();
        d12Column.setText("d12");
        
        tableView1.getColumns().addAll(p1Column, p2Column, d1Column, p3Column, d2Column, d3Column, d4Column, p4Column, d5Column, d6Column, d7Column, d8Column, d9Column, d10Column, d11Column, p5Column, d12Column);
        
        p1Column.setCellValueFactory(param -> {
            String[] x = param.getValue();
            return new SimpleStringProperty(x != null && x.length > 0 ? x[0] : "");
        });
        p2Column.setCellValueFactory(param -> {
            String[] x = param.getValue();
            return new SimpleStringProperty(x != null && x.length > 1 ? x[1] : "");
        });
        d1Column.setCellValueFactory(param -> {
            String[] x = param.getValue();
            return new SimpleStringProperty(x != null && x.length > 2 ? x[2] : "");
        });
        p3Column.setCellValueFactory(param -> {
            String[] x = param.getValue();
            return new SimpleStringProperty(x != null && x.length > 3 ? x[3] : "");
        });
        d2Column.setCellValueFactory(param -> {
            String[] x = param.getValue();
            return new SimpleStringProperty(x != null && x.length > 4 ? x[4] : "");
        });
        d3Column.setCellValueFactory(param -> {
            String[] x = param.getValue();
            return new SimpleStringProperty(x != null && x.length > 5 ? x[5] : "");
        });
        d4Column.setCellValueFactory(param -> {
            String[] x = param.getValue();
            return new SimpleStringProperty(x != null && x.length > 6 ? x[6] : "");
        });
        p4Column.setCellValueFactory(param -> {
            String[] x = param.getValue();
            return new SimpleStringProperty(x != null && x.length > 7 ? x[7] : "");
        });
        d5Column.setCellValueFactory(param -> {
            String[] x = param.getValue();
            return new SimpleStringProperty(x != null && x.length > 8 ? x[8] : "");
        });
        d6Column.setCellValueFactory(param -> {
            String[] x = param.getValue();
            return new SimpleStringProperty(x != null && x.length > 9 ? x[9] : "");
        });
        d7Column.setCellValueFactory(param -> {
            String[] x = param.getValue();
            return new SimpleStringProperty(x != null && x.length > 10 ? x[10] : "");
        });
        d8Column.setCellValueFactory(param -> {
            String[] x = param.getValue();
            return new SimpleStringProperty(x != null && x.length > 11 ? x[11] : "");
        });
        d9Column.setCellValueFactory(param -> {
            String[] x = param.getValue();
            return new SimpleStringProperty(x != null && x.length > 12 ? x[12] : "");
        });
        d10Column.setCellValueFactory(param -> {
            String[] x = param.getValue();
            return new SimpleStringProperty(x != null && x.length > 13 ? x[13] : "");
        });
        d11Column.setCellValueFactory(param -> {
            String[] x = param.getValue();
            return new SimpleStringProperty(x != null && x.length > 14 ? x[14] : "");
        });
        p5Column.setCellValueFactory(param -> {
            String[] x = param.getValue();
            return new SimpleStringProperty(x != null && x.length > 15 ? x[15] : "");
        });
        d12Column.setCellValueFactory(param -> {
            String[] x = param.getValue();
            return new SimpleStringProperty(x != null && x.length > 16 ? x[16] : "");
        });
        
        tableView1.getItems().addAll(Arrays.asList(inputTable1));
    }
    
    void HamminTable2 () {
        
        String[][] inputTable2 = Hamming.getHammingError(changeInput.getText());
        
        
    }
    
}
