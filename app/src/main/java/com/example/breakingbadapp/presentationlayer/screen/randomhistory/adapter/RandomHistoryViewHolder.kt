package com.example.breakingbadapp.presentationlayer.screen.randomhistory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbadapp.App
import com.example.breakingbadapp.R
import com.example.breakingbadapp.databinding.CharacterResponseRowBinding
import com.example.breakingbadapp.databinding.HistoryHeaderItemBinding
import com.example.breakingbadapp.databinding.ResponseHistoryFooterBinding
import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.datalayer.model.LoadPhotoConfig
import com.example.breakingbadapp.domainlayer.service.imageloader.ImageLoader
import javax.inject.Inject

sealed class RandomHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @Inject
    lateinit var imageLoader: ImageLoader

    init {
        App.appComponent.inject(this)
    }

    sealed class ItemHolder(itemView: View) : RandomHistoryViewHolder(itemView) {
        private val binding = CharacterResponseRowBinding.bind(itemView)

        fun bind(character: CharacterResponse, listener: RandomHistoryViewHolderListener?) {
            with (binding) {
                characterName.text = character.name
                characterStatus.text = character.status
                characterNickname.text = character.nickname
                characterActor.text = character.actor
                responseDatetime.text = character.datetime

                character.picture_url?.let {
                    val config = LoadPhotoConfig(url = it, placeholder = R.drawable.avatar_placeholder)
                    imageLoader.load(config, characterPicture)
                }

                removeIcon.setOnClickListener {
                    listener?.onDeleteItem(character)
                }
            }
        }

        class MiddleItemHolder(itemView: View) : ItemHolder(itemView) {
            companion object {
                fun create(parent: ViewGroup): MiddleItemHolder {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.character_response_row, parent, false)
                    return MiddleItemHolder(view)
                }
            }
        }

        class FirstItemHolder(itemView: View) : ItemHolder(itemView) {
            companion object {
                fun create(parent: ViewGroup): MiddleItemHolder {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.character_response_row_first, parent, false)
                    return MiddleItemHolder(view)
                }
            }
        }

        class LastItemHolder(itemView: View) : ItemHolder(itemView) {
            companion object {
                fun create(parent: ViewGroup): MiddleItemHolder {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.character_response_last, parent, false)
                    return MiddleItemHolder(view)
                }
            }
        }

        class SingleItemHolder(itemView: View) : ItemHolder(itemView) {
            companion object {
                fun create(parent: ViewGroup): SingleItemHolder {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.character_response_row_single, parent, false)
                    return SingleItemHolder(view)
                }
            }
        }
    }

    class HeaderItemHolder(itemView: View) : RandomHistoryViewHolder(itemView) {

        private val binding = HistoryHeaderItemBinding.bind(itemView)

        fun bind(header: String) {
            binding.headerText.text = header
        }

        companion object {
            fun create(parent: ViewGroup): HeaderItemHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.history_header_item, parent, false)
                return HeaderItemHolder(view)
            }
        }
    }

    class FooterHolder(itemView: View) : RandomHistoryViewHolder(itemView) {
        private val binding = ResponseHistoryFooterBinding.bind(itemView)

        fun bind(listener: HolderListener?) {
            binding.clearHistoryButton.setOnClickListener { listener?.onClearHistory() }
        }

        interface HolderListener {
            fun onClearHistory()
        }

        companion object {
            fun create(parent: ViewGroup): FooterHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.response_history_footer, parent, false)
                return FooterHolder(view)
            }
        }
    }

    sealed class Model {
        data class Header(val header: String) : Model()
        data class Item(val response: CharacterResponse) : Model()
        data class Footer(val type: String = "footer") : Model()
    }

    companion object {
        fun create(parent: ViewGroup, viewType: Int): RandomHistoryViewHolder =
            when (RandomHistoryHolderType.getType(viewType)) {
                RandomHistoryHolderType.FIRST -> ItemHolder.FirstItemHolder.create(parent)
                RandomHistoryHolderType.LAST -> ItemHolder.LastItemHolder.create(parent)
                RandomHistoryHolderType.MIDDLE -> ItemHolder.MiddleItemHolder.create(parent)
                RandomHistoryHolderType.HEADER -> HeaderItemHolder.create(parent)
                RandomHistoryHolderType.FOOTER -> FooterHolder.create(parent)
                RandomHistoryHolderType.SINGLE -> ItemHolder.SingleItemHolder.create(parent)
            }
    }
}