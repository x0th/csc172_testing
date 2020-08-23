import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/*
	Tests for Lab 1 of CSC172 Fall 2020
	Methods tested: Lab1.isAnagram(String, String) and Lab1.isRotation(String, String)

	Made by: Pavlo Pastaryev
	Email: ppastary@u.rochester.edu 
*/
public class Lab1Tester {
	Lab1 tester;

	@Before
	public void init() {
		tester = new Lab1();
	}

	@Test
	public void isAnagram_lettersCorrect() {
		assertEquals(true, tester.isAnagram("QweRty", "eRQwyt"));
	}

	@Test
	public void isAnagram_lettersIncorrect() {
		assertEquals(false, tester.isAnagram("QweRty", "QweRtY"));
	}

	@Test
	public void isAnagram_numbersCorrect() {
		assertEquals(true, tester.isAnagram("1234567890", "0213987654"));
	}

	@Test
	public void isAnagram_numbersIncorrect() {
		assertEquals(false, tester.isAnagram("0213986574", "0215556574"));
	}

	@Test
	public void isAnagram_mixedCorrect() {
		assertEquals(true, tester.isAnagram("qwe_123_omorw3", "3123_owrmoq_we"));
	}

	@Test
	public void isAnagram_mixedIncorrect() {
		assertEquals(false, tester.isAnagram("moasmf123$", "moasmf123%"));
	}

	@Test
	public void isAnagram_spacesCorrect() {
		assertEquals(true, tester.isAnagram("   9   2", "2      9"));
	}

	@Test
	public void isAnagram_sameString() {
		assertEquals(true, tester.isAnagram("^^^^&&123", "^^^^&&123"));
	}

	@Test
	public void isAnagram_differentLength() {
		assertEquals(false, tester.isAnagram("1111", "11111"));
	}

	@Test
	public void isRotation_correct() {
		assertEquals(true, tester.isRotation("qWeRtY", "YqWeRt"));
	}

	@Test
	public void isRotation_incorrect() {
		assertEquals(false, tester.isRotation("123yrewq", "yreqw123"));
	}

	@Test
	public void isRotation_spacesCorrect() {
		assertEquals(true, tester.isRotation("0  1  2", "1  20  "));
	}

	@Test
	public void isRotation_mixedCorrect() {
		assertEquals(true, tester.isRotation("$05102kfasn", "02kfasn$051"));
	}

	@Test
	public void isRotation_mixedIncorrect() {
		assertEquals(false, tester.isRotation("qwei124n$12n(", "(123$n4qwei12"));
	}

	@Test
	public void isRotation_sameString() {
		assertEquals(true, tester.isRotation("^^^^$$123", "^^^^$$123"));
	}

	@Test
	public void isRotation_differentLength() {
		assertEquals(false, tester.isRotation("11111", "1111"));
	}

	@Test
	public void isRotation_hugeStrings() {
		assertEquals(true, tester.isRotation("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Diam ut venenatis tellus in metus vulputate eu scelerisque felis. Eu consequat ac felis donec et odio pellentesque. Vel fringilla est ullamcorper eget nulla facilisi etiam dignissim diam. Neque sodales ut etiam sit amet nisl. Et leo duis ut diam. Viverra maecenas accumsan lacus vel facilisis. Ornare quam viverra orci sagittis eu volutpat. Varius duis at consectetur lorem donec massa. Urna et pharetra pharetra massa. Diam ut venenatis tellus in metus vulputate eu scelerisque felis. Proin nibh nisl condimentum id. Lorem mollis aliquam ut porttitor. Dui id ornare arcu odio ut sem nulla pharetra diam. Urna neque viverra justo nec ultrices dui sapien eget mi. Donec ultrices tincidunt arcu non sodales neque sodales. Eget nunc scelerisque viverra mauris in aliquam. Enim diam vulputate ut pharetra sit.",
											 "orem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Diam ut venenatis tellus in metus vulputate eu scelerisque felis. Eu consequat ac felis donec et odio pellentesque. Vel fringilla est ullamcorper eget nulla facilisi etiam dignissim diam. Neque sodales ut etiam sit amet nisl. Et leo duis ut diam. Viverra maecenas accumsan lacus vel facilisis. Ornare quam viverra orci sagittis eu volutpat. Varius duis at consectetur lorem donec massa. Urna et pharetra pharetra massa. Diam ut venenatis tellus in metus vulputate eu scelerisque felis. Proin nibh nisl condimentum id. Lorem mollis aliquam ut porttitor. Dui id ornare arcu odio ut sem nulla pharetra diam. Urna neque viverra justo nec ultrices dui sapien eget mi. Donec ultrices tincidunt arcu non sodales neque sodales. Eget nunc scelerisque viverra mauris in aliquam. Enim diam vulputate ut pharetra sit.L"));
	}
}