package onal.v1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DownloadActivity extends AppCompatActivity {
    RadioGroup contentRadioGroup;
    RadioButton contentRadioButton;
    ListView contentList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);

        contentRadioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        contentRadioButton = (RadioButton)contentRadioGroup.findViewById(contentRadioGroup.getCheckedRadioButtonId());
        contentList = (ListView)findViewById(R.id.contentList);

        contentRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioButtonImage:
                        DatabaseHelper databaseHelper = new DatabaseHelper(DownloadActivity.this);
                        List<Content> content = databaseHelper.getWallpaperContent();

                        List<String> contentName = new ArrayList<String>();
                        for(Content element : content){
                            contentName.add(element.getName());
                        }

                        ArrayAdapter contentNameAdapter = new ArrayAdapter<String>(DownloadActivity.this,
                                android.R.layout.simple_list_item_1,
                                contentName);

                        contentList.setAdapter(contentNameAdapter);
                        break;

                    case R.id.radioButtonAudio:
                        databaseHelper = new DatabaseHelper(DownloadActivity.this);
                        content = databaseHelper.getRingtoneContent();

                        contentName = new ArrayList<String>();
                        for(Content element : content){
                            contentName.add(element.getName());
                        }

                        contentNameAdapter = new ArrayAdapter<String>(DownloadActivity.this,
                                android.R.layout.simple_list_item_1,
                                contentName);

                        contentList.setAdapter(contentNameAdapter);
                        break;
                }
            }
        });
    }


}
