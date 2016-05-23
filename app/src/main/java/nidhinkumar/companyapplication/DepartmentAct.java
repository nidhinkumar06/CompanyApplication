package nidhinkumar.companyapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import nidhinkumar.companyapplication.adapter.DepartListAdapter;
import nidhinkumar.companyapplication.util.ConnectionDetector;

/**
 * Created by user on 22-05-2016.
 */
public class DepartmentAct extends AppCompatActivity{
    private Toolbar mToolbar;
    ListView list;
    String receivingdata;
    String[] strdetails;
    DepartListAdapter adapter;

    @Override
    public void onBackPressed() {
        Intent j=new Intent(DepartmentAct.this,CompanyAct.class);
        startActivity(j);
        DepartmentAct.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        receivingdata = b.getString("key");
        setContentView(R.layout.frag_departments);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Departments");
        mToolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        strdetails=receivingdata.split(",");
       adapter =new DepartListAdapter(this,strdetails);
        list=(ListView)findViewById(R.id.listview1);
        list.setAdapter(adapter);
        list.setTextFilterEnabled(true);
       // ArrayList<String>listno=new ArrayList<String>(Arrays.asList(strdetails));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(DepartmentAct.this,CompanyAct.class);
                startActivity(i);
                DepartmentAct.this.finish();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.departmentmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        if(id == R.id.action_search){
            SearchManager searchManager = (SearchManager) DepartmentAct.this.getSystemService(Context.SEARCH_SERVICE);

            SearchView searchView = null;

         //   if (searchView != null) {
          //      searchView.setSearchableInfo(searchManager.getSearchableInfo(DepartmentAct.this.getComponentName()));
          //  }
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);

            SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener()
            {
                @Override
                public boolean onQueryTextChange(String newText)
                {
                    // this is your adapter that will be filtered

                    adapter.getFilter().filter(newText);
                    adapter.notifyDataSetChanged();
                    System.out.println("on text chnge text: "+newText);
                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query)
                {
                    // this is your adapter that will be filtered
                    adapter.getFilter().filter(query);
                    adapter.notifyDataSetChanged();
                    System.out.println("on query submit: "+query);
                    return true;
                }
            };
            searchView.setOnQueryTextListener(textChangeListener);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
