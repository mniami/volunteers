package android.benchmark.ui.fragments.genericlist

import android.benchmark.R
import android.benchmark.helpers.Services
import android.benchmark.helpers.dataservices.datasource.DataSourceId
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.volunteer_details_projects.*
import java.io.Serializable

class GenericListFragmentImpl : BaseFragment<GenericPresenter>(), GenericListFragment {
    val dataService = Services.instance.dataService
    val eventBusContainer = Services.instance.eventBusContainer
    var eventClickId : Serializable? = null

    init {
        presenter = GenericPresenter(this)
        configuration = FragmentConfiguration.withLayout(R.layout.volunteer_details_projects).showBackArrow().create()
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)


        val dataSourceId = args?.get("dataSourceId") as DataSourceId
        eventClickId = args?.get("eventClickId") as Serializable

        dataSourceId?.let {
            val dataSource = dataService.getDataSource(it)

            if (dataSource.isGenericListDataSource()) {
                presenter?.genericListProvider = dataSource.asGenericListDataSource<GenericItem>()
            }
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView?.let { rv ->
            rv.setHasFixedSize(true)
            rv.layoutManager = LinearLayoutManager(context)
            presenter?.let {
                it.genericListProvider?.let {
                    rv.adapter = GenericListAdapter(it) { item ->
                        eventClickId?.let { ds ->
                            val eventBus = eventBusContainer.get<GenericItemClickEvent>(ds)
                            eventBus.post(GenericItemClickEvent(item))
                        }
                    }
                }
            }
        }
    }
}

