# Welcome to Drakensand Optimizer

## About the app

This app aims to analyze the items owned by the player and provide him with the best combination of the items to achieve maximum desired output. In the later release, there would be multiples queries supported, but as for now the application only try to maximeze the damage useing the following formulas:

<i>DamageIndex = MedianDmg * (1 - CriticalHitChance) + MedianDmg * CriticalHitChanse * CriticalDmg

  where 
MedianDmg = (MinDmg + MaxDmg) / 2</i>

## How To

The user has to provide the application with his item list in a excel CSV format. This format is quite simple, and the file could be edited in both excel and test mode. The example file named <b>itemsmage.csv</b> is provided with the application in a root folder. User has to complile his own version of that file, or edit one provided.

###The csv file 
It consists of a list with 6 columns.
- The first column reprents the Item Type (Amulet, Belt, Cloak, Ring, Crystal, Twohand, Helmet, Pauldrons, Torso, Gloves, Boots). <b>Books, 1handed weapons and orbs are not yet supported, do not add them.</b>
- columns 2nd through 5th represent the item modifiers e.g. <b>d:31</b> means item has a modifier with <i>damage = 31</i>
- final, 6th column represents the name of the item set. Three sets are preconfigured and ready to use. They are <i>dragan, witch, darkness</i> and so if you have dragan cloak, you should write the word 'dragan' in that cell. Those sets are pre-configured in the property file, which we will talk about below. You are able to add additional sets, or change names as you want, but that will require minor updates to the property file.

Each modifier (columns 2nd, 3rd, 4th and 5th) are represented by a pair of a Modifier Code and Value. Columns could be blank, like for instance with the Darkness Set Items. Here is the list of the Modifier Codes:
* <b>d</b> - Damage, use 'd' for rings, amulets etc. do not use it for weapon/2handed items
* <b>c</b> - Critical Hit - use everywhere, including weapons. The value could be both absolute and %-based.
* <b>cd</b> - Critical Damage - use anywhere. The value should always be % based
* <b>wd</b> - to be used in weapons/twohand only. Might be both absolute and relative
* <b>as</b> - attack speed. % based
* <b>ts</b> - travel speed. % based
* <b>dmax</b> - Maximum Damage, to be used in, for example, items from the Witch Chaser set (witch)
* <b>a</b> - armor. absolute or % based
* <b>r</b> - absolute or % based
* <b>h</b> - absolute or % based
* <b>bs</b> - block strength. % based. write 30%, not 0.30.
* <b>br</b> - block rate. absolute number
some of the modifiers are not yet implemented, especially those special ones like 'armor on this item', 'resistance to cold' etc. They are not required as the app does not get defensive modifiers into account yet, besides basic calculation. They will not impact the decisions of the app on what item combination is the best to maximize the damage.

Always double check the items file, as there is no validation in the application. Do not add more than 40 items. Ideally, stick to 35.

###The property file
It consists of a number of lines that represent the following:
* min and max base dmg of twohand weapon. Should be adjusted, if your main weapon has different base values, for example due to being upgraded to level 52 or being lower level
* base armor values for level 50 right side items. They do not matter for the calculations
* the commaseparated list of item sets. For example <i>dragan,witch,darkness</i> lists three sets only. Should you have any other item sets mentioned in the csv file, you need to update this field <b>(set.list)</b>
* the set modifiers written in the following format <i>set.bonus.[setname].[how many items]=[ModifierCode]:[value],[ModifierCode]:[value],[ModifierCode]:[value]</i> You would see one unique modifier code there - <b>WDE</b> - it is Extra Damage for Twohanded Weapons. In game, it works similar to the 50% Weapon Damage talent, and should be mentioned separately from D or WD.

Example line from the properties file:

set.bonus.dragan.3=m:30%,br:30%,wde:30% - represents the bonus modifiers for having 3 items.

### Using the App

When you start the app, you would usually follow the steps below:
1. Click on LoadItems button and specify the filename of your items file. You will see the message below if the file was loaded successfully.
2. Click on GenerateSnapshots button, and the application will create all possible item combinations and store them in the app folder in a file named <i>snapshots.sn</i>. This file might be pretty big, exceed 200 megabytes in case of large item list so stick to 35-37 max. If you have previously generate snapshots, you can open them by clicking LoadSnapshots button and pointing to <i>snapshots.sn</i> file
3. Now you are ready to play with the application. You can
3.1. select the Attack and Agility values using sliders
3.2. chose if you want to use 50% more weapon damage bonus
3.3. what essences to use (Green essence has a hardcoded value of d:50)
3.4. add gems in both gem fields, using similar format. for example:
wd:44,d:44,as:28%,as:2%,cd:10% - might mean two rubies (22) in the weapon, two rubies (22) in the crystal, two sapphires (14%) somewhere, also two Gems of Rage (1% attack speed, 5% of critical damage)
3.5. any other modifiers, including pet or bufs with the similar format.
3.6. Once you are ready - click Process button. The calculations might take some time, and you will see the progress below. Once the processing is complete, you will see the list of items tool selected, as well as character values.

You can re-process it again with another selection of talents or gems. Should application decide to replace an item becauses of the gem/talent change, such item would be marked with red font color for your convenience.

### You are ready to go
Play with demo item list first. Look at the results with different essences used. For instance,
1. put the following into OffGemsField:wd:142,as:67%,as:2%,cd:10%
2. Select Maximum possible Attack
3. Choose to use 50% Weapon Bonus
4. put c:5% pet into pet field.
5. process the demo items with Green essence seleceted and then with Blue/Purple.

Good luck
