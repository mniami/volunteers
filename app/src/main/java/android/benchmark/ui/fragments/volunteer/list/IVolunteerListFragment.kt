package android.benchmark.ui.fragments.volunteer.list

import android.benchmark.domain.Volunteer

interface IVolunteerListFragment {
    fun showVolunteers(volunteers: List<Volunteer>)
}