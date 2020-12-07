package ru.maxdexter.mytranslatormvvm.ui.searchfragment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.util.Util
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.maxdexter.mytranslatormvvm.R
import ru.maxdexter.mytranslatormvvm.databinding.SearchFragmentBinding

class SearchFragment : BottomSheetDialogFragment() {
    var onClickListener:((String)->Unit)? = null

    fun setClickListener(listener:(String)-> Unit){
        onClickListener = listener
    }
    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: SearchFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.search_fragment, container,false)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        binding.etSearch.addTextChangedListener(textWatcher)
        return binding.root
    }

    private var textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                if (s.length >= 2){
                    onClickListener?.invoke(s.toString())
                }
            }
        }
    }



}