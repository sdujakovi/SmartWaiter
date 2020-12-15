package com.example.smartwaiter.ui.guest.menu_guest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartwaiter.R
import com.example.smartwaiter.repository.Add_mealRepository
import kotlinx.android.synthetic.main.fragment_meni.*
import kotlinx.android.synthetic.main.fragment_meni.recycleViewMenu
import kotlinx.android.synthetic.main.fragment_meni_guest.*

class MenuGuestFragment : Fragment(R.layout.fragment_meni_guest) {
    private lateinit var lokal: String

    private lateinit var viewModel: MenuGuestViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_meni_guest, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repository = Add_mealRepository()
        val viewModelFactory = MenuGuestModelFactory(repository)

        lokal = "1"
            //requireArguments().getInt("restaurant_id").toString()

        viewModel = ViewModelProvider(this, viewModelFactory).get(MenuGuestViewModel::class.java)
        viewModel.getMeal(table = "Stavka_jelovnika", method = "select", lokal)
        viewModel.myResponse.observe(viewLifecycleOwner, {
            val response = it.body()
            if (response != null) {
                recycleViewMenuGuest.layoutManager = LinearLayoutManager(activity)
                recycleViewMenuGuest.adapter = MealGuestListAdapter(response, this)
            }
        })
    }

    fun callOrderMeal(mealId: String){
        val meal = mealId

        //val action = MenuFragmentDirections.actionMeniFragmentToEditMealFragment2(meal)
        //findNavController().navigate(action)
    }
}