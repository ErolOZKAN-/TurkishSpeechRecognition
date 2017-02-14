/**
 * Created by EROL-ASUS on 12/13/2015.
 */

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;

import java.io.IOException;

public class Forrest {
    public static void main(String[] args) throws IOException {
        Configuration configuration = new Configuration();

        configuration
                .setAcousticModelPath("OUTPUT/turkish.cd_cont_untied");
        configuration
                .setDictionaryPath("OUTPUT/turkish.dic");
        configuration.setLanguageModelPath("OUTPUT/turkish.lm");

        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
// Start recognition process pruning previously cached data.
        recognizer.startRecognition(true);
        SpeechResult result = recognizer.getResult();


        // Print utterance string without filler words.
        System.out.println(result.getHypothesis());

// Get individual words and their times.
        for (WordResult r : result.getWords()) {
            System.out.println(r);
        }

// Save lattice in a graphviz format.
        result.getLattice().dumpDot("lattice.dot", "lattice");

// Pause recognition process. It can be resumed then with startRecognition(false).
        recognizer.stopRecognition();
    }
}
