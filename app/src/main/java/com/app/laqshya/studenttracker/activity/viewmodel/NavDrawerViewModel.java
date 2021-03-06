package com.app.laqshya.studenttracker.activity.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.fragment.app.Fragment;

import com.app.laqshya.studenttracker.activity.fragments.AboutDevelopersFragment;
import com.app.laqshya.studenttracker.activity.fragments.AddCounsellorFragment;
import com.app.laqshya.studenttracker.activity.fragments.AddFacultyFragment;
import com.app.laqshya.studenttracker.activity.fragments.AddStudentFragment;
import com.app.laqshya.studenttracker.activity.fragments.AddSyllabusFragment;
import com.app.laqshya.studenttracker.activity.fragments.AttendanceFragment;
import com.app.laqshya.studenttracker.activity.fragments.BroadcastFragment;
import com.app.laqshya.studenttracker.activity.fragments.CompletionBatchesFragment;
import com.app.laqshya.studenttracker.activity.fragments.ContactFragment;
import com.app.laqshya.studenttracker.activity.fragments.DeletedBatchesFragment;
import com.app.laqshya.studenttracker.activity.fragments.FacultyBatchCardsFragment;
import com.app.laqshya.studenttracker.activity.fragments.FeedbackFragment;
import com.app.laqshya.studenttracker.activity.fragments.FeesStatusFragment;
import com.app.laqshya.studenttracker.activity.fragments.HomeFragmentAdmin;
import com.app.laqshya.studenttracker.activity.fragments.HomeFragmentCounsellor;
import com.app.laqshya.studenttracker.activity.fragments.HomeFragmentFaculty;
import com.app.laqshya.studenttracker.activity.fragments.HomeFragmentStudent;
import com.app.laqshya.studenttracker.activity.fragments.ManageStudentFragment;
import com.app.laqshya.studenttracker.activity.fragments.NotificationsFragment;
import com.app.laqshya.studenttracker.activity.fragments.PaymentFragment;
import com.app.laqshya.studenttracker.activity.fragments.PrivacyPolicyFragment;
import com.app.laqshya.studenttracker.activity.fragments.Refer_Friendfragment;
import com.app.laqshya.studenttracker.activity.fragments.ScheduleBatchesFragment;
import com.app.laqshya.studenttracker.activity.fragments.StudentAttendanceByFacultyFragment;
import com.app.laqshya.studenttracker.activity.fragments.StudentReceivedNotificationFragment;
import com.app.laqshya.studenttracker.activity.fragments.StudyMaterialFragment;
import com.app.laqshya.studenttracker.activity.fragments.SyllabusFragment;
import com.app.laqshya.studenttracker.activity.fragments.notifications.SameBatchFacultyFragment;
import com.app.laqshya.studenttracker.activity.fragments.notifications.SingleStudentNotificationFragment;
import com.app.laqshya.studenttracker.activity.model.CourseModuleList;
import com.app.laqshya.studenttracker.activity.model.CoursesStudent;
import com.app.laqshya.studenttracker.activity.repository.RegistrationRepository;

import java.util.List;

import timber.log.Timber;

public class NavDrawerViewModel extends ViewModel {
    public MutableLiveData<String> fragmentTitle = new MutableLiveData<>();
    public MutableLiveData<Boolean> isProgress = new MutableLiveData<>();
    public MutableLiveData<Integer> noOfInstallments = new MutableLiveData<>();
    public MutableLiveData<Integer> downPayment = new MutableLiveData<>();
    public MutableLiveData<Integer> totalFees = new MutableLiveData<>();
    private RegistrationRepository registrationRepository;

    public NavDrawerViewModel(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public Fragment getAdminFragment(int navindex) {
        switch (navindex) {
            case 0:
                // home
                fragmentTitle.setValue("Home");
                return new HomeFragmentAdmin();
            case 1:
                fragmentTitle.setValue("Add Counsellor");
                return new AddCounsellorFragment();

            case 2:
                fragmentTitle.setValue("Add Faculty");
                // performance
                return new AddFacultyFragment();
            case 3:
                fragmentTitle.setValue("Add Student");
                // attendance fragment
                return new AddStudentFragment();
            case 4:
                fragmentTitle.setValue("Add Syllabus");
                return new AddSyllabusFragment();
            case 5:
                fragmentTitle.setValue("Manage Students");
                // notifications fragment
                return new ManageStudentFragment();
            case 6:
                fragmentTitle.setValue("Schedules");
                return new ScheduleBatchesFragment();
            case 7:
                fragmentTitle.setValue("Completed Batches");
                return new CompletionBatchesFragment();
            case 8:
                fragmentTitle.setValue("Deleted Batches");
                return new DeletedBatchesFragment();
            case 9:
                fragmentTitle.setValue("Notifications");
                return new NotificationsFragment();

            case 10:
                fragmentTitle.setValue("About Developers");
                return new AboutDevelopersFragment();
            case 11:
                fragmentTitle.setValue("Privacy Policy");
                // privacypolicy fragment
                return new PrivacyPolicyFragment();
            default:
                fragmentTitle.setValue("Home");
                return new HomeFragmentAdmin();
        }
    }

    public LiveData<String> registerCounsellor(String email, String centerMobile, String counsellorMobile
            , String centername, String counsellorname) {
        isProgress.setValue(true);
        return registrationRepository.registerCounsellor(email, centerMobile, counsellorMobile
                , centername, counsellorname);
    }

    public LiveData<List<String>> getCenterList() {
        return registrationRepository.getCenterList();
    }

    public LiveData<List<String>> getCourseList() {
        return registrationRepository.getCourse();
    }

    public LiveData<String> registerFaculty(String username, String phoneNumber, String email, String courses) {
        isProgress.setValue(true);
        return registrationRepository.registerFaculty(email, phoneNumber, username, courses);
    }

    public LiveData<List<CourseModuleList>> getCourseModule(String course) {
        return registrationRepository.getCourseModule(course);
    }

    public void onInstallmentTextChanged(CharSequence noOfInstallments) {
        int noInstallment = 0;
        Timber.d("Installment Count is %d", noInstallment);
        if (!noOfInstallments.toString().trim().isEmpty()) {
            try {
                noInstallment = Integer.parseInt(String.valueOf(noOfInstallments));
            } catch (NumberFormatException exception) {
                Timber.d("No of installments too large");
            }
        }
        this.noOfInstallments.setValue(noInstallment);
    }

    public void onFeesChanged(CharSequence feesAmnt) {

        Timber.d("Fees Amount is %s", feesAmnt);
        int fees = 0;
        if (!feesAmnt.toString().trim().isEmpty()) {
            try {
                fees = Integer.parseInt(String.valueOf(feesAmnt));
            } catch (NumberFormatException exception) {
                Timber.d("Fees Amount too large");
            }
        }
        this.totalFees.setValue(fees);
    }

    public void onDownPaymentChanged(CharSequence dpAmnt) {
        int dpAmntVal = 0;

        Timber.d("DP` Amount is %s", dpAmnt);
        if (!noOfInstallments.toString().trim().isEmpty()) {
            try {
                dpAmntVal = Integer.parseInt(String.valueOf(dpAmnt));
            } catch (NumberFormatException exception) {
                Timber.d("No of downpayments too large");
            }
        }
        this.downPayment.setValue(dpAmntVal);
    }

    public LiveData<String> registerStudent(String name, String phone, String email) {
        return registrationRepository.registerStudent(name, phone, email);
    }

    public Fragment getFacultyFragment(int navindex) {
        switch (navindex) {
            case 0:
                fragmentTitle.setValue("Home");
                return new HomeFragmentFaculty();
            case 1:
                fragmentTitle.setValue("Attendance");
                return new FacultyBatchCardsFragment();
            case  2:
                fragmentTitle.setValue("Add Syllabus Books");
                return new AddSyllabusFragment();
            case 3:
                fragmentTitle.setValue("Notification");
                return new NotificationsFragment();
            case 4:
                fragmentTitle.setValue("Broadcast");
                return new SameBatchFacultyFragment();
            case 5:
                fragmentTitle.setValue("About Developers");
                return new AboutDevelopersFragment();
            case 6:
                fragmentTitle.setValue("Privacy Policy");
                return new PrivacyPolicyFragment();
            default:
                fragmentTitle.setValue("Home");
                return new HomeFragmentFaculty();
        }
    }

    public Fragment getCounsellorFragment(int navindex) {
        switch (navindex) {
            case 0:
                fragmentTitle.setValue("Home");
                return new HomeFragmentCounsellor();
            case 1:
                fragmentTitle.setValue("Add Faculty");
                return new AddFacultyFragment();
            case 2:
                fragmentTitle.setValue("Add Student");
                return new AddStudentFragment();
            case 3:
                fragmentTitle.setValue("Manage Student");
                return new ManageStudentFragment();
            case 4:
                fragmentTitle.setValue("Attendance");
                return new AttendanceFragment();
            case 5:
                fragmentTitle.setValue("Completed Batches");
                return new CompletionBatchesFragment();
            case 6:
                fragmentTitle.setValue("Deleted Batches");
                return new DeletedBatchesFragment();
//            case 7:
//                fragmentTitle.setValue("Notification");
//                return new NotificationsFragment();
            case 8:
                fragmentTitle.setValue("Broadcast");
                return new SingleStudentNotificationFragment();
            case 9:
                fragmentTitle.setValue("About Developers");
                return new AboutDevelopersFragment();
            case 10:
                fragmentTitle.setValue("Privacy Policy");
                return new PrivacyPolicyFragment();
            default:
                fragmentTitle.setValue("Home");
                return new HomeFragmentCounsellor();
        }
    }

    public Fragment getStudentFragment(int navindex) {
        switch (navindex) {
            case 0:
                fragmentTitle.setValue("Home");
                return new HomeFragmentStudent();
            case 1:
                //syllabus
                fragmentTitle.setValue("SyllabusList");
                return new SyllabusFragment();
            case 2:
                fragmentTitle.postValue("Read Notes");
                return new StudyMaterialFragment();
            case 3:
                fragmentTitle.setValue("Notification");
                // notifications fragment
                return new StudentReceivedNotificationFragment();
            case 4:
                // broadcast fragment
                fragmentTitle.setValue("Refer Friend");
                return new Refer_Friendfragment();
            case 5:
                fragmentTitle.setValue("Feedback");
                return new FeedbackFragment();
            case 6:
                fragmentTitle.setValue("Payment");
                return new PaymentFragment();
            case 7:
                //contactadmin
                fragmentTitle.setValue("Contact Us");
                return new ContactFragment();
            case 8:
                fragmentTitle.setValue("About Developers");
                // settings fragment
                return new AboutDevelopersFragment();
            case 9:
                fragmentTitle.setValue("Privacy Policy");
                //  privacypolicy fragment
                return new PrivacyPolicyFragment();
            default:
                fragmentTitle.setValue("Home");
                return new HomeFragmentStudent();
        }

    }

    public LiveData<String> registerCourses(CoursesStudent coursesStudent) {
        return registrationRepository.registerCourse(coursesStudent);

    }
}