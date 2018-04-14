# NameEncoder

The purpose of NameEncoder, a Java 9 library, is to find similar German family names by applying string matching and replacement.
Names are considered equal if the same encoding is calculated from their names.

## Usage

### Encode a name

```
import de.franzmue.NameEncoder.GermanNameEncoder;

GermanNameEncoder encoder = new GermanNameEncoder();

String word = "Müller";

String code = encoder.encode(word).getCode();
```

If thread safety is required use the synchronized method `getEncodedName` instead:

```
import de.franzmue.NameEncoder.GermanNameEncoder;

GermanNameEncoder encoder = new GermanNameEncoder();

String word = "Müller";

String code = encoder.getEncodedName(word);
```

To get more insights of the encoding process (i. e. intermediate code results) replace `getCode` by `getCodePath`:

```
import de.franzmue.NameEncoder.GermanNameEncoder;
import java.util.List;

GermanNameEncoder encoder = new GermanNameEncoder();

String word = "Müller";

List<String> codePath = encoder.encode(word).getCodePath();
```

It is also possible to encode a list of given names from command line (shell) by running the previously built jar file.

### Compare two names

```
import de.franzmue.NameEncoder.GermanNameEncoder;

GermanNameEncoder encoder = new GermanNameEncoder();

String word1 = "Müller";
String word2 = "Miller";

boolean areEqual = encoder.isEncodeEqual(word1, word2);
```

### Add your own rules or create your own encoder

See the [NameEncoder](src/main/java/de/franzmue/NameEncoder/NameEncoder.java) class documentation for more information.

## Background

### German language and family names

Considering the rich and fragmented history of the German language dialects in general and German family names in particular
it is not surprising that there is no small and unique set of simple rules to solve the problem.

Quite a deal of investigation has been done in the past years with limited success. It is not surprising that e. g. the application of
phonetic algorithms, which were created for the english language ([Soundex](https://en.wikipedia.org/wiki/Soundex),
[Metaphone](https://en.wikipedia.org/wiki/Metaphone) and the like), lead to poor results when applied to contemporary German names.
The results get even worse when applied to historical spelling variations.
There also seems no simple edit distance approach available to get the job done.

### My earlier work

Some years ago (2010/2011) I spent a lot of time on family name related problems. My work based on more than 830.000 German family names
which I extracted from the German white pages of the year 1997.

I took a sample of more than 1.300 German family names and analyzed their spellings. The analysis lead to an algorithm I formerly called
*Cham Phonetics*. Since strictly speaking it is not a pure phonetic algorithm I renamed it in the current GitHub repository to *German Name Encoder*.

I also analyzed 270 German family names with 1.300 historical spelling variations originating from an area close to the Czech border.
The results are covered in an extended algorithm I am calling *Extended German Name Encoder*.

These two algorithms I compared to the well known [Cologne Phonetics](https://en.wikipedia.org/wiki/Cologne_phonetics) as well as
[Phonet](https://github.com/jze/phonet4java) aka *Hannover Phonetics* (and some other algorithms).
The comparison included considering true negatives and false positives.
Though I would not call it an in-depth comparison, the results should at least point to the right direction.

My results showed that my *German Name Encoder* and *Phonet* lead to the best results dealing with the contemporary German family names. These two algorithms were far better that others including *Cologne Phonetics*, *Soundex*, *Metaphone*, and [Levenshtein](https://en.wikipedia.org/wiki/Levenshtein_distance).
However the results differed when applying the algorithms to historical name variations. Here I got the best results from *Cologne Phonetic* and my *Extended German Name Encoder*

The reason I remembered and re-implemented my old algorithms is simply that I was looking for an example project to learn the Java programming language...

## License

MIT

## Author

Franz Mühlbauer, Munich, Germany
