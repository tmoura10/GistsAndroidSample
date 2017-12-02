package br.com.tmoura.gists.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import br.com.tmoura.gists.R
import br.com.tmoura.gists.extensions.inflate
import br.com.tmoura.gists.presentation.model.GistItemViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.gist_list_item.view.gistLanguage
import kotlinx.android.synthetic.main.gist_list_item.view.gistTitle
import kotlinx.android.synthetic.main.gist_list_item.view.ownerAvatar
import kotlinx.android.synthetic.main.gist_list_item.view.ownerName

class GistListAdapter(var items: List<GistItemViewModel>)
    : RecyclerView.Adapter<AbstractViewHolder<GistItemViewModel>>() {

    override fun onBindViewHolder(holder: AbstractViewHolder<GistItemViewModel>?, position: Int) {
        holder?.bind(items[holder.adapterPosition])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder<GistItemViewModel> {
        val itemView = parent.inflate(viewType)
        return GistItem(itemView)
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = R.layout.gist_list_item

}

class GistItem(itemView: View) : AbstractViewHolder<GistItemViewModel>(itemView) {

    override fun bind(item: GistItemViewModel) = with(itemView) {
        Glide.with(itemView).load(item.ownerAvatarUrl).into(ownerAvatar)
        gistTitle.text = item.title
        gistLanguage.text = item.language
        ownerName.text = item.ownerName
    }
}