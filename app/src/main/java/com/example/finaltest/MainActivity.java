package com.example.finaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText name, email , description;
    Button select, register;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.texts, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        description = findViewById(R.id.description);
        spinner = findViewById(R.id.spinner);
        select = findViewById(R.id.select);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserEntity userEntity = new UserEntity();
                userEntity.setName(name.getText().toString());
                userEntity.setEmail(email.getText().toString());
                userEntity.setDescription(description.getText().toString());
                if (validateInput(userEntity))
                {
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.userDao();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDao.feedbackUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "User Register", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }).start();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Fill all fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean validateInput(UserEntity userEntity) {
        if (userEntity.getName().isEmpty() || userEntity.getEmail().isEmpty() || userEntity.getDescription().isEmpty())
        {
            return false;
        }

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}