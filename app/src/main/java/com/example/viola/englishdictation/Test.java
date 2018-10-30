package com.example.viola.englishdictation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import android.speech.tts.TextToSpeech;

public class Test extends AppCompatActivity {
    String wordssum;
    int wordsum;
    int countnum = 1;
    int metritis=0;
    String result1;
    int arrayposition;
    int arrayposition2;
    String chapter = "1";
    TextToSpeech t1;
    int counter=1;
    int counter2=0;
    String urlUpdate;
    String result;
    String testcoo;
    int UserID;
    String[] mistarray = {"1000"};
    String[] mistarray1 = {"1001"};
    String[] mistarray2 = {"1002"};
    String[] mistarray3 = {"1003"};
    String[] mistarray4 = {"1004"};
    String[] mistarray5 = {"1005"};
    String[] mistarray6 = {"1006"};
    String[] mistarray7 = {"1007"};
    String[] englishanswears;
    String[] greekanswears;
    Boolean[] correctanswears;

    public static final String PREFS_NAME = "MyPrefsFile";
    private static final String PREF_USERID = "UserID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        testcoo = pref.getString(PREF_USERID,"UserID");
        if (testcoo!=null&&!testcoo.equals("NoUser")&&!testcoo.equals("No Connected Service Found")&&!testcoo.equals("")) {
            UserID=Integer.parseInt(testcoo);
            if (UserID>0){
                Toast toast = Toast.makeText(getApplicationContext(), "it's in",Toast.LENGTH_LONG);
                toast.show();
                setContentView(R.layout.test);
                try {
                    wordssum = new AsyncTaskSum().execute().get();
                    TextView wordnumber = findViewById(R.id.wordnumber);
                    wordnumber.setText("1/"+wordssum);
                    wordsum = Integer.parseInt(wordssum);
                    new AsyncTaskShow().execute();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            Intent intent1 = new Intent(this, Login.class);
            startActivity(intent1);
        }
    }


    class AsyncTaskSum extends AsyncTask<String, Void, String> {

        String result;

        public AsyncTaskSum() {
            // TODO Auto-generated constructor stub
        }

        protected void onPreExecute() {
            //display progress dialog.
        }
        @Override
        protected String doInBackground(String... urls) {
            URL url;
            try {
                url = new URL("http://u779583388.hostingerapp.com/EnglishDictation/showwordssum.php");
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            HttpURLConnection conn;
            try {
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "1002";
            }
            if(conn==null){
                return "1003";}
            conn.setAllowUserInteraction(false);
            try {
                conn.connect();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            //  conn.setDoOutput(true);
            InputStream in;
            try{
                in = conn.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }
            BufferedInputStream bis = new BufferedInputStream(in);
            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                br = new BufferedReader(new InputStreamReader(in));
                while ((line=br.readLine()) != null) {
                    sb.append(line);
                }
                result=sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return "1006";
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "1007";
                    }
                }
            }

            conn.disconnect();
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            if (result.equals("NoUser")||result.equals("1001")||result.equals("1002")||result.equals("1003")||result.equals("1004")||result.equals("1005")||result.equals("1006")||result.equals("1007")||result.equals("0")){
                //Toast toast = Toast.makeText(Test.CONTEXT_INCLUDE_CODE, "Ooooopss"+result,Toast.LENGTH_LONG);
                //toast.show();
                Log.i("UserIDLogin", result);
                //Intent intent = new Intent(context, LoginScreen.class);
                //startActivity(intent);
            }
            else {
            }
        }

    }


    class AsyncTaskShow extends AsyncTask<String, Void, String[]> {

        String urlstring;
        public AsyncTaskShow() {
            // TODO Auto-generated constructor stub
        }

        protected void onPreExecute() {
            //display progress dialog.
        }
        @Override
        protected String[] doInBackground(String... urls) {
            URL url;
            try {
                url = new URL("http://u779583388.hostingerapp.com/EnglishDictation/showwords.php?chapter="+chapter+"");
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return mistarray;
            }
            HttpURLConnection conn;
            try {
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return mistarray1;
            }
            if(conn==null){
                return mistarray2;}
            conn.setAllowUserInteraction(false);
            try {
                conn.connect();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return mistarray3;
            }
            InputStream in;
            try{
                in = conn.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
                return mistarray4;
            }
            BufferedInputStream bis = new BufferedInputStream(in);
            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();
            String line;
            String[] testarray = new String[5*wordsum];
            try {
                br = new BufferedReader(new InputStreamReader(in));
                while ((line=br.readLine()) != null) {
                    sb.append(line);
                }
                result1=sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return mistarray5;
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return mistarray6;
                    }
                }
            }
            try{
                JSONArray jArray1 = new JSONArray(result1);
                for(int m=0;m<jArray1.length();m++){
                    JSONObject json_data = jArray1.getJSONObject(m);
                    String wordid=json_data.getString("wordid");
                    String word=json_data.getString("word");
                    String translation=json_data.getString("translation");
                    String scoreMariza=json_data.getString("correctMariza");
                    String scoreStella=json_data.getString("correctStella");
                    testarray[arrayposition]=wordid;
                    arrayposition++;
                    testarray[arrayposition]=word;
                    arrayposition++;
                    testarray[arrayposition]=translation;
                    arrayposition++;
                    testarray[arrayposition]=scoreMariza;
                    arrayposition++;
                    testarray[arrayposition]=scoreStella;
                    arrayposition++;
                }
            }catch(JSONException e){
                Log.e("log_tag", "Error parsing data "+e.toString());
                return mistarray7;
            }
            conn.disconnect();
            return testarray;
        }
        @Override
        protected void onPostExecute(final String[] result) {
            if(result[0].equals("1000")||result[0].equals("1001")||result[0].equals("1002")||result[0].equals("1003")||result[0].equals("1004")||result[0].equals("1005")||result[0].equals("1006")||result[0].equals("1007")){
                Toast toast = Toast.makeText(getApplicationContext(), "Aaaaaaaaaaaaaax" + result[0],Toast.LENGTH_LONG);
                toast.show();
            }
            else {
                englishanswears = new String[wordsum];
                greekanswears = new String[wordsum];
                correctanswears = new Boolean[wordsum];
                final String text = result[counter];
                speak(text);
                ImageView nextword=findViewById(R.id.nextword);
                nextword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView countnumber = findViewById(R.id.wordnumber);
                        try {
                            EditText wordanswearEdtext = findViewById(R.id.englishword);
                            EditText translationanswearEdtext = findViewById(R.id.translation);
                            String word = result[counter];
                            String translation = result[counter+1];
                            String wordanswear = wordanswearEdtext.getText().toString();
                            if (wordanswear==null || wordanswear.equals("") ||wordanswear.equals(" ")){
                                englishanswears[metritis] = "no answear";
                            }else {
                                englishanswears[metritis] = wordanswear;
                            }
                            String translationanswear = translationanswearEdtext.getText().toString();
                            if (translationanswear==null || translationanswear.equals("") ||translationanswear.equals(" ")){
                                greekanswears[metritis] = "no answear";
                            }else {
                                greekanswears[metritis] = translationanswear;
                            }
                            //Toast.makeText(getApplicationContext(),"Correct :"+word +" "+translation +" Answered :"+wordanswear +" "+translationanswear,Toast.LENGTH_LONG).show();
                            wordanswearEdtext.getText().clear();
                            translationanswearEdtext.getText().clear();
                            boolean isCorrect;
                            if (word.equals(wordanswear.trim()) && IsTranslationCorrect(translation,translationanswear)){
                                isCorrect= true;
                            } else {
                                isCorrect= false;
                            }
                            correctanswears[metritis] = isCorrect;
                            metritis++;
                            urlUpdate="http://u779583388.hostingerapp.com/EnglishDictation/updateword.php?wordentered="+wordanswear+"&translationentered="+translationanswear+"&correct="+isCorrect+"&wordid="+result[counter-1]+"&userid="+UserID+"";
                            new AsyncTaskUpdate().execute();
                        } catch (Exception e) {
                            Log.i("aaaaaaaaaaaaaaaaaa", e+"");
                        }
                        if (counter<result.length-4){
                            countnum++;
                            countnumber.setText(countnum +"/" + wordsum);
                            counter=counter+5;
                            final String text = result[counter];
                            speak(text);
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(),"the end",Toast.LENGTH_LONG);
                            toast.show();
                            setContentView(R.layout.results);
                            int score=0;
                            for (int i=0;i<correctanswears.length;i++){
                                if (correctanswears[i]){
                                    score=score+1;
                                }
                            }
                            TextView scoreview = findViewById(R.id.scoretitle);
                            double percentage = (double)score / (double)wordsum ;
                            percentage = Math.round(percentage * 100.0) / 100.0;
                            //+ "=" + (percentage *100) + "%"
                            scoreview.setText("Your score is "+score + "/" + wordsum );

                            for (int y=0;y<englishanswears.length;y++){
                                LinearLayout parent = new LinearLayout(Test.this);
                                parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                parent.setOrientation(LinearLayout.HORIZONTAL);

                                final TextView englishword = new TextView(Test.this);
                                englishword.setText(englishanswears[y]);
                                englishword.setTextSize(15);
                                englishword.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                                englishword.setHeight(400);

                                final TextView equals = new TextView(Test.this);
                                equals.setText("=");
                                equals.setTextSize(15);
                                equals.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                                equals.setHeight(400);

                                final TextView greekword = new TextView(Test.this);
                                greekword.setText(greekanswears[y]);
                                greekword.setTextSize(15);
                                greekword.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                                greekword.setHeight(400);

                                if (correctanswears[y]){
                                    englishword.setTextColor(Color.GREEN);
                                    equals.setTextColor(Color.GREEN);
                                    greekword.setTextColor(Color.GREEN);
                                } else {
                                    englishword.setTextColor(Color.RED);
                                    equals.setTextColor(Color.RED);
                                    greekword.setTextColor(Color.RED);
                                }

                                LinearLayout layout= findViewById(R.id.linearanswears);
                                parent.addView(englishword);
                                parent.addView(equals);
                                parent.addView(greekword);
                                layout.addView(parent);
                            }



                            //new AsyncTaskResults().execute();
                        }

                    }
                });
            }
    }
}


    public void speak(final String text){
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                    t1.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        ImageView speaker=findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = text;
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    public boolean IsTranslationCorrect( String translation, String translationanswear){
        translationanswear = translationanswear.trim();
        if (translation.contains(",")){
            String[] possibleTranslations = translation.split(",");
            for (int i=0;i<possibleTranslations.length;i++){
                if (translationanswear.equals(possibleTranslations[i].trim())){
                    return true;
                }
            }
        } else {
            if (translation.equals(translationanswear)){
                return true;
            }
        }
        return false;
    }

    class AsyncTaskUpdate extends AsyncTask<String, Void, String> {


        public AsyncTaskUpdate() {
            // TODO Auto-generated constructor stub
        }

        protected void onPreExecute() {
            //display progress dialog.
        }
        @Override
        protected String doInBackground(String... urls) {
            URL url;
            try {
                url = new URL(urlUpdate);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            HttpURLConnection conn;
            try {
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "1002";
            }
            if(conn==null){
                return "1003";}
            conn.setAllowUserInteraction(false);
            try {
                conn.connect();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            //  conn.setDoOutput(true);
            InputStream in;
            try{
                in = conn.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }
            BufferedInputStream bis = new BufferedInputStream(in);
            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                br = new BufferedReader(new InputStreamReader(in));
                while ((line=br.readLine()) != null) {
                    sb.append(line);
                }
                result=sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return "1006";
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "1007";
                    }
                }
            }

            conn.disconnect();
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            if (result.equals("NoUser")||result.equals("1001")||result.equals("1002")||result.equals("1003")||result.equals("1004")||result.equals("1005")||result.equals("1006")||result.equals("1007")||result.equals("0")){
                Toast toast = Toast.makeText(getApplicationContext(), "Ooooopss"+result,Toast.LENGTH_LONG);
                toast.show();
                Log.i("UserIDLogin", result);
                //Intent intent = new Intent(context, LoginScreen.class);
                //startActivity(intent);
            }
            else {
            }
        }

    }

    class AsyncTaskResults extends AsyncTask<String, Void, String[]> {


        public AsyncTaskResults() {
            // TODO Auto-generated constructor stub
        }

        protected void onPreExecute() {
            //display progress dialog.
        }
        @Override
        protected String[] doInBackground(String... urls) {
            URL url;
            try {
                url = new URL("http://192.168.1.45/english_dictation/showcorrectanswear.php?userid="+UserID+"");
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return mistarray;
            }
            HttpURLConnection conn;
            try {
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return mistarray1;
            }
            if(conn==null){
                return mistarray3;}
            conn.setAllowUserInteraction(false);
            try {
                conn.connect();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return mistarray4;
            }
            //  conn.setDoOutput(true);
            InputStream in;
            try{
                in = conn.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
                return mistarray5;
            }
            BufferedInputStream bis = new BufferedInputStream(in);
            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                br = new BufferedReader(new InputStreamReader(in));
                while ((line=br.readLine()) != null) {
                    sb.append(line);
                }
                result=sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return mistarray6;
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return mistarray7;
                    }
                }
            }
            String[] testarray = new String[3*wordsum];
            try{
                JSONArray jArray1 = new JSONArray(result1);
                for(int m=0;m<jArray1.length();m++){
                    JSONObject json_data = jArray1.getJSONObject(m);
                    String wordentered=json_data.getString("wordentered");
                    String translationentered=json_data.getString("translationentered");
                    String correctMariza=json_data.getString("correctMariza");
                    testarray[arrayposition2]=wordentered;
                    arrayposition2++;
                    testarray[arrayposition2]=translationentered;
                    arrayposition2++;
                    testarray[arrayposition2]=correctMariza;
                    arrayposition2++;
                }
            }catch(JSONException e){
                Log.e("log_tag", "Error parsing data "+e.toString());
                return mistarray7;
            }

            conn.disconnect();
            return testarray;
        }
        @Override
        protected void onPostExecute(String result[]) {
            if(result[0].equals("1000")||result[0].equals("1001")||result[0].equals("1002")||result[0].equals("1003")||result[0].equals("1004")||result[0].equals("1005")||result[0].equals("1006")||result[0].equals("1007")){
                Toast toast = Toast.makeText(getApplicationContext(), "wtff" + result[0],Toast.LENGTH_LONG);
                toast.show();
            }
            else {
                setContentView(R.layout.results);
                //Toast toast = Toast.makeText(getApplicationContext(), "OK" + result[0],Toast.LENGTH_LONG);
                //toast.show();
                int score=0;
                for (int i=2;i<result.length;i+=3){
                    if (result[i].equals("true")){
                        score=score+1;
                    }
                }
                TextView scoreview = findViewById(R.id.scoretitle);
                scoreview.setText("Your score is "+score);

                LinearLayout parent = new LinearLayout(Test.this);
                parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                parent.setOrientation(LinearLayout.HORIZONTAL);

                final TextView englishword = new TextView(Test.this);
                englishword.setText(result[counter2]);
                counter2++;
                englishword.setTextSize(25);
                englishword.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                englishword.setHeight(400);

                final TextView equals = new TextView(Test.this);
                equals.setText("=");
                equals.setTextSize(25);
                equals.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                equals.setHeight(400);

                final TextView greekword = new TextView(Test.this);
                greekword.setText(result[counter2]);
                counter2++;
                greekword.setTextSize(25);
                greekword.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                greekword.setHeight(400);

                LinearLayout layout= findViewById(R.id.linearanswears);
                parent.addView(englishword);
                parent.addView(equals);
                parent.addView(greekword);
                layout.addView(parent);
            }
        }

    }
}
