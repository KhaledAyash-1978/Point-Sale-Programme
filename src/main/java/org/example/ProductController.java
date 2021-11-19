package org.example;

import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
	@FXML
	private ImageView IvProduct;
	@FXML
	private ChoiceBox<?> cbCategories;
	@FXML
	private ChoiceBox<?> cbWeight;
	@FXML
	private TextField etDescription;
	@FXML
	private TextField etId;
	@FXML
	private TextField etPrice;

	@FXML
	private TableView<Products> tableProducts;

	@FXML
	private TableColumn<Products, Integer> colId;
		@FXML
	private TableColumn<Products, String> colDescription;
	@FXML
	private TableColumn<Products, String> colPrice;
	@FXML
	private TableColumn<Products, String> colCategory;
	@FXML
	private TableColumn<Products, String> colStatus;

	@FXML
	private JFXToggleButton tgStatus;

	JdbcDao jdbc;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		jdbc = new JdbcDao();
		showProducts();
	}

	public void saveTable(ActionEvent actionEvent) {
	}

	public void editEntry(ActionEvent actionEvent) {
	}

	public void deleteEntry(ActionEvent actionEvent) {
	}

	public void addCategory(ActionEvent actionEvent) {
	}



	public void showProducts() {
		ObservableList<Products> list = getProductList();
		colId.setCellValueFactory(new PropertyValueFactory<Products, Integer>("id"));
		colDescription.setCellValueFactory(new PropertyValueFactory<Products, String>("description"));
		colPrice.setCellValueFactory(new PropertyValueFactory<Products, String>("price"));
		colCategory.setCellValueFactory(new PropertyValueFactory<Products, String>("category"));
		colStatus.setCellValueFactory(new PropertyValueFactory<Products, String>("status"));
		tableProducts.setItems(list);
	}

	private ObservableList<Products> getProductList() {
		ObservableList<Products> prodectList = FXCollections.observableArrayList();
		Connection connection = jdbc.getConnection();
		String query = "SELECT * FROM `products`";
		Statement st;
		ResultSet rs;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Products products;
			while (rs.next()) {
				products = new Products(rs.getInt("id"), rs.getString("description"), rs.getString("price"), rs.getString("category"), rs.getBlob("image"), rs.getString("status"));
				prodectList.add(products);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return prodectList;
	}



	private void insertRecord() {
	/*String name = tfTableName.getText();
		if (!name.isEmpty()) {
			String query = "INSERT INTO `tbltables` (name) VALUES('" + name + "')";
			executeQuery(query);
			showProducts();


		}*/
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



}
