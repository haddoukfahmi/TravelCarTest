package com.travelcar_test.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.TextAppearanceSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.travelcar_test.R
import com.travelcar_test.data.model.Cars
import java.util.*


class CarsAdapater : RecyclerView.Adapter<CarsAdapater.CarsViewHolder>() {

    var carList = mutableListOf<Cars>()
    var searchText: String = ""

    fun getSearchText(searchText: String) {
        this.searchText = searchText
    }

    fun setCars(list: List<Cars>) {
        with(carList) {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        return CarsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cars_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        var cars = carList[position]

        holder.bind(cars, searchText)
    }

    class CarsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val makeTextView = itemView.findViewById<TextView>(R.id.nameTextView)
        private val modelTextView = itemView.findViewById<TextView>(R.id.modelTextView)
        private val pictureImageView = itemView.findViewById<ImageView>(R.id.pictureImageView)
        private val equipementTextView = itemView.findViewById<TextView>(R.id.equipmentsTextView)

        fun bind(car: Cars, searchText: String) {
            if (searchText.length > 0 && !searchText.isEmpty()) {
                val startPos: Int =
                    car.make.toLowerCase(Locale.US).indexOf(searchText.toLowerCase(Locale.US))
                val endPos: Int = startPos + searchText.length

                if (startPos != -1) {
                    val spannable = SpannableString(car.make)
                    val highlightSpan = TextAppearanceSpan(
                        null,
                        Typeface.BOLD,
                        -1,
                        ColorStateList.valueOf(Color.BLUE),
                        null
                    )
                    spannable.setSpan(
                        highlightSpan,
                        startPos,
                        endPos,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    makeTextView.text = spannable
                } else {
                    makeTextView.text = car.make
                }
            } else {
                makeTextView.text = car.make
            }

            modelTextView.text = String.format("%s %s", car.model, car.year)
            val requestOption = RequestOptions()
                .placeholder(android.R.drawable.stat_notify_error)
            Glide.with(itemView.context)
                .load(car.picture)
                .apply(requestOption)
                .into(pictureImageView!!)
            equipementTextView.text = car.equipments?.joinToString("\n").orEmpty()
        }
    }

}