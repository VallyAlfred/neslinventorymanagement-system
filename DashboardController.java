/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neslerp;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author DS
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane top_form;

    @FXML
    private Button logout;

    @FXML
    private Label user_lbl;

    @FXML
    private Button homebtn;

    @FXML
    private Button inventorybtn;

    @FXML
    private Button supplybtn;

    @FXML
    private Button requestbtn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label today_sup_lbl;

    @FXML
    private Label today_req_lbl;

    @FXML
    private AreaChart<?, ?> supply_chart;

    @FXML
    private BarChart<?, ?> req_chart;

    @FXML
    private AnchorPane inventory_form;

    @FXML
    private TextField search_fild;

    @FXML
    private ComboBox<?> admin_box;

    @FXML
    private TableView<BinData> inventrytableview;

    @FXML
    private TableColumn<BinData, String> binid_col;

    @FXML
    private TableColumn<BinData, String> item_col;
            
    @FXML
    private AnchorPane request_form;

    @FXML
    private TextField request_ser_no;

    @FXML
    private Button request_submitbtn;

    @FXML
    private Button request_cancelbtn;

    @FXML
    private TextField request_unit;

    @FXML
    private TextField request_dtg;

    @FXML
    private TextField request_item;

    @FXML
    private TextField request_qty_requested;

    @FXML
    private TextField request_den;

    @FXML
    private TextField request_cur_holding;

    @FXML
    private TextField request_tank_capacity;

    @FXML
    private TextField request_hold_date;

    @FXML
    private TextField request_rec_qty;

    @FXML
    private TextField request_est_bal;

    @FXML
    private TextField request_approved_qty;

    @FXML
    private TextField request_issuing_depot;

    @FXML
    private TextField request_location_of_ship;

    @FXML
    private TextField request_remarks;

    @FXML
    private TextField request_search;
    
   
   @FXML
    private TableColumn<BinData, String> ship_col;

    @FXML
    private TableColumn<BinData, String> dtg_col;
    
    @FXML
    private TableColumn<BinData,String> serno;

    @FXML
    private TableColumn<BinData, String> qty_col;

    @FXML
    private TableColumn<BinData, String> den_col;

    @FXML
    private TableColumn<BinData, String> tank_col;

    @FXML
    private TableColumn<BinData, String> depot_col;

    @FXML
    private TableColumn<BinData, String> qtyrec_col;

    @FXML
    private TableColumn<BinData, String> est_bal;

    @FXML
    private TableColumn<BinData, String> qtyapp_col;

    @FXML
    private TableColumn<BinData, String> issuin_col;

    @FXML
    private TableColumn<BinData, String> location_col;

    @FXML
    private TableColumn<BinData, String> rem_col;
   

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public ObservableList<BinData> addBinListData() {
        ObservableList<BinData> binList = FXCollections.observableArrayList();
        connect = Database.connectDb();
        String sql = "select * from bin";

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            BinData binData;
            while (result.next()) {
                binData = new BinData(result.getInt("ser_no"),
                        result.getString("ship"),
                        result.getString("dtg"),
                        result.getString("item_requested"),
                        result.getString("qty_requested"),
                        result.getString("den"),
                        result.getString("ship_current_holding"),
                        result.getString("tank_capacity"),
                        result.getString("depot_holding_as_as_Date"),
                        result.getString("qty_recommended"),
                        result.getString("est_bal_after_approval"),
                        result.getString("qty_approved"),
                        result.getString("issuing_depot"),
                        result.getString("location_of_ship"),
                        result.getString("remarks"));

                binList.add(binData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return binList;
    }

    private ObservableList<BinData> addBinList;

    public void addBinShowListData() {
        addBinList = addBinListData();
        serno.setCellValueFactory(new PropertyValueFactory<>("ser_no"));
        ship_col.setCellValueFactory(new PropertyValueFactory<>("ship"));
        dtg_col.setCellValueFactory(new PropertyValueFactory<>("dtg"));
        item_col.setCellValueFactory(new PropertyValueFactory<>("itemRequested"));
        qty_col.setCellValueFactory(new PropertyValueFactory<>("qtyRequested"));
        den_col.setCellValueFactory(new PropertyValueFactory<>("den"));
        ship_col.setCellValueFactory(new PropertyValueFactory<>("shipCurrentHolding"));
        tank_col.setCellValueFactory(new PropertyValueFactory<>("tankCapacity"));
        depot_col.setCellValueFactory(new PropertyValueFactory<>("depotHoldingAsAtDate"));
        qtyrec_col.setCellValueFactory(new PropertyValueFactory<>("qty_recommended"));
        est_bal.setCellValueFactory(new PropertyValueFactory<>("estBalanceAfterApproval"));
        qtyapp_col.setCellValueFactory(new PropertyValueFactory<>("qtyApproved"));
        issuin_col.setCellValueFactory(new PropertyValueFactory<>("issuingDepot"));
        location_col.setCellValueFactory(new PropertyValueFactory<>("locationOShip"));
        rem_col.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        inventrytableview.setItems(addBinList);
    }

    private double x = 0;
    private double y = 0;

    public void binSearch() {
        FilteredList<BinData> filter = new FilteredList<>(addBinList, e -> true);
        search_fild.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateBinData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (predicateBinData.getSerialNo().toString().contains(searchKey)) {
                    return true;
                } else if (predicateBinData.getShip().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateBinData.getDtg().contains(searchKey)) {
                    return true;
                } else if (predicateBinData.getItemRequested().contains(searchKey)) {
                    return true;
                } else if (predicateBinData.getQtyRequested().contains(searchKey)) {
                    return true;
                } else if (predicateBinData.getDen().contains(searchKey)) {
                    return true;
                } else if (predicateBinData.getShipCurrentHolding().contains(searchKey)) {
                    return true;
                } else if (predicateBinData.getTankCapacity().contains(searchKey)) {
                    return true;
                } else if (predicateBinData.getDepotHoldingAsAtDate().contains(searchKey)) {
                    return true;
                } else if (predicateBinData.getQty_recommended().contains(searchKey)) {
                    return true;
                } else if (predicateBinData.getEstBalanceAfterApproval().contains(searchKey)) {
                    return true;
                } else if (predicateBinData.getIssuingDepot().contains(searchKey)) {
                    return true;
                } else if (predicateBinData.getQtyApproved().contains(searchKey)) {
                    return true;
                } else if (predicateBinData.getLocationOShip().contains(searchKey)) {
                    return true;
                } else if (predicateBinData.getRemarks().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<BinData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(inventrytableview.comparatorProperty());
        inventrytableview.setItems(sortList);
    }

    public void requestItem() {
        String sql = "INSERT INTO requestitem(SER_NO,SHIP,DTG,ITEM_REQUESTED,QTY_REQUESTED,DEN,SHIP_CURRENT_HOLDING,TANK_CAPACITY,DEPOT_HOLDING_AS_AS_DATE,QTY_RECOMMENDED,EST_BAL_AFTER_APPROVAL,QTY_APPROVED,ISSUING_DEPOT, LOCATION_OF_SHIP, REMARK)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        connect = Database.connectDb();
        
        try{
            Alert alert;
            if(request_ser_no.getText().isEmpty()
                    ||request_unit.getText().isEmpty()
                    ||request_dtg.getText().isEmpty()
                    ||request_item.getText().isEmpty()
                    || request_qty_requested.getText().isEmpty()
                    || request_den.getText().isEmpty()
                    || request_cur_holding.getText().isEmpty()
                    || request_tank_capacity.getText().isEmpty()
                    || request_hold_date.getText().isEmpty()
                    || request_rec_qty.getText().isEmpty()
                    || request_est_bal.getText().isEmpty()
                    || request_approved_qty.getText().isEmpty()
                    || request_issuing_depot.getText().isEmpty()
                    || request_location_of_ship.getText().isEmpty()
                    || request_remarks.getText().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in the blank spaces");
                alert.showAndWait();
                
                
            }else{
                    
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, request_ser_no.getText());
                prepare.setString(2, request_unit.getText());
                prepare.setString(3, request_dtg.getText());
                prepare.setString(4, request_item.getText());
                prepare.setString(4, request_qty_requested.getText());
                prepare.setString(5, request_den.getText());
                prepare.setString(6, request_cur_holding.getText());
                prepare.setString(7, request_tank_capacity.getText());
                prepare.setString(8, request_hold_date.getText());
                prepare.setString(9, request_rec_qty.getText());
                prepare.setString(10, request_est_bal.getText());
                prepare.setString(11, request_approved_qty.getText());
                prepare.setString(12, request_issuing_depot.getText());
                prepare.setString(13, request_location_of_ship.getText());
                prepare.setString(14, request_remarks.getText());
                prepare.executeUpdate();
            }
            addBinShowListData();
            resetRequestForm();    
            
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void resetRequestForm(){
        request_ser_no.setText("");
        request_unit.setText("");
        request_dtg.setText("");
        request_item.setText("");
        request_qty_requested.setText("");
        request_den.setText("");
        request_cur_holding.setText("");
        request_tank_capacity.setText("");
        request_hold_date.setText("");
        request_rec_qty.setText("");
        request_est_bal.setText("");
        request_approved_qty.setText("");
        request_issuing_depot.setText("");
        request_location_of_ship.setText("");
        request_remarks.setText("");   
        
    }

    
    public void switchForm(ActionEvent event) {

        if (event.getSource() == homebtn) {
            home_form.setVisible(true);
            inventory_form.setVisible(false);
            request_form.setVisible(false);

            homebtn.setStyle("-fx-background-color:yellow;");
            inventorybtn.setStyle("-fx-background-color:transparent");
            requestbtn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == inventorybtn) {
            home_form.setVisible(false);
            inventory_form.setVisible(true);
            request_form.setVisible(false);

            inventorybtn.setStyle("-fx-background-color:yellow;");
            homebtn.setStyle("-fx-background-color:transparent");
            requestbtn.setStyle("-fx-background-color:transparent");

            addBinShowListData();
            binSearch();

        } else if (event.getSource() == requestbtn) {
            home_form.setVisible(false);
            inventory_form.setVisible(false);
            request_form.setVisible(true);

            inventorybtn.setStyle("-fx-background-color:yellow;");
            homebtn.setStyle("-fx-background-color:transparent");
            requestbtn.setStyle("-fx-background-color:transparent");

            addBinShowListData();
            //binSearch();

        }
    }

    @FXML
    public void logout() {
        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("AdminUserLogin.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - y);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            } else {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void minimize() {
        Stage stage = (Stage) home_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addBinShowListData();

        
    }

}
