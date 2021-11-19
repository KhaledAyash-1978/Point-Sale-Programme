package org.example;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TablesController implements Initializable {

	// Call my database
	JdbcDao jdbc;
	@FXML
	private JFXButton btnSave;
	@FXML
	private JFXButton btnUpdate;
	@FXML
	private JFXButton btndelete;
	@FXML
	private TableColumn<Tables, Integer> colId;
	@FXML
	private TableColumn<Tables, String> colName;
	@FXML
	private TableView<Tables> tableTables;
	@FXML
	private TextField tfTableName;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		addListenerForTable();
		jdbc = new JdbcDao();
		showTable();
	}

	public void showTable() {
		ObservableList<Tables> list = getTableList();
		colId.setCellValueFactory(new PropertyValueFactory<Tables, Integer>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<Tables, String>("name"));
		tableTables.setItems(list);
	}

	private void insertRecord() {
		String name = tfTableName.getText();
		if (!name.isEmpty()) {
			String query = "INSERT INTO `tbltables` (name) VALUES('" + name + "')";
			executeQuery(query);
			showTable();
			tfTableName.setText("");

		}
	}

	private void executeQuery(String query) {
		Connection connection = jdbc.getConnection();
		Statement st;
		System.out.println(query);
		try {
			st = connection.createStatement();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.out.println("Error while inserting record .");
			ex.printStackTrace();
		}
	}


	private ObservableList<Tables> getTableList() {
		ObservableList<Tables> tableList = FXCollections.observableArrayList();
		Connection connection = jdbc.getConnection();
		String query = "SELECT * FROM `tbltables`";
		Statement st;
		ResultSet rs;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Tables tables;
			while (rs.next()) {
				tables = new Tables(rs.getInt("id"), rs.getString("name"));
				tableList.add(tables);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return tableList;
	}


	public void saveTable(ActionEvent actionEvent) {
		insertRecord();
	}

	private void addListenerForTable() {
		tableTables.getSelectionModel().selectedItemProperty().addListener((obs, oldselection, newselection) -> {
			if (newselection != null) {
				btnUpdate.setDisable(false);
				btndelete.setDisable(false);
				tfTableName.setText(newselection.getName());
			} else {
				tfTableName.setText("");
				btnUpdate.setDisable(true);
				btndelete.setDisable(true);
			}
		});
	}

	public void editEntry(ActionEvent actionEvent) {
		Connection connection = jdbc.getConnection();
		try {
			Tables table = tableTables.getSelectionModel().getSelectedItem();
			String nameSelection = tfTableName.getText();
			int idSelection = table.getId();
			String query = "UPDATE tbltables SET name = '" + nameSelection + " ' WHERE id = '" + idSelection + "'";
			executeQuery(query);
			showTable();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void deleteEntry(ActionEvent actionEvent) {
		Connection connection = jdbc.getConnection();

		try {
			Tables table = tableTables.getSelectionModel().getSelectedItem();
			int idSelection = table.getId();
			String query = "DELETE FROM tbltables  WHERE id = '" + idSelection + "'";
			executeQuery(query);
			showTable();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}



}
