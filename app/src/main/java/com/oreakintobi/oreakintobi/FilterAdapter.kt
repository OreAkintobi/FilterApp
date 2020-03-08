package com.oreakintobi.oreakintobi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oreakintobi.oreakintobi.databinding.FilterItemBinding

class FilterAdapter(
    private var filtersList: List<Account>,
    private var context: Context,
    private val clickListener: (Account) -> Unit
) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {
    class ViewHolder(private val binding: FilterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Account, clickListener: (result: Account) -> Unit) {
            binding.filterList = item
            binding.root.setOnClickListener {
                clickListener(item)
            }
            Glide.with(binding.root.context).asBitmap().load(item.avatar).into(binding.smallimage)

            val colour = item.colors

            if (colour.isNotEmpty()) {
                binding.showWhenTextUnavailable.visibility = View.GONE
                binding.colourLayout.visibility = View.VISIBLE

                Glide.with(binding.root.context).asBitmap().error(R.drawable.ic_android_black_24dp)
                    .load(R.drawable.green).into(binding.button1)
                Glide.with(binding.root.context).asBitmap().error(R.drawable.ic_android_black_24dp)
                    .load(R.drawable.violet).into(binding.button2)
                Glide.with(binding.root.context).asBitmap().error(R.drawable.ic_android_black_24dp)
                    .load(R.drawable.yellow).into(binding.button3)
                Glide.with(binding.root.context).asBitmap().error(R.drawable.ic_android_black_24dp)
                    .load(R.drawable.blue).into(binding.button4)
                Glide.with(binding.root.context).asBitmap().error(R.drawable.ic_android_black_24dp)
                    .load(R.drawable.teal).into(binding.button5)
                Glide.with(binding.root.context).asBitmap().error(R.drawable.ic_android_black_24dp)
                    .load(R.drawable.maroon).into(binding.button6)
                Glide.with(binding.root.context).asBitmap().error(R.drawable.ic_android_black_24dp)
                    .load(R.drawable.circle).into(binding.button7)
                Glide.with(binding.root.context).asBitmap().error(R.drawable.ic_android_black_24dp)
                    .load(R.drawable.aquamarine).into(binding.button8)
                Glide.with(binding.root.context).asBitmap().error(R.drawable.ic_android_black_24dp)
                    .load(R.drawable.orange).into(binding.button9)
                Glide.with(binding.root.context).asBitmap().error(R.drawable.ic_android_black_24dp)
                    .load(R.drawable.mau).into(binding.button10)
            } else {

                //Hide View
                binding.showWhenTextUnavailable.visibility = View.VISIBLE
                binding.colourLayout.visibility = View.GONE
            }

            val countries = item.countries
            if (countries.isNotEmpty()) {
                binding.showWhenCountryUnavailable.visibility = View.GONE
                binding.countryView.visibility = View.VISIBLE
                binding.first.text = "China"
                binding.second.text = "South Africa"
                binding.third.text = "France"
                binding.fourt.text = "Mexico"
                binding.fivth.text = "Japan"
                binding.sixth.text = "Estonia"
                binding.seventh.text = "Colombia"
                binding.eighth.text = "China"

            } else {
                binding.showWhenCountryUnavailable.visibility = View.VISIBLE
                binding.countryView.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
        val binding = FilterItemBinding.inflate(view)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filtersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(filtersList[position], clickListener)
}