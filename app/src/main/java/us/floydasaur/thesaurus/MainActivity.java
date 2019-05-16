package us.floydasaur.thesaurus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String[][] synonyms = new String[][] {
            // each internal array is a list of synonymous words.
            {"swift",  "abrupt", "expeditious", "hasty", "nimble", "quick", "rapid", "speedy", "sudden", "unexpected"},
            {"objective", "detached", "disinterested", "dispassionate", "equitable", "evenhanded", "nonpartisan", "open-minded", "unbiased"},
            {"calculate", "adjust", "appraise", "consider", "count", "determine", "forecast", "gauge", "guess", "measure", "multiply", "reckon", "subtract", "tally", "weigh", "work out"},
            {"create", "build", "conceive", "constitute", "construct", "design", "devise", "discover", "establish", "forge", "form"},
            {"dog", "good boy/girl/nb", "puppers", "doggy", "doge"},
            {"cat", "kitty", "kit-kit", "neko", "Lord"}
    };

    EditText      editInput;
    Button        buttonShow;
    TextView      textResults;
    StringBuilder wordList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editInput   = findViewById(R.id.edit_input);
        buttonShow  = findViewById(R.id.button_show);
        textResults = findViewById(R.id.text_results);
        wordList    = new StringBuilder();

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordList.delete(0, wordList.length());

                // Check that there is something to check...
                if (!editInput.getText().toString().equals("")){
                    String query     = editInput.getText().toString();
                    String results[] = synonyms(query);

                    // Check we found synonyms at all
                    if (results != null){
                        for (String word : results) {
                            // Only add words that *aren't* the query to the list
                            if (word != query) {
                                wordList.append(word).append(", ");
                            }
                        }

                        // Remove the final ", " and update results.
                        wordList.delete(wordList.length() - 2, wordList.length());
                        textResults.setText(wordList.toString());
                    } else {
                        textResults.setText(R.string.results_none_found);
                    }
                } else {
                    textResults.setText(R.string.results_null_failure);
                }
            }
        });

    }

    /**
     * synonyms() takes a string query and returns an array of strings that are synonyms.
     * @param query is the word you would like synonyms for.
     * @return an array of strings or a null value
     */
    String[] synonyms(String query){
        for(int x = 0; x < synonyms.length; x++){
            for(int y = 0; y < synonyms[x].length; y++){
                if(query.toLowerCase().equals(synonyms[x][y].toLowerCase())){
                    return synonyms[x];
                }
            }
        }

        // If nothing is found:
        return null;
    }
}
