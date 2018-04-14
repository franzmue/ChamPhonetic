package de.franzmue.NameEncoder;

import de.franzmue.NameEncoder.GermanNameEncoder;

import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class GermanNamesEncoderTest {

	final static String CODE_FUKS = "fuks";
	final static String CODE_KROS = "kros";
	final static String CODE_MILBAUR = "milbaur";
	final static String CODE_SMIT = "smit";
	final static String CODE_TITRIK = "titrik";

	private GermanNameEncoder encoder;
	
	private String word;
	private String expectedCode;

	@Before
	public void setUp() {
		this.encoder = new GermanNameEncoder();
	}

	public GermanNamesEncoderTest(String word, String expectedCode) {
		this.word = word;
		this.expectedCode = expectedCode;
	}
	
	@Parameters(name= "{index}: encode[{0}] -> {1}")
	public static Iterable<Object[]> encodeWords() {
		return Arrays.asList(new Object[][] {
			{"ph", "f"},
			{"er", "r"},
			{"erz", "ers"},
			{"kksssk", "ksk"},

			{"Dietrich", CODE_TITRIK},
			{"Dietrich", CODE_TITRIK},
			{"Dittrich", CODE_TITRIK},
			{"Diedrich", CODE_TITRIK},
			{"Diederich", CODE_TITRIK},
			{"Ddiederich", CODE_TITRIK},
			{"Diettrich", CODE_TITRIK},
			{"Dieterich", CODE_TITRIK},
			{"Ditterich", CODE_TITRIK},
			{"Dietterich", CODE_TITRIK},
			{"Diderich", CODE_TITRIK},
			{"Ditrich", CODE_TITRIK},
			{"Didrich", CODE_TITRIK},
			{"Dietherich", CODE_TITRIK},
			{"Diterich", CODE_TITRIK},
			{"Diethrich", CODE_TITRIK},
			{"Didtrich", CODE_TITRIK},
			{"Diddrich", CODE_TITRIK},
			{"Diederik", CODE_TITRIK},
			{"Dytrych", CODE_TITRIK},
			{"Ditrych", CODE_TITRIK},
			{"Dyttrych", CODE_TITRIK},
			{"Dietrick", CODE_TITRIK},
			{"Düderich", CODE_TITRIK},
			{"Tittrich", CODE_TITRIK},
			{"Tydrich", CODE_TITRIK},
			{"Tydrych", CODE_TITRIK},
			{"Diderrich", CODE_TITRIK},
			{"Didriche", CODE_TITRIK},
			{"Didrik", CODE_TITRIK},
			{"Dietriech", CODE_TITRIK},
			{"Dithrich", CODE_TITRIK},
			{"Dittriich", CODE_TITRIK},
			{"Dittrick", CODE_TITRIK},

			{"Fuchs", CODE_FUKS},
			{"Fucks", CODE_FUKS},
			{"Fuchß", CODE_FUKS},
			{"Fuks", CODE_FUKS},
			{"Fuchss", CODE_FUKS},
			{"Fuchssche", CODE_FUKS},
			{"Vugs", CODE_FUKS},
			{"Vugts", CODE_FUKS},

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

			{"MÜHLBAUER", CODE_MILBAUR},
			{"mühlbauer", CODE_MILBAUR},

			{"M\u00fchlbauer", CODE_MILBAUR},
			{"Mühlbauer", CODE_MILBAUR},
			{"Milpauer", CODE_MILBAUR},
			{"Müllbauer", CODE_MILBAUR},
			{"Milbauer", CODE_MILBAUR},
			{"Mühlbaur", CODE_MILBAUR},
			{"Mülbauer", CODE_MILBAUR},

			{"Schmidt", CODE_SMIT},
			{"Schmitt", CODE_SMIT},
			{"Schmid", CODE_SMIT},
			{"Schmied", CODE_SMIT},
			{"Schmiedt", CODE_SMIT},
			{"Schmit", CODE_SMIT},
			{"Schmith", CODE_SMIT},
			{"Szmidt", CODE_SMIT},
			{"Szmyt", CODE_SMIT},
			{"Smitt", CODE_SMIT},
			{"Szmit", CODE_SMIT},
			{"Smythe", CODE_SMIT},
			{"Szmid", CODE_SMIT},
			{"Szmyd", CODE_SMIT},
			{"Schmide", CODE_SMIT},
			{"Schmiet", CODE_SMIT},
			{"Schmitdt", CODE_SMIT},
			{"Schmitte", CODE_SMIT},
			{"Schmiede", CODE_SMIT},
			{"Smith", CODE_SMIT},
			{"Smit", CODE_SMIT},
			{"Smidt", CODE_SMIT},
			{"Smid", CODE_SMIT},
			{"Smyth", CODE_SMIT},
			{"Schmüth", CODE_SMIT},
			{"Czmid", CODE_SMIT},
			{"Schmidde", CODE_SMIT},
			{"Schmiddt", CODE_SMIT},
			{"Schmidte", CODE_SMIT},
			{"Schmidtt", CODE_SMIT},
			{"Schmiedte", CODE_SMIT},
			{"Schmittt", CODE_SMIT},
			{"Scmmid", CODE_SMIT},
			{"Shmidt", CODE_SMIT},
			{"Smiet", CODE_SMIT},
			{"Smyt", CODE_SMIT},
		});
	}
	
	@Test
	public void testEncodeWords() {
		assertEquals(expectedCode, encoder.encode(word).getCode());
	}

}
