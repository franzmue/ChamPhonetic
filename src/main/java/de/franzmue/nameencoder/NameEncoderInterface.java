package de.franzmue.nameencoder;

import java.util.List;

import de.franzmue.nameencoder.NameEncoder;

/**
 * Defines how the NameEncoder Java 9 library can be used.
 * 
 * @author Franz Muehlbauer, info@franz-mue.de
 */
public interface NameEncoderInterface {
	
  /**
   * Checks the similarity of two given names by comparing the codes of the encoded names.
   *
   * @param word1 The first name to compare
   * @param word2 The second name to compare
   * @return Are the compared names similar (i. e. does the encoding of the names lead to identical codes)?
   */
	public boolean isEncodeEqual(final String word1, final String word2);
	
  /**
   * Encodes a name and returns the resulting code.
   * 
   * The method combines the {@link #encode} and the {@link #getCode} methods and embeds them in a synchronized section.
   *
   * @param word The name to encode
   * @return The code of a name..
   */
	public String getEncodedName(String word);
	
  /**
   * Encodes a name.
   * 
   * The next step after encoding is to get the code; see the {@link #getCode} method.
   *
   * @param word The name to encode.
   * @return The NameEncoder object itself in order to provide a fluent interface.
   */
	public NameEncoder encode(String word);
	
  /**
   * Returns the code of a name.
   * 
   * The encoding has to be done before; see the {@link #encode} method.
   *
   * @return The code of a name.
   */
	public String getCode();
	
  /**
   * Returns the code path of a name.
   * <p>
   * The method provides some further details on the steps of the encoding process. The purpose of this
   * method is to get additional insights if the method {@link #getCode} leads to
   * surprising results.
   * </p><p>
   * The first path element is the given name. After each processed rule layer the resulting code
   * ist added to the code path. The last path element is the final code which can immediately being
   * accessed by calling {@link #getCode}.
   * </p><p>
   * The encoding has to be done before; see the {@link #encode} method.
   * </p>
   *
   * @return A list with the code path elements of a name.
   */
	public List<String> getCodePath();
}
