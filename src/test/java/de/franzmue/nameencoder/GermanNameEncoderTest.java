package de.franzmue.nameencoder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.franzmue.nameencoder.GermanNameEncoder;

public class GermanNameEncoderTest {
	
	final static int CODE_PATH_SIZE = 12; // Number of rule layers plus 1 (for the word to be encoded)
	
	final static String SPACE = " ";
	final static String ARBITRARY_CODE = "abc";

	final static String WORD_MUELLER = "M\u00fcller";
	final static String CODE_MILR = "milr";
	
	final static String WORD_MUEHLBAUER = "M\u00fchlbauer";
	final static String CODE_MILBAUR = "milbaur";
	
	final static String WORD_DIETRICH = "Dietrich";
	final static String CODE_TITRIK = "titrik";

	private GermanNameEncoder encoder;

	@Before
	public void setUp() {
		this.encoder = new GermanNameEncoder();
	}

	@Test
	public void testGetEncodedNameMueller() {
		String word = WORD_MUELLER;
		String code = encoder.getEncodedName(word);

		String expectedCode = CODE_MILR;

		assertEquals(expectedCode, code);
	}

	@Test
	public void testEncodeGetCodeMueller() {
		String word = WORD_MUELLER;
		String code = encoder.encode(word).getCode();

		String expectedCode = CODE_MILR;

		assertEquals(expectedCode, code);
	}

	@Test
	public void testEncodeGetCodeDietrich() {
		String word = WORD_DIETRICH;
		String code = encoder.encode(word).getCode();

		String expectedCode = CODE_TITRIK;

		assertEquals(expectedCode, code);
	}

	@Test
	public void testEncodeLeaveDigitsUnchanged() {
		String number = "123";
		String code = encoder.encode(number).getCode();

		String expectedCode = number;

		assertEquals(expectedCode, code);
	}

	@Test
	public void testEncodeLeaveSpecialCharsUnchanged() {
		String specialChars = "+-#*.,<>";
		String code = encoder.encode(specialChars).getCode();

		String expectedCode = specialChars;

		assertEquals(expectedCode, code);
	}

	@Test
	public void testEncodeTwice() {
		String firstWord = WORD_MUELLER;
		String secondWord = WORD_MUEHLBAUER;
		String code = encoder.encode(firstWord).encode(secondWord).getCode();
		String expectedCode = CODE_MILBAUR;

		assertEquals(expectedCode, code);
	}

	@Test
	public void testEncodeGetCodePathSize() {
		String someWord = WORD_MUELLER;
		List<String> codePath = encoder.encode(someWord).getCodePath();

		assertEquals(CODE_PATH_SIZE, codePath.size());
	}

	@Test
	public void testEncodeGetCodePath() {
		String word = WORD_MUELLER;
		List<String> codePath = encoder.encode(word).getCodePath();

		String expectedCode = CODE_MILR;

		assertEquals(expectedCode, codePath.get(codePath.size() - 1));
	}

	@Test
	public void testEncodeGetCodePathSequenceMuehlbauer() {
		String word = WORD_MUEHLBAUER;
		List<String> codePath = encoder.encode(word).getCodePath();

		assertEquals(WORD_MUEHLBAUER, codePath.get(0));
		assertEquals("mihlbauer", codePath.get(1));
		assertEquals("mihlbauer", codePath.get(2));
		assertEquals("mihlbauer", codePath.get(3));
		assertEquals("mihlbauer", codePath.get(4));
		assertEquals("mihlbauer", codePath.get(5));
		assertEquals("mihlbauer", codePath.get(6));
		assertEquals("milbauer", codePath.get(7));
		assertEquals("milbauer", codePath.get(8));
		assertEquals("milbaur", codePath.get(9));
		assertEquals("milbaur", codePath.get(10));
		assertEquals(CODE_MILBAUR, codePath.get(11));
	}

	@Test
	public void testEncodeGetCodePathSequenceDietrich() {
		String word = WORD_DIETRICH;
		List<String> codePath = encoder.encode(word).getCodePath();

		assertEquals(WORD_DIETRICH, codePath.get(0));
		assertEquals("dietrich", codePath.get(1));
		assertEquals("tietrich", codePath.get(2));
		assertEquals("tietrik", codePath.get(3));
		assertEquals("tietrik", codePath.get(4));
		assertEquals("tietrik", codePath.get(5));
		assertEquals("tietrik", codePath.get(6));
		assertEquals("tietrik", codePath.get(7));
		assertEquals(CODE_TITRIK, codePath.get(8));
		assertEquals(CODE_TITRIK, codePath.get(9));
		assertEquals(CODE_TITRIK, codePath.get(10));
		assertEquals(CODE_TITRIK, codePath.get(11));
	}

	@Test( expected = UnsupportedOperationException.class )
	public void testEncodeGetImmutableCodePath() {
		String word = WORD_MUELLER;
		encoder.encode(word);

		List<String> codePath1 = encoder.getCodePath();
		codePath1.get(codePath1.size() - 1);
		
		codePath1.add(ARBITRARY_CODE);
	}

	@Test
	public void testEncodeGetCodePathSuffix() {
		String word = WORD_MUELLER;
		encoder.encode(word);
		String code = encoder.getCode();
		List<String> codePath = encoder.getCodePath();

		assertEquals(code, codePath.get(codePath.size() - 1));
	}

	@Test
	public void testIsEncodeEqualSameWords() {
		String leftWord = WORD_MUELLER;
		String rightWord = leftWord;

		assertTrue(encoder.isEncodeEqual(leftWord, rightWord));
	}

	@Test
	public void testIsEncodeEqualUpperLowerCase() {
		String leftWord = WORD_MUELLER;
		String rightWord = leftWord.toUpperCase(Locale.GERMAN);

		assertTrue(encoder.isEncodeEqual(leftWord, rightWord));
	}

	@Test
	public void testIsEncodeEqualWithWhitespaces() {
		String leftWord = WORD_MUELLER;
		String rightWord = SPACE + leftWord + SPACE;

		assertTrue(encoder.isEncodeEqual(leftWord, rightWord));
	}

	@Test
	public void testIsEncodeEqualWithInnerWhitespaces() {
		String leftWord = WORD_MUELLER;
		String rightWord = "M\u00fc  ller";

		assertTrue(encoder.isEncodeEqual(leftWord, rightWord));
	}

	@Test
	public void testMainOneArgument() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));

		String expectedOutput = String.format(Locale.GERMAN, GermanNameEncoder.RESULT_MESSAGE, WORD_MUELLER, CODE_MILR);

    String[] args = { WORD_MUELLER };
    GermanNameEncoder.main(args);
		
		assertEquals(expectedOutput, output.toString());
	}

	@Test
	public void testMainTwoArguments() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));

    String expectedOutput = String.format(
      Locale.GERMAN, GermanNameEncoder.RESULT_MESSAGE + GermanNameEncoder.RESULT_MESSAGE,
      WORD_MUELLER, CODE_MILR,
      WORD_MUEHLBAUER, CODE_MILBAUR
    );

    String[] args = { WORD_MUELLER, WORD_MUEHLBAUER};
    GermanNameEncoder.main(args);
		
		assertEquals(expectedOutput, output.toString());
	}

}
