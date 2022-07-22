package com.newnepquotes.shyarihin2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.google.android.material.navigation.NavigationView;
import com.newnepquotes.shyarihin2022.Model.Model;
import com.newnepquotes.shyarihin2022.NavigationDrawer.Facebook_Page;
import com.newnepquotes.shyarihin2022.NavigationDrawer.Instagram_Page;
import com.newnepquotes.shyarihin2022.NavigationDrawer.PrivacyPolicy;
import com.newnepquotes.shyarihin2022.NavigationDrawer.TermsConditions;
import com.newnepquotes.shyarihin2022.NavigationDrawer.Website_Page;
import com.newnepquotes.shyarihin2022.SliderPack.SliderAdapter;
import com.newnepquotes.shyarihin2022.SliderPack.SliderItem;

import java.util.ArrayList;
import java.util.List;

public class Home_Activity extends AppCompatActivity {
    // this is for automatic slider
    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();

    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    private MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        //here is the begening yo chai starting hooooo



        viewPager2 = findViewById(R.id.viewPagerImageSlider);
        //Here  the list of image form drawable, can be used
        // you can get form ap as weelll,
        List<SliderItem> sliderItmes = new ArrayList<>();
        sliderItmes.add(new SliderItem(R.drawable.sockone));
        sliderItmes.add(new SliderItem(R.drawable.stocktwo));
        sliderItmes.add(new SliderItem(R.drawable.stock_three));




        viewPager2.setAdapter(new SliderAdapter(sliderItmes, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,2500); // slider duration time
            }
        });




        //ignore above file
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navigation_menu);
        navigationView.setItemIconTintList(null);
        drawerLayout = findViewById(R.id.drawer);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.Open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.websitePage:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent website = new Intent(Home_Activity.this, Website_Page.class);
                        startActivity(website);
                        break;
                    case R.id.facebookPage:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent facebook = new Intent(Home_Activity.this, Facebook_Page.class);
                        startActivity(facebook);
                        break;
                    case R.id.InstagarmPage:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent instagram = new Intent(Home_Activity.this, Instagram_Page.class);
                        startActivity(instagram);
                        break;

                    case R.id.nav_rate:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("market://details?id="+getPackageName())));
                        }
                        catch (ActivityNotFoundException e){
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id="+getPackageName())));
                        }
                        break;

                    case R.id.nav_share:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent myIntent = new Intent(Intent.ACTION_SEND);
                        myIntent.setType("text/plain");
                        String shareBody = "Hindi Quotes and Shyari\n" + "https://play.google.com/store/apps/details?id=" + getPackageName();
                        String shareSub = "Please share this application";
                        myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                        myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                        startActivity(Intent.createChooser(myIntent,"Share Using"));
                        break;

                    case R.id.nav_privacy_policy:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent privacy = new Intent(Home_Activity.this, PrivacyPolicy.class);
                        startActivity(privacy);
                        break;
                    case R.id.nav_terms_conditions:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent terms = new Intent(Home_Activity.this, TermsConditions.class);
                        startActivity(terms);
                        break;







                }

                return true;



            }


        });
        //RecyclerView
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        //Set its Properties
        //gird view with 2 colums in each row
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //adapter
        mAdapter = new MyAdapter(this,getModels());
        mRecyclerView.setAdapter(mAdapter);


    }// here is the ending for the automatic slider



    private ArrayList<Model> getModels(){
        ArrayList<Model> models = new ArrayList<>();
        Model p;
        //create an items for recycler veiw blelow
        p = new Model();
        p.setTitle("Motivational");
        p.setImage(R.drawable.one);
        models.add(p);

         p = new Model();
        p.setTitle("Happy Quotes");
        p.setImage(R.drawable.two);
        models.add(p);

         p = new Model();
        p.setTitle("Good Night");
        p.setImage(R.drawable.three);
        models.add(p);

         p = new Model();
        p.setTitle("Funny Quotes");
        p.setImage(R.drawable.four);
        models.add(p);

         p = new Model();
        p.setTitle("Romantic Quotes");
        p.setImage(R.drawable.five);
        models.add(p);

         p = new Model();
        p.setTitle("Energetic Quotes");
        p.setImage(R.drawable.six);
        models.add(p);

         p = new Model();
        p.setTitle("Life Quotes");
        p.setImage(R.drawable.seven);
        models.add(p);

         p = new Model();
        p.setTitle("Good Morning");
        p.setImage(R.drawable.eight);
        models.add(p);

         p = new Model();
        p.setTitle("Happy Birthday");
        p.setImage(R.drawable.nine);
        models.add(p);

         p = new Model();
        p.setTitle("Love Quotes");
        p.setImage(R.drawable.ten);
        models.add(p);

         p = new Model();
        p.setTitle("Dosti Quotes");
        p.setImage(R.drawable.eleven);
        models.add(p);

        p = new Model();
        p.setTitle("Attitude Quotes");
        p.setImage(R.drawable.attitude);
        models.add(p);

        return models;


    }
    private void layoutAnimation(RecyclerView recyclerView){
        Context context = recyclerView.getContext();
        LayoutAnimationController layoutAnimationController =
                AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation_slide_left);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+ 1);

        }
    };
    @Override
    protected void onPause(){
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }
    @Override
    protected void onResume(){
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable,3000);
    }
}