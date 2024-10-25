package com.example.noteapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.data.models.NoteModel
import com.example.noteapp.databinding.ItemNoteBinding
import com.example.noteapp.ui.interFace.OnClickItem

class NoteAdapter(private val onLongClick: OnClickItem, private val onClick: OnClickItem) :
    ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noteModel = getItem(position)
        holder.bind(noteModel)
        holder.itemView.setOnLongClickListener {
            if (position + 1 < itemCount) {
                onLongClick.onLongClick(getItem(position + 1))
            } else {
                onLongClick.onLongClick(getItem(position))
            }
            true
        }

        holder.itemView.setOnClickListener {
            onClick.onClick(getItem(position))
        }
        val context = holder.itemView.context
        val backgroundColor = if (noteModel.backgroundColor != 0) {
            noteModel.backgroundColor
        } else {
            context.getColor(R.color.btn_gray)
        }
        holder.itemView.setBackgroundColor(backgroundColor)
    }

    class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(noteModel: NoteModel) {
            binding.tvTitle.text = noteModel.title
            binding.tvDescription.text = noteModel.description
            binding.tvTime.text = noteModel.creationTime

        }
    }


    class DiffUtilCallBack : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }

    }
}