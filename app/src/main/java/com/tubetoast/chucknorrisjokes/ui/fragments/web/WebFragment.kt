package com.tubetoast.chucknorrisjokes.ui.fragments.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tubetoast.chucknorrisjokes.databinding.FragmentWebBinding
import com.tubetoast.chucknorrisjokes.ui.BackHandler
import com.tubetoast.chucknorrisjokes.ui.MainActivity
import com.tubetoast.chucknorrisjokes.viewmodel.web.WebViewModel

class WebFragment : Fragment(), BackHandler {

    private var _binding : FragmentWebBinding? = null
    private val binding : FragmentWebBinding get() = _binding!!

    private val webViewModel by activityViewModels<WebViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?,
            ): Boolean {
                webViewModel.setUrl(request?.url.toString())
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        if (savedInstanceState == null) {
            binding.webView.settings.cacheMode = WebSettings.LOAD_DEFAULT;
        } else {
            binding.webView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK;
        }
        binding.webView.loadUrl(webViewModel.getUrl().value!!)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).topFragment = this
    }

    override fun onPause() {
        super.onPause()
        (requireActivity() as MainActivity).topFragment = null
    }

    override fun onBackPressed(): Boolean {
        val canGoBack = binding.webView.canGoBack()
        if (canGoBack)
            binding.webView.goBack()
        return canGoBack
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
