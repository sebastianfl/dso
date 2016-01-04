package org.seb.dso.fx.controller;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.seb.dso.fx.model.CharacterModel;
import org.seb.dso.fx.service.GenerateSnapshotsTask;
import org.seb.dso.fx.service.LoadItemsTask;
import org.seb.dso.fx.service.ProcessTask;
import org.seb.dso.model.Item;
import org.seb.dso.model.enumeration.CharClass;
import org.seb.dso.model.enumeration.EssenceType;

import javafx.beans.binding.Bindings;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.util.converter.NumberStringConverter;
import jfxtras.scene.control.ToggleGroupValue;

public class OptimizerController implements Initializable {

	private ResourceBundle bundle;

	private final CharacterModel model;

	@FXML
	private Button buttonLoadItems;
	@FXML
	private TextField textDefensiveGems;
	@FXML
	private TextField textOffensiveGems;
	@FXML
	private TextField textPetAndBuffsGems;
	@FXML
	private Label labelSelectClass;
	@FXML
	private ComboBox<CharClass> comboboxClass;
	@FXML
	private CheckBox checkboxTwohanded;
	@FXML
	private Button buttonGenerateSnapshots;
	@FXML
	private Button buttonProcess;
	@FXML
	private Label labelSnapshotsNumber;
	@FXML
	private Slider sliderAttack;
	@FXML
	private Slider sliderAgility;
	@FXML
	private CheckBox checkboxWeaponDamage;
	@FXML
	private CheckBox checkboxRage;
	@FXML
	private RadioButton radioNoEssences;
	@FXML
	private RadioButton radioGreen;
	@FXML
	private RadioButton radioBlue;
	@FXML
	private RadioButton radioPurple;
	@FXML
	private RadioButton radioRed;
	@FXML
	private TextField textPetAndBuffs;
	@FXML
	private Label labelStatus;
	@FXML
	private ProgressBar progressBar;

	@FXML
	private TableView<Item> tableBestItems;

	@FXML
	private TableView<?> tableBestPower;

	@FXML
	private ToggleGroupValue<EssenceType> groupEssence;

	@FXML
	HBox essencesContainer;

	public OptimizerController() {
		model = new CharacterModel();
	}

	@FXML
	protected void loadItems(ActionEvent event) throws Exception {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Select Items File");
		chooser.setInitialDirectory(new File("."));
		model.setItemsFile(chooser.showOpenDialog(this.buttonLoadItems.getScene().getWindow()));
		LoadItemsTask task = new LoadItemsTask(this.model);

		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
	}

	@FXML
	public void generateSnapshots(ActionEvent event) {

		GenerateSnapshotsTask task = new GenerateSnapshotsTask(this.model);

		this.buttonGenerateSnapshots.disableProperty().bind(task.runningProperty());
		this.progressBar.visibleProperty().bind(task.runningProperty());
		this.progressBar.progressProperty().bind(task.progressProperty());

		List<Item> list = model.getItems();
		for (Iterator<Item> iterator = list.iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			item.setSelected(true);
		}

		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
	}

	@FXML
	public void process(ActionEvent event) {
		ProcessTask task = new ProcessTask(this.model);

		this.buttonProcess.disableProperty().bind(task.runningProperty());
		this.progressBar.visibleProperty().bind(task.runningProperty());
		this.progressBar.progressProperty().bind(task.progressProperty());

		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent arg0) {
				tableBestItems.setItems(model.getBestSnapshot().getItemsAsObservableList());
			}
		});
		task.setOnFailed(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent event) {
				task.getException().printStackTrace();

			}
		});

		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle res) {
		bundle = res;
		comboboxClass.getItems().setAll(CharClass.values());
		comboboxClass.setValue(CharClass.MAGE);

		initEssenceToggles();

		textDefensiveGems.textProperty().bindBidirectional(model.defensiveGemsProperty());
		textOffensiveGems.textProperty().bindBidirectional(model.offensiveGemsProperty());
		textPetAndBuffs.textProperty().bindBidirectional(model.petAndBuffsProperty());
		sliderAgility.valueProperty().bindBidirectional(model.agilityProperty());
		sliderAttack.valueProperty().bindBidirectional(model.attackProperty());

		checkboxRage.selectedProperty().bindBidirectional(model.rageProperty());
		checkboxTwohanded.selectedProperty().bindBidirectional(model.twohandedProperty());
		checkboxWeaponDamage.selectedProperty().bindBidirectional(model.weaponDamageProperty());

		comboboxClass.valueProperty().bindBidirectional(model.charClassProperty());
		model.charClassProperty().set(CharClass.MAGE);

		groupEssence.valueProperty().bindBidirectional(model.essenceTypeProperty());
		model.essenceTypeProperty().set(EssenceType.GREEN);

		Tooltip attackTooltip = new Tooltip();
		sliderAttack.setTooltip(attackTooltip);
		Bindings.bindBidirectional(attackTooltip.textProperty(), sliderAttack.valueProperty(),
				new NumberStringConverter());

		Tooltip agilityTooltip = new Tooltip();
		sliderAttack.setTooltip(agilityTooltip);
		Bindings.bindBidirectional(agilityTooltip.textProperty(), sliderAgility.valueProperty(),
				new NumberStringConverter());
		
		initTables();
	}

	private void initTables() {
        TableColumn<Item, String> firstNameCol = new TableColumn<Item, String>("Item Type");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("itemType"));
 
        TableColumn<Item, String> lastNameCol = new TableColumn<Item, String>("Item Set");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Item, String>("itemSet"));	
        tableBestItems.getColumns().addAll(firstNameCol, lastNameCol);    
	}

	private void initEssenceToggles() {
		this.groupEssence = new ToggleGroupValue<EssenceType>();
		for (EssenceType type : EssenceType.values()) {
			RadioButton r = new RadioButton(type.getMessage());
			r.setUserData(type);
			this.groupEssence.add(r, (EssenceType) r.getUserData());
			essencesContainer.getChildren().add(r);
		}
	}
}