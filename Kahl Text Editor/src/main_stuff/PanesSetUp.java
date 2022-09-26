package main_stuff;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PanesSetUp extends Application {
	TextField tfUsername = null;
	TextField tfPassword = null;
	UserBag ub = new UserBag();
	TextArea maintxt = new TextArea();
	TextField status = new TextField();
	File lastFile;
	String starter;

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
		hbox.getChildren().addAll(btnLogin, btnCancel, btnCreate);

		gpane.add(hbox, 1, 3, 2, 1);

		tfUsername.setOnKeyTyped(evt -> {
			String usr = tfUsername.getText();
			String pwd = tfPassword.getText();

			boolean enableCondition = usr.length() >= 6 && pwd.length() >= 6;
			btnLogin.setDisable(!enableCondition);

		});

		tfPassword.setOnKeyTyped(evt -> {
			String usr = tfUsername.getText();
			String pwd = tfPassword.getText();

			boolean enableCondition = usr.length() >= 6 && pwd.length() >= 6;
			btnLogin.setDisable(!enableCondition);

		});

		btnLogin.setOnAction(e -> {
			if (UserBag.find(tfUsername.getText().toString()) == false) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Try again or create a new user");
				alert.setTitle("This is not a current user");
				alert.setHeaderText("There are no users with that information");
				alert.show();
			} else {
				((Stage) (((Button) e.getSource()).getScene().getWindow())).close();
				Pane root2 = mainPane();
				Scene scene2 = new Scene(root2, 400, 350, Color.BLUEVIOLET);
				Stage st = new Stage();
				st.setScene(scene2);
				st.setTitle("Text Editor");
				st.show();
			}

		});

		btnCreate.setOnAction(e -> {
			((Stage) (((Button) e.getSource()).getScene().getWindow())).close();
			Pane root3 = signupPane();
			Scene scene3 = new Scene(root3, 300, 250, Color.BLUEVIOLET);
			Stage st = new Stage();
			st.setScene(scene3);
			st.setTitle("Sign Up");
			st.show();

		});

		btnCancel.setOnAction(e -> {
			((Stage) (((Button) e.getSource()).getScene().getWindow())).close();
		});

		return gpane;
	}

	public Pane signupPane() {
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
		Button btnCreate = new Button("Create");
		btnCreate.setDisable(true);
		hbox.getChildren().addAll(btnCreate);

		gpane.add(hbox, 1, 3, 2, 1);

		tfUsername.setOnKeyTyped(evt -> {
			String usr = tfUsername.getText();
			String pwd = tfPassword.getText();

			boolean enableCondition = usr.length() >= 6 && (PasswordChecker.pass(pwd)==true);
			btnCreate.setDisable(!enableCondition);

		});

		tfPassword.setOnKeyTyped(evt -> {
			String usr = tfUsername.getText();
			String pwd = tfPassword.getText();

			boolean enableCondition = usr.length() >= 6 && (PasswordChecker.pass(pwd)==true);
			btnCreate.setDisable(!enableCondition);

		});

		btnCreate.setOnAction(e -> {
			String usr = tfUsername.getText();
			String pwd = tfPassword.getText();
			User usr1 = new User(usr, pwd);
			ub.insert(usr1);

			((Stage) (((Button) e.getSource()).getScene().getWindow())).close();
			Pane root4 = loginPane();
			Scene scene4 = new Scene(root4, 300, 250, Color.BLUEVIOLET);
			Stage st = new Stage();
			st.setScene(scene4);
			st.setTitle("Log in");
			st.show();

		});

		return gpane;
	}

	public Pane mainPane() {
		BorderPane bp = new BorderPane();

		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		Menu menuEdit = new Menu("Edit");

		MenuItem nw = new MenuItem("New");
		MenuItem op = new MenuItem("Open");
		MenuItem cl = new MenuItem("Close");
		MenuItem sv = new MenuItem("Save");
		MenuItem sva = new MenuItem("Save As");
		MenuItem ext = new MenuItem("Exit");
		MenuItem sc = new MenuItem("Spell-Check");
		MenuItem ln = new MenuItem("Learn");
		MenuItem ct = new MenuItem("Create");

		menuFile.getItems().addAll(nw, op, cl, sv, sva, sc, ln, ct, ext);

		MenuItem ud = new MenuItem("Undo");
		MenuItem wc = new MenuItem("Word Count");
		MenuItem sct = new MenuItem("Sentence Count");
		MenuItem fs = new MenuItem("Flesch Score");

		menuEdit.getItems().addAll(ud, wc, sct, fs);

		menuBar.getMenus().addAll(menuFile, menuEdit);
		bp.setTop(menuBar);

		bp.setCenter(maintxt);

		bp.setBottom(status);

		nw.setOnAction(e -> {
			maintxt.setText("");
		});

		ext.setOnAction(e -> {
			System.exit(0);
		});

		op.setOnAction(e -> {
			Pane root4 = openPane();
			Scene scene4 = new Scene(root4, 300, 250, Color.BLUEVIOLET);
			Stage st = new Stage();
			st.setScene(scene4);
			st.setTitle("Open a File");
			st.show();

		});

		cl.setOnAction(e -> {
			Pane root4 = askSave();
			Scene scene4 = new Scene(root4, 300, 250, Color.BLUEVIOLET);
			Stage st = new Stage();
			st.setScene(scene4);
			st.setTitle("Before you close");
			st.show();
		});

		sv.setOnAction(e -> {
			String save = maintxt.getText();
			Save.sv(save, lastFile);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Saving");
			alert.setHeaderText("The current text is being saved");
			alert.show();

		});

		sva.setOnAction(e -> {
			String saver = maintxt.getText();

			Pane root4 = saveasPane(saver);
			Scene scene4 = new Scene(root4, 300, 250, Color.BLUEVIOLET);
			Stage st = new Stage();
			st.setScene(scene4);
			st.setTitle("Save As");
			st.show();

		});

		sc.setOnAction(e -> {
			String check = maintxt.getText();
			try {
				String results = Spellcheck.checker(check);
				status.setText("Incorrect Words: " + results);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		ln.setOnAction(e -> {
			Pane root4 = learnPane();
			Scene scene4 = new Scene(root4, 300, 250, Color.BLUEVIOLET);
			Stage st = new Stage();
			st.setScene(scene4);
			st.setTitle("Learn");
			st.show();

		});

		ct.setOnAction(e -> {
			Pane root4 = createPane();
			Scene scene4 = new Scene(root4, 300, 250, Color.BLUEVIOLET);
			Stage st = new Stage();
			st.setScene(scene4);
			st.setTitle("Create");
			st.show();
		});

		ud.setOnAction(e -> {
			maintxt.undo();
		});

		wc.setOnAction(e -> {
			String words = maintxt.getText();
			double number = FleschScore.countWords(words);
			status.setText("Word Count: " + String.valueOf(number));
		});

		sct.setOnAction(e -> {
			String sentences = maintxt.getText();
			double number = FleschScore.countSentences(sentences);
			status.setText("Sentence Count: " + String.valueOf(number));

		});

		fs.setOnAction(e -> {
			String text = maintxt.getText();
			String result = FleschScore.getFleschScore(text);
			status.setText("Flesch Score: " + result);

		});

		return bp;

	}

	public Pane saveasPane(String str) {
		GridPane gpane = new GridPane();
		gpane.setAlignment(Pos.CENTER);
		gpane.setHgap(10);
		gpane.setVgap(10);

		Label lblFileName = new Label("Name of new File");
		TextField tfFilename = new TextField();

		gpane.add(lblFileName, 0, 0);
		gpane.add(tfFilename, 1, 0, 2, 1);

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER_RIGHT);
		hbox.setSpacing(8);
		Button btnSave = new Button("Save As");
		hbox.getChildren().addAll(btnSave);

		gpane.add(hbox, 1, 3, 2, 1);

		btnSave.setOnAction(e -> {
			File f = new File(tfFilename.getText());
			SaveAs.sa(str, f);
			lastFile = f;

			((Stage) (((Button) e.getSource()).getScene().getWindow())).close();
		});

		return gpane;

	}

	public Pane openPane() {
		GridPane gpane = new GridPane();
		gpane.setAlignment(Pos.CENTER);
		gpane.setHgap(10);
		gpane.setVgap(10);

		Label lblFileName = new Label("Choose file to open");
		TextField tfFilename = new TextField();

		gpane.add(lblFileName, 0, 0);
		gpane.add(tfFilename, 1, 0, 2, 1);

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER_RIGHT);
		hbox.setSpacing(8);
		Button btnOpen = new Button("Open");
		hbox.getChildren().addAll(btnOpen);

		gpane.add(hbox, 1, 3, 2, 1);

		btnOpen.setOnAction(e -> {
			File f = new File(tfFilename.getText());
			String content = FileOpen.openFile(f);
			maintxt.setText(content);
			((Stage) (((Button) e.getSource()).getScene().getWindow())).close();
		});

		return gpane;

	}

	public Pane askSave() {
		GridPane gpane = new GridPane();
		gpane.setAlignment(Pos.CENTER);
		gpane.setHgap(10);
		gpane.setVgap(10);

		Label lblFileName = new Label("Do you want to save?");

		gpane.add(lblFileName, 0, 0);

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER_RIGHT);
		hbox.setSpacing(8);
		Button btnY = new Button("Yes");
		Button btnN = new Button("No");
		hbox.getChildren().addAll(btnY, btnN);

		gpane.add(hbox, 1, 3, 2, 1);

		btnY.setOnAction(e -> {
			((Stage) (((Button) e.getSource()).getScene().getWindow())).close();
			String save = maintxt.getText();
			Pane root4 = saveasPane(save);
			Scene scene4 = new Scene(root4, 300, 250, Color.BLUEVIOLET);
			Stage st = new Stage();
			st.setScene(scene4);
			st.setTitle("Save As");
			st.show();
		});

		btnN.setOnAction(e -> {
			maintxt.setText("");
			((Stage) (((Button) e.getSource()).getScene().getWindow())).close();
		});

		return gpane;

	}

	public Pane learnPane() {
		GridPane gpane = new GridPane();
		gpane.setAlignment(Pos.CENTER);
		gpane.setHgap(10);
		gpane.setVgap(10);

		Label lblFileName = new Label("Choose file to open");
		TextField tfFilename = new TextField();
		Label lblStartWord = new Label("What word to start with?");
		TextField tfStartWord = new TextField();

		gpane.add(lblFileName, 0, 0);
		gpane.add(tfFilename, 1, 0, 2, 1);
		gpane.add(lblStartWord, 0, 1);
		gpane.add(tfStartWord, 1, 1, 2, 1);

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER_RIGHT);
		hbox.setSpacing(8);
		Button btnLearn = new Button("Learn");
		hbox.getChildren().addAll(btnLearn);

		gpane.add(hbox, 1, 3, 2, 1);

		btnLearn.setOnAction(e -> {
			File f = new File(tfFilename.getText());
			String content = FileOpen.openFile(f);
			starter = tfStartWord.getText();
			MarkovTextGenerator.addWords(content, starter);
			((Stage) (((Button) e.getSource()).getScene().getWindow())).close();
		});

		return gpane;
	}

	public Pane createPane() {
		GridPane gpane = new GridPane();
		gpane.setAlignment(Pos.CENTER);
		gpane.setHgap(10);
		gpane.setVgap(10);

		Label lblslen = new Label("Sentence length to create");
		TextField tfslen = new TextField();

		gpane.add(lblslen, 0, 0);
		gpane.add(tfslen, 1, 0, 2, 1);

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER_RIGHT);
		hbox.setSpacing(8);
		Button btnCreate = new Button("Create");
		hbox.getChildren().addAll(btnCreate);

		gpane.add(hbox, 1, 3, 2, 1);

		btnCreate.setOnAction(e -> {
			int num = Integer.parseInt(tfslen.getText());
			String result = MarkovTextGenerator.generateSentence(num);
			maintxt.setText(result);
			((Stage) (((Button) e.getSource()).getScene().getWindow())).close();
		});

		return gpane;
	}

	public static void main(String[] args) {
		Application.launch(args);

	}
}
