package pl.wsb.students.android.introduction.app.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import pl.wsb.students.android.introduction.app.R;

public class FirstFragment extends Fragment {
    private OnCalculationMadeListener onCalculationMadeListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.first_fragment, parent, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnCalculate = view.findViewById(R.id.btnCalculate);
        if (btnCalculate != null) {
            btnCalculate.setOnClickListener(v -> {
                handleBtnClick(view);
            });
        } //if
    }
    private void handleBtnClick(@NonNull View view) {
        EditText edtCalculation = view.findViewById(R.id.edtCalculation);
        if (edtCalculation == null) {
            return;
        } //if
        if (TextUtils.isEmpty(edtCalculation.getText())) {
            Toast
                    .makeText(
                            getContext(),
                            getString(R.string.calculation__no_number_provided),
                            Toast.LENGTH_SHORT
                    )
                    .show();
            return;
        } //if
        int providedNumber = Integer.parseInt(edtCalculation.getText().toString());
        int result = providedNumber * providedNumber;
        if (onCalculationMadeListener != null) {
            onCalculationMadeListener.onCalculationMade(result);
        } //if
    }
    public void setOnCalculationMadeListener(OnCalculationMadeListener
                                                     onCalculationMadeListener) {
        this.onCalculationMadeListener = onCalculationMadeListener;
    }
    public interface OnCalculationMadeListener {
        void onCalculationMade(int result);
    }
}