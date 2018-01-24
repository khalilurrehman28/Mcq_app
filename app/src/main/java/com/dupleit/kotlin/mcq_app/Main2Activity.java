package com.dupleit.kotlin.mcq_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.dupleit.kotlin.mcq_app.Network.APIService;
import com.dupleit.kotlin.mcq_app.Network.ApiClient;
import com.dupleit.kotlin.mcq_app.adapter.ViewPagerAdapter;
import com.dupleit.kotlin.mcq_app.modal.Question;
import com.dupleit.kotlin.mcq_app.modal.Question_Data;
import com.dupleit.kotlin.mcq_app.utils.constants;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends FragmentActivity {

    private List<Question_Data> question;
    TextView testingId;

    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //testingId = findViewById(R.id.textView);
        question = new ArrayList<>();
        getAllQuestionFromServer();
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),question);
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                invalidateOptionsMenu();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_screen_slide, menu);

        menu.findItem(R.id.action_previous).setEnabled(mPager.getCurrentItem() > 0);

        // Add either a "next" or "finish" button to the action bar, depending on which page
        // is currently selected.
        MenuItem item = menu.add(Menu.NONE, R.id.action_next, Menu.NONE,
                (mPager.getCurrentItem() == mPagerAdapter.getCount() - 1)
                        ? R.string.action_finish
                        : R.string.action_next);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Navigate "up" the demo structure to the launchpad activity.
                // See http://developer.android.com/design/patterns/navigation.html for more.
                NavUtils.navigateUpTo(this, new Intent(this, Main2Activity.class));
                return true;

            case R.id.action_previous:
                // Go to the previous step in the wizard. If there is no previous step,
                // setCurrentItem will do nothing.
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                return true;

            case R.id.action_next:
                // Advance to the next step in the wizard. If there is no next step, setCurrentItem
                // will do nothing.
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getAllQuestionFromServer() {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<Question> userCall = service.getQuestionAll();
        userCall.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                if(response!=null && response.isSuccessful()) {
                    //If the app is on foreground run the task
                    Log.d("question",""+response.body().getQuestion().get(2).getQUESTIONTEXT());

                    //webview.loadUrl(response.body().getQuestion().get(2).getQUESTIONTEXT());

                    /*
                    testingId.setText(Html.fromHtml(response.body().getQuestion().get(2).getQUESTIONTEXT(), new Html.ImageGetter() {

                        @Override
                        public Drawable getDrawable(String source) {
                            //int resourceId = getResources().getIdentifier(source, "drawable",getPackageName());
                            //Drawable drawable = getResources().getDrawable(resourceId);
                            Drawable drawable = null;
                            try {
                                drawable = drawableFromUrl(constants.webUrl+source);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Log.d("image",""+drawable.getIntrinsicHeight()+"-- width->"+drawable.getIntrinsicWidth());
                            drawable.setBounds(0, 0, 300, 100*//*height*//*);
                            return drawable;
                        }
                    }, null));*/


                }else{
                    Toast.makeText(Main2Activity.this, "data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }

    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(x);
    }
}
