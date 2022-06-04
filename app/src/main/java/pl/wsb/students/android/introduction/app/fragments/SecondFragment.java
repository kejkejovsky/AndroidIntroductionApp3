package pl.wsb.students.android.introduction.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import pl.wsb.students.android.introduction.app.R;

public class SecondFragment extends Fragment {
    private final int result;
    private OnCalculateAgainListener onCalculateAgainListener;
    public SecondFragment(int result) {
        super();
        this.result = result;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.second_fragment, parent, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView txtCalculationResult = view.findViewById(R.id.txtCalculationResult);
        if (txtCalculationResult == null) {
            return;
        } //if
        txtCalculationResult.setText(
                String.format(
                        "Wynik obliczeń: %s",
                        result
                )
        );
        Button btnCalculateAgain = view.findViewById(R.id.btnCalculateAgain);
        if (btnCalculateAgain != null) {
            btnCalculateAgain.setOnClickListener(v -> {
                handleBtnClick();
            });} //if
    }
    private void handleBtnClick() {
        if (onCalculateAgainListener != null) {
            onCalculateAgainListener.onCalculateAgain();
        } //if
    }
    public void setOnCalculateAgainListener(OnCalculateAgainListener
                                                    onCalculateAgainListener) {
        this.onCalculateAgainListener = onCalculateAgainListener;
    }
    public interface OnCalculateAgainListener {
        void onCalculateAgain();
    }
}
