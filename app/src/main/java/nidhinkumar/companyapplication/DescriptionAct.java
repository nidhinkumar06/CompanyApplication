package nidhinkumar.companyapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by user on 22-05-2016.
 */
public class DescriptionAct extends AppCompatActivity {
    TextView desc;
    String receivingdata;
    Toolbar mtool;
    @Override
    public void onBackPressed() {
        Intent j=new Intent(DescriptionAct.this,CompanyAct.class);
        startActivity(j);
        DescriptionAct.this.finish();
    }
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         Bundle b = getIntent().getExtras();
         receivingdata = b.getString("key");
         setContentView(R.layout.description_frag);
         mtool = (Toolbar) findViewById(R.id.toolbar);
         mtool.setTitle("Description");
         mtool.setNavigationIcon(R.drawable.back);

         setSupportActionBar(mtool);
         getSupportActionBar().setDisplayShowHomeEnabled(true);
         desc=(TextView)findViewById(R.id.tvdescription);
          desc.setText(receivingdata);
         mtool.setNavigationOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i=new Intent(DescriptionAct.this,CompanyAct.class);
                 startActivity(i);
                 DescriptionAct.this.finish();
             }
         });
    }
}
