package android.benchmark.ui.fragments.genericlist

import android.androidkotlinbenchmark.R
import android.benchmark.domain.Privilege
import android.benchmark.domain.User
import android.benchmark.helpers.Services
import android.benchmark.helpers.dataservices.datasource.DataSourceId
import android.benchmark.helpers.dataservices.datasource.ObservableDataSource
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.benchmark.ui.fragments.base.ToolbarConfiguration
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.generic_view.*
import java.io.Serializable
import java.util.*

class GenericListFragmentImpl : BaseFragment<GenericPresenter>(), GenericListFragment {
    private val dataSourceContainer = Services.instance.dataSourceContainer
    private val eventBusContainer = Services.instance.eventBusContainer
    private var eventClickId: Serializable? = null
    private val database = Services.instance.database
    private var currentUser = User()
    private var itemMap: GenericItemMap = EmptyGenericItemMap()
    private val mapperInstanceProvider = Services.instance.mapperInstanceProvider

    init {
        presenter = GenericPresenter(this)
        configuration = FragmentConfiguration.withLayout(R.layout.generic_view).showBackArrow().create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        loadArguments()
    }

    override fun onResume() {
        super.onResume()
        database.getCurrentUserAsync()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onNext = { user ->
                    currentUser = user

                    if (user.privilege == Privilege.ADMIN) {
                        actionButton?.visibility = View.VISIBLE
                    }
                    if (recyclerView?.adapter == null) {
                        refreshAdapter()
                    }
                })
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView?.let { rv ->
            rv.setHasFixedSize(true)
            rv.layoutManager = LinearLayoutManager(context)
        }
        actionButton?.setOnClickListener {
            if (currentUser?.privilege == Privilege.ADMIN) {
                itemMap.addItem()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(text: String?): Boolean {
                val genericAdapter = recyclerView?.adapter as GenericListAdapter?
                if (text != null) {
                    genericAdapter?.filter(text)
                }
                return true
            }

            override fun onQueryTextSubmit(text: String?): Boolean {
                return false
            }
        })
    }

    private fun refreshAdapter() {
        var genericItems = LinkedList<GenericItem<*>>()
        presenter?.items?.observeOn(AndroidSchedulers.mainThread())?.
                subscribeBy(
                        onNext = {
                            genericItems.add(it)
                        },
                        onError = {
                            Toast.makeText(this@GenericListFragmentImpl.context, "Ups", Toast.LENGTH_SHORT)
                        },
                        onComplete = {
                            val gi = genericItems
                            genericItems = LinkedList()
                            updateAdapter(gi)
                        })
    }

    private fun updateAdapter(list: List<GenericItem<*>>) {
        recyclerView?.adapter = GenericListAdapter(list) { item ->
            val lEventClickId = eventClickId
            if (item != null && lEventClickId != null) {
                val eventBus = eventBusContainer.get<GenericItemClickEvent>(lEventClickId)
                eventBus.post(GenericItemClickEvent(item))
            }
        }
        recyclerView?.invalidate()
    }

    private fun loadArguments() {
        val toolbarConfiguration = arguments?.get(GenericListFragment.TOOLBAR_CONFIGURATION)
        val dataSourceId = arguments?.get(GenericListFragment.DATA_SOURCE_ID)
        val mapperClassName = arguments?.get(GenericListFragment.MAPPER_CLASS_NAME)

        if (toolbarConfiguration is ToolbarConfiguration) {
            this.configuration.toolbar.titleResourceId = toolbarConfiguration.titleResourceId
            this.configuration.toolbar.showBackArrow = toolbarConfiguration.showBackArrow
        }

        if (dataSourceId is DataSourceId && mapperClassName is String) {
            val dataSource = dataSourceContainer.getDataSource(dataSourceId)

            if (dataSource != null && dataSource.isObservableDataSource() && dataSource is ObservableDataSource<*>) {
                if (mapperClassName != null) {
                    val mapperInstance = mapperInstanceProvider.get<GenericItemMap>(mapperClassName)
                    if (mapperInstance != null) {
                        itemMap = mapperInstance
                        presenter?.items = itemMap.map(dataSource.data.observable)
                    }
                } else {
                    val items = dataSource.data.observable as Observable<GenericItem<*>>?
                    if (items != null) {
                        presenter?.items = items
                    }
                }
            }
        }
    }
}

