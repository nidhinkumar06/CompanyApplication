package nidhinkumar.companyapplication;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nidhinkumar.companyapplication.adapter.CustomListAdapter;
import nidhinkumar.companyapplication.app.AppController;
import nidhinkumar.companyapplication.model.Company;
import nidhinkumar.companyapplication.util.ConnectionDetector;

/**
 * Created by user on 19-05-2016.
 */
public class CompanyAct extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private static final String TAG = MainActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private static final String url = "https://api.myjson.com/bins/2ggcs";
    private ProgressDialog pDialog;
    private List<Company> companydetails = new ArrayList<Company>();
    private ListView listView;
    Boolean isInternetPresent = false;
    AlertDialog alert;
    ConnectionDetector cd;
    private CustomListAdapter adapter;
    @Override
    public void onBackPressed() {
        CompanyAct.this.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.companyprimarydetails);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listView = (ListView) findViewById(R.id.companylist);
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        if(isInternetPresent) {
            adapter = new CustomListAdapter(this, companydetails);
            listView.setAdapter(adapter);
            listView.setTextFilterEnabled(true);
            pDialog = new ProgressDialog(this);
            // Showing progress dialog before making http request
            pDialog.setMessage("Loading...");
            pDialog.show();
            JsonArrayRequest movieReq = new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());
                            hidePDialog();

                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);


                                    Company company = new Company();
                                    // Company company = new Company(obj.getInt("companyID"),obj.getString("comapnyName"),obj.getString("companyOwner"),obj.getString("companyStartDate"),obj.getString("companyDescription"),departments);
                                    company.setCompanyid(obj.getInt("companyID"));
                                    company.setCompanyname(obj.getString("comapnyName"));
                                    company.setCompanyowner(obj.getString("companyOwner"));
                                    company.setStartdate(obj.getString("companyStartDate"));
                                    company.setDescription(obj.getString("companyDescription"));
                                    company.setDepartments(obj.getString("companyDepartments"));

                                    companydetails.add(company);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data
                            adapter.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    hidePDialog();

                }
            });
            AppController.getInstance().addToRequestQueue(movieReq);
        }else{
            neti();
        }
    }

    private void neti() {
        final LayoutInflater layoutInflater = LayoutInflater.from(CompanyAct.this);
        final View promptView = layoutInflater.inflate(R.layout.connectionlost, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CompanyAct.this);
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder.setCancelable(false);
        final Button retry=(Button)promptView.findViewById(R.id.btnretry);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=getIntent();
                finish();
                startActivity(intent);
            }
        });
        alert= alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        if(id == R.id.action_search){
            SearchManager searchManager = (SearchManager) CompanyAct.this.getSystemService(Context.SEARCH_SERVICE);

            SearchView searchView = null;
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);
            adapter.setFilter(companydetails);
        }

        return super.onOptionsItemSelected(item);
    }


    private List<Company> filter(List<Company> models, String query) {
        query = query.toLowerCase();

        final List<Company> filteredModelList = new ArrayList<>();
        for (Company model : models) {
            final String text = model.getCompanyname().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {


        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<Company> filteredModelList = filter(companydetails, newText);
        adapter.setFilter(filteredModelList);
        System.out.println("on text chnge text: "+newText);
        adapter.notifyDataSetChanged();
        return true;
    }
}
