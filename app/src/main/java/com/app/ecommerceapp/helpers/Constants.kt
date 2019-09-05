package com.app.ecommerceapp.helpers

import com.app.ecommerceapp.models.Person

class Constants {

    companion object {
        val PointURLAmountfragment = "http://192.168.1.124/OnlineStoreApp/insert_temporary_order.php?email=${Person.email}&product_id=${Person.addToCartProductID}&amount="
        val PIC_URL = "http://192.168.1.124/OnlineStoreApp/osimages/"
        val BRAND_SELECTED = "BRAND"
        val PRODUCT_URL = "http://192.168.1.124/OnlineStoreApp/fetch_eproducts.php?brand="
        val DELETE_URL = "http://192.168.1.124//OnlineStoreApp/decline_order.php?email=${Person.email}"
        val VERIFY_ORDER_URL = "http://192.168.1.124/OnlineStoreApp/verify_order.php?email=${Person.email}"
        val LATERST_INVOICE = "LATEST_INVOICE_NUMBER"
        val CALCULATE_TOTAL_PRICE_URL = "http://192.168.1.124//OnlineStoreApp/calculate_total_price.php?invoice_num="
        val BRANDS_URL = "http://192.168.1.124/OnlineStoreApp/fetch_brands.php"
    }

}