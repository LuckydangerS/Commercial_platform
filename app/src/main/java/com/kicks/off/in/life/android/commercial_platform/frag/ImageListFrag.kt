package com.kicks.off.`in`.life.android.commercial_platform.frag

import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.kicks.off.`in`.life.android.commercial_platform.R
import com.kicks.off.`in`.life.android.commercial_platform.databinding.ListImageFragBinding
import com.kicks.off.`in`.life.android.commercial_platform.dialoghelper.ProgressDialog
import com.kicks.off.`in`.life.android.commercial_platform.utils.ImageManager
import com.kicks.off.`in`.life.android.commercial_platform.utils.ImagePicker
import com.kicks.off.`in`.life.android.commercial_platform.utils.ItemTouchMoveCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ImageListFrag(private val fragCloseInterface : FragmentCloseInterface,
                    private val newList : ArrayList<String>?) : Fragment(), AdapterCallback {
    lateinit var binding : ListImageFragBinding
    val adapter = SelectImageRvAdapter(this)
    private val dragCallback = ItemTouchMoveCallback(adapter)
    val touchHelper = ItemTouchHelper(dragCallback)
    private var job: Job? = null
    private var addImageItem: MenuItem? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListImageFragBinding.inflate(inflater)
        return binding.root
       // (базовая версия) return inflater.inflate(R.layout.list_image_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()

        // (базовая версия) val rcView = view.findViewById<RecyclerView>(R.id.rcViewSelectImage)
        touchHelper.attachToRecyclerView(binding.rcViewSelectImage)
        binding.rcViewSelectImage.layoutManager = LinearLayoutManager(activity)
        binding.rcViewSelectImage.adapter = adapter
        if (newList != null) resizeSelectedImages(newList, true)

    }
    override fun onItemDelete() {
        addImageItem?.isVisible = true
    }

    fun updateAdapterFromEdit(bitmapList: List<Bitmap>){
        adapter.updateAdapter(bitmapList, true)
    }

    override fun onDetach() {
        super.onDetach()
        fragCloseInterface.onFragClose(adapter.mainArray)
        job?.cancel()
    }

    private fun resizeSelectedImages(newList: ArrayList<String>, needClear: Boolean){
        job = CoroutineScope(Dispatchers.Main).launch {
            val dialog = ProgressDialog.createProgressDialog(activity as Activity)
            val bitmapList = ImageManager.imageResize(newList)
            dialog.dismiss()
            adapter.updateAdapter(bitmapList, needClear)
            if (adapter.mainArray.size > 2) addImageItem?.isVisible = false
        }
    }

    private fun setUpToolbar(){

        binding.tb.inflateMenu(R.menu.menu_choose_image)
        val deleteItem = binding.tb.menu.findItem(R.id.id_delete_image)
        addImageItem = binding.tb.menu.findItem(R.id.id_add_image)

        binding.tb.setNavigationOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        deleteItem.setOnMenuItemClickListener {
            adapter.updateAdapter(ArrayList(), true)
            addImageItem?.isVisible = true
            true
        }
        addImageItem?.setOnMenuItemClickListener {
            val imageCount = ImagePicker.MAX_IMAGE_COUNT - adapter.mainArray.size
            ImagePicker.getImages(activity as AppCompatActivity, imageCount, ImagePicker.REQUEST_CODE_GET_IMAGES)
            true
        }
    }

    fun updateAdapter(newList : ArrayList<String>) {resizeSelectedImages(newList, false)}




    fun  setSingleImage(uri : String, pos : Int){
        val pBar = binding.rcViewSelectImage[pos].findViewById<ProgressBar>(R.id.pBar)

        job = CoroutineScope(Dispatchers.Main).launch {
            pBar.visibility = View.VISIBLE
            val bitmapList = ImageManager.imageResize(listOf(uri))
            pBar.visibility = View.GONE
            adapter.mainArray[pos] = bitmapList[0]
            adapter.notifyItemChanged(pos)
        }

    }


}