package com.oreakintobi.oreakintobi.view.filterList

import android.graphics.PorterDuff
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import androidx.databinding.BindingAdapter
import com.oreakintobi.oreakintobi.R
import com.oreakintobi.oreakintobi.models.FilterElement
import com.oreakintobi.oreakintobi.util.Color

@BindingAdapter("gender")
fun TextView.setGender(item: FilterElement?) {
    item?.let {
        val res = item.gender
        text = when (res) {
            "male" -> {
                context.getString(R.string.male)
            }
            "female" -> {
                context.getString(R.string.female)
            }
            else -> {
                context.getString(R.string.all_gender)
            }
        }
    }
}

@BindingAdapter("countries")
fun LinearLayout.setCountries(item: FilterElement?) {
    item?.let { element ->
        if (element.countries.isEmpty()) {
            val childLayout = buildChildLayout()
            val view = buildTextView("All countries")
            childLayout.addView(view)
            addView(childLayout)
        } else {
            element.countries.map { country ->
                val childLayout = buildChildLayout()
                val view = buildTextView(country)
                childLayout.addView(view)
                addView(childLayout)
            }
        }
    }
}

@BindingAdapter("colors")
fun LinearLayout.setColors(item: FilterElement?) {

    item?.let { element ->
        if (element.colors.isEmpty()) {
            val colors = listOf(
                "Green",
                "Violet",
                "Yellow",
                "Blue",
                "Teal",
                "Maroon",
                "Red",
                "Aquamarine",
                "Orange",
                "Mauv",
                "Puce",
                "Indigo",
                "Turquoise",
                "Goldenrod",
                "Pink",
                "Fuscia",
                "Crimson",
                "Khaki"
            )
            colors.map { color ->
                val childLayout = buildChildLayout()
                val view = buildAllColor(color)
                childLayout.addView(view)
                addView(childLayout)
            }
        } else {
            element.colors.map { color ->
                val childLayout = buildChildLayout()
                val view = buildColor(color)
                childLayout.addView(view)
                addView(childLayout)
            }
        }
    }
}

private fun LinearLayout.buildColor(color: String): TextView {
    val view = TextView(this.context)
    val layoutParams = LinearLayout.LayoutParams(64, 64)
    layoutParams.setMargins(8)
    view.layoutParams = layoutParams
    val drawable = ContextCompat.getDrawable(context, R.drawable.color_bg)
    drawable?.mutate()?.setColorFilter(
        when (color) {
            Color.RED.color -> ContextCompat.getColor(context, R.color.red)
            Color.GREEN.color -> ContextCompat.getColor(context, R.color.green)
            Color.VIOLET.color -> ContextCompat.getColor(context, R.color.violet)
            Color.YELLOW.color -> ContextCompat.getColor(context, R.color.yellow)
            Color.BLUE.color -> ContextCompat.getColor(context, R.color.blue)
            Color.TEAL.color -> ContextCompat.getColor(context, R.color.teal)
            Color.MAROON.color -> ContextCompat.getColor(context, R.color.maroon)
            Color.AQUAMARINE.color -> ContextCompat.getColor(
                context,
                R.color.aquamarine
            )
            Color.ORANGE.color -> ContextCompat.getColor(context, R.color.orange)
            Color.MAUV.color -> ContextCompat.getColor(context, R.color.mauv)
            Color.PUCE.color -> ContextCompat.getColor(context, R.color.puce)
            Color.INDIGO.color -> ContextCompat.getColor(context, R.color.indigo)
            Color.TURQUOISE.color -> ContextCompat.getColor(context, R.color.turquoise)
            Color.GOLDENROD.color -> ContextCompat.getColor(context, R.color.goldenrod)
            Color.FUSCIA.color -> ContextCompat.getColor(context, R.color.fushcia)
            Color.PINK.color -> ContextCompat.getColor(context, R.color.pink)
            Color.CRIMSON.color -> ContextCompat.getColor(context, R.color.crimson)
            Color.KHAKI.color -> ContextCompat.getColor(context, R.color.khaki)
            else -> ContextCompat.getColor(context, R.color.black)
        }, PorterDuff.Mode.SRC_IN
    )

    view.background = drawable
    return view
}

private fun LinearLayout.buildAllColor(color: String): TextView {
    val view = TextView(this.context)
    val layoutParams = LinearLayout.LayoutParams(64, 64)
    layoutParams.setMargins(8)
    view.layoutParams = layoutParams
    val drawable = ContextCompat.getDrawable(context, R.drawable.color_bg)
    drawable?.mutate()?.setColorFilter(
        when (color) {
            Color.RED.color -> ContextCompat.getColor(context, R.color.red)
            Color.GREEN.color -> ContextCompat.getColor(context, R.color.green)
            Color.VIOLET.color -> ContextCompat.getColor(context, R.color.violet)
            Color.YELLOW.color -> ContextCompat.getColor(context, R.color.yellow)
            Color.BLUE.color -> ContextCompat.getColor(context, R.color.blue)
            Color.TEAL.color -> ContextCompat.getColor(context, R.color.teal)
            Color.MAROON.color -> ContextCompat.getColor(context, R.color.maroon)
            Color.AQUAMARINE.color -> ContextCompat.getColor(
                context,
                R.color.aquamarine
            )
            Color.ORANGE.color -> ContextCompat.getColor(context, R.color.orange)
            Color.MAUV.color -> ContextCompat.getColor(context, R.color.mauv)
            Color.PUCE.color -> ContextCompat.getColor(context, R.color.puce)
            Color.INDIGO.color -> ContextCompat.getColor(context, R.color.indigo)
            Color.TURQUOISE.color -> ContextCompat.getColor(context, R.color.turquoise)
            Color.GOLDENROD.color -> ContextCompat.getColor(context, R.color.goldenrod)
            Color.FUSCIA.color -> ContextCompat.getColor(context, R.color.fushcia)
            Color.PINK.color -> ContextCompat.getColor(context, R.color.pink)
            Color.CRIMSON.color -> ContextCompat.getColor(context, R.color.crimson)
            Color.KHAKI.color -> ContextCompat.getColor(context, R.color.khaki)
            else -> ContextCompat.getColor(context, R.color.black)
        }, PorterDuff.Mode.SRC_IN
    )

    view.background = drawable
    return view
}


private fun LinearLayout.buildChildLayout(): LinearLayout {
    val childLayout = LinearLayout(context)
    val linearParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    childLayout.layoutParams = linearParams
    childLayout.gravity = Gravity.CENTER
    return childLayout
}

private fun LinearLayout.buildTextView(country: String): TextView {
    val view = TextView(this.context)
    val layoutParams = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    view.text = country
    view.setTextColor(ContextCompat.getColor(context, R.color.black))
    view.setPadding(32, 16, 32, 16)
    layoutParams.setMargins(8)
    view.layoutParams = layoutParams
    val drawable = ContextCompat.getDrawable(context, R.drawable.country_bg)
    view.background = drawable
    return view
}

