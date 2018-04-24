package de.franzmue.nameencoder;

import java.util.Locale;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.function.Supplier;

import de.franzmue.nameencoder.NameEncoderInterface;
import de.franzmue.nameencoder.RegexRule;
import de.franzmue.nameencoder.Rule;
import de.franzmue.nameencoder.Rules;

/**
 * The NameEncoder class contains all methods to do the encoding of a word of data type String.
 * However the rules of a concrete encoding algorithm are not part of this class.
 * <p>
 * The intended use is to create a subclass of the NameEncoder class and add the rules in the subclass.
 * Examples are the {@link de.franzmue.nameencoder.GermanNameEncoder} and
 * {@link de.franzmue.nameencoder.ExtendedGermanNameEncoder} classes. The encoding is then done
 * by calling the appropriate methods of a subclass object.
 * </p><p>
 * The rules are organised in layers. The ordering of the given layers are preserved.
 * Each layer in turn contains an ordered list of rules.
 * </p><p>
 * Adding a rule requires the availability of a layer. So at least one layer has to be added befpre startomg adding rules.
 * Either add a layer and the rules belonging to that layer in one step ({@link #addReplacementRuleLayer(String[][])} or
 * {@link #addRegexRuleLayer(String[][])}) or be more flexible by first adding a rule layer ({@link #addRuleLayer()})
 * and then adding rules ({@link #addReplacementRule(String, String)} or {@link #addRegexRule(String, String)}).
 * </p><p>
 * Encoding means taking the name as a start code, stepping through the subsequent layers and
 * applying the rules to the code. The resulting code after applying a rule is taken as input code to the next rule.
 * The resulting code after a layer is processed is taken as an input code to the next layer.
 * </p><p>
 * The current implementation removes the whitespaces from the given word and converts the word to lower case before starting the encoding.
 * After processing a rule layer duplicate consecutive characters are removed from the intermediate code.
 * </p><p>
 * In order to customize the processing of the code overwrite the {@link #prepareProcessing},
 * {@link #preprocessCode} or {@link #postprocessCode} methods in a subclass.
 * </p><p>
 * Each rule either replaces the occurrences of a substring or a regular expression matching by another string.
 * <p>
 * 
 * @author Franz Muehlbauer, info@franz-mue.de
 */
public class NameEncoder implements NameEncoderInterface {
	
	final static String USAGE_MESSAGE = "A list of given names is encoded to their codes.%n";
	final static String RESULT_MESSAGE = "Encoding of %s: %s%n";

	private final static String CODE_ERROR_MESSAGE = "Code is available after encoding!\n";
	private final static String RULE_ERROR_MESSAGE = "Rules can be added after adding a rule layer!\n";
	private final static String INVALID_RULE_TYPE_MESSAGE = "Implementation error: The rule type is not existing!\n";
	
	protected List<Rules> rulesLayers = new ArrayList<>();
	protected List<String> codePath;
	
	protected static Supplier<NameEncoder> encoderSupplier = () -> new NameEncoder();

	public static void main(String[] args) {
		createCodes(args, encoderSupplier);
	}

	protected static void createCodes(String[] args, Supplier<NameEncoder> encoderSupplier) {
		if (args.length == 0) {
			System.out.printf(USAGE_MESSAGE);
		} else {
			NameEncoder encoder = encoderSupplier.get();
			
			for(String word: args) {
				String code = encoder.encode(word).getCode();
				
				System.out.printf(RESULT_MESSAGE, word, code);
			}
		}
	}

  /**
   * @see de.franzmue.nameencoder.NameEncoderInterface#isEncodeEqual(String, String)
   */
  public boolean isEncodeEqual(final String word1, final String word2) {
  	String code1 = encode(word1).getCode();
  	String code2 = encode(word2).getCode();

  	return code1.equals(code2);
  }
  
  /**
   * @see de.franzmue.nameencoder.NameEncoderInterface#getEncodedName(String)
   */
  public synchronized String getEncodedName(String word) {
  	return encode(word).getCode();
  }

  /**
   * @see de.franzmue.nameencoder.NameEncoderInterface#encode(String)
   */
	public NameEncoder encode(String word) {
		resetCodePath(word);
		applyRules(word);
		return this;
	}
	
	private void resetCodePath(String startCode) {
		codePath = new ArrayList<>(rulesLayers.size() + 1);
		codePath.add(startCode); // including the word to be encoded
	}
	
  /**
   * @see de.franzmue.nameencoder.NameEncoderInterface#getCode()
   */
	public String getCode() {
		if (codePath == null)
			throw new RuntimeException(CODE_ERROR_MESSAGE);

		return codePath.get(codePath.size() - 1);
	}
	
  /**
   * @see de.franzmue.nameencoder.NameEncoderInterface#getCodePath()
   */
	public List<String> getCodePath() {
		if (codePath == null)
			throw new RuntimeException(CODE_ERROR_MESSAGE);

		return Collections.unmodifiableList(codePath);
	}
	
	private void applyRules(String word) {
		String currentCode = prepareProcessing(word);

		for (Rules rules: rulesLayers) {
			preprocessCode(currentCode);
			
			for (Rule rule: rules) {
				currentCode = applyRule(currentCode, rule);
			}

			currentCode = postprocessCode(currentCode);
		}
	}
	
	protected String prepareProcessing(String word) {
		return removeWhitespaces(word).toLowerCase(Locale.GERMAN);
	}
	
	private String removeWhitespaces(String word) {
		return word.replaceAll("\\s+", "");
	}

	protected String preprocessCode(String code) { // Currently a dummy; providing more flexibility for derived classes
		return code;
	}

	private String applyRule(String currentCode, Rule rule) {
		if (currentCode.length() == 0)
			return currentCode;
		
		if (rule instanceof RegexRule)
			return rule.getPattern().matcher(currentCode).replaceAll(rule.getDestination());

		if (rule instanceof ReplacementRule)
			return currentCode.replace(rule.getSource(), rule.getDestination());

		throw new RuntimeException(INVALID_RULE_TYPE_MESSAGE);
	}

	protected String postprocessCode(String newCode) {
		String code = removeConsecutiveDuplicates(newCode);

		codePath.add(code);
		
		return code;
	}

	private String removeConsecutiveDuplicates(String code) {
		if (code.length() == 0)
			return code;

		char[] input = code.toCharArray();
		
		StringBuilder output = new StringBuilder().append(input[0]);
		
		for (int index = 1; index < input.length; index++) {
			if (input[index - 1] != input[index]) {
				output.append(input[index]);
			}
		}

		return output.toString();
	}

 	protected NameEncoder addReplacementRuleLayer(String[][] rules) {
		addRuleLayer();
		addReplacementRules(rules);
		
		return this;
	}

	private void addReplacementRules(String[][] rules) {
		for (int index = 0; index < rules.length; index++) {
			if (Objects.nonNull(rules[index][0]) && Objects.nonNull(rules[index][1])) {
				addReplacementRule(rules[index][0], rules[index][1]);
			}
		}
	}

	protected NameEncoder addReplacementRule(String source, String destination) {
		if (rulesLayers == null)
			throw new RuntimeException(RULE_ERROR_MESSAGE);

		getCurrentRulesLayer().addReplacementRule(source, destination);

		return this;
	}
	
	protected NameEncoder addRegexRuleLayer(String[][] rules) {
		addRuleLayer();
		addRegexRules(rules);
		
		return this;
	}
	
	private void addRegexRules(String[][] rules) {
		for (int index = 0; index < rules.length; index++) {
			if (Objects.nonNull(rules[index][0]) && Objects.nonNull(rules[index][1])) {
				addRegexRule(rules[index][0], rules[index][1]);
			}
		}
	}

	protected NameEncoder addRegexRule(String regex, String replacement) {
		if (rulesLayers == null)
			throw new RuntimeException(RULE_ERROR_MESSAGE);

		getCurrentRulesLayer().addRegexRule(regex, replacement);
		
		return this;
	}

	protected NameEncoder addRuleLayer() {
		Rules rulesLayer = new Rules();
		rulesLayers.add(rulesLayer);
		return this;
	}

	private Rules getCurrentRulesLayer() {
		return rulesLayers.get(rulesLayers.size() - 1);
	}

}
