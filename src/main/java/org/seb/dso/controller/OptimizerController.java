package org.seb.dso.controller;

import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.TextField;

public class OptimizerController implements Initializable {

	private ResourceBundle bundle;

	@FXML
	private Button buttonLoadItems;
	@FXML
	private TextField textDefensiveGems;
	@FXML
	private TextField textOffensiveGems;
	@FXML
	private TextField textPetAndBuffsGems;
	@FXML
	Label labelSelectClass;
	@FXML
	ComboBox comboboxClass;
	@FXML
	CheckBox checkboxTwohanded;
	@FXML
	Button buttonGenerateSnapshots;
	@FXML
	Button buttonProcess;
	@FXML
	Label labelSnapshotsNumber;
	@FXML
	Slider sliderAttack;
	@FXML
	Slider sliderAgility;
	@FXML
	CheckBox checkboxWeaponDamage;
	@FXML
	CheckBox checkboxRage;
	@FXML
	RadioButton radioNoEssences;
	@FXML
	RadioButton radioGreen;
	@FXML
	RadioButton radioBlue;
	@FXML
	RadioButton radioPurple;
	@FXML
	RadioButton radioRed;
	@FXML
	TextField textPetAndBuffs;
	@FXML
	Label labelStatus;
	@FXML
	ProgressBar progressBar;

	@FXML
	protected void loadItems(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle res) {
		bundle = res;
	}

}