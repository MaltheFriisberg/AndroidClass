package SpilLogik;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.malthe.classexercises.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Malthe on 19/10/2016.
 */

public class GameLogic {

    private TextView textView;
    private ImageView imageView;
    private EditText editText;
    private int progress;
    private int rightGuess;
    private boolean secondTry;
    private List<String> words;
    private String word;
    public GameLogic(TextView textView, ImageView imageView) {
        this.textView = textView;
        this.imageView = imageView;

        words = new ArrayList<>();
        AddWords();
        word = PickWordRandom();
        AddWordToTextView();
    }

    public boolean play(String guess) {
        if(progress-rightGuess <= 6) {
            if(word.charAt(progress)!=guess.charAt(0) && !secondTry) {
                secondTry = true;
            } else if(word.charAt(progress)!=guess.charAt(0) && secondTry) {
                progress++;
                upDateGraphics();
                updateHint();
                secondTry = false;
            } else {
                progress++;
                rightGuess++;
                updateHint();
                //upDateGraphics();
                secondTry = false;
            }
            return true;
        }
        return false;
    }
    private void updateHint() {
        String result = new String(word.substring(0,this.progress));
        for(int i = this.progress; i <word.length(); i++) {
            result+="*";
        }
        textView.setText(result);
        //textView.append(result);
    }
    private void AddWords() {
        words.add("bilmotor");
        words.add("cykelanhænger");
        words.add("radiator");
        words.add("søgemaskine");
    }
    private String PickWordRandom() {
        Random rn =  new Random();
        int random = rn.nextInt(words.size());
        return words.get(random);
    }
    private void AddWordToTextView() {
        String result = new String();
        for(char c : word.toCharArray()) {
            result+="*";
        }
        this.textView.append(result);
    }

    private void upDateGraphics() {
        switch(progress-rightGuess) {
            case 0:
                imageView.setImageResource(R.drawable.galge);
                imageView.setVisibility(View.VISIBLE);
                break;
            case 1:
                imageView.setImageResource(R.drawable.forkert1);
                imageView.setVisibility(View.VISIBLE);
                break;
            case 2:
                imageView.setImageResource(R.drawable.forkert2);
                imageView.setVisibility(View.VISIBLE);
                break;
            case 3:
                imageView.setImageResource(R.drawable.forkert3);
                imageView.setVisibility(View.VISIBLE);
                break;
            case 4:
                imageView.setImageResource(R.drawable.forkert4);
                imageView.setVisibility(View.VISIBLE);
                break;
            case 5:
                imageView.setImageResource(R.drawable.forkert5);
                imageView.setVisibility(View.VISIBLE);
                break;
            case 6:
                imageView.setImageResource(R.drawable.forkert6);
                imageView.setVisibility(View.VISIBLE);
                break;

        }
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }
}
