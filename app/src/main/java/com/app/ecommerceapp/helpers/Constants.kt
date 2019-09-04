package com.app.ecommerceapp.helpers

import com.app.ecommerceapp.models.Person

class Constants {

    companion object {
        val PointURLAmountfragment = "http://192.168.1.124/OnlineStoreApp/insert_temporary_order.php?email=${Person.email}&product_id=${Person.addToCartProductID}&amount="
        val PIC_URL = "\"http://192.168.1.124/OnlineStoreApp/osimages/\""
        val BRAND_SELECTED = "BRAND"
        val PRODUCT_URL = "http://192.168.1.124/OnlineStoreApp/fetch_eproducts.php?brand="
    }

}