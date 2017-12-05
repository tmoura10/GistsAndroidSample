package br.com.tmoura.gists.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import br.com.tmoura.gists.adapter.GistListAdapter
import br.com.tmoura.gists.adapter.OnGistItemSelected
import br.com.tmoura.gists.presentation.model.GistItemViewModel

class GistListRecyclerView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    var items: MutableList<GistItemViewModel> = mutableListOf()
    var isLoadingItems = false
    var onRequestMoreItems: ((Int) -> Unit)? = null
    var onItemSelected: OnGistItemSelected? = null
        set(value) {
            (adapter as GistListAdapter).onItemSelected = value
        }

    init {
        adapter = GistListAdapter(items, onItemSelected)
        layoutManager = LinearLayoutManager(context)
        setupLoader()
    }

    private fun setupLoader() {
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            val threshold = 3
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = this@GistListRecyclerView.layoutManager
                if (layoutManager is LinearLayoutManager
                        && shouldLoadMoreItems(layoutManager)) {
                    loadMoreItems()
                }
            }

            private fun shouldLoadMoreItems(layoutManager: LinearLayoutManager): Boolean {
                val count = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                return (!isLoadingItems && count <= (lastVisibleItem + threshold))
            }
        })
    }

    private fun loadMoreItems() {
        onRequestMoreItems?.invoke(items.size)
        isLoadingItems = true
    }

    fun add(newItems: List<GistItemViewModel>) {
        val positionStart = items.size
        items.addAll(newItems)
        adapter.notifyItemRangeInserted(positionStart, newItems.size)
        isLoadingItems = false
    }

    fun set(newItems: List<GistItemViewModel>) {
        items = newItems.toMutableList()
        (adapter as GistListAdapter).items = items
        adapter.notifyDataSetChanged()
        isLoadingItems = false
    }

}