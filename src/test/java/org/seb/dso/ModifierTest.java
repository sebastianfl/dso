package org.seb.dso;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.seb.dso.model.Modifier;

import junit.framework.TestCase;

public class ModifierTest extends TestCase {

	Collection<Modifier> list1 = new ArrayList<Modifier>();
	Collection<Modifier> list2 = new ArrayList<Modifier>();

	public static void main(String[] args) {
		com.google.caliper.Runner.main(args);
	}
	//
	// @Before
	// public void setUp() {
	// String str = "c:12,c:12%,d:23,d:25%,d:12,d:11,d:15%,a:100,a:12%";
	// String[] smods = str.split(",");
	// for (int i = 0; i < smods.length; i++) {
	// String modText = smods[i];
	//
	// String[] vals = modText.split(":");
	// Modifier mod = new Modifier();
	// mod.setType(vals[0]);
	//
	// if (vals[1].contains("%")) {
	// mod.setValue(Double.valueOf(vals[1].substring(0, vals[1].length() - 1)));
	// mod.setAbsolute(false);
	// } else {
	// mod.setValue(Double.valueOf(vals[1]));
	// }
	// list1.add(mod);
	// }
	// for (int i = 0; i < smods.length; i++) {
	// String modText = smods[i];
	//
	// String[] vals = modText.split(":");
	// Modifier mod = new Modifier();
	//
	// if (vals[1].contains("%")) {
	// mod.setType(vals[0] + "%");
	// mod.setValue(Double.valueOf(vals[1].substring(0, vals[1].length() - 1)));
	// } else {
	// mod.setType(vals[0]);
	// mod.setValue(Double.valueOf(vals[1]));
	// }
	// list2.add(mod);
	// }
	// System.out.println("called setup");
	// }
	//
	// // public void timeNanoTime(int reps) {
	// // for (int i = 0; i < reps; i++) {
	// // System.nanoTime();
	// // }
	// //
	// // }
	// @Test
	// public void testList1() {
	//
	// long time = System.currentTimeMillis();
	// double d = 0.0, a = 0.0, c = 0.0, h = 0.0;
	// double pd = 0.0, pa = 0.0, pc = 0.0, ph = 0.0;
	// for (int i = 0; i < 1000000; i++) {
	// for (Iterator iterator = list1.iterator(); iterator.hasNext();) {
	// Modifier modifier = (Modifier) iterator.next();
	// Double ds = modifier.getValue();
	// switch (modifier.getType()) {
	// case Mod.DAMAGE:
	// if (modifier.isAbsolute()) {
	// d += ds;
	// } else {
	// pd += ds;
	// }
	// break;
	// case Mod.ARMOR:
	// if (modifier.isAbsolute()) {
	// a += ds;
	// } else {
	// pa += ds;
	// }
	// break;
	// case Mod.HP:
	// if (modifier.isAbsolute()) {
	// h += ds;
	// } else {
	// ph += ds;
	// }
	// break;
	// case Mod.CRITICAL_HIT:
	// if (modifier.isAbsolute()) {
	// c += ds;
	// } else {
	// pc += ds;
	// }
	// break;
	//
	// }
	// }
	// }
	// long res = System.currentTimeMillis() - time;
	// System.out.println("" + res);
	//
	// time = System.currentTimeMillis();
	// d = 0.0;
	// a = 0.0;
	// c = 0.0;
	// h = 0.0;
	// pd = 0.0;
	// pa = 0.0;
	// pc = 0.0;
	// ph = 0.0;
	// for (int i = 0; i < 1000000; i++) {
	// for (Iterator iterator = list1.iterator(); iterator.hasNext();) {
	// Modifier modifier = (Modifier) iterator.next();
	// Double ds = modifier.getValue();
	// switch (modifier.getType()) {
	// case Mod.DAMAGE:
	// if (modifier.isAbsolute()) {
	// d += ds;
	// } else {
	// pd += ds;
	// }
	// break;
	// case Mod.ARMOR:
	// if (modifier.isAbsolute()) {
	// a += ds;
	// } else {
	// pa += ds;
	// }
	// break;
	// case Mod.HP:
	// if (modifier.isAbsolute()) {
	// h += ds;
	// } else {
	// ph += ds;
	// }
	// break;
	// case Mod.CRITICAL_HIT:
	// if (modifier.isAbsolute()) {
	// c += ds;
	// } else {
	// pc += ds;
	// }
	// break;
	//
	// }
	// }
	// }
	// res = System.currentTimeMillis() - time;
	// System.out.println("" + res);
	// }
	//
	// @Test
	// public void testList2() {
	//
	// long time = System.currentTimeMillis();
	// double d = 0.0, a = 0.0, c = 0.0, h = 0.0;
	// double pd = 0.0, pa = 0.0, pc = 0.0, ph = 0.0;
	// for (int i = 0; i < 1000000; i++) {
	// for (Iterator iterator = list1.iterator(); iterator.hasNext();) {
	// Modifier modifier = (Modifier) iterator.next();
	// Double ds = modifier.getValue();
	// switch (modifier.getType()) {
	// case Mod.DAMAGE: {
	// d += ds;
	// break;
	// }
	// case Mod.PDAMAGE: {
	// pd += ds;
	// break;
	// }
	// case Mod.ARMOR: {
	// a += ds;
	// break;
	// }
	// case Mod.PARMOR: {
	// pa += ds;
	// break;
	// }
	// case Mod.HP: {
	// h += ds;
	// break;
	// }
	// case Mod.PHP: {
	// ph += ds;
	// break;
	// }
	// case Mod.CRITICAL_HIT: {
	// c += ds;
	// break;
	// }
	// case Mod.PCRITICAL_HIT: {
	// pc += ds;
	// break;
	// }
	//
	// }
	// }
	// }
	// long res = System.currentTimeMillis() - time;
	// System.out.println("" + res);
	// }

	@Test
	public void testDummy() {
	assertTrue(true);
	}
}
