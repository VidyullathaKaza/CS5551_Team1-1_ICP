package tutorial.cs5551.com.translateapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TranslateActivity extends AppCompatActivity {

    String API_URL = "https://api.fullcontact.com/v2/person.json?";
    String API_KEY = "b29103a702edd6a";
    String sourceText;
    String fromText;
    String toText;
    TextView outputTextView;
    Context mContext;
    Spinner myfromSpinner;
    Spinner mytospinner;

    Map<String,String> countryCodeMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        countryCodeMap.put("Azerbajan","az");
        countryCodeMap.put("Albanian","sq");
        countryCodeMap.put("Amhario","am");
        countryCodeMap.put("English","en");
        countryCodeMap.put("Arabic","ar");
        countryCodeMap.put("Armenian","hy");
        countryCodeMap.put("Africaans","af");
        countryCodeMap.put("Basque","eu");
        countryCodeMap.put("Bashkir","ba");
        countryCodeMap.put("Bellarusian","be");
        countryCodeMap.put("Bengali","bn");
        countryCodeMap.put("Burmese","my");
        countryCodeMap.put("Bulgarian","bg");
        countryCodeMap.put("Bosnian","bs");
        countryCodeMap.put("Weish","cy");
        countryCodeMap.put("Hungarian","hu");
        countryCodeMap.put("Vietnamese","vi");
        countryCodeMap.put("Haitian","ht");
        countryCodeMap.put("Galician","gl");
        countryCodeMap.put("Dutch","nl");
        countryCodeMap.put("Hill Mari","mrj");
        countryCodeMap.put("Greek","el");
        countryCodeMap.put("Georgian","ka");
        countryCodeMap.put("Gujarati","gu");
        countryCodeMap.put("Danish","da");
        countryCodeMap.put("Hebrew","he");
        countryCodeMap.put("Yiddish","yi");
        countryCodeMap.put("Indonesian","id");
        countryCodeMap.put("Irish","ga");
        countryCodeMap.put("Italian","it");
        countryCodeMap.put("Icelandic","is");
        countryCodeMap.put("Spanish","es");
        countryCodeMap.put("Kazakh","kk");
        countryCodeMap.put("Kannada","kn");;
        countryCodeMap.put("Catalan","ca");
        countryCodeMap.put("Kyrgyz","ky");
        countryCodeMap.put("Chinese","zh");
        countryCodeMap.put("Korean","ko");
        countryCodeMap.put("Xhosa","xh");
        countryCodeMap.put("Khmer","km");
        countryCodeMap.put("Laotian","lo");
        countryCodeMap.put("Latin","la");
        countryCodeMap.put("Latvian","lv");
        countryCodeMap.put("Lithuanian","lt");
        countryCodeMap.put("Luxembourgish","lb");
        countryCodeMap.put("Malagasy","mg");
        countryCodeMap.put("Malay","ms");
        countryCodeMap.put("Malayalam","ml");
        countryCodeMap.put("Maltese","mt");
        countryCodeMap.put("Macedonian","mk");
        countryCodeMap.put("Maori","mi");
        countryCodeMap.put("Marathi","mr");
        countryCodeMap.put("Mari","mhr");
        countryCodeMap.put("Mongolian","mn");
        countryCodeMap.put("German","de");
        countryCodeMap.put("Nepali","ne");
        countryCodeMap.put("Norwegian","no");
        countryCodeMap.put("Punjabi","pa");
        countryCodeMap.put("Papiamento","pap");
        countryCodeMap.put("Persian","fa");
        countryCodeMap.put("Polish","pa");
        countryCodeMap.put("Portuguese","pt");
        countryCodeMap.put("Romanian","ro");
        countryCodeMap.put("Russian","ru");
        countryCodeMap.put("Cebuano","ceb");
        countryCodeMap.put("Serbian","sr");
        countryCodeMap.put("Sinhala","si");
        countryCodeMap.put("Slovakian","sk");
        countryCodeMap.put("Slovenian","sl");
        countryCodeMap.put("Swahili","sw");
        countryCodeMap.put("Sundanese","su");
        countryCodeMap.put("Tajik","tg");
        countryCodeMap.put("Thai","th");
        countryCodeMap.put("Tagalog","tl");
        countryCodeMap.put("Tamil","ta");
        countryCodeMap.put("Tatar","tt");
        countryCodeMap.put("Telugu","te");
        countryCodeMap.put("Turkish","tr");
        countryCodeMap.put("Udmurt","udm");
        countryCodeMap.put("Uzbek","uz");
        countryCodeMap.put("Ukrainian","uk");
        countryCodeMap.put("Urdu","ur");
        countryCodeMap.put("Finnish","fi");
        countryCodeMap.put("French","fr");
        countryCodeMap.put("Hindi","hi");
        countryCodeMap.put("Croatian","hr");
        countryCodeMap.put("Czech","cs");
        countryCodeMap.put("Swedish","sv");
        countryCodeMap.put("Scottish","gd");
        countryCodeMap.put("Estonian","et");
        countryCodeMap.put("Esperanto","eo");
        countryCodeMap.put("Javanese","jv");
        countryCodeMap.put("Japanese","ja");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        outputTextView = (TextView) findViewById(R.id.txt_Result);
        //From Spinner
        myfromSpinner = (Spinner) findViewById(R.id.fromSpinner);
        ArrayAdapter<String> myAdpater = new ArrayAdapter<String>(TranslateActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.countryNames));
        myAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myfromSpinner.setAdapter(myAdpater);
        //To Spinner
        mytospinner = (Spinner) findViewById(R.id.toSpinner);
        ArrayAdapter<String> mytoAdpater = new ArrayAdapter<String>(TranslateActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.countryNames));
        mytoAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mytospinner.setAdapter(myAdpater);
    }

    public void logOut(View v){
        Intent homepage = new Intent(TranslateActivity.this, LoginActivity.class);
        startActivity(homepage);
        finish();
    }

    public void translateText(View v) {
        TextView sourceTextView = (TextView) findViewById(R.id.txt_Email);
        String fromCode = countryCodeMap.get(myfromSpinner.getSelectedItem().toString());
        String toCode = countryCodeMap.get(mytospinner.getSelectedItem().toString());
        sourceText = sourceTextView.getText().toString();
        String getURL = "https://translate.yandex.net/api/v1.5/tr.json/translate?" +
                "key=trnsl.1.1.20151023T145251Z.bf1ca7097253ff7e." +
                "c0b0a88bea31ba51f72504cc0cc42cf891ed90d2&text=" + sourceText +"&" +
                "lang="+fromCode+"-"+toCode+"&[format=plain]&[options=1]&[callback=set]";//The API service URL
        final String response1 = "";
        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request.Builder()
                    .url(getURL)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e.getMessage());
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final JSONObject jsonResult;
                    final String result = response.body().string();
                    try {
                        jsonResult = new JSONObject(result);
                        JSONArray convertedTextArray = jsonResult.getJSONArray("text");
                        final String convertedText = convertedTextArray.get(0).toString();
                        Log.d("okHttp", jsonResult.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                outputTextView.setText(convertedText);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception ex) {
            outputTextView.setText(ex.getMessage());

        }

    }
}
