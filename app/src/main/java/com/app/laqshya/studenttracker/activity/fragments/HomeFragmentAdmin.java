package com.app.laqshya.studenttracker.activity.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.laqshya.studenttracker.R;
import com.app.laqshya.studenttracker.databinding.FragmentHomeAdminBinding;

import timber.log.Timber;

public class HomeFragmentAdmin extends Fragment implements View.OnClickListener {

    FragmentHomeAdminBinding fragmentHomeAdminBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_home_admin, container, false);
        fragmentHomeAdminBinding = FragmentHomeAdminBinding.inflate(inflater, container, false);

        fragmentHomeAdminBinding.manageStudents.setOnClickListener(this);
        fragmentHomeAdminBinding.addStudents.setOnClickListener(this);
        fragmentHomeAdminBinding.scheduleBatches.setOnClickListener(this);
        fragmentHomeAdminBinding.broadcastBatches.setOnClickListener(this);
        Toolbar toolbar=getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Home");

        return fragmentHomeAdminBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.manage_students:
                fragmentTransact(new ManageStudentFragment());
                break;
            case R.id.add_students:
                fragmentTransact(new AddStudentFragment());
                break;
            case R.id.schedule_batches:
                fragmentTransact(new ScheduleBatchesFragment());
                break;

        }
    }

    private void fragmentTransact(Fragment fragment) {
        Timber.d("Fragment Called");
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction;
        if (fragmentManager != null) {
            fragmentTransaction = fragmentManager.beginTransaction()
                    .replace(R.id.frame, fragment).addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
