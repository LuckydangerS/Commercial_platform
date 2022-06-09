package com.kicks.off.`in`.life.android.commercial_platform.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kicks.off.`in`.life.android.commercial_platform.R

class ImageListFrag(private val fragCloseInterface : FragmentCloseInterface,
                    private val newList : ArrayList<String>) : Fragment() {
    val adapter = SelectImageRvAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_image_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bBack = view.findViewById<Button>(R.id.bBack)
        val rcView = view.findViewById<RecyclerView>(R.id.rcViewSelectImage)
        rcView.layoutManager = LinearLayoutManager(activity)
        val updatelist = ArrayList<SelectImageItem>()
        for(n in 0 until newList.size){
            updatelist.add(SelectImageItem(n.toString(), newList[n]))
        }
        rcView.adapter = adapter
        adapter.updateAdapter(updatelist)
        bBack.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragCloseInterface.onFragClose()
    }
}