package de.franzmue.NameEncoder;

import de.franzmue.NameEncoder.ExtendedGermanNameEncoder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ExtendedGermanNameEncoderTest {
	
	final static int CODE_PATH_SIZE = 14; // Number of rule layers plus 1 (for the word to be encoded)

	final static String WORD_MUELLER = "M\u00fcller";
	final static String CODE_MIL = "mil";
	
	final static String WORD_MUEHLBAUER = "M\u00fchlbauer";
	final static String CODE_MILBAUR = "milbaur";

	private ExtendedGermanNameEncoder encoder;

	@Before
	public void setUp() {
		this.encoder = new ExtendedGermanNameEncoder();
	}

	@Test
	public void testGetEncodedNameMueller() {
		String word = WORD_MUELLER;
		String code = encoder.getEncodedName(word);

		String expectedCode = CODE_MIL;

		assertEquals(expectedCode, code);
	}

	@Test
	public void testEncodeGetCodeMueller() {
		String word = WORD_MUELLER;
		String code = encoder.encode(word).getCode();

		String expectedCode = CODE_MIL;

		assertEquals(expectedCode, code);
	}

	@Test
	public void testEncodeGetCodeMuehlbauer() {
		String word = WORD_MUEHLBAUER;
		String code = encoder.encode(word).getCode();

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
	public void testEncodeGetCodePathSequenceMuehlbauer() {
		String word = WORD_MUELLER;
		List<String> codePath = encoder.encode(word).getCodePath();

		assertEquals(WORD_MUELLER, codePath.get(0));
		assertEquals("miler", codePath.get(1));
		assertEquals("miler", codePath.get(2));
		assertEquals("miler", codePath.get(3));
		assertEquals("miler", codePath.get(4));
		assertEquals("miler", codePath.get(5));
		assertEquals("miler", codePath.get(6));
		assertEquals("miler", codePath.get(7));
		assertEquals("miler", codePath.get(8));
		assertEquals("milr", codePath.get(9));
		assertEquals("milr", codePath.get(10));
		assertEquals("milr", codePath.get(11));
		assertEquals(CODE_MIL, codePath.get(12));
		assertEquals(CODE_MIL, codePath.get(13));
	}

	@Test
	public void testMainOneArgument() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));

		String expectedOutput = String.format(Locale.GERMAN, ExtendedGermanNameEncoder.RESULT_MESSAGE, WORD_MUELLER, CODE_MIL);

    String[] args = { WORD_MUELLER };
    ExtendedGermanNameEncoder.main(args);
		
		assertEquals(expectedOutput, output.toString());
	}

}
