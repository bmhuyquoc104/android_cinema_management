package com.example.android_cinema_management.UserManagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;

import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.Gravity;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cinema_management.Model.User;
import com.example.android_cinema_management.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.type.TimeOfDayOrBuilder;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class UserProfile extends AppCompatActivity {
    //Declare imageview
    ImageView editName, editEmail, editDateOfBirth, editPhone, editAddress, editPassword, back, avatar;
    //Declare textview
    TextView name, email, dateOfBirth, address, phone;
    //Declare edit text and text layout
    TextInputEditText password;
    //Declare button
    Button saveChanges, reset;
    //Declare FirebaseFireStore FirebaseAuth String
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //Binding with XML values
        editName = findViewById(R.id.user_profile_edit_name_iv);
        editEmail = findViewById(R.id.user_profile_edit_email_iv);
        editDateOfBirth = findViewById(R.id.user_profile_edit_date_of_birth_iv);
        editPhone = findViewById(R.id.user_profile_edit_phone_iv);
        editAddress = findViewById(R.id.user_profile_edit_address_iv);
        editPassword = findViewById(R.id.user_profile_edit_password_iv);
        name = findViewById(R.id.user_profile_fullName_tv);
        email = findViewById(R.id.user_profile_email_tv);
        dateOfBirth = findViewById(R.id.user_profile_date_of_birth_tv);
        address = findViewById(R.id.user_profile_address_tv);
        phone = findViewById(R.id.user_profile_phone_tv);
        password = findViewById(R.id.user_profile_password_tie);
        back = findViewById(R.id.user_profile_back_iv);
        saveChanges = findViewById(R.id.user_profile_save_button);
        reset = findViewById(R.id.user_profile_reset_button);
        avatar = findViewById(R.id.user_profile_avatar_iv);
        /*
         * Function to open edit dialog
         * */
        editName.setOnClickListener(View -> {
            // Set edit text hint and drawable image for name
            openEditDialog("Enter your new full name", R.drawable.ic_baseline_person_24, name);

        });
        /*
         * Function to open edit dialog
         * */
        editEmail.setOnClickListener(View -> {
            // Set edit text hint and drawable image for name
            openEditDialog("Enter your new email", R.drawable.ic_baseline_email_24, email);
        });

        /*
         * Function to open edit dialog
         * */
        editPhone.setOnClickListener(View -> {
            // Set edit text hint and drawable image for phone
            openEditDialog("Enter your new phone number", R.drawable.ic_baseline_phone_android_24, phone);
        });

        /*
         * Function to open edit dialog
         * */
        editAddress.setOnClickListener(View -> {
            // Set edit text hint and drawable image for address
            openEditDialog("Enter your new address", R.drawable.ic_baseline_home_24, address);
        });

        avatar.setOnClickListener(View -> {
            openImageDialog("Enter your avatar https://link", R.drawable.ic_baseline_image_24, avatar);
        });

        /*
         * Function to open edit dialog
         * */
        editDateOfBirth.setOnClickListener(View -> {
            // Set edit text hint and drawable image for date of birth
            openEditDialog("Enter your new date of birth", R.drawable.ic_baseline_cake_24, dateOfBirth);
        });

        /*
         * Function to open edit dialog
         * */
        editPassword.setOnClickListener(View -> {
            // Set edit text hint and drawable image for password
            openEditDialog("Enter your new password", R.drawable.ic_baseline_lock_24, password);
        });

        /*
         *Function to close activity
         * */
        back.setOnClickListener(View -> {
            finish();
        });

        //Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        //Function pull user's information
        userId = firebaseAuth.getCurrentUser().getUid();

        saveChanges.setOnClickListener(View -> {
            //put all user's information into userInformation hashMap
            HashMap<String, String> userInformation = new HashMap<>();
            userInformation.put("email", email.getText().toString());
            userInformation.put("fullName", name.getText().toString());
            userInformation.put("password", Objects.requireNonNull(password.getText()).toString());
            userInformation.put("address", address.getText().toString());
            userInformation.put("phone", phone.getText().toString());
            userInformation.put("dateOfBirth", dateOfBirth.getText().toString());
            userInformation.put("id", UUID.randomUUID().toString());
            userInformation.put("role", "regular");
            userInformation.put("status", "active");
            //put userInformation into firebaseFireStore
            db.collection("Users").document(userId)
                    .set(userInformation)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(UserProfile.this, "Successfully update user's information", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(UserProfile.this, "Fail to update user's information", Toast.LENGTH_SHORT).show();
                    });
        });

        //get user's information and put into textEdits
        DocumentReference documentReference = db.collection("Users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                name.setText(value.getString("fullName"));
                password.setText(value.getString("password"));
                phone.setText(value.getString("phone"));
                email.setText(value.getString("email"));
                dateOfBirth.setText(value.getString("dateOfBirth"));
                address.setText(value.getString("address"));
            }
        });

        /*
         *Function to reset all text view to default
         * */
        reset.setOnClickListener(view -> {
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    name.setText(value.getString("fullName"));
                    password.setText(value.getString("password"));
                    phone.setText(value.getString("phone"));
                    email.setText(value.getString("email"));
                    dateOfBirth.setText(value.getString("dateOfBirth"));
                    address.setText(value.getString("address"));
                }
            });
            Picasso.get().load("https://images.pexels.com/photos/1200450/pexels-photo-1200450.jpeg?cs=srgb&dl=pexels-louis-1200450.jpg&fm=jpg").into(avatar);
        });

    }

    /**
     * Function to open edit dialog when click the edit image view
     */
    private void openEditDialog(String editTextHint, int iconId, TextView textView) {
        // Initialize new dialog
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Set content for dialog
        dialog.setContentView(R.layout.open_user_profile_edit_dialog);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        // set the dialog to top
        windowAttributes.gravity = Gravity.TOP;
        window.setAttributes(windowAttributes);
        // Disable cancel by clicking randomly on the screen
        dialog.setCancelable(false);

        dialog.show();
        EditText change = dialog.findViewById(R.id.user_profile_dialog_et);
        // Disable the edittext
        change.setEnabled(false);
        TextInputEditText confirmPassword = dialog.findViewById(R.id.user_profile_password_tie);


        /*
         *Function to check user password before changing any information in their profile
         * */
        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // Detect the user input on text changed
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String inputConfirmPassword = Objects.requireNonNull(Objects.requireNonNull(confirmPassword.getText()).toString());
                ;
                String currentUserPassword = Objects.requireNonNull(password.getText()).toString();
                System.out.println(currentUserPassword + "test1");
                System.out.println(inputConfirmPassword + "test2");
                // Check if user input confirmPassword match their account's password
                //Enable the editttext
                change.setEnabled(true);

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        change.setHint(editTextHint);
        String hint = change.getHint().toString();
        @SuppressLint("UseCompatLoadingForDrawables") Drawable img = this.getResources().getDrawable(iconId);
        change.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);

        if (hint.equals("Enter your new date of birth")) {
            // Set the edit text is not editable
            change.setFocusable(false);
            change.setClickable(false);
            change.setOnClickListener(View -> {
                // Create instance of calendar
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DATE);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                // Create dialog when user choose pick date
                DatePickerDialog datePicker = new DatePickerDialog(this, android.R.style.Theme_Holo_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.set(Calendar.YEAR, year);
                        calendar1.set(Calendar.MONTH, month);
                        calendar1.set(Calendar.DATE, date);
                        // Set format of date
                        CharSequence charSequence = DateFormat.format("dd-MM-yyyy", calendar1);
                        change.setText(charSequence);
                    }
                }, year, month, day);
                // Get the latest date, ignore the previous date
                datePicker.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                datePicker.show();
            });
        }

        ImageButton close = dialog.findViewById(R.id.open_user_profile_close_button);
        /*
         * Function to dismiss the dialog
         * */
        close.setOnClickListener(View -> {
            dialog.dismiss();
        });

        Button save = dialog.findViewById(R.id.user_profile_dialog_save_button);
        /*
         *Function to save the changes
         * */
        save.setOnClickListener(View -> {
            dialog.dismiss();
            String newChange = change.getText().toString();
            textView.setText(newChange);
        });
    }


    /**
     * Function to open image dialog when click the avatar
     */
    private void openImageDialog(String editTextHint, int iconId, ImageView iv) {
        // Initialize new dialog
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Set content for dialog
        dialog.setContentView(R.layout.open_user_profile_edit_dialog);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        // set the dialog to top
        windowAttributes.gravity = Gravity.TOP;
        window.setAttributes(windowAttributes);
        // Disable cancel by clicking randomly on the screen
        dialog.setCancelable(false);

        dialog.show();


        EditText change = dialog.findViewById(R.id.user_profile_dialog_et);
        change.setHint(editTextHint);
        String hint = change.getHint().toString();
        @SuppressLint("UseCompatLoadingForDrawables") Drawable img = this.getResources().getDrawable(iconId);
        change.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);


        ImageView close = dialog.findViewById(R.id.open_user_profile_close_button);
        /*
         * Function to dismiss the dialog
         * */
        close.setOnClickListener(View -> {
            dialog.dismiss();
        });

        Button save = dialog.findViewById(R.id.user_profile_dialog_save_button);
        /*
         *Function to save the changes
         * */
        save.setOnClickListener(View -> {
            dialog.dismiss();
            String newChange = change.getText().toString();
            Picasso.get().load(newChange).into(iv);
        });
    }


}