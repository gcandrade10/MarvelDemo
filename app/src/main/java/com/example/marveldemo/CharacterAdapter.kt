package com.example.marveldemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.CharacterModel
import com.example.marveldemo.databinding.CharacterItemBinding

class CharacterAdapter(
    private val characters: List<CharacterModel>,
    private val listener: (CharacterModel) -> Unit
) :
    RecyclerView.Adapter<CharacterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemBinding =
            CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(itemBinding, listener)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount() = characters.size

}

class CharacterViewHolder(
    private val itemBinding: CharacterItemBinding,
    private val listener: (CharacterModel) -> Unit
) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(character: CharacterModel) {
        Glide.with(itemBinding.imageThumbnail.context)
            .load(character.image)
            .centerCrop()
            .into(itemBinding.imageThumbnail)
        itemBinding.tvItem.text = character.name
        itemBinding.root.setOnClickListener { listener(character) }
    }
}