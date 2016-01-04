package org.seb.dso.controller;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.seb.dso.Inventory;
import org.seb.dso.fx.model.CharacterModel;
import org.seb.dso.model.CharClass;
import org.seb.dso.model.EssenceType;
import org.seb.dso.model.Item;
import org.seb.dso.model.Modifier;
import org.seb.dso.model.OptimizerModel;
import org.seb.dso.processor.SnapshotProcessor;
import org.seb.dso.util.ItemUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import jfxtras.scene.control.ToggleGroupValue;
import javafx.scene.layout.HBox;

public class OptimizerController implements Initializable {

	private ResourceBundle bundle;

	private final CharacterModel model;

	private SnapshotProcessor processor;

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
	private TableView tableBestItems;

	@FXML
	private TableView tableBestPower;

	@FXML
	private ToggleGroupValue<EssenceType> groupEssence;

	@FXML HBox essencesContainer;

	public OptimizerController() {
		model = new CharacterModel();
	}

	@FXML
	protected void loadItems(ActionEvent event) throws Exception {
		
		System.out.println(this.model);
		
		OptimizerModel om = new OptimizerModel();
		om.setCharClass(model.getCharClass());
		om.setTwoHanded(model.getTwohanded());

		File itemsFile = new File("./itemsmage.csv");
		Collection<Item> items = ItemUtils.getItems(itemsFile);
		om.setItems(items);
		System.out.println(items.size());

		for (Iterator<Item> iterator = items.iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			item.setSelected(true);
		}

		Inventory inv = ItemUtils.parseInventoryFromItems(om.getItems());
		om.setInventory(inv);
		om.generateSnapshots();

		om.setTwoHanded(model.twohandedProperty().get());

		String str = model.offensiveGemsProperty().get();
		Modifier[] offGemMods = ItemUtils.parseModifiersFromString(str);
		om.setOffGems(offGemMods);

		str = model.defensiveGemsProperty().get();
		Modifier[] defGemMods = ItemUtils.parseModifiersFromString(str);
		om.setDefGems(defGemMods);

		str = model.petAndBuffsProperty().get();
		Modifier[] petAndBuffs = ItemUtils.parseModifiersFromString(str);
		om.setPetAndBuffs(petAndBuffs);

		Modifier attack = new Modifier();
		attack.setType(Modifier.Type.PDAMAGE);
		attack.setValue(model.attackProperty().get() * 2.0);
		attack.setAbsolute(false);
		om.setAttack(attack);

		Modifier agility = new Modifier();
		agility.setType(Modifier.Type.PATTACK_SPEED);
		agility.setValue(model.agilityProperty().get() * 1.6);
		agility.setAbsolute(false);
		om.setAgility(agility);

		Modifier[] weaponDmg = null;
		if (model.weaponDamageProperty().get()) {
			weaponDmg = new Modifier[2];
			weaponDmg[0] = new Modifier();
			weaponDmg[1] = new Modifier();
			weaponDmg[0].setType(Modifier.Type.PEXTRA_WEAPON_DMG);
			weaponDmg[0].setValue(50.0);
			weaponDmg[0].setAbsolute(false);
			weaponDmg[1].setType(Modifier.Type.PATTACK_SPEED);
			weaponDmg[1].setValue(-10.0);
			weaponDmg[1].setAbsolute(false);
		}
		om.setWeaponDmg(weaponDmg);

		Modifier[] rage = null;
		if (model.rageProperty().get()) {
			rage = new Modifier[2];
			rage[0] = new Modifier();
			rage[1] = new Modifier();
			rage[0].setType(Modifier.Type.PMANA);
			rage[0].setValue(25.0);
			rage[0].setAbsolute(false);

			rage[1].setType(Modifier.Type.PTRAVEL_SPEED);
			rage[1].setValue(-5.0);
			rage[1].setAbsolute(false);
		}
		om.setRage(rage);

		Modifier essence = new Modifier();
		essence.setType(Modifier.Type.PDAMAGE);
		essence.setAbsolute(false);
		Double v = 0.0;
		if (radioGreen.isSelected()) {
			v = 50.0;
			essence.setAbsolute(true);
			essence.setType(Modifier.Type.DAMAGE);
		} else if (radioBlue.isSelected()) {
			v = 100.0;
		} else if (radioPurple.isSelected()) {
			v = 200.0;
		} else if (radioRed.isSelected()) {
			v = 300.0;
		}
		essence.setValue(v);
		om.setEssence(essence);
		om.processItems();

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
	}

	private void initEssenceToggles() {
		this.groupEssence = new ToggleGroupValue<EssenceType>();
		for (EssenceType type: EssenceType.values()) {
			RadioButton r = new RadioButton(type.getMessage());
			r.setUserData(type);
			this.groupEssence.add(r, (EssenceType) r.getUserData());
			essencesContainer.getChildren().add(r);
		}
	}

}