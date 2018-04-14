package de.franzmue.NameEncoder;

import de.franzmue.NameEncoder.NameEncoder;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class NameEncoderTest {
	
	private NameEncoder encoder;

	@Before
	public void setUp() {
		this.encoder = new NameEncoder();
	}

	@Test( expected = RuntimeException.class )
	public void testGetInitialCode() {
		encoder.getCode();
	}

	@Test( expected = RuntimeException.class )
	public void testGetInitialCodePath() {
		encoder.getCodePath();
	}

	@Test
	public void testEncodeWithoutRules() {
		String word = "Kind";
		String code = encoder.encode(word).getCode();

		assertEquals(word, code);
	}

	@Test
	public void testEncodeWithoutRulesWithWhitespaces() {
		String word = "Von der Kindt";
		String code = encoder.encode(word).getCode();

		assertEquals(word, code);
	}

	@Test
	public void testGetEncodedNameWithoutRules() {
		String word = "Kind";
		String code1 = encoder.encode(word).getCode();
		String code2 = encoder.getEncodedName(word);

		assertEquals(code1, code2);
	}

	@Test
	public void testGetCodePathWithoutRules() {
		String word = "Von der Kindt";
		List<String> codePath = encoder.encode(word).getCodePath();

		assertEquals(word, codePath.get(0));
	}

	@Test
	public void testIsEncodeEqualWithoutRulesWithWhitespaces() {
		String leftWord = "Kind";
		String rightWord = " Kind ";

		assertFalse(encoder.isEncodeEqual(leftWord, rightWord));
	}

	@Test
	public void testMainNoArgument() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));

		String expectedOutput = String.format(Locale.GERMAN, NameEncoder.USAGE_MESSAGE);

    String[] args = { };
    NameEncoder.main(args);
		
		assertEquals(expectedOutput, output.toString());
	}

}
