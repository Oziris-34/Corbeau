package com.example.a34011_73_01.corbeau_project;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by 34011-73-01 on 21/10/2016.
 */

public class Creation extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        Button infoCyril = (Button)findViewById(R.id.infoCyril);
        infoCyril.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String url = "http://cyril-ollier-cv.lab-net.fr";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
             }
        });

        Button infoJeremi = (Button)findViewById(R.id.infoJeremi);
        infoJeremi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String url = "http://rougetaal.wordpress.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        Button animationWeb = (Button)findViewById(R.id.animationWeb);
        animationWeb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String url = "https://www.youtube.com/watch?v=jkFxXIqpRZY";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

}