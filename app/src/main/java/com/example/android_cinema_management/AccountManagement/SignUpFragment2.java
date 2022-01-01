package com.example.android_cinema_management.AccountManagement;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.android_cinema_management.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Objects;

import javax.xml.validation.Validator;


public class SignUpFragment2 extends Fragment {
    //Declare edit text, button
    TextInputLayout dateOfBirth, address, phone, gender;
    Button nextPage;
    EditText inputDateOfBirth;
    // Declare string and int to store value
    String fullName, password, email, confirmPassword;
    int day, month, year;
    //Declare string and array of string for gender
    String[] genderArray = {"Male", "Female"};
    String genderChosen;
    //Declare array adapter
    ArrayAdapter<String> adapterItems;
    // Declare auto complete text view
    AutoCompleteTextView autoCompleteTextView;

    public SignUpFragment2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up2, container, false);

        address = view.findViewById(R.id.frag2SignUp_text_layout_address);
        dateOfBirth = view.findViewById(R.id.frag2SignUp_text_layout_dateOfBirth);
        inputDateOfBirth = view.findViewById(R.id.frag2SignUp_dateOfBirth);
        gender = view.findViewById(R.id.frag2SignUp_text_layout_gender);
        phone = view.findViewById(R.id.frag2SignUp_text_layout_phone);
        nextPage = view.findViewById(R.id.buttonContinue);
        autoCompleteTextView = view.findViewById(R.id.frag2SignUp_gender);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                genderChosen = parent.getItemAtPosition(position).toString();
            }
        });
        // Ask user to pick the date by date picker
        inputDateOfBirth.setOnClickListener(View -> {
            // Create instance of calendar
            Calendar calendar = Calendar.getInstance();
            day = calendar.get(Calendar.DATE);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
            // Create dialog when user choose pick date
            DatePickerDialog datePicker = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.set(Calendar.YEAR, year);
                    calendar1.set(Calendar.MONTH, month);
                    calendar1.set(Calendar.DATE, date);
                    // Set format of date
                    CharSequence charSequence = DateFormat.format("dd-MM-yyyy", calendar1);
                    inputDateOfBirth.setText(charSequence);
                }
            }, year, month, day);
            // Get the latest date, ignore the previous date
            datePicker.getDatePicker().setMaxDate(calendar.getTimeInMillis());
            datePicker.show();
        });

        adapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_selector_list, genderArray);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                genderChosen = parent.getItemAtPosition(position).toString();
            }
        });


        // Receive the bundle from other fragments
        Bundle bundle = this.getArguments();
        System.out.println(bundle);
        assert bundle != null;
        fullName = bundle.getString("fullName");
        email = bundle.getString("email");
        password = bundle.getString("password");
        confirmPassword = bundle.getString("confirmPassword");
        System.out.println("fullName: " + fullName + "email: " + email + "password: " + password + "confirmpassword: "
                + confirmPassword);


        nextPage.setOnClickListener(View -> {
            if (isValidDateOfBirth() & isValidAddress() & isValidPhone() & isValidGender()) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("address", Objects.requireNonNull(address.getEditText()).getText().toString());
                bundle2.putString("dateOfBirth", inputDateOfBirth.getText().toString());
                bundle2. putString("gender", Objects.requireNonNull(gender.getEditText()).getText().toString());
                bundle2.putString("fullName", fullName);
                bundle2.putString("email", email);
                bundle2.putString("password", password);
                bundle2.putString("confirmPassword", confirmPassword);
                bundle2.putString("phone", Objects.requireNonNull(phone.getEditText()).getText().toString());
                SignUpFragment3 fragment3 = new SignUpFragment3();
                fragment3.setArguments(bundle2);
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction transaction =
                        fm.beginTransaction();
                transaction.replace(R.id.signUp_frameLayout, fragment3).addToBackStack("fragment2").commit();
            }
        });
        return view;
    }

    private boolean isValidDateOfBirth() {
        if (Objects.requireNonNull(dateOfBirth.getEditText()).getText().toString().isEmpty()) {
            // Set error for dateOfBirth textInputLayout
            dateOfBirth.setError("Field can not be empty");
            return false;
        } else {
            // Set full dateOfBirth error
            dateOfBirth.setError(null);
            dateOfBirth.setErrorEnabled(false);
            return true;
        }
    }

    ;

    private boolean isValidPhone() {
//        String checkPhone = "^(0|\\\\+84)(\\\\s|\\\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\\\d)(\\\\s|\\\\.)?(\\\\d{3})(\\\\s|\\\\.)?(\\\\d{3})$";
        if (Objects.requireNonNull(phone.getEditText()).getText().toString().isEmpty()) {
            phone.setError("Field can not be empty");
            return false;
        }
        // Check if phone is in the correct format
//        else if (!Objects.requireNonNull(phone.getEditText()).getText().toString().matches(checkPhone)) {
//            // Set error for phone textInputLayout
//            phone.setError("Invalid Phone number!");
//            return false;
//        }
        else {
            // Set phone error
            phone.setError(null);
            phone.setErrorEnabled(false);
            return true;
        }
    }

    ;

    private boolean isValidGender() {
        if (Objects.requireNonNull(gender.getEditText()).getText().toString().isEmpty()) {
            // Set error for gender textInputLayout
            gender.setError("Field can not be empty");
            return false;
        } else {
            // Set full gender error
            gender.setError(null);
            gender.setErrorEnabled(false);
            return true;
        }
    }

    ;

    private boolean isValidAddress() {
        if (Objects.requireNonNull(address.getEditText()).getText().toString().isEmpty()) {
            // Set error for address textInputLayout
            address.setError("Field can not be empty");
            return false;
        } else {
            // Set full address error
            address.setError(null);
            address.setErrorEnabled(false);
            return true;
        }
    }

    ;


}