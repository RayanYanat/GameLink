package com.example.gamelink.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gamelink.R
import kotlinx.android.synthetic.main.fragment_lol_communaute.*

class LolCommunauteFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate((R.layout.fragment_lol_communaute), container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        discord_lol_btn.setOnClickListener {
            val data = "https://discord.com/invite/leagueoflegendsfr"
            val defaultBrowser =
                Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
            defaultBrowser.data = Uri.parse(data)
            startActivity(defaultBrowser)
        }
    }
}