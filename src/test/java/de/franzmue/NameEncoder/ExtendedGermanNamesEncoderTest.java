package de.franzmue.NameEncoder;

import de.franzmue.NameEncoder.ExtendedGermanNameEncoder;

import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ExtendedGermanNamesEncoderTest {

	final static String CODE_BUKS = "buks";
	final static String CODE_KROS = "kros";
	final static String CODE_MILBAUR = "milbaur";
	final static String CODE_MIL = "mil";
	final static String CODE_TITRK = "titrk";

	private ExtendedGermanNameEncoder encoder;
	
	private String word;
	private String expectedCode;

	@Before
	public void setUp() {
		this.encoder = new ExtendedGermanNameEncoder();
	}

	public ExtendedGermanNamesEncoderTest(String word, String expectedCode) {
		this.word = word;
		this.expectedCode = expectedCode;
	}

	@Parameters(name= "{index}: encode[{0}] -> {1}")
	public static Iterable<Object[]> encodeWords() {
		return Arrays.asList(new Object[][] {
			{"ph", "f"},
			{"tim", "sim"},

			{"Dietrich", CODE_TITRK},

			{"Fuchs", CODE_BUKS},
			{"Fucks", CODE_BUKS},
			{"Fuchß", CODE_BUKS},
			{"Fuks", CODE_BUKS},
			{"Fuchss", CODE_BUKS},
			{"Fuchssche", CODE_BUKS},
			{"Vugs", CODE_BUKS},
			{"Vugts", CODE_BUKS},

			{"Gro\u00df", CODE_KROS},
			{"Groß", CODE_KROS},
			{"Gross", CODE_KROS},
			{"Gros", CODE_KROS},
			{"Groos", CODE_KROS},
			{"Kroos", CODE_KROS},
			{"Kroß", CODE_KROS},
			{"Grohs", CODE_KROS},
			{"Krohß", CODE_KROS},
			{"Große", CODE_KROS},
			{"Grosch", CODE_KROS},
			{"Grosse", CODE_KROS},
			{"Grotz", CODE_KROS},
			{"Grosche", CODE_KROS},
			{"Grosche", CODE_KROS},
			{"Grosche", CODE_KROS},
			{"Grosche", CODE_KROS},
			{"Grooss", CODE_KROS},
			{"Krocz", CODE_KROS},
			{"Kroess", CODE_KROS},
			{"Krosz", CODE_KROS},

			{"M\u00fcller", CODE_MIL},
			{"Müller", CODE_MIL},

			{"M\u00fchlbauer", CODE_MILBAUR},
			{"Mühlbauer", CODE_MILBAUR},
			{"Milpauer", CODE_MILBAUR},
		});
	}
	
	@Test
	public void testEncodeWords() {
		assertEquals(expectedCode, encoder.encode(word).getCode());
	}

}
