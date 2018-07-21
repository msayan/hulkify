package com.wololo.hulkify.ui.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.wololo.hulkify.R


class GalleryAdapter(context: Context) : ArrayAdapter<String>(context, 0) {

    override fun getView(position: Int, contentView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        var view = contentView
        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.item_gallery, parent, false)
            holder = ViewHolder(view)
            view.setTag(holder)
        } else {
            holder = view.getTag() as ViewHolder
        }

        val url = getItem(position)

        Picasso.get().load(url).into(holder.image)

        return view!!
    }

    private class ViewHolder(view: View) {
        var image: ImageView

        init {
            this.image = view.findViewById(R.id.gallery_image)
        }
    }

}