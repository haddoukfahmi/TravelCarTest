package com.travelcar_test.ui.car_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.travelcar_test.R
import com.travelcar_test.adapter.CarsAdapater
import com.travelcar_test.data.model.Cars
import kotlinx.android.synthetic.main.fragment_carslist.*


class CarsListFragment : Fragment() {

    lateinit var carsListViewModel: CarsListViewModel
    var adapter = CarsAdapater()

    companion object {
        fun newInstance(): CarsListFragment {
            return CarsListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        carsListViewModel =
            activity!!.run { ViewModelProviders.of(this).get(CarsListViewModel::class.java) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater!!.inflate(R.layout.fragment_carslist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carsListViewModel.getCars()
        carsListViewModel.getCarsData()
            .observe(activity!!, Observer { list ->
                run {
                    setRecyclerView(list)
                }
            })
        carsListViewModel.getQueryLiveData().observe(activity!!, Observer {list ->
            list.run { adapter.setCars(list!!) }
        })

        initRecyclerView()

        searchBar.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
               carsListViewModel.getupdateCarList(newText)
                adapter.getSearchText(newText)
                return true
            }
        })

    }

    private fun initRecyclerView() {
        carlistview.layoutManager = LinearLayoutManager(context)
        carlistview.adapter = adapter

    }

    private fun setRecyclerView(list: List<Cars>) {
        adapter.setCars(list)
    }
}