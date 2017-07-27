package com.example.oem.hyperrocketstock;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whocares.hyperrocketstock.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.ContentHandler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends FragmentActivity {

    private static final String TAG = Main.class.getSimpleName();
    private ViewGroup mListSelectorLayout, mDetailLayout;

    public static final int CONTENT_VIEW_ID3 = 101010111;

    static boolean has_panes;

    private static boolean flag2 = false;

    private static final int XML = 0;
    private static final int JSON = 1;
    
    private static final int CSV = 3;
    private int mode = CSV; //default
    //private int mode= JSON;

    private static String loser = null;
    private static Double mostNeg = 0.0;
    private static String winner = null;
    private static Double mostPos = 0.0;

    private static String mostAction = null;
    private static Double maxAveVol = 0.0;

    public static Stock[] pubStocks;

    private TextView symbolsList2;

    public ListView listView;
    
    public static int absolute = 0;

    ListFragment listFragment;

    private static String[] symbols;
    private static String[] savesymbols;

    private static Boolean loaded;
    public static String[] loadsymbols;

    int data_block = 100;

    public static boolean removeStock = false;

 @Override
    public void onCreate(final Bundle savedInstanceState) {  // added final lately
        super.onCreate(savedInstanceState);

        loaded = false;

        setContentView(R.layout.activity_main);

            final EditText input = (EditText) findViewById(R.id.symbol);
            final TextView symbolsList = (TextView) findViewById(R.id.symList);
            symbolsList2 = symbolsList;
            final Button addButton = (Button) findViewById(R.id.addBtn);
            final Button dlButton = (Button) findViewById(R.id.dlBtn);
            final Button buyButton = (Button) findViewById(R.id.BbBtn);
            final Button sellButton = (Button) findViewById(R.id.BsBtn);
            final Button remButton = (Button) findViewById(R.id.RemBtn);
            final Button rfsButton = (Button) findViewById(R.id.RfBtn);
            final Button clrButton = (Button) findViewById(R.id.ClrBtn);
            final Button ActButton = (Button) findViewById(R.id.ActBtn);
            final Button saveButton = (Button) findViewById(R.id.svBtn);
            final Button loadButton = (Button) findViewById(R.id.ldBtn);
            final String[] symList2 = {null};
            final TextView row = (TextView) findViewById(R.id.clickTV);
           
            runOnUiThread(new Runnable() {
                public void run() {
                    String text = "One Stock that you've inputted has a wrong Ticker Symbol or may not be a Stock and " +
                            "it has an Average Volume of N/A so replace the Ticker Symbol and Try Again!";
 runOnUiThread(new Runnable() {
                public void run() {
                    String text = "One Stock that you've inputted has a wrong Ticker Symbol or may not be a Stock and " +
                            "it has an Average Volume of N/A so replace the Ticker Symbol and Try Again!";
                    Toast.makeText(Main.this, text, Toast.LENGTH_LONG);
                }
            });

            sellButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (loaded == true) {
                        symbols = loadsymbols;
                        int num = symbols.length;
                        String text = "Based on your Portfolio of " + num + " Stocks, the best stock to SELL from your Portfolio is "
                                + winner + " which has the most positive % price change of " + mostPos +
                                " at this very moment! Click Stock to calculate Profit before Selling shares!" +
                                " Disclaimer: BUY or SELL Stocks at your OWN Risk!";
                        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        // symbols = symList2[0].split(",");
                        // loaded = false;
                        String symList = symList2[0];
                        if (symList == null || symList.trim().equals("")) {
                            String text = "Your Portfolio is empty. Add Stocks first then Get Stock Data before you can use the Refresh Button.";
                            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                            toast.show();
                        } else {
                            //}
                            //symbols = symList2[0].split(",");
                            int num = symbols.length;
                            String text = "Based on your Portfolio of " + num + " Stocks, the best stock to SELL from your Portfolio is "
                                    + winner + " which has the most positive % price change of " + mostPos +
                                    " at this very moment! Click Stock to calculate Profit before Selling shares!" +
                                    " Disclaimer: BUY or SELL Stocks at your OWN Risk!";
                            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                }
            });

            buyButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (loaded == true) {
                        symbols = loadsymbols;
                        int num = symbols.length;
                        String text = "Based on your Portfolio of " + num + " Stocks, the best stock to BUY or ADD MORE to your Portfolio is "
                                + loser + " which has the most negative % price change of " + mostNeg +
                                " at this very moment! Disclaimer: BUY or SELL Stocks at your OWN RISK!!!";
                        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                        toast.show();
                    }else {
                            String symList = symList2[0];
                            if (symList == null || symList.trim().equals("")) {
                            String text = "Your Portfolio is empty. Add Stocks first then Get Stock Data before you can use the Refresh Button.";
                            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                            toast.show();
                            } else {
                                int num = symbols.length;
                                String text = "Based on your Portfolio of " + num + " Stocks, the best stock to BUY or ADD MORE to your Portfolio is "
                                        + loser + " which has the most negative % price change of " + mostNeg +
                                        " at this very moment! Disclaimer: BUY or SELL Stocks at your OWN RISK!!!";
                                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                                toast.show();
                            }
                    }             
            });

            addButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    loaded = false;
                    winner = null;
                    loser = null;
                    mostPos = 0.0;
                    mostNeg = 0.0;
                    String newSymbol = input.getText().toString();
                    if (symbolsList.getText() == null || symbolsList.getText().length() == 0) {
                        symbolsList.setText(newSymbol);
                    } else {
                        StringBuilder sb = new StringBuilder(symbolsList.getText());
                        sb.append(",");
                        sb.append(newSymbol);
                        symbolsList.setText(sb.toString());
                    }
                    input.setText("");
                }
            });

            dlButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String symList = symbolsList.getText().toString();
                    if (symList == null || symList.trim().equals("")) {
                        Toast.makeText(getApplicationContext(), "You have not entered any Stock Ticker Symbol! Try Again!", Toast.LENGTH_LONG).show();
                    } else {
                        symList2[0] = symList;
                        symbols = symList.split(",");
                        symbolsList.setText("");
                        switch (mode) {
                            case JSON:
                                new StockJsonParser().execute(symbols);
                                break;
                            case CSV:
                                new StockCsvParser().execute(symbols);
                                loaded = false;
                                break;
                            default:
                                new StockXmlParser().execute(symbols);
                                break;
                        }
                    }
                }
            });

            remButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                removeStock = true;
                winner = null;
                loser = null;
                mostAction = null;
                mostPos=0.0;
                mostNeg=0.0;
                maxAveVol=0.0;
            if (loaded == false){
                String text = "Your Portfolio is empty. Add Stocks first then Get Stock Data before you can use the Remove Stock Button.";
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();
            }else {
                symbols = loadsymbols;
                loaded = true;
                new StockCsvParser().execute(symbols);
                String text ="Click the Stock you want removed from your Portfolio from the ListView";
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();
            }
            }
        });

            rfsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    winner = null;
                    loser = null;
                    mostAction = null;
                    mostPos=0.0;
                    mostNeg=0.0;
                    maxAveVol=0.0;
                    String symList = symList2[0];
                        if (loaded == false) {
                            if (symList == null || symList.trim().equals("")) {
                                String text = "Your Portfolio is empty. Add Stocks first then Get Stock Data before you can use the Refresh Button.";
                                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                                toast.show();
                            }else {
                                symbols = symList.split(",");
                                new StockCsvParser().execute(symbols);
                            }
                        } else {
                            symbols = loadsymbols;
                            loaded = true;
                            new StockCsvParser().execute(symbols);
                        }
                    }
            });
            
            clrButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    input.setText("");
                    symbolsList.setText("");
                }
            });
            
            saveButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                try {
                    savesymbols = symbols;
                    List<String> list = new ArrayList<String>(Arrays.asList(savesymbols));
                    Toast.makeText(getBaseContext(), "Data about to be saved" + list.toString() , Toast.LENGTH_LONG).show();                 
                    String tmp = list.toString();
                    FileOutputStream fos = openFileOutput("text.txt",MODE_WORLD_READABLE);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);       
                        osw.write(tmp);          
                        osw.flush();          
                        osw.close();          
                       Toast.makeText(getBaseContext(), "Data saved" + tmp, Toast.LENGTH_LONG).show();
                    }catch( Exception e){
                        e.printStackTrace();                
                    }
                         }
            });
            
            loadButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    mostAction = null;
                    maxAveVol = 0.0;
                    try {
                        FileInputStream fis = openFileInput("text.txt");
                        InputStreamReader isr = new InputStreamReader(fis); char[] data = new char[data_block];
                        char[] data = new char[data_block];
                        String final_data = "";
                        int size;
                            while((size=isr.read(data))>0){
                                String read_data = String.copyValueOf(data,0,size);
                                final_data += read_data;
                                data = new char[data_block];
                            }
                        String str = final_data;
                        String s = str;
                        String regex = "\\[|\\]";
                        s = s.replaceAll(regex, "");
                        loadsymbols = s.split("\\s*,\\s*");                                             
                        symbols = loadsymbols;
                         List<String> list2 = new ArrayList<String>(Arrays.asList(loadsymbols));
                        Toast.makeText(getBaseContext(),"Your Portfolio which includes "+ list2.toString() + " are being loaded.",Toast.LENGTH_LONG).show();
                        loaded = true;
                        new StockCsvParser().execute(symbols);
                    } catch(IOException e){
                        System.out.println(e);
                    }
                }
            });
            
             ActButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int num;
                    if (loaded == true){
                        symbols = loadsymbols;
                        num = symbols.length;
                        String text = "Based on your Portfolio of " + num + " Stocks, the Stock that has the Most Action (where there is more buying and selling than the usual compared to other stocks) is "
                                + mostAction + " which has the Highest Percent Average Volume of " + maxAveVol +
                                " percent at this very moment! Do you want Text Notification if " + mostAction + " price goes to a certain price? (Click Notify Button! Coming Soon)";
                        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        String symList = symList2[0];
                        if (symList == null || symList.trim().equals("")) {
                            String text = "Your Portfolio is empty. Add Stocks first then Get Stock Data before you can use the Refresh Button.";
                            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                            toast.show();
                        } else {
                            num = symbols.length;
                            String text = "Based on your Portfolio of " + num + " Stocks, the Stock that has the Most Action (where there is more buying and selling than the usual compared to other stocks) is "
                                    + mostAction + " which has the Highest Percent Average Volume of " + maxAveVol +
                                    " percent at this very moment! Do you want Text Notification if " + mostAction + " price goes to a certain price? (Click Notify Button! Coming Soon)";
                            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                            toast.show();
                        }                    
                    }
                    }
            });
