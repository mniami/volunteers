package android.benchmark.ui.fragments.volunteer.details

import android.benchmark.domain.Volunteer

interface IVolunteerDetailsFragment {
    fun onVolunteerLoaded(volunteer : Volunteer)
}