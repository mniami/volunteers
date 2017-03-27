package android.benchmark.ui.fragments.volunteer

import android.benchmark.domain.Volunteer
import android.benchmark.ui.fragments.base.IFragmentContainer

interface IVolunteerListFragment : IFragmentContainer {
    fun showVolunteers(volunteers: List<Volunteer>)
}