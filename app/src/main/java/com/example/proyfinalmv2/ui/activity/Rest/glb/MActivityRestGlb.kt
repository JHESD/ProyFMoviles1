package com.example.proyfinalmv2.ui.activity.Rest.glb

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyfinalmv2.R
import com.example.proyfinalmv2.databinding.ActivityInvitadoBinding
import com.example.proyfinalmv2.databinding.ActivityMrestGlbBinding
import com.example.proyfinalmv2.models.Rest.RestSnFilt
import com.example.proyfinalmv2.ui.activity.Rest.inv.Activity_InvDetalle
import com.example.proyfinalmv2.ui.adapters.RestAdapter
import com.example.proyfinalmv2.ui.viewmodels.ActRestGlbModel
import com.example.proyfinalmv2.ui.viewmodels.ActivityInvitadoModel

class MActivityRestGlb : AppCompatActivity(), RestAdapter.OnRestInvListener {
    lateinit var binding: ActivityMrestGlbBinding
    private val model: ActRestGlbModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMrestGlbBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        setupViewModelListeners()
    }

    // Ejecutables
    override fun onResume() {
        super.onResume()
        model.fetchLstRestInv(0)
    }

    private fun setupViewModelListeners() {
        model.restInv.observe(this) {
            (binding.rcvListGlb.adapter as RestAdapter).updateData(it)
        }

    }

    private fun setupRecyclerView() {
        binding.rcvListGlb.apply {
            layoutManager = LinearLayoutManager(
                this@MActivityRestGlb
            )
            adapter = RestAdapter(
                arrayListOf(),
                this@MActivityRestGlb
            )
        }
    }

    override fun onRestInvClick(restinv: RestSnFilt) {
        Log.d("Usuarios", "Se hizo click en el Usuario: ${restinv.name}")
        val intent = Intent(this, Activity_InvDetalle::class.java)
        intent.putExtra("IdRest",restinv.id)
        startActivity(intent)
    }
}