package com.app.ecommerceapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.ecommerceapp.R
import com.app.ecommerceapp.helpers.Constants
import com.app.ecommerceapp.models.EProduct
import com.app.ecommerceapp.models.Person
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.design_item_view.view.*

class EProductAdapterItemView(val context: Context, var items:ArrayList<EProduct>, val eventClick: OnEventAction): RecyclerView.Adapter<EProductAdapterItemView.CustomViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.design_item_view, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        (holder as CustomViewHolder).initializeRowUIComponents(items[position].id, items[position].name, items[position].price, items[position].pictureName)
    }

    interface OnEventAction{
        fun EventSelected(position: Int)
    }

    inner class CustomViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageItem = view.image_itemList
        val textItemName = view.text_itemList_name
        val textItemId = view.text_itemList_id
        val textItemPrice = view.text_itemList_price
        val imageADD = view.imgAdd

        fun initializeRowUIComponents(id: Int, name: String, price: Int, picName: String){
            textItemName.text = name
            textItemId.text = id.toString()
            textItemPrice.text = price.toString()

            var picUrl = Constants.PIC_URL
            picUrl = picUrl.replace(" ", "%20")
            Picasso.get().load(picUrl+picName).into(imageItem)

            imageADD.setOnClickListener {

                Person.addToCartProductID = id
                eventClick.EventSelected(adapterPosition)

            }
        }
    }
}