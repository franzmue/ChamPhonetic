package de.franzmue.nameencoder;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * A base class to describe a rule replacing a part of a string by another string.
 * 
 * @author Franz Muehlbauer, info@franz-mue.de
 */
public abstract class Rule {
	
	protected final String source;
	protected final String destination;

	public Rule(String source, String destination) {
		this.source = Objects.requireNonNullElse(source, "");
		this.destination = Objects.requireNonNullElse(destination, "");
	}
	
	public String getSource() {
		throw new UnsupportedOperationException();
	}
	
	public String getRegex() {
		throw new UnsupportedOperationException();
	}

	public Pattern getPattern() {
		throw new UnsupportedOperationException();
	}

	public String getDestination() {
		return destination;
	}

}
