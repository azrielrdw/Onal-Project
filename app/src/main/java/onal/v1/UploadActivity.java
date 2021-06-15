package onal.v1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;

public class UploadActivity extends AppCompatActivity{
    private int PICK_IMAGE_REQUEST = 1;
    ImageView targetImage;
    EditText textTargetUri;
    TextView textTargetPath;
    Button buttonChoose;
    Button buttonUpload;

    private int GALLERY = 1, CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);

        targetImage = (ImageView)findViewById(R.id.upload_image);
        textTargetUri = (EditText)findViewById(R.id.upload_name);
        textTargetPath = (TextView)findViewById(R.id.upload_path);
        buttonChoose = (Button)findViewById(R.id.button_select_file);
        buttonUpload = (Button)findViewById(R.id.button_upload);

        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
    }

    private void reset(){
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.upload);
            targetImage.setImageBitmap(bitmap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        textTargetUri.setHint(R.string.file_name);
        textTargetUri.setText(null);
        textTargetPath.setText(null);
    }

    private void chooseFile(){
        Intent intent = new Intent();
        intent.setType("*/*");
        String[] mimetypes = {"image/*", "audio/*"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);
    }

    private void uploadFile(){
        Content content;
        try {
            content = new Content(-1, textTargetPath.getText().toString(), textTargetUri.getText().toString());
        } catch (Exception e){
            content = new Content(-1, "error", "error");
        }

        DatabaseHelper DatabaseHelper = new DatabaseHelper(this);

        boolean uploadSuccess = DatabaseHelper.addContent(content);

        reset();

        if (uploadSuccess){
            Toast.makeText(this, "Upload Success", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Upload Fail", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK){
            Uri targetUri = data.getData();
            String filePath = UriUtils.getPathFromUri(this, targetUri);
            textTargetPath.setText(filePath);

            if(!(filePath.contains(".jpg") || filePath.contains(".png"))){
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.music);
                    targetImage.setImageBitmap(bitmap);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else{
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                    targetImage.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
