package SpilLogik;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.malthe.classexercises.R;

import java.util.ArrayList;
import java.util.Random;

import static android.R.attr.name;

/**
 * Created by Malthe on 19/10/2016.
 */

public class GalgeLogik {
    ImageView imageView;
    TextView textView;
    private ArrayList<String> words;
    private String word;
    private int lettersGuessed;
    private int guessesMade;
    private int progress;
    private boolean secondTry;

    public GalgeLogik(ImageView imageView, TextView textView) {
        this.imageView = imageView;
        this.textView = textView;
        textView.setText("");
        imageView.setVisibility(View.INVISIBLE);
        this.words = new ArrayList<>();
        AddWords();
        String result = PickWordRandom();
        this.word = PickWordRandom();

        AddWordToTextView();


    }
    public boolean play(String guess) {
        if(progress <= 6) {
            if(word.charAt(progress)!=guess.charAt(0) && !secondTry) {
                secondTry = true;
            } else if(word.charAt(progress)!=guess.charAt(0) && secondTry) {
                progress++;
                upDateGraphics();
                updateHint();
                secondTry = false;
            } else {
                progress++;
                updateHint();
                //upDateGraphics();
                secondTry = false;
            }
            return true;
        }
        return false;
    }

    private void upDateGraphics() {
        switch(progress) {
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

    private void updateHint() {
        String result = new String(word.substring(0,this.progress));
        for(int i = this.progress; i <word.length(); i++) {
            result+="*";
        }
        textView.setText(result);
        //textView.append(result);
    }

    private void AddWordToTextView() {
        String result = new String();
        for(char c : word.toCharArray()) {
            result+="*";
        }
        this.textView.append(result);
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
}
