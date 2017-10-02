package com.example.fatmaali.continent1;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Fatma Ali on 26/09/2017.
 */

public class ChoiceQuestion_3 extends AppCompatActivity {
    TextView result, Qu, AN1, AN2, AN3;
    ArrayList<String> QuList = new ArrayList<>();
    ArrayList<String> AnList = new ArrayList<>();
    ArrayList<String> ShowList = new ArrayList<>();

    int AnNum, ReNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_question);
        result = (TextView) findViewById(R.id.txt_result);
        Qu = (TextView) findViewById(R.id.txt_Question);
        AN1 = (TextView) findViewById(R.id.txt_rowAn1);
        AN2 = (TextView) findViewById(R.id.txt_rowAn2);
        AN3 = (TextView) findViewById(R.id.txt_rowAn3);


        //build the menu
        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_add_black_24dp));
        com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton actionButton = new com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();
        //build the button
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        //button1
        ImageView itemIcon = new ImageView(this);
        itemIcon.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_settings_black_24dp));
        SubActionButton buttonsettings = itemBuilder.setContentView(itemIcon).build();


        //button2
        itemIcon = new ImageView(this);
        itemIcon.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_apps_black_24dp));
        SubActionButton buttonapps = itemBuilder.setContentView(itemIcon).build();

        //button3
        itemIcon = new ImageView(this);
        itemIcon.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_email_black_24dp));
        SubActionButton buttonemail = itemBuilder.setContentView(itemIcon).build();

        //button4
        itemIcon = new ImageView(this);
        itemIcon.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_home_black_24dp));
        SubActionButton buttonhome = itemBuilder.setContentView(itemIcon).build();
        //button5
        itemIcon = new ImageView(this);
        itemIcon.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_clear_black_24dp));
        SubActionButton buttonclose = itemBuilder.setContentView(itemIcon).build();

        //add every thing
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonsettings)
                .addSubActionView(buttonapps)
                .addSubActionView(buttonemail)
                .addSubActionView(buttonhome)
                .addSubActionView(buttonclose)
                .attachTo(actionButton)
                .build();

        buttonsettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSetting = new Intent(getApplicationContext(), Setting.class);
                startActivity(intentSetting);
            }
        });
        buttonemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String txt = " this is my suggestion ";
                    Intent emailshare = new Intent(Intent.ACTION_SEND);
                    emailshare.setData(Uri.parse("mailto"));
                    emailshare.setType("message/rfc822");
                    String[] email = {"fatmaaliibrahim1994@yahoo.com", "fatmaaliibrahim2015@gmail.com"};
                    emailshare.putExtra(Intent.EXTRA_EMAIL, email);
                    emailshare.putExtra(Intent.EXTRA_SUBJECT, "Quran");
                    emailshare.putExtra(Intent.EXTRA_TEXT, txt);
                    Intent chooser = Intent.createChooser(emailshare, "launch Email");
                    startActivity(chooser);
                    startActivity(emailshare);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "No found this app", Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent morapp = new Intent(Intent.ACTION_VIEW);
                String morlink = "https://play.google.com/store/apps/developers?id=fatma94";
                morapp.setData(Uri.parse(morlink));
                startActivity(morapp);
            }
        });
        buttonhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenthome = new Intent(getApplicationContext(), Choice.class);
                startActivity(intenthome);
            }
        });
        buttonclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
finish();            }
        });

        try {
            InputStream IS = getAssets().open("Qu_1.txt");
            InputStreamReader ISR = new InputStreamReader(IS);
            BufferedReader BR = new BufferedReader(ISR);
            String line;
            while ((line = BR.readLine()) != null) {
                QuList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream IS = getAssets().open("An_1.txt");
            InputStreamReader ISR = new InputStreamReader(IS);
            BufferedReader BR = new BufferedReader(ISR);
            String line;
            while ((line = BR.readLine()) != null) {
                AnList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ShowList.add("");
        showTxt();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void showTxt() {
        if (ShowList.size() < QuList.size()) {
            Random random = new Random();
            int Ran_Qu = random.nextInt(QuList.size());

            String Qus = QuList.get(Ran_Qu);
            Boolean a = true;

            for (int i = 0; i < ShowList.size(); i++) {
                if (Qus.equals(ShowList.get(i))) {
                    showTxt();
                    a = false;
                    ShowList.add(QuList.get(Ran_Qu));
                    break;
                }
            }

            if (a) {
                Qu.setText(Qus);
                String[] Ana = AnList.get(Ran_Qu).split(",");
                AN1.setText(Ana[0]);
                AN2.setText(Ana[1]);
                AN3.setText(Ana[2]);

                AnNum = Integer.parseInt(Ana[3]);
                ShowList.add(QuList.get(Ran_Qu));
            }
        } else {
            AlertDialog.Builder AltDia_builder = new AlertDialog.Builder(this);
            AltDia_builder.setMessage(R.string.messageDialog)
                    .setIcon(R.drawable.ic_world_black_24dp)
                    .setTitle(R.string.Dialog)
                    .setPositiveButton(R.string.YesAnswer, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), "انتهت الاسئلة", Toast.LENGTH_LONG).show();
                            Intent in = new Intent(getApplicationContext(), Setting.class);
                            startActivity(in);
                        }
                    })
                    .setNegativeButton(R.string.NoMessage, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
        }
    }

    public void txt_rowAn1(View view) {
        if (AnNum == 1) {
            Toast.makeText(getApplicationContext(), "الاجابة الصحيحة", Toast.LENGTH_LONG).show();
            ReNum++;
            result.setText("عدد الاجابات الصحيحة :" + ReNum);
            showTxt();
        } else {
            Toast.makeText(getApplicationContext(), "الاجابة خاطئة", Toast.LENGTH_LONG).show();
//            showTxt();
        }
    }

    public void txt_rowAn2(View view) {
        if (AnNum == 2) {
            Toast.makeText(getApplicationContext(), "الاجابة الصحيحة", Toast.LENGTH_LONG).show();
            ReNum++;
            result.setText("عدد الاجابات الصحيحة :" + ReNum);
            showTxt();
        } else {
            Toast.makeText(getApplicationContext(), "الاجابة خاطئة", Toast.LENGTH_LONG).show();
//            showTxt();
        }
    }

    public void txt_rowAn3(View view) {
        if (AnNum == 3) {
            Toast.makeText(getApplicationContext(), "الاجابة الصحيحة", Toast.LENGTH_LONG).show();
            ReNum++;
            result.setText("عدد الاجابات الصحيحة :" + ReNum);
            showTxt();
        } else {
            Toast.makeText(getApplicationContext(), "الاجابة خاطئة", Toast.LENGTH_LONG).show();
//            showTxt();
        }
    }
}
