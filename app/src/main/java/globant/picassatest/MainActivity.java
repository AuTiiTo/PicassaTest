package globant.picassatest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import globant.picassatest.common.MainItem;
import globant.picassatest.user_interface.FirstFragment;
import globant.picassatest.user_interface.SecondFragment;

public class MainActivity extends AppCompatActivity implements SecondFragment.OnItemSelected {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_layout, SecondFragment.newInstance(), "RageComicList")
                    .commit();
        }
    }

    @Override
    public void onItemSelected(MainItem item) {
        Toast.makeText(this, "Selected " + item.getName().toString() + "! ", Toast.LENGTH_LONG).show();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_layout, FirstFragment.newInstance(item), "RageComicDetails")
                .addToBackStack(null)
                .commit();
    }
}
