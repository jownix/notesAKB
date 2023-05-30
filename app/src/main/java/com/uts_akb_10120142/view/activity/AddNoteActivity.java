package com.uts_akb_10120142.view.activity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.uts_akb_10120142.NoteInterface;
import com.uts_akb_10120142.R;
import com.uts_akb_10120142.database.DatabaseHelper;
import com.uts_akb_10120142.model.Note;

import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    ImageButton button;
    EditText editTitle;
    EditText editcategory;
    EditText editDesc;
    Button addButton;
    Button deleteButton;
    TextView titleAdd;

    private NoteInterface noteInterface;
    Note note = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        getSupportActionBar().hide();
        note = (Note) getIntent().getSerializableExtra("Note");
        button = findViewById(R.id.back);
        editTitle = findViewById(R.id.title);
        editcategory = findViewById(R.id.txt_category);
        editDesc = findViewById(R.id.txt_desc);
        addButton = findViewById(R.id.buttonAdd);
        deleteButton = findViewById(R.id.buttonDelete);
        titleAdd = findViewById(R.id.txt_add);
        noteInterface = new DatabaseHelper(this);

        button.setOnClickListener(v -> {
            finish();
        });

        if (note == null){
            deleteButton.setVisibility(View.GONE);

            addButton.setOnClickListener(v -> {
                if (editTitle.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Tittle is required!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (editcategory.getText().toString().isEmpty()){
                    Toast.makeText(this,"Category is required!",Toast.LENGTH_SHORT).show();
                }
                if (editDesc.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Description is required!", Toast.LENGTH_SHORT).show();
                }
                Date d = new Date();
                CharSequence date = DateFormat.format("EEEE, d MMM yyyy HH:mm", d.getTime());
                Note n = new Note(
                        d.getTime() + "",
                        editTitle.getText().toString(),
                        editcategory.getText().toString(),
                        editDesc.getText().toString() ,
                         date + ""
                );

                noteInterface.create(n);
                finish();
                Toast.makeText(this, "Notes added succesfully", Toast.LENGTH_SHORT).show();
            });
        } else {
            editTitle.setText(note.getTitle());
            editcategory.setText(note.getCategory());
            editDesc.setText(note.getDesc());

            deleteButton.setVisibility(View.VISIBLE);
            titleAdd.setText("Edit Notes");

            addButton.setOnClickListener(v -> {
                if (editTitle.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Tittle is required!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (editcategory.getText().toString().isEmpty()){
                    Toast.makeText(this,"Category is required!",Toast.LENGTH_SHORT).show();
                }
                if (editDesc.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Description is required!", Toast.LENGTH_SHORT).show();
                }

                Date d = new Date();
                CharSequence date = DateFormat.format("EEEE, d MMMM yyyy HH:mm",d.getTime());

                note.setTitle(editTitle.getText().toString());
                note.setCategory(editcategory.getText().toString());
                note.setDesc(editDesc.getText().toString());
                note.setDate("terakhir diedit " + date + "");
                noteInterface.update(note);
                finish();
                Toast.makeText(this, "Notes succesfully edited", Toast.LENGTH_SHORT).show();
            });
        }

        deleteButton.setOnClickListener(v-> {
            noteInterface.delete(note.getId());
            finish();
            Toast.makeText(this, "Notes succesfully removed", Toast.LENGTH_SHORT).show();
        });
    }
}


// 10120148_AriyandiJulianPratama_IF4
