import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CSE148Main extends Application{
	ObservableList<StockPrice> data2 = FXCollections.observableArrayList();

	TextField tfUsername = null;
	TextField tfPassword = null;
	
	public void start(Stage stage) throws Exception {
		Pane root = loginPane();
		
		Scene scene = new Scene(root, 300, 250, Color.LIGHTBLUE);
		stage.setScene(scene);
		
		stage.setTitle("Login here");
		stage.show();
	}
	
	public Pane loginPane() {
		GridPane gpane = new GridPane();
		gpane.setAlignment(Pos.CENTER);
		gpane.setHgap(10);
		gpane.setVgap(10);
		
		Label lblUserName = new Label("Username");
		Label lblPassword = new Label("Password");
		TextField tfUsername = new TextField();
		TextField tfPassword = new TextField();
		
		
		
		gpane.add(lblUserName, 0, 0);
		gpane.add(lblPassword, 0, 1);
		gpane.add(tfUsername, 1, 0, 2, 1);
		gpane.add(tfPassword, 1, 1, 2, 1);
		
		
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER_RIGHT);
		hbox.setSpacing(8);
		Button btnLogin = new Button("Login");
		btnLogin.setDisable(true); 
		Button btnCancel = new Button("Cancel");
		Button btnCreate = new Button("Create");
		hbox.getChildren().addAll(btnLogin, btnCancel);

		gpane.add(hbox, 1, 3, 2 ,1);
		
		tfUsername.setOnKeyTyped(evt-> {
			String usr = tfUsername.getText();
			String pwd = tfPassword.getText();
			
			boolean enableCondition = usr.length() >= 6 && pwd.length() >= 6;
			btnLogin.setDisable(!enableCondition);
			
		});
		
		tfPassword.setOnKeyTyped(evt-> {
			String usr = tfUsername.getText();
			String pwd = tfPassword.getText();
			
			boolean enableCondition = usr.length() >= 6 && pwd.length() >= 6;
			btnLogin.setDisable(!enableCondition);
			
		});
		
	
		btnLogin.setOnAction(e->{
			if(DataCenter.getInstance().validateUserCredential(tfUsername.getText(), tfPassword.getText()) == false) {
				hbox.getChildren().add(btnCreate);
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Try again or create a new user");
				alert.setTitle("This is not a current user");
				alert.setHeaderText("There are no users with that information");
				alert.show();
			}
			else {
				((Stage)(((Button)e.getSource()).getScene().getWindow())).close();
				Pane root2 = profilePane();
				Scene scene2 = new Scene(root2, 300, 250, Color.BLUEVIOLET);
				Stage st = new Stage();
				st.setScene(scene2);
				st.setTitle("My profile");
				st.show();
			}
			
		});
		
		btnCreate.setOnAction(e ->{
			String usr = tfUsername.getText();
			String pwd = tfPassword.getText();
			User usr1 = new User(usr, pwd);
			DataCenter.getInstance().addUser(usr1);
		});
		
		
		btnCancel.setOnAction(e-> {
			((Stage)(((Button)e.getSource()).getScene().getWindow())).close();
		});
		
		return gpane;
	}
	
	public Pane profilePane() {
	
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);
		
		Label lblprofName = new Label("My Stocks");
		Button btn1 = new Button("MSFT");
		btn1.setPrefWidth(70);
		btn1.setPrefHeight(30);
		Button btn2 = new Button("AAPL");
		btn2.setPrefWidth(70);
		btn2.setPrefHeight(30);
		Button btn3 = new Button("PSLV");
		btn3.setPrefWidth(70);
		btn3.setPrefHeight(30);
		Button btn4 = new Button("GOOG");
		btn4.setPrefWidth(70);
		btn4.setPrefHeight(30);
		Button btn5 = new Button("Logout");
		btn5.setPrefWidth(70);
		btn5.setPrefHeight(30);
		
		vbox.getChildren().addAll(lblprofName, btn1, btn2, btn3, btn4, btn5);
		
		btn1.setOnAction(e->{
			((Stage)(((Button)e.getSource()).getScene().getWindow())).close();
			Pane root3 = MSFTPane();
			Scene scene3 = new Scene(root3, 1350, 700, Color.BLUEVIOLET);
			Stage st = new Stage();
			st.setScene(scene3);
			st.setTitle("MSFT INFORMATION");
			st.show();
		});
		
		btn2.setOnAction(e->{
			((Stage)(((Button)e.getSource()).getScene().getWindow())).close();
			Pane root4 = AAPLPane();
			Scene scene4 = new Scene(root4, 1350, 700, Color.BLUEVIOLET);
			Stage st = new Stage();
			st.setScene(scene4);
			st.setTitle("AAPL INFORMATION");
			st.show();
		});

		btn3.setOnAction(e->{
			((Stage)(((Button)e.getSource()).getScene().getWindow())).close();
			Pane root5 = PSLVPane();
			Scene scene5 = new Scene(root5, 1350, 700, Color.BLUEVIOLET);
			Stage st = new Stage();
			st.setScene(scene5);
			st.setTitle("PSLV INFORMATION");
			st.show();
		});

		btn4.setOnAction(e->{
			((Stage)(((Button)e.getSource()).getScene().getWindow())).close();
			Pane root6 = GOOGPane();
			Scene scene6 = new Scene(root6, 1350, 700, Color.BLUEVIOLET);
			Stage st = new Stage();
			st.setScene(scene6);
			st.setTitle("GOOG INFORMATION");
			st.show();
		});
		
		btn5.setOnAction(e->{
			((Stage)(((Button)e.getSource()).getScene().getWindow())).close();
		});
		
		
		return vbox;
		

	}
	
	public Pane MSFTPane() {
		BorderPane root = new BorderPane();
		//root.setTop(demoDatePicker());
		
		StackPane stack = new StackPane();
		String style = "-fx-border-color:lightgreen;" + "-fx-border-width: 2;-fx-border-radius:5;";
		stack.setStyle(style);

		LineChart<Number, Number> lineChart = MSFTLineChart(); 

		Pane tvPane = MSFTTableView();
		
		stack.getChildren().add(lineChart);

		stack.getChildren().add(tvPane);


		root.setCenter(stack);
		
		String style1 = "-fx-border-color:lightblue;" 
				+ "-fx-border-width: 2;-fx-border-radius:3;";
		
		BorderPane btmBox = new BorderPane();
		btmBox.setStyle(style1);
		btmBox.setPadding(new Insets(10, 30, 10, 30));
		Button btnNext = new Button("Next view");
		Button btnPrev = new Button("Previous view");
		Button back = new Button("Back");
		btmBox.setCenter(back);
		btmBox.setLeft(btnPrev);
		btmBox.setRight(btnNext);
		root.setBottom(btmBox);
		
		btnNext.setOnAction(e->{
			ObservableList<Node> childs = stack.getChildren();
			if (childs.size() > 1) {
			   Node topNode = childs.get(childs.size()-1);
			   topNode.toBack();
			}
		});
		
		btnPrev.setOnAction(e->{
			ObservableList<Node> childs = stack.getChildren();
			if (childs.size() > 1) {
			   Node btmNode = childs.get(0);
			   btmNode.toFront();
			}
		});
		
		back.setOnAction(e->{
			((Stage)(((Button)e.getSource()).getScene().getWindow())).close();
			Pane root2 = profilePane();
			Scene scene2 = new Scene(root2, 300, 250, Color.BLUEVIOLET);
			Stage st = new Stage();
			st.setScene(scene2);
			st.setTitle("My profile");
			st.show();
		});
		
		return root;
	}	
	
	public Pane AAPLPane() {
		BorderPane root = new BorderPane();
		//root.setTop(demoDatePicker());
		
		StackPane stack = new StackPane();
		String style = "-fx-border-color:lightgreen;" + "-fx-border-width: 2;-fx-border-radius:5;";
		stack.setStyle(style);

		LineChart<Number, Number> lineChart = AAPLLineChart(); 

		Pane tvPane = AAPLTableView();
		
		stack.getChildren().add(lineChart);

		stack.getChildren().add(tvPane);


		root.setCenter(stack);
		
		String style1 = "-fx-border-color:lightblue;" + "-fx-border-width: 2;-fx-border-radius:3;";
		
		BorderPane btmBox = new BorderPane();
		btmBox.setStyle(style1);
		btmBox.setPadding(new Insets(10, 30, 10, 30));
		Button btnNext = new Button("Next view");
		Button btnPrev = new Button("Previous view");
		Button back = new Button("Back");
		btmBox.setCenter(back);
		btmBox.setLeft(btnPrev);
		btmBox.setRight(btnNext);
		root.setBottom(btmBox);
		
		btnNext.setOnAction(e->{
			ObservableList<Node> childs = stack.getChildren();
			if (childs.size() > 1) {
			   Node topNode = childs.get(childs.size()-1);
			   topNode.toBack();
			}
		});
		
		btnPrev.setOnAction(e->{
			ObservableList<Node> childs = stack.getChildren();
			if (childs.size() > 1) {
			   Node btmNode = childs.get(0);
			   btmNode.toFront();
			}
		});
		
		back.setOnAction(e->{
			((Stage)(((Button)e.getSource()).getScene().getWindow())).close();
			Pane root2 = profilePane();
			Scene scene2 = new Scene(root2, 300, 250, Color.BLUEVIOLET);
			Stage st = new Stage();
			st.setScene(scene2);
			st.setTitle("My profile");
			st.show();
		});
		
		return root;
	}
	
	public Pane PSLVPane(){
		BorderPane root = new BorderPane();
		//root.setTop(demoDatePicker());
		
		StackPane stack = new StackPane();
		String style = "-fx-border-color:lightgreen;" + "-fx-border-width: 2;-fx-border-radius:5;";
		stack.setStyle(style);

		LineChart<Number, Number> lineChart = PSLVLineChart(); 

		Pane tvPane = PSLVTableView();
		
		stack.getChildren().add(lineChart);

		stack.getChildren().add(tvPane);


		root.setCenter(stack);
		
		String style1 = "-fx-border-color:lightblue;" + "-fx-border-width: 2;-fx-border-radius:3;";
		
		BorderPane btmBox = new BorderPane();
		btmBox.setStyle(style1);
		btmBox.setPadding(new Insets(10, 30, 10, 30));
		Button btnNext = new Button("Next view");
		Button btnPrev = new Button("Previous view");
		Button back = new Button("Back");
		btmBox.setCenter(back);
		btmBox.setLeft(btnPrev);
		btmBox.setRight(btnNext);
		root.setBottom(btmBox);
		
		btnNext.setOnAction(e->{
			ObservableList<Node> childs = stack.getChildren();
			if (childs.size() > 1) {
			   Node topNode = childs.get(childs.size()-1);
			   topNode.toBack();
			}
		});
		
		btnPrev.setOnAction(e->{
			ObservableList<Node> childs = stack.getChildren();
			if (childs.size() > 1) {
			   Node btmNode = childs.get(0);
			   btmNode.toFront();
			}
		});
		
		back.setOnAction(e->{
			((Stage)(((Button)e.getSource()).getScene().getWindow())).close();
			Pane root2 = profilePane();
			Scene scene2 = new Scene(root2, 300, 250, Color.BLUEVIOLET);
			Stage st = new Stage();
			st.setScene(scene2);
			st.setTitle("My profile");
			st.show();
		});
		
		return root;
	}
	
	public Pane GOOGPane() {
		BorderPane root = new BorderPane();
		//root.setTop(demoDatePicker());
		
		StackPane stack = new StackPane();
		String style = "-fx-border-color:lightgreen;" + "-fx-border-width: 2;-fx-border-radius:5;";
		stack.setStyle(style);

		LineChart<Number, Number> lineChart = GOOGLineChart(); 

		Pane tvPane = GOOGTableView();
		
		stack.getChildren().add(lineChart);

		stack.getChildren().add(tvPane);


		root.setCenter(stack);
		
		String style1 = "-fx-border-color:lightblue;" + "-fx-border-width: 2;-fx-border-radius:3;";
		
		BorderPane btmBox = new BorderPane();
		btmBox.setStyle(style1);
		btmBox.setPadding(new Insets(10, 30, 10, 30));
		Button btnNext = new Button("Next view");
		Button btnPrev = new Button("Previous view");
		Button back = new Button("Back");
		btmBox.setCenter(back);
		btmBox.setLeft(btnPrev);
		btmBox.setRight(btnNext);
		root.setBottom(btmBox);
		
		btnNext.setOnAction(e->{
			ObservableList<Node> childs = stack.getChildren();
			if (childs.size() > 1) {
			   Node topNode = childs.get(childs.size()-1);
			   topNode.toBack();
			}
		});
		
		btnPrev.setOnAction(e->{
			ObservableList<Node> childs = stack.getChildren();
			if (childs.size() > 1) {
			   Node btmNode = childs.get(0);
			   btmNode.toFront();
			}
		});
		
		back.setOnAction(e->{
			((Stage)(((Button)e.getSource()).getScene().getWindow())).close();
			Pane root2 = profilePane();
			Scene scene2 = new Scene(root2, 300, 250, Color.BLUEVIOLET);
			Stage st = new Stage();
			st.setScene(scene2);
			st.setTitle("My profile");
			st.show();
		});
		
		
		return root;
	}
	
	
	public Pane AAPLTableView() {
		
		String style = "-fx-background-color:lightgreen;-fx-font-size:12px;-fx-font-weight: bold";
		BorderPane bpane = new BorderPane();
		bpane.setStyle(style);
		bpane.setPadding(new Insets(10));
		
		Label lblTitle = new Label("TableView");
		lblTitle.setPadding(new Insets(5));
		lblTitle.setTextFill(Color.TEAL);
		bpane.setTop(lblTitle);
		
		
		TableView<StockPrice> tv = new TableView<StockPrice>();

		TableColumn<StockPrice, String> col1 = new TableColumn<>("Date");
		col1.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		TableColumn<StockPrice, Double> col2 = new TableColumn<>("Open");
		col2.setCellValueFactory(new PropertyValueFactory<>("open"));
		
		TableColumn<StockPrice, Double> col3 = new TableColumn<>("High");
		col3.setCellValueFactory(new PropertyValueFactory<>("high"));
		
		TableColumn<StockPrice, Double> col4 = new TableColumn<>("Low");
		col4.setCellValueFactory(new PropertyValueFactory<>("low"));

		TableColumn<StockPrice, Double> col5 = new TableColumn<>("Close");
		col5.setCellValueFactory(new PropertyValueFactory<>("close"));

		TableColumn<StockPrice, Double> col6 = new TableColumn<>("AdjClose");
		col6.setCellValueFactory(new PropertyValueFactory<>("adjclose"));

		TableColumn<StockPrice, Integer> col7 = new TableColumn<>("Volume");
		col7.setCellValueFactory(new PropertyValueFactory<>("volume"));
		

		tv.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);
		tv.setItems(data2);
		
		
		bpane.setCenter(tv);
		
		
		readAAPL();
		
		return bpane;
	}
	
	public Pane GOOGTableView() {
		
		String style = "-fx-background-color:lightgreen;-fx-font-size:12px;-fx-font-weight: bold";
		BorderPane bpane = new BorderPane();
		bpane.setStyle(style);
		bpane.setPadding(new Insets(10));
		
		Label lblTitle = new Label("TableView");
		lblTitle.setPadding(new Insets(5));
		lblTitle.setTextFill(Color.TEAL);
		bpane.setTop(lblTitle);
		
		
		TableView<StockPrice> tv = new TableView<StockPrice>();

		TableColumn<StockPrice, String> col1 = new TableColumn<>("Date");
		col1.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		TableColumn<StockPrice, Double> col2 = new TableColumn<>("Open");
		col2.setCellValueFactory(new PropertyValueFactory<>("open"));
		
		TableColumn<StockPrice, Double> col3 = new TableColumn<>("High");
		col3.setCellValueFactory(new PropertyValueFactory<>("high"));
		
		TableColumn<StockPrice, Double> col4 = new TableColumn<>("Low");
		col4.setCellValueFactory(new PropertyValueFactory<>("low"));

		TableColumn<StockPrice, Double> col5 = new TableColumn<>("Close");
		col5.setCellValueFactory(new PropertyValueFactory<>("close"));

		TableColumn<StockPrice, Double> col6 = new TableColumn<>("AdjClose");
		col6.setCellValueFactory(new PropertyValueFactory<>("adjclose"));

		TableColumn<StockPrice, Integer> col7 = new TableColumn<>("Volume");
		col7.setCellValueFactory(new PropertyValueFactory<>("volume"));
		

		tv.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);
		tv.setItems(data2);
		
		
		bpane.setCenter(tv);
		
		
		readGOOG();
		
		return bpane;
	}
	
	public Pane MSFTTableView() {
		
		String style = "-fx-background-color:lightgreen;-fx-font-size:12px;-fx-font-weight: bold";
		BorderPane bpane = new BorderPane();
		bpane.setStyle(style);
		bpane.setPadding(new Insets(10));
		
		Label lblTitle = new Label("TableView");
		lblTitle.setPadding(new Insets(5));
		lblTitle.setTextFill(Color.TEAL);
		bpane.setTop(lblTitle);
		
		
		TableView<StockPrice> tv = new TableView<StockPrice>();

		TableColumn<StockPrice, String> col1 = new TableColumn<>("Date");
		col1.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		TableColumn<StockPrice, Double> col2 = new TableColumn<>("Open");
		col2.setCellValueFactory(new PropertyValueFactory<>("open"));
		
		TableColumn<StockPrice, Double> col3 = new TableColumn<>("High");
		col3.setCellValueFactory(new PropertyValueFactory<>("high"));
		
		TableColumn<StockPrice, Double> col4 = new TableColumn<>("Low");
		col4.setCellValueFactory(new PropertyValueFactory<>("low"));

		TableColumn<StockPrice, Double> col5 = new TableColumn<>("Close");
		col5.setCellValueFactory(new PropertyValueFactory<>("close"));

		TableColumn<StockPrice, Double> col6 = new TableColumn<>("AdjClose");
		col6.setCellValueFactory(new PropertyValueFactory<>("adjclose"));

		TableColumn<StockPrice, Integer> col7 = new TableColumn<>("Volume");
		col7.setCellValueFactory(new PropertyValueFactory<>("volume"));
		

		tv.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);
		tv.setItems(data2);
		
		
		bpane.setCenter(tv);
		
		
		readMSFT();
		
		return bpane;
	}

	public Pane PSLVTableView() {
		
		String style = "-fx-background-color:lightgreen;-fx-font-size:12px;-fx-font-weight: bold";
		BorderPane bpane = new BorderPane();
		bpane.setStyle(style);
		bpane.setPadding(new Insets(10));
		
		Label lblTitle = new Label("TableView");
		lblTitle.setPadding(new Insets(5));
		lblTitle.setTextFill(Color.TEAL);
		bpane.setTop(lblTitle);
		
		
		TableView<StockPrice> tv = new TableView<StockPrice>();

		TableColumn<StockPrice, String> col1 = new TableColumn<>("Date");
		col1.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		TableColumn<StockPrice, Double> col2 = new TableColumn<>("Open");
		col2.setCellValueFactory(new PropertyValueFactory<>("open"));
		
		TableColumn<StockPrice, Double> col3 = new TableColumn<>("High");
		col3.setCellValueFactory(new PropertyValueFactory<>("high"));
		
		TableColumn<StockPrice, Double> col4 = new TableColumn<>("Low");
		col4.setCellValueFactory(new PropertyValueFactory<>("low"));

		TableColumn<StockPrice, Double> col5 = new TableColumn<>("Close");
		col5.setCellValueFactory(new PropertyValueFactory<>("close"));

		TableColumn<StockPrice, Double> col6 = new TableColumn<>("AdjClose");
		col6.setCellValueFactory(new PropertyValueFactory<>("adjclose"));

		TableColumn<StockPrice, Integer> col7 = new TableColumn<>("Volume");
		col7.setCellValueFactory(new PropertyValueFactory<>("volume"));
		

		tv.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);
		tv.setItems(data2);
		
		
		bpane.setCenter(tv);
		
		
		readPSLV();
		
		return bpane;
	}
	
	public LineChart<Number, Number> AAPLLineChart(){
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Days");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Price");
		
		String style = "-fx-background-color:lightblue";
		LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
		lineChart.setStyle(style);
		lineChart.setPadding(new Insets(20));
		
		XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
 		series1.setName("High Price");
 		XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
 		series2.setName("Low Price");
 		
		String File = "AAPL";
		String FieldDelimiter = ",";

		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(File));
			String line;
			int x = 0;
			while ((line = br.readLine()) != null) {
				String[] priceData = line.split(FieldDelimiter, -1);
	            double priceData2 = Double.parseDouble(priceData[2]);
	            series1.getData().add(new XYChart.Data(x, priceData2));
	            x++;
	            }

		}
		catch (FileNotFoundException ex) {
			ex.getStackTrace();
		}
		catch (IOException ex) {
		ex.getStackTrace();
		}
		lineChart.getData().add(series1);
		
		try {
			br = new BufferedReader(new FileReader(File));
			String line;
			int x = 0;
			while ((line = br.readLine()) != null) {
				String[] priceData = line.split(FieldDelimiter, -1);
	            double priceData3 = Double.parseDouble(priceData[3]);
	            series2.getData().add(new XYChart.Data(x, priceData3));
	            x++;
	            }

		}
		catch (FileNotFoundException ex) {
			ex.getStackTrace();
		}
		catch (IOException ex) {
		ex.getStackTrace();
		}
		lineChart.getData().add(series2);
		
		
		
		
		return lineChart;
		
	}
	
	public LineChart<Number, Number> MSFTLineChart(){
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Days");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Price");
		
		String style = "-fx-background-color:lightblue";
		LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
		lineChart.setStyle(style);
		lineChart.setPadding(new Insets(20));
		
		XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
 		series1.setName("High Price");
 		XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
 		series2.setName("Low Price");
 		
		String File = "MSFT";
		String FieldDelimiter = ",";

		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(File));
			String line;
			int x = 0;
			while ((line = br.readLine()) != null) {
				String[] priceData = line.split(FieldDelimiter, -1);
	            double priceData2 = Double.parseDouble(priceData[2]);
	            series1.getData().add(new XYChart.Data(x, priceData2));
	            x++;
	            }

		}
		catch (FileNotFoundException ex) {
			ex.getStackTrace();
		}
		catch (IOException ex) {
		ex.getStackTrace();
		}
		lineChart.getData().add(series1);
		
		try {
			br = new BufferedReader(new FileReader(File));
			String line;
			int x = 0;
			while ((line = br.readLine()) != null) {
				String[] priceData = line.split(FieldDelimiter, -1);
	            double priceData3 = Double.parseDouble(priceData[3]);
	            series2.getData().add(new XYChart.Data(x, priceData3));
	            x++;
	            }

		}
		catch (FileNotFoundException ex) {
			ex.getStackTrace();
		}
		catch (IOException ex) {
		ex.getStackTrace();
		}
		lineChart.getData().add(series2);
		
		
		
		
		return lineChart;
		
	}
	
	public LineChart<Number, Number> GOOGLineChart(){
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Days");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Price");
		
		String style = "-fx-background-color:lightblue";
		LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
		lineChart.setStyle(style);
		lineChart.setPadding(new Insets(20));
		
		XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
 		series1.setName("High Price");
 		XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
 		series2.setName("Low Price");
 		
		String File = "GOOG";
		String FieldDelimiter = ",";

		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(File));
			String line;
			int x = 0;
			while ((line = br.readLine()) != null) {
				String[] priceData = line.split(FieldDelimiter, -1);
	            double priceData2 = Double.parseDouble(priceData[2]);
	            series1.getData().add(new XYChart.Data(x, priceData2));
	            x++;
	            }

		}
		catch (FileNotFoundException ex) {
			ex.getStackTrace();
		}
		catch (IOException ex) {
		ex.getStackTrace();
		}
		lineChart.getData().add(series1);
		
		try {
			br = new BufferedReader(new FileReader(File));
			String line;
			int x = 0;
			while ((line = br.readLine()) != null) {
				String[] priceData = line.split(FieldDelimiter, -1);
	            double priceData3 = Double.parseDouble(priceData[3]);
	            series2.getData().add(new XYChart.Data(x, priceData3));
	            x++;
	            }

		}
		catch (FileNotFoundException ex) {
			ex.getStackTrace();
		}
		catch (IOException ex) {
		ex.getStackTrace();
		}
		lineChart.getData().add(series2);
		
		
		
		
		return lineChart;
		
	}
	
	public LineChart<Number, Number> PSLVLineChart(){
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Days");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Price");
		
		String style = "-fx-background-color:lightblue";
		LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
		lineChart.setStyle(style);
		lineChart.setPadding(new Insets(20));
		
		XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
 		series1.setName("High Price");
 		XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
 		series2.setName("Low Price");
 		
		String File = "PSLV";
		String FieldDelimiter = ",";

		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(File));
			String line;
			int x = 0;
			while ((line = br.readLine()) != null) {
				String[] priceData = line.split(FieldDelimiter, -1);
	            double priceData2 = Double.parseDouble(priceData[2]);
	            series1.getData().add(new XYChart.Data(x, priceData2));
	            x++;
	            }

		}
		catch (FileNotFoundException ex) {
			ex.getStackTrace();
		}
		catch (IOException ex) {
		ex.getStackTrace();
		}
		lineChart.getData().add(series1);
		
		try {
			br = new BufferedReader(new FileReader(File));
			String line;
			int x = 0;
			while ((line = br.readLine()) != null) {
				String[] priceData = line.split(FieldDelimiter, -1);
	            double priceData3 = Double.parseDouble(priceData[3]);
	            series2.getData().add(new XYChart.Data(x, priceData3));
	            x++;
	            }

		}
		catch (FileNotFoundException ex) {
			ex.getStackTrace();
		}
		catch (IOException ex) {
		ex.getStackTrace();
		}
		lineChart.getData().add(series2);
		
		
		
		
		return lineChart;
		
	}
	
	private void readAAPL() {

	       String File = "AAPL";
	       String FieldDelimiter = ",";

	       BufferedReader br;

	       try {
	           br = new BufferedReader(new FileReader(File));
	           String line;
	           while ((line = br.readLine()) != null) {
	               String[] priceData = line.split(FieldDelimiter, -1);
	               double priceData1 = Double.parseDouble(priceData[1]);
	               double priceData2 = Double.parseDouble(priceData[2]);
	               double priceData3 = Double.parseDouble(priceData[3]);
	               double priceData4 = Double.parseDouble(priceData[4]);
	               double priceData5 = Double.parseDouble(priceData[5]);
	               int priceData6 = Integer.parseInt(priceData[6]);
	               
	               StockPrice s1 = new StockPrice(priceData[0], priceData1, priceData2, priceData3, priceData4, priceData5, priceData6);
	               data2.add(s1);

	            }

	        } 
	        catch (FileNotFoundException ex) {
	        	ex.getStackTrace();
	        }
	        catch (IOException ex) {
	        	ex.getStackTrace();
	        }

	    }
	
	private void readGOOG() {

	       String File = "GOOG";
	       String FieldDelimiter = ",";

	       BufferedReader br;

	       try {
	           br = new BufferedReader(new FileReader(File));
	           String line;
	           while ((line = br.readLine()) != null) {
	               String[] priceData = line.split(FieldDelimiter, -1);
	               double priceData1 = Double.parseDouble(priceData[1]);
	               double priceData2 = Double.parseDouble(priceData[2]);
	               double priceData3 = Double.parseDouble(priceData[3]);
	               double priceData4 = Double.parseDouble(priceData[4]);
	               double priceData5 = Double.parseDouble(priceData[5]);
	               int priceData6 = Integer.parseInt(priceData[6]);
	               StockPrice s1 = new StockPrice(priceData[0], priceData1, priceData2, priceData3, priceData4, priceData5, priceData6);
	               data2.add(s1);

	            }

	        } 
	        catch (FileNotFoundException ex) {
	        	ex.getStackTrace();
	        }
	        catch (IOException ex) {
	        	ex.getStackTrace();
	        }

	    }
	
	private void readMSFT() {

	       String File = "MSFT";
	       String FieldDelimiter = ",";

	       BufferedReader br;

	       try {
	           br = new BufferedReader(new FileReader(File));
	           String line;
	           while ((line = br.readLine()) != null) {
	               String[] priceData = line.split(FieldDelimiter, -1);
	               double priceData1 = Double.parseDouble(priceData[1]);
	               double priceData2 = Double.parseDouble(priceData[2]);
	               double priceData3 = Double.parseDouble(priceData[3]);
	               double priceData4 = Double.parseDouble(priceData[4]);
	               double priceData5 = Double.parseDouble(priceData[5]);
	               int priceData6 = Integer.parseInt(priceData[6]);
	               StockPrice s1 = new StockPrice(priceData[0], priceData1, priceData2, priceData3, priceData4, priceData5, priceData6);
	               data2.add(s1);

	            }

	        } 
	        catch (FileNotFoundException ex) {
	        	ex.getStackTrace();
	        }
	        catch (IOException ex) {
	        	ex.getStackTrace();
	        }

	    }
	
	private void readPSLV() {

	       String File = "PSLV";
	       String FieldDelimiter = ",";

	       BufferedReader br;

	       try {
	           br = new BufferedReader(new FileReader(File));
	           String line;
	           while ((line = br.readLine()) != null) {
	               String[] priceData = line.split(FieldDelimiter, -1);
	               double priceData1 = Double.parseDouble(priceData[1]);
	               double priceData2 = Double.parseDouble(priceData[2]);
	               double priceData3 = Double.parseDouble(priceData[3]);
	               double priceData4 = Double.parseDouble(priceData[4]);
	               double priceData5 = Double.parseDouble(priceData[5]);
	               int priceData6 = Integer.parseInt(priceData[6]);
	               StockPrice s1 = new StockPrice(priceData[0], priceData1, priceData2, priceData3, priceData4, priceData5, priceData6);
	               data2.add(s1);

	            }

	        } 
	        catch (FileNotFoundException ex) {
	        	ex.getStackTrace();
	        }
	        catch (IOException ex) {
	        	ex.getStackTrace();
	        }

	    }

	public static void main(String[] args) {
		Application.launch(args); 
		
	}
	
	
	
}
