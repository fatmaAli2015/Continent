package com.example.fatmaali.continent1;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {
    ListView listView;
    ArrayList<listitem> listitems = new ArrayList<>();
    ArrayList<listitem> listIndex_Search = new ArrayList<>();
    String list_type = "main_index";
    String file_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.listview_title);

        //build the menu
        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_add_black_24dp));
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
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
                Intent intentSetting = new Intent(MainActivity.this, Setting.class);
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
                Intent intenthome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intenthome);
            }
        });
        buttonclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Index("continents.txt");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (list_type.equals("sub_index")) {
            Index("continents.txt");
            list_type = "main_index";
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        // Get the SearchView and set the searchable configuration
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_Search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navContinents) {
            Intent intentNavContinents = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intentNavContinents);

        } else if (id == R.id.navCoins) {
            Intent intentNavContinents = new Intent(getApplicationContext(), Coins.class);
            startActivity(intentNavContinents);

        } else if (id == R.id.navChoose) {
            Intent intentNavContinents = new Intent(getApplicationContext(), Choice.class);
            startActivity(intentNavContinents);
        } else if (id == R.id.nav_yesNo) {

        } else if (id == R.id.nav_share) {
            Intent morapp = new Intent(Intent.ACTION_VIEW);
            String morlink = "https://play.google.com/store/apps/developers?id=fatma94";
            morapp.setData(Uri.parse(morlink));
            startActivity(morapp);
        } else if (id == R.id.nav_send) {
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void Index(String index_type) {
        listitems.clear();
        try {
            InputStream IS = getAssets().open(index_type);
            InputStreamReader ISR = new InputStreamReader(IS);
            BufferedReader BR = new BufferedReader(ISR);
            String line;
            int id = 0;
            while ((line = BR.readLine()) != null) {
                id++;
                listitems.add(new listitem(line, "file_" + id));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ListAdapter1 listAdapter1 = new ListAdapter1(listitems);
        listView.setAdapter(listAdapter1);
    }


    class ListAdapter1 extends BaseAdapter {
        ArrayList<listitem> list = new ArrayList<>();

        public ListAdapter1(ArrayList<listitem> listitems) {
            this.list = listitems;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i).getTitle();
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View view1 = View.inflate(getApplicationContext(), R.layout.row_item, null);
            TextView Title = (TextView) view1.findViewById(R.id.txt_rowItem);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img1);
            Title.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));


            Title.setText(list.get(i).getTitle());
            Title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (list_type.equals("main_index")) {
                        list_type = "sub_index";
//                      file_id=listitems.get(i).getFile_id();
                        Index(list.get(i).getFile_id() + "/index.txt");
                    } else if (list_type.equals("sub_index")) {

                    }
                }
            });
            return view1;
        }
    }

    public void search(String word) {
//        if (word.length() > 1) {
            listIndex_Search.clear();
            for (int j = 0; j < listitems.size(); j++) {
                String name = listitems.get(j).getTitle();
                String Num =listitems.get(j).getFile_id();
                if (name.contains(word)) {
                    listIndex_Search.add(new listitem(name, Num));

                }
            }
            ListAdapter1 listAdapter1 = new ListAdapter1(listIndex_Search);
            listView.setAdapter(listAdapter1);
//        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        search(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        search(newText);
        return false;
    }

}